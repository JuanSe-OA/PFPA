package co.edu.uniquindio.proyecto.Model;

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
    private String descripcion,codigoModerador,codigoEntidad,respuesta;
    private EstadoRevision estado;
    private LocalDate fecha;

}
