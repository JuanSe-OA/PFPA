package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.ActualizarClienteDto;
import co.edu.uniquindio.proyecto.dto.CambioPasswordDto;
import co.edu.uniquindio.proyecto.dto.ItemUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.RegistroClienteDto;

import java.util.List;

public interface UsuarioServicio extends CuentaServicio{

    String registrarse(RegistroClienteDto registroClienteDTO)throws Exception;

    void editarPerfil(ActualizarClienteDto actualizarClienteDTO)throws Exception;
    void actualizarPassword(CambioPasswordDto cambioPasswordDto)throws Exception;
}
