package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.negociodtos.*;
import co.edu.uniquindio.proyecto.model.Entidades.Horario;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;
import co.edu.uniquindio.proyecto.model.Enum.TipoNegocio;
import co.edu.uniquindio.proyecto.model.Enum.Ubicacion;
import co.edu.uniquindio.proyecto.servicios.implementaciones.NegocioServicioImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class NegocioTest {
    @Autowired
    private NegocioServicioImpl negocioServicio;

    @Test
    public void crearNegocioTest()throws Exception{
        Ubicacion ubicacion= new Ubicacion(4.098886,-88.09234);
        Set<String> telefonos= new HashSet<>();
        telefonos.add("3120985665");
        List<Horario> horarios= new ArrayList<>();
        horarios.add(new Horario("Lunes", LocalTime.of(9,0), LocalTime.of(22,0)));
        horarios.add(new Horario("Jueves", LocalTime.of(10,0), LocalTime.of(22,0)));
        horarios.add(new Horario("Sabado", LocalTime.of(10,0), LocalTime.of(23,0)));
        List<String> imagenes = new ArrayList<>();
        imagenes.add("Imagen.PNG");
        imagenes.add("Imagen2.PNG");
        CrearNegocioDTO crearNegocioDTO = new CrearNegocioDTO(
                "Luiggi's Pizza",
                "descripcion",
                "Cliente4",
                "Direccion",
                TipoNegocio.RESTAURANTE,
                ubicacion,
                telefonos,
                horarios,
                imagenes);
        String codigoNegocio = negocioServicio.crearNegocio(crearNegocioDTO);
        Assertions.assertNotNull(codigoNegocio);
    }

    @Test
    public void actualizarNegocioTest(){
        Ubicacion ubicacion= new Ubicacion(67.098886,-150.09234);
        Set<String> telefonos= new HashSet<>();
        telefonos.add("312283721893314");
        List<Horario> horarios= new ArrayList<>();
        horarios.add(new Horario("Martes", LocalTime.of(9,0), LocalTime.of(22,0)));
        horarios.add(new Horario("Jueves", LocalTime.of(10,0), LocalTime.of(22,0)));
        horarios.add(new Horario("Domingo", LocalTime.of(10,0), LocalTime.of(23,0)));
        List<String> imagenes = new ArrayList<>();
        imagenes.add("Imagen.PNG");
        imagenes.add("Imagen2.PNG");
        ActualizarNegocioDTO actualizarNegocioDTO = new ActualizarNegocioDTO(
                "Negocio62",
                "Tienda juj",
                "Tienda :)",
                "Direccion",
                telefonos,
                imagenes,
                horarios,
                ubicacion);
        Exception exception = Assertions.assertThrows(Exception.class, () -> {negocioServicio.actualizarNegocio(actualizarNegocioDTO);});
        Assertions.assertEquals("El negocio a modificar no ha sido encontrado",exception.getMessage());
    }

    @Test
    public void eliminarNegocioTest(){
        Exception exception = Assertions.assertThrows(Exception.class, () ->{negocioServicio.eliminarNegocio("Negocio90");});
        Assertions.assertEquals("El negocioo a eliminar no ha sido encontrado",exception.getMessage());
    }

    @Test
    public void buscarNegociosPorNombreTest(){
        List<ItemListarNegociosDTO> listarNegociosDTOS = negocioServicio.buscarNegociosPorNombre("Libreria Fantasia");
        Assertions.assertNotNull(listarNegociosDTOS);
    }

    @Test
    public void buscarNegociosPorNombreFailTest(){
        List<ItemListarNegociosDTO> listarNegociosDTOS = negocioServicio.buscarNegociosPorNombre("Negocio Inexistente");
        Assertions.assertTrue(listarNegociosDTOS.isEmpty());
    }

    @Test
    public void obtenerDetalleNegocioTest(){
        Exception exception = Assertions.assertThrows(Exception.class, () ->{negocioServicio.obtenerDetalleNegocio("Negocio76");});
        Assertions.assertEquals("No se ha podido encontrar el negocio",exception.getMessage());
    }

    @Test
    public void obtenertDetalleNegocioOkTest()throws Exception{
        DetalleNegocioDTO detalleNegocioDTO = negocioServicio.obtenerDetalleNegocio("661ffd9dd090bb1e457e559f");
        Assertions.assertNotNull(detalleNegocioDTO);
    }

    @Test
    public void obtenerInformacionNegocioTest(){
        Exception exception = Assertions.assertThrows(Exception.class, () ->{negocioServicio.obtenerInformacionNegocio("Negocio54");});
        Assertions.assertEquals("No se ha podido encontrar el negocio", exception.getMessage());
    }

    @Test
    public void obtenerInformacionNegocioOkTest()throws Exception{
        ItemNegocioInfoDTO itemNegocioInfoDTO = negocioServicio.obtenerInformacionNegocio("661ffd9dd090bb1e457e559f");
        Assertions.assertNotNull(itemNegocioInfoDTO);
    }

    @Test
    public void listarNegociosPropietarioTest()throws Exception{
        List<ItemListarNegociosDTO> detalleNegocioDTOS= negocioServicio.listarNegociosPropietario("Cliente48");
        Assertions.assertTrue(detalleNegocioDTOS.isEmpty());
    }

    @Test
    public void listarNegociosPropiosOkTest()throws Exception{
        List<ItemListarNegociosDTO> detalleNegocioDTOS= negocioServicio.listarNegociosPropietario("Cliente4");
        Assertions.assertFalse(detalleNegocioDTOS.isEmpty());
    }

    @Test
    public void listarNegociosFavoritosTest()throws Exception{
        Exception exception = Assertions.assertThrows(Exception.class, ()->{negocioServicio.listarNegociosFavoritos("Cliente 62");});
        Assertions.assertEquals("No se ha encontrado al usuario",exception.getMessage());
    }

    @Test
    public void cambiarEstadotest(){
        Exception exception= Assertions.assertThrows(Exception.class, ()->{negocioServicio.cambiarEstado("Negocio87", EstadoRegistro.INACTIVO);});
        Assertions.assertEquals("El negocio a modificar no se ha encontrado", exception.getMessage());
    }
}
