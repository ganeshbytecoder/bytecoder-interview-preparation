package com.bytecoder.DesignPatterns.structural.composite.staticLevel;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class ChildNode implements Hierarchy {
    private String levelName;

    private String code;

    private String levelRole;

    private User user;

    private List<Hierarchy> childNodes = new ArrayList<>();


    public ChildNode(User user, String levelName) {
        this.user = user;
        this.levelName = levelName;
    }


    public ChildNode addChild(Hierarchy hierarchy) {
        this.childNodes.add(hierarchy);
        return this;
    }

    @Override
    public void printHierarchyDetails() {

        System.out.println("name " + user.name + " role " + levelName);
        for (Hierarchy c : childNodes) {
            System.out.print("+++++++");
            c.printHierarchyDetails();
        }
        System.out.println();

    }
}
