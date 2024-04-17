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
import java.util.Optional;

@SpringBootTest
public class ComentarioTes {
    @Autowired
    private ComentarioServicioImpl comentarioServicioImpl;
    @Autowired
    private ComentariosRepo comentariosRepo;
    @Test
    public void CrearComentario_Test() {
        CrearComentarioDTO crearComentarioDTO = new CrearComentarioDTO(
                "COMENTARIO122222",
                "Jamás vuelvo ahí",
                "Cliente1",
                "negocio1",
                1,
                LocalDate.now()
        );
        String codigo = comentarioServicioImpl.crearComentario(crearComentarioDTO);
        Assertions.assertNotNull(codigo);
    }
    @Test
    public void responderComentario_Test() throws Exception {
        // Código de comentario existente en la base de datos
        String codigoComentario = "661f4faebec1346cbb93f65d";
        // Mensaje de respuesta
        String mensajeRespuesta = "Gracias por visitarnos";

        // Llamada al método a probar
        comentarioServicioImpl.responderComentario(codigoComentario, mensajeRespuesta);

        // Verificar que el comentario se haya guardado correctamente en la base de datos
        Optional<Comentario> comentarioGuardado = comentariosRepo.findById(codigoComentario);
        Assertions.assertTrue(comentarioGuardado.isPresent(), "El comentario no se ha guardado en la base de datos");

        // Verificar que la respuesta no sea nula
        Assertions.assertNotNull(comentarioGuardado.get().getRespuesta(), "La respuesta del comentario es nula");

    }
}
