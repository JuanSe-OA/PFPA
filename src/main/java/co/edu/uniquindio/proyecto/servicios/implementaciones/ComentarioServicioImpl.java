package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.EmailDTO;
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
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentariosRepo comentariosRepo;
    private final UsuariosRepo usuarioRepo;
    private final NegociosRepo negociosRepo;
    private final EmailServicio emailServicio;

    public ComentarioServicioImpl(ComentariosRepo comentariosRepo, UsuariosRepo usuarioRepo, NegociosRepo negociosRepo, EmailServicioImpl emailServicio) {
        this.comentariosRepo = comentariosRepo;
        this.usuarioRepo = usuarioRepo;
        this.negociosRepo = negociosRepo;
        this.emailServicio = emailServicio;
    }

    @Override
    public String crearComentario(CrearComentarioDTO crearComentarioDTO) throws Exception {
        if (crearComentarioDTO.mensaje().isEmpty() || crearComentarioDTO.codigoUsuario().isEmpty() || crearComentarioDTO.codigoNegocio().isEmpty()) {
            throw new Exception("No fue posible crear comentario, intentalo de nuevo");
        }
        Comentario comentario = new Comentario();
        comentario.setMensaje(crearComentarioDTO.mensaje());
        comentario.setFecha(LocalDate.now());
        comentario.setCalificacion(crearComentarioDTO.calificacion());
        comentario.setCodigoUsuario(crearComentarioDTO.codigoUsuario());
        comentario.setCodigoNegocio(crearComentarioDTO.codigoNegocio());
        Optional<Negocio> negocioOptional = negociosRepo.findById(comentario.getCodigoNegocio());
        if (negocioOptional.isPresent()) {
            Negocio negocio = negocioOptional.get();
            Optional<Usuario> usuarioOptional = usuarioRepo.findById(negocio.getCodigoUsuario());
            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                if (!usuario.getEmail().isEmpty()) {
                    String asunto = "Comentario nuevo";
                    String cuerpo = "Han comentado tu publicación en el negocio " + negocio.getNombre();
                    String correo = usuario.getEmail();
                    emailServicio.enviarCorreo(new EmailDTO(asunto, cuerpo, correo));
                    Optional<Usuario> usuarioOptional1 = usuarioRepo.findById(comentario.getCodigoUsuario());
                    if(usuarioOptional1.isPresent()){
                        Usuario usuario1 = usuarioOptional1.get();
                        if(!usuario1.getEmail().isEmpty()){
                            String asunto2 = "Comentario nuevo";
                            String cuerpo2= "Usted ha realizado un comentario en el negocio" + negocio.getNombre();
                            String correo2 = usuario1.getEmail();
                            emailServicio.enviarCorreo(new EmailDTO(asunto2,cuerpo2,correo2));
                        }
                    }else {
                        throw new Exception("No existe un usuario con ese email");
                    }
                } else {
                    throw new Exception("Email no encontrado");
                }
            } else {
                throw new Exception("Usuario no encontrado");
            }
        } else {
            throw new Exception("Negocio no encontrado");
        }

        Comentario comentarioGuardado=comentariosRepo.save(comentario);

        return comentarioGuardado.getCodigo();
    }

    @Override
    public void responderComentario(ResponderComentarioDTO responderComentarioDTO)throws Exception {
        Optional<Comentario> optionalComentario = comentariosRepo.findById(responderComentarioDTO.codigo());

        if (optionalComentario.isEmpty()) {
            throw new Exception("El comentario no ha sido encontrado");
        }
        Comentario comentario = optionalComentario.get();
        Optional<Usuario> usuarioOptional = usuarioRepo.findById(responderComentarioDTO.codigoUsuario());
        if (usuarioOptional.isEmpty()) {
            throw new Exception("El usuario no se ha encontrado");
        } else {
            Usuario usuario = usuarioOptional.get();
            if (!usuario.getEmail().isEmpty()) {
                Optional<Negocio> negocioOptional = negociosRepo.findById(comentario.getCodigoNegocio());
                if (negocioOptional.isPresent()) {
                    Negocio negocio = negocioOptional.get();
                    String asunto2 = "Comentario nuevo";
                    String cuerpo2= "Usted ha realizado un comentario en el negocio" + negocio.getNombre();
                    String correo2 = usuario.getEmail();
                    emailServicio.enviarCorreo(new EmailDTO(asunto2,cuerpo2,correo2));
                    Optional<Usuario> usuarioOptional1 = usuarioRepo.findById(negocio.getCodigoUsuario());
                    if (usuarioOptional1.isPresent()) {
                        Usuario usuario1 = usuarioOptional1.get();
                        if (!usuario1.getEmail().isEmpty()) {
                            String asunto = "Comentario nuevo";
                            String cuerpo = "Han realizado un comentario en el negocio" + negocio.getNombre();
                            String correo = usuario1.getEmail();
                            emailServicio.enviarCorreo(new EmailDTO(asunto,cuerpo,correo));
                        }
                        else {
                            throw new Exception("No existe un usuario con ese email");
                        }
                    }else {
                        throw new Exception("No existe el dueño del negocio");
                    }
                }else {
                    throw new Exception("El negocio no se ha encontrado");
                }
            }else {
                throw new Exception("Debes tener una cuenta para estar registrado");
            }
        }
        comentario.setRespuesta(responderComentarioDTO.respuesta());
        comentariosRepo.save(comentario);
    }

    @Override
    public List<ItemComentarioDTO> listarComentariosNegocio(String codigoNegocio) throws Exception {
        List<Comentario> comentariosNegocio = comentariosRepo.findBycodigoNegocio(codigoNegocio);
        List<ItemComentarioDTO> itemsComentariosNegocio = new ArrayList<>();
        for(Comentario c: comentariosNegocio){
            Optional<Usuario> optionalUsuario = usuarioRepo.findById(c.getCodigoUsuario());
            if(optionalUsuario.isEmpty()){
               throw  new Exception("El usuario no existe");
            }
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
        List<Comentario> comentarios = comentariosRepo.findCalificacionByCodigoNegocio(codigoNegocio);
        List<Double> calificacionesNegocio= comentarios.stream().map(Comentario::getCalificacion).collect(Collectors.toList());
        return calificacionesNegocio.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public int calcularNumeroComentarios(String codigoNegocio){
        List<Comentario> calificacionesNegocio = comentariosRepo.findCalificacionByCodigoNegocio(codigoNegocio);
        return calificacionesNegocio.size();
    }
}
