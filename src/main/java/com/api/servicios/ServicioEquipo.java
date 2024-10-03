package com.api.servicios;

import com.api.entidades.Equipo;
import com.api.repositorios.IRepositorioEquipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioEquipo implements IServicioEquipo{
    @Autowired
    private IRepositorioEquipo iRepositorioEquipo;

    @Override
    public List<Equipo> listaEquipo() {
        return this.iRepositorioEquipo.findAll();
    }

    @Override
    public Equipo buscarEquipoPorId(Long id) {
        return this.iRepositorioEquipo.findById(id).orElse(null);
    }

    @Override
    public Equipo guardarEquipo(Equipo equipo) {
        return this.iRepositorioEquipo.save(equipo);
    }

    @Override
    public void eliminarEquipo(Long id) {
        this.iRepositorioEquipo.deleteById(id);
    }
}
