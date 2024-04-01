package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.*;

import java.util.List;

public interface UsuarioServicio extends CuentaServicio{

    String registrarse(RegistroClienteDto registroClienteDTO)throws Exception;

    void editarPerfil(ActualizarClienteDto actualizarClienteDTO)throws Exception;
    void actualizarPassword(CambioPasswordDto cambioPasswordDto)throws Exception;
    void mostrarPerfil(MostrarPerfilDTO mostrarPerfilDTO)throws Exception;
}
