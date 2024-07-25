package com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.chainflow;

import com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.workflow.Request;

public interface RequestHandler {

    void handle(Request request);
}
