package com.bytecoder.java8.FunctionalProgramming;

import java.util.function.Predicate;

public interface Filter<I, O> {
    O execute(I input);

    default <V> Filter<I, V> then(Filter<O, V> filter) {
        return (I input) -> filter.execute(this.execute(input));
    }

    default <V> Filter<O, V> map(Filter<O, V> filter) {
        return (O input) -> {
            System.out.println("mapper executed");
            return filter.execute(input);
        };
    }

    default O ifOrElse(Predicate<I> binaryOperator, Filter<I, O> ifFilter, Filter<I, O> elseFilter, I input) {
        if (binaryOperator.test(input)) {
            return ifFilter.execute(input);
        } else {
            return elseFilter.execute(input);
        }
    }


}
