package co.edu.uniquindio.proyecto.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
@Getter
@Setter
@AllArgsConstructor
public class Moderador {
    @Id
    private String codigo;
}
