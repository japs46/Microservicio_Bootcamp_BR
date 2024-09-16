package com.backend.reactivo.domain.ports.in;

import org.springframework.stereotype.Component;

import com.backend.reactivo.application.dto.ResponseBootcamp;
import com.backend.reactivo.domain.models.Bootcamp;

import reactor.core.publisher.Mono;

@Component
public interface CreateBootcampUseCase {

	Mono<ResponseBootcamp> createBootcamp(Bootcamp bootcamp);
}
