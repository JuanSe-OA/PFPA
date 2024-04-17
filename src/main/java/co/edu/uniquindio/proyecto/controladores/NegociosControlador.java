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
    public ResponseEntity<MensajeDTO<String>> crearNegocio(@Valid @RequestBody CrearNegocioDTO crearNegocioDTO)throws Exception{
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
    @GetMapping("/buscar-negocio-por-nombre/{busqueda}")
    public ResponseEntity<MensajeDTO<List<ItemListarNegociosDTO>>> buscarNegociosPorNombre(@PathVariable String busqueda){
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.buscarNegociosPorNombre(busqueda)));
    }
    @GetMapping("/obtener-detalle-negocio/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<DetalleNegocioDTO>> obtenerDetalleNegocio(@PathVariable String codigoNegocio) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.obtenerDetalleNegocio(codigoNegocio)));
    }
    @GetMapping("/obtener-detalle-negocio-propio/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<DetalleNegocioPropioDTO>> obtenerDetalleNegocioPropio(@PathVariable String codigoNegocio) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.obtenerDetalleNegocioPropio(codigoNegocio)));
    }
    @GetMapping("/obtener-informacion-negocio/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<ItemNegocioInfoDTO>> obtenerInformacionNegocio(@PathVariable String codigoNegocio) throws Exception{
        return  ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.obtenerInformacionNegocio(codigoNegocio)));
    }
    @GetMapping("/listar-negocios-propietario/{codigoUsuario}")
    public ResponseEntity<MensajeDTO<List<ItemListarNegociosDTO>>> listarNegociosPropietario(@PathVariable String codigoUsuario)throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.listarNegociosPropietario(codigoUsuario)));
    }
    @GetMapping("/listar-negocios-favoritos/{codigoUsuario}")
    public ResponseEntity<MensajeDTO<List<ItemListarNegociosDTO>>> listarNegociosFavoritos(@PathVariable String codigoUsuario)throws Exception{
        return  ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.listarNegociosFavoritos(codigoUsuario)));
    }
    @GetMapping("/buscar-negocios-por-distancia/{distancia}")
    public ResponseEntity<MensajeDTO<List<ItemListarNegociosDTO>>> buscarNegociosDistancia(@PathVariable double distancia){
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.buscarNegociosDistancia(distancia)));
    }
    @PutMapping("/cambiar-estado/{codigoNegocio}{estadoRegistro}")
    public ResponseEntity<MensajeDTO<String>> cambiarEstado(@PathVariable String codigoNegocio, @PathVariable EstadoRegistro estadoRegistro) throws Exception{
        negocioServicio.cambiarEstado(codigoNegocio,estadoRegistro);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Estado de Negocio cambiado correctamente"));
    }
}
