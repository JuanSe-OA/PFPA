package co.edu.uniquindio.proyecto.dto.negociodtos;

import co.edu.uniquindio.proyecto.model.Entidades.Horario;

import java.util.List;
import java.util.Set;

public record DetalleNegocioPropioDTO(
        String codigoNegocio,
        String nombre,
        String descripcion,
        double calificacionPromedio,
        Set<String> telefonos,
        String direccion,
        List<Horario> horarios,
        List<String> imagenes) {
}
