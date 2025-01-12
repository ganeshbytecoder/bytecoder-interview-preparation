package com.bytecoder.DesignPatterns.oops.inheritanceAndAbstraction;

/**
 * This example demonstrates multiple inheritance through interfaces,
 * including conflict resolution and the use of default methods.
 */
public class MultipleInheritanceExample {

    // Worker capability
    interface Worker {
        default void work() {
            System.out.println("Working...");
        }
        
        default void takeBreak() {
            System.out.println("Taking a break");
        }
        
        void getSalary();
    }

    // Manager capability
    interface Manager {
        default void work() {
            System.out.println("Managing...");
        }
        
        void assignWork(Worker worker);
        
        default void attendMeeting() {
            System.out.println("Attending management meeting");
        }
    }

    // Remote work capability
    interface RemoteWorker {
        default void work() {
            System.out.println("Working remotely...");
        }
        
        default void attendVirtualMeeting() {
            System.out.println("Attending virtual meeting");
        }
    }

    // Demonstrating multiple inheritance and conflict resolution
    static class TeamLead implements Worker, Manager, RemoteWorker {
        private double salary;

        public TeamLead(double salary) {
            this.salary = salary;
        }

        // Must override work() due to conflict between Worker, Manager, and RemoteWorker
        @Override
        public void work() {
            // Can call specific interface's default implementation
            System.out.println("Team Lead working:");
            Manager.super.work();
            RemoteWorker.super.work();
        }

        @Override
        public void getSalary() {
            System.out.println("Salary: " + salary);
        }

        @Override
        public void assignWork(Worker worker) {
            System.out.println("Assigning work to team member");
            worker.work();
        }

        // Combining capabilities from multiple interfaces
        public void performDailyRoutine() {
            work();                 // Overridden method
            attendMeeting();        // From Manager
            attendVirtualMeeting(); // From RemoteWorker
            takeBreak();           // From Worker
        }
    }

    // Regular employee implementing Worker and RemoteWorker
    static class Developer implements Worker, RemoteWorker {
        private double salary;

        public Developer(double salary) {
            this.salary = salary;
        }

        // Choosing RemoteWorker's work implementation
        @Override
        public void work() {
            RemoteWorker.super.work();
        }

        @Override
        public void getSalary() {
            System.out.println("Developer salary: " + salary);
        }

        // Additional developer-specific method
        public void code() {
            System.out.println("Writing code...");
            work();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Team Lead Example ===");
        TeamLead lead = new TeamLead(100000);
        lead.performDailyRoutine();
        lead.getSalary();

        System.out.println("\n=== Developer Example ===");
        Developer dev = new Developer(80000);
        dev.code();
        dev.attendVirtualMeeting();
        dev.takeBreak();
        
        System.out.println("\n=== Team Interaction ===");
        lead.assignWork(dev);
    }
}
