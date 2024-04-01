package co.edu.uniquindio.proyecto.dto.negociodtos;

import co.edu.uniquindio.proyecto.model.Entidades.Horario;
import co.edu.uniquindio.proyecto.model.Enum.Ubicacion;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import java.util.List;
import java.util.Set;

public record ActualizarNegocioDTO(
        @NotBlank String codigo,
        @NotBlank @Length(max=150) String nombre,
        @NotBlank String descripcion,
        @NotBlank @Length(max=50) String direccion,
        @NumberFormat(style = NumberFormat.Style.NUMBER)
        @NotBlank Set<String> telefonos,
        @NotBlank List<String> imagenes,
        @NotBlank List<Horario> horarios,
        @NotBlank Ubicacion ubicacion) {

}
