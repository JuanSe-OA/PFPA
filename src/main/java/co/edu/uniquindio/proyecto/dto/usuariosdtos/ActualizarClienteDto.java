package co.edu.uniquindio.proyecto.dto.usuariosdtos;

import jakarta.validation.constraints.NotBlank;

public record ActualizarClienteDto (
        @NotBlank String id,
        @NotBlank String nombre,
        @NotBlank String fotoPerfil,
        @NotBlank String email,
        @NotBlank String ciudadResidencia
) {

}