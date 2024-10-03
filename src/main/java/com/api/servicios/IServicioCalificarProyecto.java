package com.api.servicios;

import com.api.entidades.CalificarProyecto;

import java.util.List;

public interface IServicioCalificarProyecto {

    List<CalificarProyecto> listaCalificarProyecto();

    CalificarProyecto buscarCalificarProyectoPorId(Long id);

    CalificarProyecto guardarCalificarProyecto(CalificarProyecto calificarProyecto);

    void eliminarCalificarProyecto(Long id);
}
