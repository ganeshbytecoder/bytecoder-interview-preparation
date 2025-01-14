# **🚀 Factory Method Pattern - Detailed Notes**

## **📌 Overview**
The **Factory Method Pattern** is a **creational design pattern** that provides an interface for creating objects **in a superclass**, but **allows subclasses to alter the type of objects that will be created**.

📌 **Key Concepts:**
- Defines an interface for **object creation** but **delegates the actual instantiation to subclasses**.
- **Promotes loose coupling** between client code and concrete implementations.
- **Encapsulates object creation logic**, making the system easier to extend.

---

## **📌 Factory Method Pattern Structure**

### **1️⃣ Define a Factory Method Interface (Abstract Creator)**
```java
abstract class Transport {
    abstract void deliver();
}
```

### **2️⃣ Implement Concrete Products (Concrete Classes)**
```java
class Truck extends Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by land in a truck.");
    }
}

class Ship extends Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by sea in a ship.");
    }
}
```

### **3️⃣ Define Abstract Factory Class (Factory Method)**
```java
abstract class Logistics {
    // Factory Method
    abstract Transport createTransport();

    public void planDelivery() {
        Transport transport = createTransport();
        transport.deliver();
    }
}
```

### **4️⃣ Implement Concrete Factory Classes**
```java
class RoadLogistics extends Logistics {
    @Override
    Transport createTransport() {
        return new Truck();
    }
}

class SeaLogistics extends Logistics {
    @Override
    Transport createTransport() {
        return new Ship();
    }
}
```

### **5️⃣ Client Code**
```java
public class FactoryMethodDemo {
    public static void main(String[] args) {
        Logistics logistics = new RoadLogistics(); // Concrete Factory
        logistics.planDelivery();  // Output: Delivering by land in a truck.

        logistics = new SeaLogistics();
        logistics.planDelivery();  // Output: Delivering by sea in a ship.
    }
}
```
---

## **📌 Key Principles of Factory Method**
✔ **Encapsulation** → Encapsulates object creation logic in factory methods.  
✔ **Loose Coupling** → Client code depends on an interface, not a specific class.  
✔ **Extensibility** → New product types can be introduced without modifying existing code.  
✔ **Single Responsibility Principle (SRP)** → Separates object creation from business logic.

---

## **📌 When to Use the Factory Method Pattern?**
📌 **Use Factory Method Pattern when:**  
✅ The **exact type of object is unknown at compile time**.  
✅ You need to **delegate object creation to subclasses**.  
✅ A **class should not depend on concrete classes**, only on abstractions.

📌 **Real-World Examples:**  
✔ **Database Drivers (JDBC DriverManager)** → Creates a connection based on database type (`MySQL`, `PostgreSQL`).  
✔ **Logging Frameworks (SLF4J, Log4j)** → Decides which logging system to use at runtime.  
✔ **Spring Framework (`BeanFactory`)** → Creates and manages application beans dynamically.

---

# **🚀 Factory Method vs. Abstract Factory Pattern**

## **📌 Factory Method Pattern**
| **Feature** | **Factory Method** |
|------------|------------------|
| **Purpose** | Defines an interface for creating objects but lets subclasses decide which class to instantiate. |
| **Product Type** | Creates **one type** of object at a time. |
| **Flexibility** | Allows subclasses to **override the creation process**. |
| **Example** | `Logistics.createTransport()` produces `Truck` or `Ship`. |

📌 **Diagram:**
```plaintext
                  Logistics (Abstract Factory)
                       |
        --------------------------------
        |                              |
  RoadLogistics                  SeaLogistics
        |                              |
      Truck                           Ship
```
---

## **📌 Abstract Factory Pattern**
| **Feature** | **Abstract Factory** |
|------------|------------------|
| **Purpose** | Creates **families of related objects** without specifying concrete classes. |
| **Product Type** | Creates **multiple types of related objects**. |
| **Flexibility** | Uses **composition** to decide object creation. |
| **Example** | `GUIFactory.createButton()` and `GUIFactory.createCheckbox()` return Windows/Mac GUI components. |

📌 **Diagram:**
```plaintext
       GUIFactory (Abstract Factory)
                |
  ---------------------------------
  |                               |
WindowsFactory              MacFactory
  |                               |
Button                        Checkbox
```

### **📌 Example: Abstract Factory Pattern**
```java
// Abstract Factory
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Concrete Factory 1
class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() { return new WindowsButton(); }
    @Override
    public Checkbox createCheckbox() { return new WindowsCheckbox(); }
}

// Concrete Factory 2
class MacFactory implements GUIFactory {
    @Override
    public Button createButton() { return new MacButton(); }
    @Override
    public Checkbox createCheckbox() { return new MacCheckbox(); }
}

// Abstract Product Interfaces
interface Button { void paint(); }
interface Checkbox { void render(); }

// Concrete Products for Windows
class WindowsButton implements Button {
    @Override public void paint() { System.out.println("Windows Button"); }
}

class WindowsCheckbox implements Checkbox {
    @Override public void render() { System.out.println("Windows Checkbox"); }
}

// Concrete Products for Mac
class MacButton implements Button {
    @Override public void paint() { System.out.println("Mac Button"); }
}

class MacCheckbox implements Checkbox {
    @Override public void render() { System.out.println("Mac Checkbox"); }
}

// Client Code
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        GUIFactory factory = new WindowsFactory();
        Button button = factory.createButton();
        button.paint();  // Output: Windows Button
    }
}
```

---

## **📌 Factory Method vs. Abstract Factory - Key Differences**
| Feature | Factory Method | Abstract Factory |
|---------|---------------|-----------------|
| **Purpose** | Lets subclasses decide which class to instantiate | Creates **families of related objects** |
| **Type of Creation** | Single object type | Multiple related object types |
| **Object Creation Control** | Uses **inheritance** to decide object creation | Uses **composition** to manage object creation |
| **Usage Scenario** | When a class should delegate instantiation to subclasses | When a system needs to create related objects together |
| **Example** | `Logistics.createTransport()` creates `Truck` or `Ship` | `GUIFactory.createButton()` and `createCheckbox()` create Windows/Mac UI elements |

---

## **📌 When to Use Factory Method vs. Abstract Factory?**
| **Use Case** | **Best Choice** |
|-------------|----------------|
| You need a single object but the exact subclass is unknown | ✅ **Factory Method** |
| You need to produce **multiple related objects** | ✅ **Abstract Factory** |
| You want **subclasses to control object creation** | ✅ **Factory Method** |
| You want to manage multiple families of products | ✅ **Abstract Factory** |

---

# **🚀 Summary**
✅ **Factory Method Pattern** → Defines an interface for creating objects but lets **subclasses decide** which class to instantiate.  
✅ **Abstract Factory Pattern** → Provides an interface to create **families of related objects** without specifying their concrete classes.

📌 **Key Takeaways:**  
✔ Factory Method is **best for single object creation** while Abstract Factory is **best for multiple related objects**.  
✔ Both patterns **encapsulate object creation**, promoting **loose coupling and scalability**.  
✔ **Abstract Factory is more complex** but is **ideal for UI frameworks, database drivers, and device-specific configurations**.

🔥 **Would you like senior-level interview questions based on Factory Method & Abstract Factory?** 🚀