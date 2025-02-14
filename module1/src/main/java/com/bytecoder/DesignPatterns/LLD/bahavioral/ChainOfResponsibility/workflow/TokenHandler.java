package com.bytecoder.DesignPatterns.LLD.bahavioral.ChainOfResponsibility.workflow;

public class TokenHandler extends BasicHandler {
    TokenHandler() {

    }

    @Override
    public void handle(Request request) {
        System.out.println("Token handler");
        if(request.ip == null){
            return;
        }else {
            next.handle(request);
        }
    }
}
