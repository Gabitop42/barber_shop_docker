package com.tiyay.barberShop.Servicio_Producto.domain.entity.dto;

import com.tiyay.barberShop.Servicio_Producto.domain.entity.ServicioProducto.Tipo;

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