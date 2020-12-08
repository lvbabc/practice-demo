package threaddemo;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    private static AtomicReference<User> atomicReference = new AtomicReference<>();

    public static void main(String[] args) {
        User user = new User("xiaomin", 22);
        atomicReference.set(user);

        User updateUser = new User("xiaowang", 24);
        atomicReference.compareAndSet(user, updateUser);
        System.out.println(atomicReference.get().toString());
    }

}
