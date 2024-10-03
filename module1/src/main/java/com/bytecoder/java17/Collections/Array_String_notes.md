## Array Methods

array is collection of similar type of elements. which has contiguous memory location.
we can store only a fix set of elements in the array. array has a variable to check length `array.length` 
### initialization 

`datatype[] array = new datatype[size]`\
`datatype[] array = new datatype[]{1,2,3}`\
`datatype[] array = {1,2,3}`


The `Arrays` class in Java is a utility class in the `java.util` package that provides static methods to manipulate arrays. This class includes methods for sorting, searching, comparing, filling, and converting arrays to strings or lists. It is specifically designed to facilitate working with arrays in an efficient and convenient way.

### Key Methods of the `Arrays` Class:

1. **Sorting Methods:**

   The `Arrays` class provides several overloaded `sort()` methods for sorting arrays. These methods are implemented using the **dual-pivot quicksort** algorithm for primitive arrays and **TimSort** for object arrays.

   - **`void sort(int[] a)`**  
     Sorts the specified array of `int` in ascending order.

     ```java
     int[] arr = {3, 5, 1, 4};
     Arrays.sort(arr);
     ```

   - **`void sort(T[] a)`**  
     Sorts the specified array of objects in ascending order based on the natural ordering of its elements.

     ```java
     String[] arr = {"apple", "banana", "cherry"};
     Arrays.sort(arr);
     ```

   - **`void sort(T[] a, Comparator<? super T> c)`**  
     Sorts the specified array of objects according to the order induced by the specified comparator.

     ```java
     Arrays.sort(arr, Comparator.reverseOrder());
     ```

2. **Searching Methods:**

   The `Arrays` class provides binary search methods for searching sorted arrays. These methods return the index of the searched element or a negative number if the element is not found.

   - **`int binarySearch(int[] a, int key)`**  
     Searches the specified array of `int` for the specified value using binary search.

     ```java
     int index = Arrays.binarySearch(arr, 4);
     ```

   - **`int binarySearch(T[] a, T key)`**  
     Searches the specified array of objects for the specified value using binary search, based on the natural ordering of its elements.

     ```java
     int index = Arrays.binarySearch(arr, "banana");
     ```

   - **`int binarySearch(T[] a, T key, Comparator<? super T> c)`**  
     Searches the specified array of objects for the specified value using binary search, using the provided comparator.

3. **Filling Methods:**

   These methods fill all or part of an array with a specified value.

   - **`void fill(int[] a, int val)`**  
     Fills the entire array with the specified value.

     ```java
     Arrays.fill(arr, 0);  // Fills the array with 0
     ```

   - **`void fill(int[] a, int fromIndex, int toIndex, int val)`**  
     Fills a range of the array with the specified value.

     ```java
     Arrays.fill(arr, 1, 3, 9);  // Fills index 1 to 2 with 9
     ```

4. **Comparison Methods:**

   These methods compare arrays lexicographically (element by element), checking whether two arrays are equal.

   - **`boolean equals(int[] a, int[] a2)`**  
     Returns `true` if the two arrays are equal (i.e., contain the same elements in the same order).

     ```java
     boolean isEqual = Arrays.equals(arr1, arr2);
     ```

   - **`int compare(T[] a, T[] b)`**  
     Compares two arrays lexicographically, returning a negative integer, zero, or a positive integer if the first array is less than, equal to, or greater than the second.

5. **Copying Methods:**

   These methods create new arrays that are copies of the specified arrays.

   - **`T[] copyOf(T[] original, int newLength)`**  
     Copies the specified array to a new array with the specified length.

     ```java
     int[] copyArr = Arrays.copyOf(arr, 5);  // Extends the array to length 5
     ```

   - **`T[] copyOfRange(T[] original, int from, int to)`**  
     Copies a range of the original array to a new array.

6. **Conversion Methods:**

   These methods convert arrays to other data structures, such as lists or strings.

   - **`List<T> asList(T... a)`**  
     Converts the specified array into a fixed-size list backed by the original array. Changes to the list are reflected in the original array.

     ```java
     List<String> list = Arrays.asList(arr);
     ```

   - **`String toString(T[] a)`**  
     Returns a string representation of the array, with elements enclosed in square brackets.

     ```java
     String arrStr = Arrays.toString(arr);
     ```


## String Methods

Java's `String` class provides a wide range of methods to manipulate and interact with strings. Here’s an in-depth explanation of all important `String` methods:

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

### String Interning:

36. **`String intern()`**
    - Returns a canonical representation of the string. If the string already exists in the string pool, the pool reference is returned.
    ```java
    String interned = s.intern();
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

The `String` class in Java provides a rich set of methods for various operations, including:

- **Character access**: `charAt()`, `substring()`
- **Searching**: `indexOf()`, `lastIndexOf()`, `contains()`
- **Comparison**: `equals()`, `compareTo()`, `equalsIgnore