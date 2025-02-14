package com.bytecoder.DesignPatterns.LLD.bahavioral.state;

 enum CustomerState {

    INITIAL{
        @Override
        public StateContext getStateContext(Customer customer) {
            return new AccountCreateState(customer);
        }
    }, ACCOUNT_CREATED() {
        @Override
        public StateContext getStateContext(Customer customer) {
            return new AccountVerifiedState(customer);
        }
    }, ACCOUNT_VERIFIED {
        @Override
        public StateContext getStateContext(Customer customer) {
            return new AccountReadyState(customer);
        }
    }, READY_TO_PAY() {
        @Override
        public StateContext getStateContext(Customer customer) {
            throw new RuntimeException("All states are done");
        }
    };

    public abstract StateContext getStateContext(Customer customer);

}
