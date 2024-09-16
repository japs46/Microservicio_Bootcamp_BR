package com.backend.reactivo.domain.models;

public class Tecnologia {

	private final Long id;
	
	private final String nombre;
	
	public Tecnologia(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	
	@Override
	public String toString() {
		return "Tecnologia{" +
	               "id=" + id +
	               ", nombre='" + nombre + '\'' +
	               '}';
	}
	
}
