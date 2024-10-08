package com.api.controlador;

import com.api.entidades.Evaluador;
import com.api.excepciones.ResourseNotFoundException;
import com.api.servicios.ServicioEvaluador;
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
@RequestMapping("api-evaluador")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorEvaluador {

    public static final Logger logger = LoggerFactory.getLogger(ControladorEvaluador.class);

    @Autowired
    private ServicioEvaluador servicioEvaluador;


    @GetMapping("/evauador")
    public List<Evaluador> listaEvaluadores(){
        List<Evaluador> listaEvaludores = this.servicioEvaluador.listaEvaluador();
        logger.info("Lista de evaluadores obtenidos");
        listaEvaludores.forEach(evaluador -> logger.info(evaluador.toString()));
        return listaEvaludores;
    }

    @GetMapping("/evaluador/{id}")
    public ResponseEntity<Evaluador> buscarEvaluador(@PathVariable Long id){
        Evaluador evaluadorEncontrado = this.servicioEvaluador.buscarEvaluadorPorId(id);
        if(evaluadorEncontrado != null){
            return ResponseEntity.ok(evaluadorEncontrado);
        }else{
            throw new ResourseNotFoundException("No se encontró el ID");
        }

    }

    @PostMapping("/evaluador")
    @ResponseStatus(HttpStatus.CREATED)
    public Evaluador guardarEvaluador(@RequestBody Evaluador evaluador){
        logger.info("Evaluador agregado: {}", evaluador);
        return this.servicioEvaluador.guardarEvaluador(evaluador);
    }

    @PutMapping("/evalaudor/{id}")
    public ResponseEntity<Evaluador> actualizarEvaluador (
            @PathVariable Long id,
            @RequestBody Evaluador evaluador){
        Evaluador evaluadorEncontrado = this.servicioEvaluador.buscarEvaluadorPorId(id);
        if(evaluadorEncontrado != null){

        }else{
            throw new ResourseNotFoundException("Recurso no encontrado:" +id);
        }
        evaluadorEncontrado.setNombre(evaluador.getNombre());
        evaluadorEncontrado.setCorreo(evaluador.getCorreo());
        evaluadorEncontrado.setGenero(evaluador.getGenero());
        evaluadorEncontrado.setEdad(evaluador.getEdad());
        evaluadorEncontrado.setNumeroDocuemnto(evaluador.getNumeroDocuemnto());
        evaluadorEncontrado.setContrasena(evaluador.getContrasena());
        evaluadorEncontrado.setEstado(evaluador.getEstado());
        evaluadorEncontrado.setAreaEvaluar(evaluador.getAreaEvaluar());
        evaluadorEncontrado.setNivelCertificaicon(evaluador.getNivelCertificaicon());
        this.servicioEvaluador.guardarEvaluador(evaluadorEncontrado);
        return ResponseEntity.ok(evaluadorEncontrado);

    }

    @DeleteMapping("/evaluador/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarEvaluador(@PathVariable Long id){
        Evaluador evaluador = this.servicioEvaluador.buscarEvaluadorPorId(id);
        if(evaluador != null){
            throw new ResourseNotFoundException("Id no encontrado: "+id);
        }
        this.servicioEvaluador.eliminarEvaluador(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Evaluador eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
