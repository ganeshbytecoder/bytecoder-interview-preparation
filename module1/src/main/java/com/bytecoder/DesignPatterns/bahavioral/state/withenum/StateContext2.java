package com.bytecoder.DesignPatterns.bahavioral.state.withenum;

import com.bytecoder.DesignPatterns.bahavioral.state.Customer;

abstract class StateContext2 {
    protected Customer customer;
    StateContext2(Customer customer){
        this.customer = customer;
    }

    public abstract void performAction();
}
