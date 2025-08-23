package com.tiyay.barberShop.asistencia.domain.entity;

import com.tiyay.barberShop.usuarios.domain.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "registro_asistencia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate fecha;
    private LocalDateTime hora_ingreso;
    private LocalDateTime hora_salida;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
