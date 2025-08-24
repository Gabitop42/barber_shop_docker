package com.tiyay.barberShop.comisiones.mapper;

import com.tiyay.barberShop.servicio_producto.domain.entity.ServicioProducto;
import com.tiyay.barberShop.comisiones.domain.entity.dto.request.ComisionRequestDTO;
import com.tiyay.barberShop.comisiones.domain.entity.dto.response.ComisionResponseDTO;
import com.tiyay.barberShop.usuarios.domain.entity.Usuario;
import com.tiyay.barberShop.ventas.domain.entity.Venta;
import com.tiyay.barberShop.comisiones.domain.entity.Comision;


public class ComisionMapper {

    public static Comision toEntity(ComisionRequestDTO dto, Usuario usuario, ServicioProducto servicioProducto, Venta venta) {
        Comision comision = new Comision();
        comision.setMontoComision(dto.montoComision());
        comision.setUsuario(usuario);
        comision.setServicioProducto(servicioProducto);
        comision.setVenta(venta);
        return comision;
    }

    public static ComisionResponseDTO toResponse(Comision comision) {
        return new ComisionResponseDTO(
                comision.getIdComision(),
                comision.getMontoComision(),
                comision.getUsuario() != null ? comision.getUsuario().getIdUsuario() : null,
                comision.getServicioProducto() != null ? comision.getServicioProducto().getId() : null,
                comision.getVenta() != null ? comision.getVenta().getId() : null
        );
    }
}
