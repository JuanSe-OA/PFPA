package co.edu.uniquindio.proyecto.dto.moderadordtos;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

public record CambiarPasswordModeradorDTO(@Length(min = 8) String passwordNueva,
                                          @Id String id
                                          ) {
}
