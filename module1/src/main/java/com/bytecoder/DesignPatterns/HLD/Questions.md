


# **Implementing Multi-Tenancy with Dynamic Data Source Switching**

## **1️⃣ Determining the Shard Using a Sharding Strategy**
Before executing database operations, we determine the correct shard using a **sharding strategy** (e.g., **hash-based**, **range-based**, etc.).

```java
public class ShardContextHolder {
    private static final ThreadLocal<String> currentShard = new ThreadLocal<>();

    public static void setCurrentShard(String shardId) {
        currentShard.set(shardId);
    }

    public static String getCurrentShard() {
        return currentShard.get();
    }

    public static void clear() {
        currentShard.remove();
    }
}
```
### **Why Use `ThreadLocal`?**
✅ Ensures each request gets the correct shard in a **multi-threaded environment**.  
✅ Prevents **mixing data sources** between requests.

---

## **2️⃣ Dynamic Shard Routing with `AbstractRoutingDataSource`**
Spring Boot provides `AbstractRoutingDataSource` to dynamically route queries to the correct database.

```java
public class DynamicShardRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return ShardContextHolder.getCurrentShard(); // Retrieves the shard for the current request
    }
}
```
### **How It Works:**
1. **Extends `AbstractRoutingDataSource`** to dynamically select the data source at runtime.
2. **Overrides `determineCurrentLookupKey()`**, which fetches the shard identifier from `ShardContextHolder`.

---

## **3️⃣ Configuring Multiple Data Sources in Spring Boot**
We define multiple database configurations and register them in `DynamicShardRoutingDataSource`.

```java
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dynamicDataSource() {
        DynamicShardRoutingDataSource routingDataSource = new DynamicShardRoutingDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("shard1", createDataSource("jdbc:mysql://localhost:3306/db_shard1"));
        dataSourceMap.put("shard2", createDataSource("jdbc:mysql://localhost:3306/db_shard2"));

        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(dataSourceMap.get("shard1")); // Default DB
        routingDataSource.afterPropertiesSet();

        return routingDataSource;
    }

    private DataSource createDataSource(String url) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername("user");
        dataSource.setPassword("password");
        return dataSource;
    }
}
```
### **Key Highlights**
✅ **Multiple shards configured** with different database connections.  
✅ **Spring Boot dynamically selects the correct shard** using `DynamicShardRoutingDataSource`.  
✅ **Uses HikariCP** for efficient connection pooling.

---

## **4️⃣ Handling Shard Selection in API Requests**
To ensure each request is routed correctly, we use an **interceptor to extract the shard key**.

```java
@Component
public class ShardInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userIdStr = request.getHeader("X-User-ID");
        if (userIdStr != null) {
            Long userId = Long.parseLong(userIdStr);
            String shard = ShardResolver.getShardForUser(userId);
            ShardContextHolder.setCurrentShard(shard);  // Set the shard before the request is processed
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ShardContextHolder.clear();  // Clear the context after request completes
    }
}
```
### **How It Works**
1. Extracts `X-User-ID` from request headers.
2. Determines the correct **shard** based on the user ID.
3. Sets the **current shard** in `ThreadLocal` before processing the request.
4. Clears the shard context after request completion.

---

## **5️⃣ Registering the Interceptor in Spring**
To activate shard selection for all requests, register the interceptor in `WebMvcConfigurer`.

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private ShardInterceptor shardInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(shardInterceptor);
    }
}
```

---

## **6️⃣ Example: Request Flow with Sharding**
### **📌 Scenario: Fetching User Profile from the Correct Shard**
```plaintext
Client → API Gateway → Shard Resolver → Correct DB → Query Execution → Response
```
✅ **Step 1: User Makes API Request**
```plaintext
GET /api/v1/users/profile
Headers: { "X-User-ID": "202" }
```
✅ **Step 2: Spring Interceptor Determines the Shard**
- Extracts `X-User-ID = 202`
- Computes **shard (`202 % 3 = 1` → `shard2`)**
- Calls `ShardContextHolder.setCurrentShard("shard2")`

✅ **Step 3: Query is Routed to the Correct Shard**
```sql
SELECT * FROM users WHERE user_id = 202;
```
✅ **Step 4: Response is Sent Back**
```json
{
  "user_id": "202",
  "name": "John Doe",
  "email": "johndoe@email.com"
}
```

---

## **🔹 Why Use This Approach?**
✅ **Dynamic Data Source Routing** → Queries are automatically routed to the correct database.  
✅ **Thread-Safe** → Uses `ThreadLocal` to isolate shard selection per request.  
✅ **Scalability** → Easily add new shards without modifying core logic.  
✅ **Efficient Resource Utilization** → Leverages `AbstractRoutingDataSource` for optimal performance.

---

### **Transactional Outbox Pattern vs. Choreography-Based Saga for Scalable Systems**

Both the **Transactional Outbox Pattern** and **Choreography-Based Saga Pattern** address **data consistency** across microservices in **event-driven architectures**, but they serve different purposes and are suited for different types of applications. Below is a comparison of their strengths, trade-offs, and best use cases for **scalable systems**.

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

---

## **2. Choreography-Based Saga**
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

## **Comparison Table:**
| Feature               | **Transactional Outbox** | **Choreography-Based Saga** |
|----------------------|---------------------|-----------------------|
| **Consistency** | Stronger (atomicity via DB transactions) | Weaker (eventual consistency) |
| **Scalability** | Moderate (depends on database performance) | High (event-driven, loosely coupled) |
| **Latency** | Higher (polling introduces delay) | Lower (real-time event processing) |
| **Complexity** | Moderate (requires outbox table & poller) | High (requires event orchestration & compensations) |
| **Failure Handling** | Reliable (retries and polling) | Complex (requires compensating transactions) |
| **Best for** | **Fintech, banking, order processing** | **Social media, e-commerce, decentralized workflows** |

---

## **Final Recommendation for Scalable Systems**
- **For high consistency & transactional reliability** → Use **Transactional Outbox Pattern** (ideal for fintech, banking, and critical workflows).
- **For high scalability & real-time responsiveness** → Use **Choreography-Based Saga** (ideal for distributed event-driven systems).
- **Hybrid Approach** → Some systems use a combination: Outbox Pattern for core services + Choreography for non-critical, asynchronous workflows.

### **Do We Need Separate Outbox Tables for Each Event Type or Transaction Type?**

No, you **don’t necessarily have to create separate outbox tables** for each event type or transaction type. However, whether to use **one outbox table or multiple** depends on factors like scalability, performance, and event processing complexity.

---

### **Approaches for Designing an Outbox Table**

#### **1️⃣ Single Outbox Table for All Events (Generic Approach)**
🔹 **Structure:**
```sql
CREATE TABLE outbox (
    id UUID PRIMARY KEY,
    event_type VARCHAR(255),
    aggregate_type VARCHAR(255),
    aggregate_id UUID,
    payload JSONB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    processed BOOLEAN DEFAULT FALSE
);
```
🔹 **How It Works:**
- The `event_type` column differentiates between various event types (e.g., `OrderCreated`, `PaymentProcessed`).
- The `aggregate_type` represents the entity involved (e.g., `Order`, `Payment`).
- The `payload` stores event data in **JSON format**.
- A **poller reads and publishes** events, marking them as `processed = TRUE`.

🔹 **Pros:**
✅ **Simple to manage** – A single table avoids unnecessary duplication.  
✅ **Scales well for moderate workloads** (especially if indexed properly).  
✅ **Easier to query for auditing** (all events in one place).

🔹 **Cons:**
⚠️ **Increased contention** – High throughput systems may experience row locking issues.  
⚠️ **Slower processing** – If event volume is high, filtering by `event_type` could impact performance.

🔹 **Best Use Case:**
- Works well if **event volume is moderate** and services can **efficiently query events**.

---

#### **2️⃣ Separate Outbox Tables per Event Type**
🔹 **Example:**
```sql
CREATE TABLE order_outbox (
    id UUID PRIMARY KEY,
    event_type VARCHAR(255),
    order_id UUID,
    payload JSONB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    processed BOOLEAN DEFAULT FALSE
);

CREATE TABLE payment_outbox (
    id UUID PRIMARY KEY,
    event_type VARCHAR(255),
    payment_id UUID,
    payload JSONB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    processed BOOLEAN DEFAULT FALSE
);
```

🔹 **How It Works:**
- Each event type (e.g., `Order Events`, `Payment Events`) has its own **dedicated outbox table**.
- **Different pollers** or Kafka consumers can process events independently.

🔹 **Pros:**
✅ **Higher throughput** – No contention between different event types.  
✅ **Easier partitioning and indexing** – Each table can be optimized separately.  
✅ **Better failure isolation** – A failure in one event type doesn't block others.

🔹 **Cons:**
⚠️ **More schema management** – Requires separate tables and maintenance.  
⚠️ **Harder to audit** – Events are spread across multiple tables.

🔹 **Best Use Case:**
- Ideal for **high-volume systems** with frequent writes (e.g., e-commerce, fintech).
- Useful when **different event types have vastly different processing needs**.

---

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

### **Final Recommendation**
| Approach | Best For | Scalability | Complexity |
|----------|---------|------------|------------|
| **Single Outbox Table** | Small-to-medium workloads, simple architecture | Medium | Low |
| **Separate Outbox Tables** | High-throughput applications, different event priorities | High | Medium-High |
| **Partitioned Outbox Table** | Large-scale systems, optimized performance | Very High | Medium |



Trade-offs and Edge Cases:
🔹 **Q: Why Kafka and not RabbitMQ?**  
✔️ **Answer:** Kafka provides **high throughput**, durability, and **better partitioning for scalability**. RabbitMQ is better for **low-latency messaging but doesn’t scale well** for analytics.

🔹 **Q: Why Postgres for bookings instead of DynamoDB?**  
✔️ **Answer:** Postgres ensures **ACID transactions**, preventing **double booking of seats**. DynamoDB is great for high-scale reads but **lacks strong transaction guarantees** without additional overhead.

🔹 **Q: Why MongoDb Vs ElasticSearch vs DynamoDb for search query?**  
✔️ **Answer:**

🔹 **Q: Why CQRS for search?**  
✔️ **Answer:** 

🔹 **Q: how CQRS will be helpful in scaling and how it will work?**  
✔️ **Answer:** 

🔹 **Q: how SSE(server side events) vs REST will be helpful in robust system and how it will work?**  
✔️ **Answer:** 

🔹 **Q: how gRPC will be helpful in robust system and how it will work?**  
✔️ **Answer:** 

🔹 **Q: how Circuit breaker and re-try will be helpful in faul-torrent and how it will work?**  
✔️ **Answer:** 

🔹 **Q: Why SAGA for async data consistency?**  
✔️ **Answer:**


🔹 **Q: Why Cassandra for events and history?**  
✔️ **Answer:**


🔹 **Q: Why memchached vs redis?**  
✔️ **Answer:**


🔹 **Q: Spring context, bean context, security context, multitenancy handling?**  
✔️ **Answer:**


🔹 **Q: how real-world failures and concurrency handling will be done and how it will work?**  
✔️ **Answer:**



Here are detailed answers to your system design interview questions along with additional important trade-offs and questions that might be asked:

---

## **Trade-offs and Edge Cases**

### **Q: Why Kafka and not RabbitMQ?**
✔️ **Answer:**
- **Kafka** is built for **high-throughput, fault-tolerant event streaming** and is best suited for real-time data processing, distributed logging, and analytics.
- **RabbitMQ** is better suited for **low-latency, per-message acknowledgments**, and task queues where immediate processing is required.
- Kafka ensures durability via **log-based storage**, whereas RabbitMQ's **message retention is limited** unless explicitly configured.
- **Scaling Kafka** is more efficient due to its **partitioning** mechanism, whereas RabbitMQ **scales horizontally with more effort**.

✅ **Use Kafka** for log aggregation, event sourcing, stream processing.  
✅ **Use RabbitMQ** for request-response messaging, microservices RPC, transactional event processing.

---

### **Q: Why Postgres for bookings instead of DynamoDB?**
✔️ **Answer:**
- **Postgres** provides **ACID compliance** which prevents **double booking** issues.
- **DynamoDB** has high scalability but **lacks strong transactions guarantees** unless you use DynamoDB Transactions, which add complexity.
- **Joins and complex queries** are better handled in Postgres.
- DynamoDB is **eventually consistent**, making it risky for critical booking systems.

✅ **Use Postgres** if the system requires **strict consistency and complex transactions**.  
✅ **Use DynamoDB** if the system is **read-heavy, requires horizontal scaling, and has simpler transactional needs**.

---

### **Q: Why MongoDB Vs ElasticSearch vs DynamoDB for search query?**
✔️ **Answer:**
| Feature       | **MongoDB** | **ElasticSearch** | **DynamoDB** |
|--------------|------------|------------------|-------------|
| **Query Type** | Document search | Full-text search | Key-value lookup |
| **Indexing** | JSON-based | Advanced inverted index | Hash/Range-based |
| **Use Case** | Product catalog, NoSQL queries | Search engine, Log analytics | Key-based lookups, caching |
| **Scalability** | Sharding & Replication | Sharding & Replication | Auto-scaling |
| **Consistency** | Eventual | Eventual | Strong (optional) |

✅ **Use MongoDB** for document-based storage with filtering.  
✅ **Use Elasticsearch** for **advanced search** (full-text, autocomplete, faceted search).  
✅ **Use DynamoDB** for key-value queries at **high throughput**.

---

### **Q: Why CQRS for search?**
✔️ **Answer:**
- **Command Query Responsibility Segregation (CQRS)** separates **write-heavy workloads from read-heavy workloads**.
- Reduces **read latency** as the system can have **optimized read models** without impacting transactional writes.
- Enables **polyglot persistence**—use **Postgres for commands (writes)** and **Elasticsearch for queries (reads)**.

✅ **Best for:** E-commerce, event-driven systems, analytics dashboards.

---

### **Q: How CQRS helps in scaling and how does it work?**
✔️ **Answer:**
- **Scalability**: Instead of having a single database handling both reads & writes, **CQRS allows scaling reads and writes independently**.
- **How it works?**
    1. **Write Operations** → Go to a transactional database (e.g., Postgres).
    2. **Read Operations** → Go to a search-optimized database (e.g., Elasticsearch).
    3. **Syncing happens** via **event sourcing or asynchronous updates**.

✅ **Prevents read-write contention, making systems more scalable.**

---

### **Q: How SSE (Server-Sent Events) vs REST helps in a robust system and how it works?**
✔️ **Answer:**
- **REST** is **request-response** based; the client must poll for updates.
- **SSE** is **event-driven**, pushing updates **in real-time**.

✅ **Use SSE** for **real-time notifications, stock prices, live dashboards**.  
✅ **Use REST** when updates are not needed frequently.

---

### **Q: How gRPC helps in a robust system and how does it work?**
✔️ **Answer:**
- **gRPC** uses **Protocol Buffers** instead of JSON, making it **lightweight and fast**.
- Supports **bidirectional streaming**, unlike REST.
- Uses **HTTP/2**, reducing network overhead.

✅ **Use gRPC** for **microservices communication with low latency**.

---

### **Q: How Circuit Breaker & Retry help in fault tolerance and how do they work?**
✔️ **Answer:**
- **Circuit Breaker** prevents cascading failures by **blocking requests** to failing services after a threshold.
- **Retry** mechanisms help recover **transient failures** by **re-attempting** failed requests.

✅ **Use Circuit Breaker** for **third-party integrations** to prevent excessive failures.  
✅ **Use Retry** with **exponential backoff** for **network requests**.

---

### **Q: Why SAGA/outbox for async data consistency?**
✔️ **Answer:**
- **Saga** breaks transactions into **multiple compensating transactions** across services.
- Works well in **microservices** where **ACID transactions aren't feasible**.

✅ **Use Saga for** order processing, payments, travel bookings.

---

### **Q: Why Cassandra for events and history?**
✔️ **Answer:**
- **Cassandra** is a **highly available**, distributed NoSQL DB.
- Optimized for **write-heavy workloads**.
- Ideal for **event sourcing** where **append-only logs** are required.

✅ **Use Cassandra for logging, audit trails, and event history.**

---

### **Q: Memcached vs Redis?**
✔️ **Answer:**
| Feature        | **Memcached** | **Redis** |
|---------------|--------------|----------|
| **Data Structure** | Key-Value only | Lists, Sets, Hashes |
| **Persistence** | No persistence | Optional persistence |
| **Use Case** | Simple caching | Advanced caching & pub/sub |

✅ **Use Memcached** for **simple in-memory caching**.  
✅ **Use Redis** for **caching + real-time pub-sub + data structures**.

---

### **Q: Spring Context, Bean Context, Security Context, Multitenancy Handling?**
✔️ **Answer:**
- **Spring Context** → Manages **bean lifecycle**.
- **Bean Context** → Controls **dependency injection**.
- **Security Context** → Stores **user authentication details**.
- **Multitenancy Handling** → Uses **database sharding** or **schema-based separation**.

✅ **Use Multitenancy** in **SaaS applications**.

---

### **Q: How real-world failures and concurrency handling will be done?**
✔️ **Answer:**
- **Failures**: Use **failover replicas, retries, and idempotency**.
- **Concurrency**: Use **Optimistic Locking (eTag), Mutexes, Distributed Locks (Redis, Zookeeper)**.

✅ **Use Distributed Locks in event-driven systems to prevent race conditions.**

---


### partitions works
```sql
No, table partitioning does not create separate tables in the traditional sense, but rather logical divisions of a single table under the hood. The database internally manages partitions as smaller storage units within a single parent table.


CREATE TABLE bookings_2025_01 PARTITION OF bookings
FOR VALUES FROM ('2025-01-01') TO ('2025-02-01');


2. Hash Partitioning (For Load Balancing)
Distributes data evenly across partitions (good for high-traffic tables).
Example: Partitioning users by user_id hash.


CREATE TABLE userDetails (
  id UUID PRIMARY KEY,
  name TEXT,
  email TEXT UNIQUE NOT NULL
) PARTITION BY HASH (id);


```

### **🚀 Does Sharding Create Different Tables Based on Columns?**
No, **sharding does not create different tables based on columns**. Instead, **sharding horizontally distributes rows across multiple databases or physical machines**, while the **table schema remains the same** in each shard.

---

## **📌 Key Differences Between Sharding & Partitioning**
| Feature  | **Sharding** | **Partitioning** |
|----------|------------|----------------|
| **Definition** | Splits data across multiple databases (horizontal scaling). | Splits data within the same database into logical partitions. |
| **How It Works** | Each shard contains a subset of the rows. | Partitions divide a table within the same database. |
| **Managed by** | Application logic or a database sharding service. | The database engine itself (e.g., PostgreSQL, MySQL). |
| **Performance Impact** | 🚀 Scales infinitely by adding more shards. | 🚀 Improves queries but still within a single DB instance. |
| **Complexity** | ❌ Requires distributed query handling & cross-shard joins. | ✅ Easier to implement & query within the same database. |
| **Use Case** | Multi-region applications, very large datasets. | High-volume tables that need optimization. |

---

## **📌 How Sharding Works**
Sharding **divides tables by rows, not columns**. The **schema stays the same**, but **each shard stores only a subset of rows**.

✅ **Example: Sharding a `users` Table Across 3 Databases**
```plaintext
Shard 1 (users_1): Users with user_id 1 - 10M
Shard 2 (users_2): Users with user_id 10M - 20M
Shard 3 (users_3): Users with user_id 20M - 30M
```
- **Query on User 15M → Sent to Shard 2**
- **Query on User 25M → Sent to Shard 3**

🔹 **Schema stays the same across all shards:**
```sql
CREATE TABLE users (
  user_id UUID PRIMARY KEY,
  name TEXT,
  email TEXT UNIQUE,
  created_at TIMESTAMP
);
```
Each shard contains **a different subset of rows**, but the **same table structure**.

---

## **📌 Types of Sharding**
### **1️⃣ Key-Based (Hash) Sharding**
- **Divides rows based on a hash function.**
- Used when **data needs to be evenly distributed.**
- Example:
  ```sql
  SHARD_KEY = user_id % TOTAL_SHARDS;
  ```
    - `user_id 101 → goes to Shard 1`
    - `user_id 204 → goes to Shard 2`

✅ **Best For:** General use cases where **even data distribution** is needed.  
❌ **Downside:** Hard to move data if shard size grows unevenly.

---

### **2️⃣ Range-Based Sharding**
- Divides data **by range of values** (e.g., `user_id`, `created_at`).
- Example:
  ```plaintext
  Shard 1 → user_id 1 - 1M
  Shard 2 → user_id 1M - 2M
  ```
✅ **Best For:** **Time-series data (logs, transactions, analytics).**  
❌ **Downside:** Uneven shard sizes if some ranges have more data.

---

### **3️⃣ Geo-Based (Directory) Sharding**
- Divides data by **geographical location** (e.g., country, region).
- Example:
  ```plaintext
  Shard 1 → Users in USA
  Shard 2 → Users in Europe
  ```
✅ **Best For:** Multi-region applications, social networks.  
❌ **Downside:** Some shards may become too large if one region has more users.

---

## **🔥 Key Takeaways**
✅ **Sharding splits rows into different databases, not columns.**  
✅ **Each shard keeps the same table schema.**  
✅ **Queries are directed to the correct shard based on a sharding key.**  
✅ **Sharding improves scalability but adds complexity.**  
✅ **Partitioning works within a single DB, while sharding spans multiple DBs.**

---
#### there are third party db service to do automically switch db based on  sharding 
#### write own configs and service to switch db service to do automically switch db based on  sharding 
#### write own configs and service to switch db service to do automically switch db based on  multitenet
```java
//✅ Middleware to Intercept Requests & Set Tenant

public class TenantInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String tenantId = request.getHeader("X-Tenant-ID");
        DataSourceContextHolder.setCurrentTenant(tenantId);
        return true;
    }
}

```
#### how same amazon users switches location and search products for same account link india and USA?

#### how to calculate request and restponse datasize. how to check throughput 


### **Scalability and Performance**
1. What is the difference between **scalability** and **performance**?
2. How would you design a system to handle **1 million requests per second**?
3. What are **bottlenecks** in a scalable system, and how do you address them?
4. How does **horizontal scaling** differ from **vertical scaling**?
5. What are some ways to optimize **database queries** for performance?
6. How does **load balancing** improve scalability? and what is load balancer is down 

---

### **Latency vs. Throughput**
7. What’s the difference between **latency** and **throughput**?
8. How do you reduce **latency** in a high-traffic application?
9. How does a **CDN** help improve latency? and aws cloud-front handles user requests?
10. When would you choose **batch processing** vs. **real-time processing**?

---

### **Availability & Consistency (CAP Theorem)**
11. Explain the **CAP theorem** and its implications in distributed systems.
12. Can a system be **CA (Consistency & Availability) but not Partition Tolerant**? Why or why not?
13. When would you choose a **CP (Consistency-Partition Tolerant) system** over an **AP (Availability-Partition Tolerant) system**?
14. How does **eventual consistency** work in distributed databases?
15. What strategies can improve a system’s **availability**?
16. Explain the concept of **failover** and how it is implemented.
17. How do you handle **network partitions** in a distributed system?

---

### **Load Balancing & Traffic Management**
18. How does a **load balancer** work?
19. What’s the difference between **Layer 4** and **Layer 7 load balancing**?
20. What’s the difference between **active-passive** and **active-active** load balancing?
21. How would you implement **global load balancing** across multiple regions?
22. How does a **reverse proxy** differ from a load balancer?
23. Explain the concept of **sticky sessions** in load balancing.
24. How would you handle **server failures** in a load-balanced system?

---

### **Database Scaling & Design**
25. When would you choose a **relational database** vs. a **NoSQL database**?
26. How does **sharding** improve database performance?
27. What are the downsides of **sharding**?
28. Explain **master-slave replication** and **master-master replication**.
29. How does **database partitioning** work?
30. What’s the tradeoff between **denormalization** and **data consistency**?
31. How would you design a database for **a high-write workload**?
32. How would you design a **time-series database**?

---

### **Caching Strategies**
33. What are the different types of **caching**?
34. What is the difference between **cache-aside** and **write-through** caching?
35. How does **write-behind caching** work?
36. What’s the risk of **cache inconsistency**, and how do you handle it?
37. What are **LRU, LFU, and TTL** in caching?
38. How does **CDN caching** work?
39. When would you use **distributed caching** (e.g., Redis, Memcached)?
40. How would you decide **what data to cache**?

---

### **Messaging & Asynchronous Processing**
41. What’s the difference between a **message queue** and a **task queue**?
42. How does **Kafka** compare to **RabbitMQ**?
43. How would you implement **event-driven architecture**?
44. What is **back pressure**, and how do you handle it in a system?
45. How would you process **millions of messages per second** in a queue?
46. What are **at-least-once**, **at-most-once**, and **exactly-once** message delivery semantics?
47. How do you ensure **message ordering** in a distributed system?

---

### **Networking & Communication Protocols**
48. What’s the difference between **TCP and UDP**?
49. When would you choose **gRPC** over **REST**?
50. How does **HTTP/2** improve upon **HTTP/1.1**?
51. How would you implement **real-time communication** (e.g., WebSockets, SSE)?
52. How do you reduce **network latency** in a microservices architecture?

---

### **Security & Authentication**
53. What is **OAuth2**, and how does it work?
54. How do **JWTs** compare to **session-based authentication**?
55. How would you secure **REST APIs**?
56. What are **SQL Injection** and **Cross-Site Scripting (XSS)**, and how do you prevent them?
57. How would you handle **DDoS attacks**?
58. How does **rate limiting** work in APIs?

---

### **Microservices & Service Discovery**
59. What are the **benefits and challenges of microservices**?
60. How do you implement **service discovery** in microservices?
61. What’s the difference between **synchronous and asynchronous communication** in microservices?
62. How would you design a **resilient microservices architecture**?
63. How do you handle **data consistency** in a microservices setup?
64. What is the **Saga pattern**, and how is it used?
65. How do you manage **distributed transactions** in microservices?

---

### 𝗞𝗮𝗳𝗸𝗮 𝗜𝗻𝘁𝗲𝗿𝘃𝗶𝗲𝘄 𝗤𝘂𝗲𝘀𝘁𝗶𝗼𝗻𝘀:
    - Basic Level:
        1. What is Apache Kafka, and what are its core components?
        2. Explain the difference between a topic, partition, and segment.
        3. How does Kafka ensure message ordering?
        4. What is a consumer group in Kafka?
    - Ktables and kafka streams
        examplain in details 

    - Intermediate Level:
        5. How does Kafka achieve fault tolerance?
        6. Explain Kafka's partitioning strategy and how it impacts performance.
        7. Describe Kafka's consumer offset management.

    - Advanced Level:
        8. Explain the concept of exactly-once semantics (EOS) in Kafka.
        9. How would you monitor and optimize Kafka performance in a production environment?
        10. How would you design a Kafka-based system to guarantee data consistency in the event of node failures?

### questions on multi-tenant 
✔ Database-per-tenant offers better isolation but increases complexity.
✔ Table-per-tenant reduces overhead but requires dynamic query handling.
✔ Use Spring Boot with Multi-Tenancy Filters to route database connections dynamically.



### **Real-World System Design Questions**
66. How would you design a **URL shortener like Bitly**?
70. How would you design a **distributed file storage system like Google Drive**?
71. How would you design a **highly available payment processing system**? and they reconcile millions transactions and consistency 
72. How would you design a **rate-limiting system for an API**?
73. How would you design a **Anki-Flashcard system for an API**?
75. Design Instagram.
    Design Tik-Tok
    Design twitter
    Design Uber
    Design What’s up
    Discussion and designing LRU cache.
    Design a garbage collection system.
    Design a system to capture unique addresses in the entire world.
    Design a recommendation system for products.
    Design a toll system for highways.
    Design URL Shortener.
    Design Instant Messenger.
    Design Elevator system.
    Design distributed caching system.
    Design Amazon Locker Service.
    Design Amazon Best Seller Item Service.
76. how billions of like and views are updated on db. how this counter works ? for youtube,
77. how billions messages are hanlded in whatsapp
78. how s3 handle load and support ? and durability, fault-tolerance 
79. how banks make sure balance is consistent even after billions of transactions per day

---



