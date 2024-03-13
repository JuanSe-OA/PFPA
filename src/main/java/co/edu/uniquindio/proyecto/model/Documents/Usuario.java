package co.edu.uniquindio.proyecto.model.Documents;

import co.edu.uniquindio.proyecto.model.Entidades.Revision;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Document ("Usuarios")

public class Usuario {
    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String nombreUsuario,ciudad,fotoPerfil;
    private List<Revision>historialDeRevisiones;
    private List<String>favoritos;
    private List<String>historialBusqueda;
}
