In Java, exception handling allows you to handle runtime errors and exceptional conditions in a controlled way, preventing the program from crashing unexpectedly. Java uses the `try`, `catch`, `finally`, and `throw` keywords for exception handling.

### **Exception Handling Keywords in Java**

1. **`try`**:
    - Wraps code that might throw an exception.

2. **`catch`**:
    - Catches the exception thrown by the `try` block and handles it.

3. **`finally`**:
    - A block that is always executed, regardless of whether an exception occurs or not. It is typically used for cleanup (e.g., closing files or database connections).

4. **`throw`**:
    - Used to explicitly throw an exception.

5. **`throws`**:
    - Used to declare that a method might throw an exception. The method specifies which exceptions it can throw.

---

### **Basic Syntax**

```java
try {
    // Code that might throw an exception
} catch (ExceptionType e) {
    // Handle the exception
} finally {
    // Code that will always run
}
```

---

### **Example 1: Basic Exception Handling**

```java
public class Example {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // This will throw ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero!");
        } finally {
            System.out.println("This will always execute.");
        }
    }
}
```

**Output**:
```
Cannot divide by zero!
This will always execute.
```

---

### **Example 2: Multiple Catch Blocks**

You can handle different types of exceptions using multiple `catch` blocks.

```java
public class Example {
    public static void main(String[] args) {
        try {
            String str = null;
            System.out.println(str.length()); // NullPointerException

            int result = 10 / 0; // ArithmeticException
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception occurred!");
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic error occurred!");
        } finally {
            System.out.println("Finally block executed.");
        }
    }
}
```

**Output**:
```
Null pointer exception occurred!
Finally block executed.
```

---

### **Example 3: `throws` and `throw`**

The `throws` keyword is used to declare exceptions that a method can throw, while `throw` is used to explicitly throw an exception.

```java
public class Example {

    // Method that can throw an exception
    public static void checkAge(int age) throws IllegalArgumentException {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be at least 18.");
        } else {
            System.out.println("Age is valid.");
        }
    }

    public static void main(String[] args) {
        try {
            checkAge(16); // Throws IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
```

**Output**:
```
Age must be at least 18.
```

---

### **Example 4: Custom Exception**

You can create your own custom exceptions by extending the `Exception` class or any of its subclasses.

```java
// Define custom exception
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class Example {

    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or older.");
        }
        System.out.println("Valid age.");
    }

    public static void main(String[] args) {
        try {
            validateAge(15); // Throws InvalidAgeException
        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

**Output**:
```
Error: Age must be 18 or older.
```

---

### **Example 5: Try-With-Resources (Java 7 and later)**

In Java 7 and later, the `try-with-resources` statement automatically closes resources like files, sockets, or database connections. Resources that implement the `AutoCloseable` interface are automatically closed when the `try` block finishes.

```java
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Example {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("example.txt"))) {
            String line = reader.readLine();
            System.out.println(line);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        }
    }
}
```

- **Automatic Resource Management**: In the `try` block, `BufferedReader` will automatically be closed when the block finishes executing, even if an exception occurs.

---

### **Best Practices for Exception Handling in Java**

1. **Catch Specific Exceptions**:
    - Always catch specific exceptions instead of using a generic `Exception` class. This makes error handling more precise and easier to debug.

2. **Avoid Empty `catch` Blocks**:
    - Don't leave the `catch` block empty. Always log the exception or take appropriate action.

3. **Use `finally` for Cleanup**:
    - Place resource cleanup (e.g., closing files or database connections) in the `finally` block to ensure it always executes.

4. **Throw Custom Exceptions**:
    - Define custom exceptions when the built-in exceptions do not provide enough information about the error.

5. **Don't Use Exceptions for Control Flow**:
    - Exceptions should be used for exceptional conditions, not for normal control flow.

---

### Summary
Java's exception handling mechanism is powerful and flexible, helping you manage runtime errors effectively. By using `try`, `catch`, `finally`, `throw`, and `throws`, you can write robust code that can handle both expected and unexpected errors gracefully.



In Java, you can create both **checked exceptions** and **unchecked exceptions** by defining custom exception classes. Checked exceptions are exceptions that must be declared in a method’s signature using the `throws` keyword, and they are usually related to recoverable conditions. Unchecked exceptions, on the other hand, do not need to be declared and are typically used for programming errors or runtime issues.

### **1. Custom Checked Exception**

A **checked exception** is a subclass of `Exception` (but not `RuntimeException`). It must be either caught in a `try-catch` block or declared to be thrown in the method signature using the `throws` keyword.

#### Creating a Custom Checked Exception

```java
// Define a custom checked exception
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class CustomExceptionExample {

    // Method that throws the custom checked exception
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or older.");
        } else {
            System.out.println("Valid age: " + age);
        }
    }

    public static void main(String[] args) {
        try {
            validateAge(16);  // This will throw InvalidAgeException
        } catch (InvalidAgeException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}
```

**Output**:
```
Caught exception: Age must be 18 or older.
```

### Explanation:
- **Checked exception** `InvalidAgeException` is created by extending the `Exception` class.
- The `validateAge` method **declares** that it can throw `InvalidAgeException` using `throws`.
- The exception is caught in the `catch` block and handled.

---

### **2. Custom Unchecked Exception**

An **unchecked exception** is a subclass of `RuntimeException` (or its subclasses). These exceptions do not need to be declared in the method signature and are typically used to indicate programming errors or conditions that are unexpected but not recoverable.

#### Creating a Custom Unchecked Exception

```java
// Define a custom unchecked exception
class NegativeAgeException extends RuntimeException {
    public NegativeAgeException(String message) {
        super(message);
    }
}

public class CustomUncheckedExceptionExample {

    // Method that throws the custom unchecked exception
    public static void validateAge(int age) {
        if (age < 0) {
            throw new NegativeAgeException("Age cannot be negative.");
        } else {
            System.out.println("Valid age: " + age);
        }
    }

    public static void main(String[] args) {
        try {
            validateAge(-5);  // This will throw NegativeAgeException
        } catch (NegativeAgeException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}
```

**Output**:
```
Caught exception: Age cannot be negative.
```

### Explanation:
- **Unchecked exception** `NegativeAgeException` is created by extending `RuntimeException`.
- The `validateAge` method **does not declare** that it throws `NegativeAgeException`.
- The exception is thrown and caught using `try-catch`.

---

### **Key Differences Between Checked and Unchecked Exceptions**

| Feature                         | Checked Exception                     | Unchecked Exception                        |
|----------------------------------|---------------------------------------|--------------------------------------------|
| **Class to Extend**              | Extends `Exception` (but not `RuntimeException`) | Extends `RuntimeException`                 |
| **Declaration Required**         | Must be declared in method signature with `throws` | Not required to be declared                |
| **Examples**                     | `IOException`, `SQLException`, `FileNotFoundException` | `NullPointerException`, `ArrayIndexOutOfBoundsException` |
| **Usage**                        | For recoverable conditions where the caller can handle the error | For programming errors or unexpected situations that cannot typically be recovered from |
| **Handling**                     | Must be handled with `try-catch` or declared with `throws` | Can be handled (though not required) |

---

### **Best Practices for Custom Exceptions**
1. **Use Checked Exceptions** for conditions that are recoverable and where the caller can take corrective actions (e.g., invalid user input, file read errors).
2. **Use Unchecked Exceptions** for programming errors or unexpected conditions that are not meant to be recovered from (e.g., invalid method arguments, null pointer errors).
3. **Provide Useful Messages** in custom exception constructors to make debugging easier.
4. **Keep It Simple**: Avoid using custom exceptions unless they add significant value to the clarity of the code.

---

### **Summary**
- **Checked exceptions** should be used for situations that are recoverable and can be explicitly handled by the caller.
- **Unchecked exceptions** are used for conditions that generally indicate bugs or unexpected failures in the code, where recovery is not possible.
- You can create custom exceptions by extending either `Exception` for checked exceptions or `RuntimeException` for unchecked exceptions.