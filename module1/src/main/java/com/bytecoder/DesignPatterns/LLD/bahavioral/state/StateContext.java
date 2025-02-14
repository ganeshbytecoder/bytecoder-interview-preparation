package com.bytecoder.DesignPatterns.LLD.bahavioral.state;

abstract class StateContext {
    protected Customer customer;
    StateContext(Customer customer){
        this.customer = customer;
    }

    public abstract void performAction();
}
