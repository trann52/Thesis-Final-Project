package Database;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


/**
 * @author Nicky Tran
 * @version 26/07/2020: 1.1
 * This class will contain all the methods used in my system to upload data to the
 * database or to retrieve data from the database.
 */
public class DbMethods {

    public static Connection connection;

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * A constructor created that makes a connection to the database. This constructor will also
     * login into the database using the credentials provided in database.properties. If the login
     * is successful, then a message will appear showing a successful connection. Otherwise an
     * exception is thrown.
     */

    public DbMethods() {

        String url;
        String username;
        String password;

        try (FileInputStream inputStream = new FileInputStream(new File("database.properties"))) {
            Properties properties = new Properties();
            properties.load(inputStream);

            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            this.connection = DriverManager.getConnection(url,username,password);
            System.out.println("A connection to the database has been made");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     *
     */


















}
