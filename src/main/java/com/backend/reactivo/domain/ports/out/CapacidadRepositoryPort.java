package com.backend.reactivo.domain.ports.out;

import com.backend.reactivo.domain.models.Capacidad;

import reactor.core.publisher.Flux;

public interface CapacidadRepositoryPort {

	Flux<Capacidad> getByBootcampId(Long bootcampId);
}
