package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.negociodtos.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.dto.negociodtos.ItemComentarioDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.ComentarioServicio;

import java.util.List;

public class ComentarioServicioImpl implements ComentarioServicio {
    @Override
    public String crearComentario(CrearComentarioDTO crearComentarioDTO) {
        return null;
    }

    @Override
    public void responderComentario(String codigoComentario, String mensaje) {

    }

    @Override
    public List<ItemComentarioDTO> listarComentariosNegocio(String codigoNegocio) {
        return null;
    }

    @Override
    public double calcularPromedioCalificaciones(List<Double> calificacionesComentarios) {
        return 0;
    }
}
