package com.api.controlador;

import com.api.entidades.Instructor;
import com.api.entidades.Reporte;
import com.api.excepciones.ResourseNotFoundException;
import com.api.servicios.ServicioInstructor;
import com.api.servicios.ServicioReporte;
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
@RequestMapping("api-reporte")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorReporte {
    public static final Logger logger = LoggerFactory.getLogger(ControladorReporte.class);

    @Autowired
    private ServicioReporte servicioReporte;


    @GetMapping("/reporte")
    public List<Reporte> listaReporte(){
        List<Reporte> listaReportes = this.servicioReporte.listaReporte();
        logger.info("Lista de reportes obtenidos");
        listaReportes.forEach(reporte -> logger.info(reporte.toString()));
        return listaReportes;
    }

    @GetMapping("/reporte/{id}")
    public ResponseEntity<Reporte> buscarReporte(@PathVariable Long id){
        Reporte reporteEncontrado = this.servicioReporte.buscarReportePorId(id);
        if(reporteEncontrado != null){
            return ResponseEntity.ok(reporteEncontrado);
        }else{
            throw new ResourseNotFoundException("No se encontró el ID");
        }

    }

    @PostMapping("/reporte")
    @ResponseStatus(HttpStatus.CREATED)
    public Reporte guardarReporte(@RequestBody Reporte reporte){
        logger.info("Reporte agregado: {}", reporte);
        return this.servicioReporte.guardarReporte(reporte);
    }

    @PutMapping("/reporte/{id}")
    public ResponseEntity<Reporte> actualizarReporte (
            @PathVariable Long id,
            @RequestBody Reporte reporte){
        Reporte reporteEncontrado = this.servicioReporte.buscarReportePorId(id);
        if(reporteEncontrado != null){

        }else{
            throw new ResourseNotFoundException("Recurso no encontrado:" +id);
        }
        reporteEncontrado.setDescripcion(reporte.getDescripcion());
        reporteEncontrado.setTitulo(reporte.getTitulo());
        this.servicioReporte.guardarReporte(reporteEncontrado);
        return ResponseEntity.ok(reporteEncontrado);

    }

    @DeleteMapping("/reporte/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarReporte(@PathVariable Long id){
        Reporte reporte = this.servicioReporte.buscarReportePorId(id);
        if(reporte != null){
            throw new ResourseNotFoundException("Id no encontrado: "+id);
        }
        this.servicioReporte.eliminarReporte(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Reporte eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
