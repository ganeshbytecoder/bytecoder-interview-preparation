Below is a more advanced Spring Boot example that uses the Bridge pattern to decouple the type of payment (immediate vs. scheduled) from the payment gateway (Stripe vs. PayPal). Imagine an e-commerce platform where users can choose not only different payment providers but also different payment modes (e.g., one-time immediate payment vs. scheduled/subscription payments). This design keeps the payment logic independent from the integration details of each payment gateway.

---

## 1. Design Overview

### Two Dimensions of Variability

- **Payment Type (Abstraction):**  
  This represents the high-level concept of making a payment. It can be extended into various types:
    - **ImmediatePayment:** Process the payment instantly.
    - **ScheduledPayment:** Schedule the payment for later (perhaps with additional business logic or delay).

- **Payment Gateway (Implementor):**  
  This represents the integration with external payment providers. You can have several implementations:
    - **StripePaymentGateway:** Processes payments via Stripe.
    - **PaypalPaymentGateway:** Processes payments via PayPal.

By applying the Bridge pattern, you can mix and match any payment type with any payment gateway without creating a combinatorial explosion of classes.

---

## 2. Code Implementation

### 2.1. The Payment Gateway Interface and Its Implementations

**PaymentGateway.java**  
This interface defines the operation to process a payment.

```java
public interface PaymentGateway {
    void processPayment(double amount, String currency);
}
```

**StripePaymentGateway.java**  
Implements payment processing for Stripe.

```java
import org.springframework.stereotype.Component;

@Component("stripeGateway")
public class StripePaymentGateway implements PaymentGateway {
    @Override
    public void processPayment(double amount, String currency) {
        // Simulated Stripe API integration logic
        System.out.println("Processing payment of " + amount + " " + currency + " via Stripe.");
    }
}
```

**PaypalPaymentGateway.java**  
Implements payment processing for PayPal.

```java
import org.springframework.stereotype.Component;

@Component("paypalGateway")
public class PaypalPaymentGateway implements PaymentGateway {
    @Override
    public void processPayment(double amount, String currency) {
        // Simulated PayPal API integration logic
        System.out.println("Processing payment of " + amount + " " + currency + " via PayPal.");
    }
}
```

---

### 2.2. The Payment Abstraction and Its Refined Variants

**Payment.java**  
An abstract class that holds a reference to a PaymentGateway. It defines a method to make a payment.

```java
public abstract class Payment {
    protected PaymentGateway paymentGateway;
    
    public Payment(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }
    
    public abstract void makePayment(double amount, String currency);
}
```

**ImmediatePayment.java**  
Processes payments immediately by delegating to the gateway.

```java
public class ImmediatePayment extends Payment {
    public ImmediatePayment(PaymentGateway paymentGateway) {
        super(paymentGateway);
    }
    
    @Override
    public void makePayment(double amount, String currency) {
        System.out.println("Initiating immediate payment...");
        paymentGateway.processPayment(amount, currency);
    }
}
```

**ScheduledPayment.java**  
Simulates scheduling a payment (for example, as part of a subscription or deferred billing).

```java
public class ScheduledPayment extends Payment {
    public ScheduledPayment(PaymentGateway paymentGateway) {
        super(paymentGateway);
    }
    
    @Override
    public void makePayment(double amount, String currency) {
        System.out.println("Scheduling payment...");
        // Simulate scheduling logic (in a real-world scenario, you might use Spring's scheduling support)
        try {
            Thread.sleep(2000); // simulate a delay for scheduled processing
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Processing scheduled payment now.");
        paymentGateway.processPayment(amount, currency);
    }
}
```

---

### 2.3. The Service Layer

**PaymentService.java**  
This service decides which Payment type and gateway to use based on incoming parameters. It demonstrates dependency injection of the two gateway implementations.

```java
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentGateway stripeGateway;
    private final PaymentGateway paypalGateway;

    public PaymentService(@Qualifier("stripeGateway") PaymentGateway stripeGateway,
                          @Qualifier("paypalGateway") PaymentGateway paypalGateway) {
        this.stripeGateway = stripeGateway;
        this.paypalGateway = paypalGateway;
    }

    public void processPayment(String type, String gateway, double amount, String currency) {
        // Select the payment gateway based on request parameter
        PaymentGateway selectedGateway;
        if ("stripe".equalsIgnoreCase(gateway)) {
            selectedGateway = stripeGateway;
        } else if ("paypal".equalsIgnoreCase(gateway)) {
            selectedGateway = paypalGateway;
        } else {
            throw new IllegalArgumentException("Unsupported payment gateway: " + gateway);
        }
        
        // Choose payment type: immediate or scheduled
        Payment payment;
        if ("immediate".equalsIgnoreCase(type)) {
            payment = new ImmediatePayment(selectedGateway);
        } else if ("scheduled".equalsIgnoreCase(type)) {
            payment = new ScheduledPayment(selectedGateway);
        } else {
            throw new IllegalArgumentException("Unsupported payment type: " + type);
        }
        
        payment.makePayment(amount, currency);
    }
}
```

---

### 2.4. Exposing a REST Endpoint

**PaymentController.java**  
A REST controller that exposes an endpoint to process payments. It leverages the service layer to decouple the API from the payment processing logic.

```java
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    
    private final PaymentService paymentService;
    
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    
    @PostMapping("/process")
    public ResponseEntity<String> processPayment(@RequestParam String type,
                                                 @RequestParam String gateway,
                                                 @RequestParam double amount,
                                                 @RequestParam String currency) {
        try {
            paymentService.processPayment(type, gateway, amount, currency);
            return ResponseEntity.ok("Payment processed successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
```

---

### 2.5. Spring Boot Application Entry Point

**BridgePaymentApplication.java**  
The main class to run the Spring Boot application.

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BridgePaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(BridgePaymentApplication.class, args);
    }
}
```

---

## 3. How It Works (Real-Life Analogy)

Imagine an online store where customers choose how and when to pay:
- **Payment Type:**  
  A customer might decide to pay immediately at checkout or opt for a subscription model (scheduled payment) where the charge is processed later.

- **Payment Gateway:**  
  Depending on the customer's preference or availability in their region, the store might process the payment through Stripe or PayPal.

Using the Bridge pattern:
- The **Payment** abstraction (with its refined versions) encapsulates the logic of *when* to process the payment (immediate vs. scheduled).
- The **PaymentGateway** implementations encapsulate *how* the payment is processed (integrating with Stripe or PayPal).

This separation lets the platform easily add new payment types (e.g., installment payments) or integrate with additional gateways without changing the high-level payment logic.

---

This advanced example demonstrates a flexible and maintainable approach using the Bridge pattern in Spring Boot, where both dimensions (payment mode and gateway) can evolve independently while still working together seamlessly.