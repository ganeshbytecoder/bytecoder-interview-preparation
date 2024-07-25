package com.gschoudhary.design.patterns.bahavioral.ChainOfResponsibility.chainflow;
import org.junit.jupiter.api.Test;

import com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.chainflow.AuthenticationHandler;
import com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.chainflow.CustomHandler;
import com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.chainflow.RequestController;
import com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.chainflow.RequestHandlerImpl;

class RequestControllerTest {
    RequestHandlerImpl requestHandler = new RequestHandlerImpl();

    RequestController requestController = new RequestController();

    @Test
    void execute() {


        requestHandler
                .addHandler(new AuthenticationHandler())
                .addHandler(new CustomHandler()).handle(null);
        requestController.execute();

    }
}