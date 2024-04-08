package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.moderadordtos.RevisarComentariosDTO;
import co.edu.uniquindio.proyecto.model.Documents.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentariosRepo extends MongoRepository<Comentario,String> {
    List<Comentario>findByCodigoNegocio(String codigo);

}
