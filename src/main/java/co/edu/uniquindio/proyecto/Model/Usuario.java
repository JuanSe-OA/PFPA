package co.edu.uniquindio.proyecto.Model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class Usuario {
    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String nombreUsuario,ciudad,fotoPerfil;
    private List<Revision>historialDeRevisiones;
    private List<String>favoritos;
    private List<String>historialBusqueda;
}
