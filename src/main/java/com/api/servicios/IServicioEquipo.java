package com.api.servicios;

import com.api.entidades.Equipo;

import java.util.List;

public interface IServicioEquipo {

    List<Equipo> listaEquipo();

    Equipo buscarEquipoPorId(Long id);

    Equipo guardarEquipo(Equipo equipo);

    void eliminarEquipo(Long id);
}
