Below is an advanced e-commerce example that uses the Bridge pattern to decouple shipping strategies (domestic vs. international) from the underlying shipping provider (e.g., FedEx vs. UPS). In a real-life e-commerce scenario, this design enables the system to choose both the shipping method and the shipping carrier independently. For instance, an order might be shipped domestically via UPS or internationally via FedEx—each with its own cost calculation and processing logic.

---

## 1. Design Overview

### Two Dimensions of Variability

- **Shipping Type (Abstraction):**  
  Represents the method for shipping an order. We define two refined abstractions:
    - **DomesticShipping:** For orders within the same country.
    - **InternationalShipping:** For orders shipped internationally (with additional fees or customs handling).

- **Shipping Provider (Implementor):**  
  Encapsulates the specifics of the carrier’s integration. We provide two implementations:
    - **FedExProvider:** Implements FedEx-specific cost calculation and shipping logic.
    - **UPSProvider:** Implements UPS-specific operations.

By applying the Bridge pattern, you can mix and match any shipping type with any provider without creating a combinatorial explosion of classes.

---

## 2. Code Implementation

### 2.1. Shipping Provider Interface and Its Implementations

**ShippingProvider.java**  
Defines methods for calculating shipping costs and processing shipments.

```java
public interface ShippingProvider {
    double calculateCost(double weight, double distance);
    void shipOrder(String orderId);
}
```

**FedExProvider.java**  
A concrete provider for FedEx. (Annotated as a Spring component for dependency injection.)

```java
import org.springframework.stereotype.Component;

@Component("fedexProvider")
public class FedExProvider implements ShippingProvider {
    @Override
    public double calculateCost(double weight, double distance) {
        // Example: FedEx might charge based on weight and distance
        return (weight * 0.5 + distance * 0.2);
    }

    @Override
    public void shipOrder(String orderId) {
        System.out.println("Order " + orderId + " shipped via FedEx.");
    }
}
```

**UPSProvider.java**  
A concrete provider for UPS.

```java
import org.springframework.stereotype.Component;

@Component("upsProvider")
public class UPSProvider implements ShippingProvider {
    @Override
    public double calculateCost(double weight, double distance) {
        // Example: UPS might have a different rate calculation
        return (weight * 0.6 + distance * 0.15);
    }

    @Override
    public void shipOrder(String orderId) {
        System.out.println("Order " + orderId + " shipped via UPS.");
    }
}
```

---

### 2.2. The Shipping Abstraction and Its Refined Variants

**Shipping.java**  
The abstract class that holds a reference to a ShippingProvider and defines operations for calculating cost and shipping an order.

```java
public abstract class Shipping {
    protected ShippingProvider provider;

    public Shipping(ShippingProvider provider) {
        this.provider = provider;
    }
    
    public abstract double calculateShipping(double weight, double distance);
    public abstract void ship(String orderId);
}
```

**DomesticShipping.java**  
Handles domestic shipping calculations and processing.

```java
public class DomesticShipping extends Shipping {
    public DomesticShipping(ShippingProvider provider) {
        super(provider);
    }

    @Override
    public double calculateShipping(double weight, double distance) {
        System.out.println("Calculating domestic shipping...");
        // Domestic shipping might use the provider's standard cost calculation
        return provider.calculateCost(weight, distance);
    }

    @Override
    public void ship(String orderId) {
        System.out.println("Processing domestic shipping...");
        provider.shipOrder(orderId);
    }
}
```

**InternationalShipping.java**  
Handles international shipping with possible extra fees (e.g., customs or handling charges).

```java
public class InternationalShipping extends Shipping {
    public InternationalShipping(ShippingProvider provider) {
        super(provider);
    }

    @Override
    public double calculateShipping(double weight, double distance) {
        System.out.println("Calculating international shipping...");
        // Add an extra flat fee for international handling
        return provider.calculateCost(weight, distance) + 20;
    }

    @Override
    public void ship(String orderId) {
        System.out.println("Processing international shipping...");
        provider.shipOrder(orderId);
    }
}
```

---

### 2.3. Service Layer

**ShippingService.java**  
A Spring service that selects the shipping type and provider based on request parameters.

```java
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    private final ShippingProvider fedexProvider;
    private final ShippingProvider upsProvider;

    public ShippingService(@Qualifier("fedexProvider") ShippingProvider fedexProvider,
                           @Qualifier("upsProvider") ShippingProvider upsProvider) {
        this.fedexProvider = fedexProvider;
        this.upsProvider = upsProvider;
    }

    public double processShipping(String shippingType, String providerName,
                                  double weight, double distance, String orderId) {
        // Select the shipping provider based on the request parameter.
        ShippingProvider selectedProvider;
        if ("fedex".equalsIgnoreCase(providerName)) {
            selectedProvider = fedexProvider;
        } else if ("ups".equalsIgnoreCase(providerName)) {
            selectedProvider = upsProvider;
        } else {
            throw new IllegalArgumentException("Unsupported provider: " + providerName);
        }

        // Choose shipping type: domestic or international.
        Shipping shipping;
        if ("domestic".equalsIgnoreCase(shippingType)) {
            shipping = new DomesticShipping(selectedProvider);
        } else if ("international".equalsIgnoreCase(shippingType)) {
            shipping = new InternationalShipping(selectedProvider);
        } else {
            throw new IllegalArgumentException("Unsupported shipping type: " + shippingType);
        }

        // Calculate shipping cost and process the shipment.
        double cost = shipping.calculateShipping(weight, distance);
        shipping.ship(orderId);
        return cost;
    }
}
```

---

### 2.4. REST Controller

**ShippingController.java**  
Exposes an endpoint for processing shipping requests.

```java
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    private final ShippingService shippingService;

    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @PostMapping("/process")
    public ResponseEntity<String> processShipping(@RequestParam String shippingType,
                                                  @RequestParam String provider,
                                                  @RequestParam double weight,
                                                  @RequestParam double distance,
                                                  @RequestParam String orderId) {
        try {
            double cost = shippingService.processShipping(shippingType, provider, weight, distance, orderId);
            return ResponseEntity.ok("Shipping processed. Total cost: $" + cost);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        }
    }
}
```

---

### 2.5. Spring Boot Application Entry Point

**ECommerceShippingApplication.java**  
The main class to run the Spring Boot application.

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ECommerceShippingApplication {
    public static void main(String[] args) {
        SpringApplication.run(ECommerceShippingApplication.class, args);
    }
}
```

---

## 3. How It Works (Real-Life Analogy)

Imagine an online store where customers check out their orders. Depending on their location and the destination:
- **Shipping Type:**
    - **Domestic Shipping:** The order is sent within the same country using the standard rates.
    - **International Shipping:** The order is sent overseas with extra fees to account for customs and handling.

- **Shipping Provider:**  
  The store can choose between carriers such as FedEx or UPS, each with different cost formulas and service levels.

Using the Bridge pattern:
- The **Shipping** abstraction (and its refined types) encapsulates the "when" and "how" of shipping an order.
- The **ShippingProvider** implementations encapsulate the carrier-specific details.

This separation allows the e-commerce system to easily support new shipping methods or carriers in the future without major changes to the overall design.

---

This example demonstrates a flexible, real-world e-commerce shipping system using the Bridge design pattern in Spring Boot, where both the shipping strategy (domestic vs. international) and the shipping provider (FedEx vs. UPS) can evolve independently while remaining integrated within the overall application architecture.