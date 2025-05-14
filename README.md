# Maboa Tech Shop(MTS) - Backend

MTS Backend is a robust Spring Boot application providing RESTful APIs for managing products, categories, orders, and sales statistics. This monolithic application separates the Sales domain from Orders to allow for better scalability and maintainability. It serves as the backend for the Maboa Tech Shop frontend built in Angular.

## Technology Stack

### Backend Framework
- Java 21
- Spring Boot 3.3.9
- Maven 3.8+

### Data Persistence
- Spring Data JPA
- H2 Database (Testing only)
- PostgreSQL (Development & Production)

### API & Documentation
- Spring REST
- Jakarta Validation
- SpringDoc OpenAPI (Swagger UI)

### Cloud Services
- Cloudinary (Image Storage)

### Development Tools
- Spring Boot Actuator (Monitoring)
- Spring Boot DevTools

## Running the Application

### Prerequisites
- JDK 21
- Maven 3.8+
- PostgreSQL (for development and production)

### Profiles & Databases

- **Test Profile:** Uses H2 database as configured in `application-test.properties`
- **Development Profile:** Uses PostgreSQL as configured in `application-dev.properties`
- **Production Profile:** Uses PostgreSQL and external configuration via environment variables as in `application-prod.properties`

### Development Mode

```bash
# Build the application
mvn clean install

# Run in development mode (PostgreSQL)
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Production Mode

```bash
# Build the application
mvn clean package

# Run in production mode (PostgreSQL)
java -jar target/beta-mts-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

### Docker Deployment

```bash
# Build Docker image
docker build -t beta-mts:latest .

# Run container using the production profile
docker run -d -p 8080:8080 -e SPRING_PROFILES_ACTIVE=prod beta-mts:latest
```

### API Documentation
Once the application is running, access the Swagger UI documentation at:
```
http://localhost:8080/swagger-ui.html
```

## API Endpoints

### Summary

| HTTP Method | Endpoint                         | Description                      |
|-------------|----------------------------------|----------------------------------|
| POST        | /products                        | Create a new product             |
| GET         | /products                        | Retrieve all products            |
| GET         | /products/{id}                   | Retrieve a product by ID         |
| PUT         | /products/{id}                   | Update a product (with image)    |
| PATCH       | /products/{id}                   | Partially update product details |
| DELETE      | /products/{id}                   | Delete a product by ID           |
| POST        | /categories                      | Create a new category            |
| GET         | /categories                      | Retrieve all categories          |
| GET         | /categories/{id}                 | Retrieve a category by ID        |
| GET         | /categories/name/{name}          | Retrieve a category by name      |
| PUT         | /categories/{id}                 | Update category details          |
| DELETE      | /categories/{id}                 | Delete a category by ID          |
| POST        | /orders                          | Create a new order               |
| GET         | /orders/sales-statistics         | Retrieve overall sales statistics|
| GET         | /orders/filtered                 | Retrieve filtered orders         |

### Endpoint Details

#### Products

- **POST /products**  
  Creates a new product. Accepts `multipart/form-data` with:
  - `product` (JSON): ProductDTO payload
  - `imageFile` (file, optional): Product image

- **GET /products**  
  Retrieves a list of all products.

- **GET /products/{id}**  
  Retrieves a product by its ID.

- **PUT /products/{id}**  
  Updates an existing product and optionally processes an image upload.

- **PATCH /products/{id}**  
  Partially updates the product (typically its description).

- **DELETE /products/{id}**  
  Deletes the product with the specified ID.

#### Categories

- **POST /categories**  
  Creates a new category. Accepts JSON CategoryDTO.

- **GET /categories**  
  Retrieves all categories.

- **GET /categories/{id}**  
  Retrieves a category by its ID.

- **GET /categories/name/{name}**  
  Retrieves a category by its name.

- **PUT /categories/{id}**  
  Updates category details.

- **DELETE /categories/{id}**  
  Deletes the category with the specified ID.

#### Orders

- **POST /orders**  
  Creates a new order. Accepts JSON OrderDTO.

- **GET /orders/sales-statistics**  
  Returns overall sales statistics.

- **GET /orders/filtered**  
  Retrieves orders filtered by criteria.

## Architecture

### Domain-Driven Design

The application follows domain-driven design principles with clear separation of concerns:

- **Controllers:** Handle HTTP requests/responses and parameter validation.
- **DTOs:** Data Transfer Objects for API communication.
- **Services:** Core business logic implementation via interface-based design.
- **Repositories:** JPA repositories for data access operations.
- **Entities:** Domain models mapped to database tables.

### Sales-Orders Domain Separation

Key architectural decisions include the separation of the Sales and Orders domains:

- **Independent Entity Models:** Separate models for Sales and Orders.
- **Dedicated Services:** Each domain has its own service layer.
- **Specialized Repositories:** Domain-specific data access.
- **Focused Controllers:** API endpoints are organized by domain.

Benefits:
- Reduced coupling and improved maintainability.
- Independent scaling for Sales and Orders components.
- Clear separation of business logic.

## Frontend Integration

This backend service integrates with the Maboa Tech Shop frontend built in Angular, which includes:

- `/admin/sales-list`: Dashboard for sales management and analytics.
- `/admin/sales-detail`: Detailed view of individual sales with metrics.

## Build & Deployment

### CI/CD Pipeline

The project uses a CI/CD pipeline for automated deployment comprising:

1. Code quality analysis.
2. Containerization (Docker).
3. Cloud deployment.

## Contributing

1. Fork the repository.
2. Create your feature branch (`git checkout -b feature/amazing-feature`).
3. Commit your changes with descriptive messages.
4. Push to the branch and create a Pull Request.

## License

[MIT License](LICENSE)