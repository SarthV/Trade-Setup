package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Portfolio;
import com.example.demo.entities.Trade;
import com.example.demo.service.PortfolioService;
import com.example.demo.service.TradeService;

@RestController
@RequestMapping("/api/trades")
public class TradeController {
	@Autowired
	TradeService service;

	@Autowired
	PortfolioService folioService;

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
		try {
			Portfolio existingPortfolio = folioService.getStockbyTix(trade.getStockTicker());
			if (existingPortfolio == null) {
				Portfolio temp = new Portfolio();
				temp.setTicker(trade.getStockTicker());
				temp.setVolume(trade.getVolume());
				if (trade.getBuyOrSell().equals("sell")) {
					temp.setPosition("short");
					temp.setAvgSellPrice(trade.getPrice());
					temp.setStocksSold(trade.getVolume());
				} else {
					temp.setPosition("holding");
					temp.setAvgBuyPrice(trade.getPrice());
					temp.setStocksBought(trade.getVolume());
				}
				folioService.newEntry(temp);
			} else if (trade.getBuyOrSell().equals("sell")) {
				System.out.println("Present in portfolio and it is a SELL order");
				existingPortfolio.setVolume(existingPortfolio.getVolume() - trade.getVolume());
				double avgSellPrice = (existingPortfolio.getAvgSellPrice() * existingPortfolio.getStocksSold()
						+ trade.getPrice() * trade.getVolume())
						/ (existingPortfolio.getStocksSold() + trade.getVolume());
				existingPortfolio.setAvgSellPrice(avgSellPrice);
				existingPortfolio.setStocksSold(existingPortfolio.getStocksSold() + trade.getVolume());
				if (existingPortfolio.getVolume() < 0) {
					existingPortfolio.setPosition("short");
				}
				if (existingPortfolio.getVolume() == 0) {
					existingPortfolio.setPosition("Squared-off");
				}

			} else {
				System.out.println("Present in portfolio and it is an order to BUY");
				existingPortfolio.setVolume(existingPortfolio.getVolume() + trade.getVolume());
				double avgBuyPrice = (existingPortfolio.getAvgBuyPrice() * existingPortfolio.getStocksBought()
						+ trade.getPrice() * trade.getVolume())
						/ (existingPortfolio.getStocksBought() + trade.getVolume());
				existingPortfolio.setAvgBuyPrice(avgBuyPrice);
				existingPortfolio.setStocksBought(existingPortfolio.getStocksBought() + trade.getVolume());
				if (existingPortfolio.getVolume() > 0) {
					existingPortfolio.setPosition("holding");
				}
				if (existingPortfolio.getVolume() == 0) {
					existingPortfolio.setPosition("Squared-off");
				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		service.newTrade(trade);

	}

	@DeleteMapping(value = "/{id}")
	public void deleteTrade(@PathVariable int id) {
		service.deleteTrade(id);
	}

	@PutMapping(value = "/{id}")
	public void editTrade(@PathVariable("id") int id, @RequestBody Trade trade) {
		if (trade.getStatusCode() == 0) {
			service.editFullTrade(id, trade);
		} else {
			System.out.println("Edit not allowed");
			return;
		}

	}

}
