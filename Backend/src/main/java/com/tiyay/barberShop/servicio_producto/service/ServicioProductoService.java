package com.tiyay.barberShop.servicio_producto.service;

import com.tiyay.barberShop.servicio_producto.domain.entity.ServicioProducto;

import com.tiyay.barberShop.servicio_producto.domain.entity.dto.request.ServicioProductoRequestDTO;
import com.tiyay.barberShop.servicio_producto.domain.entity.dto.response.ServicioProductoResponseDTO;
import com.tiyay.barberShop.servicio_producto.mapper.ServicioProductoMapper;
import com.tiyay.barberShop.servicio_producto.repository.ServicioProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioProductoService {

    private final ServicioProductoRepository repository;
    private final ServicioProductoMapper mapper;

    public ServicioProductoService(ServicioProductoRepository repository, ServicioProductoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ServicioProductoResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ServicioProductoResponseDTO getById(Long id) {
        ServicioProducto entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ServicioProducto no encontrado con id " + id));
        return mapper.toResponseDTO(entity);
    }

    public ServicioProductoResponseDTO create(ServicioProductoRequestDTO dto) {
        ServicioProducto entity = mapper.toEntity(dto);
        ServicioProducto saved = repository.save(entity);
        return mapper.toResponseDTO(saved);
    }

    public ServicioProductoResponseDTO update(Long id, ServicioProductoRequestDTO dto) {
        ServicioProducto entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ServicioProducto no encontrado con id " + id));
        mapper.updateEntity(entity, dto);
        ServicioProducto updated = repository.save(entity);
        return mapper.toResponseDTO(updated);
    }

    public void delete(Long id) {
        ServicioProducto entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ServicioProducto no encontrado con id " + id));
        repository.delete(entity);
    }
}
