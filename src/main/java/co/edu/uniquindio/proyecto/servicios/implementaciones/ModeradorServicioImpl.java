package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.dto.moderadordtos.CambiarPasswordModeradorDTO;
import co.edu.uniquindio.proyecto.dto.moderadordtos.RevisarComentariosDTO;
import co.edu.uniquindio.proyecto.dto.moderadordtos.RevisionesModeradorDTO;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.CambioPasswordDto;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        moderador.setPassword(cambiarPasswordModeradorDTO.passwordNueva());

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
        revision.setFecha(revisionesModeradorDTO.fecha());
        negocio.getHistorialRevisiones().add(revision);
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
        revision.setEstado(EstadoRevision.PENDIENTE);
        revision.setFecha(revisionesModeradorDTO.fecha());
        negocio.getHistorialRevisiones().add(revision);

    }

    @Override
    public void rechazarNegocio(RevisionesModeradorDTO revisionesModeradorDTO) throws Exception {
        Optional<Negocio> optionalNegocio = negociosRepo.findById(revisionesModeradorDTO.codigoNegocio());
        if (optionalNegocio.isEmpty()) {
            throw new Exception("El negocio no se ecnuentra registrado");
        }
        Negocio negocio = optionalNegocio.get();
        Revision revision = new Revision();
        revision.setDescripcion(revisionesModeradorDTO.descripcion());
        revision.setCodigoModerador(revisionesModeradorDTO.codigoModerador());
        revision.setCodigoEntidad(revisionesModeradorDTO.codigoNegocio());
        revision.setEstado(EstadoRevision.RECHAZADO);
        revision.setFecha(revisionesModeradorDTO.fecha());
        negocio.getHistorialRevisiones().add(revision);
    }

    @Override
    public List<RevisarComentariosDTO> revisarComentarios(String codigo) throws Exception {
        if (codigo.isEmpty()) {
            throw new Exception("El negocio no se ecnuentra registrado");
        }
        List<Comentario> comentarioList = comentariosRepo.findByNegocioId(codigo);
        List<RevisarComentariosDTO> revisarComentariosDTOS = new ArrayList<>();
        for (Comentario c : comentarioList) {
            Optional<Usuario> usuarioOptional = usuariosRepo.findById(c.getCodigoUsuario());
            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                revisarComentariosDTOS.add(new RevisarComentariosDTO(c.getMensaje(), c.getCodigoUsuario(), usuario.getNombreUsuario(), usuario.getEmail(),
                        c.getFecha()));
            }else {
                throw new Exception("El usuario no existe");
            }
        }
        return revisarComentariosDTOS;
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
}
