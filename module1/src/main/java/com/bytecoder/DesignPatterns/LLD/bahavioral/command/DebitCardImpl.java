package com.bytecoder.DesignPatterns.LLD.bahavioral.command;
public class DebitCardImpl implements PaymentCommand {

    private AccountDetails accountDetails;

    private int amount;

    public DebitCardImpl(AccountDetails accountDetails, int amount) {
        this.accountDetails = accountDetails;
        this.amount = amount;
    }

    @Override
    public void execute() {
        this.accountDetails.balance = this.accountDetails.balance - this.amount;
    }
}
