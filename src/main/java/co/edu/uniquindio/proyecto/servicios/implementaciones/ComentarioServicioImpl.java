package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.comentariodtos.CrearComentarioDTO;
import co.edu.uniquindio.proyecto.dto.comentariodtos.ItemComentarioDTO;
import co.edu.uniquindio.proyecto.dto.comentariodtos.ResponderComentarioDTO;
import co.edu.uniquindio.proyecto.model.Documents.Comentario;
import co.edu.uniquindio.proyecto.model.Documents.Negocio;
import co.edu.uniquindio.proyecto.model.Documents.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ComentariosRepo;
import co.edu.uniquindio.proyecto.repositorios.NegociosRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuariosRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ComentarioServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentariosRepo comentariosRepo;
    private final UsuariosRepo usuarioRepo;

    public ComentarioServicioImpl(ComentariosRepo comentariosRepo, UsuariosRepo usuarioRepo) {
        this.comentariosRepo = comentariosRepo;
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public String crearComentario(CrearComentarioDTO crearComentarioDTO) throws Exception{
        Comentario comentario= new Comentario();

        comentario.setMensaje(crearComentarioDTO.mensaje());
        comentario.setFecha(LocalDate.now());
        comentario.setCalificacion(crearComentarioDTO.calificacion());
        comentario.setCodigoUsuario(crearComentarioDTO.codigoUsuario());
        comentario.setCodigoNegocio(crearComentarioDTO.codigoNegocio());

        Comentario comentarioGuardado=comentariosRepo.save(comentario);

        return comentarioGuardado.getCodigo();
    }

    @Override
    public void responderComentario(ResponderComentarioDTO responderComentarioDTO)throws Exception {
        Optional<Comentario> optionalComentario = comentariosRepo.findById(responderComentarioDTO.codigo());

        if(optionalComentario.isEmpty()){
            throw new Exception("El comentario no ha sido encontrado");
        }

        Comentario comentario= optionalComentario.get();
        comentario.setRespuesta(responderComentarioDTO.respuesta());

        comentariosRepo.save(comentario);
    }

    @Override
    public List<ItemComentarioDTO> listarComentariosNegocio(String codigoNegocio) {
        List<Comentario> comentariosNegocio = comentariosRepo.findBycodigoNegocio(codigoNegocio);
        List<ItemComentarioDTO> itemsComentariosNegocio = new ArrayList<>();
        for(Comentario c: comentariosNegocio){
            Optional<Usuario> optionalUsuario = usuarioRepo.findById(c.getCodigoUsuario());
            Usuario u = optionalUsuario.get();
            ItemComentarioDTO itemComentarioDTO = new ItemComentarioDTO(
                    u.getFotoPerfil(),
                    u.getNombreUsuario(),
                    c.getCodigo(),
                    c.getMensaje(),
                    c.getRespuesta(),
                    c.getCalificacion(),
                    c.getFecha());
            itemsComentariosNegocio.add(itemComentarioDTO);
        }
        return itemsComentariosNegocio;
    }

    @Override
    public double calcularPromedioCalificaciones(String codigoNegocio) {
        List<Double> calificacionesNegocio = comentariosRepo.findCalificacionByCodigoNegocio(codigoNegocio);
        return calificacionesNegocio.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public int calcularNumeroComentarios(String codigoNegocio){
        List<Double> calificacionesNegocio = comentariosRepo.findCalificacionByCodigoNegocio(codigoNegocio);
        return calificacionesNegocio.size();
    }
}
