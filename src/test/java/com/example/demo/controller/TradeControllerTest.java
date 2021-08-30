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

import com.example.demo.entities.Trade;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("h2")
@SpringBootTest
@AutoConfigureMockMvc
public class TradeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testShouldReturnListSize3() throws Exception {
		// 1. setup stuff

		// 2. call method under test
		MvcResult mvcResult = this.mockMvc.perform(get("/api/trades")).andDo(print()).andExpect(status().isOk())
				.andReturn();

		// 3. verify the results
		List<Trade> trades = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(),
				new TypeReference<List<Trade>>() {
				});

		assertThat(trades.size()).isGreaterThan(0);
	}

	@Test
	public void testFindByIdSuccess() throws Exception {
		// 1. setup stuff

		// 2. call method under test
		MvcResult mvcResult = this.mockMvc.perform(get("/api/shippers/1")).andDo(print()).andExpect(status().isOk())
				.andReturn();

		// 3. verify the results
		Trade trades = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(),
				new TypeReference<Trade>() {
				});

		assertThat(trades.getId()).isEqualTo(1);
	}

	@Test
	public void testFindByIdFailure() throws Exception {
		// 1. setup stuff

		// 2. call method under test
		this.mockMvc.perform(get("/api/trades/9999")).andDo(print()).andExpect(status().isNotFound()).andReturn();
	}
}
