
## Week 6 **Implementing Multi-Tenancy with Dynamic Data Source Switching**

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



Here are detailed answers to your system design interview questions along with additional important trade-offs and questions that might be asked:

---

## ** partitions and Sharding Cases**

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