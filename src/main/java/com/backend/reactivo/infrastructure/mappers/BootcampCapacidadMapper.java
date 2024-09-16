package com.backend.reactivo.infrastructure.mappers;

import com.backend.reactivo.domain.models.BootcampCapacidad;
import com.backend.reactivo.infrastructure.entities.BootcampCapacidadEntity;

public class BootcampCapacidadMapper {

	public static BootcampCapacidad toDomain(BootcampCapacidadEntity entity) {
        return new BootcampCapacidad(entity.getId(), entity.getBootcamp_id(), entity.getCapacidad_id());
    }

    public static BootcampCapacidadEntity toEntity(BootcampCapacidad bootcampCapacidad) {
        return new BootcampCapacidadEntity(bootcampCapacidad.getId(), bootcampCapacidad.getBootcamp_id(), bootcampCapacidad.getCapacidad_id());
    }
    
}
