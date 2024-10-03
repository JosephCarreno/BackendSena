package com.api.servicios;

import com.api.entidades.Evaluador;

import java.util.List;

public interface IServicioEvaluador {

    List<Evaluador> listaEvaluador();

    Evaluador buscarEvaluadorPorId(Long id);

    Evaluador guardarEvaluador(Evaluador evaluador);

    void eliminarEvaluador(Long id);
}
