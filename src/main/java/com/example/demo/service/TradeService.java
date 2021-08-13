package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Trade;
import com.example.demo.repository.TradeRepository;

@Service
public class TradeService {

	@Autowired
	private TradeRepository repository;

	public List<Trade> getAllTrades() {
		return repository.findAll();
	}

	public Trade getTrade(int id) {
		return repository.getById(id);
	}

	public void newTrade(Trade trade) {
		repository.save(trade);
	}

	public void deleteTrade(int id) {
		repository.deleteById(id);
		;
	}

}
