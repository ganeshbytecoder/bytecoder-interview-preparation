package com.gschoudhary.design.patterns.structural.bridge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bytecoder.DesignPatterns.structural.bridge.CashPaymentModeService;
import com.bytecoder.DesignPatterns.structural.bridge.UPIPaymentModeService;
import com.bytecoder.DesignPatterns.structural.bridge.YesBankPaymentServiceImpl;


class YesBankPaymentServiceImplTest {
    YesBankPaymentServiceImpl yesBankPaymentService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void makePayment() {
        yesBankPaymentService = new YesBankPaymentServiceImpl(new CashPaymentModeService());
        yesBankPaymentService.makePayment();

        yesBankPaymentService = new YesBankPaymentServiceImpl(new UPIPaymentModeService());
        yesBankPaymentService.makePayment();
    }
}