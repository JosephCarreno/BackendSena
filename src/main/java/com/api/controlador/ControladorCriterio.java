package com.api.controlador;

import com.api.entidades.Administrador;
import com.api.entidades.Criterio;
import com.api.excepciones.ResourseNotFoundException;
import com.api.servicios.ServicioCriterio;
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
@RequestMapping("api-criterio")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorCriterio {
    public static final Logger logger = LoggerFactory.getLogger(ControladorCriterio.class);

    @Autowired
    private ServicioCriterio servicioCriterio;


    @GetMapping("/criterio")
    public List<Criterio> listaCriterio(){
        List<Criterio> listaCriterios = this.servicioCriterio.listaCriterio();
        logger.info("Lista de criterios obtenidos");
        listaCriterios.forEach(criterio -> logger.info(criterio.toString()));
        return  listaCriterios;
    }

    @GetMapping("/criterio/{id}")
    public ResponseEntity<Criterio> buscarCriterio(@PathVariable Long id){
        Criterio criterioEncontrado = this.servicioCriterio.buscarCriterioPorId(id);
        if(criterioEncontrado != null){
            return ResponseEntity.ok(criterioEncontrado);
        }else{
            throw new ResourseNotFoundException("No se encontró el ID");
        }

    }

    @PostMapping("/criterio")
    @ResponseStatus(HttpStatus.CREATED)
    public Criterio guardarCriterios(@RequestBody Criterio criterio){
        logger.info("Criterio agregado: {}", criterio);
        return this.servicioCriterio.guardaCriterio(criterio);
    }

    @PutMapping("/criterio/{id}")
    public ResponseEntity<Criterio> actualizarCriterios (
            @PathVariable Long id,
            @RequestBody Criterio criterio){
        Criterio criterioEncontrado = this.servicioCriterio.buscarCriterioPorId(id);
        if(criterioEncontrado != null){

        }else{
            throw new ResourseNotFoundException("Recurso no encontrado:" +id);
        }
        criterioEncontrado.setFechaIncio(criterio.getFechaIncio());
        criterioEncontrado.setFechaFinal(criterio.getFechaFinal());
        criterioEncontrado.setTitulo(criterio.getTitulo());
        criterioEncontrado.setCalificacion(criterio.getCalificacion());
        criterioEncontrado.setDescripcion(criterio.getDescripcion());
        this.servicioCriterio.guardaCriterio(criterioEncontrado);
        return ResponseEntity.ok(criterioEncontrado);

    }

    @DeleteMapping("/criterio/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarCriterio(@PathVariable Long id) {
        Criterio criterio = this.servicioCriterio.buscarCriterioPorId(id);
        if (criterio == null) {
            throw new ResourseNotFoundException("Id no encontrado: "+id);
        }
        this.servicioCriterio.eliminarCriterio(id);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Criterio eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
