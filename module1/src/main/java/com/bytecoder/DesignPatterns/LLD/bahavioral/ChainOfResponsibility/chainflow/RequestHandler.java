package com.bytecoder.DesignPatterns.LLD.bahavioral.ChainOfResponsibility.chainflow;

import com.bytecoder.DesignPatterns.LLD.bahavioral.ChainOfResponsibility.workflow.Request;

public interface RequestHandler {

    void handle(Request request);
}
