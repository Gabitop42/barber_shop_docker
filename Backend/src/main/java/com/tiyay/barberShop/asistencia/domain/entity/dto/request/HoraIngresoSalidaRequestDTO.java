package com.tiyay.barberShop.asistencia.domain.entity.dto.request;

import jakarta.validation.constraints.AssertTrue;

import java.time.LocalDateTime;

public record HoraIngresoSalidaRequestDTO(
        LocalDateTime horaIngreso,
        LocalDateTime horaSalida
) {
    @AssertTrue(message = "La hora de ingreso no puede ser posterior a la hora de salida")
    public boolean isIngresoAntesDeSalida() {
        if (horaIngreso == null || horaSalida == null) {
            return true;
        }
        return !horaIngreso.isAfter(horaSalida);
    }
}
