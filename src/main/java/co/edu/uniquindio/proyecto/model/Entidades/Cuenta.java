package co.edu.uniquindio.proyecto.model.Entidades;

import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Cuenta {
    private String nombre,password,email;
    private EstadoRegistro estadoCuenta;
}
