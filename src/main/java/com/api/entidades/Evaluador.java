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
public class Evaluador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvaluador;
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
    @Column(nullable = false,length = 50)
    private String areaEvaluar;
    @Column(nullable = false,length = 50)
    private String nivelCertificaicon;
    @OneToMany(targetEntity = Sesion.class, fetch = FetchType.LAZY,mappedBy = "eval")
    private List<Sesion> listaSesion;
    @OneToMany(targetEntity = CalificarProyecto.class, fetch = FetchType.LAZY, mappedBy = "eval")
    private List<CalificarProyecto> calificarProyecto;
}
