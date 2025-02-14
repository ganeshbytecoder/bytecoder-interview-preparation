# Comparator vs Comparable in Java

## Introduction
In Java, `Comparator` and `Comparable` are two interfaces used for comparing objects. They define methods for sorting and ordering objects but are used in different scenarios.

## `Comparable` Interface
The `Comparable` interface defines the **natural ordering** of objects. A class that implements `Comparable` can be sorted using standard sorting methods such as `Collections.sort()` or `Arrays.sort()` without an external comparator.

### Key Points:
- Defines a **single natural ordering**.
- Modifies the class itself by implementing `Comparable` and overriding `compareTo()`.
- Can be used directly with Java sorting utilities.

### Method:
```java
int compareTo(T o)
```
- Returns a **negative integer** if `this` is less than `o`.
- Returns **zero** if they are equal.
- Returns a **positive integer** if `this` is greater than `o`.

### Example:
```java
public class Employee implements Comparable<Employee> {
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "'}";
    }
}
```
```java
Collections.sort(employeeList);
```

## `Comparator` Interface
The `Comparator` interface defines **custom comparison logic** externally, allowing multiple sorting strategies.

### Key Points:
- Allows **multiple sorting criteria**.
- Does **not** modify the class.
- Used when natural ordering is not suitable or multiple sorting orders are needed.

### Method:
```java
int compare(T o1, T o2)
```
- Returns a **negative integer** if `o1` is less than `o2`.
- Returns **zero** if they are equal.
- Returns a **positive integer** if `o1` is greater than `o2`.

### Example:
```java
// Custom comparator to sort by name
Comparator<Employee> nameComparator = (e1, e2) -> e1.name.compareTo(e2.name).reverseOrder();

Comparator<Employee> nameComparator = Comparator.comparing(e -> e.name).naturalOrder();

Comparator<Employee> nameComparator = new Comparator<Employee>() {
    @Override
    public int compare(Employee e1, Employee e2) {
        return e1.name.compareTo(e2.name);
    }
};
// Comparator.naturalOrder();
// Comparator.reverseOrder();

Comparator<Employee> nameComparator = Comparator.comparing(e -> e.name);
Collections.sort(employeeList, nameComparator);

```

## When to Use `Comparable` vs `Comparator`
| Feature | Comparable | Comparator |
|---------|------------|------------|
| Sorting Logic | Natural ordering inside the class | External sorting logic |
| Flexibility | Single ordering only | Multiple sorting strategies |
| Implementation | Implements `compareTo(T o)` inside the class | Implements `compare(T o1, T o2)` in an external class or lambda |
| Usage | `Collections.sort(list)` | `Collections.sort(list, comparator)` |

## Sorting with Multiple Comparators
### Chaining Comparators using `thenComparing()`
```java
Comparator<Employee> comparator = Comparator.comparing(Employee::getName)
                                            .thenComparing(Employee::getAge);
Collections.sort(employeeList, comparator);
```

### Reverse Order Sorting:
```java
employees.sort(Comparator.comparing(Employee::getName)
                         .thenComparing(Employee::getAge)
                         .reversed());
```

## Summary
- **Use `Comparable`** when a **single natural order** is required.
- **Use `Comparator`** when multiple or custom sorting strategies are needed.
- `Comparator.thenComparing()` allows **chaining multiple sorting conditions**.

Mastering `Comparator` and `Comparable` will help in efficiently sorting objects in Java collections.

