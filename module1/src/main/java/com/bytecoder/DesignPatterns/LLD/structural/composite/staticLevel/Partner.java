package com.bytecoder.DesignPatterns.LLD.structural.composite.staticLevel;

public class Partner {
    private String name;

    RootNode rootNode;

    public Partner(String name, RootNode rootNode) {
        this.name = name;
        this.rootNode = rootNode;
    }



    public void printUserHierarchy() {
        System.out.println("Print hierarchy " + name);
        rootNode.printHierarchyDetails();
    }
}
