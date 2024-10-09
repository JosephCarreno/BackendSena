package com.api.controlador;

import com.api.entidades.Administrador;
import com.api.entidades.CalificarProyecto;
import com.api.excepciones.ResourseNotFoundException;
import com.api.servicios.ServicioCalificarProyecto;
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
@RequestMapping("api-calificarProyecto")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorCalificarProyecto {
    public static final Logger logger = LoggerFactory.getLogger(ControladorCalificarProyecto.class);

    @Autowired
    private ServicioCalificarProyecto servicioCalificarProyecto;


    @GetMapping("/calificarProyecto")
    public List<CalificarProyecto> listaCalificarProyecto(){
        List<CalificarProyecto> listaCalificarProyectos = this.servicioCalificarProyecto.listaCalificarProyecto();
        logger.info("Lista de calificar proyecto obtenidos");
        listaCalificarProyectos.forEach(calificarProyecto-> logger.info(calificarProyecto.toString()));
        return listaCalificarProyectos;
    }

    @GetMapping("/calificarProyecto/{id}")
    public ResponseEntity<CalificarProyecto> buscarCalificarProyectoPorId(@PathVariable Long id){
        CalificarProyecto calificarProyectoEncontrado = this.servicioCalificarProyecto.buscarCalificarProyectoPorId(id);
        if(calificarProyectoEncontrado != null){
            return ResponseEntity.ok(calificarProyectoEncontrado);
        }else{
            throw new ResourseNotFoundException("No se encontró el ID");
        }

    }

    @PostMapping("/calificarProyecto")
    @ResponseStatus(HttpStatus.CREATED)
    public CalificarProyecto guardarCalificarProyectos(@RequestBody CalificarProyecto calificarProyecto){
        logger.info("Calificar proyecto agregado: {}", calificarProyecto);
        return this.servicioCalificarProyecto.guardarCalificarProyecto(calificarProyecto);
    }

    @PutMapping("/calificarProyecto/{id}")
    public ResponseEntity<CalificarProyecto> actualizarCalificarProyecto (
            @PathVariable Long id,
            @RequestBody CalificarProyecto calificarProyecto){
        CalificarProyecto calificarProyectoEncontrado = this.servicioCalificarProyecto.buscarCalificarProyectoPorId(id);
        if(calificarProyectoEncontrado != null){

        }else{
            throw new ResourseNotFoundException("Recurso no encontrado:" +id);
        }
        calificarProyectoEncontrado.setCalidadCodigo(calificarProyecto.getCalidadCodigo());
        calificarProyectoEncontrado.setFuncionalidad(calificarProyecto.getFuncionalidad());
        calificarProyectoEncontrado.setInterfaazUsuario(calificarProyecto.getInterfaazUsuario());
        calificarProyectoEncontrado.setInnovacion(calificarProyecto.getInnovacion());
        calificarProyectoEncontrado.setEscalabilidad(calificarProyecto.getEscalabilidad());
        calificarProyectoEncontrado.setTrabajoEquipo(calificarProyecto.getTrabajoEquipo());
        this.servicioCalificarProyecto.guardarCalificarProyecto(calificarProyectoEncontrado);
        return ResponseEntity.ok(calificarProyectoEncontrado);

    }

    @DeleteMapping("/calificarProyecto/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarCalificarProyecto(@PathVariable Long id) {
        CalificarProyecto calificarProyecto = this.servicioCalificarProyecto.buscarCalificarProyectoPorId(id);
        if (calificarProyecto == null) {
            throw new ResourseNotFoundException("Id no encontrado: "+id);
        }
        this.servicioCalificarProyecto.eliminarCalificarProyecto(id);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Calificacion eliminada", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
