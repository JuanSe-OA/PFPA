package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.usuariosdtos.*;
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
    public void registrarClienteFailTest() {
        RegistroClienteDto registroClienteDto = new RegistroClienteDto(
                "Jaime",
                "URL",
                "Jaimesito",
                "juanma235235@gmail.com",
                "1234abcd12",
                "Armenia");

        //String codigo = usuarioServicio.registrarse(registroClienteDto);
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            usuarioServicio.registrarse(registroClienteDto);
        });

        // Verificas que el mensaje de la excepción es el esperado
        Assertions.assertEquals("Usted ya se encuentra registrado en nuestra plataforma", exception.getMessage());
    }

    @Test
    public void registrarClienteOkTest()throws Exception{
        RegistroClienteDto registroClienteDto = new RegistroClienteDto(
                "Juan Jaime",
                "URL",
                "Jaimesito Juan",
                "juanma235235@gmail.com",
                "contraseñarandom400982",
                "Armenia");
        String codigoUsuario = usuarioServicio.registrarse(registroClienteDto);
        Assertions.assertNotNull(codigoUsuario);
    }

    @Test
    public void editarPerfilFailTest(){
        ActualizarClienteDto actualizarClienteDto = new ActualizarClienteDto(
                "cliente1",
                "Jose",
                "PNG2",
                "email@mail.com",
                "Bogota");
        Exception exception = Assertions.assertThrows(Exception.class, () ->{usuarioServicio.editarPerfil(actualizarClienteDto);});
        Assertions.assertEquals("El usuario no se encuentra registrado", exception.getMessage());
    }

    @Test
    public void actualizarPasswordTest(){
        CambioPasswordDto cambioPasswordDto = new CambioPasswordDto("holaHolahOLA12AA32", "Cliente90");

        Exception exception = Assertions.assertThrows(Exception.class, () ->{usuarioServicio.actualizarPassword(cambioPasswordDto);});
        Assertions.assertEquals("Usted no se encuentra registrado", exception.getMessage());
    }

    @Test
    public void mostrarPerfilFailTest(){
        Exception exception = Assertions.assertThrows(Exception.class, ()->{usuarioServicio.mostrarPerfil("Cliente56");});
        Assertions.assertEquals("El usuario no se encuentra registrado",exception.getMessage());
    }

    @Test
    public void mostrarPerfilOkTest()throws Exception{
        MostrarPerfilDTO mostrarPerfilDTO = usuarioServicio.mostrarPerfil("Cliente4");
        Assertions.assertNotNull(mostrarPerfilDTO);
    }

    @Test
    public void eliminarPerfilTest(){
        Exception exception = Assertions.assertThrows(Exception.class, () -> {usuarioServicio.eliminarPerfil("Cliente22");});
        Assertions.assertEquals("No se encontró un usuario con esa id", exception.getMessage());
    }

    @Test
    public void listarClientesTest()throws Exception{
        List<ItemUsuarioDTO> itemUsuarioDTOList = usuarioServicio.listarClientes();
        Assertions.assertNotNull(itemUsuarioDTOList);
    }
}

