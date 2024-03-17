package co.edu.uniquindio.proyecto.dto;

import org.hibernate.validator.constraints.Length;

public record CambioPasswordDto(
        @Length(min = 8) String passwordNueva,
        String id,
        String token
) {
}