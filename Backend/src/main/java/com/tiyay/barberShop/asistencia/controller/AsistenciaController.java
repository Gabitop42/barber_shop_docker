package com.tiyay.barberShop.asistencia.controller;

import com.tiyay.barberShop.asistencia.domain.entity.dto.request.AsistenciaRequestDTO;
import com.tiyay.barberShop.asistencia.domain.entity.dto.request.HoraIngresoSalidaRequestDTO;
import com.tiyay.barberShop.asistencia.domain.entity.dto.response.AsistenciaResponseDTO;
import com.tiyay.barberShop.asistencia.service.AsistenciaService;
import jakarta.validation.Valid;
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

    @PutMapping("/modificarTiempo/{id}")
    public ResponseEntity<AsistenciaResponseDTO> modificarAsistencia(
            @PathVariable Integer id,
            @Valid @RequestBody HoraIngresoSalidaRequestDTO dto) {
        return ResponseEntity.ok(asistenciaService.modificarTiempoDeAsistencia(id, dto));
    }

    @PostMapping("/marcarAsistencia/{id}")
    public ResponseEntity<AsistenciaResponseDTO> marcarAsistencia(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(asistenciaService.marcarAsistencia(id));
    }

    @PostMapping("/marcarSalida/{id}")
    public ResponseEntity<AsistenciaResponseDTO> marcarSalida(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(asistenciaService.marcarSalida(id));
    }

    @PostMapping
    public ResponseEntity<AsistenciaResponseDTO> createAsistencia(@Valid @RequestBody AsistenciaRequestDTO dto) {
        return new ResponseEntity<>(asistenciaService.createAsistencia(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsistenciaResponseDTO> updateAsistencia(
            @PathVariable Integer id,
            @Valid @RequestBody AsistenciaRequestDTO dto) {
        return ResponseEntity.ok(asistenciaService.updateAsistencia(id, dto));
    }

    @PostMapping("/crearAsistenciaManual")
    public ResponseEntity<Void> crearAsistenciasDiariasManual() {
        asistenciaService.crearAsistenciasDiariasManual();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsistencia(@PathVariable Integer id) {
        asistenciaService.deleteAsistencia(id);
        return ResponseEntity.noContent().build();
    }
}