package com.tiyay.barberShop.usuarios.service.impl;

import com.tiyay.barberShop.usuarios.domain.entity.Usuario;

import com.tiyay.barberShop.usuarios.domain.entity.dto.request.UsuarioRequestDTO;
import com.tiyay.barberShop.usuarios.domain.entity.dto.response.UsuarioResponseDTO;
import com.tiyay.barberShop.usuarios.mapper.UsuarioMapper;
import com.tiyay.barberShop.usuarios.repository.UsuarioRepository;
import com.tiyay.barberShop.usuarios.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper mapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper mapper) {
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
    }

    @Override
    public List<UsuarioResponseDTO> getAllUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
        return mapper.toResponseDTO(usuario);
    }

    @Override
    public UsuarioResponseDTO createUsuario(UsuarioRequestDTO dto) {
        Usuario usuario = mapper.toEntity(dto);
        Usuario saved = usuarioRepository.save(usuario);
        return mapper.toResponseDTO(saved);
    }

    @Override
    public UsuarioResponseDTO updateUsuario(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
        mapper.updateEntity(usuario, dto);
        Usuario updated = usuarioRepository.save(usuario);
        return mapper.toResponseDTO(updated);
    }

    @Override
    public void deleteUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
        usuarioRepository.delete(usuario);
    }
}