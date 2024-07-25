package com.gschoudhary.design.patterns.structural.composite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bytecoder.design.patterns.structural.composite.staticLevel.*;

//problem
// create hierarchy
// get hierarchy of that level or user
//
class PartnerTest {
    RootNode ceo = new RootNode(new User("sucharita", "ceo"), "INDIAN HEAD");

    Partner sonata = new Partner("sonata", ceo);

    @BeforeEach
    void setUp() {
    }

    @Test
    public void createPartnerHierarchy() {
        ceo.addChild(new ChildNode(new User("natasha", "cto"), "National head")
                .addChild(new ChildNode(new User("jagan", "Senior engineering head"), "TechHead")
                        .addChild(new LeafHierarchy(new User("ganesh singh", "agent")).addCustomer("anurag")))
                .addChild(new ChildNode(new User("Sreehari", "engineering head"), "TechHead")));
        ceo.addChild(new ChildNode(new User("vipul", "CBO"), "National head"));
        ceo.addChild(new ChildNode(new User("punit", "CFO"), "National head"));
        sonata.printUserHierarchy();

    }

    @Test
    public void printUserHierarchy() {
        System.out.println("print hierarchy of a user");
    }
}