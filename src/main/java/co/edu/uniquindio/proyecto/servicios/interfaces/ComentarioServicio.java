package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.comentariodtos.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.dto.comentariodtos.ItemComentarioDTO;
import co.edu.uniquindio.proyecto.dto.comentariodtos.ResponderComentarioDTO;

import java.util.List;

public interface ComentarioServicio {
    String crearComentario(CrearComentarioDTO crearComentarioDTO)throws Exception;

    void responderComentario(ResponderComentarioDTO responderComentarioDTO)throws Exception;

    List<ItemComentarioDTO> listarComentariosNegocio(String codigoNegocio)throws Exception;

    double calcularPromedioCalificaciones(String codigoNegocio);
}
