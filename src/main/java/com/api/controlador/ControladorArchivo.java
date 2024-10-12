package com.api.controlador;

import com.api.entidades.Archivo;
import com.api.excepciones.ResourseNotFoundException;
import com.api.servicios.ServicioArchivo;
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
@RequestMapping("api-archivo")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorArchivo {
    public static final Logger logger = LoggerFactory.getLogger(ControladorArchivo.class);

    @Autowired
    private ServicioArchivo servicioArchivo;


    @GetMapping("/archivo")
    public List<Archivo> listaArchivo(){
        List<Archivo> listaArchivos = this.servicioArchivo.listaArchivo();
        logger.info("Lista de archivos obtenidos");
        listaArchivos.forEach(archivo -> logger.info(archivo.toString()));
        return listaArchivos;
    }

    @GetMapping("/archivo/{id}")
    public ResponseEntity<Archivo> buscarArchivoPorId(@PathVariable Long id){
        Archivo archivoEncontrado = this.servicioArchivo.buscarArchivoPorId(id);
        if(archivoEncontrado != null){
            return ResponseEntity.ok(archivoEncontrado);
        }else{
            throw new ResourseNotFoundException("No se encontró el ID");
        }

    }

    @PostMapping("/archivo")
    @ResponseStatus(HttpStatus.CREATED)
    public Archivo guardarArchivo(@RequestBody Archivo archivo){
        logger.info("Archivo agregado: {}", archivo);
        return this.servicioArchivo.guardarArchivo(archivo);
    }

    @PutMapping("/archivo/{id}")
    public ResponseEntity<Archivo> actualizarArchivo (
            @PathVariable Long id,
            @RequestBody Archivo archivo){
        Archivo archivoEncontrado = this.servicioArchivo.buscarArchivoPorId(id);
        if(archivoEncontrado != null){

        }else{
            throw new ResourseNotFoundException("Recurso no encontrado:" +id);
        }
        archivoEncontrado.setNombreArchivo(archivo.getNombreArchivo());
        archivoEncontrado.setTipoArchivo(archivo.getTipoArchivo());
        archivoEncontrado.setContenido(archivo.getContenido());
        this.servicioArchivo.guardarArchivo(archivoEncontrado);
        return ResponseEntity.ok(archivoEncontrado);

    }

    @DeleteMapping("/archivo/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarChat(@PathVariable Long id) {
        Archivo archivo = this.servicioArchivo.buscarArchivoPorId(id);
        if (archivo == null) {
            throw new ResourseNotFoundException("Id no encontrado: "+id);
        }
        this.servicioArchivo.eliminarArchivo(id);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Archivo eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
