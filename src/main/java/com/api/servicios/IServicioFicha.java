package com.api.servicios;

import com.api.entidades.Ficha;

import java.util.List;

public interface IServicioFicha {

    List<Ficha> listaFicha();

    Ficha buscarFichaPorId(Long id);

    Ficha guardarFicha(Ficha ficha);

    void eliminarFicha(Long id);
}
