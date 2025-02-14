
## String Methods

Java's `String` class provides a wide range of methods to manipulate and interact with strings. Here’s an in-depth explanation of all important `String` methods:

```java
    
    String name = "Alice";
    int age = 25;
    String message = String.format("Hello, my name is %s and I am %d years old.", name, age);
    System.out.println(message);

```

### String Creation Methods:

1. **`String()`**
   - Creates an empty string.
   ```java
   String s = new String();
   ```

2. **`String(char[] value)`**
   - Constructs a new string by decoding the character array.
   ```java
   char[] chars = {'J', 'a', 'v', 'a'};
   String s = new String(chars);
   ```

3. **`String(String original)`**
   - Creates a new string that is a copy of the original.
   ```java
   String s = new String("Hello");
   ```

### String Length:

4. **`int length()`**
   - Returns the number of characters in the string.
   ```java
   int len = s.length();
   ```

### Character Access and Searching:

5. **`char charAt(int index)`**
   - Returns the character at the specified index.
   ```java
   char c = s.charAt(1);
   ```

6. **`int indexOf(int ch)`**
   - Returns the index of the first occurrence of the specified character, or `-1` if the character is not found.
   ```java
   int idx = s.indexOf('l');
   ```

7. **`int indexOf(String str)`**
   - Returns the index of the first occurrence of the specified substring.
   ```java
   int idx = s.indexOf("lo");
   ```

8. **`int lastIndexOf(int ch)`**
   - Returns the index of the last occurrence of the specified character.
   ```java
   int idx = s.lastIndexOf('l');
   ```

9. **`int lastIndexOf(String str)`**
   - Returns the index of the last occurrence of the specified substring.
   ```java
   int idx = s.lastIndexOf("lo");
   ```

### Substrings:

10. **`String substring(int beginIndex)`**
    - Returns a substring starting from the specified index.
    ```java
    String sub = s.substring(2);
    ```

11. **`String substring(int beginIndex, int endIndex)`**
    - Returns a substring from the specified begin index to the end index (exclusive).
    ```java
    String sub = s.substring(1, 3);
    ```

### Case Conversion:

12. **`String toLowerCase()`**
    - Converts all characters to lowercase.
    ```java
    String lower = s.toLowerCase();
    ```

13. **`String toUpperCase()`**
    - Converts all characters to uppercase.
    ```java
    String upper = s.toUpperCase();
    ```

### Trimming:

14. **`String trim()`**
    - Removes leading and trailing whitespace.
    ```java
    String trimmed = s.trim();
    ```

### Equality and Comparison:

15. **`boolean equals(Object obj)`**
    - Compares the string to the specified object for equality.
    ```java
    boolean isEqual = s.equals("Hello");
    ```

16. **`boolean equalsIgnoreCase(String anotherString)`**
    - Compares the string to another string, ignoring case differences.
    ```java
    boolean isEqual = s.equalsIgnoreCase("hello");
    ```

17. **`int compareTo(String anotherString)`**
    - Compares two strings lexicographically.
    ```java
    int result = s.compareTo("Hello");
    ```

18. **`int compareToIgnoreCase(String str)`**
    - Compares two strings lexicographically, ignoring case.
    ```java
    int result = s.compareToIgnoreCase("hello");
    ```

### Concatenation:

19. **`String concat(String str)`**
    - Concatenates the specified string to the end of this string.
    ```java
    String concatenated = s.concat(" World");
    ```

### Replacing and Removing Characters:

20. **`String replace(char oldChar, char newChar)`**
    - Replaces all occurrences of the old character with the new character.
    ```java
    String replaced = s.replace('l', 'x');
    ```

21. **`String replace(CharSequence target, CharSequence replacement)`**
    - Replaces each occurrence of the target sequence with the replacement sequence.
    ```java
    String replaced = s.replace("lo", "p");
    ```

22. **`String replaceAll(String regex, String replacement)`**
    - Replaces each substring that matches the regular expression with the replacement.
    ```java
    String replaced = s.replaceAll("l.", "x");
    ```

23. **`String replaceFirst(String regex, String replacement)`**
    - Replaces the first substring that matches the regular expression with the replacement.
    ```java
    String replaced = s.replaceFirst("l.", "x");
    ```

### Splitting Strings:

24. **`String[] split(String regex)`**
    - Splits the string into an array of strings based on the regular expression.
    ```java
    String[] parts = s.split(" ");
    ```

25. **`String[] split(String regex, int limit)`**
    - Splits the string into an array of strings based on the regular expression, with a limit on the number of splits.
    ```java
    String[] parts = s.split(" ", 2);
    ```

### Conversion:

26. **`String valueOf(int i)`**
    - Returns the string representation of the specified integer.
    ```java
    String str = String.valueOf(42);
    ```

27. **`String valueOf(char c)`**
    - Returns the string representation of the specified character.
    ```java
    String str = String.valueOf('c');
    ```

28. **`String valueOf(Object obj)`**
    - Returns the string representation of the specified object.
    ```java
    String str = String.valueOf(new Object());
    ```

### Character Check:

29. **`boolean contains(CharSequence s)`**
    - Checks if the string contains the specified sequence of characters.
    ```java
    boolean hasSubstr = s.contains("ell");
    ```

30. **`boolean startsWith(String prefix)`**
    - Checks if the string starts with the specified prefix.
    ```java
    boolean starts = s.startsWith("He");
    ```

31. **`boolean startsWith(String prefix, int toffset)`**
    - Checks if the string starts with the specified prefix, beginning at the specified index.
    ```java
    boolean starts = s.startsWith("ll", 2);
    ```

32. **`boolean endsWith(String suffix)`**
    - Checks if the string ends with the specified suffix.
    ```java
    boolean ends = s.endsWith("lo");
    ```

### Joining Strings:

33. **`static String join(CharSequence delimiter, CharSequence... elements)`**
    - Joins the specified elements with the specified delimiter.
    ```java
    String joined = String.join("-", "Java", "is", "fun");

    
    String[] words = {"apple", "banana", "cherry"};
    String joined = String.join("-", words);
    ```

### Formatting:

34. **`static String format(String format, Object... args)`**
    - Returns a formatted string using the specified format string and arguments.
    ```java
    String formatted = String.format("Hello, %s!", "World");
    ```

35. **`static String format(Locale l, String format, Object... args)`**
    - Returns a formatted string using the specified format string, arguments, and locale.
    ```java
    String formatted = String.format(Locale.US, "Price: $%.2f", 10.5);
    ```


### Conversion to Char Arrays or Byte Arrays:

37. **`char[] toCharArray()`**
    - Converts the string to a character array.
    ```java
    char[] chars = s.toCharArray();
    ```

38. **`byte[] getBytes()`**
    - Encodes the string into a sequence of bytes using the platform's default charset.
    ```java
    byte[] bytes = s.getBytes();
    ```

39. **`byte[] getBytes(Charset charset)`**
    - Encodes the string into a sequence of bytes using the specified charset.
    ```java
    byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
    ```

### String Comparisons (with Region Support):

40. **`boolean regionMatches(int toffset, String other, int ooffset, int len)`**
    - Compares a region of the string to a region of another string.
    ```java
    boolean match = s.regionMatches(0, "Hel", 0, 3);
    ```

41. **`boolean regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len)`**
    - Compares a region of the string to a region of another string, optionally ignoring case.
    ```java
    boolean match = s.regionMatches(true, 0, "hel", 0, 3);
    ```

### Summary
Note : **String is immutable whereas StringBuffer and StringBuilder are mutable classes. StringBuffer is thread-safe and synchronized whereas StringBuilder is not**

The `String` class in Java provides a rich set of methods for various operations, including:

- **Character access**: `charAt()`, `substring()`
- **Searching**: `indexOf()`, `lastIndexOf()`, `contains()`
- **Comparison**: `equals()`, `compareTo()`, `equalsIgnore




### Extra Methods in `StringBuilder` (Not in `String`):

1. **`append()`**  
   Appends the specified string or value to the current `StringBuilder` instance. This method is overloaded to accept various types such as strings, characters, integers, floats, booleans, and more.

   ```java
   StringBuilder sb = new StringBuilder("Hello");
   sb.append(" World");
   sb.append(123);
   System.out.println(sb);  // Output: "Hello World123"
   ```

2. **`insert()`**  
   Inserts the specified string, character, or value at the specified index in the current `StringBuilder`. Like `append()`, it is overloaded to accept various types.

   ```java
   StringBuilder sb = new StringBuilder("Hello");
   sb.insert(5, " World");
   System.out.println(sb);  // Output: "Hello World"
   ```

3. **`delete()`**  
   Removes the characters in a substring of the `StringBuilder`. The substring starts at the specified `start` index and extends to the character at index `end - 1`.

   ```java
   StringBuilder sb = new StringBuilder("Hello World");
   sb.delete(5, 11);
   System.out.println(sb);  // Output: "Hello"
   ```

4. **`deleteCharAt()`**  
   Removes the character at the specified index from the `StringBuilder`.

   ```java
   StringBuilder sb = new StringBuilder("Hello");
   sb.deleteCharAt(1);
   System.out.println(sb);  // Output: "Hllo"
   ```

5. **`replace()`**  
   Replaces the characters in a substring of the `StringBuilder` with characters from the specified `String`. The substring begins at `start` and extends to `end - 1`.

   ```java
   StringBuilder sb = new StringBuilder("Hello World");
   sb.replace(6, 11, "Java");
   System.out.println(sb);  // Output: "Hello Java"
   ```

6. **`reverse()`**  important while reverse the string 
   Reverses the sequence of characters in the `StringBuilder`.

   ```java
   StringBuilder sb = new StringBuilder("Hello");
   sb.reverse();
   System.out.println(sb);  // Output: "olleH"
   String s = "test";
   return s.equals(new StringBuilder(s).reverse().toString()); 
   ```

7. **`setCharAt()`**  
   Sets the character at the specified index to a new character.

   ```java
   StringBuilder sb = new StringBuilder("Hello");
   sb.setCharAt(1, 'a');
   System.out.println(sb);  // Output: "Hallo"
   ```

8. **`setLength()`**  
   Sets the length of the `StringBuilder`. If the new length is less than the current length, the string is truncated. If the new length is greater than the current length, null characters (`\u0000`) are appended.

   ```java
   StringBuilder sb = new StringBuilder("Hello");
   sb.setLength(3);
   System.out.println(sb);  // Output: "Hel"
   ```


12. **`substring()`**  
    Extracts a substring from the `StringBuilder`. Unlike the `substring()` method in `String`, this method does not modify the `StringBuilder`.

    ```java
    StringBuilder sb = new StringBuilder("Hello World");
    String sub = sb.substring(6);  // Extracts a substring
    System.out.println(sub);  // Output: "World"
    ```

### Summary of Key Differences:

- **Mutability**: `StringBuilder` is mutable, meaning it can be changed after creation, while `String` is immutable.
- **Modification Methods**: `StringBuilder` provides methods like `append()`, `insert()`, `delete()`, `replace()`, `setCharAt()`, etc., which allow direct modification of the character sequence.
- **Efficiency**: `StringBuilder` is more efficient than `String` for scenarios involving frequent string manipulation (e.g., concatenation in loops), as it avoids the overhead of creating new `String` objects with every modification.

These methods make `StringBuilder` the preferred choice for scenarios where you need to build or modify strings frequently, especially in performance-critical applications.



### `String` Methods Not Available in `StringBuilder`:

1. **`equals()`**
   - `String` uses content-based equality to compare two strings.
   - `StringBuilder` uses the default `Object.equals()` method (reference comparison), which compares memory addresses rather than content.

2. **`equalsIgnoreCase(String anotherString)`**
   - Compares two strings lexicographically, ignoring case differences.
   - This method is not available in `StringBuilder`.

3. **`compareTo(String anotherString)`**
   - Lexicographically compares two strings.
   - `StringBuilder` does not have `compareTo()`.

4. **`compareToIgnoreCase(String str)`**
   - Lexicographically compares two strings, ignoring case differences.
   - Not available in `StringBuilder`.

5. **`matches(String regex)`**
   - Tests if the string matches the given regular expression.
   - `StringBuilder` does not support pattern matching.

6. **`split(String regex)`**
   - Splits the string into an array of substrings based on the specified regular expression.
   - `StringBuilder` does not have a `split()` method.

7. **`startsWith(String prefix)`**
   - Tests whether the string starts with the specified prefix.
   - This method is not available in `StringBuilder`.

8. **`endsWith(String suffix)`**
   - Tests whether the string ends with the specified suffix.
   - `StringBuilder` does not support this method.

9. **`contains(CharSequence s)`**
   - Checks if the string contains the specified character sequence.
   - Not available in `StringBuilder`.

10. **`contentEquals(CharSequence cs)`**
    - Compares the string to the specified character sequence.
    - `StringBuilder` does not have this method.

11. **`toLowerCase()` and `toUpperCase()`**
    - Converts all characters in the string to lowercase or uppercase.
    - `StringBuilder` lacks these methods, though you can manually implement them using mutable operations.

12. **`trim()`**
    - Removes leading and trailing whitespace from the string.
    - `StringBuilder` does not have a `trim()` method, though you could implement this behavior manually by deleting characters.

13. **`intern()`**
    - Returns a canonical representation of the string from the string pool.
    - Not present in `StringBuilder`.

14. **`hashCode()`**
    - `String` computes a hash code based on the content of the string.
    - `StringBuilder` inherits the default `Object.hashCode()`, which is based on memory address, not content.

15. **`toCharArray()`**
    - Converts the string to a character array.
    - This method is not available in `StringBuilder`.


----

For `int[]` arrays (primitive type arrays) in Java, the conversion process differs slightly since `Arrays.asList()` and `List.of()` only work directly with objects like `Integer[]` but not `int[]`. Here’s how you can handle conversions for `int[]` to `List<Integer>` and vice versa:

### 1. `int[]` Array to `List<Integer>`
To convert a primitive `int[]` array to a `List<Integer>`, you need to use a loop or Java Streams (Java 8+):

#### Using Java Streams (Java 8+)
```java
int[] intArray = {1, 2, 3, 4, 5};

// Convert int[] to List<Integer>
List<Integer> list = Arrays.stream(intArray)
                           .boxed()
                           .collect(Collectors.toList());

System.out.println("List: " + list);
```

### 2. `List<Integer>` to `int[]` Array
To convert a `List<Integer>` to an `int[]` array, you can use a loop or Java Streams:

#### Using Java Streams (Java 8+)
```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

// Convert List<Integer> to int[]
int[] intArray = list.stream()
                     .mapToInt(Integer::intValue)
                     .toArray();

System.out.println("Array: " + Arrays.toString(intArray));
```





