package Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @author Nicky Tran
 * @version 19/07/2020: 1.2
 * This class contains information about connecting to the database
 */
public class Database {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "nxt930";
        String password = "654321";

        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("A connection has been made");

    }
}
