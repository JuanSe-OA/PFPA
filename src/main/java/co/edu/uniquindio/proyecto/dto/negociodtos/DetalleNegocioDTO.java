package co.edu.uniquindio.proyecto.dto.negociodtos;

import co.edu.uniquindio.proyecto.model.Enum.TipoNegocio;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record DetalleNegocioDTO(
        @NotBlank String codigo,
        @NotBlank String nombre,
        @NotBlank double calificacionPromedio,
        @NotBlank int numeroCalificaciones,
        @NotBlank TipoNegocio tipoNegocio,
        @NotBlank String horaCierre,
        @NotBlank String estadoActual,
        @NotBlank List<String> imagenes) {
}
