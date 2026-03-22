# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build and Development Commands

This is a Maven-based Spring Boot project.

```bash
# Run the application
./mvnw spring-boot:run

# Build the project
./mvnw clean compile

# Run all tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=RealTimeChatAppApplicationTests

# Package the application (creates JAR in target/)
./mvnw clean package

# Build and run the JAR
./mvnw clean package && java -jar target/real-time-chat-app-0.0.1-SNAPSHOT.jar
```

## Environment Configuration

The application requires environment variables defined in a `.env` file (gitignored):

```bash
DATABASE_HOST=localhost
DATABASE_PORT=5432
DATABASE_NAME=chatapp
DATABASE_USERNAME=postgres
DATABASE_PASSWORD=your_password
JWT_SECRET_KEY=your-secret-key-min-32-characters-long
```

On Windows with PowerShell, set environment variables with:
```powershell
$env:DATABASE_HOST="localhost"
$env:JWT_SECRET_KEY="your-secret-key-min-32-characters-long"
```

## Architecture Overview

### Layered Architecture
- **Controllers** (`controller/`): REST endpoints, handle HTTP requests/responses
- **Services** (`service/`): Business logic
- **Repositories** (`repository/`): JPA data access layer
- **Models** (`model/`): JPA entities with Lombok
- **Web DTOs** (`web/request/`, `web/response/`): Request/response objects
- **Config** (`config/`): Security, JWT, and application configuration

### Authentication Flow
- JWT-based stateless authentication
- `SecurityConfig` permits `/api/auth/**` endpoints publicly
- `JwtAuthFilter` validates Bearer tokens on protected routes
- Passwords encoded with BCrypt

### Key Domain Entities
- **User**: Registered users with email (unique), username, password
- **ChatRoom**: Chat rooms with unique names
- **Message**: Chat messages linking User (sender) and ChatRoom

### API Endpoints

**Authentication** (`/api/auth`):
- `POST /register` - User registration
- `POST /login` - User login (returns JWT token)

**Chat Rooms** (`/api/chatrooms`):
- `POST /` - Create chat room (authenticated)
- `GET /` - List all chat rooms (authenticated)

### Important Implementation Notes

1. **JWT Token**: Extracted from `Authorization: Bearer <token>` header. Token expires after 24 hours.

2. **Database**: Uses PostgreSQL with Hibernate DDL auto-update (`spring.jpa.hibernate.ddl-auto=update`).

3. **Message Entity**: References `ChatRoom` model (ensure this entity exists when implementing messaging).

4. **Constructor Injection**: All services and controllers use constructor injection - maintain this pattern.

5. **Lombok**: Entities use Lombok `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor` for boilerplate reduction.
