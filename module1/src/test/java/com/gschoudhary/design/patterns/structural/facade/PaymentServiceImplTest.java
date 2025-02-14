package com.gschoudhary.design.patterns.structural.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bytecoder.DesignPatterns.LLD.structural.facade.example1.PaymentService;
import com.bytecoder.DesignPatterns.LLD.structural.facade.example1.PaymentServiceImpl;

class PaymentServiceImplTest {

    PaymentService paymentService= new PaymentServiceImpl();

    @BeforeEach
    void setUp() {
    }

    @Test
    void makePaymentWithUpi() {
        paymentService.makePayment();
    }


}