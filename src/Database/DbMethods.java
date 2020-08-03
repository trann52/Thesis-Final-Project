package Database;

import GUI.controller.LuggInfoController;
import Stream.Luggage;
import Stream.Passenger;
import Stream.SendMessage;
import Stream.Staff;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;


/**
 * @author Nicky Tran
 * @version 26/07/2020: 1.25
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
     * <p>
     * This method will check the entered booking number against the ones in the database.
     * This method will be available for passengers and staff
     *
     * @return the booking number
     */

    public boolean checkBookingNumber(Passenger passenger) {

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT passenger_booking_information, luggage_amount FROM luggageproject.public.selfpass " +
                            "WHERE passenger_booking_information = ?");
            ps.setString(1, passenger.getBookingNumber());
            ResultSet rs = ps.executeQuery();

            while (rs.first()) {
                if (rs.getString(1).equals(passenger.getBookingNumber())) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * ------------------------------------------------------------------------------------------------------------------
     *
     * @return checks if the username that is used by the staff matches the password that is
     * in the database
     */

    public boolean checkStaffUserWithPassword(Staff staff) {

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT password FROM luggageproject.public.stafflogin " +
                            "WHERE username = ?");
            ps.setString(1, staff.getUsername());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals(staff.getPassword())) {
                    return true;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This method will set the staff member to a logged in status in the database
     *
     */

    public void isStaffOnline(Staff staff){
        if (checkStaffUserWithPassword(staff)){
            try {
                PreparedStatement ps = connection.prepareStatement("UPDATE luggageproject.public.stafflogin " +
                        "SET status = ? WHERE username = ?");

                ps.setString(1, "online");
                ps.setString(2, staff.getUsername());
                ps.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This method will set the staff member to a logged out status
     */

    public void logStaffOut(Staff staff){

        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE luggageproject.public.stafflogin " +
                    "SET status = ? WHERE username = ?");

            ps.setString(1, "offline");
            ps.setString(2, staff.getUsername());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @return this method should hopefully update the the aboutluggage table with
     * all the necessary information that has been inputted in the GUI.
     * This method should occur at the same time as 'submitToLuggageStatus()'.
     */

    public void submitToAboutLuggage(SendMessage sendMessage) {

        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO luggageproject.public.aboutluggage(" +
                    "boardpass_number, barcode, type, weight, colour, dimension, fragile, excess) " +
                    "VALUES(?,?,?,?,?,?,?,?)");

            ps.setString(1, sendMessage.getPassenger().getBoardPassNumber());
            ps.setString(2, sendMessage.getLuggage().getBarcodeNumber());
            ps.setString(3, sendMessage.getLuggage().getType());
            ps.setDouble(4, sendMessage.getLuggage().getWeight());
            ps.setString(5, sendMessage.getLuggage().getColour());
            ps.setArray(6, sendMessage.getLuggage().getDimension());
            ps.setString(7, sendMessage.getLuggage().getFragile());
            ps.setString(8, sendMessage.getLuggage().getExcess());

            ps.executeUpdate();

            System.out.println("Data added to table");

        } catch (SQLException e) {

        }


    }
    /**
     * -----------------------------------------------------------------------------------------------------------------
     *  This method will inset the boarding pass number and the barcode number to the luggagestatus table.
     *  This method should occur at the same time that 'submitToAboutLuggage()'.
     */

    public void submitToLuggageStatus(SendMessage sendMessage){

        try{
            PreparedStatement ps = connection.prepareStatement(" INSERT INTO luggageproject.public.luggagestatus(" +
                    "boardpass_number, barcode, origin, destination, layovers) VALUES(?,?,?,?,?)");

            ps.setString(1, sendMessage.getPassenger().getBoardPassNumber());
            ps.setString(2, sendMessage.getLuggage().getBarcodeNumber());
            ps.setString(3, sendMessage.getPassenger().getOrigin());
            ps.setString(4, sendMessage.getPassenger().getDestination());
            ps.setArray(5, sendMessage.getPassenger().getLayovers());

            ps.executeUpdate();

            System.out.println("Data added to table");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




    /**
     *------------------------------------------------------------------------------------------------------------------
     *
     */

    public void sortToLuggageStatus(SendMessage sendMessage) {

        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE luggageproject.public.luggagestatus");


//      update luggagestatus SET sortedby[0] = 'janed4', sortedtime[0] = 'testime' where boardpass_number = 'testpass1' and barcode = '123456';

        } catch (SQLException e) {

        }
    }

    /**
     *------------------------------------------------------------------------------------------------------------------
     *
     */

















    /**
     *------------------------------------------------------------------------------------------------------------------
     *
     */



}