package com.api.servicios;

import com.api.entidades.Sustentacion;
import com.api.repositorios.IRepositorioSustentacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioSustentacion implements IServicioSustentacion{
    @Autowired
    private IRepositorioSustentacion iRepositorioSustentacion;

    @Override
    public List<Sustentacion> listaSustentacion() {
        return this.iRepositorioSustentacion.findAll();
    }

    @Override
    public Sustentacion buscarSustentacionPorId(Long id) {
        return this.iRepositorioSustentacion.findById(id).orElse(null);
    }

    @Override
    public Sustentacion guardarSustentacion(Sustentacion sustentacion) {
        return this.iRepositorioSustentacion.save(sustentacion);
    }

    @Override
    public void eliminarSustentacion(Long id) {
    this.iRepositorioSustentacion.deleteById(id);
    }
}
