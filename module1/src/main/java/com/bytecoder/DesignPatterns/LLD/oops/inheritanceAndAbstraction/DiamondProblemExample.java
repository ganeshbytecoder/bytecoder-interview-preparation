package com.bytecoder.DesignPatterns.LLD.oops.inheritanceAndAbstraction;

/**
 * how to have multiple inheritance in java through interfaces
 * 
 * This example demonstrates how Java handles the diamond problem with interfaces
 * and default methods, showing various scenarios of method resolution.
 */
public class DiamondProblemExample {

    // Base interface with default method
    interface Vehicle {
        default String getDescription() {
            return "I am a vehicle";
        }
        
        void start();
    }

    // First sub-interface
    interface Car extends Vehicle {
        default String getDescription() {
            return "I am a car";
        }
        
        default void horn() {
            System.out.println("Beep beep!");
        }
    }

    // Second sub-interface
    interface Boat extends Vehicle {
        default String getDescription() {
            return "I am a boat";
        }
        
        void horn();

        default void honk() {
            System.out.println("Hoooonk!");
        }
    }

    // Class implementing both interfaces - potential diamond problem
    static class AmphibiousVehicle implements Car, Boat {
        // Must override getDescription due to conflict
        @Override
        public String getDescription() {
            return "I am an amphibious vehicle - " + Car.super.getDescription() + 
                   " and " + Boat.super.getDescription();
        }

        @Override
        public void horn() {
//            Boat.super.horn();  this will not work since it's abstract method
            Car.super.horn();
        }

        @Override
        public void start() {
            System.out.println("Starting both engines...");
        }
        
        // Demonstrating how to call specific interface's default method
        public void makeNoise() {
            System.out.println("Making both noises:");
            honk();  // From Car
            horn();  // From Boat
        }
    }

    public static void main(String[] args) {
        AmphibiousVehicle amphib = new AmphibiousVehicle();
        System.out.println(amphib.getDescription());
        amphib.makeNoise();
        amphib.start();
    }
}
