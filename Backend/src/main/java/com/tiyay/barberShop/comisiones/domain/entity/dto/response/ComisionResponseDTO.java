package com.tiyay.barberShop.comisiones.domain.entity.dto.response;

import java.math.BigDecimal;

public record ComisionResponseDTO(
        Long idComision,
        BigDecimal montoComision,
        Long idUsuario,
        Long idServicioProducto,
        Long idVenta
) {}
