Electronic-Shop

Electronic-Shop is a Spring Boot application that implements a simple electronic products store backend.
It provides RESTful APIs to manage products (items) and customer orders (purchases). Users can list all available products, view product details, and perform operations on products.
Customers can also place orders by providing their email and card information. The system keeps track of order details and links orders to the purchased items.

Key Features:
  1) Product Management: Create, read, update, delete electronic products.
  2) Order Management: Create and query customer orders (Purchase). Each order stores customer email and card number. You can list all orders, fetch an order by ID, or search by card number.
  3) Quantity Adjustment: Special endpoints to add or remove quantity of an existing item, with validation (throws exceptions if resulting quantity is negative).
  4) Database Migrations: Built-in Flyway migrations to set up database schema automatically.
  5) Testing: Includes unit tests and integration tests for service layers, ensuring business logic correctness.
Technology Stack
  1) Java
  2) Spring Boot
  3) PostgreSQL (configured via Docker Compose)
  4) JDBC
  5) Database Migrations Flyway
  6) Maven
  7) Git
  8) Other Libraries: Lombok (for getters/setters), Spring Boot Starter Web for REST API, Spring Boot Starter JDBC for data access, JUnit/Spring Test for testing.
