package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.model.Enum.Ciudades;
import co.edu.uniquindio.proyecto.model.Enum.TipoNegocio;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EnumServicio {
    List<TipoNegocio> obtenerTiposDeNegocio() throws Exception;
    List<Ciudades> obtenerCiudades() throws Exception ;
}
