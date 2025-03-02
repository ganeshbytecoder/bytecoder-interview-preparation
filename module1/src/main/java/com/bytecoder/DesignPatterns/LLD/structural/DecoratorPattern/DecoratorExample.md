Below is an example of how you can model a coffee‐shop ordering system (using the Decorator Pattern for beverage customization) with a relational database schema and a Spring Boot API to support all functionalities.

---

## 1. Database Schema

In this example, each order is composed of one or more order items. Each order item has a base beverage (e.g., Espresso) and can be “decorated” with one or more extras (e.g., Milk, Sugar, Whip). The schema uses a join table for the many‑to‑many relationship between order items and extras.

### SQL DDL

```sql
-- Table to store base beverages
CREATE TABLE beverage (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    base_cost DECIMAL(10,2) NOT NULL
);

-- Table to store extras (decorators)
CREATE TABLE extra (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    extra_cost DECIMAL(10,2) NOT NULL
);

-- Table to store orders
CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table for each order item, referencing a beverage
CREATE TABLE order_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    beverage_id BIGINT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (beverage_id) REFERENCES beverage(id)
);

-- Join table linking order items to extras (multiple decorators per item)
CREATE TABLE order_item_extras (
    order_item_id BIGINT NOT NULL,
    extra_id BIGINT NOT NULL,
    PRIMARY KEY (order_item_id, extra_id),
    FOREIGN KEY (order_item_id) REFERENCES order_item(id),
    FOREIGN KEY (extra_id) REFERENCES extra(id)
);
```

---

## 2. Spring Boot API

Below is a sample Spring Boot application structure using Spring Data JPA. We define entity classes corresponding to our tables, repository interfaces, DTOs for API payloads, a service layer for business logic, and REST controllers.

### Project Structure

```
src/main/java/com/example/coffeeshop
├── CoffeeShopApplication.java
├── controller
│   ├── BeverageController.java
│   ├── ExtraController.java
│   └── OrderController.java
├── dto
│   ├── OrderItemRequest.java
│   └── OrderRequest.java
├── entity
│   ├── Beverage.java
│   ├── Extra.java
│   ├── Order.java
│   └── OrderItem.java
├── repository
│   ├── BeverageRepository.java
│   ├── ExtraRepository.java
│   └── OrderRepository.java
└── service
    └── OrderService.java
```

### a. Entity Classes

**Beverage.java**

```java
package com.example.coffeeshop.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Beverage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private BigDecimal baseCost;
    
    // Constructors, getters, setters
    public Beverage() {}
    
    public Beverage(String name, BigDecimal baseCost) {
        this.name = name;
        this.baseCost = baseCost;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public BigDecimal getBaseCost() { return baseCost; }
    public void setBaseCost(BigDecimal baseCost) { this.baseCost = baseCost; }
}
```

**Extra.java**

```java
package com.example.coffeeshop.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Extra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private BigDecimal extraCost;
    
    // Constructors, getters, setters
    public Extra() {}
    
    public Extra(String name, BigDecimal extraCost) {
        this.name = name;
        this.extraCost = extraCost;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public BigDecimal getExtraCost() { return extraCost; }
    public void setExtraCost(BigDecimal extraCost) { this.extraCost = extraCost; }
}
```

**Order.java**

```java
package com.example.coffeeshop.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDateTime orderDate;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;
    
    // Constructors, getters, setters
    public Order() {
        this.orderDate = LocalDateTime.now();
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    
    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }

    // Transient method to calculate the cumulative cost of the order.
    @Transient
    public BigDecimal getTotalCost() {
        BigDecimal total = BigDecimal.ZERO;
        if (orderItems != null) {
            for (OrderItem item : orderItems) {
                total = total.add(item.getItemCost());
            }
        }
        return total;
    }
    
}
```

**OrderItem.java**

```java
package com.example.coffeeshop.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    
    @ManyToOne
    @JoinColumn(name = "beverage_id")
    private Beverage beverage;
    
    @ManyToMany
    @JoinTable(
        name = "order_item_extras",
        joinColumns = @JoinColumn(name = "order_item_id"),
        inverseJoinColumns = @JoinColumn(name = "extra_id")
    )
    private Set<Extra> extras;
    
    // Constructors, getters, setters
    public OrderItem() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
    
    public Beverage getBeverage() { return beverage; }
    public void setBeverage(Beverage beverage) { this.beverage = beverage; }
    
    public Set<Extra> getExtras() { return extras; }
    public void setExtras(Set<Extra> extras) { this.extras = extras; }


    // Transient method to calculate cumulative cost for this order item.
    @Transient
    public BigDecimal getItemCost() {
        BigDecimal total = beverage.getBaseCost();
        if (extras != null) {
            for (Extra extra : extras) {
                total = total.add(extra.getExtraCost());
            }
        }
        return total;
    }

    // Transient method to compute description for this order item.
    @Transient
    public String getItemDescription() {
        String description = beverage.getName();
        if (extras != null && !extras.isEmpty()) {
            description += " with " + extras.stream()
                    .map(Extra::getName)
                    .collect(Collectors.joining(", "));
        }
        return description;
    }
}
```

---

### b. Repository Interfaces

**BeverageRepository.java**

```java
package com.example.coffeeshop.repository;

import com.example.coffeeshop.entity.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeverageRepository extends JpaRepository<Beverage, Long> {
}
```

**ExtraRepository.java**

```java
package com.example.coffeeshop.repository;

import com.example.coffeeshop.entity.Extra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtraRepository extends JpaRepository<Extra, Long> {
}
```

**OrderRepository.java**

```java
package com.example.coffeeshop.repository;

import com.example.coffeeshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
```

---

### c. DTO Classes

These classes are used to receive order creation requests.

**OrderItemRequest.java**

```java
package com.example.coffeeshop.dto;

import java.util.Set;

public class OrderItemRequest {
    private Long beverageId;
    private Set<Long> extraIds; // Optional: IDs for any extras
    
    // Getters and setters
    public Long getBeverageId() { return beverageId; }
    public void setBeverageId(Long beverageId) { this.beverageId = beverageId; }
    
    public Set<Long> getExtraIds() { return extraIds; }
    public void setExtraIds(Set<Long> extraIds) { this.extraIds = extraIds; }
}
```

**OrderRequest.java**

```java
package com.example.coffeeshop.dto;

import java.util.List;

public class OrderRequest {
    private List<OrderItemRequest> orderItems;
    
    // Getters and setters
    public List<OrderItemRequest> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItemRequest> orderItems) { this.orderItems = orderItems; }
}
```

---

### d. Service Layer

The service layer handles order creation by fetching the corresponding beverage and extra entities and then saving the order.

**OrderService.java**

```java
package com.example.coffeeshop.service;

import com.example.coffeeshop.dto.OrderItemRequest;
import com.example.coffeeshop.dto.OrderRequest;
import com.example.coffeeshop.entity.Beverage;
import com.example.coffeeshop.entity.Extra;
import com.example.coffeeshop.entity.Order;
import com.example.coffeeshop.entity.OrderItem;
import com.example.coffeeshop.repository.BeverageRepository;
import com.example.coffeeshop.repository.ExtraRepository;
import com.example.coffeeshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private BeverageRepository beverageRepository;
    
    @Autowired
    private ExtraRepository extraRepository;
    
    public Order createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();
        
        for (OrderItemRequest itemReq : orderRequest.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            Beverage beverage = beverageRepository.findById(itemReq.getBeverageId())
                    .orElseThrow(() -> new RuntimeException("Beverage not found"));
            orderItem.setBeverage(beverage);
            orderItem.setOrder(order);
            
            Set<Extra> extras = new HashSet<>();
            if (itemReq.getExtraIds() != null) {
                for (Long extraId : itemReq.getExtraIds()) {
                    Extra extra = extraRepository.findById(extraId)
                            .orElseThrow(() -> new RuntimeException("Extra not found"));
                    extras.add(extra);
                }
            }
            orderItem.setExtras(extras);
            orderItems.add(orderItem);
        }
        
        order.setOrderItems(orderItems);
        return orderRepository.save(order);
    }
    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
```

---

### e. REST Controllers

**OrderController.java**

```java
package com.example.coffeeshop.controller;

import com.example.coffeeshop.dto.OrderRequest;
import com.example.coffeeshop.entity.Order;
import com.example.coffeeshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        Order order = orderService.createOrder(orderRequest);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    
    @GetMapping
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }
    
    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);

        // Build a simple summary response that includes each item description and cost.
        List<Map<String, Object>> itemSummaries = order.getOrderItems().stream().map(item -> {
            Map<String, Object> itemMap = new HashMap<>();
            itemMap.put("description", item.getItemDescription());
            itemMap.put("cost", item.getItemCost());
            return itemMap;
        }).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("orderId", order.getId());
        response.put("orderDate", order.getOrderDate());
        response.put("totalCost", order.getTotalCost());
        response.put("items", itemSummaries);

        return new ResponseEntity<>(response, HttpStatus.OK);        
        
        
    }
}
```

**BeverageController.java**

```java
package com.example.coffeeshop.controller;

import com.example.coffeeshop.entity.Beverage;
import com.example.coffeeshop.repository.BeverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beverages")
public class BeverageController {

    @Autowired
    private BeverageRepository beverageRepository;
    
    @GetMapping
    public List<Beverage> getBeverages() {
        return beverageRepository.findAll();
    }
}
```

**ExtraController.java**

```java
package com.example.coffeeshop.controller;

import com.example.coffeeshop.entity.Extra;
import com.example.coffeeshop.repository.ExtraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/extras")
public class ExtraController {

    @Autowired
    private ExtraRepository extraRepository;
    
    @GetMapping
    public List<Extra> getExtras() {
        return extraRepository.findAll();
    }
}
```

---

### f. Main Application Class

**CoffeeShopApplication.java**

```java
package com.example.coffeeshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoffeeShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoffeeShopApplication.class, args);
    }
}
```

---

## Summary

- **Database Schema:** We defined tables for beverages, extras, orders, order items, and a join table (`order_item_extras`) to store the many-to-many relationship between order items and extras.
- **Spring Boot API:**
    - **Entities & Repositories:** Map the schema with JPA.
    - **DTOs:** Allow clients to create orders with a base beverage and multiple extras.
    - **Service Layer:** Handles order creation logic by fetching the necessary entities.
    - **Controllers:** Expose REST endpoints to list beverages, extras, and to create and view orders.

This implementation demonstrates how you can support the Decorator Pattern’s dynamic behavior (adding extras) in a real-world application using a relational database and a Spring Boot API.