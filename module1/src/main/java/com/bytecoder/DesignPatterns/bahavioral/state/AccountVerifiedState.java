package com.bytecoder.DesignPatterns.bahavioral.state;

public class AccountVerifiedState extends StateContext {

    AccountVerifiedState(Customer customer) {
        super(customer);
    }

    @Override
    public void performAction() {
        System.out.println("account is verified");
        super.customer.setCustomerState(CustomerState.ACCOUNT_VERIFIED);
    }
}
