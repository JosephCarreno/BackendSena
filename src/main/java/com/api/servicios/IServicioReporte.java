package com.api.servicios;

import com.api.entidades.Reporte;

import java.util.List;

public interface IServicioReporte {

    List<Reporte> listaReporte();

    Reporte buscarReportePorId(Long id);

    Reporte guardarReporte(Reporte reporte);

    void eliminarReporte(Long id);
}
