package co.edu.uniquindio.proyecto.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@Document("Comentarios")
@ToString
public class Comentario implements Serializable {
    @Id
    private String codigo;
    private String mensaje,respuesta,codigoUsuario,codigoNegocio;
    private double calificacion;
    private LocalTime fecha;
}
