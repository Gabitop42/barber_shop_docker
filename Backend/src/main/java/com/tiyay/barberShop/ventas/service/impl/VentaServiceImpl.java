package com.tiyay.barberShop.ventas.service.impl;

import com.tiyay.barberShop.comisiones.service.ComisionService;
import com.tiyay.barberShop.servicio_producto.domain.entity.ServicioProducto;
import com.tiyay.barberShop.servicio_producto.repository.ServicioProductoRepository;
import com.tiyay.barberShop.comisiones.domain.entity.dto.request.ComisionRequestDTO;
import com.tiyay.barberShop.usuarios.domain.entity.Usuario;
import com.tiyay.barberShop.usuarios.repository.UsuarioRepository;
import com.tiyay.barberShop.ventas.domain.entity.Venta;

import com.tiyay.barberShop.ventas.domain.entity.dto.request.VentaRequestDTO;
import com.tiyay.barberShop.ventas.domain.entity.dto.response.VentaResponseDTO;
import com.tiyay.barberShop.ventas.mapper.VentaMapper;
import com.tiyay.barberShop.ventas.repository.VentaRepository;
import com.tiyay.barberShop.ventas.service.VentaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ServicioProductoRepository servicioProductoRepository;
    private final VentaMapper ventaMapper;
    private final ComisionService comisionService;


    @Override
    public List<VentaResponseDTO> getAll() {
        return ventaRepository.findAll().stream()
                .map(ventaMapper::toResponse)
                .toList();
    }

    @Override
    public VentaResponseDTO getById(Long id) {
        return ventaRepository.findById(id)
                .map(ventaMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    @Override
    @Transactional
    public VentaResponseDTO create(VentaRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<ServicioProducto> servicioProductoList = servicioProductoRepository.findAll();

        Venta venta = ventaMapper.toEntity(dto, usuario, servicioProductoList);
        Venta saved = ventaRepository.save(venta);

        venta.getDetalles().forEach(detalle -> {
            BigDecimal porcentaje = detalle.getServicioProducto().getPorcentajeComision();
            BigDecimal montoComision = detalle.getSubtotal()
                    .multiply(porcentaje)
                    .divide(BigDecimal.valueOf(100));

            ComisionRequestDTO comisionDTO = new ComisionRequestDTO(
                    montoComision,
                    usuario.getIdUsuario(),
                    detalle.getServicioProducto().getId(),
                    saved.getId()
            );

            comisionService.create(comisionDTO);
        });

        return ventaMapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        ventaRepository.deleteById(id);
    }
}
