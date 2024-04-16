package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.negociodtos.DetalleNegocioDTO;
import co.edu.uniquindio.proyecto.model.Documents.Negocio;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRevision;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NegociosRepo extends MongoRepository <Negocio,String> {

    Optional<Negocio> findById(String id);

    //List<Negocio> findByNombre(String nombre);

    @Query("{'codigoUsuario': ?0}")
    List<Negocio> findByCodigoUsuario(String codigoUsuario);

    List<Negocio> findByNombreIsLike(String nombre);
    List<Negocio> findByEstadoRevision(EstadoRevision estadoRevision);
}
