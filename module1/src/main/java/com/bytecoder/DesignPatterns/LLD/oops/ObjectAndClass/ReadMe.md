* what is class and object
* class feilds and method
* inner classes and java file
* object class in java

final methods , immutable, mutable class/methods



## composition , inheritance ,

## inner classes


In Java, all objects inherit methods from the **`java.lang.Object`** class, as every class in Java implicitly extends this class (unless it explicitly extends another class). Additionally, methods like `compareTo` are part of specific interfaces like `Comparable`.

### Methods in `java.lang.Object`
Here are the methods provided by the `Object` class:

1. **`equals(Object obj)`**
    - Compares the current object with another object for equality.
    - By default, checks for reference equality (`==`).
    - Override this method for content-based equality.

   ```java
   @Override
   public boolean equals(Object obj) {
       // Custom implementation
   }
   ```

2. **`hashCode()`**
    - Returns an integer hash code for the object.
    - Must be consistent with `equals()` (if two objects are equal, their hash codes should also be equal).

   ```java
   @Override
   public int hashCode() {
       // Custom implementation
   }
   ```

3. **`toString()`**
    - Returns a string representation of the object.
    - The default implementation returns the class name followed by the hash code.

   ```java
   @Override
   public String toString() {
       return "Custom String Representation";
   }
   ```

4. **`getClass()`**
    - Returns the runtime class of the object.
    - Useful for reflection.

   ```java
   Class<?> clazz = obj.getClass();
   ```

5. **`clone()`**
    - Creates and returns a copy of the object.
    - The class must implement the `Cloneable` interface and override `clone()`.

   ```java
   @Override
   protected Object clone() throws CloneNotSupportedException {
       return super.clone();
   }
   ```

7. **`notify()`**
    - Wakes up a single thread waiting on the object's monitor.

8. **`notifyAll()`**
    - Wakes up all threads waiting on the object's monitor.

9. **`wait()`**
    - Causes the current thread to wait until another thread invokes `notify()` or `notifyAll()`.

10. **`wait(long timeout)`**
    - Similar to `wait()` but with a timeout.

11. **`wait(long timeout, int nanos)`**
    - Similar to `wait(long timeout)` with added nanoseconds precision.
