package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entities.Portfolio;
import com.example.demo.service.PortfolioService;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

	@Autowired
	PortfolioService service;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = "/")
	public List<Portfolio> getPortfolio() {
		return service.getFullPortfolio();
	}

	@RequestMapping(value = "/exchange/{ticker}", method = RequestMethod.GET)
	public String getInfo(@PathVariable("ticker") String ticker) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate
				.exchange("http://localhost:8090/fundamentals/" + ticker, HttpMethod.GET, entity, String.class)
				.getBody();
	}

}
