package threaddemo;


public class Account {
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    // 转账
    public void transfer(Account target, int amt) {
        // 锁定转出账户
        synchronized (this) {
            // 锁定转入账户
            synchronized (target) {
                if (this.balance > amt) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }
}