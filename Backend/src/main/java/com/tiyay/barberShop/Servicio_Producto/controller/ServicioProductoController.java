package com.tiyay.barberShop.Servicio_Producto.controller;

import com.tiyay.barberShop.Servicio_Producto.domain.entity.dto.ServicioProductoRequestDTO;
import com.tiyay.barberShop.Servicio_Producto.domain.entity.dto.ServicioProductoResponseDTO;
import com.tiyay.barberShop.Servicio_Producto.service.ServicioProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicio-producto")
public class ServicioProductoController {

    private final ServicioProductoService service;

    public ServicioProductoController(ServicioProductoService service) {
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
    public ResponseEntity<ServicioProductoResponseDTO> create(@RequestBody ServicioProductoRequestDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioProductoResponseDTO> update(@PathVariable Long id,
                                                              @RequestBody ServicioProductoRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
