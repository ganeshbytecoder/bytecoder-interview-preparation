package com.bytecoder.DesignPatterns.bahavioral.state.withenum;

public class StateService2 {
    public static void main(String[] args) {
        Customer2 customer2 = new Customer2();
       customer2 = customer2.getCustomerState().performAction(customer2).setNextState(customer2);
       customer2 = customer2.getCustomerState().performAction(customer2).setNextState(customer2);
       customer2 = customer2.getCustomerState().performAction(customer2).setNextState(customer2);
       customer2 = customer2.getCustomerState().performAction(customer2).setNextState(customer2);
        System.out.println(customer2);
    }
}
