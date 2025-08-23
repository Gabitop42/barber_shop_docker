package com.tiyay.barberShop.asistencia.controller;


import com.tiyay.barberShop.asistencia.domain.entity.dto.AsistenciaRequestDTO;
import com.tiyay.barberShop.asistencia.domain.entity.dto.AsistenciaResponseDTO;
import com.tiyay.barberShop.asistencia.service.AsistenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asistencia")
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    public AsistenciaController(AsistenciaService asistenciaService) {
        this.asistenciaService = asistenciaService;
    }

    @GetMapping
    public ResponseEntity<List<AsistenciaResponseDTO>> getAllAsistencias() {
        return ResponseEntity.ok(asistenciaService.getAllAsistencias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsistenciaResponseDTO> getAsistenciaById(@PathVariable Integer id) {
        return ResponseEntity.ok(asistenciaService.getAsistenciaById(id));
    }

    @PostMapping
    public ResponseEntity<AsistenciaResponseDTO> createAsistencia(@RequestBody AsistenciaRequestDTO dto) {
        return new ResponseEntity<>(asistenciaService.createAsistencia(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsistenciaResponseDTO> updateAsistencia(@PathVariable Integer id,
                                                                  @RequestBody AsistenciaRequestDTO dto) {
        return ResponseEntity.ok(asistenciaService.updateAsistencia(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsistencia(@PathVariable Integer id) {
        asistenciaService.deleteAsistencia(id);
        return ResponseEntity.noContent().build();
    }
}