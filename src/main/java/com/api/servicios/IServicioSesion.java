package com.api.servicios;

import com.api.entidades.Sesion;

import java.util.List;

public interface IServicioSesion {

    List<Sesion> listaSesion();

    Sesion buscarSesionPorId(Long id);

    Sesion guardarSesion(Sesion sesion);

    void eliminarSesion(Long id);
}
