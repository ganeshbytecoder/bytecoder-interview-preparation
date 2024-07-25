package com.gschoudhary.design.patterns.bahavioral.ChainOfResponsibility.requestHandler;

import org.junit.jupiter.api.Test;

import com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.requestHandler.AuthenticationHandler;
import com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.requestHandler.CustomHandler;
import com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.requestHandler.RequestController;
import com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.requestHandler.RequestHandler;
import com.bytecoder.DesignPatterns.bahavioral.ChainOfResponsibility.requestHandler.RequestHandlerImpl;

class RequestControllerTest {

    RequestController requestController= new RequestController();
    RequestHandler requestHandler = new RequestHandlerImpl();

    @Test
    void execute() {

        new AuthenticationHandler(new CustomHandler(requestHandler)).handle();
        requestController.execute();
    }
}