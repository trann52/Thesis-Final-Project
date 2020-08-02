package Database;

import Stream.Passenger;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;


/**
 * @author Nicky Tran
 * @version 26/07/2020: 1.11
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

            this.connection = DriverManager.getConnection(url, username, password);
            System.out.println("A connection to the database has been made");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     *
     * This method will check the entered booking number against the ones in the database.
     * This method will be available for passengers and staff
     *@return the booking number
     */

    public boolean checkBookingNumber(Passenger passenger) throws SQLException {

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT passenger_booking_information, luggage_amount FROM luggageproject.public.selfpass " +
                            "WHERE passenger_booking_information = ?");
            ps.setString(1, passenger.getBookingNumber());
            ResultSet rs = ps.executeQuery();

            while (rs.first()){
                if (rs.getString(1).equals(passenger.getBookingNumber())){
                    return true;
                }
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


}
