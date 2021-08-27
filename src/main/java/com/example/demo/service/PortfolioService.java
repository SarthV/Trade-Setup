package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Portfolio;
import com.example.demo.repository.PortfolioRepository;

@Transactional
@Service
public class PortfolioService {

	@Autowired
	PortfolioRepository repo;

	public List<Portfolio> getFullPortfolio() {
		return repo.findAll();
	}

	public void newEntry(Portfolio folio) {
		repo.save(folio);
	}

	public Portfolio getStockbyTix(String ticker) {
		return repo.findByTicker(ticker);
	}

}
