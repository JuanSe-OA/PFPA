package co.edu.uniquindio.proyecto.model.Documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document ("Moderadores")
public class Moderador {
    @Id
    private String codigo;
}
