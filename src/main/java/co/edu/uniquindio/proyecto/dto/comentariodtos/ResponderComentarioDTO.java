package co.edu.uniquindio.proyecto.dto.comentariodtos;

import jakarta.validation.constraints.NotBlank;

public record ResponderComentarioDTO(@NotBlank String codigo,
                                     @NotBlank String respuesta,
                                     @NotBlank String codigoUsuario) {
}
