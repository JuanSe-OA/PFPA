package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.negociodtos.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.dto.negociodtos.ItemComentarioDTO;
import co.edu.uniquindio.proyecto.model.Documents.Comentario;
import co.edu.uniquindio.proyecto.repositorios.ComentariosRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ComentarioServicio;

import java.util.List;

public class ComentarioServicioImpl implements ComentarioServicio {
    private ComentariosRepo comentariosRepo;

    public ComentarioServicioImpl(ComentariosRepo comentariosRepo) {
        this.comentariosRepo = comentariosRepo;
    }

    @Override
    public String crearComentario(CrearComentarioDTO crearComentarioDTO) {
        Comentario comentario = new Comentario();
        comentario.setCalificacion(crearComentarioDTO.calificacion());
        comentario.setFecha(crearComentarioDTO.fecha());
        comentario.setMensaje(crearComentarioDTO.mensaje());
        comentario.setCodigoUsuario(crearComentarioDTO.codigoUsuario());
        comentario.setCodigoNegocio(crearComentarioDTO.codigoNegocio());
        comentario.setCodigo(crearComentarioDTO.id());
        comentariosRepo.save(comentario);

        return comentario.getCodigo();
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
