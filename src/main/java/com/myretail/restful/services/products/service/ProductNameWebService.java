package com.myretail.restful.services.products.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.restful.services.products.constants.ProductsConstants;
import com.myretail.restful.services.products.exceptions.ProductsException;
import com.myretail.restful.services.products.models.ProductModel;

 /**
  * 
  * @author Dheeraj
  * 1. getIdExternal() method takes in ID variable from the URI, substitutes {id} value in External URI
  * 2. Modified External URI is called to caputure Product Name
  *
  */

@Component
public class ProductNameWebService {
	private static final Logger logger = LoggerFactory.getLogger(ProductsDataService.class);
	
	public String Uri ="https://redsky.target.com/v3/pdp/tcin/{ID}?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics&key=candidate" ; 
 
	public ProductModel getIdExternal(String id)  {
		Map<String, String> values = new HashMap<>();
	    String productName=null ; 
		ProductModel product = null ;
		values.put("ID", id );
		/*
		 * Substituting {ID} with value in URI
		 */
		String UriNew = StringSubstitutor.replace(Uri, values, "{", "}");
		//String UriNew = Uri.replace("{ID}", id);
		LoggerFactory.getLogger(ProductsDataService.class).info("Calling External URI: "+UriNew);
		
		/*
		 * Using RestTemplate to call an External URI which returns Response Entity
		 */
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response= restTemplate.getForEntity(UriNew, String.class);
		
		/*
		 * Check for Response Status Code if Response not 200 raise Exception
		 */
		if( response.getStatusCode()!= HttpStatus.OK ) { 
			throw new ProductsException(ProductsConstants.REMOTE_API_CALL_FAILED); // App is not raising this exception
		}else
		{ 
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = null;
			try {
				root = mapper.readTree(response.getBody());
				productName = root.get("product").findValue("item").findValue("product_description").findValue("title").asText();
			} catch (JsonMappingException e) {
				e.printStackTrace();
				throw new ProductsException(ProductsConstants.JSON_RESPONSE_ERROR, e);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				throw new ProductsException(ProductsConstants.JSON_RESPONSE_ERROR, e);
			}catch( Exception e) {
				e.printStackTrace();
				throw new ProductsException(ProductsConstants.JSON_RESPONSE_ERROR, e);
			}
			 
		}
		
		if( null != productName ) { 
			product = new ProductModel(id , productName ) ; 
		}else { 
			throw new ProductsException(ProductsConstants.JSON_RESPONSE_ERROR);
		}
		
		return product ; 
		
	}

}
