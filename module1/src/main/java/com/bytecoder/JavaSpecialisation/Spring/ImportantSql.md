### **Temporary Tables in SQL**
A **Temporary Table** is a table that exists only during the session or transaction in which it was created. It helps store intermediate results that can be referenced multiple times within a query or procedure.

Temporary tables are particularly useful for handling large data transformations or when working with stored procedures.

---

## **1. Creating Temporary Tables**
Temporary tables are created using the `CREATE TEMPORARY TABLE` statement.

### **Syntax**
```sql
CREATE TEMPORARY TABLE temp_table_name (
    column1 datatype,
    column2 datatype,
    ...
);
```
Alternatively, you can create a temporary table from an existing query:
```sql
CREATE TEMPORARY TABLE temp_table_name AS
SELECT column1, column2, ...
FROM original_table
WHERE conditions;
```
✅ **Example**
```sql
CREATE TEMPORARY TABLE TempEmployees AS
SELECT employee_id, employee_name, department_id, salary
FROM employees
WHERE salary > 50000;
```
👉 This table will store employees with salaries above 50,000.

---

## **2. Where to Use Temporary Tables?**
Temporary tables are useful in various scenarios:

### **1. Storing Intermediate Query Results**
When performing complex calculations, **temporary tables** help break the query into manageable steps.

✅ **Example: Processing Large Data Before Aggregation**
```sql
CREATE TEMPORARY TABLE TempSales AS
SELECT customer_id, SUM(order_total) AS total_spent
FROM orders
GROUP BY customer_id;

SELECT customer_id, total_spent
FROM TempSales
WHERE total_spent > 5000;
```
👉 This avoids recalculating `SUM(order_total)` repeatedly.

---

### **2. Handling Large Joins Efficiently**
When working with large datasets, joining tables directly can slow down queries. Temporary tables **store pre-processed data**, making joins more efficient.

✅ **Example: Using a Temporary Table for Faster Joins**
```sql
CREATE TEMPORARY TABLE TempHighSpendingCustomers AS
SELECT customer_id
FROM orders
GROUP BY customer_id
HAVING SUM(order_total) > 10000;

SELECT c.customer_name, h.customer_id
FROM customers c
JOIN TempHighSpendingCustomers h ON c.customer_id = h.customer_id;
```
👉 This approach optimizes performance by reducing the dataset before the join.

---

### **3. Working with Stored Procedures**
Temporary tables are often used inside stored procedures when working with **batch processing** or **iterative queries**.

✅ **Example: Storing Query Results for Reuse in a Procedure**
```sql
DELIMITER $$

CREATE PROCEDURE GetHighSalaryEmployees()
BEGIN
    CREATE TEMPORARY TABLE TempHighSalary AS
    SELECT employee_id, employee_name, salary
    FROM employees
    WHERE salary > 70000;

    SELECT * FROM TempHighSalary;
END $$

DELIMITER ;
```
👉 This temporary table is available only during the stored procedure execution.

---

### **4. Reducing Computation in Repeated Queries**
Instead of recalculating results multiple times, temporary tables **store the precomputed data** and improve query efficiency.

✅ **Example: Avoiding Repeated Calculations**
```sql
CREATE TEMPORARY TABLE TempDepartmentStats AS
SELECT department_id, COUNT(*) AS emp_count, AVG(salary) AS avg_salary
FROM employees
GROUP BY department_id;

SELECT * FROM TempDepartmentStats WHERE emp_count > 10;
SELECT * FROM TempDepartmentStats WHERE avg_salary > 50000;
```
👉 This avoids running `COUNT()` and `AVG()` multiple times.

---

### **5. Storing Complex Query Results for Debugging**
When testing complex queries, **temporary tables help debug and validate intermediate results** before executing the full query.

✅ **Example: Checking Query Results in Steps**
```sql
CREATE TEMPORARY TABLE TempDebug AS
SELECT employee_id, department_id, salary
FROM employees
WHERE salary > 50000;

SELECT * FROM TempDebug; -- Debugging Step
```
👉 This helps verify intermediate results before proceeding.

---

## **3. Types of Temporary Tables**
### **1. Local Temporary Tables (`#table_name`)**
- Created using `#` before the table name.
- Exists only during the current session.
- Auto-deleted when the session ends.

✅ **Example**
```sql
CREATE TABLE #TempTable (
    id INT,
    name VARCHAR(50)
);
```

---

### **2. Global Temporary Tables (`##table_name`)**
- Created using `##` before the table name.
- Available to all sessions but deleted when the last session using it ends.

✅ **Example**
```sql
CREATE TABLE ##GlobalTemp (
    id INT,
    name VARCHAR(50)
);
```

---

## **4. Temporary Tables vs. CTEs vs. Subqueries**
| Feature            | Temporary Tables | CTEs | Subqueries |
|--------------------|----------------|------|-----------|
| Readability       | ✅ Moderate | ✅ High | ❌ Low |
| Performance       | ✅ Good for large data | ✅ Optimized | ❌ Can be slow |
| Reusability      | ✅ Yes | ✅ Yes | ❌ No |
| Recursion Support | ❌ No | ✅ Yes | ❌ No |
| Session Persistence | ✅ Until session ends | ❌ No persistence | ❌ No persistence |
| Storage Usage     | ✅ Disk/memory | ❌ In-memory | ❌ In-memory |

👉 **Use temporary tables** when:
- You need to store large intermediate results for multiple queries.
- You are working with stored procedures or iterative queries.
- You need to debug complex SQL operations.

---

## **5. When Not to Use Temporary Tables?**
❌ When a **simple CTE or subquery** is enough (to avoid unnecessary storage usage).  
❌ When **data needs to persist across sessions** (use a permanent table instead).  
❌ When performance overhead is high due to excessive creation and deletion of temp tables.

---

## **6. Conclusion**
Temporary tables in SQL are powerful for handling **complex queries, storing intermediate results, and optimizing performance**. They are particularly useful in **stored procedures, debugging, and reducing computation** for large datasets.



### **Common Table Expressions (CTEs) in SQL**
A **Common Table Expression (CTE)** is a temporary result set in SQL that allows you to create a named subquery that can be referenced within a `SELECT`, `INSERT`, `UPDATE`, or `DELETE` statement. It improves readability and makes complex queries more manageable.

CTEs are defined using the `WITH` keyword.

### **Syntax**
```sql
WITH cte_name (column1, column2, ...) AS (
    -- SQL query
    SELECT column1, column2, ...
    FROM table_name
    WHERE conditions
)
-- Use the CTE in the main query
SELECT * FROM cte_name;
```

---

### **Where to Use CTEs?**
CTEs are useful in various scenarios:

#### **1. Improving Query Readability**
Instead of writing deeply nested subqueries, you can break the logic into multiple CTEs.

✅ **Example: Find the highest-paid employees in each department**
```sql
WITH DepartmentSalaries AS (
    SELECT department_id, employee_name, salary,
           RANK() OVER (PARTITION BY department_id ORDER BY salary DESC) AS salary_rank
    FROM employees
)
SELECT department_id, employee_name, salary
FROM DepartmentSalaries
WHERE salary_rank = 1;
```
👉 This makes it easier to read than using multiple nested subqueries.

---

#### **2. Recursive Queries (Handling Hierarchical Data)**
CTEs support recursion, which is useful for hierarchical data like **organization structures, category trees, or bill of materials**.

✅ **Example: Get all employees and their reporting hierarchy**
```sql
WITH RecursiveCTE AS (
    SELECT employee_id, manager_id, employee_name, 1 AS hierarchy_level
    FROM employees
    WHERE manager_id IS NULL  -- Start with the top-level manager

    UNION ALL

    SELECT e.employee_id, e.manager_id, e.employee_name, r.hierarchy_level + 1
    FROM employees e
    INNER JOIN RecursiveCTE r ON e.manager_id = r.employee_id
)
SELECT * FROM RecursiveCTE;
```
👉 This helps retrieve hierarchical relationships efficiently.

---

#### **3. Reusing the Same Logic Multiple Times**
CTEs can be referenced multiple times in a query without duplicating logic.

✅ **Example: Filtering out customers with high-value orders**
```sql
WITH HighValueOrders AS (
    SELECT customer_id, SUM(order_total) AS total_spent
    FROM orders
    GROUP BY customer_id
    HAVING SUM(order_total) > 5000
)
SELECT c.customer_name, h.total_spent
FROM customers c
JOIN HighValueOrders h ON c.customer_id = h.customer_id;
```
👉 The `HighValueOrders` CTE can be used multiple times in a query.

---

#### **4. Alternative to Temporary Tables**
Instead of creating temporary tables, you can use CTEs for intermediate calculations.

✅ **Example: Finding the average salary of departments**
```sql
WITH AvgSalaries AS (
    SELECT department_id, AVG(salary) AS avg_salary
    FROM employees
    GROUP BY department_id
)
SELECT e.employee_name, e.department_id, e.salary, a.avg_salary
FROM employees e
JOIN AvgSalaries a ON e.department_id = a.department_id
WHERE e.salary > a.avg_salary;
```
👉 This avoids using temporary tables and keeps the query concise.

---

### **Advantages of CTEs**
✅ **Improves Query Readability** – Breaks complex queries into smaller, readable parts.  
✅ **Recursion Support** – Useful for hierarchical data like employee-manager relationships.  
✅ **Better Performance than Nested Subqueries** – In some databases, CTEs are optimized better than deeply nested subqueries.  
✅ **Code Reusability** – Allows defining intermediate result sets that can be referenced multiple times.  
✅ **Alternative to Temporary Tables** – Avoids creating and managing temporary tables.

---

### **When to Use CTEs vs. Subqueries vs. Temporary Tables?**
| Feature            | CTEs | Subqueries | Temporary Tables |
|--------------------|------|-----------|-----------------|
| Readability       | ✅ High | ❌ Low (nested) | ✅ Moderate |
| Performance       | ✅ Optimized | ❌ Can be slow | ✅ Good for large data |
| Reusability      | ✅ Yes | ❌ No | ✅ Yes |
| Recursion Support | ✅ Yes | ❌ No | ❌ No |
| Storage Usage     | ❌ In-memory | ❌ In-memory | ✅ Stored on disk |

Use **CTEs** when you need **recursion, better readability, and reusability**.

---

### **Conclusion**
Common Table Expressions (CTEs) are a powerful feature in SQL that improve query organization, enable recursion, and provide an alternative to subqueries and temporary tables. They are especially useful when working with complex queries involving hierarchical data, calculations, and reusable logic.

Would you like to see a more advanced CTE use case, such as performance optimization tips? 🚀



# **Stored Procedures in SQL**
A **Stored Procedure** is a precompiled set of SQL statements stored in a database that can be executed as a single unit. Stored procedures improve performance, modularize code, and enhance security.

---

## **1. What is a Stored Procedure?**
A **Stored Procedure** is a reusable database object that contains a sequence of SQL statements such as `SELECT`, `INSERT`, `UPDATE`, `DELETE`, and procedural logic like loops and conditions.

### **Syntax: Creating a Stored Procedure**
```sql
CREATE PROCEDURE procedure_name (parameters)
BEGIN
    -- SQL statements
END;
```

### **Executing a Stored Procedure**
```sql
CALL procedure_name(arguments);
```

✅ **Example: Simple Stored Procedure**
```sql
DELIMITER $$

CREATE PROCEDURE GetAllEmployees()
BEGIN
    SELECT * FROM employees;
END $$

DELIMITER ;
```
To execute:
```sql
CALL GetAllEmployees();
```
👉 This retrieves all employee records.

---

## **2. Where to Use Stored Procedures?**
Stored procedures are useful in various scenarios:

### **1. Encapsulating Business Logic**
Stored procedures **centralize** and **modularize** business logic, making it easier to manage.

✅ **Example: Retrieve Employees with High Salaries**
```sql
DELIMITER $$

CREATE PROCEDURE GetHighSalaryEmployees(IN salary_threshold DECIMAL(10,2))
BEGIN
    SELECT employee_id, employee_name, salary
    FROM employees
    WHERE salary > salary_threshold;
END $$

DELIMITER ;
```
👉 The procedure takes a salary threshold and returns employees earning above it.

---

### **2. Reducing Network Traffic**
Instead of sending multiple queries from an application, a stored procedure **executes all logic on the database server** and returns the final result, reducing network overhead.

✅ **Example: Bulk Insert**
```sql
DELIMITER $$

CREATE PROCEDURE InsertEmployee(
    IN emp_name VARCHAR(100),
    IN dept_id INT,
    IN emp_salary DECIMAL(10,2)
)
BEGIN
    INSERT INTO employees (employee_name, department_id, salary)
    VALUES (emp_name, dept_id, emp_salary);
END $$

DELIMITER ;
```
👉 Instead of sending separate `INSERT` queries, calling the procedure reduces network traffic:
```sql
CALL InsertEmployee('John Doe', 2, 75000);
```

---

### **3. Improving Performance**
Stored procedures **compile once** and execute multiple times, reducing query parsing and improving efficiency.

✅ **Example: Caching Complex Queries**
```sql
DELIMITER $$

CREATE PROCEDURE GetDepartmentStats()
BEGIN
    SELECT department_id, COUNT(*) AS emp_count, AVG(salary) AS avg_salary
    FROM employees
    GROUP BY department_id;
END $$

DELIMITER ;
```
👉 Precompiled procedures execute faster than ad-hoc queries.

---

### **4. Implementing Transactions**
Stored procedures **ensure atomicity** in multi-step database operations.

✅ **Example: Handling Bank Transactions**
```sql
DELIMITER $$

CREATE PROCEDURE TransferFunds(
    IN sender_id INT,
    IN receiver_id INT,
    IN transfer_amount DECIMAL(10,2)
)
BEGIN
    DECLARE insufficient_funds BOOLEAN DEFAULT FALSE;

    START TRANSACTION;
    
    -- Check if sender has enough balance
    IF (SELECT balance FROM accounts WHERE account_id = sender_id) < transfer_amount THEN
        SET insufficient_funds = TRUE;
    ELSE
        -- Deduct from sender
        UPDATE accounts SET balance = balance - transfer_amount WHERE account_id = sender_id;

        -- Add to receiver
        UPDATE accounts SET balance = balance + transfer_amount WHERE account_id = receiver_id;
    END IF;

    -- Commit or Rollback
    IF insufficient_funds THEN
        ROLLBACK;
    ELSE
        COMMIT;
    END IF;
END $$

DELIMITER ;
```
👉 Ensures **either both updates succeed, or neither happens** (Atomicity in ACID).

---

### **5. Securing Database Operations**
Stored procedures help enforce security by **restricting direct table access** and granting permission only for procedure execution.

✅ **Example: Restricting Data Modification**
```sql
DELIMITER $$

CREATE PROCEDURE UpdateEmployeeSalary(
    IN emp_id INT,
    IN new_salary DECIMAL(10,2)
)
BEGIN
    UPDATE employees SET salary = new_salary WHERE employee_id = emp_id;
END $$

DELIMITER ;
```
👉 Instead of granting `UPDATE` privileges, users get **EXECUTE** permissions:
```sql
GRANT EXECUTE ON PROCEDURE UpdateEmployeeSalary TO 'hr_user';
```

---

### **6. Automating Routine Tasks**
Stored procedures automate periodic database tasks such as cleanup, reports, and backups.

✅ **Example: Cleaning Up Old Records**
```sql
DELIMITER $$

CREATE PROCEDURE DeleteOldOrders(IN days_old INT)
BEGIN
    DELETE FROM orders WHERE order_date < NOW() - INTERVAL days_old DAY;
END $$

DELIMITER ;
```
👉 Automates periodic cleanup without manual intervention.

---

## **3. Stored Procedure Parameters**
Stored procedures support **input, output, and input-output parameters**.

| Parameter Type  | Description |
|----------------|------------|
| **IN** | Accepts input values from the caller |
| **OUT** | Returns a value to the caller |
| **INOUT** | Accepts and modifies values |

### **Example: Using IN and OUT Parameters**
```sql
DELIMITER $$

CREATE PROCEDURE GetEmployeeSalary(
    IN emp_id INT,
    OUT emp_salary DECIMAL(10,2)
)
BEGIN
    SELECT salary INTO emp_salary FROM employees WHERE employee_id = emp_id;
END $$

DELIMITER ;
```
👉 Call the procedure and store output:
```sql
CALL GetEmployeeSalary(101, @salary);
SELECT @salary;
```

---

## **4. Stored Procedures vs. Functions**
| Feature          | Stored Procedures | Functions |
|-----------------|------------------|----------|
| Returns Value   | ❌ Can return multiple results | ✅ Must return a single value |
| Used in Queries | ❌ Cannot be used in `SELECT` | ✅ Can be used in `SELECT` |
| Supports Transactions | ✅ Yes | ❌ No |
| Input/Output Params | ✅ IN, OUT, INOUT | ✅ Only IN |
| Performance | ✅ Optimized for multiple statements | ✅ Optimized for single calculations |

---

## **5. Stored Procedures vs. Triggers**
| Feature          | Stored Procedures | Triggers |
|-----------------|------------------|----------|
| Execution       | Manually executed | Automatically executed |
| Used For        | Complex logic, automation | Enforcing constraints, logging changes |
| Input Parameters | ✅ Yes | ❌ No |
| Explicit Execution | ✅ Yes | ❌ No (fires on event) |

---

## **6. Advantages of Stored Procedures**
✅ **Performance Optimization** – Precompiled execution reduces query parsing overhead.  
✅ **Encapsulation & Code Reusability** – Encapsulates business logic into reusable modules.  
✅ **Security** – Limits direct table access and controls permissions.  
✅ **Reduced Network Traffic** – Single call reduces multiple database round trips.  
✅ **Transaction Control** – Ensures atomicity with COMMIT/ROLLBACK.  
✅ **Automates Database Tasks** – Useful for scheduled maintenance and reporting.

---

## **7. When Not to Use Stored Procedures?**
❌ When logic needs to be **dynamic** (Stored Procedures are static).  
❌ When **simple queries suffice** (No need for extra complexity).  
❌ When **cross-database portability** is required (Different databases handle stored procedures differently).  
❌ When application logic should handle computation instead of the database.

---

## **8. Conclusion**
Stored Procedures are powerful for **batch processing, security, performance optimization, automation, and business logic encapsulation**. They help reduce code duplication, improve execution speed, and enforce database integrity.

Would you like examples for a specific database (MySQL, PostgreSQL, SQL Server)? 🚀