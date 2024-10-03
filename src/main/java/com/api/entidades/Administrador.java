package com.api.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdministrador;
    @Column(nullable = false,length = 50)
    private String nombre;
    @Column(nullable = false,length = 100)
    private String correo;
    @Column(nullable = false,length = 10)
    private String genero;
    @Column(nullable = false)
    private int edad;
    @Column(nullable = false)
    private Long numeroDocumento;
    @Column(nullable = false,length = 50)
    private String contrasena;
    @Column(nullable = false,length = 20)
    private String estado;
    @Column(nullable = false,length = 50)
    private String areaAdministrar;
    @Column(nullable = false,length = 50)
    private String nivelAcceso;
    @OneToMany(targetEntity = Sesion.class, fetch = FetchType.LAZY,mappedBy = "admin")
    private List<Sesion> listaSesion;
    @OneToMany(targetEntity = Reporte.class, fetch = FetchType.LAZY, mappedBy = "admin")
    private List<Reporte> listaReporte;

}