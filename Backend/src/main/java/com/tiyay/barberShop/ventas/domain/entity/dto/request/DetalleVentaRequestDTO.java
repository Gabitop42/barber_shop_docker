package com.tiyay.barberShop.ventas.domain.entity.dto.request;

public record DetalleVentaRequestDTO(
        Long idServicioProducto,
        Integer cantidad
) {}