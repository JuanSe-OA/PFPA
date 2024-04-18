package co.edu.uniquindio.proyecto.dto.moderadordtos;

import co.edu.uniquindio.proyecto.model.Entidades.Revision;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRevision;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record RevisionesModeradorDTO(
        @NotBlank String codigoNegocio,
        @NotBlank String descripcion,
        @NotBlank String codigoModerador,
        LocalDateTime fecha


        ) {
}
