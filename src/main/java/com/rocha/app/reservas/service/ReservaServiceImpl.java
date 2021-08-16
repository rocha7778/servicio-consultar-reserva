package com.rocha.app.reservas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rocha.app.commons.model.Reserva;
import com.rocha.app.reservas.repository.ReservaRepository;

@Service
public class ReservaServiceImpl implements IReservevaService {

	@Autowired
	private ReservaRepository reservaRepositorty;
	
	@Override
	@Transactional(readOnly = true)
	public Reserva findById(long id) {
		return  reservaRepositorty.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Reserva> findAll() {
		return (List<Reserva>) reservaRepositorty.findAll();
	}

	@Override
	public Reserva save(Reserva reserva) {
		return reservaRepositorty.save(reserva);
	}

}
