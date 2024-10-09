package com.veterinaria.tailvet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.veterinaria.tailvet.service.DrogaService;
import com.veterinaria.tailvet.model.Droga;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/drogas")
@CrossOrigin(origins = "http://localhost:4200")
public class DrogaController {

    private final DrogaService drogaService;

    public DrogaController(DrogaService drogaService) {
        this.drogaService = drogaService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> cargarExcel(@RequestParam("file") MultipartFile file) {
        Map<String, String> response = new HashMap<>();

        // Validación básica del archivo
        if (file.isEmpty()) {
            response.put("message", "El archivo está vacío.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            List<Droga> drogas = new ArrayList<>();

            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) { // Saltamos la primera fila (cabecera)
                Row row = sheet.getRow(i);
                if (row == null) continue; // Saltar filas vacías

                Droga droga = new Droga();
                droga.setNombre(getStringValue(row.getCell(0)));
                droga.setPrecioVenta(getNumericValue(row.getCell(1)));
                droga.setPrecioCompra(getNumericValue(row.getCell(2)));
                droga.setUnidadesDisponibles((int) getNumericValue(row.getCell(3)));
                droga.setUnidadesVendidas((int) getNumericValue(row.getCell(4)));

                drogas.add(droga);
            }

            // Guardar todas las drogas
            drogaService.guardarTodas(drogas);
            response.put("message", "Datos cargados con éxito");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (IOException e) {
            response.put("message", "Error al procesar el archivo: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para obtener valores de celdas de manera segura
    private String getStringValue(Cell cell) {
        return cell != null ? cell.getStringCellValue() : "";
    }

    private double getNumericValue(Cell cell) {
        return cell != null && cell.getCellType() == CellType.NUMERIC ? cell.getNumericCellValue() : 0;
    }
}
