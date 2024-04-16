package co.edu.uniquindio.proyecto.dto.moderadordtos;

import co.edu.uniquindio.proyecto.model.Entidades.Revision;
import co.edu.uniquindio.proyecto.model.Enum.TipoNegocio;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import java.util.Date;

public record DetalleNegocioRevisionDTO(
        @NotBlank @Id String codigo,
        @NotBlank String nombre,
        @NotBlank String descripcion,
        @NotBlank TipoNegocio tipoNegocio,
        @NotBlank Date fecha,
        @NotBlank Revision ultimaRevision
        ) {
}
