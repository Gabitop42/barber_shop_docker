package com.tiyay.barberShop.asistencia.domain.entity.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AsistenciaResponseDTO(
        Integer id,
        LocalDate fecha,
        LocalDateTime horaIngreso,
        LocalDateTime horaSalida,
        Long idUsuario
) {}