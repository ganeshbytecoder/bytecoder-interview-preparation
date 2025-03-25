## **6️⃣ Monotonic Stack (Next Greater Element)**
Used when:
✅ Finding **next greater/smaller element** efficiently.  
✅ Works in **O(n)** time using a **stack**.

### **Example Problems**
| Problem Type | Approach |
|-------------|----------|
| **Next Greater Element** | Monotonic Stack (`O(n)`) |
| **Largest Rectangle in Histogram** | Monotonic Stack (`O(n)`) |

brute force
```python 

def getPrevSmaller(arr):
    result = [-1] * len(arr)
    for i in range(len(arr)):
        for j in range(i - 1, -1, -1):
            if arr[j] < arr[i]:
                result[i] = j
                break
    return result


def getPrevSmaller(self, arr):
    result = [-1]*len(arr)
    stack = []
    for i in range(len(arr)):

        while(stack and arr[stack[-1]] >= arr[i]):
            stack.pop()
        if(stack):
            result[i] = stack[-1]
        stack.append(i)
    return result
    


```

```python 
def getNextSmaller(arr):
    result = [len(arr)] * len(arr)
    for i in range(len(arr)):
        for j in range(i + 1, len(arr)):
            if arr[j] < arr[i]:
                result[i] = j
                break
    return result


def getNextSmaller(self, arr):
    result = [len(arr)]*len(arr)
    stack = []
    for i in range(len(arr)-1, -1, -1):

        while(stack and arr[stack[-1]] >= arr[i]):
            stack.pop()
        if(stack):
            result[i] = stack[-1]
        stack.append(i)
    return result


```

### **Example: Next Greater Element**
```python
    def next_greater_element(self,nums):
        stack = []
        result = [-1] * len(nums)  # Default to -1 (if no NGE found)

        for i in range(len(nums) - 1, -1, -1):  # Traverse from right to left
            while stack and stack[-1] <= nums[i]:  # Pop smaller elements
                stack.pop()

            if stack:  # If stack is not empty, top is the NGE
                result[i] = stack[-1]

            stack.append(nums[i])  # Push current element to stack

        return result
```
✅ **Use When**: Finding **next larger/smaller elements** efficiently.

---
