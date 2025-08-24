package com.tiyay.barberShop.servicio_producto.mapper;

import com.tiyay.barberShop.servicio_producto.domain.entity.ServicioProducto;

import com.tiyay.barberShop.servicio_producto.domain.entity.dto.request.ServicioProductoRequestDTO;
import com.tiyay.barberShop.servicio_producto.domain.entity.dto.response.ServicioProductoResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ServicioProductoMapper {

    public ServicioProductoResponseDTO toResponseDTO(ServicioProducto entity) {
        return new ServicioProductoResponseDTO(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getPrecioBase(),
                entity.getPorcentajeComision(),
                entity.isActivo(),
                entity.getTipo()
        );
    }

    public ServicioProducto toEntity(ServicioProductoRequestDTO dto) {
        ServicioProducto entity = new ServicioProducto();
        entity.setNombre(dto.nombre());
        entity.setDescripcion(dto.descripcion());
        entity.setPrecioBase(dto.precioBase());
        entity.setPorcentajeComision(dto.porcentajeComision());
        entity.setTipo(dto.tipo());
        return entity;
    }

    public void updateEntity(ServicioProducto entity, ServicioProductoRequestDTO dto) {
        entity.setNombre(dto.nombre());
        entity.setDescripcion(dto.descripcion());
        entity.setPrecioBase(dto.precioBase());
        entity.setPorcentajeComision(dto.porcentajeComision());
        entity.setTipo(dto.tipo());
    }
}
