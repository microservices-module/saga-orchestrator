# Saga Orchestrator Microservice

This microservice implements the Saga Orchestrator pattern using Spring Boot 3+, Java 21, and Spring State Machine.

## Prerequisites

- Java 21
- Maven
- ActiveMQ (for JMS messaging)

## How to Run

1. Start ActiveMQ broker (default URL: tcp://localhost:61616)
2. Build the project: `mvn clean install`
3. Run the application: `mvn spring-boot:run`

## Features

- Implements Saga Orchestrator pattern
- Uses Spring State Machine for workflow management
- Communicates with airline and hotel microservices via JMS

## Configuration

See `src/main/resources/application.properties` for configuration options.

## License

MIT
