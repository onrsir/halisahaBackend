# HalisahaBackend - Football Field Reservation System

A microservices-based backend system for managing football field reservations, built with Spring Boot and Spring Cloud.

## Services

### 1. Eureka Server (Discovery Service)
- Port: 8761
- Service discovery and registration
- Enables service-to-service communication

### 2. User Service
- Port: 8081
- User management and authentication
- JWT-based security
- Features:
  - User registration and login
  - Role-based authorization (USER, ADMIN)
  - Profile management

### 3. Field Service
- Port: 8082
- Football field management
- Features:
  - Field listing and details
  - Field type categorization (INDOOR, OUTDOOR)
  - Pricing management (base price and peak hour pricing)
  - Field availability status

### 4. Reservation Service
- Port: 8083
- Reservation management
- Features:
  - Create and manage reservations
  - Check field availability
  - Reservation status tracking
  - Integration with Field and User services

### 5. Payment Service
- Port: 8084
- Payment processing
- Features:
  - Payment processing with Stripe integration
  - Payment status tracking
  - Refund handling
  - Transaction history

### 6. Notification Service
- Port: 8086
- Notification management
- Features:
  - Email notifications
  - SMS notifications (planned)
  - Push notifications (planned)
  - Notification templates and history

### 7. API Gateway
- Port: 8085
- Single entry point for clients
- Features:
  - Route management
  - Load balancing
  - Cross-cutting concerns
  - CORS configuration

## Technical Stack

- Java 17
- Spring Boot 3.2.3
- Spring Cloud (Netflix Eureka, OpenFeign)
- Spring Security with JWT
- PostgreSQL
- Maven
- Swagger/OpenAPI Documentation

## Recent Updates

- Implemented microservices architecture with Spring Cloud
- Added service discovery with Eureka Server
- Integrated JWT-based authentication
- Added field management functionality
- Implemented reservation system
- Added payment processing capabilities
- Integrated notification system
- Implemented API Gateway for centralized routing

## Getting Started

1. Clone the repository
2. Make sure you have Java 17 and Maven installed
3. Start PostgreSQL and create required databases
4. Run services in the following order:
   ```bash
   ./start-services.sh
   ```
   Or manually:
   ```bash
   cd eureka-server && mvn spring-boot:run
   cd ../user-service && mvn spring-boot:run
   cd ../field-service && mvn spring-boot:run
   cd ../reservation-service && mvn spring-boot:run
   cd ../payment-service && mvn spring-boot:run
   cd ../notification-service && mvn spring-boot:run
   cd ../api-gateway && mvn spring-boot:run
   ```

5. Access Eureka dashboard at http://localhost:8761
6. Access API documentation at http://localhost:8085/swagger-ui.html

## API Documentation

Each service has its own Swagger documentation available at:
- User Service: http://localhost:8081/swagger-ui.html
- Field Service: http://localhost:8082/swagger-ui.html
- Reservation Service: http://localhost:8083/swagger-ui.html
- Payment Service: http://localhost:8084/swagger-ui.html
- Notification Service: http://localhost:8086/swagger-ui.html

## Environment Setup

Required environment variables:
```properties
# Database Configuration
POSTGRES_HOST=localhost
POSTGRES_PORT=5432
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres

# JWT Configuration
JWT_SECRET=your-secret-key
JWT_EXPIRATION=86400000

# Email Configuration (for Notification Service)
SMTP_HOST=smtp.gmail.com
SMTP_PORT=587
SMTP_USERNAME=your-email@gmail.com
SMTP_PASSWORD=your-app-password

# Stripe Configuration (for Payment Service)
STRIPE_SECRET_KEY=your-stripe-secret-key
STRIPE_PUBLIC_KEY=your-stripe-public-key
```

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request 