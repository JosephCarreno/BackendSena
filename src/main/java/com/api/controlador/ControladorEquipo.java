package com.api.controlador;

import com.api.entidades.Equipo;
import com.api.excepciones.ResourseNotFoundException;
import com.api.servicios.ServicioEquipo;
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
@RequestMapping("api-equipo")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorEquipo {
    public static final Logger logger = LoggerFactory.getLogger(ControladorEquipo.class);

    @Autowired
    private ServicioEquipo servicioEquipo;


    @GetMapping("/equipo")
    public List<Equipo> listaEquipo(){
        List<Equipo> listaEquipos = this.servicioEquipo.listaEquipo();
        logger.info("Lista de equipos obtenidos");
        listaEquipos.forEach(equipo -> logger.info(equipo.toString()));
        return  listaEquipos;
    }

    @GetMapping("/equipo/{id}")
    public ResponseEntity<Equipo> buscarEquipo(@PathVariable Long id){
        Equipo equipoEncontrado = this.servicioEquipo.buscarEquipoPorId(id);
        if(equipoEncontrado != null){
            return ResponseEntity.ok(equipoEncontrado);
        }else{
            throw new ResourseNotFoundException("No se encontró el ID");
        }

    }

    @PostMapping("/equipo")
    @ResponseStatus(HttpStatus.CREATED)
    public Equipo guardarEquipo(@RequestBody Equipo equipo){
        logger.info("Equipo agregado: {}", equipo);
        return this.servicioEquipo.guardarEquipo(equipo);
    }

    @PutMapping("/equipo/{id}")
    public ResponseEntity<Equipo> actualizarEquipo (
            @PathVariable Long id,
            @RequestBody Equipo equipo){
        Equipo equipoEncontrado = this.servicioEquipo.buscarEquipoPorId(id);
        if(equipoEncontrado != null){

        }else{
            throw new ResourseNotFoundException("Recurso no encontrado:" +id);
        }
        equipoEncontrado.setNombreProyecto(equipo.getNombreProyecto());
        equipoEncontrado.setNombreSoftware(equipo.getNombreSoftware());
        equipoEncontrado.setIntegrantes(equipo.getIntegrantes());
        equipoEncontrado.setLema(equipo.getLema());
        equipoEncontrado.setDescripcion(equipo.getDescripcion());
        this.servicioEquipo.guardarEquipo(equipoEncontrado);
        return ResponseEntity.ok(equipoEncontrado);

    }

    @DeleteMapping("/equipo/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarEquipo(@PathVariable Long id){
        Equipo equipo = this.servicioEquipo.buscarEquipoPorId(id);
        if(equipo != null){
            throw new ResourseNotFoundException("Id no encontrado: "+id);
        }
        this.servicioEquipo.eliminarEquipo(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Equipo eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
