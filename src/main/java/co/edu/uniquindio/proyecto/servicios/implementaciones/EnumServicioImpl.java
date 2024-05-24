package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.model.Enum.Ciudades;
import co.edu.uniquindio.proyecto.model.Enum.TipoNegocio;
import co.edu.uniquindio.proyecto.servicios.interfaces.EnumServicio;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EnumServicioImpl implements EnumServicio {

    @Override
    public List<TipoNegocio> obtenerTiposDeNegocio() throws Exception {
        return Arrays.asList(TipoNegocio.values());

    }

    @Override
    public List<Ciudades> obtenerCiudades() throws Exception {
        return Arrays.asList(Ciudades.values());
    }
}
