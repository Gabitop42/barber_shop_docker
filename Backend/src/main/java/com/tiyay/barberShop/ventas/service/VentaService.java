package com.tiyay.barberShop.ventas.service;

import com.tiyay.barberShop.ventas.domain.entity.dto.request.VentaRequestDTO;
import com.tiyay.barberShop.ventas.domain.entity.dto.response.VentaResponseDTO;

import java.util.List;

public interface VentaService {
    List<VentaResponseDTO> getAll();

    VentaResponseDTO getById(Long id);

    VentaResponseDTO create(VentaRequestDTO dto);

    void delete(Long id);
}
