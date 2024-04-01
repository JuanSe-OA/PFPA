package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.negociodtos.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.dto.negociodtos.ItemComentarioDTO;

import java.util.List;

public interface ComentarioServicio {
    String crearComentario(CrearComentarioDTO crearComentarioDTO);

    void responderComentario(String codigoComentario, String mensaje);

    List<ItemComentarioDTO> listarComentariosNegocio(String codigoNegocio);

    double calcularPromedioCalificaciones(List<Double> calificacionesComentarios);
}
