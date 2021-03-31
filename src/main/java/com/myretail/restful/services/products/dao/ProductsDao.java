package com.myretail.restful.services.products.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.GetResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.restful.services.products.constants.ProductsConstants;
import com.myretail.restful.services.products.exceptions.ProductsException;
import com.myretail.restful.services.products.models.CurrentPriceModel;
import com.myretail.restful.services.products.service.Databean;
/***
 * 
 * @author Dheeraj
 * ProductsDao calls Database instance to capture Product Price
 */

@Component
public class ProductsDao {
	private static final Logger logger = LoggerFactory.getLogger(ProductsDao.class);
	
	@Autowired 
	Collection retailPricingCollection ;
	
	ObjectMapper mapper = new ObjectMapper();

	/*
	 * Returns Product Price for the specified ID from database.
	 */
	public CurrentPriceModel getPricebyId(String id)  {
		CurrentPriceModel price =null ;
		GetResult getResult =null ; 
		try { 
			getResult = retailPricingCollection.get(id);
		}
		catch( Exception e) { 
			e.printStackTrace();
			String Exception = ProductsConstants.DATABASE_CALL_FAILURE;
			throw new ProductsException(Exception.replace("{ID}",id) , e); 
		}
		String content = getResult.contentAsObject().toString() ;
		try {
			price = mapper.readValue(content, CurrentPriceModel.class); 
		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw new ProductsException(ProductsConstants.JSON_RESPONSE_ERROR, e);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new ProductsException(ProductsConstants.JSON_RESPONSE_ERROR, e);
		}
		return price ; 

	}


	
	

}
