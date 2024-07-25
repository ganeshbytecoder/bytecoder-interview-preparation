package com.bytecoder.DesignPatterns.structural.bridge;

public class DebitCardPaymentModeService implements PaymentMode {
    public String paymentMode;

    DebitCardPaymentModeService() {
        this.paymentMode = "Debit card";
    }

    @Override
    public String getPaymentMode() {
        return this.paymentMode;
    }

    @Override
    public String makePayment() {
        return "making payment with Debit card";
    }
}
