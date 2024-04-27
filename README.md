# Capitole Challenge - Retail Price Search
====================

## Service Execution
--------------------------------

### Step 1:
In a terminal, run the service with:
- Linux: `./gradlew bootRun`
- Windows: `gradlew bootRun`

You should be able to access the healthcheck endpoint at `http://localhost:8080/api/healthZ`

## Bruno
A collection of one endpoint is provided. Bruno is an alternative to Postman after forcing users into an online-only environment.

## Jmeter - Smoke Tests - E2E Test
A collection of three end to end tests are provided. 

### Query

This endpoint will return a price listing based on:
- brand_id: Long
- product_id: Integer
- application_date: DateTime format (YYYY-MM-DDTHH:MM:SS)

It will return a price listing of highest priority for the specified date based on the existing listings on the database. Should such an entry not exist, it will return 404 with a brief description of "Product not found" and the provided query. The arguments are provided as an URL parameter 

The data provided in the challenge will be populated automatically upon service start (`data.sql` file is provided)

### Notes

This project has been built upon Java 21 however it should be compatible with Java 17. The project is domain-driven, module-segregated, and CQRS. I realized later in development that it would've been more clear naming the package 'query' instead of 'get'.