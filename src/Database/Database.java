package Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static void main(String[] args) throws IOException, SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "nxt930";
        String password = "654321";

        Connection connection = DriverManager.getConnection(url, username, password);
    }
}
