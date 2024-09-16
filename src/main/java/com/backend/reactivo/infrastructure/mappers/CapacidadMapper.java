package com.backend.reactivo.infrastructure.mappers;

import com.backend.reactivo.domain.models.Capacidad;
import com.backend.reactivo.infrastructure.entities.CapacidadEntity;

public class CapacidadMapper {

	public static Capacidad toDomain(CapacidadEntity entity) {
        return new Capacidad(entity.getId(), entity.getNombre(),null);
    }

    public static CapacidadEntity toEntity(Capacidad capacidad) {
        return new CapacidadEntity(capacidad.getId(), capacidad.getNombre(),null);
    }
	
}
