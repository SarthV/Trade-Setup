package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Trade;
import com.example.demo.repository.TradeRepository;

@Transactional
@Service
public class TradeService {

	@Autowired
	private TradeRepository repository;

	public List<Trade> getAllTrades() {
		return repository.findAll();
	}

	public Trade getTrade(int id) {
		return repository.findById(id).get();
	}

	public void newTrade(Trade trade) {
		repository.save(trade);

	}

	public void deleteTrade(int id) {
		try {
			Trade trade = getTrade(id);
			if (trade != null) {
				repository.deleteById(id);
			} else {
				System.out.println("No trade in here");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void editFullTrade(int id, Trade trade) {
		Trade existingTrade = getTrade(id);
		existingTrade.setBuyOrSell(trade.getBuyOrSell());
		existingTrade.setVolume(trade.getVolume());
		existingTrade.setPrice(trade.getPrice());
		existingTrade.setStatusCode(trade.getStatusCode());
	}

	public void editVolume(int newVol, int id) {
		Trade existingTrade = getTrade(id);
		existingTrade.setVolume(newVol);
	}

}
