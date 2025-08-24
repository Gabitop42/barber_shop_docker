package com.tiyay.barberShop.comisiones.service.impl;

import com.tiyay.barberShop.comisiones.service.ComisionService;
import com.tiyay.barberShop.servicio_producto.domain.entity.ServicioProducto;
import com.tiyay.barberShop.servicio_producto.repository.ServicioProductoRepository;
import com.tiyay.barberShop.comisiones.domain.entity.dto.request.ComisionRequestDTO;
import com.tiyay.barberShop.comisiones.domain.entity.dto.response.ComisionResponseDTO;
import com.tiyay.barberShop.usuarios.domain.entity.Usuario;
import com.tiyay.barberShop.usuarios.repository.UsuarioRepository;
import com.tiyay.barberShop.ventas.domain.entity.Venta;
import com.tiyay.barberShop.ventas.repository.VentaRepository;
import com.tiyay.barberShop.comisiones.domain.entity.Comision;

import com.tiyay.barberShop.comisiones.mapper.ComisionMapper;
import com.tiyay.barberShop.comisiones.repository.ComisionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComisionServiceImpl implements ComisionService {

    private final ComisionRepository comisionRepository;
    private final UsuarioRepository usuarioRepository;
    private final ServicioProductoRepository servicioProductoRepository;
    private final VentaRepository ventaRepository;
    private final ComisionMapper comisionMapper;


    @Override
    public List<ComisionResponseDTO> getAll() {
        return comisionRepository.findAll().stream()
                .map(comisionMapper::toResponse)
                .toList();
    }

    @Override
    public ComisionResponseDTO getById(Long id) {
        return comisionRepository.findById(id)
                .map(comisionMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Comisión no encontrada"));
    }

    @Override
    public BigDecimal getComisionTotalByUsuarioId(Long idUsuario) {
        return comisionRepository.findAll().stream()
                .filter((comision)->comision.getUsuario().getIdUsuario().equals(idUsuario))
                .map((Comision::getMontoComision))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Transactional
    @Override
    public ComisionResponseDTO create(ComisionRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        ServicioProducto servicioProducto = servicioProductoRepository.findById(dto.idServicioProducto())
                .orElseThrow(() -> new RuntimeException("ServicioProducto no encontrado"));

        Venta venta = ventaRepository.findById(dto.idVenta())
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        Comision comision = comisionMapper.toEntity(dto, usuario, servicioProducto, venta);
        return comisionMapper.toResponse(comisionRepository.save(comision));
    }

    @Override
    public void delete(Long id) {
        comisionRepository.deleteById(id);
    }
}
