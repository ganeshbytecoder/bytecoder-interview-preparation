Composite is a structural design pattern that lets you compose objects into tree structures and then work with these structures as if they were individual objects.

* same interface is implemented (will contain list of same object)
* Use the Composite pattern when you have to implement a tree-like object structure.
  * The Composite pattern provides you with two basic element types that share a common interface: simple leaves and complex containers. A container can be composed of both leaves and other containers. This lets you construct a nested recursive object structure that resembles a tree.
* Use the pattern when you want the client code to treat both simple and complex elements uniformly.
* How to Implement (compositeClass-> (hierarchy interface and its implementation) -> leafClass implements Hierarchy interface
  * Make sure that the core model of your app can be represented as a tree structure. Try to break it down into simple elements and containers. Remember that containers must be able to contain both simple elements and other containers.

  * Declare the component interface with a list of methods that make sense for both simple and complex components.

  * Create a leaf class to represent simple elements. A program may have multiple different leaf classes.

  * Create a container class to represent complex elements. In this class, provide an array field for storing references to sub-elements. The array must be able to store both leaves and containers, so make sure it’s declared with the component interface type.

  * While implementing the methods of the component interface, remember that a container is supposed to be delegating most of the work to sub-elements.

  * Finally, define the methods for adding and removal of child elements in the container.


* Examples:
  * item and boxes problem -> interface getprice()
  * Armies of most countries are structured as hierarchies.An army consists of several divisionHierarchies; a division is a set of brigades, and a brigade consists of platoons, which can be broken down into squads. Finally, a squad is a small group of real soldiers. Orders are given at the top of the hierarchy and passed down onto each level until every soldier knows what needs to be done.
  * Country-> states-> district hierarchy 


                Company
                  CEO
      CTO         CBO         CFO

  

Note: head will have all the child access
    