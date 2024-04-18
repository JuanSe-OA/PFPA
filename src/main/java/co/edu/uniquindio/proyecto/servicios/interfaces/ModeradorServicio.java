package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.moderadordtos.*;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.CambioPasswordDto;
import co.edu.uniquindio.proyecto.model.Documents.Comentario;
import co.edu.uniquindio.proyecto.model.Entidades.Revision;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRevision;

import java.util.List;

public interface ModeradorServicio extends CuentaServicio {
    void actualizarPassword(CambiarPasswordModeradorDTO cambioPasswordDto)throws Exception;
    void aceptarNegocio(RevisionesModeradorDTO revisionesModeradorDTO)throws Exception;

    void revisarNegocio(RevisionesModeradorDTO revisionesModeradorDTO)throws Exception;
    void rechazarNegocio(RevisionesModeradorDTO revisionesModeradorDTO)throws Exception;

    List<RevisarComentariosDTO> revisarComentarios(String codigo)throws Exception;
    void bloquearUsuario(String codigo)throws Exception;
    List<ItemNegociosRevisionDTO>listarRevisiones(EstadoRegistro estadoRegistro)throws Exception;
    ItemNegociosRevisionDTO revisarNegocio(String codigo)throws Exception;
}
