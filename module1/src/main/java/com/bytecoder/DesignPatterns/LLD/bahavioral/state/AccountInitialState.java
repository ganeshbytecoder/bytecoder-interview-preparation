package com.bytecoder.DesignPatterns.LLD.bahavioral.state;

public class AccountInitialState extends StateContext {

    AccountInitialState(Customer customer) {
        super(customer);
    }

    @Override
    public void performAction() {
        System.out.println("account is created");
        super.customer.setCustomerState(CustomerState.ACCOUNT_CREATED);
    }
}
