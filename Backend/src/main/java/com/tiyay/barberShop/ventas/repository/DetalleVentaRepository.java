package com.tiyay.barberShop.ventas.repository;

import com.tiyay.barberShop.ventas.domain.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}
