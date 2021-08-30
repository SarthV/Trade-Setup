package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.entities.Trade;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.TradeRepository;

@ActiveProfiles("h2")
@SpringBootTest
public class ServiceTest {

	@Autowired
	TradeService service;

	@MockBean
	TradeRepository repository;

	@Test
	public void testGetTrade() {
		// 1. Setup stuff
		// create a shipper record for testing
		int testId = 5;
		String testName = "testTrade";
		Trade testTrade = new Trade();
		testTrade.setId(testId);
		testTrade.setBuyOrSell("buy");
		testTrade.setPrice(1000);
		testTrade.setStockTicker("TATA");
		testTrade.setStatusCode(0);
		testTrade.setVolume(50);

		// tell the mock object what to do when
		// its getShipperById method is called
		when(repository.getById(testTrade.getId())).thenReturn(testTrade);

		// 2. call class under test
		// call the getShipper method on the service, this will call
		// the getShipperById method on the the mock repository
		// the mock repository returns the testShipper
		// the service should return the same testShipper here
		// we verify this happens, so we know the service is behaving as expected
		Trade returnedTrade = service.getTrade(testId);

		// 3. verify response
		assertThat(returnedTrade).isEqualTo(testTrade);
	}

	@Test
	public void testGetShipperNotFound() {
		// 1. Setup stuff
		int testId = 999;

		// tell the mock object what to do when
		// its getShipperById method is called
		// in this case it needs to throw an exception
		when(repository.getById(testId)).thenThrow(new ResourceNotFoundException());

		// 2. call class under test & 3. verify the results
		// call the getShipper method on the service, this will call
		// the getShipperById method on the the mock repository
		// the mock repository throws an exception
		// the service doesn't catch that exception
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.getTrade(testId);
		});
	}

}
