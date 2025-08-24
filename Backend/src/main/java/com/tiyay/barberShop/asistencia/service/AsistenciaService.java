package com.tiyay.barberShop.asistencia.service;

import com.tiyay.barberShop.asistencia.domain.entity.dto.request.AsistenciaRequestDTO;
import com.tiyay.barberShop.asistencia.domain.entity.dto.request.HoraIngresoSalidaRequestDTO;
import com.tiyay.barberShop.asistencia.domain.entity.dto.response.AsistenciaResponseDTO;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

public interface AsistenciaService {
    List<AsistenciaResponseDTO> getAllAsistencias();

    AsistenciaResponseDTO getAsistenciaById(Integer id);

    AsistenciaResponseDTO createAsistencia(AsistenciaRequestDTO dto);

    AsistenciaResponseDTO updateAsistencia(Integer id, AsistenciaRequestDTO dto);

    void deleteAsistencia(Integer id);

    AsistenciaResponseDTO marcarAsistencia(Integer id);

    AsistenciaResponseDTO marcarSalida(Integer id);

    AsistenciaResponseDTO modificarTiempoDeAsistencia(Integer id, HoraIngresoSalidaRequestDTO HoraDTO);

    void crearAsistenciasDiariasManual();

    @Scheduled(cron = "0 0 0 * * ?")
    void crearAsistenciasDiarias();
}
