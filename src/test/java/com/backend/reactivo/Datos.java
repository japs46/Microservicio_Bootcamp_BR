package com.backend.reactivo;

import java.util.List;

import com.backend.reactivo.domain.models.Bootcamp;
import com.backend.reactivo.domain.models.Capacidad;
import com.backend.reactivo.domain.models.Tecnologia;

import reactor.core.publisher.Flux;

public class Datos {
	
	public static Flux<Bootcamp> FLUX_BOOTCAMP(){
		return Flux.just(new Bootcamp(1L, "Bootcamp1", "descripcion b1",LISTA_CAPACIDAD_1()),
				new Bootcamp(2L,"Bootcamp2","descripcion b2",LISTA_CAPACIDAD_1()),
				new Bootcamp(3L,"Bootcamp3","descripcion b3",LISTA_CAPACIDAD_2()),
				new Bootcamp(4L,"Bootcamp4","desxripcion b4",LISTA_CAPACIDAD_2()),
				new Bootcamp(5L,"Bootcamp5","descripcion b5",LISTA_CAPACIDAD_1()));
	}
	
	public static List<Capacidad> LISTA_CAPACIDAD_1(){
		return List.of(new Capacidad(1L, "Capacidad1",LISTA_TECNOLOGIA_1()),
				new Capacidad(2L,"Capacidad2",LISTA_TECNOLOGIA_2()),
				new Capacidad(3L,"Capacidad3",LISTA_TECNOLOGIA_3()));
	}
	
	public static List<Capacidad> LISTA_CAPACIDAD_2(){
		return List.of(new Capacidad(4L,"Capacidad4",LISTA_TECNOLOGIA_1()),
				new Capacidad(5L,"Capacidad5",LISTA_TECNOLOGIA_2()),
				new Capacidad(6L,"Capacidad6",LISTA_TECNOLOGIA_3()));
	}
	
	public static List<Tecnologia> LISTA_TECNOLOGIA_1(){
		return List.of(new Tecnologia(1L, "Java"),
				new Tecnologia(2L,"Python"));
	}
	
	public static List<Tecnologia> LISTA_TECNOLOGIA_2(){
		return List.of(new Tecnologia(3L,"JavaScript"),
				new Tecnologia(4L,"Postman"));
	}
	
	public static List<Tecnologia> LISTA_TECNOLOGIA_3(){
		return List.of(new Tecnologia(5L,"SQL"),
				new Tecnologia(6L,"NoSQL"));
	}
	
	
}
