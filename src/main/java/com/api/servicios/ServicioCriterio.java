package com.api.servicios;

import com.api.entidades.Criterio;
import com.api.repositorios.IRepositorioCriterio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioCriterio implements IServicioCriterio{
    @Autowired
    private IRepositorioCriterio iRepositorioCriterio;

    @Override
    public List<Criterio> listaCriterio() {
        return this.iRepositorioCriterio.findAll();
    }

    @Override
    public Criterio buscarCriterioPorId(Long id) {
        return this.iRepositorioCriterio.findById(id).orElse(null);
    }

    @Override
    public Criterio guardaCriterio(Criterio criterio) {
        return this.iRepositorioCriterio.save(criterio);
    }

    @Override
    public void eliminarCriterio(Long id) {
    this.iRepositorioCriterio.deleteById(id);
    }
}
