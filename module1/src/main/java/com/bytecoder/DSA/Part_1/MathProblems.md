```java
package com.bytecoder.DSA.Part_1;

import java.util.HashSet;

public class MathProblems {

    //    Write an algorithm to determine if a number is happy or not

    //  method return true if n is Happy Number
    private static int numSquareSum(int n)
    {
        int num = 0;
        while (n != 0) {
            int digit = n % 10;
            num += digit * digit;
            n /= 10;
        }
        return num;
    }

    static boolean isHappyNumber(int n) {
        HashSet<Integer> st = new HashSet<>();
        while (true) {
            n = numSquareSum(n);
            if (n == 1)
                return true;
            if (st.contains(n))
                return false;
            st.add(n);
        }
    }




}
```
```python

class Solution:

    def sqrt_newton(self, num, precision=0.9):
        if num < 0:
            return None  # Square root of negative numbers is not real
        if num == 0 or num == 1:
            return num

        guess = num / 2.0  # Initial guess
        while abs(guess * guess - num) > precision:
            guess = (guess + num / guess) / 2.0  # Refine the guess
        return int(guess)

    def mySqrt(self, x: int) -> int:
        return self.sqrt_newton(x)
```


https://leetcode.com/problems/sqrtx/description/?envType=study-plan-v2&envId=top-interview-150


https://leetcode.com/problems/minimum-time-visiting-all-points/description/

### **Arithmetic Progression (AP)**
An **Arithmetic Progression (AP)** is a sequence of numbers in which the difference between consecutive terms remains constant. This constant difference is called the **common difference (d)**.

#### **General Form of an AP**
If the first term of an AP is **a** and the common difference is **d**, then the sequence looks like:

\[
a, (a + d), (a + 2d), (a + 3d), \dots
\]

#### **Formula for the nth term (General term)**
The **nth term** of an AP is given by:

\[
T_n = a + (n - 1) d
\]

where:
- \( T_n \) = nth term
- \( a \) = first term
- \( d \) = common difference
- \( n \) = position of the term

#### **Sum of First n Terms of an AP**
The sum of the first **n** terms of an AP is given by:

\[
S_n = \frac{n}{2} \times (2a + (n - 1) d)
\]

OR

\[
S_n = \frac{n}{2} \times (a + l)
\]

where:
- \( S_n \) = sum of the first n terms
- \( l \) = last term (\( l = a + (n - 1)d \))

#### **Example of an Arithmetic Progression**
Example 1: Find the 10th term of the AP: **2, 5, 8, 11, ...**

- First term \( a = 2 \)
- Common difference \( d = 5 - 2 = 3 \)
- Number of terms \( n = 10 \)

Using the formula:

\[
T_{10} = 2 + (10 - 1) \times 3 = 2 + 27 = 29
\]

So, the 10th term is **29**.

#### **Real-Life Applications of AP**
- Calculating savings with a fixed deposit per month.
- Arranging seats in an auditorium (rows with an increasing number of seats).
- Time taken for a repetitive task with a fixed increment.




Great question! The key to identifying whether a sequence follows a **quadratic, cubic, or any other polynomial pattern** is by analyzing the **differences between terms**.

---

### **Step 1: Check the First Differences**
Compute the **first differences** by subtracting consecutive terms.

If the first differences are **constant**, then the sequence follows a **linear pattern**:

\[
T_n = an + b
\]



---

### **Step 2: Check the Second Differences**
If the first differences are **not constant**, compute the **second differences**.

- If the **second differences are constant**, then the sequence follows a **quadratic pattern**:

  \[
  T_n = an^2 + bn + c
  \]

- If the **second differences are not constant**, proceed to the next step.

---

### **Step 3: Check the Third Differences**
If the second differences are **not constant**, compute the **third differences**.

- If the **third differences are constant**, then the sequence follows a **cubic pattern**:

  \[
  T_n = an^3 + bn^2 + cn + d
  \]

- If the third differences are **not constant**, continue this process for higher-degree polynomials.

---

### **Example 1: Quadratic Sequence**
Consider:  
**2, 6, 12, 20, 30, …**

#### **Step 1: Find First Differences**
\[
6 - 2 = 4, \quad 12 - 6 = 6, \quad 20 - 12 = 8, \quad 30 - 20 = 10
\]

First differences: **4, 6, 8, 10** (not constant)

#### **Step 2: Find Second Differences**
\[
6 - 4 = 2, \quad 8 - 6 = 2, \quad 10 - 8 = 2
\]

Second differences: **2, 2, 2** (constant) ✅  
So, this is a **quadratic sequence**.

---

### **Example 2: Cubic Sequence**
Consider:  
**1, 8, 27, 64, 125, …**

#### **Step 1: First Differences**
\[
8 - 1 = 7, \quad 27 - 8 = 19, \quad 64 - 27 = 37, \quad 125 - 64 = 61
\]

First differences: **7, 19, 37, 61** (not constant)

#### **Step 2: Second Differences**
\[
19 - 7 = 12, \quad 37 - 19 = 18, \quad 61 - 37 = 24
\]

Second differences: **12, 18, 24** (not constant)

#### **Step 3: Third Differences**
\[
18 - 12 = 6, \quad 24 - 18 = 6
\]

Third differences: **6, 6** (constant) ✅  
So, this is a **cubic sequence**.

---

### **Key Takeaway**
1. If **first differences are constant** → **Linear** (\( an + b \))
2. If **second differences are constant** → **Quadratic** (\( an^2 + bn + c \))
3. If **third differences are constant** → **Cubic** (\( an^3 + bn^2 + cn + d \))
4. If **fourth differences are constant** → **Quartic** (\( an^4 + bn^3 + cn^2 + dn + e \)), and so on.

This method works for any polynomial sequence!

Would you like me to generate some practice problems where you determine the type of sequence? 🚀😊



### **Geometric Progression (GP)**
A **Geometric Progression (GP)** is a sequence where each term is obtained by multiplying the previous term by a constant ratio, called the **common ratio (\( r \))**.

#### **General Form of a GP**
If the first term is **\( a \)** and the common ratio is **\( r \)**, the sequence looks like:

\[
a, ar, ar^2, ar^3, \dots
\]

#### **Formula for the nth Term**
\[
T_n = a \cdot r^{(n-1)}
\]
where:
- \( T_n \) = \( n \)th term
- \( a \) = first term
- \( r \) = common ratio
- \( n \) = term position

#### **Sum of First \( n \) Terms of a GP**
\[
S_n = a \frac{(1 - r^n)}{1 - r}, \quad \text{if } r \neq 1
\]

#### **Sum of Infinite GP** (for \( |r| < 1 \))
\[
S_{\infty} = \frac{a}{1 - r}
\]

---

### **Examples of Geometric Progressions**
Here are some **problems similar to the quadratic ones** but for **geometric progressions**.

---

### **Problem 1**
Find the **nth term formula** for the sequence:

\[
3, 6, 12, 24, 48, \dots
\]

Find the **7th term**.

---

### **Problem 2**
Find the **nth term formula** for the sequence:

\[
2, 10, 50, 250, 1250, \dots
\]

Find the **6th term**.

---

### **Problem 3**
Find the **nth term formula** for:

\[
81, 27, 9, 3, 1, \dots
\]

Find the **10th term**.

---

### **Problem 4**
A sequence follows the pattern:

\[
5, 15, 45, 135, 405, \dots
\]

Find:
1. The **nth term formula**.
2. The **8th term**.

---

### **Problem 5**
A GP has first term \( 4 \) and common ratio \( \frac{1}{2} \).  
Find:
1. The **6th term**.
2. The **sum of the first 10 terms**.

---

Would you like solutions for any of these? 🚀😊



### **When the Differences Form a Geometric Progression (GP)**

If the **differences between consecutive terms** of a sequence form a **Geometric Progression (GP)** instead of being constant (as in an Arithmetic Progression), then the original sequence follows a **higher-order pattern** (e.g., exponential or mixed progression).

---

### **Step 1: Identify the Differences**
For a sequence \( T_1, T_2, T_3, T_4, \dots \), compute the **first differences**:

\[
D_1 = T_2 - T_1, \quad D_2 = T_3 - T_2, \quad D_3 = T_4 - T_3, \dots
\]

If these **differences** form a **GP**, then the sequence is not a simple AP or GP but follows a higher-order pattern.

---

### **Example 1**
Consider the sequence:

\[
2, 5, 11, 23, 47, \dots
\]

#### **Step 1: Find the First Differences**
\[
5 - 2 = 3, \quad 11 - 5 = 6, \quad 23 - 11 = 12, \quad 47 - 23 = 24
\]

First differences: **3, 6, 12, 24, …**

#### **Step 2: Check if Differences Form a GP**
\[
\frac{6}{3} = 2, \quad \frac{12}{6} = 2, \quad \frac{24}{12} = 2
\]

Since the first differences form a **GP with ratio 2**, the original sequence follows a **quadratic pattern** rather than a simple AP or GP.

---

### **Example 2**
Consider the sequence:

\[
1, 3, 7, 15, 31, \dots
\]

#### **Step 1: Find the First Differences**
\[
3 - 1 = 2, \quad 7 - 3 = 4, \quad 15 - 7 = 8, \quad 31 - 15 = 16
\]

First differences: **2, 4, 8, 16, …**

#### **Step 2: Check if Differences Form a GP**
\[
\frac{4}{2} = 2, \quad \frac{8}{4} = 2, \quad \frac{16}{8} = 2
\]

Since the differences form a **GP with ratio 2**, the original sequence follows a **quadratic or exponential pattern**.

---

### **Finding the General Formula**
If the **first differences form a GP**, then the original sequence follows a **quadratic equation** of the form:

\[
T_n = an^2 + bn + c
\]

If the **second differences form a GP**, then the sequence follows a **cubic pattern**:

\[
T_n = an^3 + bn^2 + cn + d
\]

If the differences grow exponentially, the sequence follows a **mixed exponential pattern**.

---

### **Practice Problems**
1. Find the **general formula** for the sequence:  
   **2, 6, 14, 30, 62, …**

2. Find the **nth term** of the sequence:  
   **1, 4, 10, 22, 46, …**  
   (Hint: Check the differences!)

3. Find the **general term** for:  
   **3, 7, 15, 31, 63, …**

---

Would you like me to solve one for you? 🚀😊