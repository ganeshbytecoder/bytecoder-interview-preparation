package com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.chainflow;

import com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.workflow.Request;

public class AuthorisationHandler implements RequestHandler {
    @Override
    public void handle(Request request) {

        System.out.println("authorisation handling. ");

    }
}
