package com.bytecoder.DesignPatterns.LLD.bahavioral.ChainOfResponsibility.classLevel;

import com.bytecoder.DesignPatterns.LLD.bahavioral.ChainOfResponsibility.workflow.Request;

public class IPCheckHandler extends BaseHandler {
    public IPCheckHandler(BaseHandler next) {
        super(next);
    }

    @Override
    public void handle(Request request) {
        System.out.println("base3");
        if (next != null && request.ip != null) {
            next.handle(request);
        }
    }
}
