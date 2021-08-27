package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Portfolio;

public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {
	public Portfolio findByTicker(String ticker);
}
