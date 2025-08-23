package com.tiyay.barberShop.usuarios.mapper;

import com.tiyay.barberShop.usuarios.domain.entity.Usuario;

import com.tiyay.barberShop.usuarios.domain.entity.dto.UsuarioRequestDTO;
import com.tiyay.barberShop.usuarios.domain.entity.dto.UsuarioResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getIdUsuario(),
                usuario.getEmail(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getTelefono(),
                usuario.getEstado(),
                usuario.getRol()
        );
    }

    public Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.email());
        usuario.setContraseña(dto.contraseña());
        usuario.setNombre(dto.nombre());
        usuario.setApellido(dto.apellido());
        usuario.setTelefono(dto.telefono());
        usuario.setEstado(dto.estado());
        usuario.setRol(dto.rol());
        return usuario;
    }

    public void updateEntity(Usuario usuario, UsuarioRequestDTO dto) {
        usuario.setEmail(dto.email());
        usuario.setContraseña(dto.contraseña());
        usuario.setNombre(dto.nombre());
        usuario.setApellido(dto.apellido());
        usuario.setTelefono(dto.telefono());
        usuario.setEstado(dto.estado());
        usuario.setRol(dto.rol());
    }
}