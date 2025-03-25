### **Monitoring API Call Stats & Tracking Specific Edge Cases Using Actuator in Spring Boot**

Yes, **Spring Boot Actuator** can track **API call statistics** (e.g., number of times an API was called, response times, failures, etc.).  
Additionally, **custom metrics** can be created to track **specific edge cases** using **Micrometer**.

---

## **1️⃣ Tracking API Call Statistics with Actuator**
Spring Boot Actuator, with **Micrometer**, provides built-in metrics for API calls, including:
- **Total request count**
- **Response time**
- **Status codes**
- **Method execution counts**

### ✅ **Enable Actuator Metrics for HTTP Requests**
Modify `application.properties`:
```properties
# Enable Actuator metrics for HTTP requests
management.endpoints.web.exposure.include=metrics
management.metrics.web.server.request.autotime.enabled=true
```
- This enables **automatic tracking of API request metrics**.

### ✅ **Check API Call Metrics**
Once enabled, you can check the stats using **cURL** or a browser.

#### **1. View All Available Metrics**
```bash
curl http://localhost:8080/actuator/metrics
```
Example Response:
```json
{
  "names": [
    "http.server.requests",
    "jvm.memory.used",
    "process.cpu.usage",
    "system.cpu.usage"
  ]
}
```
- `http.server.requests` → Tracks API call count, response times, status codes.

#### **2. View API Call Count & Response Times**
```bash
curl http://localhost:8080/actuator/metrics/http.server.requests
```
Example Response:
```json
{
  "name": "http.server.requests",
  "measurements": [
    {"statistic": "COUNT", "value": 50},
    {"statistic": "TOTAL_TIME", "value": 100.5}
  ],
  "availableTags": [
    {"tag": "uri", "values": ["/api/users", "/api/orders"]},
    {"tag": "method", "values": ["GET", "POST"]},
    {"tag": "status", "values": ["200", "400", "500"]}
  ]
}
```
- **Total calls (`COUNT`)** → How many times each API was called.
- **Response time (`TOTAL_TIME`)** → Total time spent processing requests.
- **Breakdown by URI, Method, and Status**.

---

## **2️⃣ Track a Specific Edge Case in an API**
If you need to track **a specific event (e.g., an edge case is triggered in an API call)**, you can **define custom metrics using Micrometer**.

### ✅ **Step 1: Add Micrometer Dependency**
If not already added, include the **Micrometer dependency** in `pom.xml`:
```xml
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-core</artifactId>
</dependency>
```

### ✅ **Step 2: Inject `MeterRegistry` for Custom Metrics**
Modify your API to **track the specific edge case**.

#### **Example: Tracking a Special Condition in an API**
```java
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final Counter edgeCaseCounter;

    public OrderController(MeterRegistry meterRegistry) {
        this.edgeCaseCounter = meterRegistry.counter("orders.edge_case.triggered");
    }

    @GetMapping("/api/orders")
    public String getOrder(@RequestParam(required = false) Integer orderId) {
        if (orderId == null || orderId < 0) { // Edge case: Invalid Order ID
            edgeCaseCounter.increment();
            return "Invalid Order ID!";
        }
        return "Order Details for ID: " + orderId;
    }
}
```
### ✅ **Step 3: Check If Edge Case Was Triggered**
```bash
curl http://localhost:8080/actuator/metrics/orders.edge_case.triggered
```
Example Response:
```json
{
  "name": "orders.edge_case.triggered",
  "measurements": [
    {"statistic": "COUNT", "value": 5}
  ]
}
```
- `COUNT: 5` → This means **the edge case (invalid order ID) was triggered 5 times**.

---

## **3️⃣ Visualizing API Stats in Prometheus & Grafana**
If you want **real-time dashboards** for API calls and edge cases, integrate **Prometheus and Grafana**.

### ✅ **Step 1: Enable Prometheus Metrics**
Modify `application.properties`:
```properties
management.metrics.export.prometheus.enabled=true
management.endpoint.prometheus.enabled=true
```

### ✅ **Step 2: Add Prometheus as a Data Source in Grafana**
1. Install **Prometheus** and **Grafana**.
2. Configure **Prometheus to scrape Actuator metrics**.
3. Use **Grafana Dashboards** to visualize:
  - Total API Calls (`http.server.requests`)
  - Edge Case Triggers (`orders.edge_case.triggered`)

---

## **4️⃣ Summary**
| Feature | How to Track |
|---------|------------|
| **Total API Calls** | `http.server.requests` in Actuator |
| **Calls per URI & Method** | `http.server.requests?tag=uri:/api/orders` |
| **Custom Edge Cases** | Define a **Micrometer Counter** and increment it |
| **Check Edge Case Count** | `orders.edge_case.triggered` metric |
| **Monitor in Grafana** | Integrate **Prometheus & Grafana** |

---

### ✅ **Final Thoughts**
- **Actuator tracks API call statistics automatically.**
- **For tracking specific edge cases**, use **Micrometer custom metrics**.
- **Use Prometheus & Grafana** for **visual dashboards**.
- Works **without modifying database logs**.

Would you like an **example dashboard setup in Grafana** for tracking API calls and edge cases? 🚀

### **Monitoring Circuit Breaker Metrics Using Actuator in Spring Boot (Resilience4j)**
Spring Boot provides **Actuator** to monitor and expose application metrics, including **Resilience4j Circuit Breaker metrics**.

---

## **1️⃣ Dependencies Required**
First, make sure you have the necessary dependencies in `pom.xml`.

### ✅ **Add Dependencies (`Spring Boot Actuator` + `Resilience4j` + `Micrometer Prometheus`)**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot2</artifactId>
</dependency>

<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
```
- `spring-boot-starter-actuator` → Enables **monitoring endpoints**.
- `resilience4j-spring-boot2` → Provides **Circuit Breaker support**.
- `micrometer-registry-prometheus` → Allows integration with **Prometheus** for monitoring.

---

## **2️⃣ Enable Actuator and Circuit Breaker Metrics**
Modify `application.properties` to enable Actuator and metrics:
```properties
# Enable Actuator endpoints
management.endpoints.web.exposure.include=health,metrics

# Enable Circuit Breaker metrics
resilience4j.circuitbreaker.metrics.enabled=true

# Enable Prometheus metrics
management.metrics.export.prometheus.enabled=true
management.endpoint.prometheus.enabled=true
```
- **`management.endpoints.web.exposure.include=health,metrics`** → Enables metrics API.
- **`resilience4j.circuitbreaker.metrics.enabled=true`** → Enables Circuit Breaker metrics.

---

## **3️⃣ Create a Circuit Breaker Example**
### ✅ **Define Circuit Breaker Configuration**
```java
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class CircuitBreakerService {

    @CircuitBreaker(name = "backendService", fallbackMethod = "fallbackResponse")
    public String getData() {
        throw new RuntimeException("Service is down!"); // Simulating failure
    }

    public String fallbackResponse(Exception e) {
        return "Fallback response: Service temporarily unavailable.";
    }
}
```
- **`@CircuitBreaker(name = "backendService")`** → Defines the circuit breaker.
- **`fallbackResponse()`** → Executes when failures occur.

---

## **4️⃣ Create a REST Controller to Trigger Circuit Breaker**
```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerController {
    
    private final CircuitBreakerService service;

    public CircuitBreakerController(CircuitBreakerService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public String testCircuitBreaker() {
        return service.getData();
    }
}
```
- Call **`/test`** multiple times to trigger failures.

---

## **5️⃣ Monitor Circuit Breaker Metrics via Actuator**
Spring Boot Actuator exposes **Circuit Breaker metrics**.

### ✅ **View Available Metrics**
1️⃣ **Get All Metrics:**
```bash
curl http://localhost:8080/actuator/metrics
```
Example response:
```json
{
    "names": [
        "jvm.memory.used",
        "resilience4j.circuitbreaker.calls",
        "resilience4j.circuitbreaker.state"
    ]
}
```
- **`resilience4j.circuitbreaker.calls`** → Tracks total calls (successes & failures).
- **`resilience4j.circuitbreaker.state`** → Shows Circuit Breaker state.

2️⃣ **Get Specific Circuit Breaker Metrics:**
```bash
curl http://localhost:8080/actuator/metrics/resilience4j.circuitbreaker.calls
```
Example response:
```json
{
    "name": "resilience4j.circuitbreaker.calls",
    "measurements": [
        {"statistic": "COUNT", "value": 10.0}
    ],
    "availableTags": [
        {"tag": "kind", "values": ["successful", "failed", "not_permitted"]},
        {"tag": "name", "values": ["backendService"]}
    ]
}
```
- Shows total **successful, failed, and rejected requests**.

---

## **6️⃣ (Optional) Integrate with Prometheus and Grafana**
### ✅ **1. Start Prometheus (If Using for Monitoring)**
Create a `prometheus.yml` file:
```yaml
global:
  scrape_interval: 5s

scrape_configs:
  - job_name: 'spring-boot-app'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['localhost:8080']
```
Then start Prometheus:
```bash
./prometheus --config.file=prometheus.yml
```

### ✅ **2. Visualize Circuit Breaker Metrics in Grafana**
1. Install **Grafana**.
2. Add **Prometheus** as a data source.
3. Create a **dashboard** using `resilience4j_circuitbreaker_calls_total`.

---

## **7️⃣ Conclusion**
✔ **Spring Boot Actuator** allows **monitoring Circuit Breaker metrics** easily.  
✔ `@CircuitBreaker` tracks **success/failure calls** automatically.  
✔ **Actuator + Prometheus + Grafana** provide **real-time monitoring**.  
✔ Works **without external tools** but **can be enhanced with Prometheus**.



### **Comparison: Micrometer vs Actuator in Spring Boot**
Both **Micrometer** and **Spring Boot Actuator** help monitor application metrics, but they serve different purposes. Here’s a detailed comparison and when to use each.

---

## **1️⃣ What is Spring Boot Actuator?**
✅ **Spring Boot Actuator** provides built-in endpoints to **monitor and manage** your application via **REST APIs** (e.g., `/actuator/metrics`).  
✅ It is **a higher-level abstraction** that provides **predefined metrics** like API call counts, system health, and JVM stats.  
✅ **Primary Purpose**: Exposes **metrics and operational data** via REST **without requiring an external monitoring tool**.

### **Example Actuator Metrics**
```bash
curl http://localhost:8080/actuator/metrics/http.server.requests
```
- Shows total API calls, response times, and HTTP statuses.

---

## **2️⃣ What is Micrometer?**
✅ **Micrometer** is a **metrics instrumentation library** for Spring Boot applications.  
✅ It provides **low-level custom metrics** and **integrates with monitoring tools** like **Prometheus, Grafana, New Relic, and Datadog**.  
✅ **Primary Purpose**: Allows developers to create **custom application metrics** beyond the built-in Actuator metrics.

### **Example Custom Counter with Micrometer**
```java
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    
    private final Counter orderFailureCounter;

    public OrderService(MeterRegistry meterRegistry) {
        this.orderFailureCounter = meterRegistry.counter("orders.failed");
    }

    public void processOrder(boolean success) {
        if (!success) {
            orderFailureCounter.increment();
        }
    }
}
```
- Tracks **how many times orders failed**.
- The metric **`orders.failed`** is available in **Prometheus/Grafana**.

---

## **3️⃣ Key Differences Between Actuator and Micrometer**
| Feature | **Spring Boot Actuator** | **Micrometer** |
|---------|-------------------------|---------------|
| **Purpose** | Exposes **predefined system metrics** via REST endpoints | Creates **custom metrics** for application logic |
| **Built-in Metrics** | ✅ Yes (JVM, CPU, API calls, Health) | ❌ No, only custom metrics |
| **Custom Metrics?** | ❌ No (only exposes predefined ones) | ✅ Yes (custom counters, timers, gauges) |
| **Visualization Support** | Basic REST endpoints (`/actuator/metrics`) | ✅ Works with **Prometheus, Grafana, New Relic, Datadog, etc.** |
| **Data Storage** | ❌ No persistent storage (only API response) | ✅ Stores metrics in **external monitoring tools** |
| **Integration with Monitoring Tools** | Limited | ✅ Full integration with **Prometheus, Datadog, Graphite, etc.** |
| **Performance Impact** | ✅ Minimal (only exposes existing data) | 🟡 Medium (depends on how many custom metrics are collected) |
| **Use Case** | Quick app health & performance monitoring | In-depth analysis, custom tracking |

---

## **4️⃣ When to Use Actuator vs Micrometer?**
| **Scenario** | **Best Choice** |
|-------------|---------------|
| **Need built-in API metrics (e.g., request counts, response times)?** | ✅ Use **Actuator** (`/actuator/metrics`) |
| **Want to integrate with Prometheus/Grafana for dashboards?** | ✅ Use **Micrometer** |
| **Need custom counters for business logic (e.g., track failed orders)?** | ✅ Use **Micrometer** |
| **Want lightweight monitoring via REST (without external tools)?** | ✅ Use **Actuator** |
| **Need alerting on failures (via Prometheus/New Relic)?** | ✅ Use **Micrometer** |

---

## **5️⃣ Can Actuator and Micrometer Be Used Together?**
Yes! **Spring Boot Actuator internally uses Micrometer** for collecting metrics.
- Actuator **exposes** metrics via REST (`/actuator/metrics`).
- Micrometer **integrates** with external tools like **Prometheus & Grafana**.

### ✅ **Best Practice: Use Both Together**
1. **Enable Actuator for basic monitoring**.
2. **Use Micrometer for custom metrics & integrations**.

---

## **6️⃣ Final Recommendation**
- **For quick, lightweight monitoring** → Use **Actuator** (`/actuator/metrics`).
- **For custom business logic tracking & external monitoring** → Use **Micrometer**.
- **For full observability** → Use **both Actuator & Micrometer** with **Prometheus & Grafana**.

Would you like a **Prometheus & Grafana setup guide** for visualizing Micrometer metrics? 🚀