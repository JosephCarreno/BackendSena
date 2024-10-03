package com.api.servicios;

import com.api.entidades.Sustentacion;

import java.util.List;

public interface IServicioSustentacion {

    List<Sustentacion> listaSustentacion();

    Sustentacion buscarSustentacionPorId(Long id);

    Sustentacion guardarSustentacion(Sustentacion sustentacion);

    void eliminarSustentacion(Long id);
}
