package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.dto.moderadordtos.*;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.CambioPasswordDto;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.MostrarPerfilDTO;
import co.edu.uniquindio.proyecto.model.Documents.Comentario;
import co.edu.uniquindio.proyecto.model.Documents.Moderador;
import co.edu.uniquindio.proyecto.model.Documents.Negocio;
import co.edu.uniquindio.proyecto.model.Documents.Usuario;
import co.edu.uniquindio.proyecto.model.Entidades.Revision;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRevision;
import co.edu.uniquindio.proyecto.repositorios.ComentariosRepo;
import co.edu.uniquindio.proyecto.repositorios.ModeradorRepo;
import co.edu.uniquindio.proyecto.repositorios.NegociosRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuariosRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.ModeradorServicio;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class ModeradorServicioImpl implements ModeradorServicio {
    private final ModeradorRepo moderadorRepo;
    private final NegociosRepo negociosRepo;
    private final ComentariosRepo comentariosRepo;
    private final UsuariosRepo usuariosRepo;
    private final EmailServicio emailServicio;

    public ModeradorServicioImpl(ModeradorRepo moderadorRepo, NegociosRepo negociosRepo, ComentariosRepo comentariosRepo, UsuariosRepo usuariosRepo, EmailServicioImpl emailServicio) {
        this.moderadorRepo = moderadorRepo;
        this.negociosRepo = negociosRepo;
        this.comentariosRepo = comentariosRepo;
        this.usuariosRepo = usuariosRepo;
        this.emailServicio = emailServicio;
    }

    @Override
    public void actualizarPassword(CambiarPasswordModeradorDTO cambiarPasswordModeradorDTO) throws Exception {
        Optional<Moderador>optionalModerador = moderadorRepo.findById(cambiarPasswordModeradorDTO.id());
        if(optionalModerador.isEmpty()){
            throw new Exception("No fue posible encontrar su perfil en el sistema");
        }
        Moderador moderador = optionalModerador.get();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        moderador.setPassword(passwordEncoder.encode(cambiarPasswordModeradorDTO.passwordNueva()));
        moderadorRepo.save(moderador);
    }

    @Override
    public void aceptarNegocio(RevisionesModeradorDTO revisionesModeradorDTO) throws Exception {
        Optional<Negocio> optionalNegocio = negociosRepo.findById(revisionesModeradorDTO.codigoNegocio());
        if (optionalNegocio.isEmpty()) {
            throw new Exception("El negocio no se ecnuentra registrado");
        }
        Negocio negocio = optionalNegocio.get();
        Revision revision = new Revision();
        revision.setDescripcion(revisionesModeradorDTO.descripcion());
        revision.setCodigoModerador(revisionesModeradorDTO.codigoModerador());
        revision.setCodigoEntidad(revisionesModeradorDTO.codigoNegocio());
        revision.setEstado(EstadoRevision.APROVADO);
        negocio.setEstadoRegistro(EstadoRegistro.ACTIVO);
        revision.setFecha(revisionesModeradorDTO.fecha());
        negocio.getHistorialRevisiones().add(revision);
        Optional<Usuario> optionalUsuario = usuariosRepo.findById(negocio.getCodigoUsuario());
        if (optionalUsuario.isEmpty()) {
            throw new Exception("El usuario no se ecnuentra registrado");
        }
        Usuario usuario = optionalUsuario.get();
        String asunto = "Regsitro aprobado";
        String cuerpo = "Bienvenido, su negocio ya se encuentra registrado correctamente <3";
        String correo = usuario.getEmail();
        emailServicio.enviarCorreo(new EmailDTO(asunto,cuerpo,correo));
        negociosRepo.save(negocio);
    }

    @Override
    public void revisarNegocio(RevisionesModeradorDTO revisionesModeradorDTO) throws Exception {
        Optional<Negocio> optionalNegocio = negociosRepo.findById(revisionesModeradorDTO.codigoNegocio());
        if (optionalNegocio.isEmpty()) {
            throw new Exception("El negocio no se ecnuentra registrado");
        }

        Negocio negocio = optionalNegocio.get();
        Revision revision = new Revision();
        revision.setDescripcion(revisionesModeradorDTO.descripcion());
        revision.setCodigoModerador(revisionesModeradorDTO.codigoModerador());
        revision.setCodigoEntidad(revisionesModeradorDTO.codigoNegocio());
        revision.setFecha(revisionesModeradorDTO.fecha());
        revision.setEstado(EstadoRevision.PENDIENTE);
        negocio.setEstadoRegistro(EstadoRegistro.INACTIVO);
        negocio.getHistorialRevisiones().add(revision);
        Optional<Usuario> optionalUsuario = usuariosRepo.findById(negocio.getCodigoUsuario());
        if (optionalUsuario.isEmpty()) {
            throw new Exception("El usuario no se ecnuentra registrado");
        }
        Usuario usuario = optionalUsuario.get();
        String asunto = "Regsitro pendiente";
        String cuerpo = "Revisión, su negocio se encuentra en revisión, por favor revise lo siguiente: " +revision.getDescripcion();
        String correo = usuario.getEmail();
        emailServicio.enviarCorreo(new EmailDTO(asunto,cuerpo,correo));
        negociosRepo.save(negocio);

    }

    @Override
    public void rechazarNegocio(RevisionesModeradorDTO revisionesModeradorDTO) throws Exception {
        Optional<Negocio> optionalNegocio = negociosRepo.findById(revisionesModeradorDTO.codigoNegocio());
        if (optionalNegocio.isEmpty()) {
            throw new Exception("El negocio no se enuentra registrado");
        }
        Negocio negocio = optionalNegocio.get();
        Revision revision = new Revision();
        revision.setDescripcion(revisionesModeradorDTO.descripcion());
        revision.setCodigoModerador(revisionesModeradorDTO.codigoModerador());
        revision.setCodigoEntidad(revisionesModeradorDTO.codigoNegocio());
        revision.setEstado(EstadoRevision.RECHAZADO);
        revision.setFecha(revisionesModeradorDTO.fecha());
        negocio.setEstadoRegistro(EstadoRegistro.INACTIVO);
        negocio.getHistorialRevisiones().add(revision);
        Optional<Usuario> optionalUsuario = usuariosRepo.findById(negocio.getCodigoUsuario());
        if (optionalUsuario.isEmpty()) {
            throw new Exception("El usuario no se ecnuentra registrado");
        }
        Usuario usuario = optionalUsuario.get();
        String asunto = "Regsitro rechazada";
        String cuerpo = "Rechazo, su negocio ha sido rechazado, crear un nuevo negocio. Su negocio ha sido rechazado por lo siguiente: " +revision.getDescripcion();
        String correo = usuario.getEmail();
        emailServicio.enviarCorreo(new EmailDTO(asunto,cuerpo,correo));
        negociosRepo.save(negocio);
    }

    @Override
    public List<RevisarComentariosDTO> revisarComentarios(String codigo) throws Exception {
        if (codigo.isEmpty()) {
            throw new Exception("El negocio no se ecnuentra registrado");
        }
        List<Comentario> comentarioList = comentariosRepo.findBycodigoNegocio(codigo);
        List<RevisarComentariosDTO> revisarComentariosDTOSList = new ArrayList<>();
        for (Comentario c : comentarioList) {
            Optional<Usuario> usuarioOptional = usuariosRepo.findById(c.getCodigoUsuario());
            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                revisarComentariosDTOSList.add(new RevisarComentariosDTO(c.getMensaje(), c.getCodigoUsuario(), usuario.getNombreUsuario(), usuario.getEmail(),
                        c.getFecha()));
            }else {
                throw new Exception("El usuario no existe");
            }
        }
        return revisarComentariosDTOSList;
    }

    @Override
    public void bloquearUsuario(String codigo) throws Exception {
        Optional<Usuario>usuarioOptional = usuariosRepo.findById(codigo);

        if(usuarioOptional.isEmpty()){
            throw new Exception("El usuario no se encuentra registrado en la plataforma");
        }
        Usuario usuario = usuarioOptional.get();
        usuario.setEstadoCuenta(EstadoRegistro.INACTIVO);
        String asunto = "Bloqueo de cuenta";
        String cuerpo = "Usted ha incumplido con las normas de la página";
        String correo = usuario.getEmail();
        emailServicio.enviarCorreo(new EmailDTO(asunto,cuerpo,correo));
    }

    @Override
    public List<ItemNegociosRevisionDTO> listarRevisiones(EstadoRevision estadoRevision) throws Exception {
        List<Negocio>negocios = negociosRepo.findByEstadoRevision(estadoRevision);
        List<ItemNegociosRevisionDTO> itemNegociosRevisionDTOSList = new ArrayList<>();
        for (Negocio negocio : negocios) {
            itemNegociosRevisionDTOSList.add(new ItemNegociosRevisionDTO(negocio.getNombre(), negocio.getDescripcion(),
                    negocio.getTelefonos(), negocio.getDireccion(), negocio.getHorarios(),
                    negocio.getTipoNegocio(),negocio.getImagenes()));
        }
        return itemNegociosRevisionDTOSList;
    }

    @Override
    public ObtenerModeradorDTO obtenerModerador(String codigo) throws Exception {
        Optional<Moderador>moderadorOptional = moderadorRepo.findById(codigo);
        if(moderadorOptional.isEmpty()){
            throw new Exception("El usuario no se encuentra registrado");
        }
        Moderador moderador = moderadorOptional.get();
        if(!moderador.getEmail().isEmpty()){
            return new ObtenerModeradorDTO(codigo,moderador.getEmail(),moderador.getPassword());
        }
        else {
            throw new Exception("El usuario no tiene un email asociado");
        }

    }
}
