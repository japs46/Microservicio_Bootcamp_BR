package com.backend.reactivo.infrastructure.repositories;

import org.springframework.stereotype.Component;

import com.backend.reactivo.domain.models.Bootcamp;
import com.backend.reactivo.domain.ports.out.BootcampRepositoryPort;
import com.backend.reactivo.infrastructure.mappers.BootcampMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class BootcampEntityRepositoryAdapter implements BootcampRepositoryPort{
	
	private final BootcampEntityRepository bootcampEntityRepository;

	public BootcampEntityRepositoryAdapter(BootcampEntityRepository bootcampEntityRepository) {
		this.bootcampEntityRepository = bootcampEntityRepository;
	}

	@Override
	public Mono<Bootcamp> save(Bootcamp bootcamp) {
		return bootcampEntityRepository.save(BootcampMapper.toEntity(bootcamp))
				.map(BootcampMapper::toDomain);
	}

	@Override
	public Flux<Bootcamp> findAll() {
		return bootcampEntityRepository.findAll().map(BootcampMapper::toDomain);
	}

}
