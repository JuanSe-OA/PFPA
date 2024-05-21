package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.negociodtos.*;
import co.edu.uniquindio.proyecto.model.Documents.Negocio;
import co.edu.uniquindio.proyecto.model.Documents.Usuario;
import co.edu.uniquindio.proyecto.model.Entidades.Horario;
import co.edu.uniquindio.proyecto.model.Entidades.Revision;
import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;
import co.edu.uniquindio.proyecto.repositorios.ComentariosRepo;
import co.edu.uniquindio.proyecto.repositorios.NegociosRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuariosRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.NegocioServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NegocioServicioImpl implements NegocioServicio {
    private final NegociosRepo negocioRepo;
    private final ComentarioServicioImpl comentarioServicio;
    private final UsuariosRepo usuariosRepo;

    public NegocioServicioImpl(NegociosRepo negocioRepo, ComentarioServicioImpl comentarioServicio, UsuariosRepo usuariosRepo) {
        this.negocioRepo = negocioRepo;
        this.comentarioServicio = comentarioServicio;
        this.usuariosRepo = usuariosRepo;
    }

    @Override
    public String crearNegocio(CrearNegocioDTO crearNegocioDTO) throws Exception {
        Optional<Usuario> optionalUsuario = usuariosRepo.findById(crearNegocioDTO.codigoUsuario());
        if(optionalUsuario.isEmpty()){
            throw new Exception("No se ha encontrado al usuario");
        }
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
        List<Revision> historialRevisiones = new ArrayList<>();
        negocio.setHistorialRevisiones(historialRevisiones);
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
    public List<ItemListarNegociosDTO> buscarNegociosPorNombre(String busqueda) {
        List<Negocio> negociosEncontrados = negocioRepo.findByNombreIsLike(busqueda);

        List<ItemListarNegociosDTO> detalleNegociosEncontraadosDTOS = new ArrayList<>();
        for(Negocio n : negociosEncontrados){
            double calificacion = comentarioServicio.calcularPromedioCalificaciones(n.getCodigo());
            int numCalificaciones = comentarioServicio.calcularNumeroComentarios(n.getCodigo());
            Horario dia= definirDia(n);
            String estadoActual =definirEstadoActual(dia);
            String horaCierre= definirHoraCierre(dia);

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
        Horario dia= definirDia(negocio);
        String estadoActual =definirEstadoActual(dia);
        String horaCierre= definirHoraCierre(dia);

        DetalleNegocioDTO detalleNegocioDTO = new DetalleNegocioDTO(
                negocio.getCodigo(),
                negocio.getNombre(),
                promedio,
                numCalificaciones,
                negocio.getTipoNegocio(),
                horaCierre,
                estadoActual,
                negocio.getImagenes());
        return detalleNegocioDTO;
    }

    @Override
    public DetalleNegocioPropioDTO obtenerDetalleNegocioPropio(String codigoNegocio) throws Exception {
        Optional<Negocio> optionalNegocio=negocioRepo.findById(codigoNegocio);
        if(optionalNegocio.isEmpty()){
            throw new Exception("No se ha podido encontrar el negocio");
        }
        Negocio n= optionalNegocio.get();
        double calificacion = comentarioServicio.calcularPromedioCalificaciones(n.getCodigo());
        DetalleNegocioPropioDTO detalleNegocioPropio= new DetalleNegocioPropioDTO(
                n.getCodigo(),
                n.getNombre(),
                n.getDescripcion(),
                calificacion,
                n.getTelefonos(),
                n.getDireccion(),
                n.getHorarios(),
                n.getImagenes());
        return detalleNegocioPropio;
    }

    @Override
    public ItemNegocioInfoDTO obtenerInformacionNegocio(String codigoNegocio) throws Exception {
        Optional<Negocio> optionalNegocio=negocioRepo.findById(codigoNegocio);
        if(optionalNegocio.isEmpty()){
            throw new Exception("No se ha podido encontrar el negocio");
        }
        Negocio n= optionalNegocio.get();
        ItemNegocioInfoDTO infoNegocio= new ItemNegocioInfoDTO(
                n.getCodigo(),
                n.getDescripcion(),
                n.getDireccion(),
                n.getTelefonos(),
                n.getHorarios());
        return infoNegocio;
    }

    @Override
    public List<ItemListarNegociosDTO> listarNegociosPropietario(String codigoUsuario)throws Exception {
        Optional<Usuario> optionalUsuario = usuariosRepo.findById(codigoUsuario);
        if(optionalUsuario.isEmpty()){
            throw new Exception("No se ha encontrado al usuario");
        }
        Usuario u = optionalUsuario.get();
        System.out.println(u.getCodigo());
        List<Negocio> negociosPropietario= negocioRepo.findByCodigoUsuario(u.getCodigo());

        List<ItemListarNegociosDTO> detalleNegocioDTOS= new ArrayList<>();
        for(Negocio n : negociosPropietario){
            double calificacion = comentarioServicio.calcularPromedioCalificaciones(n.getCodigo());
            int numCalificaciones = comentarioServicio.calcularNumeroComentarios(n.getCodigo());
            Horario dia= definirDia(n);
            String estadoActual =definirEstadoActual(dia);
            String horaCierre= definirHoraCierre(dia);
            detalleNegocioDTOS.add(new ItemListarNegociosDTO(
                    n.getCodigo(),
                    n.getNombre(),
                    calificacion,
                    numCalificaciones,
                    n.getTipoNegocio(),
                    horaCierre,
                    estadoActual,
                    n.getDireccion(),
                    n.getImagenes()));
        }
        return detalleNegocioDTOS;
    }

    @Override
    public List<ItemListarNegociosDTO> listarNegociosFavoritos(String codigoUsuario)throws Exception {
        Optional<Usuario> optionalUsuario = usuariosRepo.findById(codigoUsuario);
        if(optionalUsuario.isEmpty()){
            throw new Exception("No se ha encontrado al usuario");
        }
        List<ItemListarNegociosDTO> negociosFavoritosDTOs= new ArrayList<>();
        List<String> favoritos= usuariosRepo.findFavoritosByCodigoUsuario(codigoUsuario);
        for(String codigo: favoritos){
            Optional<Negocio> optionalNegocio = negocioRepo.findById(codigo);
            if(optionalNegocio.isEmpty()){
                throw new Exception("No se ha podido encontrar el negocio");
            }
            Negocio n= optionalNegocio.get();
            double calificacion = comentarioServicio.calcularPromedioCalificaciones(n.getCodigo());
            int numCalificaciones = comentarioServicio.calcularNumeroComentarios(n.getCodigo());
            Horario dia= definirDia(n);
            String estadoActual =definirEstadoActual(dia);
            String horaCierre= definirHoraCierre(dia);
            negociosFavoritosDTOs.add(new ItemListarNegociosDTO(
                    n.getCodigo(),
                    n.getNombre(),
                    calificacion,
                    numCalificaciones,
                    n.getTipoNegocio(),
                    horaCierre,
                    estadoActual,
                    n.getDireccion(),
                    n.getImagenes()));
        }
        return negociosFavoritosDTOs;
    }

    @Override
    public void cambiarEstado(CambiarEstadoNegocioDTO cambiarEstadoNegocioDTO) throws Exception{
        Optional<Negocio> optionalNegocio = negocioRepo.findById(cambiarEstadoNegocioDTO.codigoNegocio());

        if(optionalNegocio.isEmpty()){
            throw new Exception("El negocio a modificar no se ha encontrado");
        }

        Negocio negocio= optionalNegocio.get();
        negocio.setEstadoRegistro(cambiarEstadoNegocioDTO.estadoRegistro());
        negocioRepo.save(negocio);
    }

    @Override
    public ObtenerNegocioDTO obtenerNegocio(String codigoNegocio) throws Exception {
        Optional<Negocio> optionalNegocio = negocioRepo.findById(codigoNegocio);
        if(optionalNegocio.isEmpty()){
            throw  new Exception("El negocio no fue encontrado");
        }
        Negocio n = optionalNegocio.get();
        ObtenerNegocioDTO obtenerNegocioDTO= new ObtenerNegocioDTO(
                n.getCodigo(),
                n.getNombre(),
                n.getDescripcion(),
                n.getDireccion(),
                n.getTipoNegocio(),
                n.getUbicacion(),
                n.getTelefonos(),
                n.getHorarios(),
                n.getImagenes()
        );
        return null;
    }

    @Override
    public List<ItemListarNegociosDTO> buscarNegociosDistancia(ObtenerDistanciaDTO obtenerDistanciaDTO) {
        List<Negocio> negocioList = negocioRepo.findAll();
        List<ItemListarNegociosDTO> itemListarNegociosDTOS = new ArrayList<>();
        for(Negocio n : negocioList){
            double calificacion = comentarioServicio.calcularPromedioCalificaciones(n.getCodigo());
            int numCalificaciones = comentarioServicio.calcularNumeroComentarios(n.getCodigo());
            Horario dia= definirDia(n);
            String estadoActual =definirEstadoActual(dia);
            String horaCierre= definirHoraCierre(dia);
            if(estaEnRango(n, obtenerDistanciaDTO.latitud(), obtenerDistanciaDTO.longitud(), obtenerDistanciaDTO.rango())){
                itemListarNegociosDTOS.add(new ItemListarNegociosDTO(n.getCodigo(),
                        n.getNombre(),calificacion,numCalificaciones,n.getTipoNegocio(),
                        horaCierre,estadoActual,n.getDireccion(),n.getImagenes()));
            }
        } return  itemListarNegociosDTOS;
    }
    public boolean estaEnRango(Negocio n, double latitudUsuario, double longitudUsuario, double rango) {
        // Convertir las coordenadas a radianes
        double latitudUsuarioRad = Math.toRadians(latitudUsuario);
        double longitudUsuarioRad = Math.toRadians(longitudUsuario);
        double latitudNegocioRad = Math.toRadians(n.getUbicacion().getLatitud());
        double longitudNegocioRad = Math.toRadians(n.getUbicacion().getLongitud());

        // Diferencia de coordenadas
        double diferenciaLatitud = latitudNegocioRad - latitudUsuarioRad;
        double diferenciaLongitud = longitudNegocioRad - longitudUsuarioRad;

        // Aplicar la fórmula de Haversine
        double a = Math.pow(Math.sin(diferenciaLatitud / 2), 2) +
                Math.cos(latitudUsuarioRad) * Math.cos(latitudNegocioRad) *
                        Math.pow(Math.sin(diferenciaLongitud / 2), 2);
        double distancia = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Radio de la Tierra en kilómetros
        double radioTierra = 6371.0;

        // Calcular la distancia
        double distanciaFinal = radioTierra * distancia;

        // Verificar si la distancia está dentro del rango dado
        return distanciaFinal <= rango;
    }


    public String definirEstadoActual(Horario dia){
        String estado="";
        LocalTime hora= LocalTime.now();
        if(dia ==null){
            estado="Cerrado";
        }
        else if(hora.isAfter(dia.getHoraFin()) || hora.isBefore(dia.getHoraInicio()) ){
            estado = "Cerrado";
        }else{
            estado="Abierto";
        }
        return  estado;
    }

    public String definirHoraCierre(Horario dia ){
        String horaCierre= "Cerrado";
        if(!(dia==null)){
            horaCierre= dia.getHoraFin().toString();
        }
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

    public Horario definirDia(Negocio n){
        LocalDate fechaActual = LocalDate.now();
        String dia= String.valueOf(fechaActual.getDayOfWeek());
        Horario horarioHoy= null;
        for(Horario h: n.getHorarios()){
            if(h.getDia().equalsIgnoreCase(dia)){
                horarioHoy=h;
            }
        }
        return horarioHoy;
    }
}
