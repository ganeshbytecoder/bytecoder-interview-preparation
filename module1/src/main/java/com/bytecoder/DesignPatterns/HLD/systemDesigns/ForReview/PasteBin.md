Here's a detailed **system design plan** for **Pastebin.com (or Bit.ly)** to handle **millions of users** while ensuring **robustness, scalability, and security**.

---

# **System Design: Pastebin / URL Shortener**
## **1. System Requirements**
### **Functional Requirements:**
- Users can create short links or paste text snippets.
- Each short URL / paste should be unique and retrievable.
- Users can delete or update their pastes (optional).
- Expiry for pastes (e.g., auto-delete after X days).
- Private and public pastes (optional authentication for private).
- API access for programmatic paste creation.

### **Non-Functional Requirements:**
- High Availability: Ensure uptime even under failures.
- Low Latency: Shorten URL in **<10ms**; retrieval **<50ms**.
- Scalability: Handle **millions of requests per second (RPS)**.
- Security: Prevent **abuse, spam, and unauthorized access**.
- Analytics: Track click counts and expiration.

---

## **2. System Capacity Estimation**
### **Traffic Assumptions:**
- **100 million users**.
- **10 million daily pastes**.
- **1 billion daily read requests** (100 reads per paste).
- **Average paste size**: **10 KB**.
- **Average URL size**: **100 bytes**.

### **Storage Calculation:**
- **Writes per day:**  
  \(10M \times 10KB = 100GB/day\)  
  \(100GB/day \times 365 = 36.5TB/year\)

- **Reads per day:**  
  \(1B \times 100B = 100GB/day\)  
  Most reads will be **cached**.

---

## **3. High-Level Architecture**
### **Components:**
1. **Load Balancer (Nginx, AWS ALB)** → Distributes traffic.
2. **Application Servers (Node.js, Java Spring Boot, Go)** → Handles business logic.
3. **Database (Cassandra, PostgreSQL, DynamoDB, MySQL)** → Stores pastes.
4. **Caching (Redis, Memcached, CDN)** → Reduces database load.
5. **Queue (Kafka, RabbitMQ, AWS SQS)** → Asynchronous processing.
6. **Analytics (BigQuery, Elasticsearch, Snowflake)** → Logs click events.

---

## **4. Database Schema**
### **Paste Table (for text pastes)**
```sql
CREATE TABLE pastes (
    id VARCHAR(10) PRIMARY KEY,
    content TEXT,
    created_at TIMESTAMP,
    expires_at TIMESTAMP,
    user_id VARCHAR(50),  -- Nullable for anonymous pastes
    visibility ENUM('public', 'private')
);
```
### **Short URL Table (for URL shortening)**
```sql
CREATE TABLE short_urls (
    short_id VARCHAR(10) PRIMARY KEY,
    long_url TEXT,
    created_at TIMESTAMP,
    expires_at TIMESTAMP,
    click_count INT DEFAULT 0
);
```
### **Analytics Table (Tracking Clicks)**
```sql
CREATE TABLE url_clicks (
    short_id VARCHAR(10),
    clicked_at TIMESTAMP,
    user_ip VARCHAR(50),
    user_agent TEXT,
    country VARCHAR(50),
    PRIMARY KEY (short_id, clicked_at)
);
```

---

## **5. URL Shortening Logic**
### **Short ID Generation Strategies**
1. **Base62 Encoding (Preferred)**
    - Encode unique **incremental ID** (or hash) in **Base62** (`[a-zA-Z0-9]`).
    - Converts numbers into a shorter string.
   ```python
   def base62_encode(num):
       chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
       base = len(chars)
       encoded = ""
       while num > 0:
           encoded = chars[num % base] + encoded
           num //= base
       return encoded
   ```

2. **UUID + Hashing**
    - Generate **UUID** and take the first **6-8 chars** of an **MD5/SHA256 hash**.

3. **Random Collision-Free Generation**
    - Ensure **uniqueness** by checking in DB before returning.

---

## **6. Read Optimization (50GB/s Handling)**
### **Caching Strategy**
- **Redis (10-20x faster than DB)**
    - Store **recently accessed pastes** (`short_id → content`).
    - Use **Least Recently Used (LRU) eviction policy**.

- **Content Delivery Network (CDN)**
    - Cache **static pastes** (e.g., Cloudflare, Akamai, AWS CloudFront).

- **Database Read Replicas**
    - Distribute read load across multiple **replicas**.

---

## **7. Write Optimization (2GB/s Handling)**
### **High-Throughput Database Writes**
- **Sharded Storage (Cassandra, DynamoDB, MySQL Partitioning)**
    - Shard by **short ID prefix** to distribute write load.

- **Batch Writes**
    - **Buffer writes** using Kafka/SQS to **bulk insert**.

- **Eventual Consistency for Analytics**
    - Store click events in **Kafka** and process asynchronously.

---

## **8. Security & Abuse Prevention**
### **Rate Limiting (Prevent Spam)**
- Use **Redis** or **API Gateway** (e.g., Kong, AWS API Gateway).
- Example:
    - **Free users:** **10 pastes/min**.
    - **Premium users:** **100 pastes/min**.

### **Bot Prevention**
- **reCAPTCHA** for anonymous users.
- **IP Blacklisting** for spam sources.
- **Honeypot fields** to catch bots.

### **Data Protection**
- **Encryption (AES-256) for Private Pastes**.
- **Access Tokens** for private URLs.
- **TLS (HTTPS) for all requests**.

---

## **9. Scaling Strategy**
| **Component**     | **Scaling Approach** |
|-------------------|----------------------|
| **Application Servers** | Horizontal scaling with Kubernetes (K8s) |
| **Database** | Read replicas, sharding |
| **Cache** | Redis Cluster (Distributed Caching) |
| **Storage** | S3 for large pastes, SSD for hot data |
| **Load Balancer** | Nginx, AWS ALB |
| **Logging** | ELK Stack, CloudWatch, Prometheus |

---

## **10. Analytics & Logging**
- **Real-time analytics** using **Kafka → BigQuery**.
- **Log Monitoring** using **Elasticsearch + Kibana**.
- **Alerting** for errors and slow queries.

---

## **11. Tech Stack Recommendation**
| **Layer** | **Technology** |
|-----------|--------------|
| **Frontend** | React, Next.js |
| **Backend** | Node.js (Fastify) / Go / Java Spring Boot |
| **Database** | PostgreSQL, DynamoDB, Cassandra |
| **Caching** | Redis, CDN (Cloudflare) |
| **Queue** | Kafka, RabbitMQ |
| **Storage** | S3, HDFS |
| **Monitoring** | Prometheus, Grafana |
| **Deployment** | Docker, Kubernetes (K8s) |

---

## **12. API Design**
### **Shorten URL API**
```http
POST /shorten
Content-Type: application/json

{
    "long_url": "https://example.com/some-long-url",
    "expires_in": 30
}
```
**Response:**
```json
{
    "short_url": "https://pb.in/abc123"
}
```

### **Retrieve Paste API**
```http
GET /p/abc123
```
**Response:**
```json
{
    "content": "This is a paste.",
    "created_at": "2025-03-04T12:00:00Z"
}
```

---

# **Final Thoughts**
This **scalable, robust, and secure** design ensures:
- **Low-latency reads** with caching & replicas.
- **High-throughput writes** using partitioning & batch processing.
- **Spam prevention & security** measures.
- **Scalability** with K8s, sharding, and CDN caching.

Would you like a **detailed diagram** or **cost estimation** for cloud deployment? 🚀