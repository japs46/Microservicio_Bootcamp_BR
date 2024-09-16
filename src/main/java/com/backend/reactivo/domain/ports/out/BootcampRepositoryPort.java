package com.backend.reactivo.domain.ports.out;

import com.backend.reactivo.domain.models.Bootcamp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface BootcampRepositoryPort {

	Mono<Bootcamp> save(Bootcamp bootcamp);
	
	Flux<Bootcamp> findAll();

}
