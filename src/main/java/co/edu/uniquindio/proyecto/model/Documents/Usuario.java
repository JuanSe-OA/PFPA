package co.edu.uniquindio.proyecto.model.Documents;

import co.edu.uniquindio.proyecto.model.Entidades.Cuenta;
import co.edu.uniquindio.proyecto.model.Entidades.Revision;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.beans.ConstructorProperties;
import java.util.List;
@Getter
//@Builder
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document ("Usuarios")

public class Usuario extends Cuenta{
    @Id
    private String codigo;
    private String nombreUsuario,ciudad,fotoPerfil;
    private List<Revision>historialDeRevisiones;
    private List<String>favoritos;
    private List<String>historialBusqueda;

}
