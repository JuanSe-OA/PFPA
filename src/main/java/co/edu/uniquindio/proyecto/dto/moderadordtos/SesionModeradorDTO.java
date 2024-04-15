package co.edu.uniquindio.proyecto.dto.moderadordtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SesionModeradorDTO(
        @NotBlank@Email String email,
        @NotBlank String password
) {
}
