package co.edu.uniquindio.proyecto.dto.negociodtos;

import co.edu.uniquindio.proyecto.model.Enum.TipoNegocio;
import co.edu.uniquindio.proyecto.model.Enum.Ubicacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DetalleNegocioDTO(
        @NotBlank String codigo,
        @NotBlank String nombre,
        @NotBlank double calificacionPromedio,
        @NotBlank int numeroCalificaciones,
        @NotBlank TipoNegocio tipoNegocio,
        @NotBlank String horaCierre,
        @NotBlank String estadoActual,
        @NotBlank List<String> imagenes,
        @NotNull Ubicacion ubicacion) {
}
