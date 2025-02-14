package com.gschoudhary.design.patterns.structural.bridge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bytecoder.DesignPatterns.LLD.structural.bridge.CashPaymentModeService;
import com.bytecoder.DesignPatterns.LLD.structural.bridge.UPIPaymentModeService;
import com.bytecoder.DesignPatterns.LLD.structural.bridge.YesBankPaymentServiceImpl;


class YesBankPaymentServiceImplTest {
    YesBankPaymentServiceImpl yesBankPaymentService;

    @BeforeEach
    void setUp() {

    }


//    using abstract factory -> payment mode using builder ->  singleton object ->

    @Test
    void makePayment() {
        yesBankPaymentService = new YesBankPaymentServiceImpl(new CashPaymentModeService());
        yesBankPaymentService.makePayment();

        yesBankPaymentService = new YesBankPaymentServiceImpl(new UPIPaymentModeService());
        yesBankPaymentService.makePayment();
    }
}