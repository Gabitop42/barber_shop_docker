package com.tiyay.barberShop.servicio_producto.controller;

import com.tiyay.barberShop.servicio_producto.domain.entity.dto.request.ServicioProductoRequestDTO;
import com.tiyay.barberShop.servicio_producto.domain.entity.dto.response.ServicioProductoResponseDTO;
import com.tiyay.barberShop.servicio_producto.service.impl.ServicioProductoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicio-producto")
public class ServicioProductoController {

    private final ServicioProductoServiceImpl service;

    public ServicioProductoController(ServicioProductoServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ServicioProductoResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioProductoResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ServicioProductoResponseDTO> create(@Valid @RequestBody ServicioProductoRequestDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioProductoResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ServicioProductoRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
