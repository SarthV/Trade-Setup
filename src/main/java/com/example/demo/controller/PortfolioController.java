package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Portfolio;
import com.example.demo.service.PortfolioService;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

	@Autowired
	PortfolioService service;

	@GetMapping(value = "/")
	public List<Portfolio> getPortfolio() {
		return service.getFullPortfolio();
	}

}
