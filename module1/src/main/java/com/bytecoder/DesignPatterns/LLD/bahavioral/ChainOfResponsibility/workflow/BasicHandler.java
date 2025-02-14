package com.bytecoder.DesignPatterns.LLD.bahavioral.ChainOfResponsibility.workflow;

public class BasicHandler implements Handler  {
   protected Handler next;
    

    BasicHandler() {

    }

    @Override
    public Handler addNext(Handler handler) {
        if (next == null) {
            next = handler;
        } else {
            next.addNext(handler);
        }
        return this;
    }


    @Override
    public void handle(Request request) {
        System.out.println("starting basic Handler");
        if(next == null){
            return;
        }else {
            next.handle(request);
        }

    }

}
