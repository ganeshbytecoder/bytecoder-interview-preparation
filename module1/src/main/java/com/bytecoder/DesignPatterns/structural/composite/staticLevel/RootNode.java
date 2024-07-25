package com.bytecoder.DesignPatterns.structural.composite.staticLevel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class RootNode implements Hierarchy {
    private String roleLevel;

    private User ceo;

    private List<Hierarchy> childNodes = new ArrayList<>();

    public RootNode(User user, String roleLevel) {
        this.ceo = user;
        this.roleLevel = roleLevel;
    }

    @Override
    public void printHierarchyDetails() {
        System.out.println("name " + ceo.name + " role  " + roleLevel);
        for (Hierarchy c : childNodes) {
            System.out.print("+");

            c.printHierarchyDetails();
        }
        System.out.println();

    }

    public void addChild(Hierarchy hierarchy) {
        this.childNodes.add(hierarchy);
    }
}
