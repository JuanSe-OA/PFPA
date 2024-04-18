package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.UsuarioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clientes")
public class ClienteControlador {
    private final UsuarioServicio usuarioServicio;
    @PutMapping("/editar-perfil")
    public ResponseEntity<MensajeDTO<String>> actualizarCliente(@Valid @RequestBody ActualizarClienteDto actualizarClienteDTO)throws Exception{
        usuarioServicio.editarPerfil(actualizarClienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Cliente actualizado correctamente") );
    }
    @PutMapping("/editar-password")
    public ResponseEntity<MensajeDTO<String>> cambiarPassword(@PathVariable CambioPasswordDto cambioPasswordDto)throws Exception{
        usuarioServicio.actualizarPassword(cambioPasswordDto);
        return  ResponseEntity.ok().body(new MensajeDTO<>(false,"Cliente actualizado correctamente") );
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarCuenta(@PathVariable String codigo)throws
            Exception{
        usuarioServicio.eliminarPerfil(codigo);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Cliente eliminado correctamente")
        );
    }
    @GetMapping("/obtener/{codigo}")
    public ResponseEntity<MensajeDTO<MostrarPerfilDTO>> obtenerCliente(@PathVariable String codigo) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                usuarioServicio.mostrarPerfil(codigo) ) );
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<MensajeDTO<List<ItemUsuarioDTO>>> listarClientes(String busqueda) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, usuarioServicio.listarClientes(busqueda) )
        );
    }
}
