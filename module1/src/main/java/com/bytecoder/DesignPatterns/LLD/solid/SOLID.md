## 1. SOLID Principles

### Single Responsibility Principle (SRP)

- A class should have only one reason to change
- Each class should focus on doing one thing well
- Example: Separating data access, business logic, and presentation layers

### Open/Closed Principle (OCP)

- Software entities should be open for extension but closed for modification
- Use abstractions and interfaces to allow new functionality through inheritance/implementation
- Example: Plugin architectures, Strategy pattern

### Liskov Substitution Principle (LSP)

- Subtypes must be substitutable for their base types without altering program correctness
- Inheritance should ensure that the child class can replace its parent class without breaking functionality
- Common violations: Breaking contracts, throwing unexpected exceptions

### Interface Segregation Principle (ISP)

- Clients should not be forced to depend on interfaces they don't use
- Prefer multiple specific interfaces over one general-purpose interface
- Example: Splitting large interfaces into smaller, focused ones

### Dependency Inversion Principle (DIP)

- High-level modules should not depend on low-level modules; both should depend on abstractions
- Abstractions should not depend on details; details should depend on abstractions
- Example: Dependency injection, IoC containers


### DRY, KISS, 