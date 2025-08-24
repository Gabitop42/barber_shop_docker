package com.tiyay.barberShop.servicio_producto.service;

import com.tiyay.barberShop.servicio_producto.domain.entity.dto.request.ServicioProductoRequestDTO;
import com.tiyay.barberShop.servicio_producto.domain.entity.dto.response.ServicioProductoResponseDTO;

import java.util.List;

public interface ServicioProductoService {
    List<ServicioProductoResponseDTO> getAll();

    ServicioProductoResponseDTO getById(Long id);

    List<ServicioProductoResponseDTO> getAllByProducto();

    List<ServicioProductoResponseDTO> getAllByServicio();

    ServicioProductoResponseDTO create(ServicioProductoRequestDTO dto);

    ServicioProductoResponseDTO update(Long id, ServicioProductoRequestDTO dto);

    void delete(Long id);
}
