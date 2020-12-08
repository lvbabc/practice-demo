package threaddemo;

public class User {
    private String name;
    private int age;
    private static String id="USER_ID";

    public User(){
        System.out.println("user 构造方法被调用");
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +'\'' +
                ", id=" + id +'\'' +
                '}';
    }
}
