package com.tiyay.barberShop.asistencia.service;

import com.tiyay.barberShop.asistencia.domain.entity.Asistencia;

import com.tiyay.barberShop.asistencia.domain.entity.dto.request.AsistenciaRequestDTO;
import com.tiyay.barberShop.asistencia.domain.entity.dto.request.HoraIngresoSalidaRequestDTO;
import com.tiyay.barberShop.asistencia.domain.entity.dto.response.AsistenciaResponseDTO;
import com.tiyay.barberShop.asistencia.mapper.AsistenciaMapper;
import com.tiyay.barberShop.asistencia.repository.AsistenciaRepository;
import com.tiyay.barberShop.usuarios.domain.entity.Usuario;
import com.tiyay.barberShop.usuarios.repository.UsuarioRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;
    private final AsistenciaMapper mapper;
    private final UsuarioRepository usuarioRepository;

    public AsistenciaService(AsistenciaRepository asistenciaRepository, AsistenciaMapper mapper,UsuarioRepository usuarioRepository) {
        this.asistenciaRepository = asistenciaRepository;
        this.mapper = mapper;
        this.usuarioRepository = usuarioRepository;
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

    public AsistenciaResponseDTO marcarAsistencia(Integer id) {
        Asistencia asistencia=asistenciaRepository.findById(id).orElseThrow(()->new RuntimeException("Asistencia no encontrada con id " + id));
        if (asistencia.getHoraIngreso()!=null) {
            throw new RuntimeException("Ya se marco la asistencia");
        }
        asistencia.setHoraIngreso(LocalDateTime.now());
        asistencia = asistenciaRepository.save(asistencia);
        return mapper.toResponseDTO(asistencia);
    }

    public AsistenciaResponseDTO marcarSalida(Integer id) {
        Asistencia asistencia=asistenciaRepository.findById(id).orElseThrow(()->new RuntimeException("Asistencia no encontrada con id " + id));
        if (asistencia.getHoraSalida()!=null) {
            throw new RuntimeException("Ya se marco la salida");
        }
        asistencia.setHoraSalida(LocalDateTime.now());
        asistencia = asistenciaRepository.save(asistencia);
        return mapper.toResponseDTO(asistencia);
    }

    public AsistenciaResponseDTO modificarTiempoDeAsistencia(Integer id, HoraIngresoSalidaRequestDTO HoraDTO) {
        Asistencia asistencia=asistenciaRepository.findById(id).orElseThrow(()->new RuntimeException("Asistencia no encontrada con id " + id));
        asistencia.setHoraIngreso(HoraDTO.horaIngreso());
        asistencia.setHoraSalida(HoraDTO.horaSalida());
        asistencia = asistenciaRepository.save(asistencia);
        return mapper.toResponseDTO(asistencia);
    }

    public void crearAsistenciasDiariasManual(){
        crearAsistenciasDiarias();
    }

    @Scheduled(cron = "0 0 0 * * ?")
    private void crearAsistenciasDiarias() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        for (Usuario usuario : usuarios) {
            boolean existe = asistenciaRepository.existsByUsuario_IdUsuarioAndFecha(
                    usuario.getIdUsuario(),
                    LocalDate.now()
            );

            if (!existe) {
                Asistencia asistencia = new Asistencia();
                asistencia.setUsuario(usuario);
                asistencia.setFecha(LocalDate.now());
                asistenciaRepository.save(asistencia);
            }
        }
    }

}
