package com.tiyay.barberShop.Servicio_Producto.domain.entity.dto;

import com.tiyay.barberShop.Servicio_Producto.domain.entity.ServicioProducto;

import java.math.BigDecimal;

public record ServicioProductoRequestDTO(
        String nombre,
        String descripcion,
        BigDecimal precioBase,
        BigDecimal porcentajeComision,
        boolean activo,
        ServicioProducto.Tipo tipo
) {}