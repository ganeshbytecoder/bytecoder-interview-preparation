package com.gschoudhary.design.patterns.structural.bridge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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