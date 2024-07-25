package com.bytecoder.DesignPatterns.creational;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public
class BankAccount implements Cloneable {
    private String bankName;

    private String accountNumber;

    private String username;

    @Override
    public BankAccount clone() throws CloneNotSupportedException {
        return (BankAccount) super.clone();
//        return new BankAccount(this.bankName, this.accountNumber, this.username);
    }
}
