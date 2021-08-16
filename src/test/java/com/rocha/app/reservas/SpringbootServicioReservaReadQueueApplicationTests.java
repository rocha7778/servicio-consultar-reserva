package com.rocha.app.reservas;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class SpringbootServicioReservaReadQueueApplicationTests {
	
	private static final String BASE_URL_API = "/reservas";
	
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}


	@Test
	void contextLoads() {
	}
	
	@Test
	void findAll() throws Exception {
		this.mockMvc.perform(get(BASE_URL_API)).andExpect(status().isOk());
	}
	
	@Test
	void findOne() throws Exception {
		this.mockMvc.perform(get(BASE_URL_API+"/13"))
		.andExpect(status().isOk());
	}
	
	@Test
	void notFound() throws Exception {
		this.mockMvc.perform(get(BASE_URL_API+"/0"))
		.andExpect(status().is4xxClientError());
	}
	

	

}
