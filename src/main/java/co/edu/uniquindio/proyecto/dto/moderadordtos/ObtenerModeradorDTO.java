package co.edu.uniquindio.proyecto.dto.moderadordtos;

import jakarta.validation.constraints.Email;

public record ObtenerModeradorDTO(
        String id,
        @Email String email,
        String password
) {
}
