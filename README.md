# Food Ordering System Using Java Design Patterns

## 1. Problem Statement

Develop a simple Java-based console application for a restaurant called **Food Corner**.

The application should simulate a basic food ordering and payment workflow. It should maintain restaurant configuration, process customer payments through different payment methods, apply discounts, and generate order representations in different formats.

The application must demonstrate the use of five software design patterns: Singleton, Factory, Adapter, Bridge, and Proxy.

The application should use hardcoded data. User input, database integration, and file-based persistence are not required.

## 2. Functional Requirements

### A. Restaurant Configuration

Create a restaurant configuration class containing the restaurant name, currency, and discount percentage.

Only one instance of this configuration should exist throughout the application.

### B. Payment Processing

Implement a common `Payment` interface with a payment method that accepts an amount.

The application should support the following payment methods:

- Cash
- Card
- bKash
- Nagad

The application should select the appropriate payment implementation based on a given payment type.

### C. Order Processing

Create an `Order` class containing the order amount, payment method, and order formatter.

The order processing workflow should:

- Retrieve the restaurant discount.
- Calculate the discount amount.
- Calculate the final payable amount.
- Process the payment.
- Generate the order representation.

### D. Order Formatting

The application should support two order formats:

- PDF format
- HTML format

The same order information should be usable with different formatting implementations.

## 3. Required Design Patterns

Implement the following five design patterns.

### 1. Singleton Pattern

Use Singleton for the `RestaurantConfig` class to ensure that only one restaurant configuration instance exists.

### 2. Factory Pattern

Use a `PaymentFactory` class to create payment objects based on the selected payment type, such as cash, card, bKash, or Nagad.

### 3. Adapter Pattern

Assume that Nagad provides a third-party payment SDK with a different method name and interface.

Create a `NagadPaymentAdapter` that allows the third-party payment implementation to work with the application's common `Payment` interface.

### 4. Bridge Pattern

Separate order information from its presentation format.

Use an `OrderFormatter` interface with separate PDF and HTML formatter implementations. The `Order` class should work with either formatter without depending on their concrete implementations.

### 5. Proxy Pattern

Create a `PaymentProxy` that acts as an intermediary between the application and the actual payment implementation.

The proxy should validate the payment amount before forwarding the request. It should reject invalid amounts and allow valid payments to proceed.

## 4. Unit Testing

Use JUnit 5 to write unit tests for the individual design pattern implementations.

The tests should verify:

- Singleton returns the same instance.
- Factory creates the correct payment implementation.
- Factory rejects invalid payment types.
- Nagad Adapter successfully invokes the third-party payment method.
- Bridge supports both PDF and HTML formatting.
- Proxy rejects invalid payment amounts and allows valid payments.

## 5. Integration Testing

Write at least one integration test using JUnit 5.

The integration test should simulate a complete food ordering workflow by combining the five design patterns.

The test should verify that:

- Restaurant configuration is retrieved.
- The payment factory creates the requested payment implementation.
- The adapter successfully processes Nagad payments.
- The proxy validates payment requests.
- The order applies the restaurant discount.
- The selected order formatter generates the expected output.

Use hardcoded test data and assertions to verify the workflow.

## 6. Dockerization

Create a `Dockerfile` to package the Java application into a Docker image.

Create a `compose.yaml` file to run the application using Docker Compose.

The application should be executable inside the container without requiring an interactive user input process.

## 7. GitHub Actions CI/CD

Create a GitHub Actions workflow that automates the following tasks:

- Check out the source code.
- Set up the required Java version.
- Build the Maven project.
- Execute unit and integration tests.
- Build the Docker image.
- Publish the Docker image to GitHub Container Registry (GHCR) when changes are pushed to the `main` branch.

Pull requests should execute the build and test stages without publishing the Docker image.
