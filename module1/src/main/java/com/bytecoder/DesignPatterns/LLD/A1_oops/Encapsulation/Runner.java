package com.bytecoder.DesignPatterns.LLD.A1_oops.Encapsulation;

interface Person{
    String company = "Hasheding";
    default String getCompany(){
        return company;
    }

    String getDetails();
}


class Customer implements Person{
    private String name;
    private String address;

    public Customer(String name, String address){
        this.name = name;
        this.address = address;
//        Person.company= "d";
    }

    @Override
    public String getDetails(){
        System.out.println(Person.company);
        return String.format("name : %s and address: %s", name, address);
    }
    @Override
    public String getCompany(){
        System.out.println("Overriding default");
        return company;
    }

}

public class Runner {
    String name = "testing";

    public String getDetails(){
        System.out.println("getting details");
        RunnerHelper runnerHelper = new RunnerHelper();
        runnerHelper.print();
        return "";
    }
    class RunnerHelper{
       public void print(){
           System.out.println("printing from outer class " + name);
       }
    }

    public static void main(String[] args) {
        Customer c = new Customer("d","ddd");
        System.out.println(c.getDetails());
        System.out.println(c.getCompany());

        Runner runner = new Runner();

        runner.getDetails();

        RunnerHelper runnerHelper = runner.new RunnerHelper();
        runnerHelper.print();


    }
}
