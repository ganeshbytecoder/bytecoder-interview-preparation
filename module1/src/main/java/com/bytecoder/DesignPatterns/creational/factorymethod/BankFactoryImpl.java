package com.bytecoder.DesignPatterns.creational.factorymethod;


public class BankFactoryImpl implements BankFactory {
    @Override
    public Bank getBank(String name) {
        if (name.equals("HDFC")) {
            return new HDFCBank();
        }
        if (name.equals("ICICI")) {
            return new IciciBank();
        }
        return null;
    }
}
