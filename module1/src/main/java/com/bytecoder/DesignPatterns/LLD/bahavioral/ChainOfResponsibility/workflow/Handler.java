package com.bytecoder.DesignPatterns.LLD.bahavioral.ChainOfResponsibility.workflow;

public interface Handler {
     Handler addNext(Handler handler);

    void handle(Request request);
}
