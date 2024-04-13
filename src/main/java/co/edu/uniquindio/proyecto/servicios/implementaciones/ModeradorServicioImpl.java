package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.moderadordtos.CambiarPasswordModeradorDTO;
import co.edu.uniquindio.proyecto.dto.moderadordtos.RevisarComentariosDTO;
import co.edu.uniquindio.proyecto.dto.moderadordtos.RevisionesModeradorDTO;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.CambioPasswordDto;
import co.edu.uniquindio.proyecto.model.Documents.Comentario;
import co.edu.uniquindio.proyecto.model.Documents.Moderador;
import co.edu.uniquindio.proyecto.model.Documents.Negocio;
import co.edu.uniquindio.proyecto.model.Entidades.Revision;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRevision;
import co.edu.uniquindio.proyecto.repositorios.ComentariosRepo;
import co.edu.uniquindio.proyecto.repositorios.ModeradorRepo;
import co.edu.uniquindio.proyecto.repositorios.NegociosRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ModeradorServicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModeradorServicioImpl implements ModeradorServicio {
    private final ModeradorRepo moderadorRepo;
    private final NegociosRepo negociosRepo;
    private final ComentariosRepo comentariosRepo;

    public ModeradorServicioImpl(ModeradorRepo moderadorRepo, NegociosRepo negociosRepo, ComentariosRepo comentariosRepo) {
        this.moderadorRepo = moderadorRepo;
        this.negociosRepo = negociosRepo;
        this.comentariosRepo = comentariosRepo;
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
    public List<RevisarComentariosDTO> revisarComentarios(RevisarComentariosDTO revisarComentariosDTO) throws Exception {
        if (revisarComentariosDTO.codigoNegocio().isEmpty()) {
            throw new Exception("El negocio no se ecnuentra registrado");
        }
        List<Comentario> comentarioList = comentariosRepo.findByCodigoNegocio(revisarComentariosDTO.codigoNegocio());
        List<RevisarComentariosDTO> revisarComentariosDTOS = new ArrayList<>();
        for (Comentario c : comentarioList) {
            revisarComentariosDTOS.add(new RevisarComentariosDTO(revisarComentariosDTO.mensaje(), revisarComentariosDTO.respuesta(), revisarComentariosDTO.codigoUsuario(), revisarComentariosDTO.codigoNegocio(), revisarComentariosDTO.calificacion(),
                    revisarComentariosDTO.fecha()));
        }
        return revisarComentariosDTOS;
    }
}
