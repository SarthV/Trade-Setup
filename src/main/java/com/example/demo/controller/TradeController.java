package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Trade;
import com.example.demo.service.TradeService;

@RestController
@RequestMapping("/api/trades")
public class TradeController {
	@Autowired
	TradeService service;

	@GetMapping(value = "/")
	public List<Trade> getAllTrades() {
		return service.getAllTrades();
	}

	@GetMapping(value = "/{id}")
	public Trade getTradeById(@PathVariable("id") int id) {
		return service.getTrade(id);
	}

	@PostMapping(value = "/")
	public void addTrade(@RequestBody Trade trade) {
		service.newTrade(trade);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteTrade(@PathVariable int id) {
		service.deleteTrade(id);
	}

}
