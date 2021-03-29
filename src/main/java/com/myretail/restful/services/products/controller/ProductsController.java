package com.myretail.restful.services.products.controller;

import java.awt.PageAttributes.MediaType;
import java.net.http.HttpHeaders;
import java.util.Arrays;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.myretail.restful.services.products.constants.ProductsConstants;
import com.myretail.restful.services.products.exceptions.ProductsException;
import com.myretail.restful.services.products.models.CurrentPriceModel;
import com.myretail.restful.services.products.models.GenericResponse;
import com.myretail.restful.services.products.models.ProductModel;
import com.myretail.restful.services.products.service.ProductsDataService;
/***
 * 
 * @author ramya
 *
 */
@Controller
public class ProductsController {
	private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);
	
	@Autowired
	ProductsDataService productsService;

	@RequestMapping("/products/{id}")
	@ResponseBody
	public ResponseEntity<?>  productController(@PathVariable("id") String id)  {
		ProductModel productNameById =null;
		
		try {
			/*
			 * Returns Product Price for the specified ID from database.
			 */
			CurrentPriceModel priceById = productsService.getproductPriceById(id); 
			logger.info("Current price of ID:"+id +" is "+ priceById);
			
			/*
			 * Returns ProductID, Product Name for the specified ID from External API.
			 */
			productNameById = productsService.getproductNameById(id);
			logger.info("Product name of ID: "+id+" is "+ productNameById);
			
			/*
			 * Setting Current Price retrieved from Database to Product Data. 
			 */
			productNameById.setCurrentPrice(priceById);
			logger.info("Product contents of ID: "+id+" is "+ productNameById);
			
		}catch(ProductsException e) { 
			e.printStackTrace();
			GenericResponse resp = new GenericResponse() ;
			resp.setMessage(ProductsConstants.API_CALL_FAILURE);
			resp.setReason(e.getMessage());
			return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
			
		}
		catch(Exception e) { 
			e.printStackTrace();
			GenericResponse resp = new GenericResponse() ;
			resp.setMessage(ProductsConstants.API_CALL_FAILURE);
			resp.setReason(e.getMessage());
			return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		 return new ResponseEntity<>(productNameById, HttpStatus.OK);
		 
		
	}
	
		


}
