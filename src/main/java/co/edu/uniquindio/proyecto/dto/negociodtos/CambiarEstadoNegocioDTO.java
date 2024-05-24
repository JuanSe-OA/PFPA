package co.edu.uniquindio.proyecto.dto.negociodtos;

import co.edu.uniquindio.proyecto.model.Enum.EstadoRegistro;

public record CambiarEstadoNegocioDTO(String codigoNegocio, EstadoRegistro estadoRegistro) {
}
