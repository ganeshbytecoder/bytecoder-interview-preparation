package com.bytecoder.DesignPatterns.LLD.bahavioral.state.withenum;

import com.bytecoder.DesignPatterns.LLD.bahavioral.state.Customer;

abstract class StateContext2 {
    protected Customer customer;
    StateContext2(Customer customer){
        this.customer = customer;
    }

    public abstract void performAction();
}
