package com.bytecoder.DesignPatterns.LLD.bahavioral.ChainOfResponsibility.workflow;

public class IPHandler extends BasicHandler {
    IPHandler(){
    }
    @Override
    public void handle(Request request) {
        System.out.println("IP handler " );
        if(next == null){
            return;
        }else {
            next.handle(request);
        }

    }
}
