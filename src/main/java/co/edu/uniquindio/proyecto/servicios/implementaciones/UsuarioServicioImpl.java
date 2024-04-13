package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.usuariosdtos.ActualizarClienteDto;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.CambioPasswordDto;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.MostrarPerfilDTO;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.RegistroClienteDto;
import co.edu.uniquindio.proyecto.model.Documents.Usuario;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;
import co.edu.uniquindio.proyecto.repositorios.UsuariosRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.UsuarioServicio;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UsuarioServicioImpl implements UsuarioServicio {
    private final UsuariosRepo usuariosRepo;
    public UsuarioServicioImpl(UsuariosRepo usuariosRepo){
        this.usuariosRepo= usuariosRepo;
    }
    @Override
    public String registrarse(RegistroClienteDto registroClienteDTO) throws Exception {

        if(existeEmail(registroClienteDTO.email())){
            throw new Exception("Usted ya se encuentra registrado en nuestra plataforma");
        }else {
            Usuario usuario = new Usuario();
            usuario.setNombre(registroClienteDTO.nombre());
            usuario.setNombreUsuario(registroClienteDTO.nickname());
            usuario.setCiudad(registroClienteDTO.ciudadResidencia());
            usuario.setEmail(registroClienteDTO.email());
            usuario.setPassword(registroClienteDTO.password());
            usuario.setFotoPerfil(registroClienteDTO.fotoPerfil());
            usuario.setEstadoCuenta(EstadoRegistro.ACTIVO);
            Usuario usuarioGuardado = usuariosRepo.save(usuario);

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String passwordEncriptada = passwordEncoder.encode( registroClienteDTO.password() );
            usuario.setPassword( passwordEncriptada );
            //Retornamos el id del cliente registrado
            return usuarioGuardado.getCodigo();
        }
    }

    @Override
    public void editarPerfil(ActualizarClienteDto actualizarClienteDTO) throws Exception {
        Optional<Usuario>usuarioOptional = usuariosRepo.findById(actualizarClienteDTO.id());
        if(usuarioOptional.isEmpty()){
            throw new Exception("El usuario no se encuentra registrado");
        }
        Usuario usuario = usuarioOptional.get();
        usuario.setFotoPerfil(actualizarClienteDTO.fotoPerfil());
        usuario.setCiudad(actualizarClienteDTO.ciudadResidencia());
        usuario.setNombre(actualizarClienteDTO.nombre());
        usuario.setEmail(actualizarClienteDTO.email());

        usuariosRepo.save(usuario);

    }

    @Override
    public void actualizarPassword(CambioPasswordDto cambioPasswordDto) throws Exception {
        Optional<Usuario>usuarioOptional = usuariosRepo.findById(cambioPasswordDto.id());
        if(usuarioOptional.isEmpty()){
            throw new Exception("Usted no se encuentra registrado");
        }
        Usuario usuario = usuarioOptional.get();
        usuario.setPassword(cambioPasswordDto.passwordNueva());
        usuariosRepo.save(usuario);

    }

    @Override
    public void mostrarPerfil(MostrarPerfilDTO mostrarPerfilDTO) throws Exception {
        Optional<Usuario>usuarioOptional = usuariosRepo.findById(mostrarPerfilDTO.id());
        if(usuarioOptional.isEmpty()){
            throw new Exception("El usuario no se encuentra registrado");
        }
        Usuario usuario = usuarioOptional.get();
        usuario.setFotoPerfil(mostrarPerfilDTO.fotoPerfil());
        usuario.setCiudad(mostrarPerfilDTO.ciudad());
        usuario.setNombre(mostrarPerfilDTO.nombre());
        usuario.setEmail(mostrarPerfilDTO.email());

    }

    private boolean existeEmail(String email) {
        return usuariosRepo.findByEmail(email).isPresent();
    }
}
