package co.edu.uniquindio.proyecto.dto.negociodtos;

import jakarta.validation.constraints.NotBlank;

public record   ObtenerDistanciaDTO(
        @NotBlank double latitud,
        @NotBlank double longitud,
        @NotBlank double rango
) {
}
