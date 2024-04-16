package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.dto.moderadordtos.SesionModeradorDTO;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.SesionDto;
import org.apache.el.parser.Token;

public interface AutenticacionServicio {
    public TokenDTO iniciarSesionUsuario(SesionDto sesionDto)throws Exception;
    public TokenDTO iniciarSesionModerador(SesionModeradorDTO sesionModeradorDTO)throws Exception;
    public TokenDTO sesionUsuarioNoAutenticado();
}
