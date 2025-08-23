package com.tiyay.barberShop.ventas.repository;

import com.tiyay.barberShop.ventas.domain.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
}