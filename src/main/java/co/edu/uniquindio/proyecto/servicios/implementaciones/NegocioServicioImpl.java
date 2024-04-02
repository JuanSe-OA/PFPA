package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.ActualizarNegocioDTO;
import co.edu.uniquindio.proyecto.dto.CrearNegocioDTO;
import co.edu.uniquindio.proyecto.dto.DetalleNegocioDTO;
import co.edu.uniquindio.proyecto.model.Documents.Negocio;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;
import co.edu.uniquindio.proyecto.repositorios.NegociosRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.NegocioServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NegocioServicioImpl implements NegocioServicio {
    private final NegociosRepo negocioRepo;

    public NegocioServicioImpl(NegociosRepo negocioRepo) {
        this.negocioRepo = negocioRepo;
    }

    @Override
    public String crearNegocio(CrearNegocioDTO crearNegocioDTO) throws Exception {
        Negocio negocio = new Negocio();

        negocio.setNombre(crearNegocioDTO.nombre());
        negocio.setDescripcion(crearNegocioDTO.descripcion());
        negocio.setCodigoUsuario(crearNegocioDTO.codigoUsuario());
        negocio.setDireccion(crearNegocioDTO.direccion());
        negocio.setTipoNegocio(crearNegocioDTO.tipoNegocio());
        negocio.setUbicacion(crearNegocioDTO.ubicacion());
        negocio.setTelefonos(crearNegocioDTO.telefonos());
        negocio.setHorarios(crearNegocioDTO.horarios());
        negocio.setImagenes(crearNegocioDTO.imagenes());
        negocio.setEstadoRegistro(EstadoRegistro.ACTIVO);

        Negocio negocioGuardado=negocioRepo.save(negocio);

        return negocioGuardado.getCodigo();
    }

    @Override
    public void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception {
        Optional<Negocio> optionalNegocio = negocioRepo.findById(actualizarNegocioDTO.codigo());

        if(optionalNegocio.isEmpty()){
            throw new Exception("El negocio a modificar no ha sido encontrado");
        }

        Negocio negocio = optionalNegocio.get();
        negocio.setNombre(actualizarNegocioDTO.nombre());
        negocio.setDescripcion(actualizarNegocioDTO.descripcion());
        negocio.setDireccion(actualizarNegocioDTO.direccion());
        negocio.setTelefonos(actualizarNegocioDTO.telefonos());
        negocio.setImagenes(actualizarNegocioDTO.imagenes());
        negocio.setHorarios(actualizarNegocioDTO.horarios());
        negocio.setUbicacion(actualizarNegocioDTO.ubicacion());

        negocioRepo.save(negocio);
    }

    @Override
    public void eliminarNegocio(String codigoNegocio)throws Exception {
        Optional<Negocio> optionalNegocio=negocioRepo.findById(codigoNegocio);

        if(optionalNegocio.isEmpty()){
            throw new Exception("El negocioo a eliminar no ha sido encontrado");
        }
        Negocio negocio= optionalNegocio.get();
        negocio.setEstadoRegistro(EstadoRegistro.INACTIVO);
        negocioRepo.save(negocio);
    }

    @Override
    public List<DetalleNegocioDTO> buscarNegocios(String busqueda) {
        List<Negocio> negociosEncontrados = negocioRepo.findByNombreIsLike(busqueda);

        List<DetalleNegocioDTO> detalleNegociosEncontraadosDTOS = new ArrayList<>();
        for(Negocio n : negociosEncontrados){
            detalleNegociosEncontraadosDTOS.add(new DetalleNegocioDTO(
                    n.getCodigo(),
                    n.getNombre(),
                    n.getDescripcion(),
                    n.getDireccion(),
                    n.getTipoNegocio(),
                    n.getTelefonos(),
                    n.getImagenes(),
                    n.getHorarios()));
        }
        return detalleNegociosEncontraadosDTOS;
    }

    @Override
    public void filtrarPorEstado(EstadoRegistro estadoRegistro) {

    }

    @Override
    public DetalleNegocioDTO obtenerDetalleNegocio(String codigoNegocio) throws Exception{
        Optional<Negocio> optionalNegocio=negocioRepo.findById(codigoNegocio);

        if(optionalNegocio.isEmpty()){
            throw new Exception("No se ha podido encontrar el negocio");
        }
        Negocio negocio= optionalNegocio.get();
        DetalleNegocioDTO detalleNegocioDTO = new DetalleNegocioDTO(
                negocio.getCodigo(),
                negocio.getNombre(),
                negocio.getDescripcion(),
                negocio.getDireccion(),
                negocio.getTipoNegocio(),
                negocio.getTelefonos(),
                negocio.getImagenes(),
                negocio.getHorarios());
        return detalleNegocioDTO;
    }

    @Override
    public List<DetalleNegocioDTO> listarNegociosPropietario(String codigoUsuario) {
        List<Negocio> negociosPropietario= negocioRepo.findByCodigoUsuario(codigoUsuario);

        List<DetalleNegocioDTO> detalleNegocioDTOS= new ArrayList<>();
        for(Negocio n : negociosPropietario){
            detalleNegocioDTOS.add(new DetalleNegocioDTO(
                    n.getCodigo(),
                    n.getNombre(),
                    n.getDescripcion(),
                    n.getDireccion(),
                    n.getTipoNegocio(),
                    n.getTelefonos(),
                    n.getImagenes(),
                    n.getHorarios()));
        }
        return detalleNegocioDTOS;
    }

    @Override
    public void cambiarEstado(String codigoNegocio, EstadoRegistro estadoRegistro) throws Exception{
        Optional<Negocio> optionalNegocio = negocioRepo.findById(codigoNegocio);

        if(optionalNegocio.isEmpty()){
            throw new Exception("El negocio a modificar no se ha encontrado");
        }

        Negocio negocio= optionalNegocio.get();
        negocio.setEstadoRegistro(estadoRegistro);
        negocioRepo.save(negocio);
    }

    @Override
    public void registrarRevision() {

    }
}