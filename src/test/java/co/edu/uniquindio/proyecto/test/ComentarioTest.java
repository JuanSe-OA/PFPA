package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.comentariodtos.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.model.Documents.Comentario;
import co.edu.uniquindio.proyecto.repositorios.ComentariosRepo;
import co.edu.uniquindio.proyecto.servicios.implementaciones.ComentarioServicioImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ComentarioTest {
    @Autowired
    private ComentarioServicioImpl comentarioServicioImpl;
    @Autowired
    private ComentariosRepo comentariosRepo;
    @Test
    public void crearComentario_Test() throws Exception{
        CrearComentarioDTO crearComentarioDTO = new CrearComentarioDTO(
                "Jamás vuelvo ahí",
                "Cliente1",
                "negocio1",
                1
        );
        String codigo = comentarioServicioImpl.crearComentario(crearComentarioDTO);
        Assertions.assertNotNull(codigo);
    }
    @Test
    public void responderComentario_Test() throws Exception {
        // Código de comentario existente en la base de datos
        String codigoComentario = "661f34e579bc8e47517b0fb4";
        // Mensaje de respuesta
        String mensajeRespuesta = "Gracias por visitarnos";

        // Llamada al método a probar
        comentarioServicioImpl.responderComentario(new ResponderComentarioDTO(codigoComentario,mensajeRespuesta));

        // Verificar que el comentario se haya guardado correctamente en la base de datos
        Optional<Comentario> comentarioGuardado = comentariosRepo.findById(codigoComentario);
        Assertions.assertTrue(comentarioGuardado.isPresent(), "El comentario no se ha guardado en la base de datos");

        // Verificar que la respuesta no sea nula
        Assertions.assertNotNull(comentarioGuardado.get().getRespuesta(), "La respuesta del comentario es nula");
    }

    @Test
    public void listarComentariosNegocioTest()throws Exception{
        List<ItemComentarioDTO> itemsComentariosNegocio= comentarioServicioImpl.listarComentariosNegocio("Negocio4");
        Assertions.assertFalse(itemsComentariosNegocio.isEmpty());
    }

    @Test
    public void calcularPromedioCalificacionesTest(){
        double promedio = comentarioServicioImpl.calcularPromedioCalificaciones("Negocio9");
        Assertions.assertEquals(promedio,0);
    }

    @Test
    public void calcularPromedioCalificacionesOkTest(){
        double promedio = comentarioServicioImpl.calcularPromedioCalificaciones("Negocio4");
        Assertions.assertEquals(promedio,4);
    }
}
