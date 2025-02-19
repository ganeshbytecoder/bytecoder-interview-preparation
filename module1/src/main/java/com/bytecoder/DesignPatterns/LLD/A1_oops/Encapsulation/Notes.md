
### **Encapsulation in OOPs**
**Encapsulation** is one of the fundamental principles of Object-Oriented Programming (OOPs). It is the process of **binding data (variables) and methods (functions) that operate on the data into a single unit**, typically a **class**. Encapsulation helps in **restricting direct access** to certain details of an object and can prevent unintended interference.

---

### **Key Features of Encapsulation**
1. **Data Hiding:** The internal representation of an object is hidden from the outside world; only the necessary details are exposed.
2. **Controlled Access:** It provides getter and setter methods to control access to variables.
3. **Increased Security:** By restricting direct access to variables, encapsulation prevents unintended modification of data.
4. **Improved Maintainability:** Since implementation details are hidden, changes can be made internally without affecting external code.
5. **Modularity & Reusability:** Encapsulation makes code modular, easier to maintain, and promotes reusability.

---

### **Example of Encapsulation in Java**
```java
class BankAccount {
    private double balance;  // Private variable

    // Constructor
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Getter method to access balance
    public double getBalance() {
        return balance;
    }

    // Setter method to modify balance safely
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Invalid deposit amount");
        }
    }
}
```

---

```java
public interface Person{
    String company = "Hasheding";
    default String getCompany(){
        return company;
    }
    
    String getDetails();
}

public class Customer implements Person{
    private String name;
    private String address;
    
    public Customer(String name, String address){
        this.name = name;
        this.address = address;
        Person.company= "d";
    }
    
    @Override
    public String getDetails(){
        System.out.println(Person.company);
        return String.format("name : %s and address: %s", name, address);
    }
}
```



Yes, **default methods in interfaces** and **methods in abstract classes** both provide default implementations and can be overridden, but there are key differences between them.

---

### **Comparison: Default Methods in Interface vs. Methods in Abstract Class**
| Feature | **Default Method in Interface** | **Method in Abstract Class** |
|---------|---------------------------------|------------------------------|
| **Definition** | A method with a body inside an `interface` using the `default` keyword. | A method with a body inside an `abstract class`. |
| **Overriding** | Can be overridden in implementing classes. | Can be overridden in subclasses. |
| **Access Modifiers** | Can be `public` or `protected` (implicitly `public`). | Can have `public`, `protected`, `private`, or package-private. |
| **Usage** | Used to provide backward compatibility in interfaces. | Used to define common behavior for subclasses. |
| **State (Instance Variables)** | Cannot have instance variables, only constants (`static final`). | Can have instance variables and modify them. |
| **Constructors** | No constructors in interfaces. | Can have constructors to initialize variables. |
| **Multiple Inheritance** | Supports multiple inheritance (a class can implement multiple interfaces). | Does not support multiple inheritance (only single inheritance). |

---

### **Example of Default Method in an Interface**
```java
interface Vehicle {
    default void start() {
        System.out.println("Vehicle is starting...");
    }
}

class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car is starting...");
    }
}

public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.start(); // Output: Car is starting...
    }
}
```
- The method `start()` in the `Vehicle` interface has a default implementation.
- The `Car` class overrides the `start()` method.

---

### **Example of Method in an Abstract Class**
```java
abstract class Appliance {
    void turnOn() {
        System.out.println("Appliance is turning on...");
    }
}

class Fan extends Appliance {
    @Override
    void turnOn() {
        System.out.println("Fan is turning on...");
    }
}

public class Main {
    public static void main(String[] args) {
        Fan fan = new Fan();
        fan.turnOn(); // Output: Fan is turning on...
    }
}
```
- The method `turnOn()` in `Appliance` has a default implementation.
- The `Fan` class overrides the method with its own implementation.

---

### **Key Takeaways**
1. **Both** default methods in interfaces and methods in abstract classes **can be overridden**.
2. **Abstract classes** can maintain state (fields) and have constructors, while **interfaces cannot**.
3. **Multiple inheritance** is supported in interfaces via multiple implementations but not in abstract classes.

Would you like a deeper dive into any of these concepts? 🚀


### **Understanding Multiple Inheritance in Interfaces with Examples**
Java **does not support multiple inheritance** in classes to avoid the **diamond problem**, but it **allows multiple inheritance in interfaces**. This is useful in real-world scenarios where a class needs to inherit behavior from multiple sources.

---

## **1. Interfaces Allow Multiple Inheritance**
A class can **implement multiple interfaces**, inheriting methods from different interfaces. This helps in designing flexible and modular systems.

### **Example: Multiple Interface Inheritance**
```java
interface Payment {
    void processPayment();
}

interface Notification {
    void sendNotification();
}

class OnlineOrder implements Payment, Notification {
    @Override
    public void processPayment() {
        System.out.println("Processing online payment...");
    }

    @Override
    public void sendNotification() {
        System.out.println("Sending email notification...");
    }
}

public class Main {
    public static void main(String[] args) {
        OnlineOrder order = new OnlineOrder();
        order.processPayment();
        order.sendNotification();
    }
}
```
### **Output**
```
Processing online payment...
Sending email notification...
```
### **Explanation**
- The `OnlineOrder` class implements both `Payment` and `Notification` interfaces.
- It **inherits multiple behaviors** and provides its own implementation.

---

## **2. Resolving Method Conflicts by Overriding in Implementing Class**
If **two interfaces have a method with the same name**, Java forces the implementing class to **override the method explicitly** to avoid ambiguity.

### **Example: Resolving Method Conflicts**
```java
interface Printer {
    default void show() {
        System.out.println("Printer is printing...");
    }
}

interface Scanner {
    default void show() {
        System.out.println("Scanner is scanning...");
    }
}

class AllInOneDevice implements Printer, Scanner {
    @Override
    public void show() {
        System.out.println("All-in-One Device:");
        Printer.super.show(); // Explicitly calling Printer's show()
        Scanner.super.show(); // Explicitly calling Scanner's show()
//        not for variables -> use Scanner.variable name only  
    }
}

public class Main {
    public static void main(String[] args) {
        AllInOneDevice device = new AllInOneDevice();
        device.show();
    }
}
```
### **Output**
```
All-in-One Device:
Printer is printing...
Scanner is scanning...
```
### **Explanation**
- `Printer` and `Scanner` both have a `default` method named `show()`.
- `AllInOneDevice` **overrides the `show()` method** to resolve the conflict.
- The overridden method **explicitly calls** `Printer.super.show()` and `Scanner.super.show()`.

---

## **3. Using `InterfaceName.super.method()` to Select a Specific Default Method**
When an implementing class **inherits multiple interfaces with the same default method**, it can explicitly specify which interface’s method to use.

### **Example: Explicit Method Selection**
```java
interface Engine {
    default void start() {
        System.out.println("Engine is starting...");
    }
}

interface Battery {
    default void start() {
        System.out.println("Battery is providing power...");
    }
}

class ElectricCar implements Engine, Battery {
    @Override
    public void start() {
        System.out.println("ElectricCar is starting...");
        Engine.super.start();  // Calls Engine's default method
        Battery.super.start(); // Calls Battery's default method
    }
}

public class Main {
    public static void main(String[] args) {
        ElectricCar car = new ElectricCar();
        car.start();
    }
}
```
### **Output**
```
ElectricCar is starting...
Engine is starting...
Battery is providing power...
```
### **Explanation**
- `Engine` and `Battery` both define a `default` `start()` method.
- `ElectricCar` **overrides** `start()` and uses `Engine.super.start()` and `Battery.super.start()` to call specific implementations.

---

## **Key Takeaways**
✅ **Multiple inheritance in interfaces** allows code reuse and modular design.  
✅ **Method conflicts must be resolved** by overriding in the implementing class.  
✅ **Explicit calling (`InterfaceName.super.method()`)** ensures that the desired method is invoked.

Would you like a real-world use case or modifications to these examples? 🚀
