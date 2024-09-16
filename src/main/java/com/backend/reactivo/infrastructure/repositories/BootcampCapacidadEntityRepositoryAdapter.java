package com.backend.reactivo.infrastructure.repositories;

import org.springframework.stereotype.Component;

import com.backend.reactivo.domain.models.BootcampCapacidad;
import com.backend.reactivo.domain.ports.out.BootcampCapacidadRepositoryPort;
import com.backend.reactivo.infrastructure.mappers.BootcampCapacidadMapper;

import reactor.core.publisher.Mono;

@Component
public class BootcampCapacidadEntityRepositoryAdapter implements BootcampCapacidadRepositoryPort{
	
	private final BootcampCapacidadEntityRepository bootcampCapacidadEntityRepository;

	public BootcampCapacidadEntityRepositoryAdapter(BootcampCapacidadEntityRepository bootcampCapacidadEntityRepository) {
		this.bootcampCapacidadEntityRepository = bootcampCapacidadEntityRepository;
	}

	@Override
	public Mono<BootcampCapacidad> save(BootcampCapacidad capacidadTecnologia) {
		return bootcampCapacidadEntityRepository.save(BootcampCapacidadMapper.toEntity(capacidadTecnologia))
				.map(BootcampCapacidadMapper::toDomain);
	}

}
