package com.backend.reactivo.application.usecases;

import org.springframework.stereotype.Component;

import com.backend.reactivo.domain.models.Bootcamp;
import com.backend.reactivo.domain.models.Capacidad;
import com.backend.reactivo.domain.ports.in.RetrieveBootcampUseCase;
import com.backend.reactivo.domain.ports.out.BootcampRepositoryPort;
import com.backend.reactivo.domain.ports.out.CapacidadRepositoryPort;
import com.backend.reactivo.domain.ports.out.TecnologiaRepositoryPort;

import reactor.core.publisher.Flux;

@Component
public class RetrieveBootcampUseCaseImpl implements RetrieveBootcampUseCase {

	private final BootcampRepositoryPort bootcampRepositoryPort;
	private final CapacidadRepositoryPort capacidadRepositoryPort;
	private final TecnologiaRepositoryPort tecnologiaRepositoryPort;

	public RetrieveBootcampUseCaseImpl(BootcampRepositoryPort bootcampRepositoryPort,
			CapacidadRepositoryPort capacidadRepositoryPort, TecnologiaRepositoryPort tecnologiaRepositoryPort) {
		this.bootcampRepositoryPort = bootcampRepositoryPort;
		this.capacidadRepositoryPort = capacidadRepositoryPort;
		this.tecnologiaRepositoryPort = tecnologiaRepositoryPort;
	}

	@Override
	public Flux<Bootcamp> getAllBootcampsPag(int page, int size, String sortBy, String direction) {
		 return bootcampRepositoryPort.findAll()
			        .flatMap(bootcamp -> 
			            // Obtener las capacidades asociadas al bootcamp
			            capacidadRepositoryPort.getByBootcampId(bootcamp.getId())
			            // Para cada capacidad, obtener sus tecnologías
			            .flatMap(capacidad -> 
			                tecnologiaRepositoryPort.getTecnologiasByCapacidad(capacidad.getId())
			                .collectList() // Recoger todas las tecnologías en una lista
			                .map(tecnologias -> 
			                    // Crear un nuevo objeto Capacidad con la lista de tecnologías
			                    new Capacidad(capacidad.getId(), capacidad.getNombre(), tecnologias)
			                )
			            )
			            .collectList() // Recoger todas las capacidades con sus tecnologías en una lista
			            .map(capacidades -> 
			                // Crear el objeto Bootcamp con la lista de capacidades (que incluye las tecnologías)
			                new Bootcamp(bootcamp.getId(), bootcamp.getNombre(), bootcamp.getDescripcion(), capacidades)
			            )
			        )
			        .sort((c1, c2) -> {
			            if ("desc".equalsIgnoreCase(direction)) {
			                if ("nombre".equalsIgnoreCase(sortBy)) {
			                    return c2.getNombre().compareTo(c1.getNombre());
			                } else if ("cantidadCapacidades".equalsIgnoreCase(sortBy)) {
			                    return Integer.compare(c2.getListaCapacidad().size(), c1.getListaCapacidad().size());
			                }
			            } else {
			                if ("nombre".equalsIgnoreCase(sortBy)) {
			                    return c1.getNombre().compareTo(c2.getNombre());
			                } else if ("cantidadCapacidades".equalsIgnoreCase(sortBy)) {
			                    return Integer.compare(c1.getListaCapacidad().size(), c2.getListaCapacidad().size());
			                }
			            }
			            return 0; // Caso por defecto si sortBy no es reconocido
			        })
			        .skip((long) page * size)
			        .take(size);
	}

}
