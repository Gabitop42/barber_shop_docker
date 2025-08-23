package com.tiyay.barberShop.ventas.mapper;

import com.tiyay.barberShop.Servicio_Producto.domain.entity.ServicioProducto;
import com.tiyay.barberShop.usuarios.domain.entity.Usuario;
import com.tiyay.barberShop.ventas.domain.entity.DetalleVenta;
import com.tiyay.barberShop.ventas.domain.entity.Venta;
import com.tiyay.barberShop.ventas.domain.entity.dto.request.VentaRequestDTO;
import com.tiyay.barberShop.ventas.domain.entity.dto.response.VentaResponseDTO;
import com.tiyay.barberShop.ventas.domain.entity.dto.response.DetalleVentaResponseDTO;


import java.util.List;
import java.util.stream.Collectors;

public class VentaMapper {

    public static Venta toEntity(VentaRequestDTO dto, Usuario usuario, List<ServicioProducto> servicios) {
        Venta venta = new Venta();
        venta.setUsuario(usuario);

        List<DetalleVenta> detalles = dto.detalles().stream()
                .map(detalleDTO -> {
                    ServicioProducto sp = servicios.stream()
                            .filter(s -> s.getId().equals(detalleDTO.idServicioProducto()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("ServicioProducto no encontrado"));
                    DetalleVenta dv = new DetalleVenta();
                    dv.setServicioProducto(sp);
                    dv.setCantidad(detalleDTO.cantidad());
                    dv.setVenta(venta);
                    dv.calcularSubtotal();
                    return dv;
                })
                .collect(Collectors.toList());

        venta.setDetalles(detalles);
        venta.recalcularTotal();
        return venta;
    }

    public static VentaResponseDTO toResponse(Venta venta) {
        List<DetalleVentaResponseDTO> detalles = venta.getDetalles().stream()
                .map(dv -> new DetalleVentaResponseDTO(
                        dv.getId(),
                        dv.getServicioProducto().getId(),
                        dv.getCantidad(),
                        dv.getPrecioUnitario(),
                        dv.getSubtotal()
                ))
                .collect(Collectors.toList());

        return new VentaResponseDTO(
                venta.getId(),
                venta.getFecha(),
                venta.getUsuario().getIdUsuario(),
                venta.getTotal(),
                detalles
        );
    }
}
