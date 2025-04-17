### ✅ Must-Practice Interval Questions

-  [163. Missing Ranges](https://leetcode.com/problems/missing-ranges/description/?envType=company&envId=facebook&favoriteSlug=facebook-three-months)
- [1854. Maximum Population Year](https://leetcode.com/problems/maximum-population-year/description/?envType=company&envId=facebook&favoriteSlug=facebook-three-months)
- [56. Merge Intervals](https://leetcode.com/problems/merge-intervals/)
- [57. Insert Interval](https://leetcode.com/problems/insert-interval/)
- [252. Meeting Rooms I](https://leetcode.com/problems/meeting-rooms/)
- [253. Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)
- [435. Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/)
- [452. Minimum Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/)
- [986. Interval List Intersections](https://leetcode.com/problems/interval-list-intersections/)
- [732. My Calendar III](https://leetcode.com/problems/my-calendar-iii/)
- [2406. Divide Intervals](https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/)
- [759. Employee Free Time](https://leetcode.com/problems/employee-free-time/)
- [1109. Corporate Flight Bookings](https://leetcode.com/problems/corporate-flight-bookings/)
- [729. My Calendar I](https://leetcode.com/problems/my-calendar-i/)
- [731. My Calendar II](https://leetcode.com/problems/my-calendar-ii/)
- [436. Find Right Interval](https://leetcode.com/problems/find-right-interval/)
- [1288. Remove Covered Intervals](https://leetcode.com/problems/remove-covered-intervals/)
- [1834. Single-Threaded CPU](https://leetcode.com/problems/single-threaded-cpu/)
- [646. Maximum Length of Pair Chain](https://leetcode.com/problems/maximum-length-of-pair-chain/)

### 🧩 Core Patterns & Concepts

#### 1️⃣ Merge Intervals (Sorting + Greedy)
- ✅ Use when merging overlapping intervals or processing calendar events.
- **Real-life use case**: Merging overlapping booking times, log intervals, or timelines.
- **How to recognize**: You’re given a list of intervals and asked to merge/clean/normalize them.
- **Example problems**:
  - [56. Merge Intervals](https://leetcode.com/problems/merge-intervals/)
  - [57. Insert Interval](https://leetcode.com/problems/insert-interval/)

```python
# Python - Merge Intervals

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

#### 2️⃣ Meeting Rooms Variants
- ✅ Track concurrent intervals.
- **Real-life use case**: Scheduling meetings in rooms, allocating taxis, platform assignments.
- **How to recognize**: Problem asks for "minimum rooms" or "maximum overlaps" at any time.
- **Example problems**:
  - [253. Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)
  - [252. Meeting Rooms I](https://leetcode.com/problems/meeting-rooms/)
  - [732. My Calendar III](https://leetcode.com/problems/my-calendar-iii/)

```java
// Java - Meeting Rooms (Two Pointers)
...
```

```java
// Java - Meeting Rooms (Heap)
...
```

---

#### 🧠 Greedy (Choose earliest end time)
- **Real-life use case**: Maximize jobs done in limited time, minimize conflicts in scheduling.
- **How to recognize**: Asked to "select max non-overlapping intervals" or minimize something by choosing smartly.
- **Example problems**:
  - [435. Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/)
  - [452. Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/)
  - [646. Maximum Length of Pair Chain](https://leetcode.com/problems/maximum-length-of-pair-chain/)

#### 🧱 Heap (Track ongoing intervals)
- **Real-life use case**: Load balancers, resource managers tracking task durations.
- **How to recognize**: Track active intervals in real-time, especially if end time is involved.
- **Example problems**:
  - [253. Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)
  - [759. Employee Free Time](https://leetcode.com/problems/employee-free-time/)

#### 🧹 Sweep Line
- **Real-life use case**: Event management, concurrent sessions, tracking peak usage.
- **How to recognize**: Start/end event model, and a need to compute overlaps efficiently.
- **Example problems**:
  - [732. My Calendar III](https://leetcode.com/problems/my-calendar-iii/)
  - [2406. Divide Intervals Into Minimum Number of Groups](https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/)
  - [1094. Car Pooling](https://leetcode.com/problems/car-pooling/)

#### 🔁 Two Pointers
- **Real-life use case**: Comparing calendars of two users, reservation system conflict checks.
- **How to recognize**: Two sorted interval lists or sorted lists to merge/intersect.
- **Example problems**:
  - [986. Interval List Intersections](https://leetcode.com/problems/interval-list-intersections/)
  - [1229. Meeting Scheduler](https://leetcode.com/problems/meeting-scheduler/)

---

### 🧩 Advanced Patterns

#### 📊 Prefix Sum / Difference Array
- **Real-life use case**: Traffic flow over time, cumulative change over intervals.
- **How to recognize**: You’re given range updates and asked for aggregate values later.
- **Example problems**:
  - [1094. Car Pooling](https://leetcode.com/problems/car-pooling/)
  - [1109. Corporate Flight Bookings](https://leetcode.com/problems/corporate-flight-bookings/)

#### 🎨 Interval Graph Coloring
- **Real-life use case**: Assigning lectures to rooms, TV shows to slots.
- **How to recognize**: Problem asks for groups/partitions of intervals without overlap.
- **Example problems**:
  - [2406. Divide Intervals Into Minimum Number of Groups](https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/)

#### 🧮 Edge Case Interval Merging
- **Real-life use case**: Inserting new bookings, adjusting overlapping windows.
- **How to recognize**: You're modifying an existing list (insert/delete) while maintaining merged state.
- **Example problems**:
  - [57. Insert Interval](https://leetcode.com/problems/insert-interval/)
  - [1288. Remove Covered Intervals](https://leetcode.com/problems/remove-covered-intervals/)

#### 🧾 Booking Systems (Segment Tree, TreeMap)
- **Real-life use case**: Calendar apps, room reservation systems, service bookings with complex rules.
- **How to recognize**: Complex overlapping rules (single, double, triple bookings).
- **Example problems**:
  - [729. My Calendar I](https://leetcode.com/problems/my-calendar-i/)
  - [731. My Calendar II](https://leetcode.com/problems/my-calendar-ii/)
  - [732. My Calendar III](https://leetcode.com/problems/my-calendar-iii/)

#### 🔍 Binary Search in Intervals
- **Real-life use case**: Finding the next available appointment slot, scheduling dependencies.
- **How to recognize**: Searching across sorted intervals or earliest possible start.
- **Example problems**:
  - [436. Find Right Interval](https://leetcode.com/problems/find-right-interval/)

#### 📐 Greedy + Custom Sorting
- **Real-life use case**: Job scheduling, prioritizing by multiple criteria (time, cost).
- **How to recognize**: You must sort based on more than one field.
- **Example problems**:
  - [1288. Remove Covered Intervals](https://leetcode.com/problems/remove-covered-intervals/)

#### 🔄 Sliding Window Over Timeline
- **Real-life use case**: API rate limits, analyzing burst traffic in fixed time windows.
- **How to recognize**: Time windows (5 min, 1 hour), and you’re asked for max/min over that period.
- **Example problems**:
  - [1834. Single-Threaded CPU](https://leetcode.com/problems/single-threaded-cpu/)

---

### 📌 Final Interview Tips

- Always **sort** intervals by start or end before processing.
- Choose the right strategy:
  - **Greedy**: Pick optimal subset (e.g., non-overlapping).
  - **Heap**: Track real-time concurrency.
  - **Sweep Line**: Count overlaps efficiently.
  - **Two Pointers**: Efficient comparison of two sorted lists.
- Focus on **edge cases**: overlapping at exact points, full containment, adjacent intervals.
- **Pattern Recognition Tips**:
  - If tracking current active intervals → Heap or Sweep Line
  - If choosing max tasks → Greedy
  - If two sorted lists involved → Two Pointers
  - If asked about changes over ranges → Prefix Sum
  - If asked about real-time booking behavior → TreeMap / Segment Tree

---
