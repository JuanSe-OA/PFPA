package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.dto.ItemComentarioDTO;

import java.util.List;

public interface ComentarioServicio {
    String crearComentario(CrearComentarioDTO crearComentarioDTO);

    void responderComentario(String codigoComentario, String mensaje)throws Exception;

    List<ItemComentarioDTO> listarComentariosNegocio(String codigoNegocio);

    double calcularPromedioCalificaciones(String codigoNegocio);
}
