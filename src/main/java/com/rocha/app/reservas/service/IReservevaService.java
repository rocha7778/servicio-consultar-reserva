package com.rocha.app.reservas.service;

import java.util.List;

import com.rocha.app.commons.model.Reserva;

public interface IReservevaService {
	
	public Reserva findById(long id);
	public List<Reserva> findAll();
	public Reserva save(Reserva reserva);

}
