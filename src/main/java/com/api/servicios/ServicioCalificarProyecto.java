package com.api.servicios;

import com.api.entidades.CalificarProyecto;
import com.api.repositorios.IRepositorioCalificarProyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioCalificarProyecto implements IServicioCalificarProyecto{
    @Autowired
    private IRepositorioCalificarProyecto iRepositorioCalificarProyecto;

    @Override
    public List<CalificarProyecto> listaCalificarProyecto() {
        return this.iRepositorioCalificarProyecto.findAll();
    }

    @Override
    public CalificarProyecto buscarCalificarProyectoPorId(Long id) {
        return this.iRepositorioCalificarProyecto.findById(id).orElse(null);
    }

    @Override
    public CalificarProyecto guardarCalificarProyecto(CalificarProyecto calificarProyecto) {
        return this.iRepositorioCalificarProyecto.save(calificarProyecto);
    }

    @Override
    public void eliminarCalificarProyecto(Long id) {
    this.iRepositorioCalificarProyecto.deleteById(id);
    }
}
