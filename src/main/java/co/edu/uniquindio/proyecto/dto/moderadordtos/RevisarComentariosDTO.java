package co.edu.uniquindio.proyecto.dto.moderadordtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

public record RevisarComentariosDTO(
        @NotBlank String mensaje,
        @NotBlank String codigoUsuario,
        @NotBlank String nombreUsuario,
        @NotBlank @Email String email,
        @NotBlank LocalDate fecha

) {
}
