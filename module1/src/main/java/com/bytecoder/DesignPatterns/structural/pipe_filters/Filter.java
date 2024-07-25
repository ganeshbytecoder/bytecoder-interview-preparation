package com.bytecoder.DesignPatterns.structural.pipe_filters;

import java.io.IOException;

public interface Filter<I, O> {
    O execute(I input) ;



}
