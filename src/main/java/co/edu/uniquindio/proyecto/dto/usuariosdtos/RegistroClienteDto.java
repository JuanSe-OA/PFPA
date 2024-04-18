package co.edu.uniquindio.proyecto.dto.usuariosdtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RegistroClienteDto(
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank String fotoPerfil,
        @NotBlank @Length(max = 15) String nombreUsuario,
        @NotBlank @Email String email,
        @NotBlank @Length(min = 8) String password,
        @NotBlank @Length(max = 40)String ciudadResidencia
) {


}
