package com.backend.reactivo.infrastructure.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("capacidad")
public class CapacidadEntity {
	
	@Id
	private final Long id;
	
	private final String nombre;
	
	private final String descripcion;
	
	public CapacidadEntity(Long id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
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
	
	@Override
	public String toString() {
		return "CapacidadEntity{" +
	               "id=" + id +
	               ", nombre='" + nombre + '\'' +
	               ", descripcion='" + descripcion + '\'' +
	               '}';
	}
}
