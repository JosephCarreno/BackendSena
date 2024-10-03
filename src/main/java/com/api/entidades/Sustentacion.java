package com.api.entidades;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Sustentacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSustentacion;
    @Column(nullable = false,length = 50)
    private String nombreProyecto;
    @Column(nullable = false,length = 50)
    private String formacion;
    @Column(nullable = false,length = 150)
    private String aprendices;
    @ManyToOne(targetEntity = Instructor.class)
    @JoinColumn(name = "fk_id_instructor",referencedColumnName = "idInstructor")
    private Instructor instructor;
    @OneToOne(targetEntity = Equipo.class)
    @JoinColumn(name = "fk_id_equipo",referencedColumnName = "idEquipo")
    private Equipo equipo;
}
