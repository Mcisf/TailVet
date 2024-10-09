package com.veterinaria.tailvet.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError() {
        // Aquí puedes añadir lógica adicional si lo necesitas
        return "error"; // Retorna la vista error.html
    }
    
    public String getErrorPath() {
        return "/error";
    }
}
