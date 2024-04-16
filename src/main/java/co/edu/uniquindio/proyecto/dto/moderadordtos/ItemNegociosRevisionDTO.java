package co.edu.uniquindio.proyecto.dto.moderadordtos;

import co.edu.uniquindio.proyecto.model.Entidades.Horario;
import co.edu.uniquindio.proyecto.model.Entidades.Revision;
import co.edu.uniquindio.proyecto.model.Enum.TipoNegocio;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;
import java.util.Set;

public record ItemNegociosRevisionDTO(
        @NotBlank String nombre,
        @NotBlank String descripcion,
        @NotBlank Set<String> telefonos,
        @NotBlank String direccion,
        @NotBlank List<Horario>horarios,
        @NotBlank TipoNegocio tipoNegocio,
        @NotBlank List<String>listaImagens
) {
}
