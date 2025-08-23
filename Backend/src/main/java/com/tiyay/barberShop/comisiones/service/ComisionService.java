package com.tiyay.barberShop.comisiones.service;

import com.tiyay.barberShop.Servicio_Producto.domain.entity.ServicioProducto;
import com.tiyay.barberShop.Servicio_Producto.repository.ServicioProductoRepository;
import com.tiyay.barberShop.comisiones.domain.entity.dto.request.ComisionRequestDTO;
import com.tiyay.barberShop.comisiones.domain.entity.dto.response.ComisionResponseDTO;
import com.tiyay.barberShop.usuarios.domain.entity.Usuario;
import com.tiyay.barberShop.usuarios.repository.UsuarioRepository;
import com.tiyay.barberShop.ventas.domain.entity.Venta;
import com.tiyay.barberShop.ventas.repository.VentaRepository;
import com.tiyay.barberShop.comisiones.domain.entity.Comision;

import com.tiyay.barberShop.comisiones.mapper.ComisionMapper;
import com.tiyay.barberShop.comisiones.repository.ComisionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComisionService {

    private final ComisionRepository comisionRepository;
    private final UsuarioRepository usuarioRepository;
    private final ServicioProductoRepository servicioProductoRepository;
    private final VentaRepository ventaRepository;

    public ComisionService(ComisionRepository comisionRepository,
                           UsuarioRepository usuarioRepository,
                           ServicioProductoRepository servicioProductoRepository,
                           VentaRepository ventaRepository) {
        this.comisionRepository = comisionRepository;
        this.usuarioRepository = usuarioRepository;
        this.servicioProductoRepository = servicioProductoRepository;
        this.ventaRepository = ventaRepository;
    }

    public List<ComisionResponseDTO> getAll() {
        return comisionRepository.findAll().stream()
                .map(ComisionMapper::toResponse)
                .toList();
    }

    public ComisionResponseDTO getById(Long id) {
        return comisionRepository.findById(id)
                .map(ComisionMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Comisión no encontrada"));
    }

    public ComisionResponseDTO create(ComisionRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        ServicioProducto servicioProducto = servicioProductoRepository.findById(dto.idServicioProducto())
                .orElseThrow(() -> new RuntimeException("ServicioProducto no encontrado"));

        Venta venta = ventaRepository.findById(dto.idVenta())
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        Comision comision = ComisionMapper.toEntity(dto, usuario, servicioProducto, venta);
        return ComisionMapper.toResponse(comisionRepository.save(comision));
    }

    public void delete(Long id) {
        comisionRepository.deleteById(id);
    }
}
