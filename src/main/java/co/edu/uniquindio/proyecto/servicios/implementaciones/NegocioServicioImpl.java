package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.negociodtos.ActualizarNegocioDTO;
import co.edu.uniquindio.proyecto.dto.negociodtos.CrearNegocioDTO;
import co.edu.uniquindio.proyecto.dto.negociodtos.DetalleNegocioDTO;
import co.edu.uniquindio.proyecto.dto.negociodtos.ItemListarNegociosDTO;
import co.edu.uniquindio.proyecto.model.Documents.Comentario;
import co.edu.uniquindio.proyecto.model.Documents.Negocio;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;
import co.edu.uniquindio.proyecto.repositorios.ComentariosRepo;
import co.edu.uniquindio.proyecto.repositorios.NegociosRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.NegocioServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NegocioServicioImpl implements NegocioServicio {
    private final NegociosRepo negocioRepo;
    private final ComentarioServicioImpl comentarioServicio;
    private final ComentariosRepo comentariosRepo;

    public NegocioServicioImpl(NegociosRepo negocioRepo, ComentarioServicioImpl comentarioServicio, ComentariosRepo comentariosRepo) {
        this.negocioRepo = negocioRepo;
        this.comentarioServicio = comentarioServicio;
        this.comentariosRepo = comentariosRepo;
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
    public List<ItemListarNegociosDTO> buscarNegocios(String busqueda) {
        List<Negocio> negociosEncontrados = negocioRepo.findByNombreIsLike(busqueda);

        List<ItemListarNegociosDTO> detalleNegociosEncontraadosDTOS = new ArrayList<>();
        for(Negocio n : negociosEncontrados){
            double calificacion = comentarioServicio.calcularPromedioCalificaciones(n.getCodigo());
            int numCalificaciones = comentarioServicio.calcularNumeroComentarios(n.getCodigo());
            String estadoActual =definirEstadoActual(n);
            String horaCierre= definirHoraCierre(n);

            detalleNegociosEncontraadosDTOS.add(new ItemListarNegociosDTO(
                    n.getCodigo(),
                    n.getNombre(), calificacion,
                    numCalificaciones,
                    n.getTipoNegocio(),
                    horaCierre,
                    estadoActual,
                    n.getDireccion(),
                    n.getImagenes()));
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
        double promedio = comentarioServicio.calcularPromedioCalificaciones(negocio.getCodigo());
        int numCalificaciones= comentarioServicio.calcularNumeroComentarios(negocio.getCodigo());
        String estadoActual =definirEstadoActual(negocio);
        String horaCierre= definirHoraCierre(negocio);

        DetalleNegocioDTO detalleNegocioDTO = new DetalleNegocioDTO(
                negocio.getCodigo(),
                negocio.getNombre(),
                promedio,
                numCalificaciones,
                negocio.getTipoNegocio(),
                horaCierre,
                estadoActual);
        return detalleNegocioDTO;
    }

    @Override
    public List<DetalleNegocioDTO> listarNegociosPropietario(String codigoUsuario) {
        List<Negocio> negociosPropietario= negocioRepo.findByCodigoUsuario(codigoUsuario);

        List<DetalleNegocioDTO> detalleNegocioDTOS= new ArrayList<>();
        for(Negocio n : negociosPropietario){
            double calificacion = comentarioServicio.calcularPromedioCalificaciones(n.getCodigo());
            int numCalificaciones = comentarioServicio.calcularNumeroComentarios(n.getCodigo());
            String estadoActual =definirEstadoActual(n);
            String horaCierre= definirHoraCierre(n);
            detalleNegocioDTOS.add(new DetalleNegocioDTO(
                    n.getCodigo(),
                    n.getNombre(),
                    calificacion,
                    numCalificaciones,
                    n.getTipoNegocio(),
                    horaCierre,
                    estadoActual));
        }
        return detalleNegocioDTOS;
    }

    @Override
    public List<ItemListarNegociosDTO> buscarNegociosDistancia(double distancia) {
        return List.of();
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

    public String definirEstadoActual(Negocio n){
        String estado="";
        return  estado;
    }

    public String definirHoraCierre(Negocio n ){
        String horaCierre="";
        LocalDateTime horaDiaActual = LocalDateTime.now();
        String dia= String.valueOf(horaDiaActual.getDayOfWeek());
        return horaCierre;
    }
    public double calcularDistancia(Negocio n,double latitud,double longitud){
        double distanciaLatitud = Math.abs(latitud - n.getUbicacion().getLatitud());
        double distanciaLongitud = Math.abs(longitud - n.getUbicacion().getLongitud());
        //Fórmula de Haversine para calculas la distancia en km

        double distancia = Math.sqrt(Math.pow(distanciaLatitud*111*Math.cos( n.getUbicacion().getLatitud()*Math.PI/180),2)) +
                Math.pow(distanciaLatitud*111,2);
        return distancia;

    }
}
