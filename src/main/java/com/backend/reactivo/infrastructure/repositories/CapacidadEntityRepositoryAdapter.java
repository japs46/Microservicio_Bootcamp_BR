package com.backend.reactivo.infrastructure.repositories;

import org.springframework.stereotype.Component;

import com.backend.reactivo.domain.models.Capacidad;
import com.backend.reactivo.domain.ports.out.CapacidadRepositoryPort;
import com.backend.reactivo.infrastructure.mappers.CapacidadMapper;

import reactor.core.publisher.Flux;

@Component
public class CapacidadEntityRepositoryAdapter implements CapacidadRepositoryPort{
	
	private final CapacidadEntityRepository capacidadEntityRepository;
	
	public CapacidadEntityRepositoryAdapter(CapacidadEntityRepository capacidadEntityRepository) {
		this.capacidadEntityRepository = capacidadEntityRepository;
	}

	@Override
	public Flux<Capacidad> getByBootcampId(Long bootcampId) {
		return capacidadEntityRepository.findByBootcampId(bootcampId)
				.map(CapacidadMapper::toDomain);
	}

}
