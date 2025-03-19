### Understanding Joins, Columns, and Queries in **Spring Boot with Hibernate & PostgreSQL**

When working with **Spring Boot, Hibernate (JPA), and PostgreSQL**, you typically interact with a **relational database** using **JPA Entities and Repositories**. Understanding **joins**, **columns**, and **queries** is crucial for effective data retrieval and relationship management.

---

## 1. **Columns in Hibernate (JPA)**
In **Hibernate**, columns are mapped using **annotations** in Entity classes.

### Example:
```java
@Entity
@Table(name = "employees")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private Double salary;

    // Getters & Setters
}
```
### Important Column Annotations:
- `@Id` → Defines the primary key.
- `@GeneratedValue(strategy = GenerationType.IDENTITY)` → Auto-increments the ID.
- `@Column(name="column_name")` → Maps the Java field to a database column.
- `@Column(nullable = false)` → Ensures the column **cannot be NULL**.
- `@Column(unique = true)` → Ensures **unique values**.
- `@Column(length = 100)` → Limits **string length**.

---

## 2. **Types of Joins in Spring Boot with Hibernate**
Hibernate supports various joins for entity relationships.

### **Join Types**
1. **One-to-One Join** (`@OneToOne`)
2. **One-to-Many Join** (`@OneToMany`)
3. **Many-to-One Join** (`@ManyToOne`)
4. **Many-to-Many Join** (`@ManyToMany`)

---

### **1. One-to-One Join**
Used when **one entity is linked to another entity uniquely**.

#### Example:
```java
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private UserProfile profile;
}
```
```java
@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bio")
    private String bio;
}
```
- **`@OneToOne`** → One user has **one profile**.
- **`@JoinColumn(name="profile_id")`** → Stores the foreign key in the **users** table.

---

### **2. One-to-Many Join**
Used when **one entity has multiple related entities**.

#### Example:
```java
@Entity
@Table(name = "departments")
public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;
}
```
```java
@Entity
@Table(name = "employees")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
```
- **One `Department` has many `Employees`**.
- **Foreign key** (`department_id`) is in `employees` table.

---

### **3. Many-to-One Join**
A **reverse** relationship of **One-to-Many**.

- Many employees **belong to** one department.
- This is handled using **`@ManyToOne`** (shown in the `Employee` entity above).

---

### **4. Many-to-Many Join**
Used when **both entities can have multiple associations**.

#### Example:
```java
@Entity
@Table(name = "students")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
}
```
```java
@Entity
@Table(name = "courses")
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String courseName;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;
}
```
- A **join table (`student_course`)** is created automatically.
- **Foreign keys** (`student_id`, `course_id`) are managed by Hibernate.

---

## 3. **Queries in Spring Boot with Hibernate & PostgreSQL**
You can execute queries using **Spring Data JPA Repositories**.

### **Basic Repository**
```java
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
```
This provides **CRUD operations automatically**.

---

### **Derived Queries (By Naming Convention)**
Spring Data JPA lets you define queries using **method names**.

#### Example:
```java
List<Employee> findByDepartmentName(String name);
List<Employee> findBySalaryGreaterThan(Double salary);
List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
```
- `findByDepartmentName("IT")` → Finds employees in IT department.
- `findBySalaryGreaterThan(50000.0)` → Finds employees with salary > 50K.

---

### **JPQL Queries (Hibernate-Specific)**
JPQL (Java Persistence Query Language) allows **custom queries**.

#### Example:
```java
@Query("SELECT e FROM Employee e WHERE e.salary > :salary")
List<Employee> getEmployeesWithSalaryGreaterThan(@Param("salary") Double salary);
```
- Uses **HQL** (Hibernate Query Language).
- Uses **Entity names, not table names**.

---

### **Native SQL Queries**
Use **raw SQL queries** in repositories.

#### Example:
```java
@Query(value = "SELECT * FROM employees WHERE salary > ?1", nativeQuery = true)
List<Employee> findEmployeesBySalaryNative(Double salary);
```
- Uses **PostgreSQL queries directly**.
- More efficient in some cases.

---

### **JOIN Queries in Spring Boot**
#### **Fetching Employees with Departments using JPQL**
```java
@Query("SELECT e FROM Employee e JOIN e.department d WHERE d.name = :departmentName")
List<Employee> findEmployeesByDepartment(@Param("departmentName") String departmentName);
```
- Fetches **employees based on department name**.

#### **Native Query for Join**
```java
@Query(value = "SELECT e.* FROM employees e JOIN departments d ON e.department_id = d.id WHERE d.name = ?1", nativeQuery = true)
List<Employee> getEmployeesByDepartmentName(String departmentName);
```
- Uses **direct SQL JOIN** for better performance.

---

## **Conclusion**
1. **Columns in Hibernate** are mapped using `@Column` annotations.
2. **Joins** (`@OneToOne`, `@OneToMany`, `@ManyToOne`, `@ManyToMany`) define entity relationships.
3. **Queries**:
    - **Derived queries** (method names).
    - **JPQL queries** (HQL syntax).
    - **Native queries** (PostgreSQL SQL).

Would you like more details on **performance tuning** for these queries? 🚀