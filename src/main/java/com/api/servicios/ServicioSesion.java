package com.api.servicios;

import com.api.entidades.Sesion;
import com.api.repositorios.IRepositorioSesion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioSesion implements IServicioSesion{
    @Autowired
    private IRepositorioSesion iRepositorioSesion;

    @Override
    public List<Sesion> listaSesion() {
        return this.iRepositorioSesion.findAll();
    }

    @Override
    public Sesion buscarSesionPorId(Long id) {
        return this.iRepositorioSesion.findById(id).orElse(null);
    }

    @Override
    public Sesion guardarSesion(Sesion sesion) {
        return this.iRepositorioSesion.save(sesion);
    }

    @Override
    public void eliminarSesion(Long id) {
    this.iRepositorioSesion.deleteById(id);
    }
}
