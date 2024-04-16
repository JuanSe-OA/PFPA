package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.usuariosdtos.RegistroClienteDto;
import co.edu.uniquindio.proyecto.model.Documents.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuariosRepo;
import co.edu.uniquindio.proyecto.servicios.implementaciones.UsuarioServicioImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class ClienteTest {
    @Autowired
    private UsuarioServicioImpl usuarioServicio;

    @Test
    public void registrarClienteTest() throws Exception {
        RegistroClienteDto registroClienteDto = new RegistroClienteDto(
                "Jaime",
                "URL",
                "Jaimesito",
                "juanma235235@gmail.com",
                "1234abcd12",
                "Armenia");

        String codigo = usuarioServicio.registrarse(registroClienteDto);
        Assertions.assertNotNull(codigo);
    }
}

