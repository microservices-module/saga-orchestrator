# Saga Design Pattern in Microservices Orchestration

## Overview
The Saga design pattern is a mechanism for managing distributed transactions in microservices architectures. Instead of relying on a single, global transaction, Sagas break a business process into a series of local transactions, each managed by a participating microservice. If a step fails, compensating transactions are triggered to undo the work of previous steps, ensuring data consistency across services.

## Types of Sagas
1. **Choreography-Based Saga**
   - Each service produces and listens to events.
   - No central coordinator; services react to events and trigger the next step.
   - Simpler, but can become complex with many services and event flows.

2. **Orchestration-Based Saga**
   - A central orchestrator (Saga Orchestrator) coordinates the saga steps.
   - Services execute commands as directed by the orchestrator.
   - Easier to manage and debug; logic is centralized.

## How Sagas Work Internally
- **Step Execution:** Each local transaction is executed by a microservice.
- **State Management:** The saga maintains the state of the overall process (e.g., using a state machine).
- **Compensation:** If a step fails, compensating transactions are triggered to revert previous successful steps.
- **Communication:** Services communicate via events (choreography) or commands (orchestration), often using messaging systems like JMS, Kafka, or RabbitMQ.
- **Persistence:** Saga state and events are persisted to ensure reliability and recovery.

## Futures of Saga Pattern
- **Resilience:** Handles failures gracefully, ensuring eventual consistency.
- **Scalability:** Decouples services, allowing independent scaling.
- **Auditability:** Each step and compensation is logged, aiding in debugging and compliance.
- **Flexibility:** Supports both choreography and orchestration models.

## This Project: Saga Orchestrator Microservice

### Architecture
- **Orchestration-Based Saga:** Uses a central Saga Orchestrator to manage distributed transactions across airline and hotel services.
- **Spring State Machine:** Manages the workflow and state transitions of orders.
- **JMS (ActiveMQ):** Facilitates communication between services.
- **Persistence:** Order states are persisted using JPA/Hibernate.

### Internal Workflow
1. **Order Initiation:** A new order triggers the saga orchestrator.
2. **State Machine:** The orchestrator uses a state machine to track the order's progress (e.g., CREATED → AIRLINE_BOOKED → HOTEL_BOOKED → COMPLETED).
3. **Service Actions:** The orchestrator sends commands to airline and hotel services via JMS queues.
4. **State Change Interceptors:** Custom interceptors persist state changes and handle business logic.
5. **Compensation:** If a booking fails (e.g., hotel unavailable), the orchestrator triggers compensating actions (e.g., cancel airline booking).
6. **Persistence & Recovery:** All state changes and events are persisted, allowing recovery and audit.

### Key Components
- **SagaOrchestratorApplication:** Main entry point, configures state machine and JMS.
- **OrderStateMachineConfig:** Defines states, events, and transitions.
- **OrderStateChangeInterceptor:** Persists state changes to the database.
- **SagaJmsListener:** Listens for JMS messages and triggers saga actions.
- **Service Classes (AirlineAction, HotelAction):** Execute business logic and send/receive JMS messages.

### Example Flow
1. User places an order.
2. Orchestrator starts the saga, moves order to CREATED state.
3. Orchestrator sends a message to airline service to book a flight.
4. Airline service responds; orchestrator moves order to AIRLINE_BOOKED state.
5. Orchestrator sends a message to hotel service to book a hotel.
6. If hotel booking succeeds, order moves to COMPLETED. If it fails, orchestrator triggers compensation (e.g., cancel flight).

## Summary
This project demonstrates the orchestration-based saga pattern using Spring Boot, State Machine, JMS, and JPA. It provides resilience, auditability, and flexibility for distributed transactions in microservices architectures.
