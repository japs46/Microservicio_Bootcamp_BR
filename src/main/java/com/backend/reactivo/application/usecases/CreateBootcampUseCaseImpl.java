package com.backend.reactivo.application.usecases;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.backend.reactivo.application.dto.ResponseBootcamp;
import com.backend.reactivo.domain.models.Bootcamp;
import com.backend.reactivo.domain.models.BootcampCapacidad;
import com.backend.reactivo.domain.models.Capacidad;
import com.backend.reactivo.domain.ports.in.CreateBootcampUseCase;
import com.backend.reactivo.domain.ports.out.BootcampCapacidadRepositoryPort;
import com.backend.reactivo.domain.ports.out.BootcampRepositoryPort;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CreateBootcampUseCaseImpl implements CreateBootcampUseCase{
	
	private final BootcampRepositoryPort bootcampRepositoryPort;
	private final BootcampCapacidadRepositoryPort bootcampCapacidadRepositoryPort;
	
	public CreateBootcampUseCaseImpl(BootcampRepositoryPort bootcampRepositoryPort,BootcampCapacidadRepositoryPort bootcampCapacidadRepositoryPort) {
		this.bootcampRepositoryPort = bootcampRepositoryPort;
		this.bootcampCapacidadRepositoryPort = bootcampCapacidadRepositoryPort;
	}

	@Override
	public Mono<ResponseBootcamp> createBootcamp(Bootcamp bootcamp) {
		// Validación: Debe haber capacidades asociadas
	    if (bootcamp.getListaCapacidad() == null || bootcamp.getListaCapacidad().isEmpty()) {
	        return Mono.just(new ResponseBootcamp("Debe asociar capacidades al bootcamp", bootcamp, false));
	    }

	    // Validación: Mínimo 1 capacidad
	    if (bootcamp.getListaCapacidad().size() < 1) {
	        return Mono.just(new ResponseBootcamp("El Bootcamp debe tener como mínimo 1 capacidad asociada", bootcamp, false));
	    }

	    // Validación: Máximo 4 capacidades
	    if (bootcamp.getListaCapacidad().size() > 4) {
	        return Mono.just(new ResponseBootcamp("El Bootcamp debe tener como máximo 4 capacidades asociadas", bootcamp, false));
	    }

	    // Validación: capacidades duplicadas (IDs únicos)
	    Set<Long> uniqueCapacidadIds = bootcamp.getListaCapacidad().stream()
	            .map(Capacidad::getId) // Obtener los IDs de las tecnologías
	            .collect(Collectors.toSet());

	    if (uniqueCapacidadIds.size() != bootcamp.getListaCapacidad().size()) {
	        return Mono.just(new ResponseBootcamp("La lista de capacidades contiene duplicados", bootcamp, false));
	    }

	    // Si todas las validaciones pasan, guardamos la entidad Capacidad
	    return bootcampRepositoryPort.save(bootcamp)
	        .flatMap(savedBootcamp -> 
	            // Guardar manualmente cada relación Capacidad-Tecnologia
	            Flux.fromIterable(bootcamp.getListaCapacidad())
	                .flatMap(capacidad -> {
	                    // Crear y guardar la entidad intermedia para la relación Capacidad-Tecnologia
	                    BootcampCapacidad bootcampCapacidad = 
	                        new BootcampCapacidad(null, savedBootcamp.getId(), capacidad.getId());

	                    return bootcampCapacidadRepositoryPort.save(bootcampCapacidad);
	                })
	                // Luego de guardar todas las relaciones, devolvemos la capacidad guardada
	                .then(Mono.just(new ResponseBootcamp("Se registró el bootcamp correctamente", savedBootcamp, true)))
	        );
	}

}
