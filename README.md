# myRetail Restful Service

This is my template for creating myRetail Restful Service project which uses Spring Boot and Couchbase.

## Architecture
1. Controller: is the presentation layer where the end points are located
2. Service: is the service layer where the business logic resides

## Technologies 
1. Spring Boot(spring-boot-starter-web,spring-boot-devtools,spring-boot-starter-test)
2. Couchbase 6.6.0
3. Java 11
4. Maven

## Couchbase Setup

### Install and launch Couchbase
With your project set up, you can install and launch Couchbase.

For whatever operating system you are using, instructions and downloads can be found at http://developer.couchbase.com/documentation/server/current/install/install-intro.html.

After you install Couchbase, launch it. You should see a webpage opening in your default browser allowing you to setup Couchbase. 

### Dependencies
The following code will rely on Spring Boot and the Couchbase Java SDK so make sure you add the correct dependency:

```Maven
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter</artifactId>
	</dependency>
	<dependency>
		<groupId>com.couchbase.client</groupId>
		<artifactId>java-client</artifactId>
	</dependency>
</dependencies>
```

## Exposed End Points - 
Get user by id  
```
http://localhost:8081/products/{id}
```

## Couchbase Buckets
* Create bucket retailPrice
* Add data to bucket

```
INSERT INTO `retailPricing` (KEY, VALUE)
VALUES ("12954218", {     "value": 0.99,     "currency_code": "USD"   })

INSERT INTO `retailPricing` (KEY, VALUE)
VALUES ("13264003", {     "value": 5.99,     "currency_code": "USD"   })

INSERT INTO `retailPricing` (KEY, VALUE)
VALUES ("13860428", {     "value": 7.99,     "currency_code": "USD"   })

INSERT INTO `retailPricing` (KEY, VALUE)
VALUES ("54456119", {     "value": 5.99,     "currency_code": "USD"   })
```


## Postman Calls
```Postman
curl --location --request GET 'http://localhost:8081/products/13860428' --data-raw ''

curl --location --request GET 'http://localhost:8081/products/54456119' --data-raw ''

curl --location --request GET 'http://localhost:8081/products/13264003' --data-raw ''

curl --location --request GET 'http://localhost:8081/products/12954218' --data-raw ''
```



