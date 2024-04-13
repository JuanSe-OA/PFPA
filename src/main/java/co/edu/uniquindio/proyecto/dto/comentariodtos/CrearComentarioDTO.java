package co.edu.uniquindio.proyecto.dto.comentariodtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record CrearComentarioDTO(
        @NotBlank @Id String id,
        @NotBlank String mensaje,
        @NotBlank String codigoUsuario,
        @NotBlank String codigoNegocio,
        @Range(min=1, max=5)
        @NotBlank double calificacion,
        @NotBlank LocalDate fecha) {
}
