* this design patern is to decorate existing functionality
* add additional functionality/enhancement to basic object dynamically 
* this design pattern is used if we want to enhance the
functionality in top of the object
* we can do so using composite only not inheritance or when subclass is not possible
* implementation 
* Decorator is a structural design pattern that lets you attach new behaviors to objects by placing these objects inside special wrapper objects that contain the behaviors.
  * create basic abstract/interface of basic object 
  * implement that basic object
  * create abstractClass extending basic abstract/interface (create constructor with that basic object)
  * implement that decorator for specific enhancement 
  
---
example
* basic burger 
  * extra cheese
  * extra veggies 
  * chicken 
  * paneer
* coffee
  * cream
  * expresso 
  * extra milk 
* person cloths 
  * glasses
  * shoes
* iceCream
  * add chocolate 
  * add nuts
* Car modification
* DOM
  * color
  * font

note - two way to implement it different different warpper classes or one wrapper and all  methods 


note - now using facade make one service with all these functionalities 