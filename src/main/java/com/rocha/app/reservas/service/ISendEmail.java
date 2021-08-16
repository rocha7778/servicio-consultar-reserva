package com.rocha.app.reservas.service;

public interface ISendEmail {
	public void senEmail(String from, String to,Long noreserva) throws Exception;

}
