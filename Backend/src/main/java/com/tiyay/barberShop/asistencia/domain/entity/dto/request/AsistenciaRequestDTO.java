package com.tiyay.barberShop.asistencia.domain.entity.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AsistenciaRequestDTO(
        @NotNull(message = "La fecha no puede ser nula")
        LocalDate fecha,

        @FutureOrPresent(message = "La hora de ingreso no puede ser menor a la hora actual")
        LocalDateTime horaIngreso,

        @PastOrPresent
        LocalDateTime horaSalida,

        @NotNull(message = "El ID de usuario es obligatorio")
        @Positive(message = "El ID de usuario debe ser positivo")
        Long idUsuario
) {
    @AssertTrue(message = "La hora de salida debe ser posterior a la hora de ingreso")
    private boolean esHoraValida() {
        if (horaIngreso == null || horaSalida == null) return true;
        return horaSalida.isAfter(horaIngreso);
    }
}