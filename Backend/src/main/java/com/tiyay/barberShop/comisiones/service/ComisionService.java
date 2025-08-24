package com.tiyay.barberShop.comisiones.service;

import com.tiyay.barberShop.comisiones.domain.entity.dto.request.ComisionRequestDTO;
import com.tiyay.barberShop.comisiones.domain.entity.dto.response.ComisionResponseDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ComisionService {
    List<ComisionResponseDTO> getAll();

    ComisionResponseDTO getById(Long id);

    BigDecimal getComisionTotalByUsuarioId(Long idUsuario);

    ComisionResponseDTO create(ComisionRequestDTO dto);

    void delete(Long id);
}
