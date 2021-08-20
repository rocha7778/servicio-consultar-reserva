package com.rocha.app.reservas.controller;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rocha.app.commons.model.Reserva;
import com.rocha.app.reservas.exceptions.ResourceNotFoundException;
import com.rocha.app.reservas.service.IReservevaService;
import com.rocha.app.reservas.service.ISendEmail;

@RestController
public class ReservaController {
	
	@Value("${cloud.aws.end-point.uri}")
	private String sqsEndpoint;
	
	@Autowired
	private ISendEmail sendEmail;
	
	@Autowired
	private IReservevaService reservaService;
	
	
	@SqsListener("${cloud.aws.end-point.uri}")
	public void getProductoFromSQS(String snsMessageJsonFormat) {
		Gson gson = new Gson();
		Reserva reserva = gson.fromJson(snsMessageJsonFormat, Reserva.class);
		reserva = reservaService.save(reserva);
		String from ="rocha7778@hotmail.com";
		String to = reserva.getEmail();
		try {
			sendEmail.senEmail(from,to,reserva.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@HystrixCommand(fallbackMethod = "reservasAlternativa")
	@GetMapping("/reservas")
	public List<Reserva> reservas(){
		return  reservaService.findAll();
	}
	
	
	public List<Reserva> reservasAlternativa(){
		
		List<Reserva> listReserva = new ArrayList<>();
		Reserva r = new Reserva();
		
		r.setEmail("rocha7778@hotmail.com");
		r.setId(Long.valueOf("1"));
		r.setNoHabitaciones(1);
		r.setNoMenores(3);
		r.setNoPersonas(5);
		r.setTitularReserva("Rocha");
		r.setTotalDias(10);
		r.setFechaIngreso(LocalDate.parse("2021-01-01").toDate());
		r.setFechaSalida(LocalDate.parse("2021-01-30").toDate());
		listReserva.add(r);
		return  listReserva;
	}
	
	
	@GetMapping("/reservas/{id}")
	public Reserva reserva(@PathVariable long id) throws ResourceNotFoundException {
		Reserva  reserva = reservaService.findById(id);
		if(reserva==null) {
			 throw new ResourceNotFoundException("No se encontró la reserva con el codigo: " + id);
		}
		
		return reserva;
	}
}
