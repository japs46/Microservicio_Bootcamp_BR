package com.backend.reactivo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.backend.reactivo.application.dto.ResponseBootcamp;
import com.backend.reactivo.application.services.BootcampService;
import com.backend.reactivo.domain.models.Bootcamp;
import com.backend.reactivo.domain.models.Capacidad;
import com.backend.reactivo.infrastructure.controllers.BootcampController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebFluxTest(BootcampController.class)
public class BootcampControllerTest {

	@Autowired 
	private WebTestClient webTestClient;
	
	@MockBean
	private BootcampService bootcampService;
	
	@Test
	public void crearBootcampOkTest() {
		List<Capacidad> listaCapacidad = Datos.LISTA_CAPACIDAD_1();
		
		Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp1", "descripcion bootcamp1", listaCapacidad);
		Mono<ResponseBootcamp> monoBootcamp= Mono.just(new ResponseBootcamp("se registro bootcamp", bootcamp, true));
		when(bootcampService.createBootcamp(any())).thenReturn(monoBootcamp);
		
		webTestClient.post().uri("/api/bootcamps/guardar").bodyValue(bootcamp)
		.exchange()
		.expectStatus().isCreated()
		.expectBody(ResponseBootcamp.class)
		.consumeWith(response ->{
			ResponseBootcamp responseBoot = response.getResponseBody();
			assertNotNull(responseBoot);
			 assertEquals(true, responseBoot.isStatus());
	        assertEquals("se registro bootcamp", responseBoot.getMensaje());
	        assertEquals(1L, responseBoot.getBootcamp().getId());
	        assertEquals(3,responseBoot.getBootcamp().getListaCapacidad().size());
		});
		
		 verify(bootcampService,times(1)).createBootcamp(any());
	}
	
	@Test
	public void crearBootcampSinCapacidadTest() {
		Bootcamp bootcamp = new Bootcamp(2L, "Bootcamp1", "descripcion c", null);
		Mono<ResponseBootcamp> monoBootcamp= Mono.just(new ResponseBootcamp("debe asociar capacidades", bootcamp, false));
		when(bootcampService.createBootcamp(any())).thenReturn(monoBootcamp);
		
		webTestClient.post().uri("/api/bootcamps/guardar").bodyValue(bootcamp)
		.exchange()
		.expectStatus().isAccepted()
		.expectBody()
		.jsonPath("status").isEqualTo(false)
		.jsonPath("mensaje").isEqualTo("debe asociar capacidades")
		.jsonPath("bootcamp.listaCapacidad").isEmpty();
		
		 verify(bootcampService,times(1)).createBootcamp(any());
	}
	
	@Test
	public void obtenerListadoTecnologiasPaginadas() {
		Flux<Bootcamp> fluxBootcamp= Datos.FLUX_BOOTCAMP();
		
		Flux<Bootcamp> test1 = fluxBootcamp.filter(bootcamp -> bootcamp.getId() == 1 || bootcamp.getId() == 2);
		
		when(bootcampService.getAllBootcampsPag(0, 2, "nombre","asc")).thenReturn(test1);
		
		webTestClient.get().uri("/api/bootcamps/obtenerPag?page=0&size=2&sortBy=nombre&direction=asc")
	    .exchange()
	    .expectStatus().isOk()
	    .expectBodyList(Bootcamp.class)
	    .consumeWith(response -> {
	        List<Bootcamp> bootcamps = response.getResponseBody();
	        assertNotNull(bootcamps);
	        assertFalse(bootcamps.isEmpty());
	        assertEquals(2,bootcamps.size());
	        assertEquals("Bootcamp1", bootcamps.get(0).getNombre());
	        assertEquals(1L, bootcamps.get(0).getId());
	        assertEquals(3, bootcamps.get(0).getListaCapacidad().size());
	        assertEquals(2, bootcamps.get(0).getListaCapacidad().get(0).getListaTecnologias().size());
	        assertEquals("Java", bootcamps.get(0).getListaCapacidad().get(0).getListaTecnologias().get(0).getNombre());
	    });
		
		verify(bootcampService,times(1)).getAllBootcampsPag(0, 2, "nombre","asc");
	}
	
}
