
Your high-level system design for a **scalable movie and show booking system** like BookMyShow is quite **comprehensive** and **well-structured** for an FAANG-level interview. Here’s my review with a **scoring breakdown** based on **clarity, scalability, completeness, and fault tolerance**:

---

### **Score: 8.5/10**
#### **Breakdown:**
1. **Functional Requirements (9/10)**
    
    - 🔸 **Edge cases like seat locking and concurrent seat selection handling are missing.**

2. **Non-Functional Requirements (8.5/10)**
    - 🔸 **No explicit mention of rate limiting, throttling, and API gateway security against abuse (e.g., bot protection).**
    - 🔸 **No mention of disaster recovery or automated failover handling.**

3. **Services (9/10)**
   
    - 🔸 **A centralized session management strategy (like Redis for token/session storage) could be mentioned.**
    - 🔸 **A more detailed payment handling workflow, including rollback mechanisms for failed transactions, could be added.**

4. **Technology Choices (8/10)**
    - 🔸 **Lack of justification for why Cassandra is chosen over Elasticsearch for search-based analytics.**
    - 🔸 **Could mention Redis-based distributed locking for concurrent seat booking prevention.**


---

### **Suggestions to Improve:**
1. **Concurrency Control for Seat Booking**
    - Use **Redis-based distributed locking** or PostgreSQL transactions to **avoid double bookings**.
    - Implement **timeout-based seat reservation** (like Amazon checkout timers).

2. **Rate Limiting & Throttling**
    - Use **API Gateway (Kong, Nginx, or AWS API Gateway) with rate limits** to prevent abuse.
    - Implement **bot protection (CAPTCHA, IP tracking, Device Fingerprinting).**

3. **Disaster Recovery & High Availability**
    - Introduce **Multi-region deployments with active-active or active-passive failover**.
    - Implement **geo-replication** for databases (especially Postgres & Mongo).

4. **Payments Handling & Rollback**
    - If a payment fails, ensure the **seat is released immediately**.
    - Consider **two-phase commit (2PC)** or **outbox pattern** to ensure eventual consistency.

5. **Search Optimization**
    - Instead of **MongoDB**, consider **Elasticsearch** for fast fuzzy searches.
    - Use **Redis caching** for frequently searched queries.

---



they expect you to explain trade-offs, scalability, and failure handling in a structured and interactive manner.

---

### **How FAANG Interviewers Will Evaluate You**
1. **Scalability (40%)** – Can the system handle millions of users in real-time?
2. **Fault Tolerance (20%)** – What happens if a service fails? How does it recover?
3. **Trade-offs (20%)** – Why Kafka and not RabbitMQ? Why Postgres for ACID but not DynamoDB?
4. **Consistency vs Availability (10%)** – Which services need **strong consistency**? Where is **eventual consistency acceptable**?
5. **Real-world Failure Scenarios (10%)** – What if **two users select the same seat at the same time**? What if a **payment fails** after booking?

---

## **What You Need to Improve Before FAANG Interview**
Right now, you have **a great list**, but FAANG interviewers will **expect more structured reasoning** in these areas:

### **1️⃣ Scalability Considerations**
🔹 **Q: Can the system handle 100M users booking tickets at the same time?**  
✔️ **Solution:**
- **Rate Limiting** at API Gateway.
- **Redis-based Distributed Locks** for seat booking.
- **Partitioned databases** (Postgres by city, MongoDB for search).
- **Eventual Consistency** with Kafka.

🔹 **Q: How do you ensure quick search response times?**  
✔️ **Solution:**
- **Elasticsearch for search** (instead of MongoDB).
- **Redis caching** for most-searched movies.
- **Asynchronous updates** to the search index.

---

### **2️⃣ Handling Failures in a Distributed System**
🔹 **Q: What happens if a user’s payment fails?**  
✔️ **Solution:**
- Implement **Two-Phase Commit (2PC)** or **SAGA pattern** to roll back seat selection.
- **Use an Outbox Pattern** to ensure booking updates only happen if payment succeeds.

🔹 **Q: What if a service like “Booking Service” crashes?**  
✔️ **Solution:**
- **Circuit Breaker Pattern** to avoid cascading failures.
- **Retry mechanism with exponential backoff**.
- **Failover replica instances** in different Availability Zones.

---

### **3️⃣ Trade-offs and Justifications**
🔹 **Q: Why Kafka and not RabbitMQ?**  
✔️ **Answer:** Kafka provides **high throughput**, durability, and **better partitioning for scalability**. RabbitMQ is better for **low-latency messaging but doesn’t scale well** for analytics.

🔹 **Q: Why Postgres for bookings instead of DynamoDB?**  
✔️ **Answer:** Postgres ensures **ACID transactions**, preventing **double booking of seats**. DynamoDB is great for high-scale reads but **lacks strong transaction guarantees** without additional overhead.

🔹 **Q: Why CQRS for search?**  
✔️ **Answer:** Read-heavy workloads (available shows, pricing) need **fast denormalized views**, while booking is a **write-heavy transactional workflow**.

---

### **4️⃣ Real-world Scenarios & Edge Cases**
🔹 **Q: Two users select the same seat at the same time—how do you prevent double booking?**  
✔️ **Solution:**
- Use **optimistic locking in Postgres** (UPDATE WHERE status = 'available' RETURNING id).
- **Distributed Locking with Redis** (set NX key for seat lock).

🔹 **Q: How do you handle bot attacks booking tickets?**  
✔️ **Solution:**
- Implement **Rate Limiting** at API Gateway.
- Use **ReCAPTCHA** for high-volume users.
- **Analyze user behavior** with Kafka & Flink (e.g., flag users booking 100+ tickets in 5 seconds).

---

### **🚀 Next Steps Before Your FAANG Interview**
✅ Prepare for **real-world failures and concurrency handling**.  
✅ Be ready to **justify trade-offs** between different technologies.  
✅ Focus on **fault tolerance, retries, failover mechanisms**.  
✅ **Practice scalability questions**—how do you handle **10M concurrent users?**  
✅ Prepare **API designs** (REST endpoints, payloads, rate limits).

---

### **🚀 What I Can Help You With Next**
1️⃣ **Deep-dive on API Design** (e.g., REST endpoints, payloads, rate limits).  
2️⃣ **Schema Design** (ER Diagrams, indexing strategies).  
3️⃣ **Handling Failures & Recovery** (SAGA, Circuit Breaker, Retry Strategies).  
4️⃣ **Mock FAANG Interview for System Design** (with real-time feedback).







## Online Movie or show booking system like BookMyShow

**Functional**
- user should be able to book the show in give city, theater
- should be be able to see available slots, seats and price 
- user should be able to select seat , no. seats and book that
- user should be able to book for future, cancel 
- online payments
- should be able to see booking history
- aids,home feed to show trendings and suggestions
- notifications
- referrals, coupens 
- rating system for the show and theater
- customer support system

**Non-Functional**
- secured system (centerlised auth service)
- Scalable and fault tolerant services
- ETL/ELT service for analtics purpose (pysaprk + Flink)
- streaming data processing to prediction scams/faults bookings/ bot booking
- Vault for secrets and configs managment
- stateless services , env agnostic so we can scale and mmigrate to differe envs after testing without build
- Docker and K8s
- Different VPC and private subnets for deployments 
- security groups to not allow outside requests in the system
- Service discovery - for load balancer and service discovery and control over inter service calls
- EFK/ELK for logs monitoring
- services monitoring - using promethious + grafana + heart beats
- actuator integrations in service to check health and other matrics
- Role based access for users
- IAM role for services and thrid party access
- CICD using jenkins -> canary deployments or blue-green


**Service**
- User details and Authentication service
- Home Feed service: home feed based on user location, history and trends and some aids
- Booking service: to book/cancel bookings-
- PricePrediction: to calculation prices and serge charges etc
- SearchService: to fetch best theaters, movies nearby and available slots, expected prices
- AidsService: to manage aids
- Payments service
- Notification Service(FanOut service)
- rating service
- Referrals and coupon service
- Customer service -> to create tickets and chat with back-office


**Technologies**
- Redis for caches- home feed, available slots, fast fetch booking details, for already searched queries or shows
- Postgres to support ACID and prevents concurrent bookings -> user details, bookings, Coupens, refferals, show_slots, seats_slots , partition by city
- MongoDB for heavy reads -> to show available theaters, movies and other details, partition by city
- Cassandra -> to capture click events, searches, booking history, user activities and other analytics details
- Kafka - to have event drive architecture , to support scale, fault-tolerant 

Architectural Patterns:
- centralised authentication
- SAGA
- Circuit breaker
- CQRS
- API-Gateway - auth, static content like some videos , images etcs
- Distributed and streaming processing using kafka-spark and flink -> fraud predictions preproceesing, analytics, etc

**Functional**
- user should be able to book the show in give city, theater
- should be be able to see available slots, seats and price
- user should be able to select seat , no. seats and book that
- user should be able to book for future, cancel
- online payments
- should be able to see booking history
- aids,home feed to show trendings and suggestions
- notifications
- referrals, coupens
- rating system for the show and theater
- customer support system


### APIS:- 
headers -> {JWT token, device_id, language, location, x-parameters}
- api/v1/users
- api/v1/users/{userId} -> user details 
- api/v1/login -> user details with token + role + refresh token 
- api/v1/signup -> user details with token + role + refresh token
- api/v1/auth/refresh-token -> To refresh an expired token.
- api/v1/theaters
- api/v1/theaters/{theaterId}?date= -> list of shows and theater details
- 
- api/v1/home/feeds  -> will return list of trending shows nearby and some aids 
- api/v1/home/carousels -> upcomming shows and promotional cards
- api/v1/shows?location=''&date=''&search='' // will return list of shows based on query params
- api/v1/shows/{id}?theater_id=1&location=''&date=''  // to check available slots and timings
- api/v1/users/{id}/bookings/init -> POST :
  - {theater_id, location, date, no.seats, seats, promocode} -> reponses to get estimated prices {request_id, estimated_price, status}
- ✅ api/v1/shows/{id}/seats/lock -> POST {theater_id, seats} -> Locks seats for 5 minutes.
- api/v1/bookings/{booking_id}/payment -> POST -> {request_id, selectedOption, promocode} -> {payment_url, status:Pending, bookingId}
- api/v1/bookings -> list of bookings with pagination
- api/v1/bookings/{booking_id} -> get complete detail of booking  
- api/v1/bookings/{booking_id} -> DELETE request to cancel the booking -> 200
- api/v1/rating POST -> {booking_id, stars, message, images, like} -> 201
- ✅ api/v1/bookings/{booking_id}/payment/status -> GET




Schema-

userDetails(role and login)
Roles and permissions tables to control access
profile(more details of users)

Theaters (id, title, descriptions, images, location, lat,long, city, owner_id  ) partition by pincode + country 
```sql
userDetails (
  id UUID PRIMARY KEY, 
  email VARCHAR UNIQUE NOT NULL, 
  phone VARCHAR UNIQUE NOT NULL,
  password_hash TEXT NOT NULL, 
  salt TEXT NOT NULL, 
  role_id UUID REFERENCES roles(id), 
  created_at TIMESTAMP DEFAULT now(), 
  updated_at TIMESTAMP DEFAULT now()
)

roles (id UUID PRIMARY KEY, role_name TEXT UNIQUE NOT NULL)
permissions (id UUID PRIMARY KEY, role_id UUID REFERENCES roles(id), permission TEXT NOT NULL)

CREATE INDEX idx_theaters_location ON theaters USING GIST (point(lat, long));
theaters (
  id UUID PRIMARY KEY, 
  title TEXT, 
  description TEXT, 
  images TEXT[], 
  location TEXT, 
  lat DECIMAL, 
  long DECIMAL, 
  city TEXT, 
  owner_id UUID REFERENCES userDetails(id), 
  created_at TIMESTAMP DEFAULT now()
)

```

Shows (id, name, description, rating, images,  isActive, start_time ,end_time, start_date, end_date, weekdaysAvailabilities, )
Shows availabilities (id,show_id, start_time ,end_time, start_date, end_date, weekdaysAvailabilities, availableSeats, bookedSeats ) //this will have all available slots for future bookings
slotAvailabilities(id, show_id, show_availability_id, seatNo, row, status, type(premium vip/ normal) )
slotAvailabilities(id, show_id, show_availability_id, seatNo, row, status, type(premium vip/ normal) ) // will be in redis 
```sql
shows (
  id UUID PRIMARY KEY, 
  name TEXT NOT NULL, 
  description TEXT, 
  rating FLOAT, 
  images TEXT[], 
  isActive BOOLEAN DEFAULT TRUE, 
  start_time TIME NOT NULL, 
  end_time TIME NOT NULL, 
  start_date DATE NOT NULL, 
  end_date DATE NOT NULL, 
  weekdaysAvailabilities TEXT[], 
  theater_id UUID REFERENCES theaters(id)
)

shows_availabilities (
  id UUID PRIMARY KEY, 
  show_id UUID REFERENCES shows(id), 
  start_time TIME NOT NULL, 
  end_time TIME NOT NULL, 
  start_date DATE NOT NULL, 
  end_date DATE NOT NULL, 
  weekdaysAvailabilities TEXT[], 
  availableSeats INT, 
  bookedSeats INT
)


slot_availabilities (
  id UUID PRIMARY KEY, 
  show_id UUID REFERENCES shows(id), 
  show_availability_id UUID REFERENCES shows_availabilities(id), 
  seat_no VARCHAR, 
  row VARCHAR, 
  status TEXT CHECK (status IN ('available', 'booked', 'locked')), 
  type TEXT CHECK (type IN ('premium', 'vip', 'normal')), 
  version INT DEFAULT 0
)

```
BookingEnqueries(requestId,userId, theater_id. show_id, slot_ids, totalPrice) // this will created whenever request for enquire to see price and details -> will be helpful for to trackuseractivitties and 
Fast booking if user proceed further. price calculation and all request details will be in cache and db.
will be used to send notifications if user does not book. and as cart in case app is closed or crashes
bookings (bookingId, theater_id. show_id, slot_ids, totalPrice)
payments (paymentid, utr, bookingId, coupon_id, raised_payment, total_payment, status )

```sql
booking_enquiries (
  request_id UUID PRIMARY KEY, 
  user_id UUID REFERENCES userDetails(id), 
  theater_id UUID REFERENCES theaters(id), 
  show_id UUID REFERENCES shows(id), 
  slot_ids UUID[], 
  total_price DECIMAL, 
  status TEXT CHECK (status IN ('Pending', 'Expired', 'Converted')), 
  created_at TIMESTAMP DEFAULT now(),
  expires_at TIMESTAMP,  -- Auto-expire after 10-15 minutes
  last_accessed_at TIMESTAMP DEFAULT now()
)


bookings (
  booking_id UUID PRIMARY KEY, 
  user_id UUID REFERENCES userDetails(id), 
  theater_id UUID REFERENCES theaters(id), 
  show_id UUID REFERENCES shows(id), 
  slot_ids UUID[], 
  total_price DECIMAL, 
  created_at TIMESTAMP DEFAULT now()
)


payments (
  payment_id UUID PRIMARY KEY, 
  utr TEXT UNIQUE, 
  booking_id UUID REFERENCES bookings(booking_id), 
  coupon_id UUID REFERENCES coupons(id), 
  raised_payment DECIMAL, 
  total_payment DECIMAL, 
  status TEXT CHECK (status IN ('Pending', 'Completed', 'Failed')), 
  failure_reason TEXT, 
  retries INT DEFAULT 0, 
  created_at TIMESTAMP DEFAULT now()
)

```
feedback (bookingId, user_id, rating, marks)
referralEntities(id, referral_code, referredBy, userId, expiry_date)
coupons (id, coupon_code, expiryDate, maxCount, count, discountPercentage, maxDiscount, )





Distributed processing for business: - will add later 

Scalability, fail-over mechanisms and fault-tolerant : will be discussed

real-world failures and concurrency handling:  will be discussed

Trade-offs and Edge Cases: 
🔹 **Q: Why Kafka and not RabbitMQ?**  
✔️ **Answer:** Kafka provides **high throughput**, durability, and **better partitioning for scalability**. RabbitMQ is better for **low-latency messaging but doesn’t scale well** for analytics.

🔹 **Q: Why Postgres for bookings instead of DynamoDB?**  
✔️ **Answer:** Postgres ensures **ACID transactions**, preventing **double booking of seats**. DynamoDB is great for high-scale reads but **lacks strong transaction guarantees** without additional overhead.

🔹 **Q: Why MongoDb Vs ElasticSearch?**  
✔️ **Answer:** 

🔹 **Q: Why CQRS for search?**  
✔️ **Answer:** Read-heavy workloads (available shows, pricing) need **fast denormalized views**, while booking is a **write-heavy transactional workflow**.

🔹 **Q: Why SAGA for search?**  
✔️ **Answer:** 


🔹 **Q: Why Cassandra for events and history?**  
✔️ **Answer:** 


🔹 **Q: how real-world failures and concurrency handling will be done and how it will work?**  
✔️ **Answer:** 


Deployments & Security
✔ Centralized Auth Service (OAuth2 + JWT) → Secure role-based access.
✔ RBAC (Role-Based Access Control) → Users & admins have separate permissions.
✔ CICD Pipelines (Jenkins) → Automates deployment with rollback options.
✔ Blue-Green & Canary Deployments → Ensures zero downtime updates.
✔ Security Best Practices:

Use private VPCs & security groups to restrict access.
IAM Roles & Least Privilege Access for microservices.
Vault for secret management (tokens, database creds).
Monitoring & Logging
✔ ELK Stack (Elasticsearch, Logstash, Kibana) → Real-time log monitoring & alerts.
✔ Prometheus + Grafana → Service health monitoring & dashboards.
✔ Distributed Tracing (Jaeger or Zipkin) → Tracks requests across microservices.
Future enhancements:  will be discussed



---

### **Success Status Codes:**
- **200 OK** → The request was successful, and the server returned the requested data.
- **201 Created** → The request was successful, and a new resource was created.
- **204 No Content** → The request was successful, but there is no content to return (commonly used for DELETE operations).

---

### **Client Error Status Codes:**
- **400 Bad Request** → The server cannot process the request due to invalid syntax, missing parameters, or a client-side error.
- **401 Unauthorized** → Authentication is required, and the request lacks valid credentials.
- **403 Forbidden** → The server understands the request but refuses to authorize it (even if authenticated, access is not allowed).
- **404 Not Found** → The requested resource could not be found on the server.
- **405 Method Not Allowed** → The request method (GET, POST, PUT, DELETE) is not allowed for the requested resource.
- **409 Conflict** → The request could not be processed due to a conflict in the current state of the resource (e.g., attempting to create a duplicate entry).
- **429 Too Many Requests** → The user has sent too many requests in a given time frame (rate limiting).

---

### **Server Error Status Codes:**
- **500 Internal Server Error** → A generic error indicating the server encountered an unexpected condition.
- **502 Bad Gateway** → The server acting as a gateway received an invalid response from an upstream server.
- **503 Service Unavailable** → The server is temporarily unavailable, usually due to maintenance or overload.
- **504 Gateway Timeout** → The server acting as a gateway did not receive a timely response from an upstream server.

