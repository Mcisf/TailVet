package com.veterinaria.tailvet.service;

import com.veterinaria.tailvet.model.Usuario;
import com.veterinaria.tailvet.model.Mascota;
import com.veterinaria.tailvet.repository.UsuarioRepository;
import com.veterinaria.tailvet.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public boolean authenticate(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        return usuario != null && usuario.getPassword().equals(password);
    }

    public Usuario obtenerUsuarioPorCedula(String cedula) {
        return usuarioRepository.findByCedula(cedula); // Asegúrate de tener este método en tu repositorio
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public boolean registrarUsuario(String nombre, String email, String password, String direccion, String telefono, int edad, double peso, String imagenUrl) {
        try {
            if (usuarioRepository.findByEmail(email) != null) {
                return false;
            }

            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setPassword(password);
            usuario.setDireccion(direccion);
            usuario.setTelefono(telefono);

            Usuario savedUsuario = save(usuario);

            Mascota mascota = new Mascota();
            mascota.setNombre("NombreMascotaEjemplo");
            mascota.setRaza("RazaEjemplo");
            mascota.setEdad(edad);
            mascota.setPeso(peso);
            mascota.setImagen(imagenUrl);
            mascota.setUsuario(savedUsuario);

            mascotaRepository.save(mascota);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public List<Mascota> findAllMascotas() {
        return mascotaRepository.findAll();
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public void deleteMascota(Long id) {
        mascotaRepository.deleteById(id);
    }

    public Usuario updateUsuario(Long id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            usuario.setId(id);
            return usuarioRepository.save(usuario);
        }
        return null;
    }
    

    public Mascota updateMascota(Mascota mascota) {
        if (mascotaRepository.existsById(mascota.getId())) {
            return mascotaRepository.save(mascota);
        }
        return null;
    }
}
