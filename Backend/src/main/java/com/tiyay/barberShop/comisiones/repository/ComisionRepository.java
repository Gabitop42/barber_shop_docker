package com.tiyay.barberShop.comisiones.repository;

import com.tiyay.barberShop.comisiones.domain.entity.Comision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComisionRepository extends JpaRepository<Comision, Long> {
}
