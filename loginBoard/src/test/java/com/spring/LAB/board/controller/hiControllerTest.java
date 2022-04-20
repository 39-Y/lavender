package com.spring.LAB.board.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = hiController.class)
public class hiControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	void hiTest() throws Exception{
		String hi = "hi";
		
		mvc.perform(get("/test/hi"))
				.andExpect(status().isOk())
				.andExpect(content().string(hi));
	}
 
	@Test
	void hiTest2() throws Exception{
		String id = "test1";
		String pw = "1234";
		mvc.perform(get("/test/hi2"))
				.param("id", id)
				.param("pw," pw)
				.andExpect(status().isOk())
				.andExpect(content().string(hi));
	}
}
