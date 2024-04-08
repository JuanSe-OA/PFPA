package co.edu.uniquindio.proyecto.model.Entidades;

import co.edu.uniquindio.proyecto.model.Enum.EstadoRevision;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Revision {
    private String descripcion,codigoModerador,codigoEntidad;
    private EstadoRevision estado;
    private LocalDate fecha;

}
