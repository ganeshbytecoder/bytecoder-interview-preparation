package com.bytecoder.JavaSpecialisation.Java.Optional;

import java.util.Optional;

public class OptionalApplications {
    public static Optional<User> optionalFunctions() {
        Optional<Customer> customer = Optional.ofNullable(new Customer(1, "testuser", 26, "test address"));
//        checking if customer exists or not without validating null
        if (customer.isPresent()) {
            System.out.println("customer exists with details " + customer.get());
        }
//        if presents(consumer)
        customer.ifPresent(System.out::println);

        User user = customer.map(c -> new User(c.getName(), c.getAge(), c.getAddress()))
                .filter(u -> u.getAge() > 18)
                .orElseThrow(() -> new RuntimeException("customer is not having age > 18"));

        System.out.println(user);
        return Optional.ofNullable(user);

    }

    public static void main(String[] args) {
//        optionalFunctions();
        var a = 10;
        System.out.println(a);
    }

}
