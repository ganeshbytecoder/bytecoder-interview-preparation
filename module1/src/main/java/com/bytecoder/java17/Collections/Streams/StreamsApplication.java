package com.bytecoder.java17.Collections.Streams;

import lombok.*;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

enum Department {
    TECH, PRODUCT,HR, SALES, OPS
}

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
class Employee {
    private   int id;
    private String name;
    private int age;
    private String address;
    private int salary;
    private Department department;


    public boolean equals(Employee obj) {
        return this.getName().equals(obj.getName());
    }
}

public class StreamsApplication {

    private static Employee[] arrayOfEmps = {
            new Employee(1, "Jeff Bezos1", 50, "USA", 100000, Department.PRODUCT),
            new Employee(2, "Jeff 2", 60, "INDIA", 100000, Department.PRODUCT),
            new Employee(3, "Rohit", 70, "USA", 100000, Department.OPS),
            new Employee(4, "Himanshu", 50, "INDIA", 100000, Department.TECH),
            new Employee(5, "Ravi", 80, "CANADA", 100000, Department.HR),
            new Employee(5, "Ravi", 90, "CANADA", 100000, Department.HR),

    };


    public static void main(String[] args) {


//        all functions of Collectors class ?



//        creation of streams -> from collections, file io,
       Stream<Integer> integerStream =  Stream.of(1,2,3,4);
        Stream<Employee> employeeStream = Arrays.stream(arrayOfEmps);
        List<Employee> employeeList = Arrays.asList(arrayOfEmps);
        for (Employee e:
             employeeList) {
            System.out.println(e);
        }


       List<Employee> employees =  Arrays.stream(arrayOfEmps)
               .sorted(Comparator.comparing(a-> a.getAge()))
               .collect(Collectors.toList());
       Set<Employee> employeeSet =  Arrays.stream(arrayOfEmps)
               .sorted(Comparator.comparing(a-> a.getAge()))
               .collect(Collectors.toSet());

        // it returns min age value employee
        BinaryOperator<Employee> binaryOperator =   BinaryOperator.minBy(Comparator.comparing(x-> x.getAge()));
//        Note key must be unique in this case if duplicate key is there then write mergeFunction which is BinaryOperator
        Map<Integer, Employee> employeeMap  = Arrays.stream(arrayOfEmps)
                .sorted(Comparator.comparing(a-> a.getAge()))
                .collect(Collectors.toMap(x-> x.getId(), Function.identity(), binaryOperator));
        System.out.println(employeeMap);
//      {1=Employee(id=1, name=Jeff Bezos1, age=50, address=USA, salary=100000, department=PRODUCT), 2=Employee(id=2, name=Jeff 2, age=60, address=INDIA, salary=100000, department=PRODUCT), 3=Employee(id=3, name=Rohit, age=70, address=USA, salary=100000, department=OPS), 4=Employee(id=4, name=Himanshu, age=50, address=INDIA, salary=100000, department=TECH), 5=Employee(id=5, name=Ravi, age=80, address=CANADA, salary=100000, department=HR)}

        DoubleSummaryStatistics doubleSummaryStatistics =Arrays.stream(arrayOfEmps).sorted(Comparator.comparing(a-> a.getAge())).map(a-> a.getAge()+10)
                .collect(Collectors.summarizingDouble(x-> x));
        System.out.println(doubleSummaryStatistics);

        List<Employee>  employees1 = Arrays.stream(arrayOfEmps).sorted(Comparator.comparing(a-> a.getAge()))
                .collect(Collectors.mapping(x->new Employee(x.getId(),x.getName(), x.getAge(), x.getAddress(), x.getSalary()+500, x.getDepartment()), Collectors.toList()));

        Map<String, List<Employee>> listMap  = Arrays.stream(arrayOfEmps).sorted(Comparator.comparing(a-> a.getAge()))
                .collect(Collectors.groupingBy(x-> x.getAddress()));
        System.out.println(listMap);
        listMap  = Arrays.stream(arrayOfEmps).sorted(Comparator.comparing(a-> a.getAge()))
                .collect(Collectors.groupingBy(x-> x.getAddress(), Collectors.toList()));
        System.out.println(listMap);

        Map<String, Long> stringLongMap  = Arrays.stream(arrayOfEmps).sorted(Comparator.comparing(a-> a.getAge()))
                .collect(Collectors.groupingBy(x-> x.getAddress(), Collectors.counting()));
        System.out.println(stringLongMap);
//      {USA=2, CANADA=2, INDIA=2}

        Map<String, List<String>> stringListMap  = Arrays.stream(arrayOfEmps).sorted(Comparator.comparing(a-> a.getAge()))
                .collect(Collectors.groupingBy(x-> x.getAddress(), Collectors.mapping(x-> x.getName(), Collectors.toList())));
        System.out.println(stringListMap);
//      {USA=[Jeff Bezos1, Rohit], CANADA=[Ravi, Ravi], INDIA=[Himanshu, Jeff 2]}
        Map<String, Set<String>> stringListMap2  = Arrays.stream(arrayOfEmps).sorted(Comparator.comparing(a-> a.getAge()))
                .collect(Collectors.groupingBy(x-> x.getAddress(), Collectors.mapping(x-> x.getName(), Collectors.toSet())));
        System.out.println(stringListMap2);
//      {USA=[Rohit, Jeff Bezos1], CANADA=[Ravi], INDIA=[Jeff 2, Himanshu]}

        Map<Boolean, List<Employee>> map = Arrays.stream(arrayOfEmps).sorted(Comparator.comparing(a-> a.getAge()))
                .collect(Collectors.partitioningBy(x-> x.getAddress() != "USA"));
        System.out.println(map);
//      {false=[Employee(id=1, name=Jeff Bezos1, age=50, address=USA, salary=100000, department=PRODUCT), Employee(id=3, name=Rohit, age=70, address=USA, salary=100000, department=OPS)], true=[Employee(id=4, name=Himanshu, age=50, address=INDIA, salary=100000, department=TECH), Employee(id=2, name=Jeff 2, age=60, address=INDIA, salary=100000, department=PRODUCT), Employee(id=5, name=Ravi, age=80, address=CANADA, salary=100000, department=HR), Employee(id=5, name=Ravi, age=90, address=CANADA, salary=100000, department=HR)]}
    }
}
