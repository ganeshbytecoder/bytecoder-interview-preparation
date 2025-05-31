* https://github.com/tssovi/grokking-the-object-oriented-design-interview/blob/master/object-oriented-design-case-studies/design-cricinfo.md

- do we have to build whole system from scratch? or do we have to reuse existing system?
- How much scale do we have to support?
- scalability
- performance
- security
- availability and consistency
- peek scalability / bottle necks and tradeoffs / performace improvements / future
- load calculation ?

# Functional

- Core business functionalities covering all the cases
- user should be able to book for future, cancel
- Payment system for (one-time, recursive and subscription )
- user click events for analytics
- should be able to see history
- home feed to show trending and suggestions
- notifications
- aids
- referrals, coupons
- rating system for product and services
- customer support system
- 

# non-functional requirements

- Microservices architecture for highly scalable, secured , fault-tolerant to support 1M users
- high throughput and low latency and High Performance
- secured system to handle 1M users
- Devops technologies and strategies for Deployments, monitoring & Security etc
- Analytics Tools and big data processing for business insight
- streaming data processing to prediction scams/faults/ bot

# High-Level Architecture and Services Overview

Users → API Gateway → Auth → Microservices → DBs / MQs

## **System Capabilities: Subscription, Recurring Payments & Booking**

This architecture is designed to support:

1. Users → API Gateway → Auth → Microservices → DBs / MQs (Synchronous Flow)
2. Users → API Gateway → Auth → Producers → Queue (Kafka/SQS) → Consumers and Microservices → DBs / MQs → Return status via WebSocket or Polling (Asynchronous Flow)

- **Flexible Subscription Management**Supports multiple subscription plans (monthly, yearly, etc.) with automated enrollment and renewal.
- **Automated Recurring Payments**Recurring deductions processed seamlessly via background services.
- **Service Booking Management**Enables scheduling and management of appointments for active subscribers.
- **Scalability**
  Architected to scale and support **1 million+ users** efficiently.

---

## **1. High-Level Architectural Services**

### **Core Services**

1. **User & Authentication Service**Manages user accounts, authentication, and authorization.
2. **Subscription Service**Handles subscription plans, user enrollments, and renewals.
3. **Payment Service**Processes payments, handles recurring deductions, transactions, and refunds.
4. **Booking Service**Manages user bookings and service appointments for subscribed users.
5. **Notification Service (Fan-out)**Sends real-time alerts, confirmations, reminders, and updates to users.
6. **Background Job Service**Executes asynchronous tasks such as recurring payment deductions and scheduled jobs.
7. **Search Service**Enables search functionality across services and content.
8. **Home Feed Service**Generates and serves personalized content based on user preferences.
9. **Aids Service**Provides support and assistance-related features.
10. **Rating & Review Service**Manages user ratings, reviews, and feedback.
11. **Referrals & Coupons Service**Handles referral programs, discounts, and promotional offers.
12. **Customer Service**
    Supports ticket creation and user communication with the back-office team.

### **Infrastructure Components**

13. **Database & Caching Layer**Stores and retrieves user data, subscription details, payments, and booking info efficiently. Incorporates caching to improve performance and reduce database load.
14. **Queue System (Kafka/SQS)**Decouples services and ensures efficient processing of background tasks and inter-service communication.
15. **API Gateway**
    Routes incoming API requests to the appropriate microservices and handles request validation, rate limiting, and monitoring.

---

## Some important Work flow of services

### **Payment Deduction Handling Process**

1. **Subscription Signup:**

   - User selects a plan → enters payment details → payment processed.
   - If successful, `next_billing_date = start_date + duration`.
2. **Recurring Payment Deduction:**

   - **Cron Job (every 24 hours):** Checks subscriptions with `next_billing_date <= now()`.
   - If due, triggers a **payment request**.
   - If successful, updates `next_billing_date`.
   - If failed, retries **3 times in 7 days** before marking as **'failed'**.
3. **Grace Period Handling:**

   - **Grace period of 7 days** before subscription is marked as **'expired'**.
   - Email notifications sent for failed payments.

---

### **5. Booking/order Service flow**

#### **Booking Flow**

1. User selects a service → Checks **active subscription**.
2. If valid, creates **booking entry**.
3. Sends **confirmation notification**.
4. User can **cancel/reschedule** within allowed timeframe.

#### **Handling Scalability**

- **Rate Limiting:** Prevent spam booking requests.
- **Distributed Locking:** Prevents double-booking (Redis-based lock).
- **Event-Driven Updates:** Booking status updates via Kafka.

---

### **Implementing Payment Retries (3 Retries in 7 Days) Approach:**

- Store **failed payments** in the database with a retry count.
- Use a **scheduled job** to retry payments every day.
- If a payment **fails 3 times in 7 days**, mark it as **'failed'**.

#### **Spring Boot Service for Payment Retries**

```java
@Service
public class PaymentRetryService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentGatewayService paymentGatewayService;

    @Autowired
    private EmailService emailService;

    private static final int MAX_RETRIES = 3;
    private static final int RETRY_PERIOD_DAYS = 7;

    @Scheduled(cron = "0 0 2 * * ?") // Runs every day at 2 AM
    @Transactional
    public void retryFailedPayments() {
        List<PaymentRetry> failedPayments = paymentRepository.findFailedPayments();

        for (PaymentRetry payment : failedPayments) {
            if (payment.getPaymentAttempts() < MAX_RETRIES) {
                boolean success = processPayment(payment);

                if (success) {
                    payment.setStatus("successful");
                } else {
                    payment.setPaymentAttempts(payment.getPaymentAttempts() + 1);
                    payment.setLastAttempt(LocalDateTime.now());
                
                    if (payment.getPaymentAttempts() >= MAX_RETRIES) {
                        payment.setStatus("failed");
                        emailService.sendPaymentFailureNotification(payment.getUser());
                    }
                }
                paymentRepository.save(payment);
            }
        }
    }

    private boolean processPayment(PaymentRetry payment) {
        try {
            return paymentGatewayService.chargeUser(payment.getUser(), payment.getSubscription().getPlan().getPrice());
        } catch (Exception e) {
            return false;
        }
    }
}
```

---

### **Grace Period Handling Approach:**

- **Monitor subscriptions** that have failed payments but are within the **7-day grace period**.
- If payment is not successful **after 7 days**, mark the subscription as **expired**.
- **Send email reminders** on Day 1, 3, and 5.

---

#### **Spring Boot Scheduled Job for Grace Period Handling**

```java
@Service
public class GracePeriodService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private EmailService emailService;

    private static final int GRACE_PERIOD_DAYS = 7;

    @Scheduled(cron = "0 0 3 * * ?") // Runs every day at 3 AM
    @Transactional
    public void checkGracePeriod() {
        List<UserSubscription> expiringSubscriptions = subscriptionRepository.findExpiringSubscriptions();

        for (UserSubscription subscription : expiringSubscriptions) {
            LocalDateTime dueDate = subscription.getNextBillingDate();
            long daysSinceFailure = ChronoUnit.DAYS.between(dueDate, LocalDateTime.now());

            if (daysSinceFailure >= GRACE_PERIOD_DAYS) {
                subscription.setStatus("expired");
                subscriptionRepository.save(subscription);
                emailService.sendSubscriptionExpiredNotification(subscription.getUser());
            } else {
                // Send reminders on Day 1, 3, and 5
                if (daysSinceFailure == 1 || daysSinceFailure == 3 || daysSinceFailure == 5) {
                    emailService.sendGracePeriodReminder(subscription.getUser(), GRACE_PERIOD_DAYS - daysSinceFailure);
                }
            }
        }
    }
}
```

---

### **4. How This Works Together**

1. **Failed Payment Handling**

   - If a payment **fails**, it is stored in `payment_retries` with `payment_attempts = 0`.
   - A **scheduled job runs daily at 2 AM** to retry payments **up to 3 times** in 7 days.
   - If **all 3 retries fail**, subscription is **marked as failed**.
   - An **email notification** is sent.
2. **Grace Period Handling**

   - If a user’s subscription payment fails, they get a **7-day grace period**.
   - During this period, **reminder emails** are sent on **Day 1, 3, and 5**.
   - If the payment is not made within **7 days**, the subscription is **marked as expired**.

---

# Architectural Patterns and Technologies

### **Technologies and Architectural Patterns**

#### **Technologies**

1. **Redis (Caching Layer)** - Used for caching frequently accessed data such as home feeds, available slots, fast-fetch booking details, and previously searched queries or shows.
2. **PostgreSQL (Relational Database)** - Ensures ACID compliance and prevents concurrent bookings. Stores critical data such as user details, bookings, coupons, referrals, show slots, and seat slots. Data is partitioned by city for optimized query performance.
3. **MongoDB (Document Database)** - Handles heavy read and write operations efficiently. Used for displaying available theaters, movies, and other related details. Data is partitioned by city to improve scalability.
4. **Cassandra (Time-Series & Event Data Store)** - Captures high-velocity data such as click events, searches, booking history, user activities, and analytics. Designed for high availability and write-intensive workloads.
5. **Kafka (Event-Driven Messaging System)** - Supports an event-driven architecture, enabling scalable and fault-tolerant asynchronous communication between microservices.
6. **Elasticsearch (Search & Analytics Engine)** - Provides full-text search capabilities for searching movies, theaters, and user-generated content. Also used for log aggregation, real-time monitoring, and analytics.

#### **Architectural Patterns**

1. **Centralized Authentication** - Ensures a unified authentication mechanism for secure access across services.
2. **Horizontal Scaling** - Uses load balancers and auto-scaling strategies to efficiently handle increasing user demand.
3. **Saga or Outbox Pattern** - Implements distributed transactions to maintain consistency across microservices.
4. **Event Sourcing** - Stores all state changes as a sequence of events, ensuring traceability and auditability.
5. **Circuit Breaker** - Prevents cascading failures by detecting service failures and restricting requests when needed.
6. **CQRS (Command Query Responsibility Segregation)** - Separates read and write operations to optimize performance and scalability.-using PostgreSQL Read Replicas or different db like mongodb or Elastic search
7. **API Gateway** - Manages authentication, request routing, and serves static content such as videos and images.
8. **Distributed & Stream Processing (Kafka, Spark, Flink)** - Processes real-time data streams for use cases such as fraud detection, pre-processing, and analytics.

# APIs and Schema-

## **2. Database Schema**

We'll use **PostgreSQL** for transactional data and **Redis** for caching frequently accessed data.

### **User Table**

``BaseEntity(created_at, updated_at, created_by, updated_by, is_active, is_deleted) ``

```sql
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    phone VARCHAR(20),
    password_hash VARCHAR(255),
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);
```

### **Subscription Plans Table**

```sql
CREATE TABLE subscription_plans (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) UNIQUE NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    duration INTERVAL NOT NULL, -- e.g., '1 month', '1 year'
    currency VARCHAR(10) DEFAULT 'USD',
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);
```

### **User Subscriptions Table**

```sql
CREATE TABLE user_subscriptions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    plan_id UUID REFERENCES subscription_plans(id) ON DELETE CASCADE,
    status VARCHAR(20) CHECK (status IN ('active', 'cancelled', 'expired')) DEFAULT 'active',
    start_date TIMESTAMP DEFAULT now(),
    next_billing_date TIMESTAMP,
    payment_method VARCHAR(255), -- e.g., 'credit_card', 'paypal'
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);
```

or

### **Bookings Table**

```sql
CREATE TABLE bookings (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    subscription_id UUID REFERENCES user_subscriptions(id) ON DELETE CASCADE,
    service_name VARCHAR(255) NOT NULL,
    booking_time TIMESTAMP NOT NULL,
    status VARCHAR(20) CHECK (status IN ('pending', 'confirmed', 'cancelled', 'completed')) DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);
```

or

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

bookings (
  booking_id UUID PRIMARY KEY, 
  user_id UUID REFERENCES userDetails(id), 
  theater_id UUID REFERENCES theaters(id), 
  show_id UUID REFERENCES shows(id), 
  slot_ids UUID[], 
  total_price DECIMAL, 
  created_at TIMESTAMP DEFAULT now()
)

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

```

### **Payments Table**

```sql
CREATE TABLE payments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    subscription_id UUID REFERENCES user_subscriptions(id) ON DELETE CASCADE,
    amount DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) CHECK (status IN ('pending', 'successful', 'failed')) DEFAULT 'pending',
    payment_method VARCHAR(255),
    transaction_id VARCHAR(255) UNIQUE,
    created_at TIMESTAMP DEFAULT now()
);
```

### **Database Table for Payment Retries**

```sql
CREATE TABLE payment_retries (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    subscription_id UUID REFERENCES user_subscriptions(id) ON DELETE CASCADE,
    payment_attempts INT DEFAULT 0,
    last_attempt TIMESTAMP DEFAULT now(),
    status VARCHAR(20) CHECK (status IN ('pending', 'successful', 'failed')) DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);
```

---

## **7. API Endpoints**

### **User Authentication**

```http
POST /api/v1/auth/signup
POST /api/v1/auth/login
```

### **Subscription Management**

```http
GET /api/v1/subscriptions/plans
POST /api/v1/subscriptions/subscribe
GET /api/v1/subscriptions/status
POST /api/v1/subscriptions/cancel
```

### **Payment Processing**

```http
POST /api/v1/payments/process
GET /api/v1/payments/history
```

### **Booking Service**

```http
POST /api/v1/bookings/create
GET /api/v1/bookings/my
POST /api/v1/bookings/cancel
```

---

In **Java Spring Boot**, you can handle **payment retries** and **grace period handling** using a combination of:

- **Spring Scheduler** (`@Scheduled`)
- **Retry Mechanism** (Spring Retry / Custom Job)
- **Background Job Processing** (Quartz Scheduler, Resilience4j, or Kafka)
- **Event-Driven Notifications** (Email alerts)

---

# Deployments, monitoring & Security more over Devops tools for microservices

- Vault for secrets and configs management
- SSL/TLS certificates for security
- stateless services , env agnostic so we can scale and migrate to different envs after testing without re-build
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
- API Gateway for static and routing
- reverse proxy and firewall for rate limiting

# Scalability, fail-over mechanisms and fault-tolerant :

- Circuit breaker, autoscaling, load balancer, K8s, docker, API gateway

# real-world failures and concurrency handling:

- multiple zone deployments and locking system at DB and cache level

# Distributed processing for business insights: -

- ETL/ELT service for analtics purpose (pysaprk + Flink)
- streaming data processing to prediction scams/faults bookings/ bot booking

# Future Enhancements:

- Add Multi-tenancy Support (good for SaaS models)
- Offer Gift Cards, Wallet, and Loyalty Points
- Enable WebSockets/SSE for real-time updates on bookings
- Integrate Webhooks for third-party service callbacks
- Provide Audit Logging & GDPR-compliant Data Deletion
- Use GraphQL Gateway for complex client data fetching

# Trade-offs, Bottlenecks , spike hendling, DDOS and security attacks  and Edge Cases or missing cases and inprovements in infra :
