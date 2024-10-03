package com.api.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInstructor;
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
    @Column(nullable = false, length = 50)
    private String contrasena;
    @Column(nullable = false,length = 20)
    private String estado;
    @Column(nullable = false,length = 30)
    private String especialidad;
    @Column(nullable = false)
    private LocalDate fechaInicioContrato;
    @Column(nullable = false)
    private LocalDate fechaFinalContrato;
    @OneToMany(targetEntity = Sesion.class, fetch = FetchType.LAZY,mappedBy = "ins")
    private List<Sesion> listaSesion;
    @OneToMany(targetEntity = Criterio.class, fetch = FetchType.LAZY, mappedBy = "ins")
    private List<Criterio> criterio;
}
