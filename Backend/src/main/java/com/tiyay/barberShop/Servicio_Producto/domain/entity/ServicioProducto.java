package com.tiyay.barberShop.Servicio_Producto.domain.entity;

import com.tiyay.barberShop.comisiones.domain.entity.Comision;
import com.tiyay.barberShop.ventas.domain.entity.DetalleVenta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "servicio_producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServicioProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @Column(nullable = false)
    private BigDecimal precioBase;

    @Column(nullable = false)
    private BigDecimal porcentajeComision;

    @Column(nullable = false)
    private boolean activo = true;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @OneToMany(mappedBy = "servicioProducto")
    private List<DetalleVenta> detalles;

    @OneToMany(mappedBy = "servicioProducto")
    private List<Comision> comisiones;

    public enum Tipo {
        SERVICIO, PRODUCTO
    }

}