# Core Lending Confirming Service

## Overview

The Core Lending Confirming Service is a microservice component of the Firefly platform that manages reverse factoring (confirming) arrangements. In a confirming arrangement, a financial institution pays suppliers early on behalf of buyers, who then pay the institution at a later date. This service provides APIs for managing confirming agreements, suppliers, invoices, advances, fees, and settlements.

## Architecture

The service follows a modular architecture with the following components:

### Modules

- **core-lending-confirming-interfaces**: Contains DTOs and service interfaces that define the API contracts
- **core-lending-confirming-models**: Contains data models, entities, and repositories for database operations
- **core-lending-confirming-core**: Contains the business logic and service implementations
- **core-lending-confirming-web**: Contains REST controllers that expose the service APIs

### Technology Stack

- **Java 21**: Programming language
- **Spring Boot**: Application framework
- **Spring WebFlux**: Reactive web framework
- **Spring Data**: Data access framework
- **Flyway**: Database migration tool
- **Maven**: Build and dependency management
- **Docker**: Containerization

## Domain Model

The service manages the following key entities:

- **Confirming Agreement**: An agreement between a buyer and the financial institution
- **Confirming Supplier**: A supplier associated with a confirming agreement
- **Confirming Invoice**: An invoice submitted by a supplier for confirming
- **Confirming Advance**: An advance payment made against an invoice
- **Confirming Fee**: Fee structures for confirming agreements
- **Confirming Invoice Settlement**: Settlement records for invoices
- **Confirming Invoice Status History**: History of invoice status changes

## API Endpoints

The service exposes RESTful APIs for managing confirming entities. All endpoints are versioned (v1) and follow a hierarchical structure.

### Base URL

```
/api/v1
```

### Endpoints

- **Confirming Agreements**
  - `GET /confirming-agreements`: List or search agreements
  - `POST /confirming-agreements`: Create a new agreement
  - `GET /confirming-agreements/{confirmingAgreementId}`: Get an agreement by ID
  - `PUT /confirming-agreements/{confirmingAgreementId}`: Update an agreement
  - `DELETE /confirming-agreements/{confirmingAgreementId}`: Delete an agreement

- **Confirming Suppliers**
  - `GET /confirming-agreements/{confirmingAgreementId}/suppliers`: List or search suppliers
  - `POST /confirming-agreements/{confirmingAgreementId}/suppliers`: Create a new supplier
  - `GET /confirming-agreements/{confirmingAgreementId}/suppliers/{confirmingSupplierID}`: Get a supplier by ID
  - `PUT /confirming-agreements/{confirmingAgreementId}/suppliers/{confirmingSupplierID}`: Update a supplier
  - `DELETE /confirming-agreements/{confirmingAgreementId}/suppliers/{confirmingSupplierID}`: Delete a supplier

- **Confirming Invoices**
  - `GET /confirming-agreements/{confirmingAgreementId}/invoices`: List or search invoices
  - `POST /confirming-agreements/{confirmingAgreementId}/invoices`: Create a new invoice
  - `GET /confirming-agreements/{confirmingAgreementId}/invoices/{confirmingInvoiceId}`: Get an invoice by ID
  - `PUT /confirming-agreements/{confirmingAgreementId}/invoices/{confirmingInvoiceId}`: Update an invoice
  - `DELETE /confirming-agreements/{confirmingAgreementId}/invoices/{confirmingInvoiceId}`: Delete an invoice

- **Confirming Advances**
  - `GET /confirming-agreements/{confirmingAgreementId}/invoices/{confirmingInvoiceId}/advances`: List or search advances
  - `POST /confirming-agreements/{confirmingAgreementId}/invoices/{confirmingInvoiceId}/advances`: Create a new advance
  - `GET /confirming-agreements/{confirmingAgreementId}/invoices/{confirmingInvoiceId}/advances/{confirmingAdvanceId}`: Get an advance by ID
  - `PUT /confirming-agreements/{confirmingAgreementId}/invoices/{confirmingInvoiceId}/advances/{confirmingAdvanceId}`: Update an advance
  - `DELETE /confirming-agreements/{confirmingAgreementId}/invoices/{confirmingInvoiceId}/advances/{confirmingAdvanceId}`: Delete an advance

- **Confirming Fees**
  - `GET /confirming-agreements/{confirmingAgreementId}/fees`: List or search fees
  - `POST /confirming-agreements/{confirmingAgreementId}/fees`: Create a new fee
  - `GET /confirming-agreements/{confirmingAgreementId}/fees/{confirmingFeeId}`: Get a fee by ID
  - `PUT /confirming-agreements/{confirmingAgreementId}/fees/{confirmingFeeId}`: Update a fee
  - `DELETE /confirming-agreements/{confirmingAgreementId}/fees/{confirmingFeeId}`: Delete a fee

- **Confirming Invoice Settlements**
  - `GET /confirming-agreements/{confirmingAgreementId}/invoices/{confirmingInvoiceId}/settlements`: List or search settlements
  - `POST /confirming-agreements/{confirmingAgreementId}/invoices/{confirmingInvoiceId}/settlements`: Create a new settlement
  - `GET /confirming-agreements/{confirmingAgreementId}/invoices/{confirmingInvoiceId}/settlements/{confirmingInvoiceSettlementId}`: Get a settlement by ID
  - `PUT /confirming-agreements/{confirmingAgreementId}/invoices/{confirmingInvoiceId}/settlements/{confirmingInvoiceSettlementId}`: Update a settlement
  - `DELETE /confirming-agreements/{confirmingAgreementId}/invoices/{confirmingInvoiceId}/settlements/{confirmingInvoiceSettlementId}`: Delete a settlement

## Setup and Installation

### Prerequisites

- Java 21 or higher
- Maven 3.8 or higher
- Docker (optional, for containerized deployment)

### Building the Service

```bash
# Clone the repository
git clone https://github.com/your-organization/core-lending-confirming.git
cd core-lending-confirming

# Build the project
mvn clean install
```

### Running the Service Locally

```bash
# Run the service
java -jar core-lending-confirming-web/target/core-lending-confirming-web.jar
```

### Running with Docker

```bash
# Build the Docker image
docker build -t core-lending-confirming .

# Run the container
docker run -p 8080:8080 core-lending-confirming
```

## Configuration

The service can be configured using standard Spring Boot configuration mechanisms. Key configuration properties include:

- Database connection settings
- Logging levels
- Security settings
- Integration endpoints

Configuration can be provided via:
- `application.properties` or `application.yml` files
- Environment variables
- Command-line arguments

## Development Guidelines

### Code Style

- Follow standard Java coding conventions
- Use meaningful variable and method names
- Write comprehensive Javadoc comments
- Follow the SOLID principles

### Testing

- Write unit tests for all business logic
- Write integration tests for API endpoints
- Aim for high test coverage

### Branching Strategy

- `main`: Production-ready code
- `develop`: Integration branch for features
- `feature/*`: Feature branches
- `bugfix/*`: Bug fix branches
- `release/*`: Release preparation branches

## API Documentation

The service uses Swagger/OpenAPI for API documentation. When the service is running, you can access the API documentation at:

```
http://localhost:8080/swagger-ui.html
```

## Dependencies

The service has the following key dependencies:

- Spring Boot
- Spring WebFlux
- Spring Data
- Flyway
- Lombok
- Jackson
- Swagger/OpenAPI

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/my-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/my-feature`)
5. Create a new Pull Request