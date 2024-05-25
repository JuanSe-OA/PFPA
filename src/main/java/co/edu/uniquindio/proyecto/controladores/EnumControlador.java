package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.model.Enum.Ciudades;
import co.edu.uniquindio.proyecto.model.Enum.TipoNegocio;
import co.edu.uniquindio.proyecto.servicios.implementaciones.EnumServicioImpl;
import co.edu.uniquindio.proyecto.servicios.interfaces.EnumServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/publico")
@RequiredArgsConstructor
public class EnumControlador {
    private final EnumServicio enumServicio;
    private final EnumServicioImpl enumServicioImpl;
    @GetMapping("/tipos-de-negocio")
    public ResponseEntity<MensajeDTO<List<TipoNegocio>>> obtenerTiposDeNegocio() throws Exception {
        List<TipoNegocio> tiposDeNegocio = enumServicio.obtenerTiposDeNegocio();
        return ResponseEntity.ok().body(new MensajeDTO<>(false,tiposDeNegocio));
    }

    @GetMapping("/ciudades")
    public ResponseEntity<MensajeDTO<List<Ciudades>>> obtenerCiudades() throws Exception {
        List<Ciudades> ciudades = enumServicio.obtenerCiudades();
        return ResponseEntity.ok().body(new MensajeDTO<>(false,ciudades));
    }
}
