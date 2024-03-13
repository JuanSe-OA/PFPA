package co.edu.uniquindio.proyecto.Model;

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
