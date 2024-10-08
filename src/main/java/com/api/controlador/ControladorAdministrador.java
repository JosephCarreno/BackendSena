package com.api.controlador;

import com.api.entidades.Administrador;
import com.api.excepciones.ResourseNotFoundException;
import com.api.servicios.ServicioAdministrador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api-administrador")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorAdministrador {

    public static final Logger logger = LoggerFactory.getLogger(ControladorAdministrador.class);

    @Autowired
    private ServicioAdministrador servicioAdministrador;


    @GetMapping("/administrador")
    public List<Administrador> listaAdministrador(){
        List<Administrador> listaAdministradores = this.servicioAdministrador.listaAdministrador();
        logger.info("Lista de administradores obtenidos");
        listaAdministradores.forEach(administrador -> logger.info(administrador.toString()));
        return listaAdministradores;
    }

    @GetMapping("/administrador/{id}")
    public ResponseEntity<Administrador> buscarAdministradorPorId(@PathVariable Long id){
        Administrador administradorEncontrado = this.servicioAdministrador.buscarAdministradorPorId(id);
        if(administradorEncontrado != null){
            return ResponseEntity.ok(administradorEncontrado);
        }else{
            throw new ResourseNotFoundException("No se encontró el ID");
        }

    }

    @PostMapping("/administrador")
    @ResponseStatus(HttpStatus.CREATED)
    public Administrador guardarAdministrador(@RequestBody Administrador administrador){
        logger.info("Administrador agregado: {}", administrador);
        return this.servicioAdministrador.guardarAdministrador(administrador);
    }

    @PutMapping("/administrador/{id}")
    public ResponseEntity<Administrador> actualizarAdministrador (
            @PathVariable Long id,
            @RequestBody Administrador administrador){
            Administrador administradorEncontrado = this.servicioAdministrador.buscarAdministradorPorId(id);
            if(administradorEncontrado != null){

            }else{
                throw new ResourseNotFoundException("Recurso no encontrado:" +id);
            }
            administradorEncontrado.setNombre(administrador.getNombre());
            administradorEncontrado.setCorreo(administrador.getCorreo());
            administradorEncontrado.setGenero(administrador.getGenero());
            administradorEncontrado.setEdad(administrador.getEdad());
            administradorEncontrado.setNumeroDocumento(administrador.getNumeroDocumento());
            administradorEncontrado.setContrasena(administrador.getContrasena());
            administradorEncontrado.setEstado(administrador.getEstado());
            administradorEncontrado.setAreaAdministrar(administrador.getAreaAdministrar());
            administradorEncontrado.setNivelAcceso(administrador.getNivelAcceso());
            this.servicioAdministrador.guardarAdministrador(administradorEncontrado);
            return ResponseEntity.ok(administradorEncontrado);

    }

    @DeleteMapping("/administrador/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarAdministrador(@PathVariable Long id){
        Administrador administrador = this.servicioAdministrador.buscarAdministradorPorId(id);
        if(administrador == null){
            throw new ResourseNotFoundException("Id no encontrado: "+id);
        }
        this.servicioAdministrador.eliminarAdministrador(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Administrador eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
