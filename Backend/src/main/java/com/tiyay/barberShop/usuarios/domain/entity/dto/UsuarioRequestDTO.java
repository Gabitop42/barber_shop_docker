package com.tiyay.barberShop.usuarios.domain.entity.dto;
import com.tiyay.barberShop.usuarios.domain.entity.Rol;

public record UsuarioRequestDTO(
        String email,
        String contraseña,
        String nombre,
        String apellido,
        String telefono,
        String estado,
        Rol rol
) {}