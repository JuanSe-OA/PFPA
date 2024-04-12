package co.edu.uniquindio.proyecto.dto.negociodtos;

import co.edu.uniquindio.proyecto.model.Entidades.Horario;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;
import java.util.List;

public record ItemNegocioInfoDTO(
        @NotBlank String codigoNegocio,
        @NotBlank String descripcion,
        @NotBlank String direccion,
        @NotBlank Set<String> telefonos,
        @NotBlank List<Horario> horarios) {
}
