# Go‑Phone — E-commerce Backend (Spring Boot/MyBatis)

<p align="center">
  <a href="./">
    <img src="logo/GO.svg" alt="Go-Phone Logo" width="160">
  </a>
</p>

The Go‑Phone project implements the core business capabilities of a phone/accessory marketplace: product management, categorization, JWT‑based user authentication, order handling, and PayOS payment gateway integration. The source code follows a layered structure (controller → service → mapper → domain), with an emphasis on standardized responses and unified error handling.

[![Java](https://img.shields.io/badge/Java-21-007396?logo=openjdk\&logoColor=white)]()
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.7-6DB33F?logo=springboot\&logoColor=white)]()
[![MyBatis](https://img.shields.io/badge/MyBatis-Mapper-3E3E3E)]()
[![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?logo=mysql\&logoColor=white)]()
[![Redis](https://img.shields.io/badge/Redis-DC382D?logo=redis\&logoColor=white)]()
[![JWT](https://img.shields.io/badge/JWT-Security-000000?logo=jsonwebtokens\&logoColor=white)]()
[![Spotless](https://img.shields.io/badge/Code%20Style-Spotless-1f6feb)]()
[![JaCoCo](https://img.shields.io/badge/Coverage-JaCoCo-orange)]()
[![SonarQube](https://img.shields.io/badge/SonarQube-4E9BCD?logo=sonarqube\&logoColor=white)]()
[![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven\&logoColor=white)]()
[![Docker](https://img.shields.io/badge/Docker-2496ED?logo=docker\&logoColor=white)]()

> All examples and paths below use the default API prefix `/api/v1`.

## Technologies Used

* **Language/Runtime**: Java **21**
* **Framework**: Spring Boot **3.4.7** (Web, Validation, Security, Mail, Actuator)
* **ORM/Mapper**: **MyBatis** (XML mapper, avoid `SELECT *`)
* **Primary Database**: **MySQL 8**
* **In‑memory Store**: **Redis** (OTP, rate limiting, short‑lived cache)
* **Security**: **JWT** (jjwt) + Spring Security
* **Code Formatting**: **Spotless**
* **Test Coverage**: **JaCoCo**
* **Code Quality**: **SonarQube** (local/server)
* **Packaging & Infrastructure**: Maven, Docker/Docker Compose

## Directory Structure

High‑level (abridged):

```
go_phone/
 ├─ common/
 │   ├─ constants/ApiConstants.java
 │   ├─ exception/{ErrorCode.java, AppException.java, GlobalExceptionHandler.java}
 │   ├─ mapper/BaseMapper.java
 │   ├─ response/{ApiResponse.java, PageResponse.java, ResponseHandler.java, ResponseWriter.java}
 │   └─ util/{CalculateOffset.java, OrderNumberGenerator.java, HashingUtils.java}
 ├─ feature/
 │   ├─ auth/ ...
 │   ├─ product/ ...
 │   ├─ category/ (brand, color, made‑from) ...
 │   ├─ orders/ ...
 │   └─ payment/ (payos) ...
 ├─ docker-compose.yml
 ├─ pom.xml
 └─ docs/
     ├─ Environment configuration guide.docx/        # .env guide
     ├─ Docker setup guide.docx/               # Docker/Compose guide
     └─ PayOS setup guide.docx/                # PayOS integration guide
```

## API Standardization & Error Handling

### Response Schema

```json
{
  "success": true,
  "message": "SUCCESS",
  "code": null,
  "data": { "..." },
  "timestamp": "2025-09-10T12:34:56.789"
}
```

* `success`: processing status
* `message`: general/concise detail message
* `code`: business error code (when `success=false`)
* `data`: returned payload
* `timestamp`: response timestamp

### Error Code Conventions (examples)

* `5000`: System or authentication error
* `4000`: External service error
* `3000`: Invalid data/input
* `2000`: Not found/already exists

All exceptions are captured in `GlobalExceptionHandler` and mapped to the schema above.

## Authentication Specification

* Mechanism: JWT Bearer token.
* Header: `Authorization: Bearer <token>` for protected endpoints.
* Tokens are issued upon successful login and expire as configured.

## Endpoint Catalog

> Default prefix: `/api/v1`. Pagination parameters: `page` (starting from 1), `size`. Offset is computed by `CalculateOffset`.

### Auth

* `POST  /auth/register` – register account
* `POST  /auth/login` – log in, returns JWT
* `POST  /auth/logout` – log out
* `POST  /auth/introspect` – verify token validity
* `POST  /auth/forgot/request` – send OTP (email), requires a 6‑digit code
* `POST  /auth/forgot/reset` – complete password reset

### Product

* `GET   /product/get` – product list
* `GET   /product/page?page=&size=` – pagination
* `GET   /product/search?keyword=&page=&size=` – search with pagination
* `GET   /product/get/{id}` – detail by id
* `POST  /product/create` – create
* `PUT   /product/update/{id}` – update
* `DELETE /product/delete/{id}` – soft delete

### Category (brand/color/made‑from)

* `GET   /category/{brand|color|made-from}/get`
* `GET   /category/{brand|color|made-from}/page?page=&size=`
* `GET   /category/{brand|color|made-from}/search?keyword=&page=&size=`
* `GET   /category/{brand|color|made-from}/get/{id}`
* `POST  /category/{brand|color|made-from}/create`
* `PUT   /category/{brand|color|made-from}/update/{id}`
* `DELETE /category/{brand|color|made-from}/delete/{id}`

### Orders

* `POST  /orders/create` – create order; `OrderNumberGenerator` issues a 10‑digit code \[1\_000\_000\_000, 9\_999\_999\_999]
* `GET   /orders/get/{orderCode}` – lookup order by code

### Payments (PayOS)

* `POST  /payments/payos/create` – create PayOS payment session (returns payment link)
* `POST  /payments/payos/webhooks/payos` – webhook for payment status updates

### System/Actuator

* `GET   /actuator/health` – application health

## Configuration & Operations

### Environment Configuration (.env)

See: `docs/Environment configuration guide.docx/`
(includes MySQL, Redis, JWT, Mail, PayOS environment variables)

### Docker Setup

See: `docs/Docker setup guide.docx/`
(includes Docker Compose infrastructure, port mapping, initial schema via `init.sql`)

### PayOS Setup

See: `docs/PayOS setup guide.docx/`
(includes `PAYOS_CLIENT_ID`, `PAYOS_API_KEY`, `PAYOS_CHECKSUM_KEY`, payment flow and webhook)

### Build & Run

**Requirements**: JDK 21, Maven 3.9+

Build:

```bash
mvn clean package -DskipTests
```

Run via JAR (ensure environment variables are configured per `docs/Environment configuration guide.docx/`):

```bash
java -jar target/gophone-0.1.jar
```

Run via Maven:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## Testing & Code Quality

* **JaCoCo**: collect and view code coverage

  ```bash
  mvn test
  mvn jacoco:report
  # HTML report: target/site/jacoco/index.html
  ```
* **Spotless**: enforce code style

  ```bash
  mvn spotless:apply
  mvn spotless:check
  ```
* **SonarQube**: static analysis

  ```bash
  mvn -DskipTests sonar:sonar \
    -Dsonar.host.url=<SONAR_URL> \
    -Dsonar.login=<SONAR_TOKEN>
  ```

## Security Notes

* Do not log sensitive information (passwords, tokens, secrets).
* Manage JWT secrets and credentials via environment variables/secret manager.
* Restrict access to sensitive endpoints at the Security layer.

## Sample cURL

Login:

```bash
curl -X POST http://localhost:8888/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user@example.com","password":"your-password"}'
```

List products (paginated):

```bash
curl "http://localhost:8888/api/v1/product/page?page=1&size=20"
```

Create order:

```bash
curl -X POST http://localhost:8888/api/v1/orders/create \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <JWT>" \
  -d '{"items":[{"productId":1,"qty":2}], "note":"..."}'
```

Create PayOS payment session:

```bash
curl -X POST http://localhost:8888/api/v1/payments/payos/create \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <JWT>" \
  -d '{"orderCode":1234567890, "amount": 1000000, "description":"Thanh toan don hang"}'
```
