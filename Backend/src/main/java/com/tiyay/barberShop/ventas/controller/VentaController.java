package com.tiyay.barberShop.ventas.controller;


import com.tiyay.barberShop.ventas.domain.entity.dto.request.VentaRequestDTO;
import com.tiyay.barberShop.ventas.domain.entity.dto.response.VentaResponseDTO;
import com.tiyay.barberShop.ventas.service.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venta")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public List<VentaResponseDTO> getAll() {
        return ventaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.getById(id));
    }

    @PostMapping
    public ResponseEntity<VentaResponseDTO> create(@RequestBody VentaRequestDTO dto) {
        return ResponseEntity.ok(ventaService.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ventaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
