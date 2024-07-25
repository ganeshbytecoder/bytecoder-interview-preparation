package com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.chainflow;

import com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.workflow.Request;

public class AuthenticationHandler implements RequestHandler {

    @Override
    public void handle(Request request) {

        System.out.println("Authentication handler  ");
        throw new RuntimeException("not working");
    }
}
