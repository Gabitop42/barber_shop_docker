package com.tiyay.barberShop.usuarios.domain.entity;

import com.tiyay.barberShop.asistencia.domain.entity.Asistencia;
import com.tiyay.barberShop.comisiones.domain.entity.Comision;
import com.tiyay.barberShop.ventas.domain.entity.Venta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String email;
    private String contraseña;
    private String nombre;
    private String apellido;
    private String telefono;

    private String estado;

    @OneToMany(mappedBy = "usuario")
    private List<Asistencia> asistencias;

    @OneToMany(mappedBy = "usuario")
    private List<Venta> ventas;

    @OneToMany(mappedBy = "usuario")
    private List<Comision> comisiones;


    @Enumerated(EnumType.STRING)
    private Rol rol;

}