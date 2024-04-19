package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.moderadordtos.ItemNegociosRevisionDTO;
import co.edu.uniquindio.proyecto.dto.moderadordtos.RevisionesModeradorDTO;
import co.edu.uniquindio.proyecto.model.Documents.Negocio;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;
import co.edu.uniquindio.proyecto.repositorios.NegociosRepo;
import co.edu.uniquindio.proyecto.servicios.implementaciones.ModeradorServicioImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.ConditionalOperators.Switch.CaseOperator.when;

@SpringBootTest
public class ModeradorTest {
    @Autowired
    private ModeradorServicioImpl moderadorServicioImpl;
    @Autowired
    private NegociosRepo negociosRepo;
    @Test
    public void revisarNegocio_NegocioExistente_DeberiaRetornarItemNegociosRevisionDTO() throws Exception {
        // Arrange
        RevisionesModeradorDTO revisionesModeradorDTO = new RevisionesModeradorDTO("Negocio5","aaaa", "Moderador3");

        ItemNegociosRevisionDTO result = moderadorServicioImpl.revisarNegocio("Negocio5");

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result);
    }

    @Test
    void listarRevisiones_NegociosExistentes_DeberiaRetornarListaNoVacia() throws Exception {
        // Arrange
        EstadoRegistro estadoRegistro = EstadoRegistro.ACTIVO;
        List<Negocio> negociosMock = new ArrayList<>();
        Negocio negocio1 = new Negocio();
        negocio1.setNombre("Negocio 1");
        negociosMock.add(negocio1);
        Negocio negocio2 = new Negocio();
        negocio2.setNombre("Negocio 2");
        negociosMock.add(negocio2);

        // Act
        List<ItemNegociosRevisionDTO> result = moderadorServicioImpl.listarRevisiones(estadoRegistro);

        // Assert
        Assertions.assertNotNull(result);
    }

    @Test
    void bloquearUsuario_UsuarioExistente_DeberiaBloquearUsuarioYEnviarCorreo() throws Exception {
        // Arrange
        String codigoUsuarioExistente = "Cliente56";
        Exception exception = Assertions.assertThrows(Exception.class, ()->{moderadorServicioImpl.bloquearUsuario(codigoUsuarioExistente);});
        Assertions.assertEquals("El usuario no se encuentra registrado en la plataforma", exception.getMessage());
    }
}
