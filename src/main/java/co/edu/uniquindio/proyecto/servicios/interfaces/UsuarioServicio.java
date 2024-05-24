package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.usuariosdtos.*;

import java.util.List;

public interface UsuarioServicio extends CuentaServicio {

    String registrarse(RegistroClienteDto registroClienteDTO) throws Exception;

    void editarPerfil(ActualizarClienteDto actualizarClienteDTO) throws Exception;

    void actualizarPassword(CambioPasswordDto cambioPasswordDto) throws Exception;

    MostrarPerfilDTO mostrarPerfil(String codigo) throws Exception;

    void eliminarPerfil(String codigo) throws Exception;

    List<ItemUsuarioDTO> listarClientes() throws Exception;

    String agregarNegocioFavoritos(AgregarNegocioFavoritoDTO agregarNegocioFavoritoDTO)throws Exception;
}

