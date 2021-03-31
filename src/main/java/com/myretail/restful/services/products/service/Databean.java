package com.myretail.restful.services.products.service;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.couchbase.client.core.env.TimeoutConfig;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.ClusterOptions;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.env.ClusterEnvironment;
import com.myretail.restful.services.products.constants.ProductsConstants;

/***
 * 
 * @author Dheeraj
 * 1. Connection to Couchbase DB is established
 * 2. Connection to Couchbase Bucket is established
 *
 */
@Service
public class Databean {
	//getBeanPricing
	/*
	 * Establishing Connection to CouchBase DB Cluster
	 */
	private Cluster getCluster() { 
		ClusterEnvironment env = ClusterEnvironment.builder()
				.timeoutConfig(TimeoutConfig.kvTimeout(Duration.ofSeconds(5)))
			    .build();
		Cluster cluster = Cluster.connect(
			    ProductsConstants.LOCAL_HOST,
			    ClusterOptions.clusterOptions(ProductsConstants.DB_USER_NAME,ProductsConstants.DB_PWD)
			        .environment(env));
		return cluster ; 
		
	}
	
	/*
	 * Connecting to CouchBase bucket
	 */
	@Bean
	private Collection getBeanPricing() { 
		Cluster cluster = getCluster(); 
		Bucket bucket = cluster.bucket(ProductsConstants.DB_BUCKET_NAME);
		Collection collection = bucket.defaultCollection();
		return collection ; 	
	}
	

}
