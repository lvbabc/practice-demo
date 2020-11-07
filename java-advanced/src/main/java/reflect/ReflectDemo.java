package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectDemo {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException {
        getClazz();
        getInstance();
        constructor();
        method();
        field();
    }

    public static void getClazz() {
        //1、通过Class.forName获取Employee类的Class对象
        String classPath = "reflect.Employee";
        Class<?> employeeClass1 = null;
        try {
            employeeClass1 = Class.forName(classPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //2、通过实例对象获取Employee的Class对象
        Employee employee = new Employee();
        Class<?> employeeClass2 = employee.getClass();

        //3、字面常量的方式获取Class对象
        Class<Employee> employeeClass3 = Employee.class;

        System.out.println("Class.forName == instance.getClass(): " + (employeeClass1 == employeeClass2));
        System.out.println("Class.forName == Employee.class: " + (employeeClass1 == employeeClass3));
    }

    public static void getInstance() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 1、Class 对象调用newInstance()方法
        Class<Employee> clazz = Employee.class;
        Employee employee = (Employee) clazz.newInstance();

        // 2、Constructor 构造器调用newInstance()方法
        Constructor<?> constructor = clazz.getConstructor(String.class, String.class, String.class, int.class,
                String.class, int.class);

        Employee employee1 = clazz.cast(constructor.newInstance("", "", "", 1, "", 1));
    }

    public static void constructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Employee.class;

        System.out.println("--------------------------------------------");
        //获取带String, int参数的public构造函数
        Constructor cs1 = clazz.getConstructor(String.class, int.class);
        Employee employee1 = (Employee) cs1.newInstance("小张", 20);
        System.out.println(cs1.toGenericString() + ":" + employee1.toString());

        System.out.println("--------------------------------------------");
        //获取带String, String, int参数的private构造函数
        Constructor cs2 = clazz.getDeclaredConstructor(String.class, String.class, int.class);
        cs2.setAccessible(true);
        Employee employee2 = (Employee) cs2.newInstance("小张", "50", 20);
        System.out.println(cs2.toGenericString() + ":" + employee2.toString());
        System.out.println("--------------------------------------------");

        //获取所有构造包含private
        Constructor<?> cons[] = clazz.getConstructors();
        // 查看每个构造方法需要的参数
        for (int i = 0; i < cons.length; i++) {
            //获取构造函数参数类型
            Class<?> clazzs[] = cons[i].getParameterTypes();
            System.out.println("构造函数[" + i + "]:" + cons[i].toString());
            System.out.print("参数类型[" + i + "]:(");
            for (int j = 0; j < clazzs.length; j++) {
                if (j == clazzs.length - 1)
                    System.out.print(clazzs[j].getName());
                else
                    System.out.print(clazzs[j].getName() + ",");
            }
            System.out.println(")");
        }
    }

    public static void method() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName("reflect.Employee");

        System.out.println("--------------------------------------------");
        // 获取所有public的方法, 包括继承的方法:
        Method[] publicMethods = clazz.getMethods();
        for (Method m : publicMethods) {
            System.out.println("m::" + m.toGenericString());
        }

        System.out.println("--------------------------------------------");
        // 获取当前类的所有方法包含private,该方法无法获取继承自父类的method
        Method[] allMethods = clazz.getDeclaredMethods();

        for (Method m : allMethods) {
            System.out.println("m1::" + m);
        }
        System.out.println("--------------------------------------------");
        Constructor cs1 = clazz.getConstructor(String.class, int.class);
        Employee employee1 = (Employee) cs1.newInstance("小张", 20);

        Method method = clazz.getDeclaredMethod("work");
        method.setAccessible(true);
        method.invoke(employee1);
    }

    public static void field() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> clazz = Class.forName("reflect.Employee");

        System.out.println("--------------------------------------------");
        //获取所有修饰符为public的字段,包含父类字段,注意修饰符为public才会获取
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            System.out.println("f:" + f.toGenericString());
        }

        System.out.println("----------------------getDeclaredFields----------------------");
        //获取当前类所字段(包含private字段),注意不包含父类的字段
        Field[] fields1 = clazz.getDeclaredFields();
        for (Field f:fields1) {
            System.out.println("f2:"+f.toGenericString());
        }

        System.out.println("--------------------------------------------");
        Employee employee = (Employee) clazz.newInstance();
        Field nameField = clazz.getField("name");
        nameField.set(employee, "小王");
        System.out.println(employee);
    }
}
