package com.bytecoder.DesignPatterns.LLD.creational;


public class Person {

    private final String name;

// this is option field so we can set we need it from builder only
    private  String college;

    private Person(String name) {
        this.name = name;
    }

    private void setCollege(String college){
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


    public static PersonBuilder builder(String name) {
        return new PersonBuilder(name);
    }

    public static class PersonBuilder {
        private String name;

        private String college;

        public PersonBuilder(){}

        public PersonBuilder(String name) {
            this.name = name;
        }


        public PersonBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder setCollege(String college) {
            this.college = college;
            return this;
        }

        public Person build() {
            Person person =  new Person(this.name);
            person.setCollege(this.college);
            return person;

        }
    }

}
