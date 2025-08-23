package com.tiyay.barberShop.Servicio_Producto.repository;

import com.tiyay.barberShop.Servicio_Producto.domain.entity.ServicioProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioProductoRepository extends JpaRepository<ServicioProducto, Long> {}