
1️⃣ **Merge Intervals (Sorting + Greedy)**  
✅ Used for interval problems like **meeting rooms, merge overlapping intervals**.

**Example:** **Merge Intervals**
```python
def merge_intervals(intervals: list[list[int]]) -> list[list[int]]:
    intervals.sort()  
    merged = []

    for interval in intervals:
        if not merged or merged[-1][1] < interval[0]:
            merged.append(interval)
        else:
            merged[-1][1] = max(merged[-1][1], interval[1])

    return merged
```
max room required for given meetings
```python 
import java.util.*;

public class MeetingRooms {

    public static int maxMeetingRooms(int[][] meetings) {
        if (meetings == null || meetings.length == 0) return 0;

        int n = meetings.length;
        int[] startTimes = new int[n];
        int[] endTimes = new int[n];

        for (int i = 0; i < n; i++) {
            startTimes[i] = meetings[i][0];
            endTimes[i] = meetings[i][1];
        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int startPointer = 0, endPointer = 0;
        int rooms = 0, maxRooms = 0;

        while (startPointer < n) {
            if (startTimes[startPointer] < endTimes[endPointer]) {
                rooms++;
                startPointer++;
            } else {
                rooms--;
                endPointer++;
            }
            maxRooms = Math.max(maxRooms, rooms);
        }

        return maxRooms;
    }

    public static void main(String[] args) {
        int[][] meetings = {
            {0, 30},
            {5, 10},
            {15, 20}
        };

        System.out.println("Max meeting rooms required: " + maxMeetingRooms(meetings));
    }
}

```

```java
import java.util.*;

public class MeetingRoomsHeap {

    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        // Step 1: Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 2: Min-heap to track end times of ongoing meetings
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Step 3: Process each meeting
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            // If a meeting has ended before the current starts, reuse the room
            while (!minHeap.isEmpty() && start >= minHeap.peek()) {
                minHeap.poll();  // Room becomes free
            }

            minHeap.offer(end);  // Allocate room for current meeting
        }

        // Step 4: Size of heap = max rooms used at any point
        return minHeap.size();
    }

    public static void main(String[] args) {
        int[][] meetings = {
            {0, 30},
            {5, 10},
            {15, 20}
        };

        System.out.println("Minimum rooms required: " + minMeetingRooms(meetings));
    }
}

```
max platforms required for give trains timing at platform
---
* https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/solutions/5950330/more-simpler-java-solution/
* https://leetcode.com/problems/insert-interval/description/?envType=study-plan-v2&envId=top-interview-150
* https://leetcode.com/problems/merge-intervals/description/?envType=study-plan-v2&envId=top-interview-150
* https://leetcode.com/problems/summary-ranges/description/?envType=study-plan-v2&envId=top-interview-150
* https://leetcode.com/problems/meeting-rooms-ii/description/

Absolutely — you're spot on! This **"Meeting Rooms II"** (or "Minimum Platforms for Trains") problem is a **classic interval scheduling** question and very popular in interviews, especially for roles at FAANG or any backend/system-heavy roles.

Here are **similar and related interval-based problems** that are **commonly asked** in interviews — many with overlapping concepts like sweep-line, greedy, heap, and merging intervals:

---

## 🔥 TOP Similar Interval Problems on LeetCode:

---

### **1. [56. Merge Intervals](https://leetcode.com/problems/merge-intervals/)**
- **Type**: Merge overlapping intervals
- **Approach**: Sort + Merge into result list
- ✅ Must-know for basic interval manipulation

---

### **2. [57. Insert Interval](https://leetcode.com/problems/insert-interval/)**
- **Type**: Insert a new interval into a sorted list and merge if needed
- **Used for**: Scheduling systems
- ✅ Variation of Merge Intervals

---

### **3. [252. Meeting Rooms I](https://leetcode.com/problems/meeting-rooms/)**
- **Type**: Just check if all meetings can be attended (no overlap)
- **Approach**: Sort by start time, check if current starts before previous ends
- ✅ Simpler version of Meeting Rooms II

---

### **4. [759. Employee Free Time](https://leetcode.com/problems/employee-free-time/)**
- **Type**: Given employees' schedules, find common free time slots
- **Approach**: Merge all busy intervals, invert to find gaps
- ✅ Uses heap & interval merging

---

### **5. [435. Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/)**
- **Type**: Remove minimum number of intervals to make the rest non-overlapping
- **Approach**: Greedy, sort by end time
- ✅ Classic greedy optimization problem

---

### **6. [986. Interval List Intersections](https://leetcode.com/problems/interval-list-intersections/)**
- **Type**: Find overlapping parts between two interval lists
- **Approach**: Two-pointer traversal
- ✅ Great for calendar systems

---

### **7. [759. Employee Free Time](https://leetcode.com/problems/employee-free-time/)**
- **Type**: Find shared "free time" intervals among employees
- **Approach**: Heap + interval merge
- ✅ Complex but highly valuable

---

### **8. [732. My Calendar III](https://leetcode.com/problems/my-calendar-iii/)**
- **Type**: Return max number of concurrent events
- **Approach**: Sweep line algorithm or TreeMap
- ✅ Advanced version of Meeting Rooms II

---

### **9. [1029. Two City Scheduling](https://leetcode.com/problems/two-city-scheduling/)**
- **Type**: Schedule based on cost — send people to 2 cities minimizing cost
- **Approach**: Greedy, sort by cost difference
- ✅ Conceptually different, but often grouped with interval-type scheduling

---

### 📌 Tips for Mastering Interval Problems:

- Always start by **sorting intervals** (usually by start time or end time)
- Understand when to use:
    - **Greedy** (e.g., choosing earliest end time)
    - **Heap** (e.g., managing active meetings)
    - **Sweep Line** (counting overlaps efficiently)
    - **Two Pointers** (merging/comparing two lists)
- Practice variations: overlap check, merging, removal, insertion, maximum concurrency.

---

Want me to organize these into a **study plan**, or provide **Java templates** to solve each of them?