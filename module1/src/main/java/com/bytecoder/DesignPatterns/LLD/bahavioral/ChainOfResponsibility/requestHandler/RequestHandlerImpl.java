package com.bytecoder.DesignPatterns.LLD.bahavioral.ChainOfResponsibility.requestHandler;

public class RequestHandlerImpl implements RequestHandler {
    @Override
    public void handle() {
        System.out.println("basic handler");
    }
}
