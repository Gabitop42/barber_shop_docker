package com.tiyay.barberShop.ventas.domain.entity.dto.request;

import java.util.List;

public record VentaRequestDTO(
        Long idUsuario,
        List<DetalleVentaRequestDTO> detalles
) {}