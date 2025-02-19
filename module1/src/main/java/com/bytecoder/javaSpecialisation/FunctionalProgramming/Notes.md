### **FunctionalInterface and Function Composition in Java**

#### **FunctionalInterface Overview**
- A **Functional Interface** is an interface with exactly **one abstract method** (SAM - Single Abstract Method).
- Used with **lambdas** and **method references** to enable functional programming.

#### **Key Points**:
1. **Annotation**: Use `@FunctionalInterface` (optional but recommended).
2. **Can Contain**:
    - One abstract method.
    - Multiple `default` and `static` methods.
    - Methods from `Object` class (e.g., `toString`, `equals`).
3. **Examples**:
   ```java
   @FunctionalInterface
   public interface MyInterface {
       void execute(); // Single Abstract Method
   }

   MyInterface obj = () -> System.out.println("Hello!");
   obj.execute();
   ```

---

#### **Built-in Functional Interfaces** (from `java.util.function`):

| **Interface**         | **Abstract Method**         | **Purpose**                          |
|-----------------------|-----------------------------|--------------------------------------|
| `Function<T, R>`      | `R apply(T t)`              | Transforms `T` to `R`.               |
| `Consumer<T>`         | `void accept(T t)`          | Performs an action on `T`.           |
| `Supplier<T>`         | `T get()`                   | Supplies a result with no input.     |
| `Predicate<T>`        | `boolean test(T t)`         | Tests a condition on `T`.            |
| `BiFunction<T, U, R>` | `R apply(T t, U u)`         | Transforms `T`, `U` to `R`.          |
| `UnaryOperator<T>`    | `T apply(T t)`              | A `Function` with same input/output. |
| `BinaryOperator<T>`   | `T apply(T t1, T t2)`       | A `BiFunction` with same types.      |

---

#### **Default and Static Methods in Functional Interfaces**
- **Default**: Provides reusable implementation.
  ```java
  default void log() {
      System.out.println("Logging...");
  }
  ```
- **Static**: Utility methods in the interface.
  ```java
  static void display() {
      System.out.println("Static Method");
  }
  ```

---

#### **Function Composition**
Combine multiple functions for sequential or pipeline execution.

##### **Key Methods**:
1. **`andThen(Function)`**: Executes the first function, then the second.
   ```java
   Function<Integer, Integer> multiplyBy2 = x -> x * 2;
   Function<Integer, Integer> add3 = x -> x + 3;

   Function<Integer, Integer> multiplyAndAdd = multiplyBy2.andThen(add3);
   System.out.println(multiplyAndAdd.apply(5)); // Output: 13
   ```

2. **`compose(Function)`**: Executes the second function, then the first.
   ```java
   Function<Integer, Integer> add3 = x -> x + 3;
   Function<Integer, Integer> multiplyBy2 = x -> x * 2;

   Function<Integer, Integer> addAndMultiply = multiplyBy2.compose(add3);
   System.out.println(addAndMultiply.apply(5)); // Output: 16
   ```

---

#### **Chaining Examples**

1. **Predicates**:
    - Combine conditions using `and`, `or`, `negate`.
   ```java
   Predicate<Integer> isEven = x -> x % 2 == 0;
   Predicate<Integer> isPositive = x -> x > 0;

   Predicate<Integer> isPositiveEven = isEven.and(isPositive);
   System.out.println(isPositiveEven.test(4)); // Output: true
   ```

2. **Consumers**:
    - Perform multiple actions using `andThen`.
   ```java
   Consumer<String> print = System.out::println;
   Consumer<String> printUpperCase = s -> System.out.println(s.toUpperCase());

   print.andThen(printUpperCase).accept("hello");
   // Output: hello
   //         HELLO
   ```

3. **Pipeline Example**:
    - Combine multiple transformations:
   ```java
   Function<String, String> trim = String::trim;
   Function<String, String> toUpperCase = String::toUpperCase;
   Function<String, String> addExclamation = s -> s + "!";

   Function<String, String> pipeline = trim.andThen(toUpperCase).andThen(addExclamation);
   System.out.println(pipeline.apply(" hello ")); // Output: HELLO!
   ```

---

#### **Why Use Functional Interfaces & Composition?**
1. **Simplifies Code**: Reduces boilerplate, making code concise and readable.
2. **Reusable Logic**: Functions can be reused and combined for complex operations.
3. **Stream Integration**: Works seamlessly with Java Streams.
4. **Declarative Programming**: Adopts functional programming principles.

