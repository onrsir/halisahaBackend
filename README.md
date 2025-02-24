# HaliSaha Backend

This is a microservices-based backend system for a football field rental application. The system is built using Spring Boot and follows a microservices architecture.

## Project Structure

The project consists of the following microservices:

1. **Eureka Server** (Port: 8761)
   - Service discovery and registration
   - Manages service registry

2. **User Service** (Port: 8081)
   - User authentication and authorization
   - JWT-based security
   - User profile management
   - Role-based access control

3. **Field Service** (To be implemented)
   - Manages football field information
   - Field availability
   - Field types (indoor/outdoor)
   - Field pricing

4. **Reservation Service** (To be implemented)
   - Handles field reservations
   - Manages booking slots
   - Reservation confirmation

5. **Payment Service** (To be implemented)
   - Payment processing
   - Payment history
   - Refund handling

6. **Notification Service** (To be implemented)
   - Sends notifications
   - Reservation confirmations
   - Reminders
   - System notifications

## Setup Instructions

1. **Database Setup**
   ```sql
   CREATE DATABASE halisaha;
   ```

2. **Environment Requirements**
   - Java 17
   - Maven
   - PostgreSQL
   - Docker (optional)

3. **Configuration**
   - Each service has its own `application.yml` file
   - Update database credentials as needed
   - Configure JWT secret and expiration

4. **Running the Services**
   - Start Eureka Server first
   - Start other services in any order
   - Services will automatically register with Eureka

## Security

The application uses JWT-based authentication with the following features:
- Token-based authentication
- Role-based authorization (USER, ADMIN)
- Secure password storage
- Protected endpoints

## API Documentation

### Authentication Endpoints
```
POST /api/auth/signup - User registration
POST /api/auth/signin - User login
GET /api/auth/user - Get user profile
```

### Field Management Endpoints (To be implemented)
```
GET /api/fields - List all fields
GET /api/fields/{id} - Get field details
POST /api/fields - Add new field (ADMIN)
PUT /api/fields/{id} - Update field (ADMIN)
DELETE /api/fields/{id} - Delete field (ADMIN)
```

### Reservation Endpoints (To be implemented)
```
GET /api/reservations - List user reservations
POST /api/reservations - Create reservation
GET /api/reservations/{id} - Get reservation details
DELETE /api/reservations/{id} - Cancel reservation
```

## Development Status

- [x] Project Structure Setup
- [x] Eureka Server Implementation
- [x] User Service Basic Setup
- [ ] Field Service Implementation
- [ ] Reservation Service Implementation
- [ ] Payment Service Integration
- [ ] Notification Service Setup
- [ ] API Gateway Implementation
- [ ] Service Intercommunication
- [ ] Testing
- [ ] Documentation 