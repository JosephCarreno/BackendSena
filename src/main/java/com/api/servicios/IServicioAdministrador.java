package com.api.servicios;

import com.api.entidades.Administrador;

import java.util.List;

public interface IServicioAdministrador {

    List<Administrador> listaAdministrador();

    Administrador buscarAdministradorPorId(Long id);

    Administrador guardarAdministrador(Administrador administrador);

    void eliminarAdministrador(Long id);
}
