package com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.requestHandler;

public class AuthorisationHandler implements RequestHandler{

    private RequestHandler requestHandler;

    public AuthorisationHandler(RequestHandler requestHandler){
        this.requestHandler= requestHandler;
    }
    @Override
    public void handle() {

        requestHandler.handle();
        System.out.println("authorisation handling. ");

    }
}
