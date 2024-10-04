### Auto-unboxing in Java

**Auto-unboxing** in Java is the automatic conversion of an object of a wrapper class (like `Integer`, `Double`, `Boolean`, etc.) into its corresponding primitive type (`int`, `double`, `boolean`, etc.). This is the reverse of **auto-boxing**, where a primitive type is automatically converted into its corresponding wrapper class object.

Auto-unboxing occurs in situations where a primitive value is expected, but the value is provided as a wrapper object. Java automatically extracts the primitive value from the wrapper object.

### Example of Auto-unboxing

Here's a simple example of auto-unboxing in action:

```java
public class AutoUnboxingExample {
    public static void main(String[] args) {
        Integer integerObject = 10;  // Auto-boxing happens here
        int primitiveInt = integerObject;  // Auto-unboxing happens here

        System.out.println("Integer object: " + integerObject);
        System.out.println("Primitive int: " + primitiveInt);
    }
}
```

**Explanation:**
- In the first line, `Integer integerObject = 10;`, Java automatically boxes the primitive `int` value `10` into an `Integer` object (`auto-boxing`).
- In the second line, `int primitiveInt = integerObject;`, Java automatically unboxes the `Integer` object `integerObject` back into its primitive `int` value (`auto-unboxing`).

### Where Auto-unboxing Occurs

Auto-unboxing happens in several situations:

1. **Assignment**: When assigning a wrapper class object to a primitive type variable.
   ```java
   Integer integerObject = 100;
   int primitiveInt = integerObject;  // Auto-unboxing
   ```

2. **Arithmetic Operations**: When performing arithmetic operations with wrapper objects.
   ```java
   Integer a = 100;
   Integer b = 50;
   int sum = a + b;  // Auto-unboxing happens before the addition
   ```

3. **Comparison Operations**: When using relational operators like `<`, `>`, `==`, etc.
   ```java
   Integer a = 10;
   Integer b = 20;
   if (a < b) {  // Auto-unboxing happens before the comparison
       System.out.println("a is less than b");
   }
   ```

4. **Method Arguments**: When passing a wrapper class object as an argument to a method that expects a primitive type.
   ```java
   public static void printNumber(int number) {
       System.out.println(number);
   }

   public static void main(String[] args) {
       Integer integerObject = 42;
       printNumber(integerObject);  // Auto-unboxing happens here
   }
   ```

5. **Return Values**: When returning a wrapper class object from a method that has a primitive return type.
   ```java
   public static int getNumber() {
       Integer number = 100;
       return number;  // Auto-unboxing happens here
   }
   ```

### Common Wrapper Classes Supporting Auto-unboxing

- `Integer` → `int`
- `Double` → `double`
- `Float` → `float`
- `Long` → `long`
- `Short` → `short`
- `Byte` → `byte`
- `Boolean` → `boolean`
- `Character` → `char`

### Important Considerations

1. **Null Pointer Exception**: Auto-unboxing can throw a `NullPointerException` if the wrapper object is `null`. For example:

   ```java
   Integer integerObject = null;
   int primitiveInt = integerObject;  // Throws NullPointerException
   ```

2. **Performance Overhead**: While auto-unboxing provides convenience, it can introduce a performance overhead because of the implicit conversion. In performance-critical applications, it's important to be aware of this overhead, particularly in situations like loops where boxing and unboxing might happen repeatedly.

### Example Showing `NullPointerException` with Auto-unboxing:

```java
public class AutoUnboxingNullExample {
    public static void main(String[] args) {
        Integer integerObject = null;

        try {
            int primitiveInt = integerObject;  // This will throw NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException: " + e.getMessage());
        }
    }
}
```


Java's **Wrapper Classes** provide methods that allow you to work with primitive types (`int`, `double`, `boolean`, etc.) as objects. Each primitive type in Java has a corresponding wrapper class in the `java.lang` package. Wrapper classes provide several utility methods for converting between different types, parsing values, and comparing objects. Below are the key functions provided by the wrapper classes.

### Common Wrapper Classes and Their Primitive Equivalents

- `Integer` → `int`
- `Double` → `double`
- `Float` → `float`
- `Long` → `long`
- `Short` → `short`
- `Byte` → `byte`
- `Boolean` → `boolean`
- `Character` → `char`

### Key Methods in Wrapper Classes

#### 1. **`valueOf()`**
   Converts a primitive type or string into its corresponding wrapper object.

   - **Integer.valueOf(int i)**: Converts a primitive `int` to an `Integer` object.
   - **Integer.valueOf(String s)**: Converts a string to an `Integer` object.
   
   ```java
   Integer intObj1 = Integer.valueOf(42);
   Integer intObj2 = Integer.valueOf("42");
   ```

#### 2. **`parse<Type>()`**
   Converts a string to the corresponding primitive type.

   - **Integer.parseInt(String s)**: Parses a string and returns an `int`.
   - **Double.parseDouble(String s)**: Parses a string and returns a `double`.

   ```java
   int number = Integer.parseInt("42");
   double value = Double.parseDouble("42.0");
   ```

#### 3. **`toString()`**
   Converts the wrapper object to a string representation of the primitive value.

   - **Integer.toString()**: Converts an `Integer` object to its string representation.
   - **Boolean.toString()**: Converts a `Boolean` object to a string representation.

   ```java
   Integer intObj = 42;
   String str = intObj.toString();  // Output: "42"
   ```

#### 4. **`<Type>.TYPE`**
   Provides the `Class` object representing the corresponding primitive type.

   ```java
   Class<?> intClass = Integer.TYPE;  // Represents the primitive type int
   ```

#### 5. **`<Type>.compareTo()`**
   Compares two wrapper objects lexicographically or numerically.

   - **`int compareTo(T anotherT)`**: Compares the current wrapper object with another wrapper object of the same type.

   ```java
   Integer a = 10;
   Integer b = 20;
   int result = a.compareTo(b);  // Output: negative because a < b
   ```

#### 6. **`<Type>.compare()`**
   Compares two values (either primitives or wrapper objects).

   - **`int compare(int x, int y)`**: Compares two `int` values.
   - **`int compare(double x, double y)`**: Compares two `double` values.

   ```java
   int result = Integer.compare(10, 20);  // Output: negative because 10 < 20
   ```

#### 7. **`<Type>.equals()`**
   Compares the wrapper object to another object for equality.

   - **`boolean equals(Object obj)`**: Checks if the current wrapper object is equal to the specified object.

   ```java
   Integer a = 100;
   Integer b = 100;
   boolean isEqual = a.equals(b);  // Output: true
   ```

#### 8. **`<Type>.hashCode()`**
   Returns the hash code of the wrapper object.

   - **`int hashCode()`**: Returns a hash code for the current object.

   ```java
   Integer a = 42;
   int hash = a.hashCode();  // Output: 42
   ```

#### 9. **`<Type>.MIN_VALUE` and `<Type>.MAX_VALUE`**
   These are constants that provide the minimum and maximum values for the corresponding primitive type.

   - **`int Integer.MIN_VALUE`**: The minimum value for an `int` (−2^31).
   - **`int Integer.MAX_VALUE`**: The maximum value for an `int` (2^31 - 1).

   ```java
   int minInt = Integer.MIN_VALUE;  // Output: -2147483648
   int maxInt = Integer.MAX_VALUE;  // Output: 2147483647
   ```

#### 10. **`<Type>.is<Type>()`**
   Methods available in the `Character` class to check the type of a character.

   - **`boolean isDigit(char ch)`**: Checks if the character is a digit.
   - **`boolean isLetter(char ch)`**: Checks if the character is a letter.

   ```java
   boolean isDigit = Character.isDigit('5');  // Output: true
   boolean isLetter = Character.isLetter('A');  // Output: true
   ```

#### 11. **`<Type>.get<Type>()`**
   Extracts the primitive value from the wrapper object.

   - **`int intValue()`**: Extracts the primitive `int` value from an `Integer` object.
   - **`double doubleValue()`**: Extracts the primitive `double` value from a `Double` object.

   ```java
   Integer intObj = 42;
   int primitiveInt = intObj.intValue();  // Output: 42
   ```

#### 12. **`<Type>.toXxxString()`**
   Converts the primitive type or wrapper object to a string in different number systems.

   - **`String toBinaryString(int i)`**: Returns a string representation of the integer in binary.
   - **`String toHexString(int i)`**: Returns a string representation of the integer in hexadecimal.
   - **`String toOctalString(int i)`**: Returns a string representation of the integer in octal.

   ```java
   String binary = Integer.toBinaryString(10);  // Output: "1010"
   String hex = Integer.toHexString(255);  // Output: "ff"
   ```

#### 13. **`<Type>.decode(String nm)`**
   Decodes a string into a primitive type or wrapper object. It can handle decimal, hexadecimal (`0x`), and octal (`0`) representations.

   ```java
   Integer decoded = Integer.decode("0x1A");  // Output: 26
   ```

### Wrapper Class Methods by Type

| Wrapper Class | Key Methods                                                    |
|---------------|----------------------------------------------------------------|
| `Integer`     | `valueOf()`, `parseInt()`, `toString()`, `compare()`, `intValue()` |
| `Double`      | `valueOf()`, `parseDouble()`, `toString()`, `compare()`, `doubleValue()` |
| `Float`       | `valueOf()`, `parseFloat()`, `toString()`, `compare()`, `floatValue()` |
| `Long`        | `valueOf()`, `parseLong()`, `toString()`, `compare()`, `longValue()` |
| `Short`       | `valueOf()`, `parseShort()`, `toString()`, `compare()`, `shortValue()` |
| `Byte`        | `valueOf()`, `parseByte()`, `toString()`, `compare()`, `byteValue()` |
| `Boolean`     | `valueOf()`, `parseBoolean()`, `toString()`, `compare()`, `booleanValue()` |
| `Character`   | `valueOf()`, `toString()`, `compare()`, `charValue()`, `isDigit()`, `isLetter()` |

### Example: Using Wrapper Class Methods

```java
public class WrapperClassExample {
    public static void main(String[] args) {
        // Using Integer wrapper class
        Integer intObj = Integer.valueOf(42);  // Boxing
        int intPrimitive = intObj.intValue();  // Unboxing

        // Parsing a string to an int
        int number = Integer.parseInt("123");

        // Comparing two Integer objects
        Integer a = 100;
        Integer b = 200;
        int comparison = Integer.compare(a, b);  // Output: negative because a < b

        // Minimum and Maximum values
        int minValue = Integer.MIN_VALUE;  // -2147483648
        int maxValue = Integer.MAX_VALUE;  // 2147483647

        // Converting int to binary string
        String binaryString = Integer.toBinaryString(10);  // Output: "1010"

        // Print values
        System.out.println("intObj: " + intObj);
        System.out.println("intPrimitive: " + intPrimitive);
        System.out.println("Parsed number: " + number);
        System.out.println("Comparison result: " + comparison);
        System.out.println("Min Value: " + minValue);
        System.out.println("Max Value: " + maxValue);
        System.out.println("Binary String: " + binaryString);
    }
}
```

### Summary

Java's wrapper classes (`Integer`, `Double`, `Boolean`, etc.) provide various utility methods to:
- **Convert** between primitives and strings (`valueOf()`, `parse<Type>()`).
- **Compare** wrapper objects and primitives (`compareTo()`, `compare()`).
- **Perform type checks** (for `Character` class).
- **Get** and **extract



-----------


Handling `date`, `datetime`, and `timestamp` in databases, Python, and Java can be tricky but essential for creating robust applications that work across different time zones and systems. Here's a breakdown of how to manage them in each environment:

---

## **1. Database Handling of `date`, `datetime`, and `timestamp`:**

### SQL Databases (MySQL, PostgreSQL, etc.)

#### `DATE`
- **Format:** Typically `YYYY-MM-DD` (e.g., `2024-10-04`)
- **Usage:** Stores only the date part, with no time information.
- **Example in SQL:**
  ```sql
  CREATE TABLE events (
      event_date DATE
  );
  ```

#### `DATETIME`
- **Format:** `YYYY-MM-DD HH:MM:SS` (e.g., `2024-10-04 14:30:00`)
- **Usage:** Stores both the date and time, but no timezone information.
- **Example in SQL:**
  ```sql
  CREATE TABLE events (
      event_timestamp DATETIME
  );
  ```

#### `TIMESTAMP`
- **Format:** `YYYY-MM-DD HH:MM:SS` (e.g., `2024-10-04 14:30:00`)
- **Usage:** Stores date and time with timezone or the offset from UTC. Automatically converts values to UTC upon insertion and back to the server's local time zone when retrieved (depending on database settings).
- **Example in SQL:**
  ```sql
  CREATE TABLE events (
      event_time TIMESTAMP
  );
  ```

### Considerations for Database:

- **Time Zone Handling:** `TIMESTAMP` can handle time zones and automatically converts values to UTC before storing.
- **Performance:** `DATE` and `DATETIME` types have better performance if you don't need to deal with time zones.

---

## **2. Python Handling of `date`, `datetime`, and `timestamp`:**

Python provides the `datetime` module to work with dates and times.

### `date`
- **Description:** Represents a date (year, month, and day) without time.
- **Usage:**
  ```python
  from datetime import date

  today = date.today()  # e.g., 2024-10-04
  ```
  
### `datetime`
- **Description:** Combines date and time but without time zone information.
- **Usage:**
  ```python
  from datetime import datetime

  now = datetime.now()  # e.g., 2024-10-04 14:30:00
  ```
  
### `timestamp`
- **Description:** The number of seconds since the Unix epoch (January 1, 1970). Represents a point in time in UTC.
- **Usage:**
  ```python
  import time

  timestamp = time.time()  # e.g., 1736118600.0 (seconds since epoch)
  ```

### Handling Timezones in Python:

The `pytz` library or the built-in `timezone` support in `datetime` is used to manage time zones.

- **Adding Timezones:**
  ```python
  from datetime import datetime
  import pytz

  # Without timezone
  naive_dt = datetime.now()  # 2024-10-04 14:30:00
  
  # Adding timezone
  ist = pytz.timezone('Asia/Kolkata')
  localized_dt = ist.localize(naive_dt)  # 2024-10-04 14:30:00+05:30
  ```

- **Converting Timezones:**
  ```python
  utc_dt = localized_dt.astimezone(pytz.utc)  # Convert to UTC
  ```

### Converting between `datetime` and `timestamp`:

- **To Timestamp:**
  ```python
  timestamp = datetime.now().timestamp()  # Convert datetime to timestamp
  ```

- **From Timestamp:**
  ```python
  dt_from_timestamp = datetime.fromtimestamp(timestamp)  # Convert timestamp to datetime
  ```

### Key Libraries:
- `pytz`: For timezone handling.
- `datetime`: For working with date and time in Python.
  
---

## **3. Java Handling of `date`, `datetime`, and `timestamp`:**

Java uses the `java.time` package introduced in Java 8 for working with dates, times, and timezones.

### `LocalDate`
- **Description:** Represents a date (year, month, and day) without time.
- **Usage:**
  ```java
  LocalDate date = LocalDate.now();  // e.g., 2024-10-04
  ```

### `LocalDateTime`
- **Description:** Represents date and time without timezone information.
- **Usage:**
  ```java
  LocalDateTime dateTime = LocalDateTime.now();  // e.g., 2024-10-04 14:30:00
  ```

### `Instant`
- **Description:** Represents a moment in time as an offset from the epoch, typically used for timestamps.
- **Usage:**
  ```java
  Instant timestamp = Instant.now();  // e.g., 2024-10-04T08:30:00Z (in UTC)
  ```

### `ZonedDateTime`
- **Description:** Represents date and time with timezone information.
- **Usage:**
  ```java
  ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));  // e.g., 2024-10-04T14:30:00+05:30
  ```

### Converting between `LocalDateTime`, `Instant`, and `ZonedDateTime`:

- **To Timestamp (`Instant`):**
  ```java
  Instant timestamp = dateTime.atZone(ZoneId.systemDefault()).toInstant();
  ```

- **From Timestamp (`Instant`):**
  ```java
  LocalDateTime dateTime = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
  ```

### Handling Timezones in Java:

Java has strong timezone support via `ZoneId` and `ZonedDateTime`.

- **Convert to UTC:**
  ```java
  ZonedDateTime utcDateTime = zonedDateTime.withZoneSameInstant(ZoneOffset.UTC);
  ```

- **Convert to Specific Timezone (e.g., IST):**
  ```java
  ZonedDateTime istDateTime = utcDateTime.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));
  ```

### Legacy Classes (Prior to Java 8):
- `Date`: Handles date and time but has many limitations and is considered deprecated in favor of the `java.time` package.
- `Calendar`: Provides more functionality than `Date` but is more complex and less intuitive than `java.time`.

### Key Points:
- **LocalDate**: For date only.
- **LocalDateTime**: For date and time without timezones.
- **ZonedDateTime**: For handling date, time, and time zones.
- LocalDateTime.now() does not take the zone from the system's default or any other zone context. Instead, LocalDateTime is specifically designed to represent date and time without any time zone information. When you call LocalDateTime.now(), it retrieves the current date and time from the system clock

---

## **Summary of Key Differences**

| Concept     | Database    | Python (`datetime`) | Java (`java.time`)            |
|-------------|-------------|---------------------|-------------------------------|
| `DATE`      | `DATE`      | `date`              | `LocalDate`                   |
| `DATETIME`  | `DATETIME`  | `datetime`          | `LocalDateTime`               |
| `TIMESTAMP` | `TIMESTAMP` | `timestamp`         | `Instant`, `ZonedDateTime`, `OffsetDateTime`  |
| Timezone Support | Limited to `TIMESTAMP` | Use `pytz` or `timezone` | Built-in with `ZonedDateTime` |


---

`Instant`, `OffsetDateTime`, and `ZonedDateTime` are all part of the `java.time` package introduced in Java 8 to handle date and time in a precise and flexible way. Here's a detailed breakdown of each class, their use cases, and how they relate to one another:

### **1. `Instant`**

- **What It Is:**
  - `Instant` represents a single point in time in UTC (Universal Coordinated Time), often referred to as a timestamp. It is the most basic time representation in Java and contains no time zone or offset information.
  - Internally, `Instant` stores the number of seconds (and nanoseconds) that have elapsed since the Unix epoch (1970-01-01T00:00:00Z).

- **Use Cases:**
  - You would use `Instant` when you want to represent a precise point in time that is globally consistent across time zones, especially when storing or transmitting timestamps (e.g., in logging systems, distributed systems).
  - For operations like calculating time differences or comparing two points in time, `Instant` is useful because it is always in UTC.

- **Example:**
  ```java
  Instant now = Instant.now();
  System.out.println("Current Instant: " + now);  // E.g., 2024-10-04T08:30:00Z
  ```

- **Key Properties:**
  - Timezone: UTC only.
  - Immutable and thread-safe.

---

### **2. `OffsetDateTime`**

- **What It Is:**
  - `OffsetDateTime` represents a date and time with an offset from UTC. The offset is typically expressed as `+02:00`, `-05:00`, etc.
  - It combines the date, time, and the offset, but **not the full time zone** (e.g., it doesn’t contain time zone rules like daylight saving time transitions).

- **Use Cases:**
  - You would use `OffsetDateTime` when you care about the exact offset from UTC but do not need full time zone support. It is particularly useful for APIs, protocols (like ISO 8601), and systems where you need to represent a point in time with its relative offset to UTC.
  - It’s commonly used when the offset is static or when you don’t need to account for daylight saving time.

- **Example:**
  ```java
  OffsetDateTime offsetDateTime = OffsetDateTime.now();
  System.out.println("Current OffsetDateTime: " + offsetDateTime);  // E.g., 2024-10-04T14:30:00+05:30
  ```

- **Key Properties:**
  - Timezone: Contains only a UTC offset, not a full time zone.
  - Immutable and thread-safe.

---

### **3. `ZonedDateTime`**

- **What It Is:**
  - `ZonedDateTime` represents a date and time with a complete time zone (including rules for daylight saving time and historical time zone changes).
  - It combines the local date and time, the zone offset (e.g., `+05:30`), and the time zone ID (e.g., `Asia/Kolkata`, `Europe/London`, `America/New_York`).

- **Use Cases:**
  - You would use `ZonedDateTime` when you need to work with date and time across different time zones and when it’s important to account for the rules that govern those time zones (like daylight saving time).
  - It’s ideal for scheduling applications, internationalization, and any scenarios where the time zone rules affect the local time (e.g., business meetings across different time zones).

- **Example:**
  ```java
  ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
  System.out.println("Current ZonedDateTime: " + zonedDateTime);  // E.g., 2024-10-04T14:30:00+05:30[Asia/Kolkata]
  ```

- **Key Properties:**
  - Timezone: Contains both a time zone and the UTC offset.
  - Immutable and thread-safe.

---

### **Comparison of `Instant`, `OffsetDateTime`, and `ZonedDateTime`:**

| Feature              | **Instant**                      | **OffsetDateTime**                 | **ZonedDateTime**                  |
|----------------------|----------------------------------|------------------------------------|------------------------------------|
| **Time Representation** | A point in time in UTC          | Date and time with UTC offset       | Date and time with full time zone  |
| **Time Zone Awareness** | UTC only                       | Fixed UTC offset (e.g., `+05:30`)  | Full time zone (e.g., `Asia/Kolkata`) |
| **Daylight Saving Time** | No support                    | No support                         | Supports DST and time zone rules   |
| **Use Case**          | Logging, storing timestamps     | APIs, protocols, fixed offsets     | Scheduling across time zones, international apps |
| **Example**           | `2024-10-04T08:30:00Z`          | `2024-10-04T14:30:00+05:30`        | `2024-10-04T14:30:00+05:30[Asia/Kolkata]` |

---

### **Conversions Between `Instant`, `OffsetDateTime`, and `ZonedDateTime`:**

You can easily convert between these classes, depending on your needs.

- **From `Instant` to `ZonedDateTime`:**
  ```java
  Instant instant = Instant.now();
  ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Asia/Kolkata"));
  ```

- **From `ZonedDateTime` to `OffsetDateTime`:**
  ```java
  ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
  OffsetDateTime offsetDateTime = zonedDateTime.toOffsetDateTime();
  ```

- **From `OffsetDateTime` to `Instant`:**
  ```java
  OffsetDateTime offsetDateTime = OffsetDateTime.now();
  Instant instant = offsetDateTime.toInstant();
  ```

### **When to Use Which:**

- **Use `Instant`** when you just need a timestamp that is globally consistent and represents an exact point in time (e.g., logging, database storage of timestamps, network protocols).
  
- **Use `OffsetDateTime`** when you need to represent a date and time with a fixed offset from UTC, but time zone rules (like daylight saving time) are not important (e.g., for API responses that specify an offset).

- **Use `ZonedDateTime`** when working with times that involve time zone rules, such as daylight saving time or historical changes (e.g., scheduling applications, global meetings, calendar systems).

### **Summary:**
- **`Instant`:** A single point in time in UTC, perfect for logging and timestamping.
- **`OffsetDateTime`:** A date-time with a fixed UTC offset, good for protocols and APIs.
- **`ZonedDateTime`:** A date-time with full time zone support, ideal for applications dealing with time zones and daylight saving changes.




### Solution: Handle Mixed Timestamps with and without Time Zones

You can use `DateTimeFormatter` to define multiple parsing formats, and then use a `try-catch` mechanism to handle both cases. Here's an example:

### Example Code: Handling Timestamps with and without Time Zones

```java
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    public static void main(String[] args) {
        // Example timestamps
        String[] timestamps = {
            "2024-10-04T14:30:00+05:30",  // With timezone
            "2024-10-04T14:30:00"          // Without timezone
        };

        for (String timestamp : timestamps) {
            LocalDateTime localDateTime = parseToLocalDateTime(timestamp);
            System.out.println("Parsed LocalDateTime: " + localDateTime);
        }
    }

    public static LocalDateTime parseToLocalDateTime(String timestamp) {
        try {
            // Attempt to parse with timezone (OffsetDateTime)
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(timestamp);
            return offsetDateTime.toLocalDateTime();  // Discard timezone
        } catch (DateTimeParseException e1) {
            try {
                // If no timezone is present, parse as LocalDateTime
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                return LocalDateTime.parse(timestamp, formatter);
            } catch (DateTimeParseException e2) {
                // Handle invalid format or error case
                throw new RuntimeException("Invalid timestamp format: " + timestamp);
            }
        }
    }
}
```

### Explanation:

1. **Define the Mixed Timestamps:**  
   You may encounter timestamps both with time zones and without time zones in your data. In this example, one timestamp includes the timezone (`"2024-10-04T14:30:00+05:30"`) and the other does not (`"2024-10-04T14:30:00"`).

2. **Use `try-catch` to Handle Both Cases:**  
   - First, attempt to parse the timestamp as an `OffsetDateTime`, which expects a time zone.
   - If the first attempt fails (i.e., the string doesn't include a time zone), it catches the `DateTimeParseException` and tries to parse it as a `LocalDateTime` using a formatter without timezone information.
   
3. **Extract `LocalDateTime`:**  
   For timestamps with a time zone, the method `toLocalDateTime()` is used to discard the time zone. For timestamps without a time zone, the parsed `LocalDateTime` is directly returned.

4. **Handle Errors:**  
   If neither parsing attempt succeeds, a runtime exception is thrown, indicating that the timestamp format is invalid.

### Example Output:

```
Parsed LocalDateTime: 2024-10-04T14:30
Parsed LocalDateTime: 2024-10-04T14:30
```

### Key Points:

- **Flexible Parsing:** The `try-catch` mechanism allows you to handle both types of timestamps without requiring separate logic for each one.
- **Discarding Time Zone:** When a timestamp includes a time zone, `OffsetDateTime` is used to parse it, and then the time zone is discarded by converting it to `LocalDateTime`.
- **Error Handling:** Proper error handling ensures that any malformed or unexpected formats are caught and managed gracefully.

