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

    void eliminarNegocio(String codigoNegocio)throws Exception;

    List<DetalleNegocioDTO> buscarNegocios(String busqueda);

    void filtrarPorEstado(EstadoRegistro estadoRegistro);

    DetalleNegocioDTO obtenerDetalleNegocio(String codigoNegocio)throws Exception;

    List<DetalleNegocioDTO> listarNegociosPropietario(String codigoUsuario);

    void cambiarEstado(String codigoNegocio, EstadoRegistro estadoRegistro)throws Exception;

    void registrarRevision();
}
