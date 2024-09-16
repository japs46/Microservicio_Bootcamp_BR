package com.backend.reactivo.domain.ports.in;

import com.backend.reactivo.domain.models.Bootcamp;

import reactor.core.publisher.Flux;

public interface RetrieveBootcampUseCase {

	Flux<Bootcamp> getAllBootcampsPag(int page, int size, String sortBy,String direction);
}
