package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.comentariodtos.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.dto.comentariodtos.ItemComentarioDTO;
import co.edu.uniquindio.proyecto.dto.comentariodtos.ResponderComentarioDTO;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.CambioPasswordDto;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.ItemUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.RegistroClienteDto;
import co.edu.uniquindio.proyecto.servicios.implementaciones.ComentarioServicioImpl;
import co.edu.uniquindio.proyecto.servicios.interfaces.ComentarioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comentarios")
public class ComentarioControlador {
    private final ComentarioServicioImpl comentarioServicio;
    @PostMapping("/crear-comentario")
    public ResponseEntity<MensajeDTO<String>> crearComentario(@Valid @RequestBody
                                                                  CrearComentarioDTO crearComentarioDTO)throws Exception{
        comentarioServicio.crearComentario(crearComentarioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario creado correctamente")
        );
    }
    @PutMapping("/responder-comentario")
    public ResponseEntity<MensajeDTO<String>> responderComentario(@RequestBody ResponderComentarioDTO responderComentarioDTO)throws Exception{
        comentarioServicio.responderComentario(responderComentarioDTO);
        return  ResponseEntity.ok().body(new MensajeDTO<>(false,"Respuesta publicada correctamente") );
    }
    @GetMapping("/listar-comentarios/{codigo}")
    public ResponseEntity<MensajeDTO<List<ItemComentarioDTO>>> listarComentariosNegocio(@PathVariable @Valid String codigo) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.listarComentariosNegocio(codigo) )
        );
    }
}
