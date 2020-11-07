package reflect;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Employee extends Person {
    public static Integer totalNum = 0; // 员工数
    public int empNo;   // 员工编号 公有
    protected String position;  // 职位 保护
    private int salary; // 工资   私有

    public Employee() {
        super();
    }

    public Employee(String name, int empNo) {
        this(name, "20", "drive", empNo, "社畜", 1000);
    }

    private Employee(String name, String age, int empNo) {
        this(name, age, "drive", empNo, "社畜", 1000);
    }

    public Employee(String name, String age, String hobby, int empNo, String position, int salary) {
        super(name, age, hobby);
        this.empNo = empNo;
        this.position = position;
        this.salary = salary;
        Employee.totalNum++;
    }

    private void work() {
        System.out.printf("My name is %s, 工作中勿扰.%n", name);
    }

    public void sayHello() {
        System.out.printf("Hello, 我是 %s, 今年 %s 岁, 爱好是%s, 我目前的工作是%s, 月入%s元\n", name, getAge(), getHobby(), position, salary);
    }
}
