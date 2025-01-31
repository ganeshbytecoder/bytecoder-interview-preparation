Exception handling in Python allows you to manage runtime errors gracefully, preventing program crashes and enabling recovery from unexpected conditions. Python uses `try`, `except`, `else`, and `finally` blocks for handling exceptions.

---

### **Basic Syntax**

```python
try:
    # Code that might raise an exception
    risky_operation()
except SomeException as e:
    # Handle the exception
    print(f"An error occurred: {e}")
else:
    # Code that runs if no exception occurs
    print("Operation successful.")
finally:
    # Code that always runs, regardless of whether an exception occurred
    print("Execution completed.")
```

---

### **Components**

1. **`try` Block**:
    - Contains the code that might raise an exception.

2. **`except` Block**:
    - Handles the exception if it occurs.
    - You can specify the exception type or use a generic handler.

3. **`else` Block** *(optional)*:
    - Executes if no exceptions occur in the `try` block.

4. **`finally` Block** *(optional)*:
    - Executes no matter what, often used for cleanup.

---

### **Examples**

#### 1. Handling a Specific Exception
```python
try:
    result = 10 / 0  # This will raise a ZeroDivisionError
except ZeroDivisionError:
    print("Cannot divide by zero!")
```

**Output**:
```
Cannot divide by zero!
```

---

#### 2. Catching Multiple Exceptions
```python
try:
    value = int("abc")  # This will raise a ValueError
except (ValueError, TypeError) as e:
    print(f"An error occurred: {e}")
```

**Output**:
```
An error occurred: invalid literal for int() with base 10: 'abc'
```

---

#### 3. Generic Exception Handling
```python
try:
    value = int("abc")
except Exception as e:  # Catches all exceptions
    print(f"An unexpected error occurred: {e}")
```

**Output**:
```
An unexpected error occurred: invalid literal for int() with base 10: 'abc'
```

---

#### 4. Using `else`
```python
try:
    result = 10 / 2
except ZeroDivisionError:
    print("Cannot divide by zero!")
else:
    print(f"The result is: {result}")
```

**Output**:
```
The result is: 5.0
```

---

#### 5. Using `finally`
```python
try:
    file = open("test.txt", "r")
    content = file.read()
except FileNotFoundError:
    print("File not found!")
finally:
    print("Closing resources...")
```

**Output**:
```
File not found!
Closing resources...
```

---

### **Raising Exceptions**
You can explicitly raise exceptions using the `raise` statement.

#### Example:
```python
def check_age(age):
    if age < 18:
        raise ValueError("Age must be at least 18.")
    return "Access granted."

try:
    print(check_age(16))
except ValueError as e:
    print(f"Error: {e}")
```

**Output**:
```
Error: Age must be at least 18.
```

---

### **Custom Exceptions**
You can define your own exceptions by subclassing `Exception`.

#### Example:
```python
class CustomError(Exception):
    pass

try:
    raise CustomError("This is a custom error.")
except CustomError as e:
    print(f"Caught custom exception: {e}")
```

**Output**:
```
Caught custom exception: This is a custom error.
```

---

### **Best Practices for Exception Handling**
1. **Be Specific**:
    - Catch specific exceptions rather than using a generic `except Exception`.

2. **Avoid Silent Failures**:
    - Log or display meaningful messages to help with debugging.

3. **Use `finally` for Cleanup**:
    - Ensure resources (e.g., files, database connections) are properly closed.

4. **Don’t Use Exceptions for Control Flow**:
    - Avoid using exceptions as an alternative to normal logic (e.g., checking conditions).

5. **Custom Exceptions**:
    - Create custom exceptions for application-specific errors for better clarity.

---

### Summary
Python's exception handling mechanism is flexible, allowing you to deal with expected and unexpected errors gracefully. By using `try`, `except`, `else`, and `finally`, you can write robust and error-resilient programs.


Creating a custom exception in Python involves subclassing the built-in `Exception` class (or one of its subclasses). This allows you to define meaningful exceptions specific to your application's domain, making error handling more structured and readable.

---

### **Steps to Create Custom Exceptions**

1. **Define a Custom Exception Class**
    - Subclass the `Exception` class.
    - Add a custom message or attributes if needed.

2. **Raise the Custom Exception**
    - Use the `raise` keyword to throw the exception.

3. **Handle the Custom Exception**
    - Use a `try`-`except` block to catch the custom exception.

---

### **Basic Example**

```python
# Define a custom exception
class InvalidAgeError(Exception):
    def __init__(self, age, message="Age must be 18 or older."):
        self.age = age
        self.message = message
        super().__init__(self.message)

# Function that raises the custom exception
def validate_age(age):
    if age < 18:
        raise InvalidAgeError(age)
    return "Age is valid!"

# Handle the custom exception
try:
    print(validate_age(16))  # This will raise InvalidAgeError
except InvalidAgeError as e:
    print(f"InvalidAgeError: {e.message} (Provided Age: {e.age})")
```

**Output**:
```
InvalidAgeError: Age must be 18 or older. (Provided Age: 16)
```

---

### **Custom Exception with Multiple Attributes**

```python
# Define a custom exception with multiple attributes
class InsufficientFundsError(Exception):
    def __init__(self, balance, amount, message="Insufficient funds for the transaction."):
        self.balance = balance
        self.amount = amount
        self.message = f"{message} Available balance: {balance}, Required: {amount}."
        super().__init__(self.message)

# Function that raises the custom exception
def withdraw(balance, amount):
    if amount > balance:
        raise InsufficientFundsError(balance, amount)
    return balance - amount

# Handle the custom exception
try:
    print(withdraw(500, 1000))  # This will raise InsufficientFundsError
except InsufficientFundsError as e:
    print(f"InsufficientFundsError: {e.message}")
```

**Output**:
```
InsufficientFundsError: Insufficient funds for the transaction. Available balance: 500, Required: 1000.
```

---

### **Custom Base Exception for Application**
You can create a hierarchy of custom exceptions by defining a base class for all your custom exceptions.

```python
# Base custom exception
class ApplicationError(Exception):
    """Base class for all application-specific exceptions"""
    pass

# Specific custom exceptions
class DatabaseError(ApplicationError):
    pass

class ValidationError(ApplicationError):
    pass

# Using custom exceptions
try:
    raise DatabaseError("Database connection failed.")
except ApplicationError as e:
    print(f"ApplicationError: {e}")
```

**Output**:
```
ApplicationError: Database connection failed.
```

---

### **Key Points**
1. **`__init__` Customization**:
    - Add attributes to store additional information about the error.

2. **Custom Message**:
    - Use `super().__init__()` to pass the custom message to the base `Exception` class.

3. **Hierarchical Exceptions**:
    - Use a base custom exception to group related exceptions logically.

---

### **When to Use Custom Exceptions**
- To handle domain-specific errors (e.g., `InvalidAgeError`, `InsufficientFundsError`).
- To differentiate between different types of errors in large applications.
- To provide detailed context about the error.


### Common Runtime Exceptions in Python:
- ZeroDivisionError: Raised when dividing by zero.
- IndexError: Raised when accessing an invalid index in a list or string.
- TypeError: Raised when an operation or function is applied to an object of inappropriate type.
- ValueError: Raised when a function receives an argument of the correct type but an inappropriate value.
- KeyError: Raised when a dictionary key is not found.