package com.backend.reactivo.domain.models;

import java.util.List;

public class Capacidad {

	private final Long id;
	
	private final String nombre;
	
	private List<Tecnologia> listaTecnologias;
	
	public Capacidad(Long id, String nombre, List<Tecnologia> listaTecnologias) {
		this.id = id;
		this.nombre = nombre;
		this.listaTecnologias = listaTecnologias;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public List<Tecnologia> getListaTecnologias() {
		return listaTecnologias;
	}

	@Override
	public String toString() {
		return "Capacidad{" +
	               "id=" + id +
	               ", nombre='" + nombre + '\'' +
	               '}';
	}
	
}
