package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.SesionDto;
import org.apache.el.parser.Token;

public interface AutenticacionServicio {
    public TokenDTO iniciarSesionCliente(SesionDto sesionDto)throws Exception;
}
