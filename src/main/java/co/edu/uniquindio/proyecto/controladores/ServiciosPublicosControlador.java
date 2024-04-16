package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.comentariodtos.ItemComentarioDTO;
import co.edu.uniquindio.proyecto.dto.negociodtos.DetalleNegocioDTO;
import co.edu.uniquindio.proyecto.dto.negociodtos.ItemListarNegociosDTO;
import co.edu.uniquindio.proyecto.dto.negociodtos.ItemNegocioInfoDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.ComentarioServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.NegocioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/publico")
public class ServiciosPublicosControlador {

    private final ComentarioServicio comentarioServicio;
    private final NegocioServicio negocioServicio;

    @GetMapping("/listar-comentarios-negocio")
    public ResponseEntity<MensajeDTO<List<ItemComentarioDTO>>> listarComentariosNegocio(String codigo) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.listarComentariosNegocio(codigo) )
        );
    }

    @GetMapping("/buscar-negocio-por-nombre/{busqueda}")
    public ResponseEntity<MensajeDTO<List<ItemListarNegociosDTO>>> buscarNegociosPorNombre(@PathVariable String busqueda){
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.buscarNegociosPorNombre(busqueda)));
    }

    @GetMapping("/obtener-detalle-negocio/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<DetalleNegocioDTO>> obtenerDetalleNegocio(@PathVariable String codigoNegocio) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.obtenerDetalleNegocio(codigoNegocio)));
    }

    @GetMapping("/obtener-informacion-negocio/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<ItemNegocioInfoDTO>> obtenerInformacionNegocio(@PathVariable String codigoNegocio) throws Exception{
        return  ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.obtenerInformacionNegocio(codigoNegocio)));
    }

    @GetMapping("/buscar-negocios-por-distancia/{distancia}")
    public ResponseEntity<MensajeDTO<List<ItemListarNegociosDTO>>> buscarNegociosDistancia(@PathVariable double distancia){
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.buscarNegociosDistancia(distancia)));
    }
}
