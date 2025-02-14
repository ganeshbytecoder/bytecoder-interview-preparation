package com.bytecoder.DesignPatterns.LLD.creational;

public class Prototype {
    //    Prototype design pattern is used when the Object creation is a costly affair and requires a lot of time and resources
//    and you have a similar object already existing. Prototype pattern provides a mechanism to copy the original object to
//    a new object and then modify it according to our needs.
    public static void main(String[] args) throws CloneNotSupportedException {
        BankAccount bankAccount = new BankAccount("com/gschoudhary/io", "com/gschoudhary/io", "com/gschoudhary/io");
        System.out.println(bankAccount);
        bankAccount = bankAccount.clone();
        bankAccount.setBankName("clone");
        System.out.println(bankAccount);
    }
}
