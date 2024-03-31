package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ActualizarNegocioDTO;
import co.edu.uniquindio.proyecto.dto.CrearNegocioDTO;
import co.edu.uniquindio.proyecto.dto.DetalleNegocioDTO;
import co.edu.uniquindio.proyecto.model.Documents.Negocio;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;

import java.util.List;

public interface NegocioServicio {
    String crearNegocio(CrearNegocioDTO crearNegocioDTO) throws Exception;

    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception;

    void eliminarNegocio(String codigoNegocio);

    List<DetalleNegocioDTO> buscarNegocios(String nombreNegocio);

    void filtrarPorEstado(EstadoRegistro estadoRegistro);

    List<DetalleNegocioDTO> listarNegociosPropietario(String codigoUsuario);

    void cambiarEstado(String codigonegocio, EstadoRegistro estadoRegistro);

    void registrarRevision();
}
