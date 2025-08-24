package com.tiyay.barberShop.asistencia.mapper;

import com.tiyay.barberShop.asistencia.domain.entity.Asistencia;

import com.tiyay.barberShop.asistencia.domain.entity.dto.request.AsistenciaRequestDTO;
import com.tiyay.barberShop.asistencia.domain.entity.dto.response.AsistenciaResponseDTO;
import com.tiyay.barberShop.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class AsistenciaMapper {

    private final UsuarioRepository usuarioRepository;

    public AsistenciaMapper(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public AsistenciaResponseDTO toResponseDTO(Asistencia asistencia) {
        return new AsistenciaResponseDTO(
                asistencia.getId(),
                asistencia.getFecha(),
                asistencia.getHoraIngreso(),
                asistencia.getHoraSalida(),
                asistencia.getUsuario().getIdUsuario()
        );
    }

    public Asistencia toEntity(AsistenciaRequestDTO dto) {
        Asistencia asistencia = new Asistencia();
        asistencia.setFecha(dto.fecha());
        asistencia.setHoraIngreso(dto.horaIngreso());
        asistencia.setHoraSalida(dto.horaSalida());

        asistencia.setUsuario(
                usuarioRepository.findById(dto.idUsuario())
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + dto.idUsuario()))
        );

        return asistencia;
    }

    public void updateEntity(Asistencia asistencia, AsistenciaRequestDTO dto) {
        asistencia.setFecha(dto.fecha());
        asistencia.setHoraSalida(dto.horaIngreso());
        asistencia.setHoraSalida(dto.horaSalida());
        asistencia.setUsuario(
                usuarioRepository.findById(dto.idUsuario())
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + dto.idUsuario()))
        );
    }
}
