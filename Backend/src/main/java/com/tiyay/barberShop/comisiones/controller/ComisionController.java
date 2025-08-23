package com.tiyay.barberShop.comisiones.controller;


import com.tiyay.barberShop.comisiones.domain.entity.dto.request.ComisionRequestDTO;
import com.tiyay.barberShop.comisiones.domain.entity.dto.response.ComisionResponseDTO;
import com.tiyay.barberShop.comisiones.service.ComisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comision")
public class ComisionController {

    private final ComisionService comisionService;

    public ComisionController(ComisionService comisionService) {
        this.comisionService = comisionService;
    }

    @GetMapping
    public List<ComisionResponseDTO> getAll() {
        return comisionService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComisionResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(comisionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ComisionResponseDTO> create(@RequestBody ComisionRequestDTO dto) {
        return ResponseEntity.ok(comisionService.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        comisionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
