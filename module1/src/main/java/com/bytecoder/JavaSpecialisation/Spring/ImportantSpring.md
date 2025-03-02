### **Triggering Notifications & Pushing Data Between Tables Using Stored Procedures in Spring Boot**

A **Stored Procedure** can be used to **push data from one table to another** and **trigger notifications** when specific conditions are met (e.g., an order is shipped, a bank transaction exceeds a limit, etc.). We achieve this using **database triggers** that call stored procedures.

---

## **1. Use Case: Order Processing Notification**
### **Scenario:**
- When an order’s status is updated to **"Shipped"**, we:
    1. **Move the order data to a history table** (`order_history`).
    2. **Send a notification** to the user.

---

## **2. Step-by-Step Implementation**
### **Step 1: Create Tables**
#### **Orders Table**
```sql
CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    product_name VARCHAR(255),
    order_status VARCHAR(50),
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
#### **Order History Table**
```sql
CREATE TABLE order_history (
    order_id INT,
    user_id INT,
    product_name VARCHAR(255),
    order_status VARCHAR(50),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
#### **Notifications Table**
```sql
CREATE TABLE notifications (
    notification_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

### **Step 2: Create a Stored Procedure to Move Data and Trigger Notifications**
```sql
DELIMITER $$

CREATE PROCEDURE MoveOrderToHistoryAndNotify(IN order_id INT)
BEGIN
    DECLARE user_id INT;
    DECLARE product_name VARCHAR(255);

    -- Fetch order details
    SELECT user_id, product_name INTO user_id, product_name FROM orders WHERE order_id = order_id;

    -- Move the order to history table
    INSERT INTO order_history (order_id, user_id, product_name, order_status, updated_at)
    SELECT order_id, user_id, product_name, order_status, NOW() FROM orders WHERE order_id = order_id;

    -- Insert notification for the user
    INSERT INTO notifications (user_id, message)
    VALUES (user_id, CONCAT('Your order "', product_name, '" has been shipped!'));

    -- Delete the order from main table
    DELETE FROM orders WHERE order_id = order_id;
END $$

DELIMITER ;
```

---

### **Step 3: Create a Trigger to Call the Stored Procedure**
We create a **trigger** that calls the stored procedure whenever an `order_status` changes to `"Shipped"`.

```sql
DELIMITER $$

CREATE TRIGGER OrderShippedTrigger
AFTER UPDATE ON orders
FOR EACH ROW
BEGIN
    IF NEW.order_status = 'Shipped' THEN
        CALL MoveOrderToHistoryAndNotify(NEW.order_id);
    END IF;
END $$

DELIMITER ;
```
✅ **Now, whenever an order is marked as "Shipped", the order moves to the history table, and a notification is triggered.**

---

### **Step 4: Integrating with Spring Boot**
Now, let’s call the stored procedure from **Spring Boot**.

---

### **Spring Boot Implementation**
#### **1. Entity for Orders**
```java
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    
    private Integer userId;
    private String productName;
    private String orderStatus;

    // Getters and Setters
}
```

#### **2. Repository for Calling the Stored Procedure**
```java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Procedure(name = "MoveOrderToHistoryAndNotify")
    void moveOrderToHistoryAndNotify(@Param("order_id") Integer orderId);
}
```

---

#### **3. Service Layer**
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void markOrderAsShipped(Integer orderId) {
        orderRepository.moveOrderToHistoryAndNotify(orderId);
    }
}
```

---

#### **4. Controller to Update Order Status**
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @PutMapping("/{id}/ship")
    public ResponseEntity<String> shipOrder(@PathVariable Integer id) {
        orderService.markOrderAsShipped(id);
        return ResponseEntity.ok("Order marked as shipped and notification sent.");
    }
}
```

---

## **3. Best Use Cases in Fintech & E-commerce**
### **🔹 Fintech Use Cases**
| Use Case | Feature Used | Description |
|----------|-------------|-------------|
| **Real-time Fraud Detection** | Trigger + Stored Procedure | Alert the bank when suspicious transactions occur. |
| **Daily Account Balance Update** | Scheduled Procedure | Update and cache daily balance summaries. |
| **Loan Payment Tracking** | Trigger + Stored Procedure | Move overdue payments to a separate table. |

✅ **Example: Fraud Detection Trigger**
```sql
DELIMITER $$

CREATE TRIGGER FraudCheckTrigger
AFTER INSERT ON transactions
FOR EACH ROW
BEGIN
    IF NEW.amount > 10000 THEN
        INSERT INTO fraud_alerts (account_id, message)
        VALUES (NEW.account_id, 'High-value transaction detected!');
    END IF;
END $$

DELIMITER ;
```

---

### **🔹 E-commerce Use Cases**
| Use Case | Feature Used | Description |
|----------|-------------|-------------|
| **Order Shipment Notification** | Trigger + Stored Procedure | Notify users when an order is shipped. |
| **Customer Segmentation** | Stored Procedure | Categorize customers based on their purchases. |
| **Abandoned Cart Recovery** | Trigger + Stored Procedure | Notify users if they leave items in their cart for too long. |

✅ **Example: Abandoned Cart Trigger**
```sql
DELIMITER $$

CREATE TRIGGER AbandonedCartTrigger
AFTER INSERT ON cart
FOR EACH ROW
BEGIN
    IF TIMESTAMPDIFF(HOUR, NEW.added_at, NOW()) > 24 THEN
        INSERT INTO notifications (user_id, message)
        VALUES (NEW.user_id, 'You have items in your cart. Complete your purchase!');
    END IF;
END $$

DELIMITER ;
```

---

## **4. Conclusion**
✅ **Stored Procedures + Triggers** in Spring Boot allow real-time event handling, such as **notifications and data movement**.  
✅ **Best suited for fintech (fraud alerts, balance updates) and e-commerce (order tracking, abandoned cart recovery).**  
✅ **Spring Boot’s `@Procedure` and Hibernate’s EntityManager** make integration seamless.

Would you like a **GitHub repository** with a complete working example? 🚀



### **Scalable Design for Large Data Volumes in Spring Boot with Stored Procedures, Triggers, and Optimized Queries**

When handling **huge amounts of data**, we need to ensure that our database operations are **efficient, scalable, and performant**. Here’s how we can scale our **Stored Procedures, Triggers, and Data Movement** strategies in **Spring Boot with Hibernate**.

---

## **1. Challenges with Large Data Processing**
1. **Triggers Causing Locking Issues** → Row-level locks can slow down transactions.
2. **Stored Procedures Affecting Performance** → Moving bulk data using procedures can block the database.
3. **Scaling Queries** → Inefficient queries can degrade read performance.
4. **Concurrency Issues** → High traffic can lead to deadlocks and long execution times.
5. **Batch Processing** → Handling millions of rows in a single transaction is expensive.
6. **Notifications at Scale** → Sending real-time alerts without blocking transactions.

---

## **2. Optimizing Stored Procedures for Scalability**
### **✅ 2.1 Use Batch Processing Instead of Single Inserts**
Instead of inserting records one by one, we **bulk process records** at scheduled intervals.

#### **Example: Optimized Bulk Move from Orders to Order History**
```sql
DELIMITER $$

CREATE PROCEDURE MoveOrdersToHistoryBatch(IN batch_size INT)
BEGIN
    DECLARE rows_affected INT;

    REPEAT
        -- Move a batch of orders (e.g., 500 at a time)
        INSERT INTO order_history (order_id, user_id, product_name, order_status, updated_at)
        SELECT order_id, user_id, product_name, order_status, NOW()
        FROM orders WHERE order_status = 'Shipped' LIMIT batch_size;

        -- Delete the moved records in batches
        DELETE FROM orders WHERE order_id IN (SELECT order_id FROM order_history LIMIT batch_size);

        -- Get the affected rows count
        SET rows_affected = ROW_COUNT();
    
    UNTIL rows_affected = 0
    END REPEAT;
END $$

DELIMITER ;
```
✅ **Why This is Scalable?**
- **Processes orders in batches (e.g., 500 at a time)** instead of moving all records at once.
- **Avoids locking the entire table**, improving performance.
- Can be scheduled to run periodically.

---

### **✅ 2.2 Use Asynchronous Queues for Notifications (Instead of Triggers)**
Instead of **directly inserting into the `notifications` table in a trigger**, **use a message queue** like **RabbitMQ or Kafka** to handle large-scale notifications asynchronously.

#### **Step 1: Modify Stored Procedure to Push Messages into a Queue Table**
```sql
DELIMITER $$

CREATE PROCEDURE QueueOrderNotification(IN order_id INT)
BEGIN
    INSERT INTO notification_queue (order_id, user_id, message, status)
    SELECT order_id, user_id, CONCAT('Your order "', product_name, '" has been shipped!'), 'PENDING'
    FROM orders WHERE order_id = order_id;
END $$

DELIMITER ;
```
✅ **Why This is Scalable?**
- Inserts into a queue table instead of immediately processing notifications.
- The `notification_queue` table can be processed asynchronously using **Spring Boot and Kafka/RabbitMQ**.

#### **Step 2: Spring Boot Service to Process Notifications**
```java
@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Transactional
    @Scheduled(fixedRate = 5000) // Runs every 5 seconds
    public void processPendingNotifications() {
        List<NotificationQueue> pendingNotifications = notificationRepository.findPendingNotifications();

        for (NotificationQueue notification : pendingNotifications) {
            sendPushNotification(notification.getUserId(), notification.getMessage());
            notification.setStatus("SENT");
            notificationRepository.save(notification);
        }
    }

    private void sendPushNotification(Integer userId, String message) {
        // Implement external notification service
        System.out.println("Sending notification to User " + userId + ": " + message);
    }
}
```
✅ **Why This is Scalable?**
- Uses **@Scheduled** to process notifications asynchronously.
- **Does not block the database** while sending notifications.
- **Can be scaled with Kafka/RabbitMQ** for distributed processing.

---

## **3. Optimizing Queries & Indexing**
### **✅ 3.1 Use Partitioning for Large Tables**
For huge datasets, partitioning helps distribute data across multiple storage locations.

#### **Partitioning `orders` Table by Order Status**
```sql
ALTER TABLE orders PARTITION BY LIST COLUMNS(order_status) (
    PARTITION p_pending VALUES IN ('Pending'),
    PARTITION p_shipped VALUES IN ('Shipped'),
    PARTITION p_delivered VALUES IN ('Delivered')
);
```
✅ **Why This is Scalable?**
- **Faster queries** since only relevant partitions are scanned.
- **Parallelism** can be used for distributed storage.

---

### **✅ 3.2 Use Indexing for Fast Queries**
Indexes improve the performance of `SELECT`, `JOIN`, and `WHERE` operations.

#### **Create Index on Order Status**
```sql
CREATE INDEX idx_order_status ON orders(order_status);
```
✅ **Why This is Scalable?**
- Queries using `WHERE order_status = 'Shipped'` will execute much faster.

---

### **✅ 3.3 Use Read-Write Database Replication**
For high read workloads (e.g., **analytics, reports**), use a **primary-replica** architecture.

#### **Spring Boot Config for Read/Write Splitting**
```yaml
spring:
  datasource:
    url: jdbc:mysql://primary-db-server:3306/orders
    replica-url: jdbc:mysql://replica-db-server:3306/orders
```
✅ **Why This is Scalable?**
- **Write operations go to the primary database**.
- **Read operations can be offloaded to replicas**, reducing load.

---

## **4. Handling Concurrency & Distributed Processing**
### **✅ 4.1 Implementing Optimistic Locking to Handle Concurrent Updates**
If multiple users update the same order, **optimistic locking** ensures data integrity.

#### **Modify Entity to Enable Optimistic Locking**
```java
import jakarta.persistence.*;

@Entity
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    
    private String orderStatus;

    @Version
    private Integer version;  // Optimistic Locking

    // Getters and Setters
}
```
✅ **Why This is Scalable?**
- Prevents **race conditions** when multiple transactions update the same record.

---

### **✅ 4.2 Implementing Parallel Batch Processing Using Multi-Threading**
For bulk operations, **multi-threading** in Spring Boot allows parallel processing.

#### **Parallel Batch Processing Service**
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class OrderBatchProcessor {

    private ExecutorService executorService = Executors.newFixedThreadPool(5); // 5 parallel threads

    public void processOrdersInBatches(List<Order> orders) {
        for (Order order : orders) {
            executorService.submit(() -> processOrder(order));
        }
    }

    private void processOrder(Order order) {
        // Process order asynchronously
        System.out.println("Processing Order: " + order.getOrderId());
    }
}
```
✅ **Why This is Scalable?**
- **Processes multiple orders in parallel** instead of sequentially.
- **Reduces processing time for large datasets**.

---

## **5. Final Architecture for High Scalability**
### ✅ **Scalability Improvements Implemented**
1. **Batch processing** stored procedures for bulk updates.
2. **Asynchronous notification queue** instead of direct inserts.
3. **Partitioning & Indexing** for faster queries.
4. **Read-write database replication** to scale read queries.
5. **Optimistic locking** for concurrent updates.
6. **Parallel processing with multi-threading** for faster order processing.

---

## **6. Conclusion**
✅ **Why These Solutions Scale?**
- **Reduces database locks** by batch processing updates.
- **Optimizes reads** with partitioning and indexing.
- **Offloads processing** to async services (RabbitMQ/Kafka).
- **Handles concurrency** using optimistic locking.
- **Distributes load** using replica databases.

Would you like a **full GitHub project** demonstrating these optimizations? 🚀