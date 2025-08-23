package com.tiyay.barberShop.asistencia.domain.entity.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AsistenciaRequestDTO(
        LocalDate fecha,
        LocalDateTime horaIngreso,
        LocalDateTime horaSalida,
        Long idUsuario
) {}