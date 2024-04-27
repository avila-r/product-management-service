# 🌱 Product Management Service, a RESTful API built-in Spring Boot.

'Product Management Service' is a W.I.P platform where authenticated users can insert, access and manipulate products. This API authenticate users by validating username and password, and generate JWT token using secret key.

**WARNING:** This is a W.I.P project, many features may not be configured or available. This description is being dynamically updated and may offer not yer developed services/endpoints.

## Features

* Spring-Boot Application
* Spring Data JPA for data persistence
* Spring Security for Web Security configuration
* JWT Authorization
* PostgreSQL as relational database 
* Docker and Docker-Compose configuration 

## Summary

- [Requirements](#requirements)
- [Testing](#testing)

## Requirements
The application needs to run as a multi-container application using **Docker Engine** (for Linux) or **Docker Desktop** (for MacOS & Windows)

## Testing
Make sure the Docker and Docker-Compose are locally configured, then you can run the multi-container application on your local machine.

### Clone Repository
```bash
$ git clone https://github.com/avila-r/product-management-service && cd product-management-service
```

### Run Docker Compose

First build the image:
```bash
$ docker compose up --build --force-recreate
```

Then your local machine will build all images. 

The commerce-api container (RESTful API) will run by default on port `8080`;
The commerce-database container (PostgreSQL RDBMS) needs to run on port `5432`;

Configure the port by changing in __docker-compose.yml__, then modify __application.properties__ at Spring application.

### Products endpoint
The application has an product management endpoint `/product` that will accept requests to create, read, update and delete products.

 Insert products using cURL requests:
 ```bash
curl -X POST \
  http://localhost:8080/products \
  -H 'Content-type: application/json' \
  -d '{
    "name": "<Product Name>",
    "category": "<Product Category>",
    "description": "<Product Description>",
    "price": int<Product Value>,
    "stock": int<Product Quantity>
  }'
```

 Get all products using cURL request:
 ```bash
curl http://localhost:8080/product
```
 Get product by id using cURL request:
 ```bash
curl http://localhost:8080/product/{id}
```

If everything is working as expected, the request should return your products.