package com.myretail.restful.services.products.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductModel {
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("current_price")
	private CurrentPriceModel currentPrice;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CurrentPriceModel getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(CurrentPriceModel currentPrice) {
		this.currentPrice = currentPrice;
	}
	@Override
	public String toString() {
		return "ProductModel [id=" + id + ", name=" + name + ", currentPrice=" + currentPrice + "]";
	}
	public ProductModel(String id, String name, CurrentPriceModel currentPrice) {
		super();
		this.id = id;
		this.name = name;
		this.currentPrice = currentPrice;
	}
	
	public ProductModel(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public ProductModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
