package com.myretail.restful.services.products.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.kv.MutationResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.restful.services.products.constants.ProductsConstants;
import com.myretail.restful.services.products.exceptions.ProductsException;
import com.myretail.restful.services.products.models.CurrentPriceModel;
/***
 * 
 * @author Dheeraj
 * 1. Get Request - getproductPriceById() method returns Product price from myRetail bucket
 * 2. Put Request - setPricebyId() method upserts Product price to myRetail bucket
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
	/*
	 * Upsert Product Price for the specified ID into database.
	 */
	public String setPricebyId(String id,CurrentPriceModel currentprice)  {
		try { 
			retailPricingCollection.upsert(id, currentprice);
		}
		catch(Exception e) { 
			e.printStackTrace();
			String Exception = ProductsConstants.DATABASE_CALL_FAILURE;
			throw new ProductsException(Exception.replace("{ID}",id) , e); 
		}
		return "Data successfully upserted into "+ProductsConstants.DB_BUCKET_NAME +" bucket"; 
	}

}
