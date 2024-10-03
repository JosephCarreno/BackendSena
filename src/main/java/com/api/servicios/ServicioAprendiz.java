package com.api.servicios;

import com.api.entidades.Aprendiz;
import com.api.repositorios.IRepositorioAprendiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioAprendiz implements IServicioAprendiz{
    @Autowired
    private IRepositorioAprendiz iRepositorioAprendiz;

    @Override
    public List<Aprendiz> listaAprendiz() {
        return this.iRepositorioAprendiz.findAll();
    }

    @Override
    public Aprendiz buscarAprendizPorId(Long id) {
        return this.iRepositorioAprendiz.findById(id).orElse(null);
    }

    @Override
    public Aprendiz guardarAprendiz(Aprendiz aprendiz) {
        return this.iRepositorioAprendiz.save(aprendiz);
    }

    @Override
    public void eliminarAprendiz(Long id) {
    this.iRepositorioAprendiz.deleteById(id);
    }
}
