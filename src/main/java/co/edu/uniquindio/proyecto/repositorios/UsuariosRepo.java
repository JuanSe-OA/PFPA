package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.model.Documents.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepo extends MongoRepository<Usuario,String> {
}
