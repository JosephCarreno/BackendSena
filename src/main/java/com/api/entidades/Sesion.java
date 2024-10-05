package com.api.entidades;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Sesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSesion;
    @Column(nullable = false,length = 15)
    private String usuario;
    @Column(unique = true,nullable = false)
    private String contrasena;
    @ManyToOne(targetEntity = Administrador.class)
    @JoinColumn(name = "fk_id_administradores",referencedColumnName = "idAdministrador")
    private Administrador admin;
    @ManyToOne(targetEntity = Instructor.class)
    @JoinColumn(name = "fk_id_instructor",referencedColumnName = "idInstructor")
    private Instructor ins;
    @ManyToOne(targetEntity = Evaluador.class)
    @JoinColumn(name = "fk_id_evaluador",referencedColumnName = "idEvaluador")
    private Evaluador eval;
    @ManyToOne(targetEntity = Aprendiz.class)
    @JoinColumn(name = "fk_id_aprendiz",referencedColumnName = "idAprendiz")
    private Aprendiz apren;
}
