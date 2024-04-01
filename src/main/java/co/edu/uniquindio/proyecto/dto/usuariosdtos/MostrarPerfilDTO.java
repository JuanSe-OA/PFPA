package co.edu.uniquindio.proyecto.dto.usuariosdtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

public record MostrarPerfilDTO(
        @NotBlank @Id String id,
        @NotBlank String nombre,
        @NotBlank String fotoPerfil,
        @NotBlank String ciudad,
        @NotBlank @Email String email) {
}
