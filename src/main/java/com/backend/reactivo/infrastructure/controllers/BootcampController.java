package com.backend.reactivo.infrastructure.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.reactivo.application.dto.ResponseBootcamp;
import com.backend.reactivo.application.services.BootcampService;
import com.backend.reactivo.domain.models.Bootcamp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/bootcamps")
public class BootcampController {

	private final BootcampService bootcampService;

	public BootcampController(BootcampService bootcampService) {
		this.bootcampService = bootcampService;
	}
	
	@Operation(summary = "Crear un nuevo Bootcamp", description = "Guarda un nuevo bootcamp en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Bootcamp guardada exitosamente")
    @ApiResponse(responseCode = "406", description = "No se aceptó la solicitud")
	@PostMapping("/guardar")
	public Mono<ResponseEntity<ResponseBootcamp>> crearCapacidad(@RequestBody Bootcamp bootcamp){
    	return bootcampService.createBootcamp(bootcamp)
    			.map(responseBootcamp-> {
    				if (responseBootcamp.isStatus()) {
    					return ResponseEntity.status(HttpStatus.CREATED).body(responseBootcamp);
					}
    				 return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBootcamp);
    			})
    			.defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
	}
	

//    @Operation(summary = "Obtener Capacidad por ID", description = "Recupera una capacidad por su ID.")
//    @ApiResponse(responseCode = "200", description = "Capacidad encontrada")
//    @ApiResponse(responseCode = "404", description = "Capacidad no encontrada")
//	@GetMapping("/obtener/{id}")
//	public Mono<ResponseEntity<Capacidad>> obtenerCapacidad(@PathVariable Long id){
//		return capacidadService.getCapacidad(id)
//				.map(capacidad -> ResponseEntity.ok(capacidad))
//				.defaultIfEmpty(new ResponseEntity<Capacidad>(HttpStatus.NOT_FOUND));
//	}
//	
//    @Operation(summary = "Obtener todas las capacidades", description = "Recupera todas las capacidades disponibles.")
//    @ApiResponse(responseCode = "200", description = "Lista de capacidades")
//	@GetMapping("/obtener")
//	public Flux<Capacidad> obtenerCapacidades(){
//		return capacidadService.getAllCapacidades();
//	}
//    
    @GetMapping("/obtenerPag")
    public Flux<Bootcamp> obtenerBootcampsPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "nombre") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return bootcampService.getAllBootcampsPag(page, size, sortBy,direction);
    }
}
