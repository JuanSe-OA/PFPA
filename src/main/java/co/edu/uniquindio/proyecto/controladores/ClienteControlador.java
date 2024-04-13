package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.usuariosdtos.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.UsuarioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clientes")
public class ClienteControlador {
    private final UsuarioServicio usuarioServicio;
    @PostMapping("/registrar-cliente")
    public String registrarse(@Valid @RequestBody RegistroClienteDto registroClienteDTO)throws
            Exception{
        return usuarioServicio.registrarse(registroClienteDTO);
    }
    @PutMapping("/editar-perfil")
    public void editarPerfil(@Valid @RequestBody ActualizarClienteDto
                                          actualizarClienteDTO)throws Exception{
        usuarioServicio.editarPerfil(actualizarClienteDTO);
    }
    @PutMapping("/editar-password")
    public void cambiarPassword(@PathVariable CambioPasswordDto cambioPasswordDto)throws Exception{
        usuarioServicio.actualizarPassword(cambioPasswordDto);
    }

    @DeleteMapping("/eliminar/{codigo}")
    public void eliminarCuenta(@PathVariable String codigo)throws Exception{
        usuarioServicio.eliminarPerfil(codigo);
    }
    @GetMapping("/obtener/{codigo}")
    public MostrarPerfilDTO obtenerCliente(@PathVariable String codigo) throws Exception{
        return usuarioServicio.mostrarPerfil(codigo);
    }

    @GetMapping("/listar-todos")
    public List<ItemUsuarioDTO> listarClientes(String busqueda)throws Exception{
        return usuarioServicio.listarClientes(busqueda);
    }
}
