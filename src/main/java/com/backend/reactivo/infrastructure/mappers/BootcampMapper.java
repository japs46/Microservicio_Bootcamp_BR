package com.backend.reactivo.infrastructure.mappers;

import com.backend.reactivo.domain.models.Bootcamp;
import com.backend.reactivo.infrastructure.entities.BootcampEntity;

public class BootcampMapper {

	public static Bootcamp toDomain(BootcampEntity entity) {
        return new Bootcamp(entity.getId(), entity.getNombre(), entity.getDescripcion(),null);
    }

    public static BootcampEntity toEntity(Bootcamp bootcamp) {
        return new BootcampEntity(bootcamp.getId(), bootcamp.getNombre(), bootcamp.getDescripcion());
    }
    
}
