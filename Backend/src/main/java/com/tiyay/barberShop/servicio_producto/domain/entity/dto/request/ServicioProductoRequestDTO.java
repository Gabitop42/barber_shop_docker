package com.tiyay.barberShop.servicio_producto.domain.entity.dto.request;

import com.tiyay.barberShop.servicio_producto.domain.entity.ServicioProducto;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;


public record ServicioProductoRequestDTO(

        @NotNull
        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,

        @NotNull
        @NotBlank(message = "La descripción no puede estar vacía")
        String descripcion,

        @NotNull(message = "El precio base es obligatorio")
        @Positive(message = "El precio base debe ser mayor que 0")
        BigDecimal precioBase,

        @NotNull(message = "El porcentaje de comisión es obligatorio")
        @DecimalMin(value = "0.0", inclusive = true, message = "El porcentaje de comisión no puede ser negativo")
        @DecimalMax(value = "100.0", inclusive = true, message = "El porcentaje de comisión no puede ser mayor a 100")
        BigDecimal porcentajeComision,

        @NotNull(message = "El tipo de servicio es obligatorio")
        ServicioProducto.Tipo tipo
) {}