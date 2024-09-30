# Sorting Algorithms 

## bubble sort : 
comparing the each pair of elements and swapping them if need. bubble sort continues its iterations until no more swaps are needed. 
* algorithm takes O(n^2) and space complexity O(1)

```java
public static void main(String[] args) {
    int[] array = {10, 2, 3, 5, 0};
    for (int i = 0; i < array.length; i++) {
        for (int j = 0; j < array.length - 1; j++) {
            if (array[j+1] < array[j]) {
                swap(array, j, j+1);
            }
        }
    }
    System.out.println(array);
}
```

## selection sort: 
we creates two index i and j where is used for current element and j is to find least element from right side of list.
swap it with the value in the current position. 
Repeat this process for all the elements until the entire array is sorted. 
* algorithm takes O(n^2) and space complexity O(1)


```java
public static void main(String[] args) {
    int[] array = {10, 2, 3, 5, 0};
    for (int i = 0; i < array.length; i++) {
        int min = i;
        for (int j = i + 1; j < array.length; j++) {
            if (array[j] < array[min]) {
                min = j;
            }
        }
        swap(array, i, min);
    }
}

```


## Insertion sort: 
in this algorithm we pick a element and slide it till it satisfy the condition then swap it 

```java

public static void main(String[] args) {
    int[] array = {10, 2, 3, 5, 1};
    for (int i = 1; i < array.length; i++) {
        int k = i;
        int temp = array[k];
        
        while (k > 0 && temp < array[k - 1]) {
            array[k] = array[k - 1];
            k--;
        }
        array[k] = temp;
    }
    for (int i : array) {
        System.out.println(i);
    }
}
```


## Merge Sort


## Heap Sort 


## Quick Sort


## Radix Sort


---------------------------------------------------------

## problems 

**Problem-1:** check the list if any element is duplicated or not

hint:
* brute-force with two loops  
* sorting technique

**Problem** Given an array , where each element of the array represents a vote in the elections. means this array contains list of chosen candidate IDs. find the candidate who wins the elections

hint:
* brute-force to find count for each candidate and update max count with candidate ID
* sorting technique and count for each Id 
* counting sort 
* using hashtable 

**Problem** Given two array of n elements and target value K. check whether a+b =k where a is from A and b is from B;

hint:
* using brute force
* using hashing technique 
* sort both the array and use binary search to find (k-a) in B


**Problem** if we have a telephone directory with 10 million entries which sorting is best


**Problem** nuts and bolts : given a set of nuts of different sizes and n bolts such that there is a one to one mapping only

Hint: 
* Brute force approach 

**Problem** You are given an m x n integer matrix matrix with the following two properties:
* Each row is sorted in non-decreasing order.
* The first integer of each row is greater than the last integer of the previous row.

![img_1.png](img_1.png)

**Problem** Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
* Integers in each row are sorted in ascending from left to right.
* Integers in each column are sorted in ascending from top to bottom.

![img.png](img.png)

**Problem** Sort elements of list by frequency
* list of objects {value, frequency} and sort the list based on frequency
* hashmap and sort by value







