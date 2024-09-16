package com.backend.reactivo.domain.ports.out;

import com.backend.reactivo.domain.models.BootcampCapacidad;

import reactor.core.publisher.Mono;

public interface BootcampCapacidadRepositoryPort {

	Mono<BootcampCapacidad> save(BootcampCapacidad capacidadTecnologia);
	
}
