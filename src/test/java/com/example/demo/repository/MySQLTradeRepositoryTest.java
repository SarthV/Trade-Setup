package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.entities.Trade;

@ActiveProfiles("h2")
@SpringBootTest
public class MySQLTradeRepositoryTest {

	@Autowired
	TradeRepository repository;

	@Test
	public void testGetAllTrades() {
		// 1. Any setup stuff

		// 2. Call the method under test
		List<Trade> returnedList = repository.findAll();

		// 3 Verify the results
		assertThat(returnedList).isNotEmpty();

		for (Trade trade : returnedList) {
			System.out.println("trade name: " + trade.getStockTicker());
		}
	}
}
