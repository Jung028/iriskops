# iriskops

A high-performance riskops management system built on **Spring Boot 3.5.6** and **SOFABoot 4.6.0**. This project utilizes a clean, multi-module architecture designed for scalability and maintainability, integrating SOFA RPC, Kafka, and MyBatis.

## 🏗 Architecture Overview

The project follows a standard layered architecture:

*   **`app/bootstrap`**: The application entry point and startup configuration.
*   **`app/web`**: The web layer containing REST controllers and web-specific configurations.
*   **`app/biz/service/impl`**: Business logic implementation layer.
*   **`app/core/service`**: Core domain services and repository implementations.
*   **`app/core/model`**: Domain models, entities, enums, and exceptions.
*   **`app/common/service/facade`**: API definitions (interfaces), request/result DTOs, and JAX-RS annotations.
*   **`app/common/dal`**: Data Access Layer including MyBatis mappers, SQL maps, and Data Objects (DO).
*   **`app/common/util`**: Shared utility classes and common constants.
*   **`app/common/service/integration`**: Integration layer for calling external systems.

## 🚀 Tech Stack

*   **Framework**: Spring Boot 3.5.6, SOFABoot 4.6.0
*   **RPC/Middleware**: SOFA RPC (Bolt/REST), Nacos (Registry)
*   **Database**: PostgreSQL
*   **ORM**: MyBatis / MyBatis-Plus
*   **Messaging**: Apache Kafka
*   **Cache**: Redis
*   **API**: JAX-RS (javax.ws.rs)

## 📋 Prerequisites

*   **Java**: JDK 17 or higher (Project configured for Java 17+ compatibility)
*   **Maven**: 3.8.x or higher
*   **Database**: PostgreSQL instance running on `localhost:5432`
*   **Middleware**: 
    *   Redis (`localhost:6379`)
    *   Kafka (`localhost:9095`)
    *   Nacos (`localhost:8848`)

## 🛠 Getting Started

### 1. Configuration
Database and middleware settings are located in:
`app/web/src/main/resources/application.properties`

### 2. Build the Project
Compile and install all modules:
```bash
mvn clean install
```

### 3. Run the Application
Start the web module:
```bash
mvn spring-boot:run -pl app/web
```
Alternatively, run the `Main.java` class in `app/web/src/main/java/com/alipay/riskops/web/Main.java`.

## 🔌 API Endpoints

### Riskops Service (JAX-RS / SOFA RPC)
*   **Path**: `/riskopsService`
*   **Interface**: `RiskopsService.java`
*   **Example Method**: `queryRiskopsInfo(QueryRiskopsInfoRequest request)`

## 🔧 Recent Maintenance Notes

*   **Resource Alignment**: Fixed a structural issue where `application.properties` was incorrectly placed in `src/main/java`. It has been moved to the standard `src/main/resources` directory.
*   **Dependency Optimization**: 
    *   Cleaned up duplicate Maven declarations for `spring-web` and `slf4j-api`.
    *   Added `javax.ws.rs-api` to the facade module to resolve "Cannot resolve symbol" errors for `@Path`, `@Produces`, and `@Consumes`.
*   **Build Stability**: Resolved `MissingRequiredPropertiesException` by ensuring `spring.application.name` is correctly loaded from the classpath.
