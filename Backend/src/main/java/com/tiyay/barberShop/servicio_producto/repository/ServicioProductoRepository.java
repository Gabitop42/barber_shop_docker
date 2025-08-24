package com.tiyay.barberShop.servicio_producto.repository;

import com.tiyay.barberShop.servicio_producto.domain.entity.ServicioProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioProductoRepository extends JpaRepository<ServicioProducto, Long> {}