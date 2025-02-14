package com.gschoudhary.design.patterns.creational;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bytecoder.DesignPatterns.LLD.creational.BankAccount;
import com.bytecoder.DesignPatterns.LLD.creational.Person;
import com.bytecoder.DesignPatterns.LLD.creational.Singleton.User;
import com.bytecoder.DesignPatterns.LLD.creational.factorymethod.Bank;
import com.bytecoder.DesignPatterns.LLD.creational.factorymethod.BankFactory;
import com.bytecoder.DesignPatterns.LLD.creational.factorymethod.BankFactoryImpl;
import com.bytecoder.DesignPatterns.LLD.creational.factorymethod.IciciBank;

class CreationalDesignTest {
    @BeforeEach
    void setUp() {
    }

    @Test
    void factoryMethod() {

        BankFactory bankFactory = new BankFactoryImpl();
        Bank bank = bankFactory.getBank("HDFC");
        bank.printDetails();
        bank = bankFactory.getBank("ICICI");
        bank.printDetails();
        Assertions.assertTrue(bank instanceof IciciBank);
    }

    @Test
    void singletonTest() {
        User user = User.getUser();
        System.out.println(user.hashCode());
        user.setName("ganesh");
        Assertions.assertEquals(user.hashCode(), User.getUser().hashCode());

    }

    @Test
    void builder() {
//     m-1
        Person person = Person.builder()
                .setName("ganesh")
                .build();
        System.out.println(person.toString());
//m-2
        Person.PersonBuilder personBuilder = new Person.PersonBuilder();
        person = personBuilder.setName("ram").setCollege("Kal").build();
        System.out.println(person);
    }

    @Test
    void prototype() throws CloneNotSupportedException {
        BankAccount bankAccount = new BankAccount("com/gschoudhary/io", "com/gschoudhary/io", "com/gschoudhary/io");
        System.out.println(bankAccount);
        BankAccount bankAccount2 = bankAccount.clone();
        bankAccount2.setBankName("clone");
        Assertions.assertEquals(bankAccount.getAccountNumber(), bankAccount2.getAccountNumber());
    }

}
