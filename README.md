# 🌱 Product Management Service, a RESTful API built-in Spring Boot.

'Product Management Service' is a platform where authenticated users can insert, access and manipulate products. This API authenticate users by validating username and password, and generate JWT token using secret key.

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

### Authentication endpoints
The application has an authorization filter, and you need to register your user at endpoint `/auth/register`, that will accept POST requests. You need to define ADMIN role if want to be able to post, update and delete products. If you define "USER" role, you'll be able just to get products data.

**Register users:**
```bash
{
 "role": "ADMIN",
 "login": "avila.dev",
 "password": "12345"
}
```

If everything is working as expected, the request should return your account data:

```bash
{
  "id": 1,
  "role": "ADMIN",
  "login": "avila.dev",
  "password": "$2a$10$H5l1v/FgiuX4XraMQEllruWQrv5oH1oRh1KjbC/ESiBkbPY9xRmHK",
  "enabled": true,
  "authorities": [
  {
    "authority": "ROLE_ADMIN"
  },
  {
    "authority": "ROLE_USER"
  }
  ],
  "username": "avila.dev",
  "accountNonExpired": true,
  "accountNonLocked": true,
  "credentialsNonExpired": true
}
```

Now you can access the application if logged-in. For this, use `/auth/login` endpoint, that will accept POST requests.

**Login:**
```bash
{
  "login": "avila.dev",
  "password": "12345"
}
```

Now, you'll receive a generated JSON Web Token (JWT) within your authentication data as response, that needs to be included at HTTP requests if you want to be authenticated.

### Products endpoints
At `commerce/product` endpoint, you'll be able to manage products, including sample operations as create, read, update and delete.

> [!NOTE]
> This application has JWT-based authentication. You need to include a Bearer header with your token if want to use any feature here.

**Insert product**
```bash
# Admin
POST `http://localhost:8080/commerce/product/insert`
  {
    # Name must be unique
    "name": "Product Name",
    "category": "Product Category", 
    "description": "This product was inserted as test-purpose!",
    "price": 89.99, 
    "stock": 5
  }
```

**Update product**
```bash
# Admin
POST `http://localhost:8080/commerce/product/update`
  {
    "name": "Product 2 Name",
    "category": "Product Category",
    "description": "This product was inserted as test-purpose! Now received an update!",
    "price": 29.99,
    "stock": 3
  }
```

**Delete product**
```bash
# Admin
POST `http://localhost:8080/commerce/product/delete`
  {
    "name": "Product 2 Name",
    "category": "Product Category",
    "description": "This product was inserted as test-purpose! Now received an update!",
    "price": 29.99,
    "stock": 3
  }
```

**List products**
```bash
# Admin or User
GET `http://localhost:8080/commerce/product`
```

**List products by category**
```bash
# Admin or User
GET `http://localhost:8080/commerce/product/category/{category}`
GET `http://localhost:8080/commerce/product/category/Product%20Category`

GET `http://localhost:8080/commerce/product/category?category={category}`
GET `http://localhost:8080/commerce/product/category?category=Product%20Category`
```    

**Get product by id**
```bash
# Admin or User
GET `http://localhost:8080/commerce/product/id/{id}`
GET `http://localhost:8080/commerce/product/id?id={id}`
```