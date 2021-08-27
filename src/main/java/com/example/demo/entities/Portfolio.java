package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "portfolio")
public class Portfolio {

	@Id
	@GeneratedValue
	private int id;
	private String ticker;
	private int volume;
	private int stocksSold;
	private int stocksBought;
	private double avgBuyPrice;
	private double avgSellPrice;
	private String position;

	public Portfolio() {

	}

	public Portfolio(int id, String ticker, int volume, int stocksSold, int stocksBought, double avgBuyPrice,
			double avgSellPrice, String position) {
		super();
		this.id = id;
		this.ticker = ticker;
		this.volume = volume;
		this.stocksSold = stocksSold;
		this.stocksBought = stocksBought;
		this.position = position;
		this.avgBuyPrice = avgBuyPrice;
		this.avgSellPrice = avgSellPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public double getAvgBuyPrice() {
		return avgBuyPrice;
	}

	public void setAvgBuyPrice(double avgBuyPrice) {
		this.avgBuyPrice = avgBuyPrice;
	}

	public double getAvgSellPrice() {
		return avgSellPrice;
	}

	public void setAvgSellPrice(double avgSellPrice) {
		this.avgSellPrice = avgSellPrice;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getStocksSold() {
		return stocksSold;
	}

	public void setStocksSold(int stocksSold) {
		this.stocksSold = stocksSold;
	}

	public int getStocksBought() {
		return stocksBought;
	}

	public void setStocksBought(int stocksBought) {
		this.stocksBought = stocksBought;
	}

}
