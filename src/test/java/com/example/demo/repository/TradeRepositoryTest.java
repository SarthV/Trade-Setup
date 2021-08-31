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
public class TradeRepositoryTest {

	@Autowired
	TradeRepository repo;

	@Test
	public void testGetAllStocks() {
		List<Trade> returnedList = repo.findAll();

		assertThat(returnedList).isNotEmpty();
	}

	@Test
	public void testGetStockById() {
		assertThat(repo.findById(1).get().getStockTicker()).isEqualTo("TATA");
	}

//	@Test
//	public void testAddStock() {
//		int testId = 6;
//		String testTckr = "testStock";
//		double testPrice = 1.00;
//		int testVolume = 100;
//		String testBuyOrSell = "Buy";
//		int testStatusCode = 0;
//		// LocalDateTime testDateTime = LocalDateTime.now();
//
//		Trade testStock = new Trade();
//
//		testStock.setId(testId);
//		testStock.setStockTicker(testTckr);
//		testStock.setBuyOrSell(testBuyOrSell);
//		// testStock.setDateTime(testDateTime);
//		testStock.setPrice(testPrice);
//		testStock.setStatusCode(testStatusCode);
//		testStock.setVolume(testVolume);
//		repo.save(testStock);
//		assertThat(repo.getById(6)).isEqualTo(testStock);
//	}

}