### **`Solution-1: sealed` vs `final` in Java**

Both `sealed` and `final` restrict inheritance, but they serve different purposes and provide different levels of flexibility.

---

## **1. `final` Keyword (Restricts Inheritance Completely)**

* When a **class** is declared `final`, it **cannot be extended** (no subclasses).
* When a **method** is `final`, it **cannot be overridden** in subclasses.
* When a **variable** is `final`, it **cannot be reassigned** after initialization.

### **Example: `final` Class**

```java
final class A {
    void display() {
        System.out.println("A's method");
    }
}

// This will cause a compilation error:
// class B extends A {}  // ❌ ERROR: Cannot extend final class 'A'

public class Test {
    public static void main(String[] args) {
        A obj = new A();
        obj.display();
    }
}
```

✅ **Key Takeaways:**

* `final class` cannot have subclasses.
* Used to  **ensure immutability and prevent modification** .

---

### **2. `sealed` Keyword (Restricts Inheritance Selectively)**

* Introduced in  **Java 17** .
* Allows a class to  **restrict which classes can extend it** .
* The extending classes must be **explicitly listed** using `permits`.
* The permitted subclasses  **must be `final`, `sealed`, or `non-sealed`** .

### **Example: `sealed` Class**

```java
sealed class A permits B, C {  // Only B and C can extend A
    void display() {
        System.out.println("A's method");
    }
}

final class B extends A {  // No further subclassing allowed
}

sealed class C extends A permits D {  // C can only be extended by D
}

final class D extends C {  // No further subclassing
}

public class Test {
    public static void main(String[] args) {
        A obj1 = new B();
        obj1.display();

        A obj2 = new D();
        obj2.display();
    }
}
```

✅ **Key Takeaways:**

* **Controls inheritance scope** (only permitted classes can extend it).
* Helps in **designing sealed hierarchies** for better maintainability.
* `sealed` classes **must be extended by `final`, `sealed`, or `non-sealed`** classes.

---

## **3. Comparison Table**

| Feature                    | `final`                                                          | `sealed`                                  |
| -------------------------- | ------------------------------------------------------------------ | ------------------------------------------- |
| **Introduced in**    | Java 1.0                                                           | Java 17                                     |
| **Purpose**          | Prevent further inheritance                                        | Restrict inheritance to specific classes    |
| **Can be extended?** | ❌ No                                                              | ✅ Yes, but only by permitted subclasses    |
| **Flexibility**      | Completely locked                                                  | Controlled inheritance                      |
| **Use Case**         | Security, immutability, utility classes (e.g.,`String`,`Math`) | Defining structured inheritance hierarchies |
| **Example**          | `final class A {}`                                               | `sealed class A permits B, C {}`          |

---

## **4. When to Use What?**

| **Scenario**                              | **Use `final`or `sealed`?** |
| ----------------------------------------------- | ------------------------------------- |
| Prevent any subclassing                         | ✅`final`                           |
| Allow only specific subclasses                  | ✅`sealed`                          |
| Allow controlled future modifications           | ✅`sealed`                          |
| Enforce immutability (e.g.,`String`,`Math`) | ✅`final`                           |

---

## **5. `sealed` with `non-sealed` (Allowing Further Extension)**

If you want to **partially restrict** inheritance but still allow unrestricted subclassing, use `non-sealed`.

```java
sealed class A permits B, C {}

final class B extends A {}  // Cannot be extended further

non-sealed class C extends A {}  // Can be extended by any class

class D extends C {}  // Allowed because C is non-sealed
```

✅ `non-sealed` allows **regular inheritance** while keeping `sealed` restrictions.

---

## **Conclusion**

* Use `**final**` when **you never want** a class to be extended.
* Use `**sealed**` when  **you want controlled inheritance** .
* Use `**non-sealed**` to  **reopen inheritance selectively** .



### **Solution 2: Implications of Using Default Methods with Lambda Expressions and Functional Interfaces in Java**

Java introduced **default methods** in interfaces (Java 8) to provide backward compatibility and enable more flexible API evolution. When used with **lambda expressions** and  **functional interfaces** , default methods have important implications:

---

## **1. Default Methods Can Provide Utility Functions**

🔹 **Implication:** Default methods allow functional interfaces to **include reusable utility methods** without breaking existing implementations.

### **Example: Utility Methods in Functional Interfaces**

```java
@FunctionalInterface
interface Calculator {
    int compute(int a, int b);

    // Default method as a utility function
    default int add(int a, int b) {
        return a + b;
    }
}

public class Test {
    public static void main(String[] args) {
        Calculator multiply = (a, b) -> a * b;  // Lambda expression
        System.out.println(multiply.compute(3, 4));  // Output: 12
        System.out.println(multiply.add(3, 4));  // Output: 7 (default method used)
    }
}
```

✅ **Key Takeaway:**

* Default methods **extend functionality** without modifying existing lambda-based implementations.
* This is  **useful in API design** , as interfaces can evolve without breaking implementations.

---

## **2. Default Methods Can Affect Method Reference Resolution**

🔹 **Implication:** When using  **method references** , default methods in interfaces can **influence which method is resolved** at runtime.

### **Example: Ambiguity in Method References**

```java
interface A {
    default void print() {
        System.out.println("A's default method");
    }
}

interface B {
    default void print() {
        System.out.println("B's default method");
    }
}

class MyClass implements A, B {
    @Override
    public void print() {  // Must override to resolve conflict
        System.out.println("MyClass's overridden method");
    }
}

public class Test {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        obj.print();  // Calls MyClass's overridden method
    }
}
```

✅ **Key Takeaway:**

* If multiple interfaces have the same default method, the implementing class **must override it explicitly** to resolve ambiguity.
* Method references to default methods may require explicit resolution.

---

## **3. Default Methods May Influence Type Inference**

🔹 **Implication:** Default methods can sometimes  **change how Java infers types** , especially when working with lambda expressions.

### **Example: Type Inference with Default Methods**

```java
@FunctionalInterface
interface Printer {
    void print(String message);

    default void log(String message) {
        System.out.println("Logging: " + message);
    }
}

public class Test {
    public static void main(String[] args) {
        Printer printer = System.out::println;  // Method reference
        printer.print("Hello, World!");

        printer.log("This is a log message."); // Default method usage
    }
}
```

✅ **Key Takeaway:**

* Type inference works **as expected** when using lambda expressions.
* Default methods do **not** interfere with lambda expressions, but they **enhance** functional interfaces.

---

## **4. Default Methods Can Be Used to Enhance Functional Interfaces**

🔹 **Implication:** Functional interfaces typically contain  **one abstract method** , but default methods  **allow additional functionality** .

### **Example: Enhancing Functional Interfaces**

```java
@FunctionalInterface
interface Converter {
    int convert(String input);

    // Default method to validate input before conversion
    default boolean isValid(String input) {
        return input != null && !input.isEmpty();
    }
}

public class Test {
    public static void main(String[] args) {
        Converter converter = Integer::parseInt;

        String input = "123";
        if (converter.isValid(input)) {  // Using default method
            System.out.println(converter.convert(input));  // Output: 123
        }
    }
}
```

✅ **Key Takeaway:**

* Default methods allow functional interfaces to  **provide additional helper methods** .
* Helps maintain **cleaner and more reusable** lambda-based code.

---

## **Summary Table: Implications of Default Methods in Functional Interfaces**

| **Implication**                        | **Description**                                                                                       | **Example**                    |
| -------------------------------------------- | ----------------------------------------------------------------------------------------------------------- | ------------------------------------ |
| **Utility Functions**                  | Provide additional helper methods in interfaces                                                             | `add()`method in `Calculator`    |
| **Affect Method Reference Resolution** | Must override if multiple interfaces define the same default method                                         | `print()`method in `A`and `B`  |
| **Influence Type Inference**           | Default methods don’t interfere with lambda expressions but can add extra behavior                         | `log()`method in `Printer`       |
| **Enhance Functional Interfaces**      | Allows functional interfaces to have more reusable methods without breaking the single abstract method rule | `isValid()`method in `Converter` |

---

## **Final Thoughts**

* ✅ **Default methods extend functional interfaces** without breaking backward compatibility.
* ✅ They  **enable more expressive and reusable APIs** .
* ✅  **However** , multiple interfaces with the same default method  **require explicit resolution** .




### **Solution-3 How Virtual Extension Methods (Default Methods) Affect the Liskov Substitution Principle (LSP)**

#### **📌 What is the Liskov Substitution Principle (LSP)?**

The **Liskov Substitution Principle (LSP)** states that  **subtypes must be substitutable for their base types without altering the correctness of the program** . In other words,  **if class `B` extends class `A`, then anywhere an object of `A` is expected, an object of `B` should work without issues** .

With **default methods (virtual extension methods)** in Java (introduced in  **Java 8** ), interfaces can  **evolve without breaking existing implementations** , but they must be carefully designed to follow LSP.

---

## **1️⃣ Must Maintain Behavioral Subtyping**

🔹 **Implication:** A **default method should not alter the expected behavior** of existing implementations.

🔹 **Why?** If a subtype (a class implementing the interface) behaves differently than expected, it  **violates LSP** .

### **🚀 Example: Violating LSP with Default Methods**

```java
interface Vehicle {
    void start();

    default void stop() {  // New default method
        System.out.println("Vehicle stopped");
    }
}

class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car starting...");
    }
}

class Bicycle implements Vehicle {
    @Override
    public void start() {
        System.out.println("Bicycle starting...");
    }

    @Override
    public void stop() {  // Overrides stop behavior
        throw new UnsupportedOperationException("Bicycles don't have engines to stop!");
    }
}

public class Test {
    public static void main(String[] args) {
        Vehicle v = new Bicycle();
        v.start();
        v.stop();  // ❌ Unexpected exception breaks LSP
    }
}
```

**💥 Problem:**

* The `stop()` method was added as a  **default method** .
* The `Bicycle` class  **overrides it with an exception** , which  **breaks behavioral expectations** .

**✅ Solution:**

* Ensure that default methods **apply correctly** to all subclasses.
* If a default method  **doesn’t make sense for all subtypes** , it  **shouldn’t be added** .

---

## **2️⃣ Default Implementations Should Be Truly Optional**

🔹 **Implication:** Default methods should **provide useful behavior** but  **not force all implementations to use them** .

### **🚀 Example: Truly Optional Default Methods**

```java
interface Logger {
    void log(String message);

    default void logInfo(String message) {  // Optional method
        log("[INFO]: " + message);
    }
}

class ConsoleLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}

public class Test {
    public static void main(String[] args) {
        Logger logger = new ConsoleLogger();
        logger.log("Error occurred");  // Works
        logger.logInfo("Application started");  // Works (optional)
    }
}
```

**✅ Why does this work?**

* The `logInfo()` method is **optional** and doesn’t interfere with `log()`.
* Implementers can **ignore it** or **override it** without breaking anything.

---

## **3️⃣ Should Not Break Existing Contract**

🔹 **Implication:** Default methods should  **not introduce new behavior that contradicts existing implementations** .

### **🚀 Example: Breaking LSP by Modifying an Existing Contract**

```java
interface Payment {
    void pay(double amount);

    default void refund(double amount) {  // New default method
        throw new UnsupportedOperationException("Refunds not supported!");
    }
}

class CreditCardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refunded " + amount + " to Credit Card.");
    }
}

public class Test {
    public static void main(String[] args) {
        Payment p = new CreditCardPayment();
        p.pay(100);
        p.refund(50);  // Works as expected

        Payment p2 = new Payment() {  // Anonymous class using default behavior
            @Override
            public void pay(double amount) {
                System.out.println("Generic payment of " + amount);
            }
        };
        p2.refund(30);  // ❌ Throws UnsupportedOperationException unexpectedly!
    }
}
```

**💥 Problem:**

* The `refund()` method  **assumes payments don't support refunds** , but some implementations (like `CreditCardPayment`) do.
* This **violates LSP** because  **some instances of `Payment` allow refunds while others throw an exception** .

**✅ Solution:**

* **Never introduce breaking changes** in an interface contract.
* Provide **graceful default behavior** or  **document limitations clearly** .

---

## **4️⃣ May Require Documentation Updates**

🔹 **Implication:** When adding default methods, **existing implementations might not be aware** of the new behavior, leading to unexpected issues.

### **🚀 Example: Need for Documentation Updates**

```java
interface DataProcessor {
    void process(String data);

    default void log(String data) {
        System.out.println("Processing: " + data);
    }
}
```

If a developer had already implemented:

```java
class SecureDataProcessor implements DataProcessor {
    @Override
    public void process(String data) {
        System.out.println("Securely processing: " + data);
    }
}
```

Calling:

```java
DataProcessor dp = new SecureDataProcessor();
dp.log("User data");  // Uses new default method (unexpected behavior)
```

**💥 Problem:**

* `SecureDataProcessor` might **not be aware** of the new `log()` method.
* The default behavior **might not align** with security practices.
* The original developer **must be informed** of the change.

**✅ Solution:**

* Always **update documentation** when adding default methods.
* **Clearly state** the expected behavior and override recommendations.

---

## **📌 Summary Table: LSP Implications of Default Methods**

| **Implication**                              | **Explanation**                               | **Example Issue**                             | **Best Practice**                              |
| -------------------------------------------------- | --------------------------------------------------- | --------------------------------------------------- | ---------------------------------------------------- |
| **Maintain Behavioral Subtyping**            | Default methods should not change expected behavior | `stop()`method breaking `Bicycle`logic          | Ensure all subtypes can follow the behavior          |
| **Default Methods Should Be Truly Optional** | Should not force implementations to use them        | Making `logInfo()`mandatory in `Logger`         | Keep default methods as helpers                      |
| **Should Not Break Existing Contract**       | Should not introduce conflicting rules              | Adding `refund()`as unsupported in `Payment`    | Default methods should not contradict existing logic |
| **May Require Documentation Updates**        | Existing classes might not be aware of new methods  | `log()`added to `DataProcessor`affects security | Update docs and provide clear behavior               |

---

## **🛠 Final Thoughts**

* ✅ **Default methods should follow LSP** by ensuring subtypes can be used without unexpected behavior changes.
* ✅ Always **consider backward compatibility** before adding a default method.
* ✅ Default methods should be **truly optional** and  **well-documented** .
* ❌ Avoid **breaking existing contracts** or  **introducing surprising behaviors** .



### **Solution 4: Common Anti-Patterns in Inheritance and How to Avoid Them**

Inheritance is a fundamental concept in Object-Oriented Programming (OOP), but when misused, it can lead to maintainability and scalability issues. Below are **four common anti-patterns in inheritance** and best practices to avoid them.

---

## **1️⃣ Deep Inheritance Hierarchies (Excessive Subclassing)**

### **🛑 Problem:**

* Having too many levels of inheritance  **makes the code hard to understand, modify, and maintain** .
* Changes in the base class can unintentionally  **break multiple subclasses** .
* It **increases coupling** between classes.

### **🚀 Example of Deep Inheritance (Anti-Pattern)**

```java
class Animal {
    void eat() { System.out.println("Eating..."); }
}

class Mammal extends Animal {
    void breathe() { System.out.println("Breathing..."); }
}

class Canine extends Mammal {
    void bark() { System.out.println("Barking..."); }
}

class Dog extends Canine {
    void fetch() { System.out.println("Fetching..."); }
}

class GoldenRetriever extends Dog {
    void swim() { System.out.println("Swimming..."); }
}

public class Test {
    public static void main(String[] args) {
        GoldenRetriever gr = new GoldenRetriever();
        gr.eat(); // Works, but the hierarchy is unnecessarily deep.
    }
}
```

### **🛠 How to Avoid It?**

✅ **Prefer Composition over Deep Inheritance**

* Instead of deep inheritance, **use composition** (`Has-A` relationship) to reuse behavior.

### **✅ Refactored Approach Using Composition**

```java
class Animal {
    void eat() { System.out.println("Eating..."); }
}

class BarkingBehavior {
    void bark() { System.out.println("Barking..."); }
}

class Dog extends Animal {
    private BarkingBehavior barkingBehavior = new BarkingBehavior();

    void bark() { barkingBehavior.bark(); }  // Delegation
}

public class Test {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat();
        dog.bark();  // Barking is now separate behavior
    }
}
```

**💡 Benefits of Composition Over Deep Inheritance:**

* **Flexible and modular** (can change behavior without affecting unrelated classes).
* **Avoids tight coupling** and complex dependency chains.

---

## **2️⃣ God Interfaces (Overloaded Base Classes)**

### **🛑 Problem:**

* A **God Interface** (also called  **Fat Interface** ) happens when a base class or interface has **too many methods** that are not needed by all subclasses.
* Leads to  **violations of the Interface Segregation Principle (ISP)** .

### **🚀 Example of a God Interface (Anti-Pattern)**

```java
interface Worker {
    void work();
    void eat();  // Not all workers need this (e.g., robots)
    void sleep();  // Not all workers need this
}

class HumanWorker implements Worker {
    @Override
    public void work() { System.out.println("Working..."); }
    @Override
    public void eat() { System.out.println("Eating..."); }
    @Override
    public void sleep() { System.out.println("Sleeping..."); }
}

class RobotWorker implements Worker {
    @Override
    public void work() { System.out.println("Working..."); }
    @Override
    public void eat() { throw new UnsupportedOperationException("Robots don't eat!"); }
    @Override
    public void sleep() { throw new UnsupportedOperationException("Robots don't sleep!"); }
}
```

### **🛠 How to Avoid It?**

✅ **Follow the Interface Segregation Principle (ISP)**

* Split large interfaces into  **smaller, more specific interfaces** .

### **✅ Refactored Approach: Split Interfaces**

```java
interface Worker {
    void work();
}

interface Eater {
    void eat();
}

interface Sleeper {
    void sleep();
}

class HumanWorker implements Worker, Eater, Sleeper {
    @Override
    public void work() { System.out.println("Working..."); }
    @Override
    public void eat() { System.out.println("Eating..."); }
    @Override
    public void sleep() { System.out.println("Sleeping..."); }
}

class RobotWorker implements Worker {
    @Override
    public void work() { System.out.println("Working..."); }
}
```

**💡 Benefits:**

* **Prevents unnecessary method overrides** .
* **Improves flexibility** —objects implement only the behavior they need.

---

## **3️⃣ Inheritance for Code Reuse Only (Misusing Inheritance Instead of Composition)**

### **🛑 Problem:**

* Using inheritance **only** for code reuse without a proper **"Is-A" relationship** leads to  **violations of LSP** .
* Unnecessary inheritance  **forces subclasses to inherit unwanted behavior** .

### **🚀 Example of Misusing Inheritance for Code Reuse**

```java
class Printer {
    void print() { System.out.println("Printing..."); }
}

class Scanner extends Printer {  // ❌ A Scanner is NOT a Printer!
    void scan() { System.out.println("Scanning..."); }
}
```

### **🛠 How to Avoid It?**

✅ **Use Composition Instead of Inheritance for Code Reuse**

* If one class **is not truly a subtype** of another, use  **composition instead** .

### **✅ Refactored Approach: Use Composition**

```java
class Printer {
    void print() { System.out.println("Printing..."); }
}

class Scanner {
    void scan() { System.out.println("Scanning..."); }
}

class MultiFunctionDevice {
    private Printer printer = new Printer();
    private Scanner scanner = new Scanner();

    void print() { printer.print(); }
    void scan() { scanner.scan(); }
}
```

**💡 Benefits:**

* **More flexible** —can change behavior independently.
* **No unwanted dependencies** .

---

## **4️⃣ Breaking the Liskov Substitution Principle (LSP)**

### **🛑 Problem:**

* **A subclass should be usable in place of its superclass without breaking behavior** .
* Violating LSP makes  **polymorphism unreliable** .

### **🚀 Example: Violating LSP**

```java
class Bird {
    void fly() { System.out.println("Flying..."); }
}

class Penguin extends Bird {  // ❌ Problem: Penguins can't fly!
    @Override
    void fly() {
        throw new UnsupportedOperationException("Penguins can't fly!");
    }
}
```

If we use:

```java
Bird myBird = new Penguin();
myBird.fly();  // ❌ Throws an exception! LSP broken.
```

### **🛠 How to Avoid It?**

✅ **Redesign the class hierarchy to avoid invalid substitutions.**

* Use  **composition instead of inheritance** .

### **✅ Refactored Approach: Separate Behaviors**

```java
interface Flyer {
    void fly();
}

class Bird {
    void makeSound() { System.out.println("Bird sounds"); }
}

class Sparrow extends Bird implements Flyer {
    @Override
    public void fly() { System.out.println("Flying high!"); }
}

class Penguin extends Bird {
    void swim() { System.out.println("Swimming underwater!"); }
}
```

Now:

```java
Bird penguin = new Penguin();  // ✅ Works fine
penguin.makeSound();  // ✅ No unexpected exceptions
```

**💡 Benefits:**

* **LSP is preserved** —Penguins are still birds, but they don't pretend to fly.
* **More natural and realistic hierarchy** .

---

## **📌 Summary Table: Common Inheritance Anti-Patterns & Fixes**

| **Anti-Pattern**                    | **Problem**                              | **Fix**                                                   |
| ----------------------------------------- | ---------------------------------------------- | --------------------------------------------------------------- |
| **Deep Inheritance Hierarchies**    | Hard to maintain, tightly coupled              | Use**composition**instead of deep class trees             |
| **God Interfaces**                  | Interfaces have too many unrelated methods     | Follow**Interface Segregation Principle (ISP)**           |
| **Inheritance for Code Reuse Only** | Breaks "Is-A" relationship                     | Use**Composition over Inheritance**                       |
| **Breaking LSP**                    | Subtypes don’t behave like their superclasses | Ensure**valid "Is-A" relationships** , separate behaviors |

---

## **🎯 Conclusion**

* 🚫  **Avoid deep inheritance hierarchies** —prefer composition.
* 🔄 **Use interface segregation** to avoid "God Interfaces."
* ✅  **Favor composition over inheritance for code reuse** .
* ⚠️ **Ensure subclasses don’t break LSP** for reliable polymorphism.



### **Solution 5: Abstract Classes vs Interfaces in Java**

Understanding **when to use abstract classes vs interfaces** is crucial for designing flexible and maintainable Java applications. Both **abstract classes** and **interfaces** allow defining **blueprints** for other classes, but they serve different purposes.

---

## **🟢 Abstract Classes**

Abstract classes provide a **base class** that other classes can extend. They can  **define both state and behavior** , making them useful for **shared logic** among related classes.

### **Key Features of Abstract Classes**

✅ **Can have constructors and state (instance variables)**

✅ **Can have `final`, `protected`, and private members**

✅ **Can define both abstract and concrete methods**

✅ **Can have static and non-static fields**

✅ **Better for defining a base class that encapsulates common state and behavior**

### **🚀 Example of an Abstract Class**

```java
abstract class Animal {
    protected String name;  // Instance variable (state)

    Animal(String name) {  // Constructor
        this.name = name;
    }

    abstract void makeSound();  // Abstract method (must be implemented)

    void eat() {  // Concrete method
        System.out.println(name + " is eating.");
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name);
    }

    @Override
    void makeSound() {
        System.out.println("Woof! Woof!");
    }
}

public class Test {
    public static void main(String[] args) {
        Dog dog = new Dog("Buddy");
        dog.makeSound();  // Output: Woof! Woof!
        dog.eat();  // Output: Buddy is eating.
    }
}
```

### **🔹 When to Use Abstract Classes?**

* **You want to share code among several related classes.**
* **You need common fields and methods with access modifiers (private, protected).**
* **You need non-static or non-final fields.**
* **The class hierarchy is fixed, and you expect controlled inheritance.**

---

## **🔵 Interfaces**

Interfaces define **pure contracts** or **capabilities** that classes must implement. They support **multiple inheritance** and ensure  **loose coupling** .

### **Key Features of Interfaces**

✅ **All fields are `public static final` by default (constants only)**

✅ **All methods are `public` by default**

✅ **Can have `static` and `default` methods (Java 8+)**

✅ **Supports multiple inheritance (a class can implement multiple interfaces)**

✅ **Better for defining contracts or capabilities rather than shared state**

### **🚀 Example of an Interface**

```java
interface Vehicle {
    void drive();  // Abstract method (must be implemented)

    default void honk() {  // Default method (can be overridden)
        System.out.println("Honking...");
    }
}

class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Car is driving...");
    }
}

public class Test {
    public static void main(String[] args) {
        Car car = new Car();
        car.drive();  // Output: Car is driving...
        car.honk();  // Output: Honking...
    }
}
```

### **🔹 When to Use Interfaces?**

* **You want to define a contract for behavior that multiple classes can implement.**
* **You need multiple inheritance (since a class can implement multiple interfaces).**
* **You want to enable loose coupling between components.**
* **You're defining an API where behavior matters more than shared state.**

---

## **🟠 Abstract Class vs Interface: Feature Comparison Table**

| Feature                        | Abstract Class                                                           | Interface                                                      |
| ------------------------------ | ------------------------------------------------------------------------ | -------------------------------------------------------------- |
| **Fields**               | Can have instance variables (state)                                      | Only `public static final`constants                          |
| **Methods**              | Can have abstract and concrete methods                                   | Can have abstract,`default`, and `static`methods           |
| **Access Modifiers**     | Supports `private`,`protected`, and `public`members                | Methods are `public`by default                               |
| **Constructors**         | ✅ Can have constructors                                                 | ❌ Cannot have constructors                                    |
| **Multiple Inheritance** | ❌ Not supported (can extend only one class)                             | ✅ Supported (a class can implement multiple interfaces)       |
| **Code Reuse**           | ✅ Good for sharing code among related classes                           | ❌ Not ideal for code reuse (except default methods)           |
| **Use Case**             | When**common behavior and state**should be shared among subclasses | When**multiple unrelated classes**need to share behavior |

---

## **🛠 Choosing Between Abstract Class and Interface**

### **✅ Use an **Abstract Class** when:**

✔️ You want to provide **shared state** (fields) and behavior across related classes.

✔️ You need **access control** (`private`, `protected` methods).

✔️ You expect a **strict class hierarchy** and want to enforce an  **"is-a" relationship** .

### **✅ Use an **Interface** when:**

✔️ You want to define a **contract for behavior** across unrelated classes.

✔️ You need **multiple inheritance** (a class can implement multiple interfaces).

✔️ You want to ensure **loose coupling** between components.

✔️ You are **designing an API** where method signatures matter more than shared state.

---

## **🚀 Example: Abstract Class + Interface Together**

You can **combine** abstract classes and interfaces for maximum flexibility.

```java
interface Flyable {
    void fly();
}

abstract class Bird {
    protected String name;

    Bird(String name) {
        this.name = name;
    }

    abstract void makeSound();
}

class Sparrow extends Bird implements Flyable {
    Sparrow(String name) {
        super(name);
    }

    @Override
    void makeSound() {
        System.out.println("Chirp Chirp!");
    }

    @Override
    public void fly() {
        System.out.println(name + " is flying!");
    }
}

public class Test {
    public static void main(String[] args) {
        Sparrow sparrow = new Sparrow("Jack");
        sparrow.makeSound();  // Output: Chirp Chirp!
        sparrow.fly();  // Output: Jack is flying!
    }
}
```

### **💡 Key Takeaways**

✔️ The **abstract class `Bird`** handles common attributes (`name`) and behaviors (`makeSound`).

✔️ The **interface `Flyable`** defines flying behavior, allowing multiple classes (not just birds) to implement it.

✔️  **Flexible and reusable design** .

---

## **🎯 Conclusion**

| **Scenario**                                                 | **Use Abstract Class?** | **Use Interface?** |
| ------------------------------------------------------------------ | ----------------------------- | ------------------------ |
| You want to**share fields and methods**among related classes | ✅ Yes                        | ❌ No                    |
| You need**constructors**                                     | ✅ Yes                        | ❌ No                    |
| You want to enforce a**common base type**                    | ✅ Yes                        | ❌ No                    |
| You need**multiple inheritance**                             | ❌ No                         | ✅ Yes                   |
| You are**defining a contract for behavior**                  | ❌ No                         | ✅ Yes                   |
| You want to**reduce coupling**between components             | ❌ No                         | ✅ Yes                   |

### **Golden Rule**

* If **you need to define state and behavior** →  **Use an Abstract Class** .
* If **you need to define only behavior** (without state) →  **Use an Interface** .
* If **you need both** (state + behavior & multiple inheritance) →  **Use both** !

Would you like help designing your own class hierarchy? 🚀
