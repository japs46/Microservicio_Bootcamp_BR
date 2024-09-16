package com.backend.reactivo.application.dto;

import com.backend.reactivo.domain.models.Bootcamp;

public class ResponseBootcamp {

	private String mensaje;
	
	private Bootcamp bootcamp;
	
	private boolean status;

	public ResponseBootcamp(String mensaje, Bootcamp bootcamp, boolean status) {
		this.mensaje = mensaje;
		this.bootcamp = bootcamp;
		this.status = status;
	}

	public String getMensaje() {
		return mensaje;
	}

	public Bootcamp getBootcamp() {
		return bootcamp;
	}

	public boolean isStatus() {
		return status;
	}
	
}
