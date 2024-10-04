package com.bytecoder.DesignPatterns.bahavioral.command;
public class DebitAmountImpl implements PaymentCommand {

    private AccountDetails accountDetails;

    private int amount;

    public DebitAmountImpl(AccountDetails accountDetails, int amount) {
        this.accountDetails = accountDetails;
        this.amount = amount;
    }

    @Override
    public void execute() {
        this.accountDetails.balance = this.accountDetails.balance - this.amount;
    }
}
