package entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Employee {

    private String empId;
    private String name;
    private String address;
    private int age;
    private String nic;
    private String contact;
    private String salary;
    private String userName;
    private String password;

    public Employee(String empId, String name, String address, int age, String nic, String contact, String salary, String userName, String password) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.age = age;
        this.nic = nic;
        this.contact = contact;
        this.salary = salary;
        this.userName = userName;
        this.password = password;
    }

    public Employee(String empId, String name, String address, int age, String nic, String contact, String salary) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.age = age;
        this.nic = nic;
        this.contact = contact;
        this.salary = salary;
    }

    public int getDob() {
        return this.age;
    }
}
