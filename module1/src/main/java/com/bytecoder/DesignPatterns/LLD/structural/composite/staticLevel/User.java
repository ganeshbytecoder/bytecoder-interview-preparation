package com.bytecoder.DesignPatterns.LLD.structural.composite.staticLevel;

import lombok.ToString;

@ToString
public class User {
    String name;

    String role;

    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }
}
