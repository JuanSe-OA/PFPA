package co.edu.uniquindio.proyecto.dto.moderadordtos;

import co.edu.uniquindio.proyecto.model.Entidades.Revision;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRevision;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public record RevisionesModeradorDTO(
        @NotBlank String codigoNegocio,
        @NotBlank String descripcion,
        @NotBlank String codigoModerador,
        @NotBlank String respuesta,
        @NotBlank LocalDate fecha


        ) {
}
