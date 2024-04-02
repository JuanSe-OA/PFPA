package co.edu.uniquindio.proyecto.model.Documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("Comentarios")
@ToString
public class Comentario implements Serializable {
    @Id
    private String codigo;
    private String mensaje,respuesta,codigoUsuario,codigoNegocio;
    private double calificacion;
    private LocalDate fecha;
}
