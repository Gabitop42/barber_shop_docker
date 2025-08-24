package com.tiyay.barberShop.usuarios.mapper;

import com.tiyay.barberShop.usuarios.domain.entity.Rol;
import com.tiyay.barberShop.usuarios.domain.entity.Usuario;

import com.tiyay.barberShop.usuarios.domain.entity.dto.request.UsuarioRequestDTO;
import com.tiyay.barberShop.usuarios.domain.entity.dto.response.UsuarioResponseDTO;
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
                usuario.isActivo(),
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
        usuario.setRol(Rol.BARBERO);
        return usuario;
    }

    public void updateEntity(Usuario usuario, UsuarioRequestDTO dto) {
        usuario.setEmail(dto.email());
        usuario.setContraseña(dto.contraseña());
        usuario.setNombre(dto.nombre());
        usuario.setApellido(dto.apellido());
        usuario.setTelefono(dto.telefono());
    }
}