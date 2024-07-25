package com.bytecoder.DesignPatterns.bahavioral.state;

public class StateService {
    public static void main(String[] args) {
        Customer customer = new Customer();
//        StateContext stateContext = new AccountCreateState(customer);
//        stateContext.performAction();
//         stateContext = new AccountVerifiedState(customer);
//         stateContext.performAction();
//         stateContext = new AccountVerifiedState(customer);
//      stateContext.performAction();
        customer.getCustomerState().getStateContext(customer).performAction();
        customer.getCustomerState().getStateContext(customer).performAction();
        customer.getCustomerState().getStateContext(customer).performAction();
    }
}
