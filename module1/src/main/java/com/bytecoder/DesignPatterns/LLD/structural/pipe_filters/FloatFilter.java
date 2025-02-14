package com.bytecoder.DesignPatterns.LLD.structural.pipe_filters;

public class FloatFilter implements Filter<String, Float> {

    @Override
    public Float execute(String input) {
        System.out.println("Float filter" +input);
        return Float.valueOf(input);
    }

}