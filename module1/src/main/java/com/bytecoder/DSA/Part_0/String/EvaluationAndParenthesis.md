Parenthesis and expression evaluation and calulator :-

Infix Notation: Operators are written between the operands they operate on, e.g. 3 + 4.
Prefix Notation: Operators are written before the operands, e.g + 3 4
Postfix Notation: Operators are written after operands. e.g  3 4 +
Infix to Postfix  or  Infix to Postfix Expression
Infix to Prefix or Prefix to Infix Expression





## Expression evaluation

### ✅ Evaluation of Infix Expression

Unlike prefix and postfix, evaluating **infix expressions** directly requires handling:

- **Operator precedence** (e.g. `*` > `+`)
- **Associativity** (left-to-right or right-to-left)
- **Parentheses**

Hence, we use two **stacks**:
1. **Operand Stack** – stores numbers
2. **Operator Stack** – stores operators and handles precedence

---

### 🔧 Algorithm:

1. **Scan left to right**:
    - If digit → build number → push to **operand stack**
    - If `(` → push to **operator stack**
    - If `)` → pop operators until `(` is found
    - If operator → pop from stack while top has **higher or equal precedence**, then push current operator

2. **After traversal**, apply remaining operators.

---

### 🧠 Python Code:

```python
def precedence(op):
    if op == '+' or op == '-':
        return 1
    if op == '*' or op == '/':
        return 2
    if op == '^':
        return 3
    return 0

def apply_op(a, b, op):
    if op == '+': return a + b
    if op == '-': return a - b
    if op == '*': return a * b
    if op == '/': return int(a / b)  # truncate towards 0 like C/C++
    if op == '^': return a ** b

def evaluate_infix(expression):
    operands = []
    operators = []
    i = 0
    while i < len(expression):
        if expression[i] == ' ':
            i += 1
            continue
        if expression[i] == '(':
            operators.append(expression[i])
        elif expression[i].isdigit():
            num = 0
            while i < len(expression) and expression[i].isdigit():
                num = num * 10 + int(expression[i])
                i += 1
            operands.append(num)
            continue  # already incremented i in loop
        elif expression[i] == ')':
            while operators and operators[-1] != '(':
                b = operands.pop()
                a = operands.pop()
                op = operators.pop()
                operands.append(apply_op(a, b, op))
            operators.pop()  # pop '('
        else:
            # Operator
            while (operators and precedence(operators[-1]) >= precedence(expression[i])):
                b = operands.pop()
                a = operands.pop()
                op = operators.pop()
                operands.append(apply_op(a, b, op))
            operators.append(expression[i])
        i += 1

    while operators:
        b = operands.pop()
        a = operands.pop()
        op = operators.pop()
        operands.append(apply_op(a, b, op))

    return operands[-1]

# Test
expr = "3 + 4 * 2 / (1 - 5) ^ 2 ^ 3"
print("Result:", evaluate_infix(expr))  # Output: approx 3.00012207
```

---

## Evaluation of Prefix and Postfix Expression


Both **prefix** and **postfix** notations are designed to be evaluated **directly using a stack**, which is more efficient.

---

## ✅ Why you don't convert:

- Infix requires **operator precedence** and **parentheses handling**.
- Postfix and Prefix remove this complexity.
- You just scan and use a **stack** to compute step-by-step.

---

## 🔧 How Evaluation Works:

### 1. **Evaluate Postfix** (Left to Right)
- Use a **stack**.
- Push operands.
- On operator: Pop 2 operands, compute, push result.

```python
def evaluate_postfix(expr):
    stack = []
    for ch in expr:
        if ch.isdigit():
            stack.append(int(ch))
        else:
            b = stack.pop()
            a = stack.pop()
            if ch == '+':
                stack.append(a + b)
            elif ch == '-':
                stack.append(a - b)
            elif ch == '*':
                stack.append(a * b)
            elif ch == '/':
                stack.append(int(a / b))
            elif ch == '^':
                stack.append(pow(a, b))
    return stack[0]

# Example
print(evaluate_postfix("342*15-23^^/+"))  # Output: 3.00012207 (approx)
```

---

### 2. **Evaluate Prefix** (Right to Left)
- Same idea, just **scan right to left**.
- Push operands.
- On operator: Pop 2 operands, compute, push result.

```python
def evaluate_prefix(expr):
    stack = []
    for ch in reversed(expr):
        if ch.isdigit():
            stack.append(int(ch))
        else:
            a = stack.pop()
            b = stack.pop()
            if ch == '+':
                stack.append(a + b)
            elif ch == '-':
                stack.append(a - b)
            elif ch == '*':
                stack.append(a * b)
            elif ch == '/':
                stack.append(int(a / b))
            elif ch == '^':
                stack.append(pow(a, b))
    return stack[0]

# Example
print(evaluate_prefix("+3/*42^^-1523"))  # Output: 3.00012207 (approx)
```

---


- https://leetcode.com/problems/evaluate-reverse-polish-notation/description/
- https://leetcode.com/problems/basic-calculator/
- https://leetcode.com/problems/basic-calculator-ii/description/
- https://leetcode.com/problems/basic-calculator-iii/description/




1.https://leetcode.com/problems/generate-parentheses Medium
2.https://leetcode.com/problems/score-of-parentheses Medium
5.https://leetcode.com/problems/remove-outermost-parentheses Easy
8.https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses Medium
9.https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses Easy
10.https://leetcode.com/problems/longest-valid-parentheses/ Hard
7.https://leetcode.com/problems/remove-invalid-parentheses Hard
- https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/



- https://www.geeksforgeeks.org/evaluation-of-postfix-expression/
- https://www.geeksforgeeks.org/evaluation-of-prefix-expression/
- https://www.geeksforgeeks.org/evaluation-of-infix-expression/
- 

https://leetcode.com/problems/expression-add-operators/description/
https://leetcode.com/problems/different-ways-to-add-parentheses/description/