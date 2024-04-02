package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.dto.ItemComentarioDTO;
import co.edu.uniquindio.proyecto.model.Documents.Comentario;
import co.edu.uniquindio.proyecto.repositorios.ComentariosRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ComentarioServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentariosRepo comentariosRepo;

    public ComentarioServicioImpl(ComentariosRepo comentariosRepo) {
        this.comentariosRepo = comentariosRepo;
    }

    @Override
    public String crearComentario(CrearComentarioDTO crearComentarioDTO) {
        Comentario comentario= new Comentario();

        comentario.setMensaje(crearComentarioDTO.mensaje());
        comentario.setFecha(crearComentarioDTO.fecha());
        comentario.setCalificacion(crearComentarioDTO.calificacion());
        comentario.setCodigoUsuario(crearComentarioDTO.codigoUsuario());
        comentario.setCodigoNegocio(crearComentarioDTO.codigoNegocio());

        Comentario comentarioGuardado=comentariosRepo.save(comentario);

        return comentarioGuardado.getCodigo();
    }

    @Override
    public void responderComentario(String codigoComentario, String mensaje)throws Exception {
        Optional<Comentario> optionalComentario = comentariosRepo.findById(codigoComentario);

        if(optionalComentario.isEmpty()){
            throw new Exception("El comentario no ha sido encontrado");
        }

        Comentario comentario= optionalComentario.get();
        comentario.setRespuesta(mensaje);

        comentariosRepo.save(comentario);
    }

    @Override
    public List<ItemComentarioDTO> listarComentariosNegocio(String codigoNegocio) {
        List<Comentario> comentariosNegocio = comentariosRepo.findByNegocioId(codigoNegocio);

        List<ItemComentarioDTO> itemsComentariosNegocio = new ArrayList<>();
        for(Comentario c: comentariosNegocio){
            itemsComentariosNegocio.add(new ItemComentarioDTO(
                            c.getCodigo(),
                            c.getMensaje(),
                            c.getRespuesta(),
                            c.getCalificacion(),
                            c.getFecha()));
        }
        return itemsComentariosNegocio;
    }

    @Override
    public double calcularPromedioCalificaciones(String codigoNegocio) {
        List<Double> calificacionesNegocio = comentariosRepo.findCalificacionByCodigoNegocio(codigoNegocio);
        return calificacionesNegocio.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }
}
