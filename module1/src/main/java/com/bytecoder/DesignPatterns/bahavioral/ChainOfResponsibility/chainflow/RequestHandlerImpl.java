package com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.chainflow;

import java.util.LinkedList;

import com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.workflow.Request;

public class RequestHandlerImpl implements RequestHandler {
    private LinkedList<RequestHandler> handlers = new LinkedList<>();

    public RequestHandlerImpl addHandler(RequestHandler requestHandler) {
        handlers.add(requestHandler);
        return this;
    }

    @Override
    public void handle(Request request) {
        System.out.println("basic handler");
        try {
            for (RequestHandler handler : handlers
            ) {
                handler.handle(request);
            }
        }catch (Exception e){
            System.out.println("exception occurred");
        }
    }
}
