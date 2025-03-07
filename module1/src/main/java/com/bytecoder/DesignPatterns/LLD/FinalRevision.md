
### Can an interface extend multiple interfaces? What happens if there are conflicting default methods?

* interfaces can do multiple inheritance but not class. class can have multiple implementation 
* class does not support multi-inheritance but interfaces does 
* default methods are instance level can be @Override 
* on conflict
```java
public interface CombinedInterface extends InterfaceA, InterfaceB {
// We must provide a new default implementation or explicitly choose one:
@Override
default void myMethod() {
// Option 1: Create an entirely new implementation
System.out.println("CombinedInterface default implementation");

        // Option 2: Delegate to one of the parent interfaces
        InterfaceA.super.myMethod();  // or InterfaceB.super.myMethod();
    }
}

```

Java’s rules for default methods keep interface inheritance safer and clearer:

Interfaces can offer default implementations.
If there’s a conflict, someone (the interface or the implementing class) must override it and pick a single resolution.


### What is the diamond problem in Java? How does Java resolve it? 
* The “diamond problem” refers to a situation where a class (or type) can inherit the same method (or member) from multiple paths in an inheritance hierarchy, causing ambiguity about which implementation should be chosen.
* In Java, classes do not have this problem because Java does not allow multiple inheritance of classes. However, from Java 8 onward, interfaces can have default methods, and a similar issue can arise when an interface extends multiple parent interfaces that define the same default method.


### every class is sub-class of Object in java explain 

```java
class Employee {
    String name;
    
    Employee(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return name.equals(employee.name);
    }

//    Used in hash-based collections like HashMap, HashSet, HashTable.
//    Default: Derived from memory address.
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
//    wait(), notify(), and notifyAll()
}
```



### synchronised creates intrinsic lock  for that class 

### How does Java handle object creation and garbage collection in OOP? in detail and steps how code execution take place and other components for JVM
* Java Stack: Each thread has its own stack, storing frames for each method call (local variables, partial results, references to objects on the heap)






