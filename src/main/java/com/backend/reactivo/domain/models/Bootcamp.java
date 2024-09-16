package com.backend.reactivo.domain.models;

import java.util.List;

public class Bootcamp {
	
	private final Long id;
	
	private final String nombre;
	
	private final String descripcion;
	
	private final List<Capacidad> listaCapacidad;

	public Bootcamp(Long id, String nombre, String descripcion, List<Capacidad> listaCapacidad) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.listaCapacidad = listaCapacidad;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public List<Capacidad> getListaCapacidad() {
		return listaCapacidad;
	}

	@Override
    public String toString() {
        return "Bootcamp{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
	
}
