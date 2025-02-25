

Let's illustrate the query with a concrete example. Assume we have the following sample data in our tables:

---

### Sample Data

#### Employees Table

| employee_id | name    | position | region | manager_id |
|-------------|---------|----------|--------|------------|
| 1           | Alice   | CEO      | Global | NULL       |
| 2           | Bob     | Manager  | North  | 1          |
| 3           | Charlie | Employee | North  | 2          |
| 4           | Diana   | Employee | North  | 2          |
| 5           | Eve     | Manager  | South  | 1          |

> **Note:** In this example, we are focusing on Manager Bob (employee_id = 2) who works in the **North** region.

#### Customers Table

| customer_id | name           | region | assigned_employee_id |
|-------------|----------------|--------|----------------------|
| C001        | North Corp     | North  | 2                    |
| C002        | Charlie LLC    | North  | 3                    |
| C003        | Diana's Group  | North  | 4                    |
| C004        | South Ventures | South  | 5                    |

---

### The Query

Now, let’s look at the query:

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

Assume we set `:manager_id = 2` (for Manager Bob).

---

### Step-by-Step Explanation

#### 1. Building the Subordinate List (Recursive CTE)

- **Base Case:**
  ```sql
  SELECT employee_id
  FROM employees
  WHERE employee_id = 2;
  ```
  This returns:
    - **Bob** (employee_id: 2)

- **Recursive Case:**
  The query then finds employees whose `manager_id` is in the current list.
  ```sql
  SELECT e.employee_id
  FROM employees e
  INNER JOIN subordinate_ids s ON e.manager_id = s.employee_id;
  ```
    - For Bob (employee_id: 2), it finds:
        - **Charlie** (employee_id: 3, manager_id: 2)
        - **Diana** (employee_id: 4, manager_id: 2)

- **Resulting List:**  
  The `subordinate_ids` CTE will now contain:
    - 2 (Bob)
    - 3 (Charlie)
    - 4 (Diana)

#### 2. Fetching the Manager's Region

The subquery:
```sql
(SELECT region FROM employees WHERE employee_id = 2)
```
- Returns **North** because Bob’s region is North.

#### 3. Main Query: Filtering Customers

The main query selects customers that satisfy two conditions:

1. **Region Match:**
   ```sql
   WHERE c.region = 'North'
   ```
    - This ensures only customers in the North region are considered.

2. **Assigned Employee is in the Subordinate List:**
   ```sql
   AND c.assigned_employee_id IN (SELECT employee_id FROM subordinate_ids)
   ```
    - This ensures the customer is assigned to either Bob, Charlie, or Diana (employee_ids 2, 3, or 4).

Now, looking at our sample customer records:

- **C001 – North Corp:**
    - Region: North
    - assigned_employee_id: 2 (Bob)  
      **Matches** both conditions.

- **C002 – Charlie LLC:**
    - Region: North
    - assigned_employee_id: 3 (Charlie)  
      **Matches** both conditions.

- **C003 – Diana's Group:**
    - Region: North
    - assigned_employee_id: 4 (Diana)  
      **Matches** both conditions.

- **C004 – South Ventures:**
    - Region: South
    - assigned_employee_id: 5 (Eve)  
      **Fails** the region condition (South ≠ North) and is not in the subordinate list.

---

### Final Outcome

For Manager Bob (employee_id = 2), the query returns the following customers:
- **North Corp (C001)**
- **Charlie LLC (C002)**
- **Diana's Group (C003)**

These customers are in the North region and are assigned to Bob or his direct subordinates.

---

### Summary

1. **Recursive CTE:**  
   Collects all employee IDs under the manager (Bob, in this case), including Bob himself and his subordinates.
2. **Region Filtering:**  
   Retrieves Bob's region and ensures that only customers from the same region are returned.
3. **Customer Assignment Filtering:**  
   Ensures that the customer is assigned to one of the employees in Bob’s hierarchy.




Below is an example of how you can implement the given PostgreSQL recursive query in a Spring Boot application using Hibernate. This example includes entity definitions, a repository with a native query, and a service that calls the repository method.

---

### 1. Define the Entities

#### Employee Entity

```java
import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private String position;

    @Column(name = "region")
    private String region;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    // Getters and setters

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}
```

#### Customer Entity

```java
import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @Column(name = "customer_id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "region")
    private String region;

    @Column(name = "assigned_employee_id")
    private Integer assignedEmployeeId;

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
       this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
       this.name = name;
    }

    public String getRegion() {
       return region;
    }

    public void setRegion(String region) {
       this.region = region;
    }

    public Integer getAssignedEmployeeId() {
       return assignedEmployeeId;
    }

    public void setAssignedEmployeeId(Integer assignedEmployeeId) {
       this.assignedEmployeeId = assignedEmployeeId;
    }
}
```

---

### 2. Create the Repository

Use a native query (with PostgreSQL syntax) inside a Spring Data JPA repository. The query uses a recursive CTE to gather the manager’s subordinate IDs and then filters the customers based on the manager’s region and assignment.

```java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query(value = "WITH RECURSIVE subordinate_ids AS ( " +
                   "    SELECT employee_id " +
                   "    FROM employees " +
                   "    WHERE employee_id = :managerId " +
                   "    UNION ALL " +
                   "    SELECT e.employee_id " +
                   "    FROM employees e " +
                   "    INNER JOIN subordinate_ids s ON e.manager_id = s.employee_id " +
                   ") " +
                   "SELECT c.* " +
                   "FROM customers c " +
                   "WHERE c.region = ( " +
                   "    SELECT region " +
                   "    FROM employees " +
                   "    WHERE employee_id = :managerId " +
                   ") " +
                   "AND c.assigned_employee_id IN ( " +
                   "    SELECT employee_id FROM subordinate_ids " +
                   ")", nativeQuery = true)
    List<Customer> findCustomersForManager(@Param("managerId") Integer managerId);
}
```

---

### 3. Create a Service to Use the Repository

The service layer calls the repository method to fetch the customers for a given manager.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getCustomersForManager(Integer managerId) {
        return customerRepository.findCustomersForManager(managerId);
    }
}
```

---

### 4. (Optional) Expose the Service via a Controller

If you want to provide a REST endpoint to fetch customers for a manager, you can create a simple controller:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/manager/{managerId}")
    public List<Customer> getCustomersForManager(@PathVariable Integer managerId) {
        return customerService.getCustomersForManager(managerId);
    }
}
```

---

### Explanation

- **Entities:**  
  The `Employee` and `Customer` classes are mapped to the `employees` and `customers` tables, respectively. The `Employee` entity includes a self-referential relationship via the `manager` field.

- **Repository with Native Query:**  
  The `CustomerRepository` interface extends `JpaRepository` and contains a method annotated with `@Query` using a native PostgreSQL query. This query:
    - Uses a recursive CTE (`subordinate_ids`) to collect all employees under a specified manager (including the manager).
    - Fetches the manager’s region.
    - Returns customers whose region matches the manager’s region and whose assigned employee is in the list of subordinate IDs.

- **Service & Controller:**  
  The `CustomerService` calls the repository method, and the `CustomerController` provides an endpoint to fetch the data.

This setup demonstrates how to integrate a complex, recursive query into a Spring Boot application using Hibernate and Spring Data JPA, ensuring that role-based and region-based customer access is efficiently handled in your application.