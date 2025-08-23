package com.tiyay.barberShop.comisiones.domain.entity.dto.request;

import java.math.BigDecimal;

public record ComisionRequestDTO(
        BigDecimal montoComision,
        Long idUsuario,
        Long idServicioProducto,
        Long idVenta
) {}
