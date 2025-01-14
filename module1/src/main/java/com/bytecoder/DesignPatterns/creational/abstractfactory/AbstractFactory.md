

# **🚀 Chapter 4: Factory Pattern - Detailed Notes**

## **📌 Overview**
The **Factory Pattern** is a **creational design pattern** that provides an interface for creating objects in a superclass while allowing subclasses to alter the type of objects that will be created. It promotes **loose coupling** and adheres to the **Single Responsibility Principle (SRP)** and **Open/Closed Principle (OCP)**.

---

## **📌 Why Use the Factory Pattern?**
🚀 **Key Problems It Solves:**
1. **Removes Tight Coupling:** The client code does not need to instantiate concrete classes directly.
2. **Encapsulates Object Creation Logic:** Centralizes object creation in one place.
3. **Supports Open/Closed Principle (OCP):** New product types can be added without modifying existing code.
4. **Enhances Maintainability & Scalability:** Reduces the impact of changes in object instantiation logic.

---

## **📌 Types of Factory Patterns**
### ✅ **1. Simple Factory (Not a true design pattern)**
- A single method creates objects based on input parameters.
- **Not flexible**; violates **OCP** (new types require modification).

### ✅ **2. Factory Method Pattern (True Factory Pattern)**
- Defines an interface for object creation but lets subclasses decide which class to instantiate.
- **Encapsulates object creation** in a dedicated method (`createProduct()`).

### ✅ **3. Abstract Factory Pattern**
- Provides an interface for creating families of related objects without specifying their concrete classes.
- **Useful for UI themes, cross-platform applications, and database connectors.**

---

## **📌 Implementation of Factory Patterns in Java**

### **1️⃣ Simple Factory (Basic Implementation)**
**Scenario:**  
Imagine we need to create different types of **Pizza** objects dynamically based on user input.

```java
// Step 1: Define a Common Interface
interface Pizza {
    void prepare();
}

// Step 2: Implement Concrete Pizza Classes
class CheesePizza implements Pizza {
    @Override
    public void prepare() { System.out.println("Preparing Cheese Pizza"); }
}

class PepperoniPizza implements Pizza {
    @Override
    public void prepare() { System.out.println("Preparing Pepperoni Pizza"); }
}

// Step 3: Create a Simple Factory Class
class PizzaFactory {
    public static Pizza createPizza(String type) {
        return switch (type.toLowerCase()) {
            case "cheese" -> new CheesePizza();
            case "pepperoni" -> new PepperoniPizza();
            default -> throw new IllegalArgumentException("Invalid pizza type");
        };
    }
}

// Step 4: Client Code
public class PizzaStore {
    public static void main(String[] args) {
        Pizza pizza = PizzaFactory.createPizza("cheese"); // Factory creates the pizza
        pizza.prepare();
    }
}
```

✅ **Pros of Simple Factory:**
- Centralized object creation.
- Reduces dependency on concrete classes.

🚫 **Cons of Simple Factory:**
- Violates OCP (must modify factory when adding a new type).
- Still requires `if-else` or `switch-case`.

---

### **2️⃣ Factory Method Pattern (Encapsulated Object Creation)**
**Scenario:**  
Each pizza store has different specializations (New York & Chicago).

```java
// Step 1: Define an Abstract Product
interface Pizza {
    void prepare();
}

// Step 2: Implement Concrete Products
class NYStyleCheesePizza implements Pizza {
    @Override
    public void prepare() { System.out.println("Preparing NY Style Cheese Pizza"); }
}

class ChicagoStyleCheesePizza implements Pizza {
    @Override
    public void prepare() { System.out.println("Preparing Chicago Style Cheese Pizza"); }
}

// Step 3: Create an Abstract Factory Method
abstract class PizzaStore {
    abstract Pizza createPizza();

    public Pizza orderPizza() {
        Pizza pizza = createPizza(); // Factory Method
        pizza.prepare();
        return pizza;
    }
}

// Step 4: Implement Concrete Factories
class NYPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza() {
        return new NYStyleCheesePizza();
    }
}

class ChicagoPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza() {
        return new ChicagoStyleCheesePizza();
    }
}

// Step 5: Client Code
public class FactoryMethodPattern {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        nyStore.orderPizza();  // "Preparing NY Style Cheese Pizza"
    }
}
```

✅ **Benefits of Factory Method Pattern:**
- **Open/Closed Principle (OCP)** – New products can be added without modifying existing factories.
- **Encapsulation** – Object creation is abstracted from the client.
- **Flexibility** – Different implementations for different contexts.

🚫 **Downsides:**
- **Complexity** – Requires extra classes for each factory.

---

### **3️⃣ Abstract Factory Pattern (Families of Objects)**
**Scenario:**  
We need to create **Pizza ingredients (Dough, Sauce, Cheese)** dynamically based on location.

```java
// Step 1: Define Abstract Factory Interface
interface PizzaIngredientFactory {
    String createDough();
    String createSauce();
    String createCheese();
}

// Step 2: Implement Concrete Factories
class NYIngredientFactory implements PizzaIngredientFactory {
    @Override public String createDough() { return "Thin Crust Dough"; }
    @Override public String createSauce() { return "Marinara Sauce"; }
    @Override public String createCheese() { return "Reggiano Cheese"; }
}

class ChicagoIngredientFactory implements PizzaIngredientFactory {
    @Override public String createDough() { return "Thick Crust Dough"; }
    @Override public String createSauce() { return "Plum Tomato Sauce"; }
    @Override public String createCheese() { return "Mozzarella Cheese"; }
}

// Step 3: Define Abstract Pizza Class
abstract class Pizza {
    protected PizzaIngredientFactory ingredientFactory;

    public Pizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    void prepare() {
        System.out.println("Preparing pizza with " + ingredientFactory.createDough()
                + ", " + ingredientFactory.createSauce() + ", and " + ingredientFactory.createCheese());
    }
}

// Step 4: Implement Concrete Pizzas
class CheesePizza extends Pizza {
    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        super(ingredientFactory);
    }
}

// Step 5: Implement Factory Usage
public class AbstractFactoryPattern {
    public static void main(String[] args) {
        PizzaIngredientFactory nyFactory = new NYIngredientFactory();
        Pizza pizza = new CheesePizza(nyFactory);
        pizza.prepare();
    }
}
```

✅ **Benefits of Abstract Factory:**
- Supports **cross-platform compatibility** (UI Toolkits, OS-specific components).
- Follows **Single Responsibility Principle (SRP)** – Object creation logic is separate.
- Works well for **families of related objects**.

🚫 **Cons:**
- More **complex** than other patterns.
- Requires **multiple factory classes**.

---

## **📌 Real-World Applications of Factory Pattern**
✔ **Spring Framework** – `BeanFactory`, `ApplicationContext`  
✔ **Database Connections** – `ConnectionFactory` in JDBC  
✔ **Logging Frameworks** – `LoggerFactory` in SLF4J  
✔ **Cloud Services** – AWS SDK uses factories for creating service clients

---

## **📌 Factory Pattern vs Other Creational Patterns**

| **Pattern**  | **Use Case** |
|-------------|-------------|
| **Factory Method** | When a class **delegates object creation** to subclasses |
| **Abstract Factory** | When creating **families of objects** that should work together |
| **Builder** | When **step-by-step construction** of objects is required |
| **Prototype** | When **cloning existing objects** is needed |

---

## **📌 Summary**
🔹 **Factory Pattern** abstracts object creation, promoting **loose coupling**.  
🔹 Used in **Spring, Java I/O, Database Factories, UI Toolkits**.  
🔹 **Simple Factory** → Basic implementation.  
🔹 **Factory Method** → Encapsulates object creation in subclasses.  
🔹 **Abstract Factory** → Creates families of objects.

Would you like **interview questions & answers** for **Factory Pattern**? 🚀


# **🚀 Factory Pattern - Senior-Level Interview Questions & Answers**

## **📌 Conceptual Questions**

### **1. What is the Factory Pattern, and why is it used?**
✅ **Answer:**  
The **Factory Pattern** is a **creational design pattern** that provides an interface for creating objects but lets subclasses decide which class to instantiate. It is used to:
- **Encapsulate object creation logic**
- **Promote loose coupling** between client code and concrete implementations
- **Support the Open/Closed Principle (OCP)** by allowing new types without modifying existing code
- **Centralize complex object creation logic**

📌 **Example:**  
Instead of using `new` directly, we use a factory method:
```java
interface Pizza { void prepare(); }

class CheesePizza implements Pizza {
    @Override public void prepare() { System.out.println("Cheese Pizza Ready"); }
}

class PizzaFactory {
    public static Pizza createPizza(String type) {
        return switch (type.toLowerCase()) {
            case "cheese" -> new CheesePizza();
            default -> throw new IllegalArgumentException("Invalid type");
        };
    }
}
```
📌 **Why is this useful?**
- If new pizza types are added, the **client code does not change**; only the factory changes.

---

### **2. What are the types of Factory Patterns? When should each be used?**
✅ **Answer:**  
| **Type** | **Usage** |
|---------|----------|
| **Simple Factory (Static Factory Method)** | Centralized object creation but violates OCP (modifications required for new types). |
| **Factory Method Pattern** | Subclasses define which concrete class to instantiate. Useful when multiple families of objects are needed. |
| **Abstract Factory Pattern** | Used when a system needs to create **families of related objects** without specifying their concrete classes. |

📌 **Example of Factory Method Pattern:**
```java
// Factory Method in Abstract Class
abstract class PizzaStore {
    abstract Pizza createPizza(); // Factory Method
    public Pizza orderPizza() {
        Pizza pizza = createPizza();
        pizza.prepare();
        return pizza;
    }
}

// Concrete Factory
class NYPizzaStore extends PizzaStore {
    @Override Pizza createPizza() { return new CheesePizza(); }
}
```
---

### **3. How does the Factory Pattern promote the Open/Closed Principle (OCP)?**
✅ **Answer:**
- The **client code does not change** when new product types are introduced.
- Object creation is encapsulated in the **factory class**.
- New concrete implementations can be **added without modifying existing code**.

📌 **Example:**
```java
class PepperoniPizza implements Pizza {
    @Override public void prepare() { System.out.println("Pepperoni Pizza Ready"); }
}
```
- We **only update the Factory**:
```java
class PizzaFactory {
    public static Pizza createPizza(String type) {
        return switch (type.toLowerCase()) {
            case "cheese" -> new CheesePizza();
            case "pepperoni" -> new PepperoniPizza(); // New product added
            default -> throw new IllegalArgumentException("Invalid type");
        };
    }
}
```
🚀 **Client code remains unchanged!**

---

### **4. How does the Factory Pattern compare to the Builder Pattern?**
✅ **Answer:**  
| **Feature** | **Factory Pattern** | **Builder Pattern** |
|------------|----------------|----------------|
| **Purpose** | Creates different product types | Constructs complex objects step-by-step |
| **Flexibility** | Good for **families of objects** | Good for **complex objects with many parameters** |
| **Usage** | Abstracts object creation | Allows customization while building an object |

📌 **Use Factory when:** You need to create different subclasses of an object based on parameters.  
📌 **Use Builder when:** You need to **build objects progressively** with **custom configurations**.

---

### **5. What are the advantages and disadvantages of using the Factory Pattern?**
✅ **Answer:**  
✅ **Advantages:**  
✔ Promotes **loose coupling**  
✔ Supports **Open/Closed Principle (OCP)**  
✔ Centralizes complex object creation  
✔ Improves **testability** by injecting dependencies

🚫 **Disadvantages:**  
❌ Can **increase complexity** if not used properly  
❌ **Performance overhead** if object creation is lightweight and doesn't require encapsulation

---

## **📌 Design & Implementation Questions**

### **6. How would you design a Factory Pattern for a payment processing system?**
✅ **Answer:**
- Different payment methods (`CreditCardPayment`, `PayPalPayment`, `UPIPayment`) implement a common interface.
- A **Factory Method** decides which payment processor to instantiate.

```java
// Step 1: Define Strategy Interface
interface PaymentProcessor {
    void processPayment(double amount);
}

// Step 2: Implement Concrete Payment Processors
class CreditCardPayment implements PaymentProcessor {
    @Override public void processPayment(double amount) {
        System.out.println("Processing Credit Card Payment: $" + amount);
    }
}

class PayPalPayment implements PaymentProcessor {
    @Override public void processPayment(double amount) {
        System.out.println("Processing PayPal Payment: $" + amount);
    }
}

// Step 3: Factory Class
class PaymentFactory {
    public static PaymentProcessor getPaymentProcessor(String method) {
        return switch (method.toLowerCase()) {
            case "creditcard" -> new CreditCardPayment();
            case "paypal" -> new PayPalPayment();
            default -> throw new IllegalArgumentException("Invalid payment method");
        };
    }
}

// Step 4: Client Code
public class PaymentApp {
    public static void main(String[] args) {
        PaymentProcessor processor = PaymentFactory.getPaymentProcessor("creditcard");
        processor.processPayment(100.0);
    }
}
```
📌 **Follow-up:**
- _How would you extend this for cryptocurrency payments?_
- _How would you handle retries and transaction failures?_

---

### **7. How would you implement a Factory Pattern using Java 8 Functional Interfaces?**
✅ **Answer:**  
Java 8 introduced **Function<T, R>**, which allows creating a factory using **lambda expressions** instead of switch statements.

```java
import java.util.Map;
import java.util.function.Supplier;

interface Shape { void draw(); }
class Circle implements Shape { @Override public void draw() { System.out.println("Drawing Circle"); } }
class Square implements Shape { @Override public void draw() { System.out.println("Drawing Square"); } }

class ShapeFactory {
    private static final Map<String, Supplier<Shape>> factoryMap = Map.of(
        "circle", Circle::new,
        "square", Square::new
    );

    public static Shape createShape(String type) {
        Supplier<Shape> supplier = factoryMap.get(type.toLowerCase());
        if (supplier != null) return supplier.get();
        throw new IllegalArgumentException("Invalid shape type");
    }
}

// Usage
public class FunctionalFactory {
    public static void main(String[] args) {
        Shape shape = ShapeFactory.createShape("circle");
        shape.draw();
    }
}
```
✅ **Why is this better?**  
✔ Eliminates `switch-case` logic  
✔ Improves maintainability  
✔ Uses **lazy initialization**

---

### **8. How does the Factory Pattern improve testability?**
✅ **Answer:**
- Instead of **hardcoding object creation**, **factories return an instance of an interface**, making it easy to inject **mock objects** during unit testing.

📌 **Example: Injecting a Mock Payment Processor in Unit Tests**
```java
class MockPaymentProcessor implements PaymentProcessor {
    @Override public void processPayment(double amount) {
        System.out.println("Mock payment processed: $" + amount);
    }
}

// Unit Test
public class PaymentTest {
    public static void main(String[] args) {
        PaymentProcessor mockProcessor = new MockPaymentProcessor();
        mockProcessor.processPayment(50.0);  // Using mock instead of real processor
    }
}
```
🚀 **Factory Pattern makes testing easier by allowing dependency injection.**

---

## **📌 Real-World Applications**
✔ **Spring Framework** – `BeanFactory` & `ApplicationContext`  
✔ **Database Connections** – `ConnectionFactory` in JDBC  
✔ **Logging Frameworks** – `LoggerFactory` in SLF4J  
✔ **Cloud Services** – AWS SDK uses factories for service clients

---

🔥 **Would you like more system design interview questions based on Factory Pattern?** 🚀