package com.api.servicios;

import com.api.entidades.Criterio;

import java.util.List;

public interface IServicioCriterio {

    List<Criterio> listaCriterio();

    Criterio buscarCriterioPorId(Long id);

    Criterio guardaCriterio(Criterio criterio);

    void eliminarCriterio(Long id);
}
