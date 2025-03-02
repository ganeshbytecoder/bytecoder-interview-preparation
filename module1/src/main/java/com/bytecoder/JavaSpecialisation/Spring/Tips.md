Yes, Spring (Spring Boot) follows a **one-thread-per-request** model by default. When a request comes in, it is handled by a thread pulled from a **thread pool**, managed by **Tomcat (or any other embedded web server like Jetty or Undertow)**.

---

## **🔍 How Spring Handles API Requests with Threads**
1. **Client sends an HTTP request**.
2. The request is received by **Spring Boot's embedded Tomcat** (or another servlet container).
3. Tomcat uses a **thread pool** (default: `NioEndpoint` worker threads) and assigns a **worker thread** to handle the request.
4. The worker thread processes the request, invokes the appropriate controller method, and waits for the response.
5. Once the response is sent, the thread is **released back to the pool**.

---

## **🛠️ Thread Pool Configuration in Spring Boot (Tomcat Default)**
Spring Boot uses **Tomcat’s thread pool**, which is **configurable** via `application.properties`:

```properties
server.tomcat.threads.max=200     # Max worker threads
server.tomcat.threads.min-spare=10  # Minimum spare threads (idle)
```
- `max=200`: **Up to 200 threads can handle concurrent requests**.
- `min-spare=10`: **Keeps at least 10 idle threads ready**.

---

## **🚀 How to Customize Thread Pool for Better Performance?**
### **1️⃣ Custom Thread Pool Executor in Spring**
By default, Spring Boot controllers execute on **Tomcat’s worker threads**, but we can define a custom **thread pool for async tasks**.

#### **Create a Custom Thread Pool Executor**
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig {

    @Bean(name = "customExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);  // Minimum active threads
        executor.setMaxPoolSize(50);   // Maximum allowed threads
        executor.setQueueCapacity(100); // Max tasks waiting in queue
        executor.setThreadNamePrefix("CustomThread-");
        executor.initialize();
        return executor;
    }
}
```
Now, you can use this executor in your services for **async execution**.

---

### **2️⃣ Using `@Async` to Offload Work to a Separate Thread Pool**
```java
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Async("customExecutor")  // Uses the custom thread pool
    public void asyncMethod() {
        System.out.println(Thread.currentThread().getName() + " is processing...");
    }
}
```
🔹 **Benefit:** The request-handling thread is **immediately freed**, and the work is processed asynchronously.

---

## **🔄 Blocking vs. Non-Blocking Request Handling**
### **1️⃣ Default Blocking I/O (One Thread per Request)**
- Traditional Spring Boot applications **block the thread** until the response is ready.
- Works well for **CPU-bound** tasks but **wastes** threads on I/O operations (e.g., DB calls, HTTP calls).

### **2️⃣ Non-Blocking (Reactive) with WebFlux**
- Spring WebFlux (`spring-boot-starter-webflux`) enables **non-blocking request processing**.
- Uses **Netty** instead of Tomcat.
- Instead of waiting, the thread is **freed** and returned to the pool while I/O happens.

#### **Example: WebFlux Controller**
```java
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReactiveController {

    @GetMapping("/data")
    public Mono<String> fetchData() {
        return Mono.fromSupplier(() -> {
            simulateSlowService(); // Simulate DB or API delay
            return "Processed!";
        });
    }

    private void simulateSlowService() {
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
    }
}
```
**🔹 Advantage:** This **does not block** a thread while waiting for I/O, making it efficient for high-concurrency applications.

---

## **✅ Summary**
| **Feature** | **Default Spring Boot (Tomcat)** | **Async with `@Async`** | **Spring WebFlux (Reactive)** |
|-------------|--------------------------------|-------------------------|------------------------------|
| **Thread Handling** | One thread per request | Separate thread pool for async tasks | Non-blocking, event-driven |
| **Best for** | Standard REST APIs | Background tasks (email, logs, etc.) | High concurrency, async I/O |
| **Thread Pool** | Tomcat worker threads | Custom `ThreadPoolTaskExecutor` | Netty event loop |

Would you like help configuring **async processing** or switching to **WebFlux** for high-performance applications? 🚀