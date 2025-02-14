package com.gschoudhary.design.patterns.structural.marker;

import com.bytecoder.DesignPatterns.LLD.structural.marker.*;
import com.bytecoder.DesignPatterns.structural.marker.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PaymentServiceTest {
    List<PaymentService> paymentServiceList;


    @BeforeEach
    void setUp() {
        paymentServiceList = new ArrayList<>();
        paymentServiceList.add(new PaytmPaymentService());
        paymentServiceList.add(new BharatPayPaymentService());
        paymentServiceList.add(new PhonePayPaymentService());
    }

    @Test
    void execute() {
        for (PaymentService paymentService:
             paymentServiceList) {
            if(paymentService instanceof MarkerInterface){
                System.out.println("marker service");
                continue;
            }
            paymentService.execute("payment");
        }
    }
}