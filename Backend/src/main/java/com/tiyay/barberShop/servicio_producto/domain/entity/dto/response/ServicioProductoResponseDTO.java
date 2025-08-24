package com.tiyay.barberShop.servicio_producto.domain.entity.dto.response;

import com.tiyay.barberShop.servicio_producto.domain.entity.ServicioProducto.Tipo;

import java.math.BigDecimal;

public record ServicioProductoResponseDTO(
        Long id,
        String nombre,
        String descripcion,
        BigDecimal precioBase,
        BigDecimal porcentajeComision,
        boolean activo,
        Tipo tipo
) {}