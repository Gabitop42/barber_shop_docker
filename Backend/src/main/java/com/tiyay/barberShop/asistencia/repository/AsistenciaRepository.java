package com.tiyay.barberShop.asistencia.repository;

import com.tiyay.barberShop.asistencia.domain.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {
    Optional<Asistencia> findByUsuario_IdUsuarioAndHoraSalidaIsNull(Long idUsuario);
    boolean existsByUsuario_IdUsuarioAndFecha(Long idUsuario, LocalDate fecha);

    boolean existsByFecha(LocalDate fecha);
}