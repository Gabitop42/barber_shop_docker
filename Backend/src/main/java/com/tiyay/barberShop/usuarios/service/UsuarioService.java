package com.tiyay.barberShop.usuarios.service;

import com.tiyay.barberShop.usuarios.domain.entity.dto.request.UsuarioRequestDTO;
import com.tiyay.barberShop.usuarios.domain.entity.dto.response.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponseDTO> getAllUsuarios();

    UsuarioResponseDTO getUsuarioById(Long id);

    UsuarioResponseDTO createUsuario(UsuarioRequestDTO dto);

    UsuarioResponseDTO updateUsuario(Long id, UsuarioRequestDTO dto);

    void deleteUsuario(Long id);
}
