package com.bytecoder.DesignPatterns.LLD.structural.bridge;

import lombok.Data;

@Data
public class UPIPaymentModeService implements PaymentMode {
    public String paymentMode;

    public UPIPaymentModeService() {
        this.paymentMode = "UPI";
    }

    @Override
    public String getPaymentMode() {
        return this.paymentMode;
    }

    @Override
    public String makePayment() {
        return "making payment with UPI";
    }
}
