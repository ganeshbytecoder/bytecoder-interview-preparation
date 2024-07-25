Bridge is a structural design pattern that lets you split a large class or a set of closely related classes into two separate hierarchies—abstraction 
and implementation—which can be developed independently of each other.

* connecting components together through abstractions
* payment using multiple banks -> UPI implements PaymentMode , Cards, Netbanking
* YesBankPaymentService(PaymentMode)
* ICICIBankPaymentService(PaymentMode)

* Note - when you have choice like combination between two or more one type and two or more other type of implementation using bridge using composition not inheritance
* examples :
* separate class hierarchies. so that the original classes will reference an object of the new hierarchy, and use composite to work them 
  * TVs() and remotes(digital or normal)
  * Pizza types (veg, non-veg , egg) and bread-types 
  * Use the Bridge pattern when you want to divide and organize a monolithic class that has several variants of some functionality 
  * (for example, if the class can work with various database servers).
    * The Bridge pattern lets you split the monolithic class into several class hierarchies.
    After this, you can change the classes in each hierarchy independently of the classes in the others. This approach simplifies code maintenance and minimizes the risk of breaking existing code.