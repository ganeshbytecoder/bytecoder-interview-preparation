package com.bytecoder.DesignPatterns.structural.composite.staticLevel;

import java.util.ArrayList;
import java.util.List;

public class LeafHierarchy implements Hierarchy {
    private String levelName = "";

    private String code;

    private String levelRole = "Center";

    private User agent;

    public LeafHierarchy(User agent) {
        this.agent = agent;
    }

    private List<String> customers = new ArrayList<>();

    public LeafHierarchy addCustomer(String child) {
        customers.add(child);
        return this;
    }

    @Override
    public void printHierarchyDetails() {
        System.out.println("name " + agent.name + " role " + levelName);

        for (String user : customers) {
            System.out.println("--------- customer name  " + user);

        }
    }
}
