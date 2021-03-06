package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.demo.entities.Portfolio;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("h2")
@SpringBootTest
@AutoConfigureMockMvc
public class PortfolioControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetAllPortfolio() throws Exception {

		MvcResult mvcResult = this.mockMvc.perform(get("/portfolio/")).andDo(print()).andExpect(status().isOk())
				.andReturn();

		List<Portfolio> trade = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(),
				new TypeReference<List<Portfolio>>() {
				});

		assertThat(trade.size()).isGreaterThan(0);
	}

}
