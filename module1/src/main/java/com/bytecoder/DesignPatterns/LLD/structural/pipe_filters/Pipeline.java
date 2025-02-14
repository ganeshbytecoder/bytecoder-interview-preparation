package com.bytecoder.DesignPatterns.LLD.structural.pipe_filters;

class Pipeline<I, O> {

    private Filter<I, O> current;

    Pipeline(Filter<I, O> current) {
        this.current = current;
    }

    <P> Pipeline<I, P> pipe(Filter<O, P> next) {
        return new Pipeline<>(new Pipe<>(current, next));
    }

    // Using functional programming.
    <P> Pipeline<I, P> pipe2(Filter<O, P> next) {
        return new Pipeline<>((input -> next.execute(current.execute(input))));
    }

    O process(I input) {
        return current.execute(input);
    }
}