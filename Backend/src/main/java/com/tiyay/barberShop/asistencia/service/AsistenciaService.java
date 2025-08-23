package com.tiyay.barberShop.asistencia.service;

import com.tiyay.barberShop.asistencia.domain.entity.Asistencia;

import com.tiyay.barberShop.asistencia.domain.entity.dto.AsistenciaRequestDTO;
import com.tiyay.barberShop.asistencia.domain.entity.dto.AsistenciaResponseDTO;
import com.tiyay.barberShop.asistencia.mapper.AsistenciaMapper;
import com.tiyay.barberShop.asistencia.repository.AsistenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;
    private final AsistenciaMapper mapper;

    public AsistenciaService(AsistenciaRepository asistenciaRepository, AsistenciaMapper mapper) {
        this.asistenciaRepository = asistenciaRepository;
        this.mapper = mapper;
    }

    public List<AsistenciaResponseDTO> getAllAsistencias() {
        return asistenciaRepository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public AsistenciaResponseDTO getAsistenciaById(Integer id) {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asistencia no encontrada con id " + id));
        return mapper.toResponseDTO(asistencia);
    }

    public AsistenciaResponseDTO createAsistencia(AsistenciaRequestDTO dto) {
        Asistencia asistencia = mapper.toEntity(dto);
        Asistencia saved = asistenciaRepository.save(asistencia);
        return mapper.toResponseDTO(saved);
    }

    public AsistenciaResponseDTO updateAsistencia(Integer id, AsistenciaRequestDTO dto) {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asistencia no encontrada con id " + id));
        mapper.updateEntity(asistencia, dto);
        Asistencia updated = asistenciaRepository.save(asistencia);
        return mapper.toResponseDTO(updated);
    }

    public void deleteAsistencia(Integer id) {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asistencia no encontrada con id " + id));
        asistenciaRepository.delete(asistencia);
    }
}
