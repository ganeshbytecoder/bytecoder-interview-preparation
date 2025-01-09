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

## 4. Best Practices and Common Pitfalls

### Immutability

- Benefits of immutable objects
- Implementing immutable classes
- Performance considerations
- Collections.unmodifiableXXX vs truly immutable collections

### Thread Safety

- Synchronization mechanisms
- Volatile keyword
- Atomic classes
- Thread-safe collections
- Lock-free programming

### Performance Optimization

- Object creation and pooling
- Memory management
- Lazy initialization
- Caching strategies

### Common Anti-patterns

- God Object
- Golden Hammer
- Premature Optimization
- Circular Dependencies
- Tight Coupling

## 5. Testing and Maintainability

### Testing OOP Code

- Unit testing best practices
- Mocking and stubbing
- Test doubles (Mock vs Stub vs Spy)
- Integration testing

### Code Quality

- Code smells and refactoring
- Clean code principles
- Documentation best practices
- Code reviews

### Maintainability

- Package structure
- Naming conventions
- Error handling
- Logging best practices

## 6. Modern Java OOP Features

### Records

- Immutable data classes
- Compact constructors
- Pattern matching with records

### Sealed Classes

- Controlling inheritance
- Pattern matching with sealed classes
- Combining with records

### Pattern Matching

- instanceof pattern matching
- Switch expressions with patterns
- Future developments

### Text Blocks

- Multiline string literals
- String templates (Java 21)
- Formatting and indentation

### **Complete List of Java Keywords**

Here is the full list of **Java keywords**:

1. `abstract`
2. `assert`
3. `boolean`
4. `break`
5. `byte`
6. `case`
7. `catch`
8. `char`
9. `class`
10. `const` (reserved)
11. `continue`
12. `default`
13. `do`
14. `double`
15. `else`
16. `enum`
17. `extends`
18. `final`
19. `finally`
20. `float`
21. `for`
22. `goto` (reserved)
23. `if`
24. `implements`
25. `import`
26. `instanceof`
27. `int`
28. `interface`
29. `long`
30. `module` (Java 9)
31. `native`
32. `new`
33. `null`
34. `open` (Java 9)
35. `opens` (Java 9)
36. `package`
37. `permits` (Java 17)
38. `private`
39. `protected`
40. `provides` (Java 9)
41. `public`
42. `requires` (Java 9)
43. `return`
44. `sealed` (Java 15)
45. `short`
46. `static`
47. `strictfp`
48. `super`
49. `switch`
50. `synchronized`
51. `this`
52. `throw`
53. `throws`
54. `to` (reserved)
55. `transient`
56. `try`
57. `uses` (Java 9)
58. `var` (Java 10)
59. `void`
60. `volatile`
61. `while`
62. `with` (reserved)
63. `yield` (Java 13)

---

## datatypes

## Difference between JDK, JRE, and JVM

## list of string to one string

```java

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConcatStrings {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("Hello", " ", "World");

        // Using the String.join() method (Java 8 and above)
        String joinedString = String.join("", strings);
        System.out.println(joinedString); // Output: Hello World

        // Using StringBuilder
        StringBuilder sb = new StringBuilder();
        for (String str : strings) {
            sb.append(str);
        }
        System.out.println(sb.toString()); // Output: Hello World

        // Using streams (Java 8 and above)
        String result = strings.stream().collect(Collectors.joining());
        System.out.println(result); // Output: Hello World
    }
}
```

Here is the full list of **Java keywords**:

1. `abstract`
2. `assert`
3. `const` (reserved)
4. `continue`
5. `default`
6. `do`
7. `enum`
8. `extends`
9. `final`
10. `finally`
11. `goto` (reserved)
12. `implements`
13. `import`
14. `instanceof`
15. `new`
16. `null`
17. `open` (Java 9)
18. `opens` (Java 9)
19. `permits` (Java 17)
20. `provides` (Java 9)
21. `requires` (Java 9)
22. `sealed` (Java 15)
23. `static`
24. `super`
25. `switch`
26. `synchronized`
27. `to` (reserved)
28. `transient`
29. `volatile`
30. `with` (reserved)
31. `yield` (Java 13)

I'll provide key use cases for important Java keywords that often come up in senior engineering interviews:

`abstract`:

- Abstract classes that can't be instantiated
- Abstract methods in interfaces/abstract classes
- Template method pattern implementation

`final`:

- Immutable variables/references
- Preventing method overriding
- Classes that can't be inherited
- Performance optimization (JVM)

`static`:

- Class-level members/methods
- Static initialization blocks
- Static factory methods
- Utility classes
- Static imports
- Memory management

`synchronized`:

- Method-level thread synchronization
- Block-level synchronization
- Double-checked locking pattern
- Thread-safe singleton implementation
- Collection synchronization

`volatile`:

- Thread-safe variable updates
- Double-checked locking
- Flag variables in multi-threaded code
- Ensuring memory visibility

`transient`:

- Excluding fields from serialization
- Security-sensitive data
- Caching implementations
- Resource handles

`throws`:

- Exception propagation
- Checked exception handling
- API contract definition
- Method overriding rules

`sealed` (Java 15+):

- Restricting class inheritance
- Domain model enforcement
- API design control
- Framework development

`instanceof`:

- Type checking
- Safe casting
- Pattern matching (Java 16+)
- Polymorphic behavior

Here's a focused breakdown for senior Java interviews:

**Thread Safety Implications**

Key Concepts:

- Race conditions
- Memory visibility
- Atomic operations
- Happens-before relationship
- Thread confinement

Implementation Mechanisms:

```java
// 1. Synchronized Methods
public synchronized void method() {}

// 2. Synchronized Blocks
synchronized(lock) {
    // critical section
}

// 3. Volatile Variables
private volatile boolean flag;

// 4. Atomic Classes
private AtomicInteger counter = new AtomicInteger();

// 5. Thread-Safe Collections
Map<K,V> map = Collections.synchronizedMap(new HashMap<>());
ConcurrentHashMap<K,V> concurrentMap = new ConcurrentHashMap<>();
```

Common Interview Questions:

1. Double-checked locking problems
2. Singleton thread-safety
3. ConcurrentModificationException causes
4. Deadlock detection/prevention
5. Compare synchronized vs volatile

**Performance Considerations**

Key Areas:

1. Memory Access

```java
// Cache-friendly code
// Contiguous memory access
for(int i = 0; i < array.length; i++) {
    // sequential access pattern
}
```

2. Lock Contention

```java
// Fine-grained locking
private final Object[] locks = new Object[16];
int segment = hash % locks.length;
synchronized(locks[segment]) {
    // reduced contention
}
```

3. GC Impact

```java
// Object pooling for heavy objects
class ObjectPool<T> {
    private final Queue<T> pool;
    public T acquire() {...}
    public void release(T obj) {...}
}
```

**Memory Management**

Key Concepts:

1. Heap vs Stack

```java
// Stack: primitives, references
int x = 5;
// Heap: objects
Object obj = new Object();
```

2. Memory Leaks

```java
// Common causes
// 1. Static Collections
private static final List<Data> cache = new ArrayList<>();

// 2. Unclosed Resources
try (FileInputStream fis = new FileInputStream(file)) {
    // proper resource management
}

// 3. Inner Class References
private static class StaticInnerClass {} // prevents memory leaks
```

3. Garbage Collection

- Young/Old Generation
- GC Algorithms (CMS, G1, ZGC)
- GC Tuning Parameters

Interview Focus Points:

1. OutOfMemoryError handling
2. Memory leak detection
3. GC tuning experience
4. Heap dump analysis
5. Performance optimization strategies

Need specific details on any of these areas?

Here's a detailed breakdown of key interview focus points for senior Java developers:

1. OutOfMemoryError Handling

```java
// Prevention strategies
-XX:HeapDumpOnOutOfMemoryError // JVM argument
-XX:HeapDumpPath=/path/to/dump // Dump location

// Handling in code
try {
    // memory-intensive operation
} catch (OutOfMemoryError oome) {
    // 1. Log error details
    // 2. Free resources
    // 3. Reset state
    // 4. Notify monitoring systems
    System.gc(); // Request GC (use carefully)
}
```

2. Memory Leak Detection

```java
// Common patterns
class LeakExample {
    // 1. Static collections growing unbounded
    static List<Object> leakyList = new ArrayList<>();
  
    // 2. Unclosed resources
    void leakyMethod() {
        Connection conn = getConnection();
        // Missing conn.close()
    }
  
    // 3. Event listener not removed
    class LeakyListener implements ActionListener {
        // Not removed when parent is destroyed
    }
}

// Detection tools:
// - JVisualVM
// - Eclipse MAT
// - JProfiler
```

3. GC Tuning Experience

```bash
# Key JVM flags
-XX:NewRatio=2
-XX:SurvivorRatio=8
-XX:MaxTenuringThreshold=15
-XX:+UseG1GC
-XX:ConcGCThreads=4

# Monitoring commands
jstat -gcutil <pid> 1000
jmap -heap <pid>
```

4. Heap Dump Analysis

```java
// Key metrics to analyze
1. Dominator tree
2. Biggest objects
3. Reference chains
4. Memory leaks
5. GC roots

// Tools used
Eclipse MAT commands:
- Leak Suspects Report
- Top Consumers
- Path to GC Roots
```

5. Performance Optimization

```java
// 1. Code level
final StringBuilder sb = new StringBuilder(expectedSize); // Pre-sizing
ArrayList<T> list = new ArrayList<>(initialCapacity); // Initial capacity

// 2. Data structures
Map<K,V> map = capacity < 1000 ? new HashMap<>() : new ConcurrentHashMap<>();

// 3. Caching
@Cacheable(value = "users")
public User getUser(String id) {}

// 4. Batch processing
@Transactional
public void batchInsert(List<Entity> entities) {
    for (int i = 0; i < entities.size(); i++) {
        if (i > 0 && i % BATCH_SIZE == 0) {
            entityManager.flush();
            entityManager.clear();
        }
        entityManager.persist(entities.get(i));
    }
}
```

Common Interview Questions:

1. How do you identify memory leaks in production?
2. What GC algorithm would you choose for low-latency applications?
3. How do you handle OutOfMemoryError in microservices?
4. Explain your approach to performance optimization in a real project.
5. What tools do you use for memory analysis?
