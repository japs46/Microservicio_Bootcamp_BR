package com.backend.reactivo.domain.models;

public class BootcampCapacidad {

	private final Long id;
	
	private final Long bootcamp_id;
	
	private final Long capacidad_id;

	public BootcampCapacidad(Long id, Long bootcamp_id, Long capacidad_id) {
		this.id = id;
		this.bootcamp_id = bootcamp_id;
		this.capacidad_id = capacidad_id;
	}

	public Long getId() {
		return id;
	}

	public Long getBootcamp_id() {
		return bootcamp_id;
	}

	public Long getCapacidad_id() {
		return capacidad_id;
	}

}
