package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.dto.negociodtos.DetalleNegocioDTO;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.*;
import co.edu.uniquindio.proyecto.model.Documents.Usuario;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;
import co.edu.uniquindio.proyecto.repositorios.UsuariosRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.UsuarioServicio;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServicioImpl implements UsuarioServicio {
    private final UsuariosRepo usuariosRepo;
    private final EmailServicio emailServicio;
    LocalTime hora = LocalTime.now();


    public UsuarioServicioImpl(UsuariosRepo usuariosRepo, EmailServicio emailServicio){
        this.usuariosRepo= usuariosRepo;
        this.emailServicio = emailServicio;
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
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String passwordEncriptada = passwordEncoder.encode( registroClienteDTO.password() );
            usuario.setPassword( passwordEncriptada );
            Usuario usuarioGuardado = usuariosRepo.save(usuario);
            String asunto = "Registro exitoso";
            String cuerpo = "Le damos la bienvenida a nuestra página web de map, esperamos puedas encontrar tus" +
                    " lugares favoritos";
            String correo = usuario.getEmail();

            emailServicio.enviarCorreo(new EmailDTO(asunto,cuerpo,correo));

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
        emailServicio.enviarCorreo(new EmailDTO("Modificación de perfil","Usted ha modificado su perfil hoy a las" + hora, usuario.getEmail() ));

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
        emailServicio.enviarCorreo(new EmailDTO("Modificación de contraseña","Usted ha modificado su contraseña hoy a las" + hora, usuario.getEmail() ));


    }

    @Override
    public MostrarPerfilDTO mostrarPerfil(String codigo) throws Exception {
        Optional<Usuario>usuarioOptional = usuariosRepo.findById(codigo);
        if(usuarioOptional.isEmpty()){
            throw new Exception("El usuario no se encuentra registrado");
        }
        Usuario usuario = usuarioOptional.get();
        MostrarPerfilDTO mostrarPerfilDTO = new MostrarPerfilDTO(codigo,
                usuario.getNombreUsuario(), usuario.getFotoPerfil(),
                usuario.getCiudad(), usuario.getEmail());
        return mostrarPerfilDTO;
    }

    @Override
    public void eliminarPerfil(String codigo) throws Exception {
        Optional<Usuario>usuarioOptional = usuariosRepo.findById(codigo);
        if(usuarioOptional.isEmpty()){
            throw new Exception("No se encontró un usuario con esa id");
        }
        Usuario usuario = usuarioOptional.get();
        usuario.setEstadoCuenta(EstadoRegistro.INACTIVO);
    }

    @Override
    public List<ItemUsuarioDTO> listarClientes(String busqueda) throws Exception {
        List<Usuario>usuarioList = usuariosRepo.findByNombreIsLike(busqueda);
        List<ItemUsuarioDTO>itemUsuarioDTOList = new ArrayList<ItemUsuarioDTO>();
        for(Usuario usuario : usuarioList){
            itemUsuarioDTOList.add(new ItemUsuarioDTO(
                    usuario.getCodigo(), usuario.getNombreUsuario(),
                    usuario.getFotoPerfil()
            ));
        }
        return itemUsuarioDTOList;
    }

    private boolean existeEmail(String email) {
        return usuariosRepo.findByEmail(email).isPresent();
    }

    public List<String> listarNegociosFavoritos(String codigoUsuario){
        List<String> codigoNegociosFavoritos= usuariosRepo.findFavoritos();
        return codigoNegociosFavoritos;
    }
}
