package com.tiyay.barberShop.ventas.service;

import com.tiyay.barberShop.servicio_producto.domain.entity.ServicioProducto;
import com.tiyay.barberShop.servicio_producto.repository.ServicioProductoRepository;
import com.tiyay.barberShop.comisiones.domain.entity.dto.request.ComisionRequestDTO;
import com.tiyay.barberShop.comisiones.service.ComisionService;
import com.tiyay.barberShop.usuarios.domain.entity.Usuario;
import com.tiyay.barberShop.usuarios.repository.UsuarioRepository;
import com.tiyay.barberShop.ventas.domain.entity.Venta;

import com.tiyay.barberShop.ventas.domain.entity.dto.request.VentaRequestDTO;
import com.tiyay.barberShop.ventas.domain.entity.dto.response.VentaResponseDTO;
import com.tiyay.barberShop.ventas.mapper.VentaMapper;
import com.tiyay.barberShop.ventas.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ServicioProductoRepository servicioProductoRepository;
    private final ComisionService comisionService;

    public VentaService(VentaRepository ventaRepository,
                        UsuarioRepository usuarioRepository,
                        ServicioProductoRepository servicioProductoRepository,
                        ComisionService comisionService) {
        this.ventaRepository = ventaRepository;
        this.usuarioRepository = usuarioRepository;
        this.servicioProductoRepository = servicioProductoRepository;
        this.comisionService = comisionService;
    }

    public List<VentaResponseDTO> getAll() {
        return ventaRepository.findAll().stream()
                .map(VentaMapper::toResponse)
                .toList();
    }

    public VentaResponseDTO getById(Long id) {
        return ventaRepository.findById(id)
                .map(VentaMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    public VentaResponseDTO create(VentaRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<ServicioProducto> servicios = servicioProductoRepository.findAll();

        Venta venta = VentaMapper.toEntity(dto, usuario, servicios);
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

        return VentaMapper.toResponse(saved);
    }

    public void delete(Long id) {
        ventaRepository.deleteById(id);
    }
}
