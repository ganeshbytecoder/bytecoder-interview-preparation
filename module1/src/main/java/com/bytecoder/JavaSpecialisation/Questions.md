For a **Java technical screening round**, the questions typically cover **core Java concepts, OOP, multithreading, collections, exception handling, Java 8 features, and Spring Boot**. Here’s a structured set of questions based on difficulty levels:
11. Explain **fail-fast vs fail-safe iterators** in Java.
12. How does **ConcurrentHashMap** achieve thread safety?
13. How does Java achieve **memory management** with Garbage Collection?

---

### **Core Java & OOP Concepts**
1. What are the key differences between **abstract classes** and **interfaces**?
2. Explain **method overloading** vs **method overriding** with examples.
3. What is **polymorphism**, and how is it implemented in Java?
4. How does Java achieve **memory management** with Garbage Collection?
5. What is the difference between **final, finally, and finalize**?
6. Explain **deep copy vs shallow copy** in Java.
7. How does Java handle **multiple inheritance**?
8. Anonymous Class

---

### **Java Collections Framework**
8. What are the main differences between **ArrayList** and **LinkedList**?
9. How does a **HashMap** work internally in Java?
10. What is the difference between **HashSet**, **TreeSet**, and **LinkedHashSet**?
11. Explain **fail-fast vs fail-safe iterators** in Java.
12. How does **ConcurrentHashMap** achieve thread safety?
13. What is the difference between LinkedHashMap , TreeMap and HashMap

---


---

### **Exception Handling**
20. What is the difference between **checked and unchecked exceptions**?
21. Explain how **try-with-resources** works in Java.
22. Can we override a method that throws an exception with a method that does not?
23. How does **custom exception handling** work?

---

### **Java 8 & Functional Programming**
24. What are **lambda expressions**, and how do they improve code readability?
25. Explain the difference between **map()** and **flatMap()** in Java Streams.
26. What are the benefits of using **Optional<>** in Java?
27. How does **Stream API** works and examples of collecting list, array, map etc
27. How does **Stream API** help in parallel processing?
28. What is the difference between **Collectors.toList()** and **Collectors.toMap()**?

---

### **Spring Boot & Microservices**
29. What are the key annotations in Spring Boot and their usage?
30. How does **Spring Boot manage dependency injection**?
31. What is the difference between **@Component, @Service, and @Repository**?
32. Explain **Spring Boot caching** and how it works.
33. What are **circuit breakers**, and how do they work in microservices?
34. How does **Spring Security** handle authentication and authorization?
35. Explain **Spring Boot Actuator** and its importance in monitoring.
36. Junit, mockito 

---

### **Database & Persistence**
36. What is the difference between **JPA and Hibernate**?
37. How does the **second-level cache** work in Hibernate?
38. What is **lazy loading vs eager loading** in Hibernate?
39. Explain the ACID properties of a transaction.
40. What is the **N+1 query problem**, and how can it be solved?

---

### **System Design & Scalability**
41. How would you design a **URL Shortener** (like Bit.ly) using Java?
42. What is **CQRS**, and how is it used in microservices?
43. Explain **Saga Pattern** and its use in distributed transactions.
44. How would you optimize a **high-throughput API** using Java?
45. What is the role of **Kafka in microservices**?

---


### **Multithreading & Concurrency**
13. Explain the difference between **Thread** and **Runnable** in Java.
14. What are **synchronized blocks** and **locks**? How do they help in concurrency?
15. What is the purpose of **volatile** keyword?
16. Explain the difference between **Executor Framework** and **Thread Pool**.
17. How does **CountDownLatch** and **CyclicBarrier** work?
18. What is the **ForkJoinPool**, and when should it be used?
19. How does Java prevent **deadlocks**, and how can you detect them?
20. threadPool, ExecutorService executor = Executors.newFixedThreadPool(10);




### **When to Use What?**
- **Use `HashSet`** when order does not matter and you need the fastest operations.
- **Use `TreeSet`** when you need elements to be sorted automatically.
- **Use `LinkedHashSet`** when you need to maintain insertion order.

### **When to Use Which?**
- **Use `HashMap`** when you need fast lookups and do not care about order.
- **Use `LinkedHashMap`** when you need fast lookups while preserving insertion order.
- **Use `TreeMap`** when you need sorted keys.
- **Use `Hashtable`** when you need thread safety but do not want to use synchronization manually.
- **Use `ConcurrentHashMap`** for high-performance concurrent operations in a multi-threaded environment.








Java interview questions:  https://github.com/DopplerHQ/awesome-interview-questions?tab=readme-ov-file#java

all Spring containers and connections ->
IOC, Beanfactory, context , Multitenancy , security context , spring context,









1.     You need to handle 1 million requests per second. How would you scale your backend architecture?
2.     If you are building an order management system, how would you design the services? (Database choices, API interactions, scalability)
3.     Design a simple service that asynchronously processes tasks using Spring Boot.
4.     You have a distributed system where one Microservice must call another but should retry on failure. How would you implement this in Spring Boot?
5.     You deployed a Spring Boot service, but it crashes with an "Out of Memory" error. How do you debug this?
6.     Your Spring Boot REST API, which fetches data from a database, suddenly becomes slow. The response time has increased from 100ms to 3 seconds.
7.     Your Spring Boot microservice is running on Kubernetes and after a few hours, it crashes with OutOfMemoryError.
·       What are the possible causes of memory leaks in Java?
·       How to find which objects are causing the memory leak?
·       How to use a profiler (like JVisualVM, YourKit) to detect leaks?

8.     One of your microservices has started consuming high CPU (90%), even though the incoming traffic is normal.
·       How to investigate and identify the root cause?
·       What could cause a thread to enter an infinite loop?
·        How can you profile CPU usage in a running application?

9.     You start your Spring Boot application, but it fails with a "BeanCurrentlyInCreationException" due to a circular dependency.
·       How to debug and fix this issue?
·       What Spring mechanisms help break circular dependencies?

10.  Your Spring Boot app occasionally freezes and stops processing requests.
     ·       How to detect a deadlock in Java?
     ·       How can you use jstack to diagnose the issue?
     ·       How can you avoid deadlocks in database transactions?






**Dependency Injection & Inversion of Control (IoC) - Detailed Notes**

### **What is Dependency Injection (DI)?**
Dependency Injection is a **design pattern** used to achieve **Inversion of Control (IoC)** by **injecting dependencies** into a class rather than letting the class create them itself. This makes the code **loosely coupled**, more testable, and easier to manage.

---

### **Key Concepts of Dependency Injection**
1. **Dependency**: An object that another object depends on.
    - Example: A `Car` depends on an `Engine`.
2. **Injection**: Instead of a class creating its own dependencies, they are provided (injected) from outside.
3. **Inversion of Control (IoC)**: The responsibility of managing dependencies is shifted from the class itself to an external framework or container.

---

### **Inversion of Control (IoC)**
Inversion of Control (IoC) is a design principle in which the control of object creation and dependency management is transferred from the class itself to an external framework or container.

This principle decouples components in a system, making it easier to manage dependencies, improve testability, and maintain clean architecture.

---

### **Example Without Dependency Injection (Tightly Coupled Code)**
```java
class Engine {
    void start() {
        System.out.println("Engine started!");
    }
}

class Car {
    private Engine engine;

    public Car() {  // Car is responsible for creating Engine
        this.engine = new Engine();
    }

    void drive() {
        engine.start();
        System.out.println("Car is moving!");
    }
}
```
💡 **Problem**: The `Car` class is **tightly coupled** to `Engine`, making it difficult to replace `Engine` with another type (e.g., `ElectricEngine`).

---

### **Example With Dependency Injection (Loosely Coupled Code)**
```java
class Engine {
    void start() {
        System.out.println("Engine started!");
    }
}

// Dependency injected via constructor
class Car {
    private Engine engine;

    public Car(Engine engine) {  
        this.engine = engine;  
    }

    void drive() {
        engine.start();
        System.out.println("Car is moving!");
    }
}

// Injecting dependency externally
public class Main {
    public static void main(String[] args) {
        Engine engine = new Engine();  // Created externally
        Car car = new Car(engine);     // Injected via constructor
        car.drive();
    }
}
```
✅ **Advantages of DI**:
- **Loose coupling**: `Car` doesn’t depend on a specific implementation of `Engine`.
- **Easier testing**: You can mock dependencies in unit tests.
- **Better maintainability**: Swap dependencies without modifying the class.

---

### **Types of Dependency Injection**
1. **Constructor Injection** (Recommended)
    - Inject dependencies via the class constructor.
   ```java
   class Car {
       private Engine engine;
       public Car(Engine engine) { this.engine = engine; }
   }
   ```

2. **Setter Injection**
    - Inject dependencies using a setter method.
   ```java
   class Car {
       private Engine engine;
       public void setEngine(Engine engine) { this.engine = engine; }
   }
   ```

3. **Field Injection (Not Recommended)**
    - Directly inject dependencies into fields using annotations like `@Autowired` in Spring.
   ```java
   class Car {
       @Autowired
       private Engine engine;
   }
   ```

---

### **Dependency Injection in Spring Boot (Example)**
Spring Boot uses DI with annotations like `@Component` and `@Autowired`.

```java
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Component
class Engine {
    void start() {
        System.out.println("Engine started!");
    }
}

@Service
class Car {
    private final Engine engine;

    @Autowired  // Spring injects the Engine dependency
    public Car(Engine engine) {
        this.engine = engine;
    }

    void drive() {
        engine.start();
        System.out.println("Car is moving!");
    }
}
```

---

### **Example: Naming a Bean and Injecting by Name**
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// Define Engine Interface
interface Engine {
    void start();
}

// Implementation 1 - Petrol Engine
@Component("petrolEngine")  // Custom Bean Name
class PetrolEngine implements Engine {
    public void start() {
        System.out.println("Petrol Engine started!");
    }
}

// Implementation 2 - Diesel Engine
@Component("dieselEngine")  // Custom Bean Name
class DieselEngine implements Engine {
    public void start() {
        System.out.println("Diesel Engine started!");
    }
}

// Car class with Constructor Injection using Bean Name
@Service
class Car {
    private final Engine engine;

    @Autowired
    public Car(@Qualifier("petrolEngine") Engine engine) {  // Inject by Bean Name
        this.engine = engine;
    }

    void drive() {
        engine.start();
        System.out.println("Car is moving!");
    }
}

// Spring Configuration Class
@Configuration
class AppConfig {
    @Bean(name = "electricEngine")  // Another way to name a bean
    public Engine electricEngine() {
        return new Engine() {
            public void start() {
                System.out.println("Electric Engine started!");
            }
        };
    }
}
```

---

### **Key Points:**
1. **Use `@Component("beanName")`** – Assigns a name to a `@Component` bean.
2. **Use `@Bean(name = "beanName")`** – Assigns a name to a manually created bean in a `@Configuration` class.
3. **Use `@Qualifier("beanName")`** – Specifies which named bean to inject when multiple implementations exist.

📌 **In this example:**
- **`@Qualifier("petrolEngine")`** ensures that `Car` uses `PetrolEngine`.
- You can switch to **`@Qualifier("dieselEngine")`** to use the `DieselEngine` instead.
- **`@Bean(name = "electricEngine")`** shows how to name a bean in a `@Configuration` class.








# Spring Framework Interview Revision Notes 🚀

## 1. Core Concepts

### 1.1 Dependency Injection (DI) & Inversion of Control (IoC)
- **DI**: Design pattern where dependencies are injected rather than created internally
- **IoC**: Broader principle where control of object creation is given to framework
- **Benefits**: Loose coupling, better testability, easier maintenance

#### Quick Reference: IoC vs DI
| Feature | IoC | DI |
|---------|-----|-----|
| Scope | Broader principle | Specific implementation |
| Control | Framework manages objects | Dependencies passed externally |
| Methods | Factory, Service Locator, DI | Constructor, Setter, Field injection |

### 1.2 Types of Dependency Injection
1. **Constructor Injection** (✅ Recommended)
```java
@Service
class Car {
    private final Engine engine;
    
    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }
}
```

2. **Setter Injection**
```java
@Service
class Car {
    private Engine engine;
    
    @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
```

3. **Field Injection** (⚠️ Not Recommended)
```java
@Service
class Car {
    @Autowired
    private Engine engine;
}
```

## 2. Spring Annotations Quick Reference

### 2.1 Component Annotations
- `@Component`: Generic component
- `@Service`: Business logic layer
- `@Repository`: Data access layer
- `@Controller`: Web layer
- `@RestController`: REST API endpoints

### 2.2 Configuration Annotations
- `@Configuration`: Class with bean definitions
- `@Bean`: Method-level annotation for custom beans
- `@ComponentScan`: Specify packages to scan
- `@PropertySource`: External properties file

### 2.3 Dependency Injection
- `@Autowired`: Automatic dependency injection
- `@Qualifier`: Specify which bean to inject
- `@Primary`: Preferred bean when multiple exist
- `@Value`: Inject property values

## 3. Common Interview Questions & Answers

### 3.1 Basic Concepts
Q: **What is Spring Framework?**
- Java framework for building enterprise applications
- Provides comprehensive infrastructure support
- Core features: DI, AOP, Data Access, Web MVC

Q: **What are Spring Boot advantages?**
- Automatic configuration
- Embedded server support
- Starter dependencies
- Production-ready features

### 3.2 Advanced Concepts
Q: **Explain Spring Bean Lifecycle**
1. Instantiation
2. Populate Properties
3. BeanNameAware
4. BeanFactoryAware
5. Pre-initialization (@PostConstruct)
6. InitializingBean
7. Custom init-method
8. Post-initialization
9. Pre-destruction (@PreDestroy)
10. Custom destroy-method

Q: **What is Spring AOP?**
- Aspect-Oriented Programming
- Handles cross-cutting concerns
- Common uses: logging, security, transactions

## 4. Best Practices & Common Pitfalls

### 4.1 Best Practices ✅
1. Use Constructor Injection
2. Keep components small and focused
3. Use appropriate stereotypes
4. Implement proper exception handling
5. Use configuration properties

### 4.2 Common Pitfalls ⚠️
1. Circular dependencies
2. Memory leaks in singleton beans
3. Improper transaction management
4. Missing component scanning
5. Incorrect bean scope usage

## 5. Spring Boot Features

### 5.1 Auto-configuration
- Automatically configures based on dependencies
- Can be customized or disabled
- Conditional configuration

### 5.2 Starter Dependencies
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

### 5.3 Actuator Endpoints
- `/health`: Application health information
- `/metrics`: Application metrics
- `/info`: Application information
- `/env`: Environment properties

## 6. Testing in Spring

### 6.1 Test Annotations
- `@SpringBootTest`: Full application context
- `@WebMvcTest`: Web layer testing
- `@DataJpaTest`: JPA components testing
- `@MockBean`: Mock dependencies

### 6.2 Example Test
```java
@SpringBootTest
class CarServiceTest {
    @Autowired
    private CarService carService;
    
    @MockBean
    private EngineRepository engineRepo;
    
    @Test
    void testCarStart() {
        // Test implementation
    }
}
```

## 7. Quick Reference: Bean Scopes

| Scope | Description |
|-------|-------------|
| singleton | One instance per Spring container |
| prototype | New instance each time requested |
| request | One instance per HTTP request |
| session | One instance per HTTP session |
| application | One instance per ServletContext |

## 8. Performance Optimization Tips
1. Use lazy initialization where appropriate
2. Optimize component scanning
3. Use appropriate bean scopes
4. Implement caching strategies
5. Profile and monitor application

## 9. Security Best Practices
1. Use Spring Security
2. Implement proper authentication
3. Use HTTPS
4. Validate input
5. Implement proper session management

---
📌 **Remember**:
- Focus on understanding core concepts
- Practice explaining with examples
- Be ready to discuss real-world applications
- Know common pitfalls and solutions


