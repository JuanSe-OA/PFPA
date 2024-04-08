package co.edu.uniquindio.proyecto.dto.moderadordtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

public record RevisarComentariosDTO(
        @NotBlank String mensaje,
        @NotBlank String respuesta,
        @NotBlank String codigoUsuario,
        @NotBlank String codigoNegocio,
        @NotBlank  @Range(min=1, max=5)
        double calificacion,
        @NotBlank LocalDate fecha

) {
}
