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
public class Aprendiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAprendiz;
    @Column(nullable = false,length = 50)
    private String nombre;
    @Column(nullable = false,length = 100)
    private String correo;
    @Column(nullable = false,length = 10)
    private String genero;
    @Column(nullable = false)
    private int edad;
    @Column(nullable = false)
    private Long numeroDocuemnto;
    @Column(nullable = false,length = 50)
    private String contrasena;
    @Column(nullable = false,length = 20)
    private String estado;
    @Column(nullable = false)
    private int numeroFicha;
    @Column(nullable = false,length = 50)
    private String nombreFicha;
    @Column(nullable = false,length = 50)
    private String nivelEducacion;
    @OneToMany(targetEntity = Sesion.class, fetch = FetchType.LAZY,mappedBy = "apren")
    private List<Sesion> listaSesion;
    @OneToMany(targetEntity = Equipo.class, fetch = FetchType.LAZY, mappedBy = "apren")
    private List<Equipo> listaEquipo;
}
