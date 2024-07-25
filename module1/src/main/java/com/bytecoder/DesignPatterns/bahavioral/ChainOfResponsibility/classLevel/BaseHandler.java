package com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.classLevel;

import com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.workflow.Request;

public class BaseHandler {

    BaseHandler next;

    public BaseHandler(BaseHandler next){
        this.next = next;
    }

    public void handle(Request request){
        System.out.println("base");
        if(next != null){
            next.handle(request);
        }
    }
}
