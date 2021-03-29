package com.myretail.restful.services.products.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrentPriceModel {
	@JsonProperty("value")
	private Double value;
	@JsonProperty("currency_code")
	private String currencyCode;
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public CurrentPriceModel(Double value, String currencyCode) {
		super();
		this.value = value;
		this.currencyCode = currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	@Override
	public String toString() {
		return "CurrentPrice [value=" + value + ", currencyCode=" + currencyCode + "]";
	}
	public CurrentPriceModel() {
		super();
		// TODO Auto-generated constructor stub
	}
}
