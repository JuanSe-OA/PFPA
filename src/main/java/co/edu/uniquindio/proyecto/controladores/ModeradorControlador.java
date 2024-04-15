package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.moderadordtos.CambiarPasswordModeradorDTO;
import co.edu.uniquindio.proyecto.dto.moderadordtos.RevisionesModeradorDTO;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.ActualizarClienteDto;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.CambioPasswordDto;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.ItemUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.MostrarPerfilDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.ModeradorServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/moderador")
public class ModeradorControlador {
    private final ModeradorServicio moderadorServicio;

    @PutMapping("/editar-password")
    public ResponseEntity<MensajeDTO<String>> cambiarPassword(@PathVariable CambiarPasswordModeradorDTO cambiarPasswordModeradorDTO)throws Exception{
        moderadorServicio.actualizarPassword(cambiarPasswordModeradorDTO);
        return  ResponseEntity.ok().body(new MensajeDTO<>(false,"Moderador actualizado correctamente") );
    }

    @PutMapping("/Aceptar/Negocio")
    public ResponseEntity<MensajeDTO<String>> AceptarNegocio(@PathVariable RevisionesModeradorDTO revisionesModeradorDTO)throws
            Exception{
        moderadorServicio.aceptarNegocio(revisionesModeradorDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Negocio aceptado correctamente")
        );
    }
    @PutMapping("/Revisar/Negocio")
    public ResponseEntity<MensajeDTO<String>> RevisarNegocio(@PathVariable RevisionesModeradorDTO revisionesModeradorDTO)throws
            Exception{
        moderadorServicio.revisarNegocio(revisionesModeradorDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Negocio revisado correctamente")
        );
    }
    @PutMapping("/Rechazar/Negocio")
    public ResponseEntity<MensajeDTO<String>> RechazarNegocio(@PathVariable RevisionesModeradorDTO revisionesModeradorDTO)throws
            Exception{
        moderadorServicio.rechazarNegocio(revisionesModeradorDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Negocio rechazado correctamente")
        );
    }
    @PutMapping("/Bloquear/{codigo}")
    public ResponseEntity<MensajeDTO<String>> BloquearUsuario(@PathVariable String codigo)throws
            Exception{
        moderadorServicio.bloquearUsuario(codigo);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Cliente bloqueado correctamente")
        );
    }


}