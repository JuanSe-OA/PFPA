package co.edu.uniquindio.proyecto.model.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@AllArgsConstructor
@Setter
@Getter
public class Horario {
    private String dia;
    private LocalTime horaInicio,horaFin;
}
