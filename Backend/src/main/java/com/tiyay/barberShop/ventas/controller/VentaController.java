package com.tiyay.barberShop.ventas.controller;

import com.tiyay.barberShop.ventas.domain.entity.dto.request.VentaRequestDTO;
import com.tiyay.barberShop.ventas.domain.entity.dto.response.VentaResponseDTO;
import com.tiyay.barberShop.ventas.service.impl.VentaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venta")
public class VentaController {

    private final VentaServiceImpl ventaServiceImpl;

    public VentaController(VentaServiceImpl ventaServiceImpl) {
        this.ventaServiceImpl = ventaServiceImpl;
    }

    @GetMapping
    public List<VentaResponseDTO> getAll() {
        return ventaServiceImpl.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ventaServiceImpl.getById(id));
    }

    @PostMapping
    public ResponseEntity<VentaResponseDTO> create(@Valid @RequestBody VentaRequestDTO dto) {
        return new ResponseEntity<>(ventaServiceImpl.create(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ventaServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}
