package com.bytecoder.DesignPatterns.structural.bridge;

public class YesBankPaymentServiceImpl implements PaymentService {
    private PaymentMode paymentMode;

    public YesBankPaymentServiceImpl(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    @Override
    public void makePayment() {

        System.out.println("Payment Mode " + paymentMode.getPaymentMode());
        System.out.println(paymentMode.makePayment());


    }
}
