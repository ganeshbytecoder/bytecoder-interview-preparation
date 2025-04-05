# Basic of CQRS

Yes, having two separate databases—one for commands (writes) and one for queries (reads)—is a common practice in **CQRS (Command Query Responsibility Segregation)**. This approach improves performance, scalability, and fault isolation.

### **Why Use Separate Databases in CQRS?**
1. **Optimized for Specific Workloads**
    - **PostgreSQL (Write-Optimized - Command Side):**
        - Ensures strong consistency and transactional integrity (ACID compliance).
        - Good for handling complex transactions and enforcing constraints.
    - **MongoDB (Read-Optimized - Query Side):**
        - Schema-less structure allows for flexible query responses.
        - Faster reads, especially for JSON-based queries and hierarchical data.

2. **Scalability & Performance**
    - The write database (PostgreSQL) is optimized for handling updates, inserts, and ensuring data consistency.
    - The read database (MongoDB) is optimized for fast data retrieval, reducing the load on the primary database.

3. **Eventual Consistency**
    - In a **CQRS Event-Sourced system**, writes go to PostgreSQL, and events are propagated to MongoDB asynchronously.
    - This allows MongoDB to be a **read replica** with denormalized and precomputed views for efficient queries.

4. **Fault Tolerance & Isolation**
    - If the read database (MongoDB) is under heavy load, it doesn't affect write operations.
    - If the write database (PostgreSQL) needs to be upgraded, the read side remains operational.

### **Implementation Strategies**
- **Event Sourcing Approach**:
    - Store all events in an event store (e.g., PostgreSQL).
    - Use event-driven architecture (Kafka, RabbitMQ) to sync updates to MongoDB.

- **ETL (Extract, Transform, Load) Pipelines**:
    - Periodically synchronize data from PostgreSQL to MongoDB using tools like **Debezium, Kafka Connect, or Change Data Capture (CDC)**.

- **Dual Writes (Not Recommended in Most Cases)**:
    - Writing directly to both databases can lead to inconsistency due to failures.
    - Instead, prefer **event-driven synchronization**.

### **Potential Challenges**
1. **Data Synchronization**
    - Ensuring MongoDB has the latest changes from PostgreSQL requires a robust mechanism (CDC, message queues, etc.).

2. **Eventual Consistency**
    - Reads might return slightly stale data, which is acceptable in most cases but must be managed.

3. **Operational Complexity**
    - Maintaining two different databases increases infrastructure overhead, monitoring, and operational effort.

### **Alternative Approaches**
- Instead of using MongoDB, consider **PostgreSQL Read Replicas** for queries.
- Use **Elasticsearch** instead of MongoDB if full-text search or analytics are a priority.


### **Implementing CQRS (Command Query Responsibility Segregation) in Spring Boot Applications**
CQRS (Command Query Responsibility Segregation) is an architectural pattern that separates the read (query) and write (command) operations for a system. This helps in improving performance, scalability, and maintainability, especially in microservices architectures.

---

### **Key Components in CQRS Implementation**
1. **Command Side (Write Model)**
    - Handles **Create, Update, Delete (CUD)** operations.
    - Uses **Event Sourcing** to store changes as events.
    - Can use **Transactional Databases**.

2. **Query Side (Read Model)**
    - Handles **Read Operations**.
    - Uses **Denormalized Views** optimized for queries.
    - Can use **NoSQL or Caching** for performance.

3. **Event Bus**
    - Transfers changes from **Command Side** to **Query Side**.
    - Implements **Event-Driven Architecture**.

---

### **Implementation of CQRS in Spring Boot**
We will implement CQRS with **Spring Boot, Spring Data, Axon Framework (optional), and Kafka (for event propagation)**.

---

## **Step 1: Define the Model**

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private String product;
    private int quantity;
    private String status;
}
```

---

## **Step 2: Create Command Side (Write Model)**

### **Define Commands**
```java
import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class CreateOrderCommand {
    @TargetAggregateIdentifier
    private String orderId;
    private String product;
    private int quantity;
}
```

---

### **Define Aggregate for Event Sourcing**
```java
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private String product;
    private int quantity;
    private String status;

    public OrderAggregate() {}

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        apply(new OrderCreatedEvent(command.getOrderId(), command.getProduct(), command.getQuantity()));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.orderId = event.getOrderId();
        this.product = event.getProduct();
        this.quantity = event.getQuantity();
        this.status = "CREATED";
    }
}
```

---

### **Publish Events to Kafka**
```java
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public EventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String topic, Object event) {
        kafkaTemplate.send(topic, event);
    }
}
```

---

### **Define Command Controller**
```java
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderCommandController {

    private final CommandGateway commandGateway;

    public OrderCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createOrder(@RequestBody Order order) {
        String orderId = UUID.randomUUID().toString();
        CreateOrderCommand command = new CreateOrderCommand(orderId, order.getProduct(), order.getQuantity());
        commandGateway.send(command);
        return "Order placed with ID: " + orderId;
    }
}
```

---

## **Step 3: Create Query Side (Read Model)**

### **Define Event for Read Model**
```java
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderCreatedEvent {
    private String orderId;
    private String product;
    private int quantity;
}
```

---

### **Define Read Model Repository (MongoDB)**
```java
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {}
```

---

### **Consume Events from Kafka for Read Model**
```java
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventListener {

    private final OrderRepository orderRepository;

    public OrderEventListener(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @KafkaListener(topics = "order-events", groupId = "orders-group")
    public void handleOrderCreated(OrderCreatedEvent event) {
        Order order = new Order(event.getOrderId(), event.getProduct(), event.getQuantity(), "CREATED");
        orderRepository.save(order);
    }
}
```

---

### **Define Query Controller**
```java
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderQueryController {

    private final OrderRepository orderRepository;

    public OrderQueryController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable String id) {
        return orderRepository.findById(id).orElse(null);
    }
}
```

---

## **Step 4: Configure Kafka for Event Propagation**
```yaml
spring:
  kafka:
    bootstrap-servers: localhost:9092
    consumer:
      group-id: "orders-group"
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
    consumer:
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
      properties:
        spring.json.trusted.packages: "*"
```

---

## **Key Takeaways**
1. **Command Side**
    - Uses **Axon Framework** for event-driven CQRS.
    - Commands are handled by Aggregates.
    - Events are published to Kafka.

2. **Query Side**
    - Uses **MongoDB** for optimized read storage.
    - Listens to Kafka events and updates query storage.

3. **Event Bus (Kafka)**
    - Connects **Command** and **Query** sides.
    - Ensures eventual consistency.

---

### **Advantages of This CQRS Implementation**
✅ **Scalability** – Read and write workloads can be scaled independently.  
✅ **Performance** – Optimized read models provide fast queries.  
✅ **Flexibility** – Different databases can be used for reading and writing.  
✅ **Event Sourcing** – Enables an audit log of all state changes.
