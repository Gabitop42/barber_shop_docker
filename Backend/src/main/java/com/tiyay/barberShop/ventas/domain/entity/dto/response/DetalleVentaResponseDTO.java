package com.tiyay.barberShop.ventas.domain.entity.dto.response;

import java.math.BigDecimal;

public record DetalleVentaResponseDTO(
        Long id,
        Long idServicioProducto,
        Integer cantidad,
        BigDecimal precioUnitario,
        BigDecimal subtotal
) {}