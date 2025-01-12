# **📌 2. What is Encapsulation?**
📌 **Definition:**  
✅ **Encapsulation** is the process of **hiding an object’s internal state (data) and restricting direct access to it**.  
✅ **It answers "How an object maintains its state and protects data integrity".**  
✅ Implemented using **Access Modifiers (`private`, `protected`, `public`)**.

📌 **Key Features:**
- **Protects data from unintended modification.**
- **Encapsulated fields require getter & setter methods for controlled access.**
- **Increases security by restricting access to data.**
- Methods are implemented using **Access Modifiers (`private`, `protected`, `public`)** for controlled access.


### **✅ Real-World Analogy for Encapsulation**
💡 **A Bank Account**:
- **Balance is private** (Users cannot modify it directly).
- Users **withdraw or deposit money through methods** (`getBalance()`, `deposit()`, `withdraw()`).

---

### **✅ Example: Encapsulation in Java**
```java
// 🔹 Bank Account Class (Encapsulation Example)
class BankAccount {
    private double balance; // Private variable (Data Hiding)

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Getter Method (Controlled Access)
    public double getBalance() {
        return balance;
    }

    // Setter Method (Controlled Modification)
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
}

// 🔹 Usage
public class EncapsulationExample {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        System.out.println("Current Balance: $" + account.getBalance());
        account.deposit(500);
        System.out.println("Updated Balance: $" + account.getBalance());
    }
}
```
✅ **Key Takeaways for Encapsulation:**
- **Encapsulates the `balance` variable (Private access).**
- **Restricts direct modification (`balance` cannot be accessed directly).**
- **Provides controlled access via `getBalance()` and `deposit()`.**

---













# **🚀 Abstraction vs. "Is-a" Relationship vs Polymorphism in OOP**

# **📌 1. What is Abstraction?**
📌 **Definition:**  
✅ **Abstraction** is the process of **hiding implementation details** and exposing only the **essential features** of an object.  
✅ It focuses on **"what an object does"** rather than **"how it does it"**.

📌 **Key Features:**
- **Implemented using Abstract Classes and Interfaces**.
- **Hides implementation details** (Encapsulation).
- **Defines behavior that subclasses must implement**.

### **✅ Example of Abstraction (Abstract Class)**
```java
// 🔹 Abstract Class (Defines "What" but Not "How")
abstract class Animal {
    abstract void makeSound(); // Abstract Method (No Implementation)
    
    public void sleep() { // Concrete Method
        System.out.println("Sleeping...");
    }
}

// 🔹 Concrete Class (Implements Abstract Methods)
class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof! Woof!");
    }
}

// 🔹 Usage
public class AbstractionExample {
    public static void main(String[] args) {
        Animal dog = new Dog();
        dog.makeSound();  // Output: Woof! Woof!
        dog.sleep();      // Output: Sleeping...
    }
}
```
✅ **Key Takeaways for Abstraction:**
- **Animal is abstract** because it does not define how `makeSound()` works.
- **Dog provides implementation for `makeSound()`**.
- **Users only care about `makeSound()` and `sleep()`, not how it works internally**.

---

# **📌 2. What is an "Is-a" Relationship? (Inheritance)**
📌 **Definition:**  
✅ **"Is-a" Relationship** represents **Inheritance** in OOP, where a subclass **inherits** properties and behavior from a parent class.  
✅ It defines **hierarchical relationships** (e.g., Dog **is-a** Animal).

📌 **Key Features:**
- **Implemented using `extends` keyword in Java**.
- **Allows code reuse** by inheriting fields and methods.
- **Supports method overriding (Polymorphism).**

### **✅ Example of "Is-a" Relationship (Inheritance)**
```java
// 🔹 Parent Class
class Vehicle {
    void start() {
        System.out.println("Vehicle is starting...");
    }
}

// 🔹 Subclass (Inheritance: Car IS-A Vehicle)
class Car extends Vehicle {
    void honk() {
        System.out.println("Car is honking!");
    }
}

// 🔹 Usage
public class InheritanceExample {
    public static void main(String[] args) {
        Car myCar = new Car();
        myCar.start();  // Inherited from Vehicle
        myCar.honk();   // Defined in Car
    }
}
```
✅ **Key Takeaways for "Is-a" Relationship (Inheritance):**
- **Car "is-a" Vehicle** (inherits behavior).
- **Car can use `start()` method from Vehicle** without redefining it.

---

# **📌 3. Key Differences Between Abstraction and "Is-a" (Inheritance)**

| **Feature** | **Abstraction** | **"Is-a" (Inheritance)** |
|------------|---------------|----------------|
| **Definition** | Hides implementation details | Establishes a parent-child relationship |
| **Purpose** | Focuses on **what** an object does | Represents **hierarchical relationships** |
| **Implementation** | Uses **Abstract Classes & Interfaces** | Uses **Inheritance (`extends`)** |
| **Code Reusability** | **Forces subclasses** to provide implementation | **Allows subclasses** to inherit behavior |
| **Example** | `Animal` defines `makeSound()`, but does not implement it | `Car` inherits `start()` from `Vehicle` |

---

# **📌 4. How Abstraction and "Is-a" Work Together**
📌 **Scenario:**  
✅ **Abstraction defines common behavior** (`Animal`).  
✅ **"Is-a" (Inheritance) allows specific behavior** (`Dog IS-A Animal`).

```java
// 🔹 Abstract Class (Abstraction)
abstract class Animal {
    abstract void makeSound();
}

// 🔹 Subclass (Inheritance)
class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow!");
    }
}

// 🔹 Usage
public class AbstractionAndInheritance {
    public static void main(String[] args) {
        Animal cat = new Cat(); // Cat IS-A Animal
        cat.makeSound();  // Output: Meow!
    }
}
```
✅ **Key Takeaways:**
- **Abstraction (`Animal`) defines behavior but does not implement it.**
- **"Is-a" (Inheritance) allows `Cat` to inherit and implement `makeSound()`.**

---

# **📌 5. When to Use Abstraction vs. "Is-a" (Inheritance)?**
| **Use Case** | **Best Choice** | **Why?** |
|-------------|---------------|--------|
| **You want to hide implementation details.** | ✅ **Abstraction** | Defines only the essential features. |
| **You need to enforce method implementation.** | ✅ **Abstraction** | Abstract methods must be implemented by subclasses. |
| **You want to model hierarchical relationships.** | ✅ **Inheritance ("Is-a")** | Child class extends Parent class. |
| **You need code reusability.** | ✅ **Inheritance ("Is-a")** | Inherited methods avoid redundant code. |
| **You need multiple implementations of the same behavior.** | ✅ **Abstraction** | Different subclasses implement methods differently. |

---

# **📌 6. Combining Abstraction & "Is-a" for a Real-World System**
📌 **Scenario:**  
✅ **Abstraction (`BankAccount`) defines behavior** like `deposit()`, `withdraw()`.  
✅ **"Is-a" Relationship (`SavingsAccount` and `CheckingAccount` inherit BankAccount).**

```java
// 🔹 Abstract Class (Abstraction)
abstract class BankAccount {
    protected double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    abstract void withdraw(double amount);

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
    }
}

// 🔹 Subclass (Inheritance: SavingsAccount IS-A BankAccount)
class SavingsAccount extends BankAccount {
    public SavingsAccount(double balance) { super(balance); }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + " from Savings Account.");
        } else {
            System.out.println("Insufficient balance!");
        }
    }
}

// 🔹 Subclass (Inheritance: CheckingAccount IS-A BankAccount)
class CheckingAccount extends BankAccount {
    public CheckingAccount(double balance) { super(balance); }

    @Override
    public void withdraw(double amount) {
        balance -= amount;
        System.out.println("Withdrew $" + amount + " from Checking Account (Overdraft allowed).");
    }
}

// 🔹 Usage
public class BankingSystem {
    public static void main(String[] args) {
        BankAccount savings = new SavingsAccount(500);
        BankAccount checking = new CheckingAccount(1000);

        savings.withdraw(200);
        checking.withdraw(1200);
    }
}
```
✅ **Key Takeaways:**
- **`BankAccount` is abstract (Abstraction)** → `withdraw()` must be implemented by subclasses.
- **`SavingsAccount` & `CheckingAccount` use "Is-a" relationship** → They inherit common behavior.

---

# **📌 3. What is Polymorphism?**
📌 **Definition:**  
✅ **Polymorphism allows multiple implementations of the same interface or method**.  
✅ **It enables flexibility & extensibility** in OOP.

📌 **Types of Polymorphism:**  
✅ **Compile-time (Method Overloading)** → Same method name, different parameters.  
✅ **Runtime (Method Overriding)** → Subclass provides a specific implementation.

### **✅ Example 1: Method Overloading (Compile-time Polymorphism)**
```java
class MathOperations {
    // Overloaded methods (Same name, different parameters)
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}

// 🔹 Usage
public class OverloadingExample {
    public static void main(String[] args) {
        MathOperations math = new MathOperations();
        System.out.println(math.add(2, 3));       // Output: 5
        System.out.println(math.add(2, 3, 4));    // Output: 9
    }
}
```
✅ **Key Takeaways for Method Overloading:**
- **Same method name, but different signatures (parameters).**
- **Decided at compile-time (Static Binding).**

---

### **✅ Example 2: Method Overriding (Runtime Polymorphism)**
```java
// 🔹 Parent Class
class Animal {
    void makeSound() {
        System.out.println("Animal makes a sound.");
    }
}

// 🔹 Subclass (Overriding makeSound method)
class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("Meow!");
    }
}

// 🔹 Usage
public class OverridingExample {
    public static void main(String[] args) {
        Animal myAnimal = new Cat(); // Dynamic Binding
        myAnimal.makeSound();  // Output: Meow! (Calls Cat's version)
    }
}
```
✅ **Key Takeaways for Method Overriding:**
- **The method behavior is redefined in a subclass.**
- **Decided at runtime (Dynamic Binding).**

---

# **📌 4. Key Differences Between Abstraction, "Is-a" (Inheritance), and Polymorphism**

| **Feature** | **Abstraction** | **"Is-a" Relationship (Inheritance)** | **Polymorphism** |
|------------|---------------|----------------|----------------|
| **Definition** | Hides implementation details | Establishes parent-child relationship | Allows multiple implementations of the same method |
| **Purpose** | Defines behavior without implementation | Enables code reuse | Provides flexibility in method behavior |
| **Implementation** | **Abstract Classes & Interfaces** | **`extends` keyword (Superclass-Subclass)** | **Method Overloading & Overriding** |
| **Code Reusability** | No code reuse (Only method definitions) | **Yes (Subclass reuses Parent class methods)** | **No direct reuse, but allows variations in behavior** |
| **Binding Type** | **Compile-time (Interfaces, Abstract Classes)** | **Compile-time (Extends keyword)** | **Compile-time (Overloading) & Runtime (Overriding)** |
| **Example** | `Animal` defines `makeSound()`, but does not implement it | `Dog IS-A Animal`, so it inherits behavior | `Animal.makeSound()` behaves differently for `Dog`, `Cat`, etc. |

---

# **📌 5. How They Work Together**
✅ **Abstraction ensures behavior is defined but not implemented.**  
✅ **Inheritance ("Is-a") allows code reuse & shared behavior.**  
✅ **Polymorphism allows flexibility by overriding or overloading behavior.**

### **✅ Example: Combining Abstraction, Inheritance & Polymorphism**
```java
// 🔹 Abstract Class (Abstraction)
abstract class Animal {
    abstract void makeSound(); // Must be implemented by subclasses

    public void sleep() {
        System.out.println("Sleeping...");
    }
}

// 🔹 Subclass (Inheritance + Polymorphism)
class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof! Woof!");
    }
}

// 🔹 Subclass (Inheritance + Polymorphism)
class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow!");
    }
}

// 🔹 Usage
public class OOPExample {
    public static void main(String[] args) {
        Animal myDog = new Dog(); // Polymorphism
        Animal myCat = new Cat();

        myDog.makeSound();  // Output: Woof! Woof!
        myCat.makeSound();  // Output: Meow!
        myDog.sleep();      // Output: Sleeping...
    }
}
```
✅ **Key Takeaways:**
- **Abstraction (`Animal` class) defines behavior but doesn’t implement it.**
- **Inheritance (`Dog` and `Cat` extend Animal) allows code reuse.**
- **Polymorphism (`makeSound()` behaves differently for Dog & Cat).**

---

# **🚀 Final Summary**
| **Concept** | **Purpose** | **Example** |
|------------|------------|-------------|
| **Abstraction** | Defines behavior but hides implementation | `Animal` class with abstract `makeSound()` method |
| **"Is-a" (Inheritance)** | Enables parent-child relationship & code reuse | `Dog IS-A Animal`, so it inherits `eat()` method |
| **Polymorphism** | Allows method variations in different classes | `makeSound()` is overridden in `Dog` and `Cat` |




---

# **📌 1. Top Interview Questions on Abstraction, "Is-a" (Inheritance), and Polymorphism**

## **🟢 Conceptual Questions**
### **Q1: What is the difference between Abstraction and Encapsulation?**
✅ **Expected Answer:**
- **Abstraction hides implementation details** and only exposes functionality.
- **Encapsulation hides data using access modifiers (`private`, `protected`)**.

✅ **Example:**
```java
abstract class BankAccount {  // Abstraction (Hides details)
    protected double balance;
    abstract void withdraw(double amount);
}
class SavingsAccount extends BankAccount {
    @Override
    void withdraw(double amount) { balance -= amount; }
}
```
- Here, **BankAccount abstracts withdrawal logic**, while **Encapsulation protects `balance`**.

### **Q2: How does Java achieve multiple inheritance using Abstraction?**
✅ **Expected Answer:**
- **Java does not support multiple inheritance with classes** but supports it using **Interfaces (Abstraction).**
- **A class can implement multiple interfaces but extend only one class.**

✅ **Example:**
```java
interface Drivable { void drive(); }
interface Flyable { void fly(); }

class FlyingCar implements Drivable, Flyable {
    @Override public void drive() { System.out.println("Driving..."); }
    @Override public void fly() { System.out.println("Flying..."); }
}
```

### **Q3: When should you prefer Composition to Inheritance ("Is-a")?**
✅ **Expected Answer:**
- **Use Inheritance ("Is-a") when objects have a natural parent-child relationship**.
- **Use Composition ("Has-a") when behavior needs flexibility.**

✅ **Example:**
```java
// Inheritance (Tightly Coupled)
class Car extends Vehicle {}

// Composition (Loosely Coupled)
class Car {
    private Engine engine;  // "Has-a" relationship
}
```

---

## **🟢 Advanced Technical Questions**
### **Q4: How does method overriding support runtime polymorphism?**
✅ **Expected Answer:**
- **Method overriding allows subclasses to define their own implementation of a method** defined in a superclass.
- **At runtime, Java dynamically binds the method call to the correct implementation (Dynamic Dispatch).**

✅ **Example:**
```java
class Animal { void makeSound() { System.out.println("Animal sound"); } }
class Dog extends Animal { @Override void makeSound() { System.out.println("Bark"); } }

public class Test {
    public static void main(String[] args) {
        Animal myAnimal = new Dog(); // Runtime Polymorphism
        myAnimal.makeSound(); // Output: Bark
    }
}
```

### **Q5: How does Java resolve overloaded methods at compile-time?**
✅ **Expected Answer:**
- **Overloaded methods are resolved using method signatures at compile-time.**
- **Java determines which method to call based on:**  
  ✅ Exact match → ✅ Widening → ✅ Autoboxing → ✅ Varargs

✅ **Example:**
```java
class MathOps {
    void add(int a, int b) { System.out.println(a + b); }
    void add(double a, double b) { System.out.println(a + b); }
}
```
- **Calling `add(5, 10.5)` calls `double` method, not `int` method.**

---

## **🟢 System Design Questions**
### **Q6: How would you design a Payment Processing System using OOP principles?**
✅ **Expected Answer:**
- **Use Abstraction** (`PaymentMethod` interface) to define `processPayment()`.
- **Use Inheritance** (`CreditCard`, `PayPal` classes extend `PaymentMethod`).
- **Use Polymorphism** to allow dynamic payment selection.

✅ **Example Design:**
```java
// Abstraction
abstract class PaymentMethod {
    abstract void processPayment(double amount);
}

// Inheritance & Polymorphism
class CreditCard extends PaymentMethod {
    @Override void processPayment(double amount) { System.out.println("Paid $" + amount + " via Credit Card."); }
}
class PayPal extends PaymentMethod {
    @Override void processPayment(double amount) { System.out.println("Paid $" + amount + " via PayPal."); }
}

// Payment Processor (Uses Polymorphism)
class PaymentProcessor {
    public void pay(PaymentMethod method, double amount) { method.processPayment(amount); }
}

// Usage
public class PaymentSystem {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        processor.pay(new CreditCard(), 100.0);  // Runtime Polymorphism
    }
}
```
### **Follow-up Questions:**
- _How would you extend the system for new payment methods?_
- _How would you handle transaction failures?_ (_Answer: Retry Mechanism, Event-driven processing_).

---

# **📌 2. System Design Case Studies Using Abstraction, Inheritance, and Polymorphism**
## **🟢 Case Study 1: Designing a Ride-Sharing System (Uber, Lyft)**
📌 **Objective:** Build a system to manage **Drivers, Riders, and Rides.**

✅ **Key Concepts Used:**
- **Abstraction:** `RideService` defines `requestRide()`, but does not implement it.
- **Inheritance ("Is-a"):** `UberRide` and `LyftRide` extend `RideService`.
- **Polymorphism:** `requestRide()` works differently for Uber & Lyft.

✅ **Architecture:**
```java
// Abstract Class (Abstraction)
abstract class RideService {
    abstract void requestRide(String location, String destination);
}

// Subclasses (Inheritance)
class UberRide extends RideService {
    @Override void requestRide(String location, String destination) { System.out.println("Uber ride requested!"); }
}

class LyftRide extends RideService {
    @Override void requestRide(String location, String destination) { System.out.println("Lyft ride requested!"); }
}

// Ride Booking (Polymorphism)
class RideBooking {
    public void bookRide(RideService ride, String loc, String dest) {
        ride.requestRide(loc, dest); // Calls correct method at runtime
    }
}

// Usage
public class RideSharingSystem {
    public static void main(String[] args) {
        RideBooking rideBooking = new RideBooking();
        rideBooking.bookRide(new UberRide(), "Downtown", "Airport");
    }
}
```
✅ **Follow-up Questions:**
- _How would you scale this for millions of users?_ (_Answer:_ Use **Geo-Distributed Microservices**).
- _How would you calculate ride fares dynamically?_ (_Answer:_ Use **Strategy Pattern for pricing**).

---

## **🟢 Case Study 2: Building an Employee Management System**
📌 **Objective:** Manage different types of employees in an organization.

✅ **Key Concepts Used:**
- **Abstraction:** `Employee` defines `calculateSalary()` but does not implement it.
- **Inheritance ("Is-a"):** `FullTimeEmployee` and `PartTimeEmployee` extend `Employee`.
- **Polymorphism:** `calculateSalary()` is implemented differently.

✅ **Architecture:**
```java
// Abstract Class (Abstraction)
abstract class Employee {
    protected String name;
    public Employee(String name) { this.name = name; }
    abstract double calculateSalary();
}

// Subclasses (Inheritance)
class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name) { super(name); }
    @Override double calculateSalary() { return 5000; }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name) { super(name); }
    @Override double calculateSalary() { return 2000; }
}

// Polymorphism in Action
class PayrollSystem {
    public void processSalary(Employee emp) {
        System.out.println(emp.name + "'s Salary: $" + emp.calculateSalary());
    }
}

// Usage
public class EmployeeManagementSystem {
    public static void main(String[] args) {
        PayrollSystem payroll = new PayrollSystem();
        payroll.processSalary(new FullTimeEmployee("Alice"));
    }
}
```
✅ **Follow-up Questions:**
- _How would you integrate HR policies dynamically?_ (_Answer:_ Use **Decorator Pattern** for benefits).

---

# **🚀 Final Takeaways**
| **Concept** | **Use Case** | **Example** |
|------------|------------|-------------|
| **Abstraction** | Defines contract | `Vehicle` abstract class |
| **Inheritance ("Is-a")** | Code reuse | `Car IS-A Vehicle` |
| **Polymorphism** | Method flexibility | `calculateSalary()` behaves differently |












# **🚀 Association vs Composition vs Aggregation in Object-Oriented Programming (OOP)**
**Association, Composition, and Aggregation** are fundamental relationships in **Object-Oriented Programming (OOP)** that define how objects interact with each other.

---

# **📌 1. What is Association?**
📌 **Association** represents a **general relationship** between two objects. It does not enforce ownership or dependency. Objects **can exist independently** and interact with each other.

✅ **Key Characteristics:**
- **"Uses-a" relationship**
- **No strict dependency** between objects
- **Can be one-to-one, one-to-many, many-to-many**

---

## **🔹 Example 1: Association (Student and Library)**
📌 **Scenario:**  
A **Student and a Library** are related but **can exist independently**. A **Student uses the Library**, but both can exist separately.

```java
// 🔹 Student Class (Independent Entity)
class Student {
    private final String name;

    public Student(String name) {
        this.name = name;
    }

    public void borrowBook(Library library) {
        System.out.println(name + " is borrowing a book from " + library.getLibraryName());
    }
}

// 🔹 Library Class (Independent Entity)
class Library {
    private final String libraryName;

    public Library(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLibraryName() {
        return libraryName;
    }
}

// 🔹 Usage
public class AssociationExample {
    public static void main(String[] args) {
        Student student = new Student("Alice");
        Library library = new Library("City Library");

        student.borrowBook(library);
    }
}
```
✅ **Key Takeaways for Association:**
- **No ownership**: The Student and Library are **loosely connected**.
- **Both objects can exist independently**.

---


# **🚀 Composition vs Aggregation in Object-Oriented Programming (OOP)**
Both **Composition** and **Aggregation** represent a **"Has-a" relationship** between objects. However, they differ in **ownership, dependency, and object lifecycle**.

---

# **📌 1. Composition vs Aggregation - Key Differences**
| **Aspect** | **Composition** | **Aggregation** |
|------------|---------------|---------------|
| **Definition** | Strong relationship: One object **owns** another. | Weak relationship: One object **uses** another. |
| **Dependency** | **Child cannot exist without Parent.** | **Child can exist independently of Parent.** |
| **Lifespan** | **Child’s lifecycle is tied to the Parent.** | **Child can outlive the Parent.** |
| **Type** | **Strong "Has-a" relationship.** | **Weak "Has-a" relationship.** |
| **Example** | `Car HAS-A Engine` (Engine dies if Car is destroyed). | `University HAS-A Student` (Student exists even if University is closed). |

---

# **📌 2. Composition Example - Strong "Has-a" Relationship**
📌 **Scenario:**  
A **Car has an Engine**. If the **Car is destroyed, the Engine is also destroyed**.

```java
// 🔹 Engine Class (Tightly Coupled with Car)
class Engine {
    private String type;

    public Engine(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

// 🔹 Car Class (COMPOSITION: Owns Engine)
class Car {
    private final Engine engine; // Strong Dependency

    public Car(String engineType) {
        this.engine = new Engine(engineType); // Engine is part of Car
    }

    public void start() {
        System.out.println("Car with " + engine.getType() + " engine is starting.");
    }
}

// 🔹 Usage
public class CompositionExample {
    public static void main(String[] args) {
        Car car = new Car("V8");
        car.start();  // Output: Car with V8 engine is starting.
    }
}
```
✅ **Key Takeaways for Composition:**
- **Car owns Engine (Strong dependency).**
- **Engine cannot exist without Car.**
- **When Car is destroyed, Engine is also destroyed.**

---

# **📌 3. Aggregation Example - Weak "Has-a" Relationship**
📌 **Scenario:**  
A **University has Students**. But **Students can exist without the University**.

```java
import java.util.ArrayList;
import java.util.List;

// 🔹 Student Class (Independent Entity)
class Student {
    private final String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// 🔹 University Class (AGGREGATION: Uses Students)
class University {
    private final String name;
    private final List<Student> students; // Weak Dependency

    public University(String name) {
        this.name = name;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void showStudents() {
        System.out.println("Students in " + name + ":");
        for (Student s : students) {
            System.out.println("- " + s.getName());
        }
    }
}

// 🔹 Usage
public class AggregationExample {
    public static void main(String[] args) {
        University university = new University("Harvard");
        Student alice = new Student("Alice");
        Student bob = new Student("Bob");

        university.addStudent(alice);
        university.addStudent(bob);

        university.showStudents();
    }
}
```
✅ **Key Takeaways for Aggregation:**
- **University has a list of Students (Weak dependency).**
- **If the University is destroyed, Students still exist independently.**
- **Allows reusability (Same Student can be in multiple Universities).**

---


# **📌 5. Hybrid Example: Combining Composition & Aggregation**
📌 **Scenario:**  
A **University has Departments** (**Composition**) and **Departments have Professors** (**Aggregation**).

```java
import java.util.ArrayList;
import java.util.List;

// 🔹 Professor Class (Independent Entity)
class Professor {
    private final String name;

    public Professor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// 🔹 Department Class (Aggregation: Professors are Independent)
class Department {
    private final String name;
    private final List<Professor> professors;

    public Department(String name) {
        this.name = name;
        this.professors = new ArrayList<>();
    }

    public void addProfessor(Professor professor) {
        professors.add(professor);
    }

    public void showProfessors() {
        System.out.println("Professors in " + name + " Department:");
        for (Professor p : professors) {
            System.out.println("- " + p.getName());
        }
    }
}

// 🔹 University Class (Composition: Owns Departments)
class University {
    private final String name;
    private final List<Department> departments; // Composition (University owns Departments)

    public University(String name) {
        this.name = name;
        this.departments = new ArrayList<>();
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void showDepartments() {
        System.out.println(name + " University Departments:");
        for (Department d : departments) {
            d.showProfessors();
        }
    }
}

// 🔹 Usage
public class HybridExample {
    public static void main(String[] args) {
        University university = new University("MIT");
        Department cs = new Department("Computer Science");
        Department physics = new Department("Physics");

        Professor alice = new Professor("Dr. Alice");
        Professor bob = new Professor("Dr. Bob");

        cs.addProfessor(alice);
        physics.addProfessor(bob);

        university.addDepartment(cs);
        university.addDepartment(physics);

        university.showDepartments();
    }
}
```
✅ **Why is this Hybrid?**
- **University COMPOSES Departments (Strong ownership, if University closes, Departments disappear).**
- **Departments AGGREGATE Professors (Professors exist independently).**

---


## **📌 5. When to Use Association, Aggregation, or Composition?**
| **Scenario** | **Best Choice** | **Why?** |
|-------------|---------------|--------|
| **Doctor works in a Hospital** | ✅ **Association** | Doctor can work in multiple hospitals, and hospitals exist independently. |
| **Library has Books** | ✅ **Aggregation** | Books can exist outside the Library. |
| **Company has Employees** | ✅ **Aggregation** | Employees exist even if the Company is removed. |
| **Department has Professors** | ✅ **Aggregation** | Professors can work in multiple departments. |
| **Car has an Engine** | ✅ **Composition** | Engine is part of the Car and cannot exist independently. |
| **Human has a Heart** | ✅ **Composition** | Heart is part of the Human and cannot exist separately. |

---

# **🚀 Final Summary**
| **Feature** | **Association** | **Aggregation** | **Composition** |
|------------|---------------|---------------|--------------|
| **Definition** | Objects are related but independent | Objects have a relationship but can exist separately | One object owns another |
| **Type** | "Uses-a" Relationship | "Has-a" (Weak) | "Has-a" (Strong) |
| **Dependency** | No dependency | Child is independent of Parent | Child **cannot exist** without Parent |
| **Lifecycle** | No lifecycle dependency | Child outlives Parent | Child dies with Parent |
| **Example** | **Student uses Library** | **University has Students** | **Car has an Engine** |


---

# **🚀 Has-a Relationship vs. Composite Pattern in Java**

Both **"Has-a" relationships (Composition & Aggregation)** and the **Composite Pattern** allow **object composition**, but they serve different purposes. Below is a **detailed comparison** along with **real-world examples and when to use each.**

---

# **📌 1. Understanding "Has-a" Relationship (Composition & Aggregation)**
### **🔹 Definition:**
- A **"Has-a" relationship** means an object **contains another object** as a field (instance variable).
- There are two types:  
  ✅ **Composition** → Strong ownership (tightly coupled, lifecycle dependency)  
  ✅ **Aggregation** → Weak ownership (loosely coupled, independent lifecycles)

### **✅ Example: "Has-a" Relationship (Car and Engine)**
```java
// 🔹 Composition Example (Car HAS-A Engine)
class Engine {
    private String type;
    public Engine(String type) { this.type = type; }
    public String getType() { return type; }
}

class Car {
    private final Engine engine; // Composition (Car cannot exist without Engine)

    public Car(String engineType) {
        this.engine = new Engine(engineType);
    }

    public void start() {
        System.out.println("Car with " + engine.getType() + " engine is starting.");
    }
}

// 🔹 Usage
public class HasARelationshipExample {
    public static void main(String[] args) {
        Car car = new Car("V8");
        car.start();  // Output: Car with V8 engine is starting.
    }
}
```
### **✅ Key Takeaways:**
- **Composition**: The `Car` **owns** the `Engine`. If the car is destroyed, the engine is also destroyed.
- **Aggregation**: If the `Engine` were shared among multiple `Car` objects, it would be **aggregation** instead.

---

# **📌 2. Understanding Composite Pattern**
### **🔹 Definition:**
- The **Composite Pattern** is a **structural design pattern** that treats individual objects and groups of objects **uniformly**.
- It is used to build **tree-like structures** (e.g., UI components, file systems).

### **✅ Example: Composite Pattern (Folder-File Hierarchy)**
```java
import java.util.ArrayList;
import java.util.List;

// 🔹 Component Interface
interface FileSystem {
    void showDetails();
}

// 🔹 Leaf (Individual File)
class File implements FileSystem {
    private final String name;
    public File(String name) { this.name = name; }
    @Override public void showDetails() { System.out.println("File: " + name); }
}

// 🔹 Composite (Folder containing Files and other Folders)
class Folder implements FileSystem {
    private final String name;
    private final List<FileSystem> children = new ArrayList<>();

    public Folder(String name) { this.name = name; }

    public void add(FileSystem component) { children.add(component); }

    @Override
    public void showDetails() {
        System.out.println("Folder: " + name);
        for (FileSystem child : children) {
            child.showDetails();
        }
    }
}

// 🔹 Usage
public class CompositePatternExample {
    public static void main(String[] args) {
        File file1 = new File("document.txt");
        File file2 = new File("image.jpg");
        
        Folder folder = new Folder("MyFolder");
        folder.add(file1);
        folder.add(file2);

        Folder root = new Folder("Root");
        root.add(folder);

        root.showDetails();
    }
}
```

---

# **📌 3. Key Differences: "Has-a" vs. Composite Pattern**
| Feature | "Has-a" Relationship (Composition & Aggregation) | Composite Pattern |
|---------|---------------------------------|----------------|
| **Definition** | Object **contains another object** | Object **can contain itself** or similar objects |
| **Example** | `Car HAS-A Engine` | `Folder contains Files and Folders` |
| **Hierarchy** | Strict **1-to-1 or 1-to-Many relationship** | **Tree structure (recursive hierarchy)** |
| **Object Lifetime** | **Composition**: Strong ownership (Car cannot exist without Engine) | No strict ownership, child elements can exist independently |
| **Flexibility** | Used for **encapsulation** and **code reuse** | Used for **hierarchical structures** |
| **Common Use Cases** | Car-Engine, Employee-Department | UI components, File systems, Organization charts |

---


# **📌 5. Combining "Has-a" and Composite Pattern**
📌 **Real-world systems often use both** **"Has-a" relationships and Composite Pattern together**.

### **✅ Example: File System with Ownership (Combining Both Concepts)**
```java
import java.util.List;
import java.util.ArrayList;

// 🔹 File Component (Has-a Relationship & Composite)
abstract class FileSystemComponent {
    protected String name;
    public FileSystemComponent(String name) { this.name = name; }
    abstract void showDetails();
}

// 🔹 Leaf: File (Strong Ownership - Composition)
class File extends FileSystemComponent {
    private final String owner;

    public File(String name, String owner) {
        super(name);
        this.owner = owner; // Has-a Relationship (Composition)
    }

    @Override public void showDetails() {
        System.out.println("File: " + name + " (Owner: " + owner + ")");
    }
}

// 🔹 Composite: Folder (Contains Files & Other Folders)
class Folder extends FileSystemComponent {
    private final List<FileSystemComponent> children = new ArrayList<>();

    public Folder(String name) { super(name); }

    public void add(FileSystemComponent component) { children.add(component); }

    @Override public void showDetails() {
        System.out.println("Folder: " + name);
        for (FileSystemComponent child : children) {
            child.showDetails();
        }
    }
}

// 🔹 Usage
public class HasACompositeExample {
    public static void main(String[] args) {
        File file1 = new File("doc.txt", "Alice");
        File file2 = new File("report.pdf", "Bob");

        Folder folder = new Folder("Documents");
        folder.add(file1);
        folder.add(file2);

        folder.showDetails();
    }
}
```
### **✅ Key Takeaways:**
- The **File** class **HAS-A Owner** (**Composition**).
- The **Folder** class follows the **Composite Pattern** by containing **Files and other Folders**.

---

# **🚀 Final Summary**
| Feature | "Has-a" Relationship | Composite Pattern |
|---------|----------------------|------------------|
| **Definition** | Object **contains another object** | Object **contains similar objects** (hierarchical structure) |
| **Ownership** | **Strong (Composition)** or **Weak (Aggregation)** | No strict ownership (recursive containment) |
| **Use Cases** | **Encapsulation, Code Reuse** | **Hierarchical Data Structures** |
| **Real-world Examples** | Car-Engine, Employee-Department | UI Components, File Systems |


---

# **📌 1. Real-World Use Cases of "Has-a" (Composition & Aggregation)**

### **✅ Example 1: E-Commerce System (Product-Cart Relationship)**
**Scenario:**
- A **Cart** contains multiple **Products**.
- **Cart HAS-A List of Products** (Aggregation).
- If the Cart is deleted, **Products remain in inventory**.

```java
import java.util.List;
import java.util.ArrayList;

// 🔹 Product Class
class Product {
    private final String name;
    private final double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}

// 🔹 Shopping Cart (HAS-A List of Products)
class ShoppingCart {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void displayCart() {
        System.out.println("Shopping Cart Items:");
        for (Product product : products) {
            System.out.println("- " + product.getName() + ": $" + product.getPrice());
        }
    }
}

// 🔹 Usage
public class ECommerceSystem {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Product("Laptop", 1200));
        cart.addProduct(new Product("Mouse", 25));

        cart.displayCart();  // Products exist independently of Cart
    }
}
```


### **✅ Why Use Aggregation Instead of Composition?**
- **If the cart is deleted, products still exist in inventory.**
- **Products are shared across multiple carts (e.g., wishlist, saved items).**

**Follow-Up Questions:**
- _How would you make this scalable for millions of users?_ (_Answer:_ Use **Redis caching for active carts**).
- _How would you handle concurrent updates?_ (_Answer:_ Use **optimistic locking** to prevent race conditions).

---

# **📌 2. Real-World Use Cases of Composite Pattern**

### **✅ Example 2: File System (Folders & Files)**
**Scenario:**
- A **Folder contains Files and Subfolders** (Recursive structure).
- The **Composite Pattern** allows uniform handling of both.
- Deleting a **Folder** deletes all its **children (Files & Subfolders)**.

```java
import java.util.List;
import java.util.ArrayList;

// 🔹 Component Interface
interface FileSystem {
    void showDetails();
}

// 🔹 Leaf: File
class File implements FileSystem {
    private final String name;

    public File(String name) { this.name = name; }
    @Override public void showDetails() { System.out.println("File: " + name); }
}

// 🔹 Composite: Folder (Contains Files and Other Folders)
class Folder implements FileSystem {
    private final String name;
    private final List<FileSystem> children = new ArrayList<>();

    public Folder(String name) { this.name = name; }

    public void add(FileSystem component) { children.add(component); }

    @Override
    public void showDetails() {
        System.out.println("Folder: " + name);
        for (FileSystem child : children) {
            child.showDetails();
        }
    }
}

// 🔹 Usage
public class FileSystemExample {
    public static void main(String[] args) {
        File file1 = new File("doc.txt");
        File file2 = new File("photo.jpg");

        Folder folder = new Folder("My Documents");
        folder.add(file1);
        folder.add(file2);

        Folder root = new Folder("Root");
        root.add(folder);

        root.showDetails();  // Displays hierarchy
    }
}
```


# **📌 4. Hybrid Approach: Combining "Has-a" & Composite**
Many **real-world applications** use **both** patterns for **optimized performance and maintainability**.

### **✅ Example 3: Organization Hierarchy with Employee & Departments**
**Scenario:**
- An **Employee HAS-A Department** (Aggregation).
- A **Department contains Employees and Sub-Departments** (Composite).

```java
import java.util.List;
import java.util.ArrayList;

// 🔹 Employee (HAS-A Department)
class Employee {
    private final String name;
    private final Department department; // Aggregation

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public void showDetails() {
        System.out.println(name + " works in " + department.getName());
    }
}

// 🔹 Composite: Department
class Department {
    private final String name;
    private final List<Employee> employees = new ArrayList<>();
    private final List<Department> subDepartments = new ArrayList<>();

    public Department(String name) { this.name = name; }

    public void addEmployee(Employee employee) { employees.add(employee); }
    public void addSubDepartment(Department department) { subDepartments.add(department); }
    public String getName() { return name; }

    public void showStructure() {
        System.out.println("Department: " + name);
        for (Employee e : employees) {
            e.showDetails();
        }
        for (Department d : subDepartments) {
            d.showStructure();
        }
    }
}

// 🔹 Usage
public class OrganizationHierarchy {
    public static void main(String[] args) {
        Department tech = new Department("Technology");
        Department hr = new Department("Human Resources");

        Employee alice = new Employee("Alice", tech);
        Employee bob = new Employee("Bob", hr);

        tech.addEmployee(alice);
        hr.addEmployee(bob);

        Department company = new Department("Company");
        company.addSubDepartment(tech);
        company.addSubDepartment(hr);

        company.showStructure();
    }
}
```
### **✅ Why Use Both Patterns?**
- **"Has-a" (Aggregation)** ensures Employees are independent of the Department.
- **Composite** allows Departments to contain Sub-Departments.
- **Scalable for large organizations.**

**Follow-Up Questions:**
- _How would you store this hierarchy in a database?_ (_Answer:_ Use **Adjacency List or Nested Set Model**).
- _How would you handle dynamic role assignments?_ (_Answer:_ Use **Decorator Pattern** for user roles).

---

# **🚀 Comprehensive Guide to Compound Design Patterns in Java**

## **📌 What Are Compound Design Patterns?**
A **Compound Design Pattern** is a **combination of multiple design patterns** that work together to solve **complex software design problems**. These patterns often appear in **large-scale applications** where a single design pattern is insufficient.

### **🔹 Why Use Compound Patterns?**
✅ **Combines strengths of multiple patterns**  
✅ **Enhances maintainability & scalability**  
✅ **Encapsulates multiple behaviors**  
✅ **Solves real-world software architecture problems**

---

# **📌 List of Common Compound Design Patterns**
| **Compound Pattern** | **Combination of Patterns** | **Use Case** |
|----------------------|----------------------------|--------------|
| **Model-View-Controller (MVC)** | Observer + Strategy + Composite | GUI frameworks, Web applications |
| **Model-View-Presenter (MVP)** | Observer + Strategy | Decouples UI logic in applications |
| **Decorator-Composite** | Decorator + Composite | GUI Components, Text formatting |
| **Abstract Factory with Prototype** | Abstract Factory + Prototype | Object creation with cloning |
| **State-Strategy Pattern** | State + Strategy | UI navigation, Finite State Machines |
| **Bridge with Factory** | Bridge + Factory | Plug-in architectures, Hardware abstractions |
| **Observer with Mediator** | Observer + Mediator | Event-driven systems, Messaging frameworks |
| **Proxy with Decorator** | Proxy + Decorator | Security proxies, Caching layers |

---

# **🔹 1. Model-View-Controller (MVC)**
📌 **Combines:**  
✅ **Observer Pattern** → Keeps View updated when Model changes  
✅ **Strategy Pattern** → Allows different View & Controller behaviors  
✅ **Composite Pattern** → Helps organize UI elements

### **✅ Problem It Solves**
- Separates UI logic from business logic
- Allows multiple views for the same data model
- Supports user interaction via controllers

### **✅ Example: MVC in Java**
```java
// 🔹 Model (Notifies Views)
import java.util.ArrayList;
import java.util.List;

class Model {
    private int data;
    private final List<Observer> observers = new ArrayList<>();

    void addObserver(Observer observer) {
        observers.add(observer);
    }

    void setData(int data) {
        this.data = data;
        notifyObservers();
    }

    int getData() {
        return data;
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(data);
        }
    }
}

// 🔹 Observer Interface
interface Observer {
    void update(int data);
}

// 🔹 View
class View implements Observer {
    @Override
    public void update(int data) {
        System.out.println("View Updated: " + data);
    }
}

// 🔹 Controller (Strategy Pattern)
class Controller {
    private final Model model;

    Controller(Model model) {
        this.model = model;
    }

    void changeData(int newData) {
        model.setData(newData);
    }
}

// 🔹 Main
public class MVCPattern {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        model.addObserver(view);
        
        Controller controller = new Controller(model);
        controller.changeData(42); // Updates View
    }
}
```
### **✅ Benefits**
- **Decouples UI (View) from business logic (Model)**
- **Multiple Views can observe the same Model**
- **Controllers can change behavior dynamically**

---

## **📌 1. Model-View-Controller (MVC) - Observer + Strategy + Composite**
### **🔹 Question: How does MVC implement multiple design patterns, and why is it useful in large applications?**
✅ **Example Answer:**  
MVC combines **Observer, Strategy, and Composite** patterns:
- **Observer Pattern**: Views subscribe to Model updates.
- **Strategy Pattern**: Controllers handle user interactions and delegate actions.
- **Composite Pattern**: UI elements (buttons, text, etc.) are organized hierarchically.

📌 **Why MVC is useful?**
- **Decouples UI from business logic** (Model-View separation).
- **Supports multiple views (e.g., Mobile, Web)** from the same Model.
- **Makes unit testing easier** because Model logic is independent of UI.

**✅ Follow-up:** _How would you modify MVC for a real-time system?_  
_Answer:_ Use **Reactive Streams or Observer Pattern** for push-based data updates.

---


# **📌 5. Bridge with Factory Pattern**
📌 **Combines:**  
✅ **Bridge Pattern** → Decouples abstraction from implementation.  
✅ **Factory Pattern** → Centralizes object creation.

### **✅ Problem It Solves**
- Helps **plug-in architectures**, where implementation details may vary.
- **Separates object creation** and **implementation details**.

---

### **✅ Example: Device Controller with Bridge & Factory**
```java
// 🔹 Device Abstraction
abstract class Device {
    protected Driver driver;

    protected Device(Driver driver) {
        this.driver = driver;
    }

    abstract void start();
}

// 🔹 Implementor Interface (Bridge)
interface Driver {
    void initialize();
}

// 🔹 Concrete Implementations
class LinuxDriver implements Driver {
    @Override
    public void initialize() {
        System.out.println("Initializing Linux Driver...");
    }
}

class WindowsDriver implements Driver {
    @Override
    public void initialize() {
        System.out.println("Initializing Windows Driver...");
    }
}

// 🔹 Concrete Device Implementations
class Printer extends Device {
    public Printer(Driver driver) {
        super(driver);
    }

    @Override
    public void start() {
        System.out.print("Starting Printer -> ");
        driver.initialize();
    }
}

class Scanner extends Device {
    public Scanner(Driver driver) {
        super(driver);
    }

    @Override
    public void start() {
        System.out.print("Starting Scanner -> ");
        driver.initialize();
    }
}

// 🔹 Factory to Create Devices
class DeviceFactory {
    public static Device createDevice(String type, Driver driver) {
        return switch (type.toLowerCase()) {
            case "printer" -> new Printer(driver);
            case "scanner" -> new Scanner(driver);
            default -> throw new IllegalArgumentException("Invalid device type.");
        };
    }
}

// 🔹 Main
public class BridgeFactoryPattern {
    public static void main(String[] args) {
        Device printer = DeviceFactory.createDevice("printer", new LinuxDriver());
        printer.start();  // Starting Printer -> Initializing Linux Driver...
        
        Device scanner = DeviceFactory.createDevice("scanner", new WindowsDriver());
        scanner.start();  // Starting Scanner -> Initializing Windows Driver...
    }
}
```
### **✅ Benefits**
- **Bridge decouples device abstraction from driver implementation.**
- **Factory ensures device creation is centralized and extensible.**
- **Easier to introduce new devices (Printers, Scanners) or drivers (MacOS, Android).**

---

# **📌 6. Proxy with Decorator Pattern**
📌 **Combines:**  
✅ **Proxy Pattern** → Controls access to an object.  
✅ **Decorator Pattern** → Dynamically adds new functionality.

### **✅ Problem It Solves**
- **Controls object access** (e.g., security, caching).
- **Adds behavior dynamically** without modifying existing classes.

---

### **✅ Example: Secure File Access with Logging**
```java
// 🔹 Component Interface
interface FileAccess {
    void readFile();
}

// 🔹 Real File Implementation
class RealFile implements FileAccess {
    private final String filename;

    public RealFile(String filename) {
        this.filename = filename;
    }

    @Override
    public void readFile() {
        System.out.println("Reading file: " + filename);
    }
}

// 🔹 Proxy (Adds Access Control)
class SecureFileProxy implements FileAccess {
    private final RealFile file;
    private final String userRole;

    public SecureFileProxy(String filename, String userRole) {
        this.file = new RealFile(filename);
        this.userRole = userRole;
    }

    @Override
    public void readFile() {
        if (userRole.equals("admin")) {
            file.readFile();
        } else {
            System.out.println("Access Denied!");
        }
    }
}

// 🔹 Decorator (Adds Logging)
class LoggingDecorator implements FileAccess {
    private final FileAccess fileAccess;

    public LoggingDecorator(FileAccess fileAccess) {
        this.fileAccess = fileAccess;
    }

    @Override
    public void readFile() {
        System.out.println("Logging access...");
        fileAccess.readFile();
    }
}

// 🔹 Main
public class ProxyDecoratorPattern {
    public static void main(String[] args) {
        FileAccess secureFile = new SecureFileProxy("secret.txt", "admin");
        FileAccess loggedFile = new LoggingDecorator(secureFile);
        
        loggedFile.readFile();  // Logs access and reads file
    }
}
```
### **✅ Benefits**
- **Proxy ensures security by restricting access based on user roles.**
- **Decorator adds logging dynamically without modifying file logic.**
- **Highly extensible for additional features like encryption or caching.**

---

# **🔹 2. Decorator-Composite Pattern**
📌 **Combines:**  
✅ **Decorator Pattern** → Dynamically adds functionality  
✅ **Composite Pattern** → Treats individual & group objects uniformly

### **✅ Problem It Solves**
- Extends functionality **without modifying existing classes**
- Organizes UI elements into a tree structure

### **✅ Example: UI Components in Java**
```java
// 🔹 Component Interface
interface Component {
    void render();
}

// 🔹 Leaf Element
class Button implements Component {
    @Override
    public void render() {
        System.out.println("Rendering Button");
    }
}

// 🔹 Composite (Container of Components)
class Panel implements Component {
    private final List<Component> children = new ArrayList<>();

    void add(Component component) {
        children.add(component);
    }

    @Override
    public void render() {
        System.out.println("Rendering Panel");
        for (Component child : children) {
            child.render();
        }
    }
}

// 🔹 Decorator (Adding Border)
class BorderDecorator implements Component {
    private final Component component;

    BorderDecorator(Component component) {
        this.component = component;
    }

    @Override
    public void render() {
        System.out.print("Border -> ");
        component.render();
    }
}

// 🔹 Main
public class DecoratorCompositePattern {
    public static void main(String[] args) {
        Component button = new Button();
        Component decoratedButton = new BorderDecorator(button);
        
        Panel panel = new Panel();
        panel.add(decoratedButton);
        
        panel.render();
    }
}
```
### **✅ Benefits**
- **Allows dynamic extension of UI components**
- **Supports hierarchical UI structures**

---

## **📌 2. Decorator-Composite Pattern**
### **🔹 Question: When should you use a combination of Decorator and Composite?**
✅ **Example Answer:**  
📌 **When to use?**
- **Decorator** adds behavior at runtime (e.g., styling a UI component).
- **Composite** organizes objects hierarchically (e.g., UI elements like Panels & Buttons).

**Example Use Case:**  
A **text editor** where:
- Each character **(Leaf Node)** is part of a **Paragraph (Composite Node)**.
- Text decorations (bold, italic, underline) **use Decorator**.

📌 **Code Example:**
```java
interface Text {
    String render();
}

class PlainText implements Text {
    private final String content;
    public PlainText(String content) { this.content = content; }
    @Override public String render() { return content; }
}

// 🔹 Decorators
class BoldText implements Text {
    private final Text text;
    public BoldText(Text text) { this.text = text; }
    @Override public String render() { return "<b>" + text.render() + "</b>"; }
}

// 🔹 Composite (Paragraph with multiple texts)
class Paragraph implements Text {
    private final List<Text> elements = new ArrayList<>();
    void add(Text text) { elements.add(text); }
    @Override public String render() {
        return elements.stream().map(Text::render).collect(Collectors.joining(" "));
    }
}

// 🔹 Usage
Text text = new BoldText(new PlainText("Hello"));
Paragraph paragraph = new Paragraph();
paragraph.add(text);
paragraph.add(new PlainText("World!"));
System.out.println(paragraph.render());  // Output: <b>Hello</b> World!
```
✅ **Follow-up:** _How does this approach compare to subclassing?_  
_Answer:_ Subclassing is **static** (fixed at compile-time), while **Decorators** allow **dynamic extension**.

---



# **🔹 3. Observer with Mediator**
📌 **Combines:**  
✅ **Observer Pattern** → Handles event-driven updates  
✅ **Mediator Pattern** → Centralized communication between objects

### **✅ Problem It Solves**
- Prevents **tight coupling** between components
- Reduces **direct dependencies** between objects

### **✅ Example: Chat System in Java**
```java
// 🔹 Mediator Interface
interface ChatMediator {
    void sendMessage(String message, User user);
}

// 🔹 Concrete Mediator
class ChatRoom implements ChatMediator {
    private final List<User> users = new ArrayList<>();

    void addUser(User user) {
        users.add(user);
    }

    @Override
    public void sendMessage(String message, User user) {
        for (User u : users) {
            if (u != user) {
                u.receive(message);
            }
        }
    }
}

// 🔹 Abstract User
abstract class User {
    protected ChatMediator mediator;
    protected String name;

    User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    abstract void send(String message);
    abstract void receive(String message);
}

// 🔹 Concrete User
class ChatUser extends User {
    ChatUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    void send(String message) {
        System.out.println(name + " sends: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    void receive(String message) {
        System.out.println(name + " received: " + message);
    }
}

// 🔹 Main
public class ObserverMediatorPattern {
    public static void main(String[] args) {
        ChatMediator chatRoom = new ChatRoom();

        User user1 = new ChatUser(chatRoom, "Alice");
        User user2 = new ChatUser(chatRoom, "Bob");
        User user3 = new ChatUser(chatRoom, "Charlie");

        ((ChatRoom) chatRoom).addUser(user1);
        ((ChatRoom) chatRoom).addUser(user2);
        ((ChatRoom) chatRoom).addUser(user3);

        user1.send("Hello, everyone!");
    }
}
```
### **✅ Benefits**
- **Decouples communication between users**
- **Scales well for large event-driven systems**


---

## **📌 3. Observer-Mediator Pattern**
### **🔹 Question: How does Mediator improve Observer-based event-driven systems?**
✅ **Example Answer:**  
📌 **Problem with Observer Pattern alone:**
- Too many direct dependencies between Observers and Subjects.
- **Difficult to manage event flow** in large systems.

📌 **How Mediator helps?**
- Centralizes event handling.
- **Reduces dependencies** between objects.
- **Improves scalability** for event-driven applications.

**Example Use Case:** **Messaging System**
- **Observers (Users)** subscribe to notifications.
- **Mediator (Chat Room)** handles message distribution.

```java
interface ChatMediator {
    void sendMessage(String message, User sender);
}

class ChatRoom implements ChatMediator {
    private final List<User> users = new ArrayList<>();
    void addUser(User user) { users.add(user); }
    @Override
    public void sendMessage(String message, User sender) {
        users.stream().filter(u -> u != sender).forEach(u -> u.receive(message));
    }
}

abstract class User {
    protected ChatMediator mediator;
    protected String name;
    User(ChatMediator mediator, String name) { this.mediator = mediator; this.name = name; }
    abstract void send(String message);
    abstract void receive(String message);
}

class ChatUser extends User {
    ChatUser(ChatMediator mediator, String name) { super(mediator, name); }
    @Override void send(String message) { System.out.println(name + " sends: " + message); mediator.sendMessage(message, this); }
    @Override void receive(String message) { System.out.println(name + " received: " + message); }
}

// 🔹 Usage
ChatMediator chatRoom = new ChatRoom();
User alice = new ChatUser(chatRoom, "Alice");
User bob = new ChatUser(chatRoom, "Bob");
((ChatRoom) chatRoom).addUser(alice);
((ChatRoom) chatRoom).addUser(bob);
alice.send("Hello, Bob!");
```
✅ **Follow-up:** _When should you prefer an Event Bus over Mediator?_  
_Answer:_ **Event Buses** are more scalable when **events don't require a strict central controller**.

---'

# **📌 2. Building a Real-Time Chat Application (Observer + Mediator + State Pattern)**
### **🔹 Problem Statement**
*"Design a **real-time chat system** where users can send messages, join chat rooms, and receive real-time updates."*

### **✅ Design Approach**
📌 **Observer Pattern** → Allows users to subscribe to chat rooms.  
📌 **Mediator Pattern** → Centralized message handling for chat rooms.  
📌 **State Pattern** → Manages user status (Online, Offline, Busy).

---

### **✅ System Implementation**
```java
import java.util.ArrayList;
import java.util.List;

// 🔹 Observer Interface (Users Receiving Messages)
interface ChatObserver {
    void receiveMessage(String message);
}

// 🔹 Concrete User
class User implements ChatObserver {
    private final String name;
    private ChatRoomMediator chatRoom;

    public User(String name) { this.name = name; }

    void joinRoom(ChatRoomMediator chatRoom) {
        this.chatRoom = chatRoom;
        chatRoom.addUser(this);
    }

    void sendMessage(String message) {
        System.out.println(name + " sends: " + message);
        chatRoom.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(name + " received: " + message);
    }
}

// 🔹 Mediator (Centralized Chat Room)
interface ChatRoomMediator {
    void addUser(ChatObserver user);
    void sendMessage(String message, ChatObserver sender);
}

// 🔹 Concrete Mediator
class ChatRoom implements ChatRoomMediator {
    private final List<ChatObserver> users = new ArrayList<>();

    @Override public void addUser(ChatObserver user) { users.add(user); }
    @Override public void sendMessage(String message, ChatObserver sender) {
        users.forEach(user -> { if (user != sender) user.receiveMessage(message); });
    }
}

// 🔹 User State (Online, Offline, Busy)
interface UserState {
    void handleStatus(User user);
}

class OnlineState implements UserState {
    @Override public void handleStatus(User user) {
        System.out.println(user + " is Online.");
    }
}

class BusyState implements UserState {
    @Override public void handleStatus(User user) {
        System.out.println(user + " is Busy.");
    }
}

// 🔹 Usage
public class ChatApplication {
    public static void main(String[] args) {
        ChatRoomMediator chatRoom = new ChatRoom();
        User alice = new User("Alice");
        User bob = new User("Bob");

        alice.joinRoom(chatRoom);
        bob.joinRoom(chatRoom);

        alice.sendMessage("Hello, Bob!");
        bob.sendMessage("Hey, Alice!");
    }
}
```
### **✅ Follow-Up Questions**
- _How would you support millions of concurrent users?_ (_Answer:_ Use **WebSockets and scalable microservices**).
- _How would you persist chat messages?_ (_Answer:_ Store in **Cassandra or Redis** for fast retrieval).

---

## **📌 4. State-Strategy Pattern**
### **🔹 Question: How does combining State and Strategy improve scalability?**
✅ **Example Answer:**  
📌 **State Pattern** → Represents state changes dynamically.  
📌 **Strategy Pattern** → Chooses behavior at runtime.

**Example Use Case:** **Game Character AI**
- **State Pattern** manages health (Normal, Injured, Dead).
- **Strategy Pattern** determines attack strategy (Melee, Ranged, Magic).

```java
interface AttackStrategy {
    void attack();
}

class MeleeAttack implements AttackStrategy {
    @Override public void attack() { System.out.println("Attacking with sword!"); }
}

class RangedAttack implements AttackStrategy {
    @Override public void attack() { System.out.println("Shooting an arrow!"); }
}

// 🔹 Character States
interface CharacterState {
    void handle(CharacterContext context);
}

class NormalState implements CharacterState {
    @Override
    public void handle(CharacterContext context) {
        System.out.println("Character is Normal.");
        context.setAttackStrategy(new MeleeAttack());
    }
}

class InjuredState implements CharacterState {
    @Override
    public void handle(CharacterContext context) {
        System.out.println("Character is Injured.");
        context.setAttackStrategy(new RangedAttack());
    }
}

class CharacterContext {
    private CharacterState state;
    private AttackStrategy attackStrategy;
    public CharacterContext() { this.state = new NormalState(); }
    void setState(CharacterState state) { this.state = state; state.handle(this); }
    void setAttackStrategy(AttackStrategy strategy) { this.attackStrategy = strategy; }
    void attack() { attackStrategy.attack(); }
}

// 🔹 Usage
CharacterContext character = new CharacterContext();
character.attack();  // Attacks with sword
character.setState(new InjuredState());
character.attack();  // Shoots an arrow
```
✅ **Follow-up:** _When would you use Strategy without State?_  
_Answer:_ **Use Strategy alone when states don’t affect behavior transitions**.

---

## **🚀 Final Summary**
| **Compound Pattern** | **Key Question to Expect** |
|----------------------|----------------------------|
| **MVC (Observer + Strategy + Composite)** | How does MVC enable decoupling? |
| **Decorator-Composite** | When should you prefer a decorator over inheritance? |
| **Observer-Mediator** | How does Mediator improve event handling in Observer systems? |
| **State-Strategy** | How does combining State & Strategy improve scalability? |

---


## **📌 1. Designing a Plugin-Based Architecture (Factory + Strategy + Bridge)**
### **🔹 Question:**
*"How would you design a plugin system that allows dynamic addition of new features (e.g., a media player supporting multiple file formats)?"*

### **✅ Expected Design Approach:**
📌 **Factory Pattern** → Dynamically instantiates new plugins.  
📌 **Strategy Pattern** → Allows different plugins to implement different behaviors.  
📌 **Bridge Pattern** → Decouples the plugin interface from its implementation.

### **✅ Example System: Media Player Plugin System**
```java
// 🔹 Plugin Interface (Bridge Abstraction)
interface MediaPlugin {
    void play(String file);
}

// 🔹 Implementations (Bridge Implementor)
class MP3Plugin implements MediaPlugin {
    @Override public void play(String file) { System.out.println("Playing MP3: " + file); }
}

class MP4Plugin implements MediaPlugin {
    @Override public void play(String file) { System.out.println("Playing MP4: " + file); }
}

// 🔹 Factory for Creating Plugins
class PluginFactory {
    public static MediaPlugin getPlugin(String fileType) {
        return switch (fileType.toLowerCase()) {
            case "mp3" -> new MP3Plugin();
            case "mp4" -> new MP4Plugin();
            default -> throw new IllegalArgumentException("Unsupported file format.");
        };
    }
}

// 🔹 Media Player Context (Uses Strategy)
class MediaPlayer {
    private MediaPlugin plugin;
    public void setPlugin(MediaPlugin plugin) { this.plugin = plugin; }
    public void play(String file, String fileType) {
        setPlugin(PluginFactory.getPlugin(fileType));
        plugin.play(file);
    }
}

// 🔹 Usage
public class PluginArchitectureExample {
    public static void main(String[] args) {
        MediaPlayer player = new MediaPlayer();
        player.play("song.mp3", "mp3");
        player.play("video.mp4", "mp4");
    }
}
```
### **✅ Follow-Up Questions:**
- _How would you make this extensible for cloud-based streaming plugins?_
- _How would you dynamically load plugins at runtime?_ (_Answer:_ Use **Java Reflection + ServiceLoader**).

---

## **📌 2. Designing a Distributed Notification System (Observer + Mediator)**
### **🔹 Question:**
*"Design a system where users can subscribe to different notifications (Email, SMS, Push) and get real-time updates."*

### **✅ Expected Design Approach:**
📌 **Observer Pattern** → Users subscribe to notifications.  
📌 **Mediator Pattern** → Centralized notification dispatching.

### **✅ Example System: Notification Service**
```java
// 🔹 Observer Interface
interface Subscriber {
    void receive(String message);
}

// 🔹 Concrete Subscribers
class EmailSubscriber implements Subscriber {
    @Override public void receive(String message) { System.out.println("Email Notification: " + message); }
}

class SMSSubscriber implements Subscriber {
    @Override public void receive(String message) { System.out.println("SMS Notification: " + message); }
}

// 🔹 Mediator (Notification Service)
class NotificationService {
    private final List<Subscriber> subscribers = new ArrayList<>();

    void subscribe(Subscriber subscriber) { subscribers.add(subscriber); }
    void notifySubscribers(String message) {
        subscribers.forEach(subscriber -> subscriber.receive(message));
    }
}

// 🔹 Usage
public class NotificationSystem {
    public static void main(String[] args) {
        NotificationService service = new NotificationService();
        service.subscribe(new EmailSubscriber());
        service.subscribe(new SMSSubscriber());

        service.notifySubscribers("New message received!");
    }
}
```
### **✅ Follow-Up Questions:**
- _How would you make this system event-driven using Kafka or RabbitMQ?_
- _How would you scale it for millions of users?_ (_Answer:_ Use **distributed message brokers**).

---

## **📌 3. Designing a Web Caching System (Proxy + Decorator)**
### **🔹 Question:**
*"Design a caching layer for a web application where requests are first checked in cache before fetching from the database."*

### **✅ Expected Design Approach:**
📌 **Proxy Pattern** → Controls access to database requests.  
📌 **Decorator Pattern** → Adds caching behavior dynamically.

### **✅ Example System: Caching Proxy**
```java
import java.util.HashMap;
import java.util.Map;

// 🔹 Database Access Interface
interface Database {
    String fetchData(String key);
}

// 🔹 Real Database Implementation
class RealDatabase implements Database {
    @Override public String fetchData(String key) {
        System.out.println("Fetching from DB: " + key);
        return "Data for " + key;
    }
}

// 🔹 Proxy (Caching Layer)
class CachingProxy implements Database {
    private final Database database;
    private final Map<String, String> cache = new HashMap<>();

    public CachingProxy(Database database) {
        this.database = database;
    }

    @Override
    public String fetchData(String key) {
        if (cache.containsKey(key)) {
            System.out.println("Returning cached data for: " + key);
            return cache.get(key);
        }
        String data = database.fetchData(key);
        cache.put(key, data);
        return data;
    }
}

// 🔹 Usage
public class WebCachingSystem {
    public static void main(String[] args) {
        Database database = new CachingProxy(new RealDatabase());

        System.out.println(database.fetchData("User1"));
        System.out.println(database.fetchData("User1"));  // Should return cached data
    }
}
```
### **✅ Follow-Up Questions:**
- _How would you implement an eviction policy (LRU, TTL)?_
- _How would you scale this caching layer across multiple servers?_ (_Answer:_ Use **Redis or Memcached**).

---

## **📌 4. Designing an E-commerce Checkout System (State + Strategy)**
### **🔹 Question:**
*"Design a checkout system where users can pay with different methods (Credit Card, PayPal) and order states change dynamically."*

### **✅ Expected Design Approach:**
📌 **State Pattern** → Handles order states (New, Processing, Shipped).  
📌 **Strategy Pattern** → Allows different payment methods.

### **✅ Example System: E-Commerce Checkout**
```java
// 🔹 Payment Strategy
interface PaymentMethod {
    void pay(double amount);
}

class CreditCardPayment implements PaymentMethod {
    @Override public void pay(double amount) { System.out.println("Paid $" + amount + " via Credit Card."); }
}

class PayPalPayment implements PaymentMethod {
    @Override public void pay(double amount) { System.out.println("Paid $" + amount + " via PayPal."); }
}

// 🔹 Order State Interface
interface OrderState {
    void handle(OrderContext context);
}

// 🔹 Concrete States
class NewOrder implements OrderState {
    @Override public void handle(OrderContext context) { 
        System.out.println("Order placed.");
        context.setState(new ProcessingOrder());
    }
}

class ProcessingOrder implements OrderState {
    @Override public void handle(OrderContext context) {
        System.out.println("Order is being processed.");
        context.setState(new ShippedOrder());
    }
}

class ShippedOrder implements OrderState {
    @Override public void handle(OrderContext context) { System.out.println("Order has been shipped!"); }
}

// 🔹 Order Context
class OrderContext {
    private OrderState state = new NewOrder();
    private PaymentMethod paymentMethod;

    void setState(OrderState state) { this.state = state; }
    void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }
    void processOrder() { state.handle(this); }
    void makePayment(double amount) { paymentMethod.pay(amount); }
}

// 🔹 Usage
public class CheckoutSystem {
    public static void main(String[] args) {
        OrderContext order = new OrderContext();
        order.setPaymentMethod(new PayPalPayment());

        order.makePayment(100.0);
        order.processOrder();
        order.processOrder();
    }
}
```
### **✅ Follow-Up Questions:**
- _How would you handle refunds in this system?_
- _How would you implement an order tracking system?_

---


# **📌 3. E-Commerce Platform (Composite + Observer + Proxy)**
### **🔹 Problem Statement**
*"Design an **e-commerce system** that supports product categories, shopping carts, and order tracking."*

### **✅ Design Approach**
📌 **Composite Pattern** → Organizes product categories hierarchically.  
📌 **Observer Pattern** → Users receive order status updates.  
📌 **Proxy Pattern** → Caches frequently viewed products.

---

### **✅ Follow-Up Questions**
- _How would you scale this system for millions of daily users?_
- _How would you implement dynamic pricing and promotions?_

---

# **📌 4. State-Strategy Pattern**
📌 **Combines:**  
✅ **State Pattern** → Object behavior changes based on its state.  
✅ **Strategy Pattern** → Allows dynamic selection of an algorithm at runtime.

### **✅ Problem It Solves**
- Avoids large `if-else` or `switch` statements for state-based behavior.
- Provides a **scalable** way to handle **finite state machines** (FSM).

---

### **✅ Example: Order Processing System**
```java
// 🔹 State Interface
interface OrderState {
    void processOrder(OrderContext context);
}

// 🔹 Concrete States
class NewOrderState implements OrderState {
    @Override
    public void processOrder(OrderContext context) {
        System.out.println("Processing new order...");
        context.setState(new ShippedOrderState());
    }
}

class ShippedOrderState implements OrderState {
    @Override
    public void processOrder(OrderContext context) {
        System.out.println("Order has been shipped.");
        context.setState(new DeliveredOrderState());
    }
}

class DeliveredOrderState implements OrderState {
    @Override
    public void processOrder(OrderContext context) {
        System.out.println("Order is delivered.");
    }
}

// 🔹 Context Class (Uses Strategy Pattern)
class OrderContext {
    private OrderState state;

    public OrderContext() {
        this.state = new NewOrderState();
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void processOrder() {
        state.processOrder(this);
    }
}

// 🔹 Main
public class StateStrategyPattern {
    public static void main(String[] args) {
        OrderContext order = new OrderContext();
        order.processOrder();  // Processing new order...
        order.processOrder();  // Order has been shipped.
        order.processOrder();  // Order is delivered.
    }
}
```
### **✅ Benefits**
- **Encapsulates state behavior** instead of relying on `if-else` logic.
- **Strategy Pattern ensures loose coupling** between state changes.
- **Easier to extend new states** without modifying existing logic.

---

# **📌 1. Building a Scalable Payment Gateway (Factory + Strategy + Chain of Responsibility)**
### **🔹 Problem Statement**
*"Design a **payment gateway** that supports multiple payment methods (Credit Card, PayPal, UPI) and applies security checks, fraud detection, and transaction logging before processing payments."*

### **✅ Design Approach**
📌 **Factory Pattern** → Creates the appropriate payment processor.  
📌 **Strategy Pattern** → Selects a payment method dynamically.  
📌 **Chain of Responsibility** → Applies security, fraud checks, and logging in a pipeline.

---

### **✅ System Implementation**
```java
// 🔹 Payment Strategy Interface
interface PaymentStrategy {
    boolean processPayment(double amount);
}

// 🔹 Concrete Payment Strategies
class CreditCardPayment implements PaymentStrategy {
    @Override public boolean processPayment(double amount) {
        System.out.println("Processing Credit Card Payment: $" + amount);
        return true;
    }
}

class PayPalPayment implements PaymentStrategy {
    @Override public boolean processPayment(double amount) {
        System.out.println("Processing PayPal Payment: $" + amount);
        return true;
    }
}

// 🔹 Factory to Create Payment Processors
class PaymentFactory {
    public static PaymentStrategy getPaymentMethod(String type) {
        return switch (type.toLowerCase()) {
            case "creditcard" -> new CreditCardPayment();
            case "paypal" -> new PayPalPayment();
            default -> throw new IllegalArgumentException("Unsupported payment type");
        };
    }
}

// 🔹 Chain of Responsibility: Security Checks
abstract class PaymentHandler {
    private PaymentHandler next;

    void setNextHandler(PaymentHandler next) { this.next = next; }
    void handle(double amount, PaymentStrategy strategy) {
        if (process(amount, strategy) && next != null) {
            next.handle(amount, strategy);
        }
    }
    protected abstract boolean process(double amount, PaymentStrategy strategy);
}

// 🔹 Fraud Detection Handler
class FraudDetectionHandler extends PaymentHandler {
    @Override protected boolean process(double amount, PaymentStrategy strategy) {
        System.out.println("Performing fraud detection...");
        return amount < 5000; // Allow payments below $5000
    }
}

// 🔹 Transaction Logging Handler
class LoggingHandler extends PaymentHandler {
    @Override protected boolean process(double amount, PaymentStrategy strategy) {
        System.out.println("Logging payment transaction...");
        return true;
    }
}

// 🔹 Payment Gateway
class PaymentGateway {
    public static void processTransaction(double amount, String method) {
        PaymentStrategy payment = PaymentFactory.getPaymentMethod(method);
        PaymentHandler fraudCheck = new FraudDetectionHandler();
        PaymentHandler logger = new LoggingHandler();

        fraudCheck.setNextHandler(logger);
        fraudCheck.handle(amount, payment);
        payment.processPayment(amount);
    }
}

// 🔹 Usage
public class PaymentSystem {
    public static void main(String[] args) {
        PaymentGateway.processTransaction(3000, "creditcard");
    }
}
```
### **✅ Follow-Up Questions**
- _How would you scale this system for millions of transactions per second?_
- _How would you implement retries and failure handling?_ (_Answer:_ Use **event-driven messaging queues** like Kafka or RabbitMQ).

---



# **📌 3. Designing a Recommendation System (Factory + Strategy + Composite)**
### **🔹 Problem Statement**
*"Design a **recommendation engine** that provides personalized content based on user preferences (e.g., Netflix or Amazon recommendations)."*

### **✅ Design Approach**
📌 **Factory Pattern** → Creates different recommendation algorithms.  
📌 **Strategy Pattern** → Selects the best recommendation method for a user.  
📌 **Composite Pattern** → Allows combining multiple recommendation strategies.

---

### **✅ Follow-Up Questions**
- _How would you make this work for millions of users in real-time?_
- _How would you optimize recommendations using machine learning?_ (_Answer:_ Use **collaborative filtering & deep learning models**).

---

# **🚀 Final Takeaways**
| **System** | **Patterns Used** | **Real-World Use Case** |
|------------|------------------|------------------------|
| **Payment Gateway** | Factory + Strategy + Chain of Responsibility | Stripe, PayPal |
| **Chat System** | Observer + Mediator + State | WhatsApp, Slack |
| **Recommendation System** | Factory + Strategy + Composite | Netflix, Amazon |


---


# **📌 1. Social Media Platform (Observer + Mediator + Proxy + CQRS)**
### **🔹 Problem Statement**
*"Design a **social media platform** where users can post updates, follow others, and receive real-time notifications."*

### **✅ Design Approach**
📌 **Observer Pattern** → Users subscribe to other users' updates.  
📌 **Mediator Pattern** → Manages notifications and message broadcasting.  
📌 **Proxy Pattern** → Caches frequently accessed user profiles.  
📌 **CQRS (Command Query Responsibility Segregation)** → Separates **write operations (posting updates)** from **read operations (newsfeed retrieval)** for scalability.

---

### **✅ System Implementation**
```java
import java.util.*;

// 🔹 Observer Pattern: Users following other users
interface Observer {
    void notify(String message);
}

// 🔹 Concrete User (Observer)
class User implements Observer {
    private final String name;
    private final List<User> followers = new ArrayList<>();

    public User(String name) { this.name = name; }

    void follow(User user) { user.addFollower(this); }
    void addFollower(User user) { followers.add(user); }
    void postUpdate(String update) {
        System.out.println(name + " posted: " + update);
        followers.forEach(f -> f.notify(name + " update: " + update));
    }

    @Override public void notify(String message) {
        System.out.println(name + " received notification: " + message);
    }
}

// 🔹 Usage
public class SocialMediaPlatform {
    public static void main(String[] args) {
        User alice = new User("Alice");
        User bob = new User("Bob");

        bob.follow(alice);
        alice.postUpdate("Hello, world!");
    }
}
```
### **✅ Follow-Up Questions**
- _How would you handle a billion users subscribing to updates?_ (_Answer:_ Use **Kafka for event-driven notifications**).
- _How would you optimize the News Feed?_ (_Answer:_ **Precompute feeds using Redis**).

---

# **📌 2. Online Ticket Booking System (State + Strategy + Factory + Singleton)**
### **🔹 Problem Statement**
*"Design a **movie ticket booking system** where users can search for movies, book tickets, and make payments."*

### **✅ Design Approach**
📌 **State Pattern** → Manages seat status (Available, Reserved, Booked).  
📌 **Strategy Pattern** → Supports multiple payment methods.  
📌 **Factory Pattern** → Creates payment processors dynamically.  
📌 **Singleton Pattern** → Ensures a single **inventory manager** for ticket availability.

---

### **✅ System Implementation**
```java
import java.util.*;

// 🔹 Seat States
interface SeatState {
    void handle(Seat seat);
}

class AvailableState implements SeatState {
    @Override public void handle(Seat seat) { System.out.println("Seat is available."); seat.setState(new ReservedState()); }
}

class ReservedState implements SeatState {
    @Override public void handle(Seat seat) { System.out.println("Seat is reserved."); seat.setState(new BookedState()); }
}

class BookedState implements SeatState {
    @Override public void handle(Seat seat) { System.out.println("Seat is booked."); }
}

// 🔹 Seat Context
class Seat {
    private SeatState state = new AvailableState();

    void setState(SeatState state) { this.state = state; }
    void book() { state.handle(this); }
}

// 🔹 Payment Strategy
interface PaymentMethod {
    void pay(double amount);
}

class CreditCardPayment implements PaymentMethod {
    @Override public void pay(double amount) { System.out.println("Paid $" + amount + " via Credit Card."); }
}

class PayPalPayment implements PaymentMethod {
    @Override public void pay(double amount) { System.out.println("Paid $" + amount + " via PayPal."); }
}

// 🔹 Payment Factory
class PaymentFactory {
    public static PaymentMethod getPaymentMethod(String type) {
        return switch (type.toLowerCase()) {
            case "creditcard" -> new CreditCardPayment();
            case "paypal" -> new PayPalPayment();
            default -> throw new IllegalArgumentException("Unsupported payment type");
        };
    }
}

// 🔹 Singleton Ticket Inventory
class TicketInventory {
    private static TicketInventory instance;
    private final Map<String, Integer> movies = new HashMap<>();

    private TicketInventory() { movies.put("Avengers", 50); }

    public static synchronized TicketInventory getInstance() {
        if (instance == null) instance = new TicketInventory();
        return instance;
    }

    public boolean bookTicket(String movie) {
        int available = movies.getOrDefault(movie, 0);
        if (available > 0) {
            movies.put(movie, available - 1);
            return true;
        }
        return false;
    }
}

// 🔹 Usage
public class TicketBookingSystem {
    public static void main(String[] args) {
        TicketInventory inventory = TicketInventory.getInstance();
        System.out.println("Booking Avengers ticket: " + inventory.bookTicket("Avengers"));

        Seat seat = new Seat();
        seat.book();  // Reserves seat
        seat.book();  // Books seat

        PaymentMethod payment = PaymentFactory.getPaymentMethod("creditcard");
        payment.pay(15.0);
    }
}
```
### **✅ Follow-Up Questions**
- _How would you handle concurrent bookings?_ (_Answer:_ Use **distributed locks in Redis**).
- _How would you handle refund policies?_ (_Answer:_ Use **event-driven transaction rollback**).

---

# **📌 4. Ride-Sharing System (Strategy + Observer + Factory)**
### **🔹 Problem Statement**
*"Design a **ride-sharing service** like Uber where passengers request rides, and drivers accept requests."*

### **✅ Design Approach**
📌 **Strategy Pattern** → Supports different pricing models (Surge, Standard).  
📌 **Observer Pattern** → Users receive real-time updates.  
📌 **Factory Pattern** → Dynamically assigns ride types (Economy, Premium).

---

### **✅ Follow-Up Questions**
- _How would you handle real-time location tracking?_
- _How would you prevent fraud (fake rides, fake payments)?_

---




# **🚀 Summary of Compound Patterns**
| **Compound Pattern** | **Use Case** |
|----------------------|-------------|
| **State-Strategy** | UI Navigation, Finite State Machines |
| **Bridge-Factory** | Plug-in systems, Hardware abstraction |
| **Proxy-Decorator** | Security layers, Logging, Caching |
| **MVC (Observer + Strategy + Composite)** | GUI frameworks, Web apps |
| **Decorator-Composite** | UI Components, Text formatting |
| **Observer-Mediator** | Chat systems, Messaging frameworks |


---
## **🎯 Which Pattern Should You Use?**
| **Requirement** | **Best Pattern** |
|---------------|------------------|
| **Decouple UI from Logic** | **MVC, MVP** |
| **Extend UI components dynamically** | **Decorator-Composite** |
| **Manage event-driven applications** | **Observer-Mediator** |
| **State-driven behavior** | **State-Strategy** |
| **Control access & add behavior** | **Proxy-Decorator** |

---


| **System** | **Patterns Used** | **Real-World Use Case** |
|------------|------------------|------------------------|
| **Social Media** | Observer + Mediator + Proxy | Facebook, Twitter |
| **Ticket Booking** | State + Strategy + Singleton | BookMyShow, Fandango |
| **E-Commerce** | Composite + Observer + Proxy | Amazon, Flipkart |
| **Ride-Sharing** | Strategy + Observer + Factory | Uber, Lyft |

