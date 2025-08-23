package com.tiyay.barberShop.ventas.domain.entity;

import com.tiyay.barberShop.usuarios.domain.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ventas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalles = new ArrayList<>();

    public void agregarDetalle(DetalleVenta detalleVenta) {
        detalleVenta.setVenta(this);
        this.detalles.add(detalleVenta);
        recalcularTotal();
    }

    public void eliminarDetalle(DetalleVenta detalleVenta) {
        this.detalles.remove(detalleVenta);
        detalleVenta.setVenta(null);
        recalcularTotal();
    }

    public void recalcularTotal() {
        this.total = detalles.stream()
                .map(DetalleVenta::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
