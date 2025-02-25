Below are the revised and structured notes for the Composite design pattern:

---

# Composite Design Pattern – Detailed Notes

**Definition:**  
Composite is a **structural design pattern** that allows you to build complex object structures (typically in a tree format) and work with these structures uniformly—as if they were individual objects.

---

## Key Concepts

- **Component Interface:**  
  All elements (both simple and complex) implement a common interface. This ensures that the client code can interact with them uniformly.

- **Leaf:**  
  Represents a simple element that does not have any children. Leaves implement the component interface and contain the actual data or behavior.

- **Composite (Container):**  
  Represents a complex element that may have children (which can be either leaves or other composites). It maintains a list (or collection) of components and delegates work to its children.

---

## When to Use the Composite Pattern

- **Tree-Like Structures:**  
  When you need to model a hierarchical structure (like organizational charts, file systems, or UI components) where components can be composed recursively.

- **Uniformity in Client Code:**  
  When the client code should treat individual objects and compositions of objects in the same way, without needing to know if it's dealing with a simple element or a container.

---

## Implementation Guidelines

1. **Define the Component Interface:**
  - List all the methods that both simple elements (leaves) and complex elements (composites) should implement.
  - Example methods might include operations like `displayDetails()`, `getPrice()`, or any behavior that fits the domain.

2. **Create the Leaf Class(es):**
  - Implements the component interface.
  - Represents the simplest objects in the hierarchy.
  - Can be one or more types, depending on the system requirements.

3. **Create the Composite (Container) Class:**
  - Implements the component interface.
  - Contains an array or list that holds references to other components (both leaves and composites).
  - Provides methods to add or remove child components.
  - Delegates calls to its child components for operations that are common to both leaves and containers.

4. **Design Considerations:**
  - Ensure that the core model of your application can be represented as a tree structure.
  - Containers should be able to hold both simple elements and other containers.
  - When implementing methods in the composite, remember that the container often delegates the work to its children, thus encapsulating the tree traversal logic.

---

## Examples

### 1. Item and Boxes Problem
- **Scenario:**  
  Imagine a scenario where you have individual items and boxes that can contain both individual items and other boxes.
- **Key Operation:**  
  Each element might implement a method like `getPrice()`, and boxes calculate their price by summing up the prices of their contained items and sub-boxes.

### 2. Military Hierarchy
- **Scenario:**  
  An army's organizational structure:
  - **Army:** Composed of several divisions.
  - **Division:** Composed of multiple brigades.
  - **Brigade:** Composed of several platoons.
  - **Platoon:** Composed of squads.
  - **Squad:** A small unit of soldiers.
- **Key Operation:**  
  Orders and commands issued at the top level are delegated down through each level of the hierarchy.

### 3. Geographical Hierarchy
- **Scenario:**  
  Modeling regions:
  - **Country:** Contains states.
  - **State:** Contains districts.
- **Key Operation:**  
  Operations like aggregating data or applying policies can be performed uniformly across the hierarchy.

### 4. Company Organizational Structure
- **Scenario:**  
  A typical corporate hierarchy:
  ```
                Company
                   CEO
      CTO         CBO         CFO
      (Assistants, Managers, Deputies, etc.)
  ```
- **Key Points:**
  - The **CEO** (head) has access to all subordinate levels.
  - Regional or departmental heads (e.g., Head of India) can have their own hierarchy:
    ```
                Company
                Head of India
      (States, branches, and individuals within each branch)
    ```
  - This structure ensures that higher-level managers have control over a large scope, while lower-level managers or employees have limited or no access to certain data (e.g., sensitive customer or operational information).

---

## Summary

- **Uniformity:**  
  Every element in the composite structure adheres to the same interface, making client operations simpler and more consistent.

- **Flexibility:**  
  The pattern allows complex hierarchies to be built easily and supports dynamic addition or removal of components.

- **Delegation:**  
  Composites delegate operations to their children, enabling recursive traversal and manipulation of the entire structure.

- **Applicability:**  
  Use the Composite pattern when your application's core model naturally forms a tree structure and when you need to treat individual objects and compositions uniformly.

These notes provide a comprehensive overview of the Composite design pattern, its implementation, and real-life applications, ensuring a clear understanding of how and when to use this pattern in software design.

Below are two Java examples demonstrating the Composite design pattern applied in different domains. One example is tailored for an e-commerce system, and the other is designed for a fintech (investment portfolio) scenario.

---

## E-Commerce Example

In an e-commerce context, you can use the Composite pattern to model a store’s product hierarchy. In this example, individual products (leaves) and product categories (composites) are represented through a common interface. This allows a category to contain both individual products and sub-categories.

```java
import java.util.ArrayList;
import java.util.List;

// Component Interface
interface ProductComponent {
    double getPrice();
    void displayDetails();
}

// Leaf: Individual Product
class Product implements ProductComponent {
    private String name;
    private double price;
    
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    @Override
    public double getPrice() {
        return price;
    }
    
    @Override
    public void displayDetails() {
        System.out.println("Product: " + name + " - Price: $" + price);
    }
}

// Composite: Product Category that can contain Products or other Categories
class ProductCategory implements ProductComponent {
    private String categoryName;
    private List<ProductComponent> products = new ArrayList<>();
    
    public ProductCategory(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public void add(ProductComponent product) {
        products.add(product);
    }
    
    public void remove(ProductComponent product) {
        products.remove(product);
    }
    
    @Override
    public double getPrice() {
        double total = 0;
        for (ProductComponent product : products) {
            total += product.getPrice();
        }
        return total;
    }
    
    @Override
    public void displayDetails() {
        System.out.println("Category: " + categoryName);
        for (ProductComponent product : products) {
            product.displayDetails();
        }
    }
}

// Demo class to illustrate usage
public class ECommerceDemo {
    public static void main(String[] args) {
        // Create individual products
        ProductComponent laptop = new Product("Laptop", 1200.00);
        ProductComponent smartphone = new Product("Smartphone", 800.00);
        ProductComponent headphones = new Product("Headphones", 150.00);
        
        // Create a category and add products to it
        ProductCategory electronics = new ProductCategory("Electronics");
        electronics.add(laptop);
        electronics.add(smartphone);
        electronics.add(headphones);
        
        // Create more individual products
        ProductComponent chair = new Product("Chair", 85.00);
        ProductComponent table = new Product("Table", 200.00);
        
        // Create another category
        ProductCategory furniture = new ProductCategory("Furniture");
        furniture.add(chair);
        furniture.add(table);
        
        // Create a top-level category that represents the whole store
        ProductCategory store = new ProductCategory("Online Store");
        store.add(electronics);
        store.add(furniture);
        
        // Display details of the store structure
        store.displayDetails();
        System.out.println("Total Store Price: $" + store.getPrice());
    }
}
```

### Explanation

- **ProductComponent Interface:** Defines the common operations for both individual products and categories.
- **Product (Leaf):** Implements the interface for basic products.
- **ProductCategory (Composite):** Manages a list of `ProductComponent` objects and performs operations like calculating the total price by delegating to its children.
- **ECommerceDemo:** Demonstrates building a composite structure (store hierarchy) and performing operations uniformly on both individual products and composite categories.

---

## Fintech Example

In a fintech scenario, consider modeling an investment portfolio. Each investment (stock, bond, etc.) is a leaf, while a portfolio (which may consist of several investments or even sub-portfolios) acts as a composite. This structure allows you to calculate the total value and display details for an entire portfolio.

```java
import java.util.ArrayList;
import java.util.List;

// Component Interface
interface InvestmentComponent {
    double getValue();
    void displayDetails();
}

// Leaf: Individual Investment
class Investment implements InvestmentComponent {
    private String name;
    private double value;
    
    public Investment(String name, double value) {
        this.name = name;
        this.value = value;
    }
    
    @Override
    public double getValue() {
        return value;
    }
    
    @Override
    public void displayDetails() {
        System.out.println("Investment: " + name + " - Value: $" + value);
    }
}

// Composite: Portfolio that can contain multiple investments or sub-portfolios
class Portfolio implements InvestmentComponent {
    private String portfolioName;
    private List<InvestmentComponent> investments = new ArrayList<>();
    
    public Portfolio(String portfolioName) {
        this.portfolioName = portfolioName;
    }
    
    public void addInvestment(InvestmentComponent investment) {
        investments.add(investment);
    }
    
    public void removeInvestment(InvestmentComponent investment) {
        investments.remove(investment);
    }
    
    @Override
    public double getValue() {
        double totalValue = 0;
        for (InvestmentComponent inv : investments) {
            totalValue += inv.getValue();
        }
        return totalValue;
    }
    
    @Override
    public void displayDetails() {
        System.out.println("Portfolio: " + portfolioName);
        for (InvestmentComponent inv : investments) {
            inv.displayDetails();
        }
    }
}

// Demo class to illustrate usage
public class FintechDemo {
    public static void main(String[] args) {
        // Create individual investments
        InvestmentComponent appleStock = new Investment("Apple Inc.", 15000);
        InvestmentComponent googleStock = new Investment("Google Inc.", 20000);
        InvestmentComponent bond = new Investment("Government Bond", 5000);
        
        // Create a composite portfolio for tech investments
        Portfolio techPortfolio = new Portfolio("Tech Investments");
        techPortfolio.addInvestment(appleStock);
        techPortfolio.addInvestment(googleStock);
        
        // Create a diversified portfolio that includes the tech portfolio and another investment
        Portfolio diversifiedPortfolio = new Portfolio("Diversified Portfolio");
        diversifiedPortfolio.addInvestment(techPortfolio);
        diversifiedPortfolio.addInvestment(bond);
        
        // Display details of the portfolio structure
        diversifiedPortfolio.displayDetails();
        System.out.println("Total Portfolio Value: $" + diversifiedPortfolio.getValue());
    }
}
```

### Explanation

- **InvestmentComponent Interface:** Declares methods to get the investment value and display investment details.
- **Investment (Leaf):** Represents an individual financial asset.
- **Portfolio (Composite):** Manages a collection of `InvestmentComponent` objects (which can be individual investments or other portfolios) and computes the total value by aggregating the values of its children.
- **FintechDemo:** Demonstrates the use of the composite structure to model a portfolio hierarchy and perform operations uniformly across the structure.

---

These examples illustrate how the Composite design pattern can simplify the handling of hierarchical data structures in both e-commerce and fintech applications. Each example allows clients to treat individual elements and compositions uniformly, promoting flexibility and scalability in design.




Below is an enhanced Java example that extends the employee hierarchy composite pattern by adding customer details access based on an employee’s role and region. In this example:

- **CEO**: Can view all customers in the company (their own plus those accessible via subordinates).
- **Manager**: Can see only the customers assigned to them that match their designated region (and, optionally, those from subordinate managers if desired).
- **Developer (or others)**: Do not have access to any customer details.

The code is organized into the following sections:
1. **Customer Class** – Represents a customer with an ID, name, and region.
2. **EmployeeComponent Interface** – Declares common methods for employees.
3. **Employee Class** – Implements the composite pattern and adds customer handling.
4. **Demo Class** – Builds the hierarchy and demonstrates how customer access works.

---

```java
import java.util.ArrayList;
import java.util.List;

// 1. Customer class representing a customer with an ID, name, and region.
class Customer {
    private String id;
    private String name;
    private String region;

    public Customer(String id, String name, String region) {
        this.id = id;
        this.name = name;
        this.region = region;
    }

    public String getRegion() {
        return region;
    }
    
    public void display() {
        System.out.println("Customer: " + name + " (ID: " + id + ", Region: " + region + ")");
    }
}

// 2. EmployeeComponent interface declares common methods for employees.
interface EmployeeComponent {
    void showEmployeeDetails();
    void displayCustomerDetails();
    void add(EmployeeComponent employee);  // For composite behavior.
}

// 3. Employee class implementing the composite design pattern with customer access logic.
class Employee implements EmployeeComponent {
    private String name;
    private String position; // e.g., CEO, Manager, Developer, etc.
    private String region;   // Applicable for roles like Manager.
    private List<EmployeeComponent> subordinates;
    private List<Customer> customers;

    // Constructor for roles with an associated region.
    public Employee(String name, String position, String region) {
        this.name = name;
        this.position = position;
        this.region = region;
        this.subordinates = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    // Overloaded constructor for roles that do not have a region.
    public Employee(String name, String position) {
        this(name, position, null);
    }

    // Composite operation: add a subordinate.
    @Override
    public void add(EmployeeComponent employee) {
        subordinates.add(employee);
    }

    // Composite operation: remove a subordinate.
    public void remove(EmployeeComponent employee) {
        subordinates.remove(employee);
    }

    // Add a customer to this employee's accessible list.
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Display employee details recursively.
    @Override
    public void showEmployeeDetails() {
        System.out.println(position + ": " + name + (region != null ? " (" + region + ")" : ""));
        for (EmployeeComponent subordinate : subordinates) {
            subordinate.showEmployeeDetails();
        }
    }

    // Display customer details based on role and region.
    @Override
    public void displayCustomerDetails() {
        if (position.equalsIgnoreCase("CEO")) {
            // CEO can see all customers: own and from all subordinates.
            System.out.println("\nAccessible customers for " + name + " (" + position + "):");
            for (Customer c : customers) {
                c.display();
            }
            for (EmployeeComponent subordinate : subordinates) {
                subordinate.displayCustomerDetails();
            }
        } else if (position.equalsIgnoreCase("Manager")) {
            // Manager can see customers only if they match their region.
            System.out.println("\nAccessible customers for " + name + " (" + position + ", Region: " + region + "):");
            for (Customer c : customers) {
                if (region != null && region.equalsIgnoreCase(c.getRegion())) {
                    c.display();
                }
            }
            // Optionally, if subordinate managers exist, they might also have relevant customers.
            for (EmployeeComponent subordinate : subordinates) {
                subordinate.displayCustomerDetails();
            }
        } else {
            // Other roles (e.g., Developer) do not have access to customer details.
            System.out.println("\nNo customer access for " + name + " (" + position + ").");
        }
    }
}

// 4. Demo class illustrating the employee hierarchy and customer access control.
public class EmployeeCustomerAccessDemo {
    public static void main(String[] args) {
        // Create the top-level executive (CEO).
        EmployeeComponent ceo = new Employee("Alice", "CEO");

        // Create managers for different regions.
        Employee managerNorth = new Employee("Bob", "Manager", "North");
        Employee managerSouth = new Employee("Carol", "Manager", "South");

        // Create developers (or other roles without customer access).
        Employee developer1 = new Employee("David", "Developer");
        Employee developer2 = new Employee("Eve", "Developer");

        // Build the employee hierarchy.
        ceo.add(managerNorth);
        ceo.add(managerSouth);
        managerNorth.add(developer1);
        managerSouth.add(developer2);

        // Assign customers to various employees.
        // CEO gets a global customer.
        ((Employee) ceo).addCustomer(new Customer("C001", "Global Corp", "Global"));

        // Manager in the North region gets several customers.
        managerNorth.addCustomer(new Customer("C002", "North Co", "North"));
        managerNorth.addCustomer(new Customer("C003", "Northern Traders", "North"));
        // A customer that does not match the manager's region.
        managerNorth.addCustomer(new Customer("C004", "South Ventures", "South"));

        // Manager in the South region gets customers.
        managerSouth.addCustomer(new Customer("C005", "South Enterprises", "South"));
        managerSouth.addCustomer(new Customer("C006", "Southern Distributors", "South"));

        // Display the employee hierarchy.
        System.out.println("Employee Hierarchy:");
        ceo.showEmployeeDetails();

        // Display customer access based on role.
        System.out.println("\n---\n");
        System.out.println("Customer Access for CEO:");
        ceo.displayCustomerDetails();

        System.out.println("\nCustomer Access for Manager North:");
        managerNorth.displayCustomerDetails();

        System.out.println("\nCustomer Access for Developer1:");
        developer1.displayCustomerDetails();
    }
}
```

---

### Explanation

- **Customer Class:**  
  Defines a customer with an identifier, name, and region. The `display()` method prints the customer details.

- **EmployeeComponent Interface:**  
  Declares two key methods: one for showing employee details and another for displaying accessible customer details. The `add()` method is declared for composite behavior.

- **Employee Class:**
  - Holds common information (name, position, region).
  - Maintains lists of subordinates and customers.
  - The `showEmployeeDetails()` method recursively prints the hierarchy.
  - The `displayCustomerDetails()` method checks the employee’s role:
    - **CEO**: Recursively displays all customers from itself and its subordinate chain.
    - **Manager**: Displays only customers that match the manager’s region.
    - **Developer and others**: Indicate that no customer access is allowed.

- **EmployeeCustomerAccessDemo Class:**  
  Sets up a sample hierarchy with a CEO, two managers (North and South), and developers under each manager. It then assigns customers accordingly and demonstrates how each role accesses customer data.

This example shows how you can combine the Composite design pattern with role-based access control, enabling hierarchical employee structures to have differentiated access to customer details based on both role and regional responsibility.



--- 
## schema

Below is an example PostgreSQL schema designed to represent an employee hierarchy (with roles and regions) and customer assignments, along with optimized queries to quickly fetch customer records based on an employee’s role.

---

## PostgreSQL Schema

### Employees Table

This table represents employees. It includes a self-referencing column (`manager_id`) so that you can build a hierarchical structure (e.g., CEO → Manager → Developer).

```sql
CREATE TABLE employees (
    employee_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    position VARCHAR(50) NOT NULL,
    region VARCHAR(50),
    manager_id INT REFERENCES employees(employee_id)
);
```

### Customers Table

This table stores customer information. Each customer is assigned to an employee (via `assigned_employee_id`). We also store the customer’s region for filtering purposes.

```sql
CREATE TABLE customers (
    customer_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    region VARCHAR(50) NOT NULL,
    assigned_employee_id INT REFERENCES employees(employee_id)
);
```

### Indexes for Fast Queries

Adding indexes on frequently filtered columns (like region and assigned employee) improves query performance.

```sql
CREATE INDEX idx_customers_region ON customers(region);
CREATE INDEX idx_customers_assigned_employee ON customers(assigned_employee_id);
```

---

## Fast Fetch Queries

### 1. Query for a CEO

A CEO is allowed to see all customers in the company regardless of region or assignment. This query simply returns all customer records.

```sql
SELECT *
FROM customers;
```

### 2. Query for a Manager

A Manager can only access customers that belong to their designated region. In addition, they should see customers assigned to themselves as well as to any subordinates within their branch of the hierarchy. The following recursive query first builds a list of subordinate employee IDs (starting with the manager’s own ID) and then filters customers by the manager’s region.

Assume `:manager_id` is a parameter (the manager’s employee ID):

```sql
WITH RECURSIVE subordinate_ids AS (
    -- Start with the manager
    SELECT employee_id
    FROM employees
    WHERE employee_id = :manager_id
    UNION ALL
    -- Recursively add direct reports
    SELECT e.employee_id
    FROM employees e
    INNER JOIN subordinate_ids s ON e.manager_id = s.employee_id
)
SELECT c.*
FROM customers c
WHERE c.region = (
    SELECT region
    FROM employees
    WHERE employee_id = :manager_id
)
AND c.assigned_employee_id IN (
    SELECT employee_id FROM subordinate_ids
);
```

### 3. Query for a Developer (or Other Non-Managerial Roles)

Since developers (or similar roles) do not have access to customer details, you can simply return no records. This might also be enforced at the application layer.

```sql
SELECT *
FROM customers
WHERE false;
```

---

## Explanation

- **Employees Table:**  
  Each employee is identified by `employee_id` and may reference their direct manager via `manager_id`. The `region` field is used for roles like Manager, where access is region-based.

- **Customers Table:**  
  Each customer is assigned an employee and has a `region` property. This enables filtering by region.

- **Indexes:**  
  Indexes on `region` and `assigned_employee_id` help the database quickly locate the relevant customer records during queries.

- **CEO Query:**  
  The CEO’s query is straightforward as it fetches all rows from the `customers` table.

- **Manager Query:**  
  The recursive CTE (`subordinate_ids`) gathers all subordinate employee IDs (including the manager’s own ID) from the hierarchy. The query then fetches customers who are in the same region as the manager and whose `assigned_employee_id` appears in this list.

- **Developer Query:**  
  For roles without customer access, the query is set to always return an empty result.

These schema definitions and queries are designed to provide efficient data access while supporting the hierarchical and role-based customer access requirements of the composite design pattern example.

