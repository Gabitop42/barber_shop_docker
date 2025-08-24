package com.tiyay.barberShop.usuarios.domain.entity.dto.request;
import com.tiyay.barberShop.usuarios.domain.entity.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record UsuarioRequestDTO(

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email debe tener un formato válido")
        String email,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String contraseña,

        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        String apellido,

        @NotBlank(message = "El teléfono es obligatorio")
        @Pattern(regexp = "\\d{9}", message = "El teléfono debe contener entre 9 dígitos numéricos")
        String telefono

) {}