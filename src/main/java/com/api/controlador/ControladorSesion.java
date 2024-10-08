package com.api.controlador;

import com.api.entidades.Sesion;
import com.api.excepciones.ResourseNotFoundException;
import com.api.servicios.ServicioSesion;
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
@RequestMapping("api-sesion")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorSesion {
    public static final Logger logger = LoggerFactory.getLogger(ControladorSesion.class);

    @Autowired
    private ServicioSesion servicioSesion;


    @GetMapping("/sesion")
    public List<Sesion> listaSesion(){
        List<Sesion> listaSesiones = this.servicioSesion.listaSesion();
        logger.info("Lista de sesiones obtenidos");
        listaSesiones.forEach(sesion -> logger.info(sesion.toString()));
        return  listaSesiones;
    }

    @GetMapping("/sesion/{id}")
    public ResponseEntity<Sesion> buscarSesion(@PathVariable Long id){
        Sesion sesionEncontrado = this.servicioSesion.buscarSesionPorId(id);
        if(sesionEncontrado != null){
            return ResponseEntity.ok(sesionEncontrado);
        }else{
            throw new ResourseNotFoundException("No se encontró el ID");
        }

    }

    @PostMapping("/sesion")
    @ResponseStatus(HttpStatus.CREATED)
    public Sesion guardarSesion(@RequestBody Sesion sesion){
        logger.info("Sesion agregado: {}", sesion);
        return this.servicioSesion.guardarSesion(sesion);
    }

    @PutMapping("/sesion/{id}")
    public ResponseEntity<Sesion> actualizarSesion (
            @PathVariable Long id,
            @RequestBody Sesion sesion){
        Sesion sesionEncontrado = this.servicioSesion.buscarSesionPorId(id);
        if(sesionEncontrado != null){

        }else{
            throw new ResourseNotFoundException("Recurso no encontrado:" +id);
        }
        sesionEncontrado.setContrasena(sesion.getContrasena());
        sesionEncontrado.setUsuario(sesion.getUsuario());
        this.servicioSesion.guardarSesion(sesionEncontrado);
        return ResponseEntity.ok(sesionEncontrado);

    }

    @DeleteMapping("/sesion/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarSesion(@PathVariable Long id){
        Sesion sesion = this.servicioSesion.buscarSesionPorId(id);
        if(sesion == null){
            throw new ResourseNotFoundException("Id no encontrado: "+id);
        }
        this.servicioSesion.eliminarSesion(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Sesion eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
