package com.parcial.parcial1corte.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="students")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String program;
}
