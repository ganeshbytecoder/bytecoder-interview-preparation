https://leetcode.com/problems/simplify-path/submissions/1521546931/?envType=study-plan-v2&envId=top-interview-150
https://leetcode.com/problems/min-stack/?envType=study-plan-v2&envId=top-interview-150
https://leetcode.com/problems/evaluate-reverse-polish-notation/description/?envType=study-plan-v2&envId=top-interview-150
https://leetcode.com/problems/decode-string/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days
https://leetcode.com/problems/exclusive-time-of-functions/?envType=company&envId=linkedin&favoriteSlug=linkedin-thirty-days

https://leetcode.com/problems/exclusive-time-of-functions/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days

**Problem** balancing of symbols,

Note: if symbol is one then below else use stack
```python3
        def isValid(s) :
            opens =0
            close = 0
    
            for i in s:
                if( i in ['(', '{', '[']):
                    opens +=1
                else:
                    close +=1
            
            return opens==close

```

### reverseStack using recursion
```java
public static int reverseStack(Stack<Integer> stack){
        if(stack.isEmpty()){
            return 0;
        }
        int temp = stack.pop();
        int ans  = reverseStack(stack);
        stack.add(temp);
        return ans;
    }
```


**Problem** undo sequence and page history

**Problem** evaluation of postfix expression

**Problem** design a stack such that getMinimum() should be O(1)

hint: take an auxiliary stack that maintains the minimum of all values in the stack.

**Problem** largest rectangle under the histogram

**Problem** given an array of elements , replace every element with the nearest greater element on the right of that element

hint: brute-force using two for loop 

**Problem** given a singly linked list l1->l2->l3.....-> ln. reorder it to l1->ln-> l2->ln-1....


**Problem** list of Max element in sliding window on an array

hint :
* using brute-force
* using priority queue


8. **Find the Next/Previous Greater/Smaller Element** (https://leetcode.com/problems/next-greater-element-i/submissions/1452271799/)
   - **Hint**: Traverse the array from right to left, pushing elements onto a stack. For each element, pop smaller elements and use the stack's top as the next greater element.
    
```java
public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> hm=new HashMap<>();
        Stack<Integer> st=new Stack<>();
        for(int i=nums2.length-1;i>=0;i--){
            while(!st.isEmpty() && st.peek()<nums2[i]){
                st.pop();
            }
            if(st.isEmpty()) hm.put(nums2[i],-1);
            else hm.put(nums2[i],st.peek());
            st.push(nums2[i]); 
        }

        int [] ans=new int[nums1.length];
        for(int i=0;i<nums1.length;i++){
            ans[i]=hm.get(nums1[i]);
        }
    return ans;
}
```


9. **The Celebrity Problem**
    Given a square matrix mat[][] of size n x n, such that mat[i][j] = 1 means ith person knows jth person, the task is to find the celebrity. A celebrity is a person who is known to all but does not know anyone. Return the index of the celebrity, if there is no celebrity return -1.
    Input: mat = { {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}, {0, 0, 1, 0} }\
    Output: id = 2\
    Explanation: The person with ID 2 does not know anyone but everyone knows him\

    Input: mat = { {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 1, 0, 0}, {0, 0, 1, 0} }\
    Output: No celebrity\
    Explanation: There is no celebrity.\

   - **Hint**: Use two pointers or a stack. Keep eliminating non-celebrities by comparing them, eventually narrowing down to a single candidate.
   - indegree and outdegree of graph problem 


10. **Arithmetic Expression Evaluation**
    - **Hint**: Use two stacks: one for operators and one for operands. Follow the operator precedence and associativity rules while pushing and popping elements.

11. **Evaluation of Postfix Expression**
    - **Hint**: Traverse the expression and push operands onto the stack. When encountering an operator, pop the top two elements, apply the operator, and push the result back onto the stack.

12. important **Insert Element at the Bottom of Stack Without Extra DS**
    - **Hint**: Use recursion to pop all elements, insert the new element at the bottom, and push all the popped elements back onto the stack.

13. important **Reverse a Stack Using Recursion**
    - **Hint**: Recursively pop elements until the stack is empty, then insert each element at the bottom using a helper recursive function.
-  Follow the steps mentioned below to implement the idea:
```text
    Create a stack and push all the elements in it.
    Call reverse(), which will pop all the elements from the stack and pass the popped element to function insert_at_bottom()
    Whenever insert_at_bottom() is called it will insert the passed element at the bottom of the stack.
    Print the stack   
```
         

14. **Sort a Stack Using Recursion**
    - **Hint**: Pop elements recursively and insert them back in sorted order using a helper function that finds the correct position for each element.

16. **Length of the Longest Valid Substring** \
    Given a string s consisting only of opening and closing parenthesis 'ie '('  and ')', find out the length of the longest valid(well-formed) parentheses substring.

    - **Hint**: Use a stack to store indices of unbalanced opening brackets. Calculate the length of valid substrings using the stack and track the longest.

```python
    def longestValidParentheses(self, s: str) -> int:
        ans  =0
        close =0
        op = 0
        
        for i in s:
            if(i == '('):
                op +=1
            else:
                close +=1

            if(op ==  close):
                ans = max(ans, op + close)
            if(close>op):
                op=0
                close=0

        op=close=0
        for i in s[::-1]:
            if(i == '('):
                op +=1
            else:
                close +=1

            if(op ==  close):
                ans = max(ans, op + close)
            if(op>close):
                op=0
                close=0
        
        return ans
```


17. **Expression Contains Redundant Brackets**
    - **Hint**: Use a stack to track operators and parentheses. If you find consecutive parentheses without any operators between them, they are redundant.

```java

    static boolean checkRedundancy(String s) {
        // create a stack of characters 
        Stack<Character> st = new Stack<>();
        char[] str = s.toCharArray();
        // Iterate through the given expression 
        for (char ch : str) {
 
            // if current character is close parenthesis ')' 
            if (ch == ')') {
                char top = st.peek();
                st.pop();
 
                // If immediate pop have open parenthesis '(' 
                // duplicate brackets found 
                boolean flag = true;
 
                while (top != '(') {
 
                    // Check for operators in expression 
                    if (top == '+' || top == '-'
                            || top == '*' || top == '/') {
                        flag = false;
                    }
 
                    // Fetch top element of stack 
                    top = st.peek();
                    st.pop();
                }
 
                // If operators not found 
                if (flag == true) {
                    return true;
                }
            } else {
                st.push(ch); // push open parenthesis '(', 
            }                // operators and operands to stack 
        }
        return false;
    }
```

18. **Implement Stack Using Queue**
    - **Hint**: Use two queues and simulate stack behavior by ensuring the push or pop operation involves transferring elements between queues.

19. **Implement Stack Using Deque**
    - **Hint**: Use a double-ended queue to simulate stack operations (push and pop) from one end.


