# Advanced Inheritance Concepts in Java - Interview Questions

## Senior/Lead Level Interview Questions

### Conceptual Questions

1. **What is the diamond problem in inheritance, and how does Java solve it?**

   - The diamond problem occurs when a class inherits from two classes that have a common ancestor
   - Java solves it by:
     * Prohibiting multiple class inheritance
     * Using interfaces with clear resolution rules for default methods
     * Requiring explicit override when ambiguous
2. **Why did Java introduce default methods in interfaces? What problems do they solve?**

   - Introduced in Java 8 to enable interface evolution
   - Allows adding new methods to interfaces without breaking existing implementations
   - Enables better API design and backward compatibility
   - Facilitates optional functionality in interfaces
3. **Compare and contrast abstract classes and interfaces in Java 8+. When would you choose one over the other?**

   - Abstract Classes:
     * Can have state and constructor
     * Support protected members
     * Single inheritance only
     * Better for related classes sharing code
   - Interfaces:
     * All public methods
     * No state (except constants)
     * Multiple inheritance
     * Better for defining contracts
4. **How does method resolution work when a class implements multiple interfaces with conflicting default methods?**

   - Class implementation takes precedence over default methods
   - Most specific interface implementation wins
   - Explicit override required when ambiguous
   - Can call specific interface implementations using super

### Scenario-Based Questions

5. **You're designing a logging system that needs to support multiple output formats (console, file, database). How would you use inheritance/interfaces to make it extensible?**

   ```java
   interface Logger {
       void log(String message);
       default void logError(String error) {
           log("ERROR: " + error);
       }
   }
   ```
6. **In a payment processing system, how would you design the hierarchy to support multiple payment methods while ensuring easy addition of new methods?**

   - Use interfaces for payment method contracts
   - Abstract classes for common functionality
   - Strategy pattern for payment processing
7. **How would you refactor a deep inheritance hierarchy to make it more maintainable?**

   - Consider composition over inheritance
   - Use interfaces to define behaviors
   - Extract common code into utility classes
   - Apply Interface Segregation Principle

### Advanced Technical Questions

8. **What are the implications of using default methods with lambda expressions and functional interfaces? (check details in other notes solution-2)**

   - Default methods can provide utility functions
   - Can affect method reference resolution
   - May influence type inference
   - Can be used to enhance functional interfaces
9. **How do sealed classes (Java 17+) affect inheritance? What problems do they solve? (for detail Solution-1)**

   - Restrict which classes can inherit
   - Provide better type safety
   - Enable exhaustive pattern matching
   - Help with maintenance and evolution
10. **Explain how virtual extension methods (default methods) affect the Liskov Substitution Principle? (limitations of default for details check solution 3)**

    - Must maintain behavioral subtyping
    - Default implementations should be truly optional
    - Should not break existing contract
    - May require documentation updates

### Design Pattern Questions

11. **How would you implement the Template Method pattern using both abstract classes and interfaces? Compare the approaches.**

    - Abstract class approach:
      * Better for fixed algorithms
      * Can protect helper methods
      * Can share state
    - Interface approach:
      * More flexible
      * Allows multiple inheritance
      * Better for optional steps
12. **When implementing the Strategy pattern, how do you decide between using interfaces and abstract classes?**

    - Interfaces for:
      * Multiple strategies possible
      * No shared state needed
      * Simple contracts
    - Abstract classes for:
      * Complex algorithms
      * Shared utility methods
      * Protected helper methods

### Best Practices Questions

13. **What are the best practices for designing interfaces in Java?**

    - Keep interfaces focused (Interface Segregation Principle)
    - Document default methods clearly
    - Consider backward compatibility
    - Use appropriate granularity
14. **How do you handle version evolution of interfaces with default methods?**

    - Add new methods as default
    - Document changes clearly
    - Consider binary compatibility
    - Provide migration guides
15. **What are common anti-patterns in inheritance, and how do you avoid them?**

    - Deep inheritance hierarchies
    - God interfaces
    - Inheritance for code reuse only
    - Breaking LSP

## Multiple Inheritance Through Interfaces

Java supports multiple inheritance through interfaces, allowing a class to implement multiple interfaces while avoiding the complications of multiple inheritance of implementation (diamond problem).

### Key Points:

1. A class can implement multiple interfaces
2. An interface can extend multiple interfaces
3. Modern Java features like default methods make interfaces more powerful
4. Resolving conflicts between default methods

## Default Methods in Interfaces

Introduced in Java 8, default methods allow interfaces to have method implementations while maintaining backward compatibility.

### Key Features:

1. Provide default implementation in interfaces
2. Can be overridden by implementing classes
3. Enable interface evolution without breaking existing implementations
4. Can access other interface methods (abstract and default)

### Rules for Default Methods:

1. Default methods must be marked with `default` modifier
2. Can be overridden by implementing classes
3. If a class implements multiple interfaces with same default method, it must override the method
4. Object class methods cannot be overridden as default methods
5. If multiple interfaces define the same method but without default implementations, **there is no conflict** because the implementing class must provide its own implementation.

## Abstract Classes vs Interfaces

Understanding when to use each is crucial for good design.

### Abstract Classes:

1. Can have constructor and state
2. Can have final methods
3. Can have protected and private members
4. Can have static and non-static fields
5. Better for defining a base class that encapsulates common state and behavior

### Interfaces:

1. All fields are public static final by default
2. All methods are public by default
3. Can have static and default methods
4. Better for defining a contract or capability
5. Support multiple inheritance

### When to Use Which:

- Use Abstract Class when:

  * You want to share code among several related classes
  * You have common fields and methods with access modifiers
  * You want to declare non-static or non-final fields
- Use Interface when:

  * You want to define a contract for a behavior
  * You need multiple inheritance
  * You want to enable loose coupling
  * You're defining an API

## Diamond Problem

The diamond problem occurs in multiple inheritance when a class inherits from two classes that have a common ancestor.

### Java's Solutions:

1. Prohibiting multiple inheritance of classes
2. Using interfaces with default methods
3. Clear rules for resolving conflicts:
   - Class methods take precedence over interface default methods
   - More specific interface wins
   - Explicit override required when ambiguous

### Best Practices:

1. Keep interfaces focused and cohesive
2. Use composition over inheritance when possible
3. Document default method behavior
4. Be explicit about overriding when dealing with multiple inheritance
5. Use interface inheritance hierarchies carefully
