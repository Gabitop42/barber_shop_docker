package com.tiyay.barberShop.comisiones.domain.entity;

import com.tiyay.barberShop.servicio_producto.domain.entity.ServicioProducto;
import com.tiyay.barberShop.usuarios.domain.entity.Usuario;
import com.tiyay.barberShop.ventas.domain.entity.Venta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "comisiones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comision {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComision;

    private BigDecimal montoComision;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "servicio_producto_id")
    private ServicioProducto servicioProducto;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

}
