package com.bytecoder.DesignPatterns.LLD.structural.facade.example1;

public class PaymentServiceImpl extends PaymentService {
    @Override
    protected void userDetail() {
        System.out.println("Checking user details");

    }

    @Override
    protected void addressDetail() {
        System.out.println("validation address details");


    }

    @Override
    protected void orderDetail() {
        System.out.println("validation order details");


    }

    @Override
    protected void warehouseDetail() {
        System.out.println("validation warehouse details");
    }

    @Override
    protected void paymentDetail() {
        System.out.println("making payment");

    }
}
