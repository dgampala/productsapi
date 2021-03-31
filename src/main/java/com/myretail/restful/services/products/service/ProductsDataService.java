package com.myretail.restful.services.products.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.myretail.restful.services.products.dao.ProductsDao;
import com.myretail.restful.services.products.models.CurrentPriceModel;
import com.myretail.restful.services.products.models.ProductModel;
/***
 * 
 * @author Dheeraj
 * 1. ProductsDataService holds the methods which intern calls methods which 
 * a. Captures Product Price
 * b. Captures Product Name
 *
 */

@Component
public class ProductsDataService {
	private static final Logger logger = LoggerFactory.getLogger(ProductsDataService.class);

	@Autowired
	ProductsDao dao ; 
	
	@Autowired
	ProductNameWebService webservice ; 
	
	/*
	 * Returns Product Price for the specified ID from database.
	 */
	public CurrentPriceModel getproductPriceById(String id)  {
		CurrentPriceModel pricebyId = dao.getPricebyId(id);
		return pricebyId ; 
	}
	
	/*
	 * Returns ProductID, Product Name for the specified ID from External API.
	 */
	public ProductModel getproductNameById(String id) { 
		ProductModel product = webservice.getIdExternal(id); 
		return product ; 
	}
	


	

}
