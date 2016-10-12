package Account;

/**
 * Created by Dmitry on 11.10.2016.
 */
public class AccountEntity {
    private int id;
    private int balance;

    public AccountEntity(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}
