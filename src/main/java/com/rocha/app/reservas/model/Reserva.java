package com.rocha.app.reservas.model;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reserva")

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1940117426283265687L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="EST")
	private Date fechaIngreso;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="EST")
	private Date fechaSalida;
	private int noPersonas;
	private String titularReserva;
	private int noHabitaciones;
	private int noMenores;
	private String email;
	@JsonInclude()
	@Transient
	private long totalDias;
	
	
	public long getTotalDias() {
		
		long diff = fechaSalida.getTime() - fechaIngreso.getTime();
		totalDias =  TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		return totalDias;
	   
	}
	
	
}
