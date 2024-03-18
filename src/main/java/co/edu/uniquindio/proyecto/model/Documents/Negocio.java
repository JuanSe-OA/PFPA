package co.edu.uniquindio.proyecto.model.Documents;

import co.edu.uniquindio.proyecto.model.Entidades.Horario;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;
import co.edu.uniquindio.proyecto.model.Entidades.Revision;
import co.edu.uniquindio.proyecto.model.Enum.TipoNegocio;
import co.edu.uniquindio.proyecto.model.Enum.Ubicacion;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Document("Negocios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Negocio implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String nombre,descripcion,codigoUsuario,direccion;
    private Set<String> telefonos;
    private TipoNegocio tipoNegocio;
    private List<String>imagenes;
    private EstadoRegistro estadoRegistro;
    private List<Horario>horarios;
    private Ubicacion ubicacion;
    private List<Revision> HistorialRevisiones;

}
