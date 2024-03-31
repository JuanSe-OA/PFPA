package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.model.Entidades.Horario;
import co.edu.uniquindio.proyecto.model.Enum.TipoNegocio;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import java.util.List;
import java.util.Set;

public record DetalleNegocioDTO(
        @NotBlank String nombre,
        @NotBlank String descripcion,
        @NotBlank String direccion,
        @NotBlank TipoNegocio tipoNegocio,
        @NumberFormat(style = NumberFormat.Style.NUMBER)
        @NotBlank Set<String> telefonos,
        @NotBlank List<String> imagenes,
        @NotBlank List<Horario> horarios) {
}
