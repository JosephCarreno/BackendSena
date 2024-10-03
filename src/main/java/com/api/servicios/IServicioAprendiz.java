package com.api.servicios;

import com.api.entidades.Aprendiz;

import java.util.List;

public interface IServicioAprendiz {

    List<Aprendiz> listaAprendiz();

    Aprendiz buscarAprendizPorId(Long id);

    Aprendiz guardarAprendiz(Aprendiz aprendiz);

    void eliminarAprendiz(Long id);
}
