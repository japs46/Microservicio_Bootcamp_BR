package com.backend.reactivo.application.services;

import org.springframework.stereotype.Service;

import com.backend.reactivo.application.dto.ResponseBootcamp;
import com.backend.reactivo.domain.models.Bootcamp;
import com.backend.reactivo.domain.ports.in.CreateBootcampUseCase;
import com.backend.reactivo.domain.ports.in.RetrieveBootcampUseCase;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BootcampService implements CreateBootcampUseCase,RetrieveBootcampUseCase{
	
	private final CreateBootcampUseCase createBootcampUseCase;
	private final RetrieveBootcampUseCase retrieveBootcampUseCase;
	
	public BootcampService(CreateBootcampUseCase createBootcampUseCase,RetrieveBootcampUseCase retrieveBootcampUseCase) {
		this.createBootcampUseCase = createBootcampUseCase;
		this.retrieveBootcampUseCase = retrieveBootcampUseCase;
	}

	@Override
	public Mono<ResponseBootcamp> createBootcamp(Bootcamp bootcamp) {
		return createBootcampUseCase.createBootcamp(bootcamp);
	}

	@Override
	public Flux<Bootcamp> getAllBootcampsPag(int page, int size, String sortBy, String direction) {
		return retrieveBootcampUseCase.getAllBootcampsPag(page, size, sortBy, direction);
	}

}
