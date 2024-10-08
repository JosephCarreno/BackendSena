package com.api.controlador;

import com.api.entidades.Ficha;
import com.api.excepciones.ResourseNotFoundException;
import com.api.servicios.ServicioFicha;
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
@RequestMapping("api-ficha")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorFicha {
    public static final Logger logger = LoggerFactory.getLogger(ControladorFicha.class);

    @Autowired
    private ServicioFicha servicioFicha;


    @GetMapping("/ficha")
    public List<Ficha> listaFicha(){
        List<Ficha> listaFichas = this.servicioFicha.listaFicha();
        logger.info("Lista de fichas obtenidos");
        listaFichas.forEach(equipo -> logger.info(equipo.toString()));
        return  listaFichas;
    }

    @GetMapping("/ficha/{id}")
    public ResponseEntity<Ficha> buscarFicha(@PathVariable Long id){
        Ficha ficha = this.servicioFicha.buscarFichaPorId(id);
        if(ficha != null){
            return ResponseEntity.ok(ficha);
        }else{
            throw new ResourseNotFoundException("No se encontró el ID");
        }

    }

    @PostMapping("/ficha")
    @ResponseStatus(HttpStatus.CREATED)
    public Ficha guardarFicha(@RequestBody Ficha ficha){
        logger.info("ficha agregada: {}", ficha);
        return this.servicioFicha.guardarFicha(ficha);
    }

    @PutMapping("/ficha/{id}")
    public ResponseEntity<Ficha> actualizarFicha (
            @PathVariable Long id,
            @RequestBody Ficha ficha){
        Ficha fichaEncontrado = this.servicioFicha.buscarFichaPorId(id);
        if(fichaEncontrado != null){

        }else{
            throw new ResourseNotFoundException("Recurso no encontrado:" +id);
        }
        fichaEncontrado.setNumeroFicha(ficha.getNumeroFicha());
        fichaEncontrado.setNombreFicha(ficha.getNombreFicha());
        fichaEncontrado.setFechaIncio(ficha.getFechaIncio());
        fichaEncontrado.setFechaFinal(ficha.getFechaFinal());
        this.servicioFicha.guardarFicha(fichaEncontrado);
        return ResponseEntity.ok(fichaEncontrado);

    }

    @DeleteMapping("/ficha/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarFicha(@PathVariable Long id){
        Ficha ficha = this.servicioFicha.buscarFichaPorId(id);
        if(ficha != null){
            throw new ResourseNotFoundException("Id no encontrado: "+id);
        }
        this.servicioFicha.eliminarFicha(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Ficha eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
