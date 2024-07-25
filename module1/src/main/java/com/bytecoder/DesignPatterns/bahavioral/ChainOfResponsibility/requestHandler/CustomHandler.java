package com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.requestHandler;

public class CustomHandler implements RequestHandler {
    private RequestHandler requestHandler;

    public CustomHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public void handle() {
        requestHandler.handle();
        System.out.println("Custom handle");
    }
}
