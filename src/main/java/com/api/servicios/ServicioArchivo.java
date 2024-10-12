package com.api.servicios;

import com.api.entidades.Archivo;
import com.api.repositorios.IRepositorioArchivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioArchivo implements IServicioArchivo{
    @Autowired
    private IRepositorioArchivo iRepositorioArchivo;
    @Override
    public List<Archivo> listaArchivo() {
        return this.iRepositorioArchivo.findAll();
    }

    @Override
    public Archivo buscarArchivoPorId(Long id) {
        return this.iRepositorioArchivo.findById(id).orElse(null);
    }

    @Override
    public Archivo guardarArchivo(Archivo archivo) {
        return this.iRepositorioArchivo.save(archivo);
    }

    @Override
    public void eliminarArchivo(Long id) {
        this.iRepositorioArchivo.deleteById(id);
    }
}
