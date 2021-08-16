package com.rocha.app.reservas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.rocha.app.reservas.exceptions.ResourceNotFoundException;
import com.rocha.app.commons.model.Reserva;
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
	
	
	@SqsListener("https://sqs.us-east-1.amazonaws.com/585780553964/sprint-boot-sqs")
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
	
	
	@GetMapping("/reservas")
	public List<Reserva> reservas(){
		return  reservaService.findAll();
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
