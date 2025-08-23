package com.tiyay.barberShop.usuarios.domain.entity.dto;

import com.tiyay.barberShop.usuarios.domain.entity.Rol;

public record UsuarioResponseDTO(
        Long idUsuario,
        String email,
        String nombre,
        String apellido,
        String telefono,
        String estado,
        Rol rol
) {}