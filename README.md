# DowJones
Upload a bulk data


# Dow Jones Stock Record Manager

A Spring Boot application to manage and query weekly stock records from the Dow Jones Index dataset.

## Features
- Upload bulk datasets.
- Query records by stock ticker.
- Add a new stock record.

## Technologies
- Spring Boot
- h2-console
- Swagger

## Running the Application
1. Set up h2-console and update `application.properties`.
2. Run the application with `mvn spring-boot:run`.
3. Access Swagger at `http://localhost:8080/swagger-ui/`.

## Testing
Run tests with `mvn test`.

## Endpoints
- **POST /api/stock-records/upload**: Upload a dataset.
- **GET /api/stock-records/query/{stock}**: Query records by stock ticker.
- **POST /api/stock-records/add**: Add a new record.