package com.api.controlador;

import com.api.entidades.Aprendiz;
import com.api.excepciones.ResourseNotFoundException;
import com.api.servicios.ServicioAprendiz;
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
@RequestMapping("api-aprendiz")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorAprendiz {
    public static final Logger logger = LoggerFactory.getLogger(ControladorAprendiz.class);

    @Autowired
    private ServicioAprendiz servicioAprendiz;


    @GetMapping("/aprendiz")
    public List<Aprendiz> listaAprendiz(){
        List<Aprendiz> listaAprenidces = this.servicioAprendiz.listaAprendiz();
        logger.info("Lista de aprendices obtenidos");
        listaAprenidces.forEach(aprendiz-> logger.info(aprendiz.toString()));
        return listaAprenidces;
    }

    @GetMapping("/aprendiz/{id}")
    public ResponseEntity<Aprendiz> buscarAprendizPorId(@PathVariable Long id){
        Aprendiz aprendizEncontrado = this.servicioAprendiz.buscarAprendizPorId(id);
        if(aprendizEncontrado != null){
            return ResponseEntity.ok(aprendizEncontrado);
        }else{
            throw new ResourseNotFoundException("No se encontró el ID");
        }

    }

    @PostMapping("/aprendiz")
    @ResponseStatus(HttpStatus.CREATED)
    public Aprendiz guardarAprendiz(@RequestBody Aprendiz aprendiz){
        logger.info("Aprendiz agregado: {}", aprendiz);
        return this.servicioAprendiz.guardarAprendiz(aprendiz);
    }

    @PutMapping("/aprendiz/{id}")
    public ResponseEntity<Aprendiz> actualizarAprendiz (
            @PathVariable Long id,
            @RequestBody Aprendiz aprendiz){
        Aprendiz administradorEncontrado = this.servicioAprendiz.buscarAprendizPorId(id);
        if(administradorEncontrado != null){

        }else{
            throw new ResourseNotFoundException("Recurso no encontrado:" +id);
        }
        administradorEncontrado.setNombre(aprendiz.getNombre());
        administradorEncontrado.setCorreo(aprendiz.getCorreo());
        administradorEncontrado.setGenero(aprendiz.getGenero());
        administradorEncontrado.setEdad(aprendiz.getEdad());
        administradorEncontrado.setNumeroDocuemnto(aprendiz.getNumeroDocuemnto());
        administradorEncontrado.setContrasena(aprendiz.getContrasena());
        administradorEncontrado.setEstado(aprendiz.getEstado());
        administradorEncontrado.setNumeroFicha(aprendiz.getNumeroFicha());
        administradorEncontrado.setNombreFicha(aprendiz.getNombreFicha());
        administradorEncontrado.setNivelEducacion(aprendiz.getNivelEducacion());
        this.servicioAprendiz.guardarAprendiz(administradorEncontrado);
        return ResponseEntity.ok(administradorEncontrado);

    }

    @DeleteMapping("/aprendiz/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarAprendiz(@PathVariable Long id){
        Aprendiz aprendiz = this.servicioAprendiz.buscarAprendizPorId(id);
        if(aprendiz != null){
            throw new ResourseNotFoundException("Id no encontrado: "+id);
        }
        this.servicioAprendiz.eliminarAprendiz(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Aprendiz eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
