package com.rocha.app.reservas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.rocha.app.commons.model"})
public class SpringbootServicioReservaReadQueueApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioReservaReadQueueApplication.class, args);
	}

}
