package com.api.controlador;

import com.api.entidades.Instructor;
import com.api.excepciones.ResourseNotFoundException;
import com.api.servicios.ServicioInstructor;
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
@RequestMapping("api-instructor")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorInstructor {
    public static final Logger logger = LoggerFactory.getLogger(ControladorInstructor.class);

    @Autowired
    private ServicioInstructor servicioInstructor;


    @GetMapping("/instructor")
    public List<Instructor> listaInstructor(){
        List<Instructor> listaInstructor = this.servicioInstructor.listaInstructor();
        logger.info("Lista de administradores obtenidos");
        listaInstructor.forEach(instructor -> logger.info(instructor.toString()));
        return listaInstructor;
    }

    @GetMapping("/instructor/{id}")
    public ResponseEntity<Instructor> buscarInstructor(@PathVariable Long id){
        Instructor instructorEncontrado = this.servicioInstructor.buscarInstructorPorId(id);
        if(instructorEncontrado != null){
            return ResponseEntity.ok(instructorEncontrado);
        }else{
            throw new ResourseNotFoundException("No se encontró el ID");
        }

    }

    @PostMapping("/instructor")
    @ResponseStatus(HttpStatus.CREATED)
    public Instructor guardarInstructor(@RequestBody Instructor instructor){
        logger.info("Isntructor agregado: {}", instructor);
        return this.servicioInstructor.guardarInstructor(instructor);
    }

    @PutMapping("/instructor/{id}")
    public ResponseEntity<Instructor> actualizarInstructor (
            @PathVariable Long id,
            @RequestBody Instructor instructor){
        Instructor instructorEncontrado = this.servicioInstructor.buscarInstructorPorId(id);
        if(instructorEncontrado != null){

        }else{
            throw new ResourseNotFoundException("Recurso no encontrado:" +id);
        }
        instructorEncontrado.setNombre(instructor.getNombre());
        instructorEncontrado.setCorreo(instructor.getCorreo());
        instructorEncontrado.setGenero(instructor.getGenero());
        instructorEncontrado.setEdad(instructor.getEdad());
        instructorEncontrado.setNumeroDocuemnto(instructor.getNumeroDocuemnto());
        instructorEncontrado.setContrasena(instructor.getContrasena());
        instructorEncontrado.setEstado(instructor.getEstado());
        instructorEncontrado.setEspecialidad(instructor.getEspecialidad());
        instructorEncontrado.setFechaInicioContrato(instructor.getFechaInicioContrato());
        instructorEncontrado.setFechaFinalContrato(instructor.getFechaFinalContrato());
        this.servicioInstructor.guardarInstructor(instructorEncontrado);
        return ResponseEntity.ok(instructorEncontrado);

    }

    @DeleteMapping("/instructor/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarInstructor(@PathVariable Long id){
        Instructor instructor = this.servicioInstructor.buscarInstructorPorId(id);
        if(instructor != null){
            throw new ResourseNotFoundException("Id no encontrado: "+id);
        }
        this.servicioInstructor.eliminarInstructor(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Instructor eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
