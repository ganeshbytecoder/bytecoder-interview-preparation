package com.bytecoder.DesignPatterns.structural.pipe_filters;

public class IntFilter implements Filter<Float, Integer> {
    @Override
    public Integer execute(Float input) {
        return input.intValue();
    }

}
