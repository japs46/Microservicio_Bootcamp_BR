package com.backend.reactivo.infrastructure.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("bootcamp")
public class BootcampEntity {

	@Id
	private final Long id;

	private final String nombre;

	private final String descripcion;
	
//	private final List<Tecnologia> listaTecnologia;

	public BootcampEntity(Long id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
//		this.listaTecnologia = listaTecnologia;
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
	
//	public List<Tecnologia> getListaTecnologia() {
//		return listaTecnologia;
//	}

	@Override
    public String toString() {
        return "BootcampEntity{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
