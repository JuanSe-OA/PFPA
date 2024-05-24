package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.dto.moderadordtos.SesionModeradorDTO;
import co.edu.uniquindio.proyecto.dto.usuariosdtos.SesionDto;
import co.edu.uniquindio.proyecto.model.Documents.Moderador;
import co.edu.uniquindio.proyecto.model.Documents.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ModeradorRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuariosRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.proyecto.utils.JWUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AutenticacionServicioImpl implements AutenticacionServicio {
    private final UsuariosRepo usuariosRepo;
    private final ModeradorRepo moderadorRepo;
    private final JWUtils jwtUtils;
    @Override
    public TokenDTO iniciarSesionUsuario(SesionDto sesionDto) throws Exception {
        if(sesionDto.email().isEmpty()||sesionDto.password().isEmpty()){
            throw new Exception("El email y la contraseña no pueden estar vacíos");
        }
        Optional<Usuario> usuarioOptional = usuariosRepo.findByEmail(sesionDto.email());
        if (usuarioOptional.isEmpty()) {
            throw new Exception("El correo no se encuentra registrado");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Usuario usuario = usuarioOptional.get();
        if(!passwordEncoder.matches(sesionDto.password(), usuario.getPassword()) ) {
            throw new Exception("La contraseña es incorrecta");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", "CLIENTE");
        map.put("nombre", usuario.getNombre());
        map.put("id", usuario.getCodigo());
        return new TokenDTO( jwtUtils.generarToken(usuario.getEmail(), map) );
    }

    @Override
    public TokenDTO iniciarSesionModerador(SesionModeradorDTO sesionModeradorDTO) throws Exception {
        if(sesionModeradorDTO.email().isEmpty() || sesionModeradorDTO.password().isEmpty()) {
            throw new Exception("El email y la contraseña no pueden estar vacíos");
        }
        Optional<Moderador> moderadorOptional = moderadorRepo.findByEmail(sesionModeradorDTO.email());
        if (moderadorOptional.isEmpty()) {
            throw new Exception("El correo no se encuentra registrado");
        }
        Moderador moderador = moderadorOptional.get();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode( moderador.getPassword());
        moderador.setPassword( passwordEncriptada );
        moderadorRepo.save(moderador);
        if( !passwordEncoder.matches(sesionModeradorDTO.password(), moderador.getPassword()) ) {
            throw new Exception("La contraseña es incorrecta");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", "MODERADOR");
        map.put("nombre", moderador.getNombre());
        map.put("id", moderador.getCodigo());
        return new TokenDTO( jwtUtils.generarToken(moderador.getEmail(), map) );
    }

    @Override
    public TokenDTO sesionUsuarioNoAutenticado() {
        String uniqueId= UUID.randomUUID().toString();
        Map<String, Object> map= new HashMap<>();
        map.put("rol","USUARIO_NO_AUTENTICADO");
        map.put("id",uniqueId);
        return new TokenDTO(jwtUtils.generarToken(uniqueId,map));
    }

}
