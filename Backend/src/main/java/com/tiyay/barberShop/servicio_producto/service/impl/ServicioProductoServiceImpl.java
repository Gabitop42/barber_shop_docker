package com.tiyay.barberShop.servicio_producto.service.impl;

import com.tiyay.barberShop.servicio_producto.domain.entity.ServicioProducto;

import com.tiyay.barberShop.servicio_producto.domain.entity.dto.request.ServicioProductoRequestDTO;
import com.tiyay.barberShop.servicio_producto.domain.entity.dto.response.ServicioProductoResponseDTO;
import com.tiyay.barberShop.servicio_producto.mapper.ServicioProductoMapper;
import com.tiyay.barberShop.servicio_producto.repository.ServicioProductoRepository;
import com.tiyay.barberShop.servicio_producto.service.ServicioProductoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioProductoServiceImpl implements ServicioProductoService {

    private final ServicioProductoRepository repository;
    private final ServicioProductoMapper mapper;

    public ServicioProductoServiceImpl(ServicioProductoRepository repository, ServicioProductoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ServicioProductoResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ServicioProductoResponseDTO getById(Long id) {
        ServicioProducto entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ServicioProducto no encontrado con id " + id));
        return mapper.toResponseDTO(entity);
    }

    @Override
    public List<ServicioProductoResponseDTO> getAllByProducto() {
        return repository.findAll().stream()
                .filter(servicioProducto -> servicioProducto.getTipo().equals(ServicioProducto.Tipo.PRODUCTO))
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<ServicioProductoResponseDTO> getAllByServicio() {
        return repository.findAll().stream()
                .filter(servicioProducto -> servicioProducto.getTipo().equals(ServicioProducto.Tipo.SERVICIO))
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Override
    public ServicioProductoResponseDTO create(ServicioProductoRequestDTO dto) {
        ServicioProducto entity = mapper.toEntity(dto);
        ServicioProducto saved = repository.save(entity);
        return mapper.toResponseDTO(saved);
    }

    @Override
    public ServicioProductoResponseDTO update(Long id, ServicioProductoRequestDTO dto) {
        ServicioProducto entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ServicioProducto no encontrado con id " + id));
        mapper.updateEntity(entity, dto);
        ServicioProducto updated = repository.save(entity);
        return mapper.toResponseDTO(updated);
    }

    @Override
    public void delete(Long id) {
        ServicioProducto entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ServicioProducto no encontrado con id " + id));
        repository.delete(entity);
    }
}
