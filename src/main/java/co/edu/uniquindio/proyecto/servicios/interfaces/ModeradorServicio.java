package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.moderadordtos.RevisarComentariosDTO;
import co.edu.uniquindio.proyecto.dto.moderadordtos.RevisionesModeradorDTO;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.CambioPasswordDto;
import co.edu.uniquindio.proyecto.model.Documents.Comentario;
import co.edu.uniquindio.proyecto.model.Entidades.Revision;

import java.util.List;

public interface ModeradorServicio extends CuentaServicio {
    void actualizarPassword(CambioPasswordDto cambioPasswordDto)throws Exception;
    void aceptarNegocio(RevisionesModeradorDTO revisionesModeradorDTO)throws Exception;

    void revisarNegocio(RevisionesModeradorDTO revisionesModeradorDTO)throws Exception;
    void rechazarNegocio(RevisionesModeradorDTO revisionesModeradorDTO)throws Exception;

    List<RevisarComentariosDTO> revisarComentarios(RevisarComentariosDTO revisarComentariosDTO)throws Exception;
}
