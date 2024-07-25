package com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.workflow;

public interface Handler {
     Handler addNext(Handler handler);

    void handle(Request request);
}
