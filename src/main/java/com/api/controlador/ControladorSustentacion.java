package com.api.controlador;

import com.api.entidades.Sustentacion;
import com.api.excepciones.ResourseNotFoundException;
import com.api.servicios.ServicioSustentacion;
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
@RequestMapping("api-sustentacion")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorSustentacion {
    public static final Logger logger = LoggerFactory.getLogger(ControladorSustentacion.class);

    @Autowired
    private ServicioSustentacion servicioSustentacion;


    @GetMapping("/sustentacion")
    public List<Sustentacion> listaSustentacion(){
        List<Sustentacion> listaSustentaciones = this.servicioSustentacion.listaSustentacion();
        logger.info("Lista de sustentacion obtenidos");
        listaSustentaciones.forEach(sustentacion -> logger.info(sustentacion.toString()));
        return  listaSustentaciones;
    }

    @GetMapping("/sustentacion/{id}")
    public ResponseEntity<Sustentacion> buscarSustentacion(@PathVariable Long id){
        Sustentacion sustentacionEncontrado = this.servicioSustentacion.buscarSustentacionPorId(id);
        if(sustentacionEncontrado!= null){
            return ResponseEntity.ok(sustentacionEncontrado);
        }else{
            throw new ResourseNotFoundException("No se encontró el ID");
        }

    }

    @PostMapping("/sustentacion")
    @ResponseStatus(HttpStatus.CREATED)
    public Sustentacion guardarSustentacion(@RequestBody Sustentacion sustentacion){
        logger.info("Sustentacion agregado: {}", sustentacion);
        return this.servicioSustentacion.guardarSustentacion(sustentacion);
    }

    @PutMapping("/sustentacion/{id}")
    public ResponseEntity<Sustentacion> actualizarSustentacion (
            @PathVariable Long id,
            @RequestBody Sustentacion sustentacion){
        Sustentacion sustentacionEncontrado = this.servicioSustentacion.buscarSustentacionPorId(id);
        if(sustentacionEncontrado != null){

        }else{
            throw new ResourseNotFoundException("Recurso no encontrado:" +id);
        }
        sustentacionEncontrado.setNombreProyecto(sustentacion.getNombreProyecto());
        sustentacionEncontrado.setFormacion(sustentacion.getFormacion());
        sustentacionEncontrado.setAprendices(sustentacion.getAprendices());
        this.servicioSustentacion.guardarSustentacion(sustentacionEncontrado);
        return ResponseEntity.ok(sustentacionEncontrado);

    }

    @DeleteMapping("/sustentacion/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarSustentacion(@PathVariable Long id){
        Sustentacion sustentacion = this.servicioSustentacion.buscarSustentacionPorId(id);
        if(sustentacion != null){
            throw new ResourseNotFoundException("Id no encontrado: "+id);
        }
        this.servicioSustentacion.eliminarSustentacion(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Sustentacion eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
