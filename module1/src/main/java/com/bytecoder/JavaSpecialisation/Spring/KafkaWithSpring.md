Here is a Spring Boot implementation to consume messages from a specific Kafka topic and partitions using the `@KafkaListener` annotation. This implementation allows specifying partitions explicitly.

---

### **Steps to Consume a Kafka Topic with Given Partitions in Spring Boot**
1. Add dependencies (`spring-kafka`).
2. Configure Kafka consumer properties.
3. Implement a Kafka consumer to listen to specific partitions.

---

### **1. Add Dependencies**
Ensure you have the following dependency in your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
    <version>3.0.0</version>
</dependency>
```

---

### **2. Configure Kafka Consumer Properties**
Define Kafka properties in `application.yml`:

```yaml
spring:
  kafka:
    bootstrap-servers: localhost:9092
    consumer:
      group-id: my-group
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
```

---

### **3. Implement Kafka Consumer with Partition-Specific Listener**
You can use `@KafkaListener` and specify partitions manually.

```java
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaPartitionConsumer {

    @KafkaListener(
        topicPartitions = @org.springframework.kafka.annotation.TopicPartition(
            topic = "my_topic", 
            partitions = { "0", "2" } // Specify partitions to listen
        ),
        groupId = "my-group"
    )
    public void listenToSpecificPartitions(ConsumerRecord<String, String> record) {
        System.out.println("Received message: " + record.value() 
                + " from partition: " + record.partition());
    }
}
```

---

### **Key Points**
- The `@KafkaListener` annotation listens to the specified `topicPartitions`.
- `topicPartitions` allows specifying the **topic name** and **partitions** explicitly.
- The `groupId` ensures that multiple consumers can balance the load.
- The `ConsumerRecord` object provides metadata (partition, offset, key, and value).
- one partition one consumer only 
- multiple partitions -> one consumers possiple
- no. partitions should be greater than consumers (2-3 times). More consumers than partitions results in idle consumers.
- Adding/removing consumers triggers a rebalance
- To allow multiple consumers to read the same partition, use different consumer groups.
---

### **4. Run and Test**
Start the Spring Boot application and produce messages to Kafka using:

```shell
kafka-console-producer --broker-list localhost:9092 --topic my_topic
```


## **🔹 Interview Cheat Sheet Summary**
| **Config** | **Default Value** |
|------------|------------------|
| Default Partitions | `1` |
| Max Message Size | `1MB (1048576 bytes)` |
| Retention Time | `7 days` |
| Retention Size | `Unlimited (-1)` |
| Partition Size | `1GB per segment` |
| Offset Retention | `7 days` |
| Producer Acknowledgments | `acks=1` |
| Default Replication Factor | `1` |
| Consumer Fetch Size | `50MB` |
| Auto Commit Offsets | `true` (every 5 sec) |
| Zookeeper Timeout | `6 sec` |
| Log Cleanup Policy | `delete` |
| Min In-Sync Replicas | `1` |
| Compression | `none` |

---

## **💡 Interview Tips**
✔ **Be ready to explain why Kafka defaults might be changed in production.**  
✔ **Know how to tune retention time, message size, and partitions for scalability.**  
✔ **Expect scenario-based questions like: "What happens if the retention size is too low?"**  
✔ **Understand how `min.insync.replicas` affects data consistency.**




# **Q: Transactional Outbox Pattern vs. Choreography-Based Saga for Scalable Systems**

Both the **Transactional Outbox Pattern** and **Choreography-Based Saga Pattern** address **data consistency** across microservices in
**event-driven architectures**,

---

## **1. Transactional Outbox Pattern**
### **Overview:**
The **Transactional Outbox Pattern** ensures **atomicity** between database changes and event publishing by storing events in an **outbox table** within the same transaction as the database operation. A background process (poller) reads and publishes events to the message broker.

### **How It Works:**
1. Service **A** writes data into its database and records an event in the **outbox table** within the same transaction.
2. A **background process** reads the outbox table and publishes the event to a message broker (Kafka, RabbitMQ, etc.).
3. Other services consume the event and update their state accordingly.

### **Strengths:**
✅ **Strong Consistency:** Ensures that database operations and event publishing happen atomically. No risk of data loss or out-of-sync issues.  
✅ **Reliable Event Publishing:** Uses the database as a buffer, avoiding dual-write problems.  
✅ **Scalability:** Works well for high-throughput systems as the outbox table can be optimized with partitioning and indexing.  
✅ **Fault Tolerance:** If the event publishing fails, retries can be implemented without losing the original event.

### **Challenges:**
⚠️ **Increased Latency:** Events are not published immediately; they depend on a polling mechanism.  
⚠️ **Additional Infrastructure:** Requires an outbox table and a poller to process and publish events.  
⚠️ **Complexity in Ordering:** May require **event deduplication** and proper ordering mechanisms.

### **Best Use Cases:**
- **Fintech & Banking Systems** (ensures strict consistency in transactions).
- **E-commerce Order Processing** (ensures payment and order status updates remain in sync).
- **Critical Business Workflows** (where event loss is unacceptable).


### **Q: Outbox Pattern implementation**
### Solution
| Approach | Best For | Scalability | Complexity |
|----------|---------|------------|------------|
| **Single Outbox Table** | Small-to-medium workloads, simple architecture | Medium | Low |
| **Separate Outbox Tables** | High-throughput applications, different event priorities | High | Medium-High |
| **Partitioned Outbox Table** | Large-scale systems, optimized performance | Very High | Medium |

#### **3️⃣ Hybrid Approach: Partitioned Outbox Table**
🔹 **How It Works:**
- Use **a single outbox table** but implement **table partitioning** based on `event_type` or `aggregate_type`.
- Example **PostgreSQL partitioning**:
```sql
CREATE TABLE outbox (
    id UUID PRIMARY KEY,
    event_type VARCHAR(255),
    aggregate_type VARCHAR(255),
    aggregate_id UUID,
    payload JSONB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    processed BOOLEAN DEFAULT FALSE
) PARTITION BY LIST (event_type);

CREATE TABLE outbox_orders PARTITION OF outbox FOR VALUES IN ('OrderCreated', 'OrderUpdated');
CREATE TABLE outbox_payments PARTITION OF outbox FOR VALUES IN ('PaymentProcessed', 'PaymentFailed');
```
🔹 **Pros:**
✅ **Balances simplicity and performance**.  
✅ **Efficient querying via partitions**.  
✅ **Less schema complexity** compared to separate tables.

🔹 **Cons:**
⚠️ Requires **database support for partitioning**.  
⚠️ Slightly more complex setup than a single table.

🔹 **Best Use Case:**
- **Large-scale applications** that need **efficient query performance** without multiple tables.

---

---

### **2. Choreography-Based Saga**
### **Overview:**
The **Choreography-Based Saga Pattern** manages distributed transactions across microservices using **event-driven interactions**, where each service listens to relevant events and triggers subsequent actions.

### **How It Works:**
1. A microservice (e.g., `Order Service`) initiates a business process and publishes an event (`OrderCreated`).
2. Other microservices (e.g., `Payment Service`, `Inventory Service`) **react** to this event and trigger their own actions.
3. Each service ensures local consistency and publishes subsequent events.
4. If a failure occurs, compensating actions (rollbacks) are triggered.

### **Strengths:**
✅ **High Scalability:** Services remain **loosely coupled** and can independently handle events, making the system **highly scalable**.  
✅ **Real-Time Processing:** Events are processed asynchronously and immediately, reducing **latency** compared to polling mechanisms.  
✅ **No Centralized Coordinator:** Each service **autonomously** decides its actions, making the system resilient.  
✅ **Resilient to Failures:** If a service fails, only that part of the transaction is affected.

### **Challenges:**
⚠️ **Complex Failure Handling:** Requires **compensating transactions** and careful **state management** to handle failures.  
⚠️ **Difficult to Debug:** Since events flow **asynchronously**, debugging and tracing transactions require **distributed tracing** (e.g., OpenTelemetry).  
⚠️ **Event Explosion:** A high number of events can create a **"domino effect"**, leading to **unexpected behavior** if not managed properly.

### **Best Use Cases:**
- **High-Traffic Applications** (e.g., social media, real-time notifications).
- **E-commerce Systems** (order processing across multiple services).
- **Decentralized Workflows** (where each service acts independently).

---

### **Comparison Table:**
| Feature               | **Transactional Outbox** | **Choreography-Based Saga** |
|----------------------|---------------------|-----------------------|
| **Consistency** | Stronger (atomicity via DB transactions) | Weaker (eventual consistency) |
| **Scalability** | Moderate (depends on database performance) | High (event-driven, loosely coupled) |
| **Latency** | Higher (polling introduces delay) | Lower (real-time event processing) |
| **Complexity** | Moderate (requires outbox table & poller) | High (requires event orchestration & compensations) |
| **Failure Handling** | Reliable (retries and polling) | Complex (requires compensating transactions) |
| **Best for** | **Fintech, banking, order processing** | **Social media, e-commerce, decentralized workflows** |

---

### **Final Recommendation for Scalable Systems**
- **For high consistency & transactional reliability** → Use **Transactional Outbox Pattern** (ideal for fintech, banking, and critical workflows).
- **For high scalability & real-time responsiveness** → Use **Choreography-Based Saga** (ideal for distributed event-driven systems).
- **Hybrid Approach** → Some systems use a combination: Outbox Pattern for core services + Choreography for non-critical, asynchronous workflows.
