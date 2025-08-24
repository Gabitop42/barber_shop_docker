package com.tiyay.barberShop.usuarios.service;

import com.tiyay.barberShop.usuarios.domain.entity.Usuario;

import com.tiyay.barberShop.usuarios.domain.entity.dto.request.UsuarioRequestDTO;
import com.tiyay.barberShop.usuarios.domain.entity.dto.response.UsuarioResponseDTO;
import com.tiyay.barberShop.usuarios.mapper.UsuarioMapper;
import com.tiyay.barberShop.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper mapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper mapper) {
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
    }

    public List<UsuarioResponseDTO> getAllUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
        return mapper.toResponseDTO(usuario);
    }

    public UsuarioResponseDTO createUsuario(UsuarioRequestDTO dto) {
        Usuario usuario = mapper.toEntity(dto);
        Usuario saved = usuarioRepository.save(usuario);
        return mapper.toResponseDTO(saved);
    }

    public UsuarioResponseDTO updateUsuario(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
        mapper.updateEntity(usuario, dto);
        Usuario updated = usuarioRepository.save(usuario);
        return mapper.toResponseDTO(updated);
    }

    public void deleteUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
        usuarioRepository.delete(usuario);
    }
}