package com.tiyay.barberShop.ventas.domain.entity.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record VentaRequestDTO(

        @NotNull(message = "El usuario es obligatorio")
        Long idUsuario,

        @NotNull(message = "La lista de detalles no puede ser nula")
        @NotEmpty(message = "Debe haber al menos un detalle en la venta")
        @Valid
        List<DetalleVentaRequestDTO> detalles

) {}