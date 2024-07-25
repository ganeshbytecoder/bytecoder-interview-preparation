package com.bytecoder.DesignPatterns.bahavioral.state;

public class AccountCreateState extends StateContext {

    AccountCreateState(Customer customer) {
        super(customer);
    }

    @Override
    public void performAction() {
        System.out.println("account is created");
        super.customer.setCustomerState(CustomerState.ACCOUNT_CREATED);
    }
}
