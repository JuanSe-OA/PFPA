package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.model.Documents.Usuario;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuariosRepo extends MongoRepository<Usuario,String> {
    //public List<Usuario> findByEstado(EstadoRegistro estadoRegistro);
    Optional<Usuario>findByEmail(String email);
    Optional<Usuario>findById(String id);
    List<Usuario> findByNombreIsLike(String nombre);
    @Query(value = "{'codigoUsuario': ?0}", fields = "{'favoritos' :  1}")
    List<String> findFavoritosByCodigoUsuario(String codigoUsuario);
}
