package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.*;
import co.edu.uniquindio.proyecto.model.Documents.Negocio;
import co.edu.uniquindio.proyecto.model.Documents.Usuario;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;
import co.edu.uniquindio.proyecto.repositorios.NegociosRepo;
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
    private final NegociosRepo negociosRepo;
    LocalTime hora = LocalTime.now();


    public UsuarioServicioImpl(UsuariosRepo usuariosRepo, EmailServicio emailServicio, NegociosRepo negociosRepo){
        this.usuariosRepo= usuariosRepo;
        this.emailServicio = emailServicio;
        this.negociosRepo = negociosRepo;
    }
    @Override
    public String registrarse(RegistroClienteDto registroClienteDTO) throws Exception {

        if(existeEmail(registroClienteDTO.email())){
            throw new Exception("Usted ya se encuentra registrado en nuestra plataforma");
        }else {
            Usuario usuario = new Usuario();
            usuario.setNombre(registroClienteDTO.nombre());
            usuario.setNombreUsuario(registroClienteDTO.nombreUsuario());
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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usuario.setPassword(passwordEncoder.encode(cambioPasswordDto.passwordNueva()));
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
    public List<ItemUsuarioDTO> listarClientes() throws Exception {
        List<Usuario>usuarioList = usuariosRepo.findAll();
        List<ItemUsuarioDTO>itemUsuarioDTOList = new ArrayList<>();
        for(Usuario usuario : usuarioList){
            itemUsuarioDTOList.add(new ItemUsuarioDTO(
                    usuario.getCodigo(), usuario.getNombreUsuario(),
                    usuario.getFotoPerfil()
            ));
        }
        return itemUsuarioDTOList;
    }

    @Override
    public void agregarNegocioFavoritos(AgregarNegocioFavoritoDTO agregarNegocioFavoritoDTO) throws Exception {
        Optional<Negocio> optionalNegocio = negociosRepo.findById(agregarNegocioFavoritoDTO.codigoNegocio());
        if(optionalNegocio.isEmpty()){
            throw new Exception("El negocio no ha sido encontrado");
        }
        Optional<Usuario> optionalUsuario = usuariosRepo.findById(agregarNegocioFavoritoDTO.codigoUsuario());
        if(optionalUsuario.isEmpty()){
            throw new Exception("El usuario no ha sido encontrado");
        }
        Usuario usuario = optionalUsuario.get();
        usuario.getFavoritos().add(agregarNegocioFavoritoDTO.codigoNegocio());
    }

    private boolean existeEmail(String email) {
        return usuariosRepo.findByEmail(email).isPresent();
    }

}
