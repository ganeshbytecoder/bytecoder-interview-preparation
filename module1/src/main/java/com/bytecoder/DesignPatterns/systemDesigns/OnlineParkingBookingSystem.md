# **🚀 System Design: Online Parking Slot Booking with Future & Recurring Booking Support** (other examples - oyo, bookMyShow, airbnb etc)

## **📌 1. Overview**
The **Online Parking System** allows users to **search for nearby parking slots, book them in advance, and schedule recurring bookings**. It supports:  
✅ **Real-time slot availability** for one-time and future bookings.  
✅ **Recurring bookings (daily, weekly, monthly)**.  
✅ **Dynamic pricing for peak hours and long-term reservations**.  
✅ **Cancellation & refund policies**.  
✅ **Notifications & reminders** for upcoming bookings.  
✅ **Scalability to support millions of users across cities**.

---

## **📌 2. System Features & Requirements**

### **🚗 Core Features**
1️⃣ **Real-Time Slot Availability**: Users can search for **nearby parking slots** for a given date & time.  
2️⃣ **Future Booking**: Users can book a **parking slot in advance** for a specific date & time.  
3️⃣ **Recurring Booking**: Users can book **daily, weekly, or monthly** slots for long-term parking needs.  
4️⃣ **Cancellation & Refunds**: Users can cancel bookings, and refunds are processed based on **cancellation policies**.  
5️⃣ **Dynamic Pricing**: Prices vary based on **time of the day, location, and demand**.  
6️⃣ **Notifications & Reminders**: Users receive **reminders before their booking starts**.  
7️⃣ **Payments & Discounts**: Integrated **Stripe, PayPal, UPI**, and discount coupons for long-term bookings.

### **📈 Non-Functional Requirements (NFRs)**
✅ **Scalability**: System must handle **millions of users searching & booking slots in real-time**.  
✅ **Availability**: Ensure **99.99% uptime**, even during peak hours.  
✅ **Performance**: Search results must be **fetched in milliseconds** using **Geo-Spatial Indexing**.  
✅ **Security**: Secure **user payments, account details, and booking transactions**.

---

## **📌 3. Database Schema Design**
We use **PostgreSQL (with PostGIS for location indexing)** + **Redis for caching availability**.

### **🗺️ Parking Slots Table**
```sql
CREATE TABLE parking_slots (
    slot_id UUID PRIMARY KEY,
    lot_id UUID REFERENCES parking_lots(lot_id),
    floor INTEGER,
    slot_number VARCHAR(10),
    price_per_hour DECIMAL(5,2),
    is_available BOOLEAN DEFAULT TRUE
);
```

### **🏢 Parking Lots Table**
```sql
CREATE TABLE parking_lots (
    lot_id UUID PRIMARY KEY,
    name VARCHAR(100),
    location GEOMETRY(Point, 4326), -- PostGIS for Geo-Spatial indexing
    total_slots INTEGER,
    available_slots INTEGER,
    pricing_policy JSONB -- Dynamic pricing rules
);
```

### **📅 Bookings Table**
```sql
CREATE TABLE bookings (
    booking_id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(user_id),
    slot_id UUID REFERENCES parking_slots(slot_id),
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    is_recurring BOOLEAN DEFAULT FALSE,
    recurrence_pattern VARCHAR(20) CHECK (recurrence_pattern IN ('daily', 'weekly', 'monthly', 'none')) DEFAULT 'none',
    amount_paid DECIMAL(10,2),
    status VARCHAR(20) CHECK (status IN ('active', 'cancelled', 'completed', 'scheduled')),
    payment_id UUID REFERENCES payments(payment_id),
    created_at TIMESTAMP DEFAULT NOW()
);
```

### **⏳ Slot Availability Table (Prevents Overlapping Bookings)**
```sql
CREATE TABLE slot_availability (
    slot_id UUID REFERENCES parking_slots(slot_id),
    booking_id UUID REFERENCES bookings(booking_id),
    date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    PRIMARY KEY (slot_id, date, start_time)
);
```

### **💰 Payments Table**
```sql
CREATE TABLE payments (
    payment_id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(user_id),
    booking_id UUID REFERENCES bookings(booking_id),
    amount DECIMAL(10,2),
    status VARCHAR(20) CHECK (status IN ('pending', 'completed', 'failed')),
    created_at TIMESTAMP DEFAULT NOW()
);
```

---

## **📌 4. High-Level Architecture**
**🔹 Technologies Used:**
- **Frontend**: React.js / Vue.js (for Web), Flutter / React Native (for Mobile).
- **Backend**: **Spring Boot / Node.js + Express** (REST API + WebSockets).
- **Database**: **PostgreSQL (with PostGIS for location-based search)**.
- **Cache**: **Redis (for slot availability & search caching)**.
- **Message Queue**: **Kafka / RabbitMQ (for notifications & booking events)**.
- **Search & Indexing**: **Elasticsearch / PostGIS (for fast parking lot search)**.
- **Payment Processing**: **Stripe, PayPal, UPI**.
- **Deployment**: **AWS (EKS, RDS, S3, Lambda, SNS for notifications)**.

---

## **📌 5. API Endpoints**
### **🔍 1. Search Available Parking Slots**
```http
GET /api/parking/available?lat=37.7749&lng=-122.4194&date=2024-02-15&start_time=10:00&end_time=12:00
```
#### **Response**
```json
{
  "available_slots": [
    {
      "lot_id": "123",
      "slot_id": "456",
      "name": "Downtown Parking",
      "floor": 2,
      "price_per_hour": 5.00,
      "distance": "1.2 km"
    }
  ]
}
```

---

### **📅 2. Book a Parking Slot (One-Time or Future)**
```http
POST /api/bookings
```
#### **Payload**
```json
{
  "user_id": "567",
  "slot_id": "789",
  "start_time": "2024-02-15T08:00:00Z",
  "end_time": "2024-02-15T12:00:00Z",
  "is_recurring": false,
  "payment_method": "credit_card"
}
```

---

### **🔄 3. Book a Recurring Slot**
```http
POST /api/bookings
```
#### **Payload**
```json
{
  "user_id": "567",
  "slot_id": "789",
  "start_time": "2024-02-15T08:00:00Z",
  "end_time": "2024-02-15T12:00:00Z",
  "is_recurring": true,
  "recurrence_pattern": "weekly",
  "payment_method": "paypal"
}
```

---

### **📋 4. View Upcoming Bookings**
```http
GET /api/bookings/upcoming?user_id=567
```
#### **Response**
```json
{
  "upcoming_bookings": [
    {
      "booking_id": "111",
      "slot_id": "789",
      "start_time": "2024-02-15T08:00:00Z",
      "end_time": "2024-02-15T12:00:00Z",
      "status": "scheduled"
    },
    {
      "booking_id": "112",
      "slot_id": "789",
      "start_time": "2024-02-22T08:00:00Z",
      "end_time": "2024-02-22T12:00:00Z",
      "status": "scheduled",
      "recurrence_pattern": "weekly"
    }
  ]
}
```

---

### **❌ 5. Cancel a Booking**
```http
POST /api/bookings/cancel
```
#### **Payload**
```json
{
  "booking_id": "111"
}
```

---

## **📌 6. Scalability & Optimization Strategies**
| Feature | Solution |
|---------|---------|
| **High Availability** | Deploy on AWS **EKS (Kubernetes)** across multiple regions. |
| **Real-Time Slot Availability** | Use **Redis** to cache availability and **WebSockets** for instant updates. |
| **Recurring Bookings** | Use **batch processing** and **background jobs**. |
| **Location-Based Search** | Use **PostGIS with Geo-Spatial Indexing**. |
| **Dynamic Pricing** | Implement **ML-based price adjustments** using historical data. |

---

## **🚀 Final Summary**
| Feature | Status |
|---------|--------|
| **Real-time slot availability** | ✅ Implemented |
| **Future & Recurring bookings** | ✅ Implemented |
| **Fast search & caching** | ✅ Optimized |
| **Scalability & high availability** | ✅ Supported |
| **Notifications & payments** | ✅ Integrated |

---


### **📌 Preventing Overlapping Bookings in the Slot Availability Table**

To ensure that **parking slots** are **not double-booked**, we need to implement a **robust booking validation mechanism** at the database level. The **Slot Availability Table** will store **time-based reservations** and enforce **overlapping prevention** through constraints and indexing.

---

## **1️⃣ Key Challenges**
✅ Preventing **multiple users** from booking the same slot at the same time.  
✅ Allowing **future and recurring bookings** without conflicts.  
✅ Supporting **real-time availability checks** efficiently.  
✅ Handling **race conditions** when multiple users try booking the same slot.

---

## **2️⃣ Solution: Slot Availability Table Schema**
We introduce a **slot_availability** table that stores **time-based slot reservations** and ensures no two bookings overlap.

```sql
CREATE TABLE slot_availability (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    slot_id UUID REFERENCES parking_slots(id) ON DELETE CASCADE,
    booking_id UUID REFERENCES bookings(id) ON DELETE CASCADE,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    UNIQUE (slot_id, start_time, end_time),
    CHECK (start_time < end_time)
);
```

✅ **How It Works:**
- **Each slot** will have an entry for every reserved time range.
- **`CHECK (start_time < end_time)`** ensures valid time ranges.
- **`UNIQUE (slot_id, start_time, end_time)`** prevents duplicate bookings.
- When a **booking is canceled**, the corresponding **slot_availability entry is removed**.

---

## **3️⃣ Preventing Overlapping Bookings**
We need to **ensure that a new booking does not overlap** with an existing one. This can be achieved using **SQL constraints and queries**.

### **🟢 SQL Query to Check for Overlapping Bookings**
Before confirming a new booking, we check if any **conflicting reservations** exist.

```sql
SELECT COUNT(*) 
FROM slot_availability 
WHERE slot_id = :slot_id
AND (
    (:new_start_time < end_time AND :new_end_time > start_time)  -- Overlapping condition
);
```

✅ **If the result is `0`, the slot is available for booking.**  
✅ **If the result is `>0`, the booking request is rejected due to conflict.**

---

## **4️⃣ Handling Recurring Bookings**
For **future and recurring bookings**, we must ensure that **each instance of a recurring reservation does not conflict**.

### **🟡 Example: Booking a Slot Every Monday for 4 Weeks**
A user wants to **reserve a slot every Monday from 10 AM - 12 PM** for the next **4 weeks**.

```sql
INSERT INTO slot_availability (slot_id, booking_id, start_time, end_time)
SELECT :slot_id, :booking_id, start_time, end_time
FROM generate_series(
    '2024-01-15 10:00:00'::timestamp,  -- Start date
    '2024-02-05 10:00:00'::timestamp,  -- End date (4 weeks later)
    '1 week'::interval                 -- Repeat weekly
) AS start_time,
generate_series(
    '2024-01-15 12:00:00'::timestamp,
    '2024-02-05 12:00:00'::timestamp,
    '1 week'::interval
) AS end_time
WHERE NOT EXISTS (
    SELECT 1 FROM slot_availability 
    WHERE slot_id = :slot_id 
    AND (
        start_time < end_time AND end_time > start_time
    )
);
```

✅ **How This Prevents Conflicts:**
- **Generates multiple time slots** based on a recurrence pattern.
- **Checks for conflicts before inserting records.**
- **If conflict exists, the transaction fails.**

---

## **5️⃣ Locking Mechanism for High Concurrency**
For **real-time booking systems**, race conditions occur when **multiple users try to book the same slot simultaneously**.

### **🔵 Solution: Pessimistic Locking**
Before confirming a booking, we **lock the slot row** to prevent race conditions.

```sql
BEGIN;
SELECT * FROM slot_availability WHERE slot_id = :slot_id 
AND (:new_start_time < end_time AND :new_end_time > start_time) 
FOR UPDATE;

-- If no conflict, insert booking
INSERT INTO slot_availability (slot_id, booking_id, start_time, end_time)
VALUES (:slot_id, :booking_id, :new_start_time, :new_end_time);
COMMIT;
```

✅ **Ensures that two users cannot book the same slot simultaneously.**

---

## **6️⃣ Indexing for Fast Lookups**
To support **real-time search queries** for available slots, we optimize the database using **indexes**.

```sql
CREATE INDEX idx_slot_availability_time ON slot_availability (slot_id, start_time, end_time);
```

✅ **Improves query performance for availability lookups.**  
✅ **Speeds up range queries for real-time slot searches.**

---

## **🚀 Final Summary**
| **Feature** | **Implementation** |
|------------|------------------|
| **Prevent Overlapping Bookings** | SQL Query with `WHERE NOT EXISTS` condition |
| **Recurring Bookings** | `generate_series()` to create time slots |
| **Concurrency Handling** | Pessimistic Locking with `FOR UPDATE` |
| **Performance Optimization** | Indexing on `(slot_id, start_time, end_time)` |

---

This **design guarantees** that no two users can **book the same slot at the same time** while **supporting future and recurring bookings efficiently**. 🚀

Would you like to add **dynamic pricing or automated slot release for no-shows**? 😃