
# **Java Streams - A Comprehensive Guide**

Java Streams were introduced in **Java 8** as part of the `java.util.stream` package. Streams allow developers to process collections of data in a functional programming style, offering operations like filtering, mapping, reducing, and more.

---

## **What is a Stream?**
- **Definition**: A Stream is a sequence of elements that supports various operations to process data in a declarative manner.
- **Key Characteristics**:
    - **Not a Data Structure**: Streams do not store data. They process data on-demand.
    - **Functional**: Uses functional programming techniques for processing.
    - **Laziness**: Operations are not executed until a terminal operation is invoked.
    - **Immutability**: Stream elements are not modified; instead, transformations are applied to produce new data.
    - **Once Use**: A Stream can only be traversed once.

---

## **Stream Operations**
Stream operations are divided into two categories:

### **1. Intermediate Operations**
- **Definition**: Transform a Stream into another Stream. These are lazy, meaning they don’t execute until a terminal operation is called.
- **Common Intermediate Operations**:
    - **filter(Predicate)**  
      Filters elements based on a condition.
      ```java
      List<Integer> evenNumbers = numbers.stream()
          .filter(n -> n % 2 == 0)
          .collect(Collectors.toList());
      ```

    - **map(Function)**  
      Transforms each element using a function.
      ```java
      List<String> upperCaseNames = names.stream()
          .map(String::toUpperCase)
          .collect(Collectors.toList());
      ```

    - **flatMap(Function)**  
      Flattens nested structures into a single Stream.
      ```java
      List<String> words = sentences.stream()
          .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
          .collect(Collectors.toList());
      ```

    - **distinct()**  
      Removes duplicate elements.
      ```java
      List<Integer> uniqueNumbers = numbers.stream()
          .distinct()
          .collect(Collectors.toList());
      ```

    - **sorted()**  
      Sorts elements in natural order or using a comparator.
      ```java
      List<Integer> sortedNumbers = numbers.stream()
          .sorted()
          .collect(Collectors.toList());
      ```

    - **limit(n)**  
      Limits the Stream to the first `n` elements.
      ```java
      List<Integer> firstThree = numbers.stream()
          .limit(3)
          .collect(Collectors.toList());
      ```

    - **skip(n)**  
      Skips the first `n` elements.
      ```java
      List<Integer> remaining = numbers.stream()
          .skip(3)
          .collect(Collectors.toList());
      ```

---

### **2. Terminal Operations**
- **Definition**: Execute and close the Stream pipeline. After a terminal operation, the Stream cannot be reused.
- **Common Terminal Operations**:
    - **collect(Collector)**  
      Collects elements into a collection or another form.
      ```java
      List<Integer> list = numbers.stream().collect(Collectors.toList());
      Set<Integer> set = numbers.stream().collect(Collectors.toSet());
      ```

    - **forEach(Consumer)**  
      Performs an action for each element.
      ```java
      numbers.stream().forEach(System.out::println);
      ```

    - **reduce(BinaryOperator)**  
      Combines elements into a single value.
      ```java
      int sum = numbers.stream()
          .reduce(0, Integer::sum);
      ```

    - **count()**  
      Counts the number of elements.
      ```java
      long count = numbers.stream().count();
      ```

    - **findFirst()**  
      Returns the first element as an `Optional`.
      ```java
      Optional<Integer> first = numbers.stream().findFirst();
      ```

    - **findAny()**  
      Returns any element as an `Optional`.
      ```java
      Optional<Integer> any = numbers.stream().findAny();
      ```

    - **allMatch(Predicate)**  
      Checks if all elements match a condition.
      ```java
      boolean allEven = numbers.stream().allMatch(n -> n % 2 == 0);
      ```

    - **anyMatch(Predicate)**  
      Checks if any element matches a condition.
      ```java
      boolean hasEven = numbers.stream().anyMatch(n -> n % 2 == 0);
      ```

    - **noneMatch(Predicate)**  
      Checks if no elements match a condition.
      ```java
      boolean noneNegative = numbers.stream().noneMatch(n -> n < 0);
      ```

---

## **Stream Creation**
Streams can be created from various sources:

### **1. Collections**
```java
List<Integer> list = Arrays.asList(1, 2, 3);
Stream<Integer> stream = list.stream();
```

### **2. Arrays**
```java
Stream<String> stream = Arrays.stream(new String[]{"A", "B", "C"});
```

### **3. Stream.of()**
```java
Stream<String> stream = Stream.of("A", "B", "C");
```

### **4. Generate/Iterate**
```java
Stream<Integer> infiniteStream = Stream.iterate(1, n -> n + 1).limit(10);
Stream<Double> randomNumbers = Stream.generate(Math::random).limit(5);
```

### **5. Files (I/O)**
```java
Stream<String> lines = Files.lines(Paths.get("file.txt"));
```

---

## **Parallel Streams**
- **Purpose**: Allows parallel processing to utilize multiple CPU cores.
- **Creation**:
  ```java
  List<Integer> list = Arrays.asList(1, 2, 3, 4);
  list.parallelStream().forEach(System.out::println);
  ```
- **Caution**:
    - Use parallel streams only when processing large data sets.
    - Be aware of thread-safety and shared mutable state.


# 📜 **Essential `Collectors` Static Methods in Java Streams**

The `java.util.stream.Collectors` class provides powerful utilities to collect and transform data from streams. Below is a well-structured guide to the **most important static methods** you must know.

---

#### **1. toList()**
- **Purpose**: Collects stream elements into a `List`.
- **Example**:
  ```java
  List<Integer> list = Stream.of(1, 2, 3, 4).collect(Collectors.toList());
  ```

---

#### **2. toSet()**
- **Purpose**: Collects stream elements into a `Set` (removes duplicates).
- **Example**:
  ```java
  Set<Integer> set = Stream.of(1, 2, 2, 3).collect(Collectors.toSet());
  ```

---

#### **3. toMap()**
- **Purpose**: Collects elements into a `Map`.
- **Syntax**:
  ```java
  toMap(keyMapper, valueMapper)
  ```  
- **Example**:
  ```java
  Map<Integer, String> map = Stream.of(1, 2, 3)
      .collect(Collectors.toMap(i -> i, i -> "Value" + i));
  ```
- **Handle Key Collisions**:
  ```java
  Map<Integer, String> map = Stream.of(1, 2, 2, 3)
      .collect(Collectors.toMap(i -> i, i -> "Value" + i, (v1, v2) -> v1));
  ```

---

#### **4. joining()**
- **Purpose**: Concatenates stream elements into a single `String`.
- **Examples**:
    - **Simple Join**:
      ```java
      String result = Stream.of("A", "B", "C").collect(Collectors.joining());
      ```
    - **With Delimiter**:
      ```java
      String result = Stream.of("A", "B", "C").collect(Collectors.joining(", "));
      ```
    - **With Prefix & Suffix**:
      ```java
      String result = Stream.of("A", "B", "C").collect(Collectors.joining(", ", "[", "]"));
      ```

---

#### **5. groupingBy()**
- **Purpose**: Groups stream elements by a classifier function.
- **Examples**:
    - **Basic Grouping**:
      ```java
      Map<Integer, List<String>> grouped = Stream.of("one", "two", "three", "four")
          .collect(Collectors.groupingBy(String::length));
      ```
    - **Grouping with Downstream Collector**:
      ```java
      Map<Integer, Set<String>> grouped = Stream.of("one", "two", "three", "four")
          .collect(Collectors.groupingBy(String::length, Collectors.toSet()));
      ```

---

#### **6. partitioningBy()**
- **Purpose**: Splits elements into two groups based on a predicate.
- **Example**:
  ```java
  Map<Boolean, List<Integer>> partitioned = Stream.of(1, 2, 3, 4, 5)
      .collect(Collectors.partitioningBy(i -> i % 2 == 0));
  ```

---

#### **7. counting()**
- **Purpose**: Counts the number of elements in the stream.
- **Example**:
  ```java
  long count = Stream.of(1, 2, 3, 4).collect(Collectors.counting());
  ```

---

#### **8. summarizingInt(), summarizingDouble(), summarizingLong()**
- **Purpose**: Collects statistics such as count, sum, min, max, and average.
- **Example**:
  ```java
  IntSummaryStatistics stats = Stream.of(1, 2, 3, 4)
      .collect(Collectors.summarizingInt(Integer::intValue));
  System.out.println(stats.getSum());    // 10
  System.out.println(stats.getAverage()); // 2.5
  ```

---

#### **9. reducing()**
- **Purpose**: Reduces stream elements into a single value.
- **Examples**:
    - **Without Initial Value**:
      ```java
      Optional<Integer> sum = Stream.of(1, 2, 3, 4)
          .collect(Collectors.reducing(Integer::sum));
      ```
    - **With Initial Value**:
      ```java
      int sum = Stream.of(1, 2, 3, 4)
          .collect(Collectors.reducing(0, Integer::sum));
      ```

---

#### **10. mapping()**
- **Purpose**: Applies a mapping function before collecting results.
- **Example**:
  ```java
  List<String> uppercase = Stream.of("one", "two", "three")
      .collect(Collectors.mapping(String::toUpperCase, Collectors.toList()));
  ```

---

#### **11. flatMapping()**
- **Purpose**: Applies a flat-mapping function before collecting results.
- **Example**:
  ```java
  List<String> flattened = Stream.of(List.of("a", "b"), List.of("c", "d"))
      .collect(Collectors.flatMapping(List::stream, Collectors.toList()));
  ```

---

#### **12. minBy() and maxBy()**
- **Purpose**: Finds the minimum or maximum element using a comparator.
- **Examples**:
  ```java
  Optional<Integer> min = Stream.of(1, 2, 3, 4).collect(Collectors.minBy(Integer::compareTo));
  Optional<Integer> max = Stream.of(1, 2, 3, 4).collect(Collectors.maxBy(Integer::compareTo));
  ```

---

#### **13. collectingAndThen()**
- **Purpose**: Adapts a collector with a finishing transformation.
- **Example**:
  ```java
  List<Integer> unmodifiableList = Stream.of(1, 2, 3, 4)
      .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
  ```

---

#### **14. filtering()**
- **Purpose**: Filters elements before collecting.
- **Example**:
  ```java
  List<Integer> filtered = Stream.of(1, 2, 3, 4, 5)
      .collect(Collectors.filtering(i -> i % 2 == 0, Collectors.toList()));
  ```

---

#### **15. teeing()** *(Java 12+)*
- **Purpose**: Combines two collectors and merges their results.
- **Example**:
  ```java
  double average = Stream.of(1, 2, 3, 4)
      .collect(Collectors.teeing(
          Collectors.summingDouble(Double::valueOf),
          Collectors.counting(),
          (sum, count) -> sum / count
      ));
  ```

---

### 🌟 **Why These Methods Matter**
- **Efficiency**: Simplifies complex data processing.
- **Flexibility**: Supports nested operations for advanced transformations.
- **Readability**: Makes code concise and expressive.

Master these methods to take full advantage of Java Streams and elevate your coding skills! 🚀