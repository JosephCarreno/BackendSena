package com.api.servicios;

import com.api.entidades.Evaluador;
import com.api.repositorios.IRepositorioEvaluador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioEvaluador implements IServicioEvaluador{
    @Autowired
    private IRepositorioEvaluador iRepositorioEvaluador;
    @Override
    public List<Evaluador> listaEvaluador() {
        return this.iRepositorioEvaluador.findAll();
    }

    @Override
    public Evaluador buscarEvaluadorPorId(Long id) {
        return this.iRepositorioEvaluador.findById(id).orElse(null);
    }

    @Override
    public Evaluador guardarEvaluador(Evaluador evaluador) {
        return this.iRepositorioEvaluador.save(evaluador);
    }

    @Override
    public void eliminarEvaluador(Long id) {
    this.iRepositorioEvaluador.deleteById(id);
    }
}
