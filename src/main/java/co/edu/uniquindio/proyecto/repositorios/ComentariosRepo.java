package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.model.Documents.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComentariosRepo extends MongoRepository<Comentario,String> {
    Optional<Comentario> findById(String id);

    @Query("{'codigoNegocio': ?0}")
    List<Comentario> findByNegocioId(String negocioId);

    @Query(value = "{'codigoNegocio': ?0}", fields = "{'calificacion': 1}")
    List<Double> findCalificacionByCodigoNegocio(String codigoNegocio);
}
