package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ActualizarNegocioDTO;
import co.edu.uniquindio.proyecto.dto.CrearNegocioDTO;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;

public interface NegocioServicio {
    String crearNegocio(CrearNegocioDTO crearNegocioDTO) throws Exception;

    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception;

    void eliminarNegocio(String codigoNegocio);

    void buscarNegocios(String nombreNegocio);

    void filtrarPorEstado(EstadoRegistro estadoRegistro);

    void listarNegociosPropietario(String codigoUsuario);

    void cambiarEstado(String codigonegocio, EstadoRegistro estadoRegistro);

    void registrarRevision();
}
