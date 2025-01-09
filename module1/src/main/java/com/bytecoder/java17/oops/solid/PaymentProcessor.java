package com.bytecoder.java17.oops.solid;

import java.math.BigDecimal;

/**
 * This class demonstrates SOLID principles in action:
 * 1. Single Responsibility: Each class has one specific responsibility
 * 2. Open/Closed: New payment methods can be added without modifying existing code
 * 3. Liskov Substitution: All payment methods can be used interchangeably
 * 4. Interface Segregation: Interfaces are specific to client needs
 * 5. Dependency Inversion: High-level modules depend on abstractions
 */

// Payment processing interface - demonstrates Interface Segregation Principle
interface PaymentProcessor {
    boolean processPayment(Payment payment);
}

// Payment notification interface - separated from payment processing
interface PaymentNotifier {
    void notifyPaymentComplete(Payment payment);
}

// Payment validation interface
interface PaymentValidator {
    boolean validatePayment(Payment payment);
}

// Record for immutable payment data
record Payment(String id, BigDecimal amount, String currency, PaymentMethod method) {
    // Validation in compact constructor
    public Payment {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Payment amount must be positive");
        }
        if (currency == null || currency.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency must be specified");
        }
    }
}

// Enum for payment methods
enum PaymentMethod {
    CREDIT_CARD,
    DEBIT_CARD,
    BANK_TRANSFER,
    CRYPTO
}

// Abstract base class for payment processors
abstract class BasePaymentProcessor implements PaymentProcessor {
    private final PaymentValidator validator;
    private final PaymentNotifier notifier;

    protected BasePaymentProcessor(PaymentValidator validator, PaymentNotifier notifier) {
        this.validator = validator;
        this.notifier = notifier;
    }

    @Override
    public boolean processPayment(Payment payment) {
        if (!validator.validatePayment(payment)) {
            return false;
        }

        boolean success = doProcessPayment(payment);
        if (success) {
            notifier.notifyPaymentComplete(payment);
        }
        return success;
    }

    // Template method to be implemented by specific processors
    protected abstract boolean doProcessPayment(Payment payment);
}

// Concrete implementation for credit card payments
class CreditCardProcessor extends BasePaymentProcessor {
    public CreditCardProcessor(PaymentValidator validator, PaymentNotifier notifier) {
        super(validator, notifier);
    }

    @Override
    protected boolean doProcessPayment(Payment payment) {
        // Implementation for credit card processing
        return true; // Simplified for example
    }
}

// Concrete implementation for bank transfers
class BankTransferProcessor extends BasePaymentProcessor {
    public BankTransferProcessor(PaymentValidator validator, PaymentNotifier notifier) {
        super(validator, notifier);
    }

    @Override
    protected boolean doProcessPayment(Payment payment) {
        // Implementation for bank transfer processing
        return true; // Simplified for example
    }
}

// Factory for creating payment processors - demonstrates Factory pattern
class PaymentProcessorFactory {
    private final PaymentValidator validator;
    private final PaymentNotifier notifier;

    public PaymentProcessorFactory(PaymentValidator validator, PaymentNotifier notifier) {
        this.validator = validator;
        this.notifier = notifier;
    }

    public PaymentProcessor createProcessor(PaymentMethod method) {
        return switch (method) {
            case CREDIT_CARD, DEBIT_CARD -> new CreditCardProcessor(validator, notifier);
            case BANK_TRANSFER -> new BankTransferProcessor(validator, notifier);
            case CRYPTO -> throw new UnsupportedOperationException("Crypto payments not yet implemented");
        };
    }
}

// Example implementation of PaymentValidator
class DefaultPaymentValidator implements PaymentValidator {
    @Override
    public boolean validatePayment(Payment payment) {
        return payment.amount().compareTo(BigDecimal.ZERO) > 0 &&
               payment.currency() != null &&
               !payment.currency().trim().isEmpty();
    }
}

// Example implementation of PaymentNotifier
class EmailPaymentNotifier implements PaymentNotifier {
    @Override
    public void notifyPaymentComplete(Payment payment) {
        // Implementation for email notification
        System.out.println("Payment notification sent for payment: " + payment.id());
    }
}

// Usage example
class PaymentService {
    private final PaymentProcessorFactory factory;

    public PaymentService() {
        PaymentValidator validator = new DefaultPaymentValidator();
        PaymentNotifier notifier = new EmailPaymentNotifier();
        this.factory = new PaymentProcessorFactory(validator, notifier);
    }

    public boolean processPayment(Payment payment) {
        PaymentProcessor processor = factory.createProcessor(payment.method());
        return processor.processPayment(payment);
    }
}
