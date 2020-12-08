package threaddemo;

import org.openjdk.jol.info.ClassLayout;

public class SyncTest {
    static User user = new User();
//    static User[] users = new User[10];

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(user).toPrintable());
//        System.out.println(ClassLayout.parseInstance(users).toPrintable());
    }
}
