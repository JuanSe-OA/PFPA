package co.edu.uniquindio.proyecto.model.Entidades;

import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Data
public class Cuenta {
    private String nombre,password,email;
    private EstadoRegistro estadoCuenta;
}
