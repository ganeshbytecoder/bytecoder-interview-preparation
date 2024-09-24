package com.bytecoder.DesignPatterns.structural.pipe_filters;


class Pipe<I, O, P> implements Filter<I, P> {

    private Filter<I, O> current;

    private Filter<O, P> next;

    Pipe(Filter<I, O> current, Filter<O, P> next) {
        this.current = current;
        this.next = next;
    }

    public P execute(I input) {
        return next.execute(current.execute(input));
    }

}