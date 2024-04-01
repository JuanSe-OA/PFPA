package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.usuariosdtos.CambioPasswordDto;

public interface ModeradorServicio extends CuentaServicio {
    void actualizarPassword(CambioPasswordDto cambioPasswordDto)throws Exception;
    void aceptarNegocio()throws Exception;

    void revisarNegocio()throws Exception;
    void revisarComentarios()throws Exception;
}
