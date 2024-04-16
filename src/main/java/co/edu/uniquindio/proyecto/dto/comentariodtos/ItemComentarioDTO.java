package co.edu.uniquindio.proyecto.dto.comentariodtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record ItemComentarioDTO(
        @NotBlank String fotoPerfilUsuario,
        @NotBlank String nombreUsuario,
        @NotBlank String codigoComentario,
        @NotBlank String mensaje,
        String respuesta,
        @Range(min=1, max=5)
        @NotBlank double calificacion,
        @NotBlank LocalDate fecha) {
}
