package co.edu.uniquindio.proyecto.dto.negociodtos;

import co.edu.uniquindio.proyecto.model.Enum.TipoNegocio;
import co.edu.uniquindio.proyecto.model.Enum.Ubicacion;
import jakarta.validation.constraints.NotBlank;

public record ItemMarcadorNegocioDTO(
        @NotBlank String codigoNegocio,
        @NotBlank String nombre,
        @NotBlank String estadoActual,
        @NotBlank TipoNegocio tipoNegocio,
        @NotBlank String imagen,
        @NotBlank Ubicacion ubicacion
) {
}
