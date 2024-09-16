package com.backend.reactivo;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.reactivo.application.dto.ResponseBootcamp;
import com.backend.reactivo.application.services.BootcampService;
import com.backend.reactivo.domain.models.Bootcamp;
import com.backend.reactivo.domain.models.Capacidad;
import com.backend.reactivo.domain.ports.in.CreateBootcampUseCase;
import com.backend.reactivo.domain.ports.in.RetrieveBootcampUseCase;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
public class BootcampServiceTest {

	@Mock
	private CreateBootcampUseCase createBootcampUseCase;

	@Mock
	private RetrieveBootcampUseCase retrieveBootcampUseCase;

	@InjectMocks
	private BootcampService bootcampService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Inicializa los mocks
	}

	@Test
	public void createBootcampTest() {
		List<Capacidad> listaCapacidad = Datos.LISTA_CAPACIDAD_1();

		Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp1", "descripcion bootcamp1", listaCapacidad);
		Mono<ResponseBootcamp> monoBootcamp = Mono.just(new ResponseBootcamp("se registro bootcamp", bootcamp, true));
		when(createBootcampUseCase.createBootcamp(any())).thenReturn(monoBootcamp);

		StepVerifier.create(bootcampService.createBootcamp(bootcamp)).expectSubscription().assertNext(response -> {
			assertNotNull(response);
			assertEquals("Se registro bootcamp", response.getMensaje());
			assertEquals(true, response.isStatus());
			assertEquals(1L, response.getBootcamp().getId());
			assertEquals(3, response.getBootcamp().getListaCapacidad().size());
		}).expectComplete();

		verify(createBootcampUseCase).createBootcamp(bootcamp);

	}

	@Test
	public void obtenerCapacidadesPaginadasTest() {
		Flux<Bootcamp> fluxBootcamp = Datos.FLUX_BOOTCAMP();

		Flux<Bootcamp> test1 = fluxBootcamp.filter(bootcamp -> bootcamp.getId() == 1 || bootcamp.getId() == 2);

		when(bootcampService.getAllBootcampsPag(0, 2, "nombre", "asc")).thenReturn(test1);

		StepVerifier.create(bootcampService.getAllBootcampsPag(0, 2, "nombre", "asc").log()).expectSubscription()
				.expectNextMatches(capacity -> capacity.getNombre().equals("Bootcamp1"))
				.expectNextMatches(capacity -> capacity.getNombre().equals("Bootcamp2")).expectComplete();

		verify(retrieveBootcampUseCase, times(1)).getAllBootcampsPag(0, 2, "nombre", "asc");

	}
}
