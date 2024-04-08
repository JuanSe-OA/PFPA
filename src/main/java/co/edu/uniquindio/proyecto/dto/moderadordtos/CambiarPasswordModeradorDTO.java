package co.edu.uniquindio.proyecto.dto.moderadordtos;

import org.hibernate.validator.constraints.Length;

public record CambiarPasswordModeradorDTO(@Length(min = 8) String passwordNueva,
                                          String id,
                                          String token) {
}
