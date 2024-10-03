package com.api.servicios;

import com.api.entidades.Administrador;
import com.api.repositorios.IRepositorioAdministrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioAdministrador implements IServicioAdministrador {

    @Autowired
    private IRepositorioAdministrador iRepositorioAdministrador;


    @Override
    public List<Administrador> listaAdministrador() {
        return this.iRepositorioAdministrador.findAll();
    }

    @Override
    public Administrador buscarAdministradorPorId(Long id) {
        return this.iRepositorioAdministrador.findById(id).orElse(null);
    }

    @Override
    public Administrador guardarAdministrador(Administrador administrador) {
       return this.iRepositorioAdministrador.save(administrador);
    }

    @Override
    public void eliminarAdministrador(Long id) {
        this.iRepositorioAdministrador.deleteById(id);
    }
}
