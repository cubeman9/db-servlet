package Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry on 11.10.2016.
 */
public class Utils {
    static final String url = "jdbc:postgresql://95.213.204.15:5432/kek";
    static final String username = "testuser";
    static final String password = "testuser";

    static synchronized ResultSet selectQuery(String query) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            connection.close();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    static synchronized void updateQuery(String query) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static synchronized List<AccountEntity> dbToEntityList() {
        List<AccountEntity> accountEntityList = new ArrayList<>();
        ResultSet resultSet = Utils.selectQuery("SELECT * FROM balance");
        try {
            while (resultSet.next()) {
                AccountEntity accountEntity = new AccountEntity(resultSet.getInt("id"), resultSet.getInt("balance"));
                accountEntityList.add(accountEntity);
            }
            return accountEntityList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}