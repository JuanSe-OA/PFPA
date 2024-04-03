package co.edu.uniquindio.proyecto.dto.negociodtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record CrearComentarioDTO(
        @NotBlank String mensaje,
        @NotBlank String codigoUsuario,
        @NotBlank String codigoNegocio,
        @Range(min=1, max=5)
        @NotBlank double calificacion,
        @NotBlank @DateTimeFormat LocalDate fecha) {
}
