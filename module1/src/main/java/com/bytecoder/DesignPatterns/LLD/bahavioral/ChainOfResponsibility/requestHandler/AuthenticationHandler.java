package com.bytecoder.DesignPatterns.LLD.bahavioral.ChainOfResponsibility.requestHandler;

public class AuthenticationHandler implements RequestHandler {
    private RequestHandler requestHandler;

    public AuthenticationHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public void handle() {
        requestHandler.handle();

        System.out.println("Authentication handler  ");
    }
}
