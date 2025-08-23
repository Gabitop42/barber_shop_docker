package com.tiyay.barberShop.asistencia.repository;

import com.tiyay.barberShop.asistencia.domain.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {}