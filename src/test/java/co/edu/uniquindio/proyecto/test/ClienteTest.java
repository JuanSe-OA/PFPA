package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.model.Documents.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuariosRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class ClienteTest {
    @Autowired
    private UsuariosRepo usuariosRepo;

    @Test
    public void registrarClienteTest() {
//Creamos el cliente con sus propiedades
        Usuario usuario = Usuario.builder()
                .nombreUsuario("juanitos")
                .ciudad("Armenia")
                .build();
//Guardamos el cliente
        Usuario registro = usuariosRepo.save(usuario);
//Verificamos que se haya guardado validando que no sea null
        Assertions.assertNotNull(registro);
    }
}

