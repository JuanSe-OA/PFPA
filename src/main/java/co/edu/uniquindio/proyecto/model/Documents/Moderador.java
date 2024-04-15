package co.edu.uniquindio.proyecto.model.Documents;

import co.edu.uniquindio.proyecto.model.Entidades.Cuenta;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document ("Moderadores")
public class Moderador extends Cuenta {
    @Id
    private String codigo;
}
