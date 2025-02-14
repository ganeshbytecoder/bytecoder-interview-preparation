package com.bytecoder.DesignPatterns.LLD.bahavioral.state.withenum;

public enum CustomerStates2 {
    INITIAL {
        @Override
        public CustomerStates2 performAction(Customer2 customer) {
            stateContextService.perform(CustomerStates2.INITIAL);

            return customer.getCustomerState();
        }

        @Override
        public Customer2 setNextState(Customer2 customer) {
            customer.setCustomerState(CustomerStates2.ACCOUNT_CREATED);
            return customer;
        }
    }, ACCOUNT_CREATED() {
        @Override
        public CustomerStates2 performAction(Customer2 customer) {
            stateContextService.perform(CustomerStates2.ACCOUNT_CREATED);

            return customer.getCustomerState();
        }

        @Override
        public Customer2 setNextState(Customer2 customer) {
            customer.setCustomerState(ACCOUNT_VERIFIED);

            return customer;
        }
    }, ACCOUNT_VERIFIED {
        @Override
        public CustomerStates2 performAction(Customer2 customer) {
            stateContextService.perform(CustomerStates2.ACCOUNT_VERIFIED);
            return customer.getCustomerState();
        }

        @Override
        public Customer2 setNextState(Customer2 customer) {
            customer.setCustomerState(READY_TO_PAY);

            return customer;
        }
    }, READY_TO_PAY() {
        @Override
        public CustomerStates2 performAction(Customer2 customer) {
            throw new RuntimeException("All states are done");
        }

        @Override
        public Customer2 setNextState(Customer2 customer) {
            customer.setCustomerState(READY_TO_PAY);

            return customer;
        }
    };

    private static StateContextService stateContextService = new StateContextService();

    public abstract CustomerStates2 performAction(Customer2 customer);

    public abstract Customer2 setNextState(Customer2 customer);

    public static class StateContextService{

        public void perform(CustomerStates2 customerStates2){
            System.out.println("performing action " + customerStates2.name());
        }
    }
}
