package com.tiyay.barberShop.ventas.domain.entity.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record VentaResponseDTO(
        Long id,
        LocalDateTime fecha,
        Long idUsuario,
        BigDecimal total,
        List<DetalleVentaResponseDTO> detalles
) {}
