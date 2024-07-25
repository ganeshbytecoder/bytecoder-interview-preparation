package com.bytecoder.DesignPatterns.creational;

public class Person {
    private String name;

    private String college;

    private Person(String name, String college) {
        this.name = name;
        this.college = college;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", college='" + college + '\'' +
                '}';
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

    public static class PersonBuilder {
        private String name;

        private String college;

        public PersonBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder setCollege(String college) {
            this.college = college;
            return this;
        }

        public Person build() {
            return new Person(this.name, this.college);
        }
    }

}
