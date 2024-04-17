package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.negociodtos.*;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;

import java.util.List;

public interface NegocioServicio {
    String crearNegocio(CrearNegocioDTO crearNegocioDTO) throws Exception;

    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception;

    void eliminarNegocio(String codigoNegocio)throws Exception;

    List<ItemListarNegociosDTO> buscarNegociosPorNombre(String busqueda);

    void filtrarPorEstado(EstadoRegistro estadoRegistro);

    DetalleNegocioDTO obtenerDetalleNegocio(String codigoNegocio)throws Exception;

    List<ItemListarNegociosDTO>buscarNegociosDistancia(ObtenerDistanciaDTO obtenerDistanciaDTO);
    DetalleNegocioPropioDTO obtenerDetalleNegocioPropio (String codigoNegocio) throws Exception;

    ItemNegocioInfoDTO obtenerInformacionNegocio(String codigoNegocio) throws Exception;

    List<ItemListarNegociosDTO> listarNegociosPropietario(String codigoUsuario);

    List<ItemListarNegociosDTO> listarNegociosFavoritos(String codigoUsuario)throws Exception;

    void cambiarEstado(String codigoNegocio, EstadoRegistro estadoRegistro)throws Exception;
}
