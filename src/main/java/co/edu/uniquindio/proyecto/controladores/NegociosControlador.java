package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.negociodtos.*;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;
import co.edu.uniquindio.proyecto.servicios.interfaces.NegocioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/negocios")
public class NegociosControlador {
    private final NegocioServicio negocioServicio;
    @PostMapping("/crear-negocio")
    public ResponseEntity<MensajeDTO<String>> crearNegocio(@RequestBody CrearNegocioDTO crearNegocioDTO)throws Exception{
        negocioServicio.crearNegocio(crearNegocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Negocio creado correctamente"));
    }
    @PutMapping("/actualizar-negocio")
    public ResponseEntity<MensajeDTO<String>> actualizarNegocio(@Valid @RequestBody ActualizarNegocioDTO actualizarNegocioDTO)throws Exception{
        negocioServicio.actualizarNegocio(actualizarNegocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Negocio actualizado correctamente"));
    }
    @PutMapping("/eliminar-negocio/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<String>> eliminarNegocio(@PathVariable String codigoNegocio)throws Exception{
        negocioServicio.eliminarNegocio(codigoNegocio);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Negocio eliminado correctamente"));
    }
    @GetMapping("/obtener-detalle-negocio-propio/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<DetalleNegocioPropioDTO>> obtenerDetalleNegocioPropio(@PathVariable String codigoNegocio) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.obtenerDetalleNegocioPropio(codigoNegocio)));
    }
    @GetMapping("/listar-negocios-propietario/{codigoUsuario}")
    public ResponseEntity<MensajeDTO<List<ItemListarNegociosDTO>>> listarNegociosPropietario(@PathVariable String codigoUsuario)throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.listarNegociosPropietario(codigoUsuario)));
    }
    @GetMapping("/listar-negocios-favoritos/{codigoUsuario}")
    public ResponseEntity<MensajeDTO<List<ItemListarNegociosDTO>>> listarNegociosFavoritos(@PathVariable String codigoUsuario)throws Exception{
        return  ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.listarNegociosFavoritos(codigoUsuario)));
    }
    @PutMapping("/cambiar-estado")
    public ResponseEntity<MensajeDTO<String>> cambiarEstado(@RequestBody CambiarEstadoNegocioDTO cambiarEstadoNegocioDTO) throws Exception{
        negocioServicio.cambiarEstado(cambiarEstadoNegocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Estado de Negocio cambiado correctamente"));
    }
}
