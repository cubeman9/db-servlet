package Account;

import java.sql.*;

/**
 * Created by Dmitry on 11.10.2016.
 */
public class AccountService {

    static synchronized int getAmount(int id) {
        ResultSet resultSet = Utils.selectQuery("SELECT * FROM balance");
        try {
            while (resultSet.next()) {
                if (resultSet.getInt("id") == id) {
                    return resultSet.getInt("balance");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    static synchronized void addAmount(int id, int balance) {
        Utils.updateQuery("INSERT INTO balance (id, balance) " +
                "VALUES (" + id + ", " + balance + ") " +
                "ON CONFLICT (id) DO UPDATE SET" +
                " balance = balance.balance + " + balance);
    }
}
