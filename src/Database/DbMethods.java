package Database;

import Stream.Luggage;
import Stream.Passenger;
import Stream.SendMessage;
import Stream.Staff;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Properties;


/**
 * @author Nicky Tran
 * @version 26/07/2020: 1.38
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
     */

    public void isStaffOnline(Staff staff) {
        if (checkStaffUserWithPassword(staff)) {
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

    public void logStaffOut(Staff staff) {

        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE luggageproject.public.stafflogin " +
                    "SET state = ? WHERE username = ?");

            ps.setString(1, "offline");
            ps.setString(2, staff.getUsername());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * -----------------------------------------------------------------------------------------------------------------
     * <p>
     * This method should hopefully update the the aboutluggage table with
     * all the necessary information that has been inputted in the GUI.
     * This method should occur at the same time as 'submitToLuggageStatus()'.
     *
     * @return
     */

    public String submitToAboutLuggage(Luggage luggage) {

        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO luggageproject.public.aboutluggage(" +
                    "boardpass_number, barcode, type, weight, colour, dimension, fragile, excess) " +
                    "VALUES(?,?,?,?,?,?,?,?)");

            ps.setString(1, luggage.getBoardPassNumber());
            ps.setString(2, luggage.getBarcodeNumber());
            ps.setString(3, luggage.getType());
            ps.setString(4, luggage.getWeight());
            ps.setString(5, luggage.getColour());
            ps.setString(6, luggage.getDimension());
            ps.setString(7, luggage.getFragile());
            ps.setString(8, luggage.getExcess());

            ps.executeUpdate();

            System.out.println("Data added to table");

        } catch (SQLException e) {

        }


        return null;
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This method will inset the boarding pass number and the barcode number to the luggagestatus table.
     * This method should occur at the same time that 'submitToAboutLuggage()'. However this version will not be used
     * as the origin, destination and layovers information should be retrieved from a table containing all the booking information.
     * This would be another system, so I have not included this.
     *
     */

    public String submitToLuggageStatus(SendMessage sendMessage) {

        try {
            PreparedStatement ps = connection.prepareStatement(" INSERT INTO luggageproject.public.luggagestatus(" +
                    "boardpass_number, barcode, origin, destination, layovers) VALUES(?,?,?,?,?)");

            ps.setString(1, sendMessage.getLuggage().getBoardPassNumber());
            ps.setString(2, sendMessage.getLuggage().getBarcodeNumber());
            ps.setString(3, sendMessage.getPassenger().getOrigin());
            ps.setString(4, sendMessage.getPassenger().getDestination());
            ps.setArray(5, sendMessage.getPassenger().getLayovers());

            ps.executeUpdate();

            System.out.println("Data added to table");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This method will inset the boarding pass number and the barcode number to the luggagestatus table.
     *
     */

    public String submitToLuggageStatus2(SendMessage sendMessage) {

        try {
            PreparedStatement ps = connection.prepareStatement(" INSERT INTO luggageproject.public.luggagestatus(" +
                    "boardpass_number, barcode) VALUES(?,?)");

            ps.setString(1, sendMessage.getLuggage().getBoardPassNumber());
            ps.setString(2, sendMessage.getLuggage().getBarcodeNumber());

            ps.executeUpdate();

            System.out.println("Data added to table");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * ------------------------------------------------------------------------------------------------------------------
     * The sorted rows in the luggagestatus table is updated.
     * This method should occur at the same time as sortInsertToViewStatus().
     */
// TODO NEED TO CHECK IF WORKS WHEN CONNECTED TO SERVER AND CLIENT
    public boolean sortToLuggageStatus(SendMessage sendMessage) {

//        for (int i = 0; i <= 15; i++) {

        try {
            // this statement will query the luggagestatus table to get the barcode
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM luggageproject.public.luggagestatus " +
                    "WHERE barcode = ? ");
            ps.setString(1, sendMessage.getLuggage().getBarcodeNumber());
            ResultSet rs = ps.executeQuery();

            // if the barcode matches, then the next statement will update the sorted rows with the appropriate information
            while (rs.absolute(2)) {
                if (rs.getString(2).equals(sendMessage.getPassenger().getBookingNumber())) {

                    PreparedStatement sort = connection.prepareStatement(
                            "BEGIN " +
                                    "DECLARE @i int = 0;" +
                                    "WHILE @i <=15 " +
                                    "UPDATE luggageproject.public.luggagestatus " +
                                    "SET sortedby[@i] = ?, sortedtime[@i] =?, sortedlocation[@i] = ? " +
                                    "WHERE barcode = ? " +
                                    "@i = @i + 1 " +
                                    "END");

                    sort.setString(1, sendMessage.getStaff().getUsername());
                    sort.setString(2, DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).format(ZonedDateTime.now()));
                    sort.setArray(3, sendMessage.getLuggage().getLocation());
                    sort.setString(4, sendMessage.getLuggage().getBarcodeNumber());

                    sort.executeUpdate();
                    return true;
                }
            }

        } catch (SQLException e) {

        }
        return false;
    }
//    }

    /**
     * ------------------------------------------------------------------------------------------------------------------
     * Data is inserted into the viewstatus table when luggage is sorted
     * This method should occur at the same time as sortToLuggageStatus().
     */

    public String sortInsertToViewStatus(Luggage luggage) {

        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO luggageproject.public.viewstatus(" +
                    "barcode, location, status, datetime) VALUES(?,?,?,?)");

            ps.setString(1, luggage.getBarcodeNumber());
            ps.setArray(2, luggage.getLocation());
            ps.setString(3, "Sorted");
            ps.setString(4, DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).format(ZonedDateTime.now()));

            ps.executeUpdate();

            System.out.println("Data added to table");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * -----------------------------------------------------------------------------------------------------------------
     * The loaded rows in the luggagestatus table is updated
     * This method should occur at the same time as loadIntoViewStatus().
     */

    // TODO NEED TO CHECK IF WORKS WHEN CONNECTED TO SERVER AND CLIENT
    public boolean loadToLuggageStatus(SendMessage sendMessage) {

        try {
            // this statement will query the luggagestatus table to get the barcode
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM luggageproject.public.luggagestatus " +
                    "WHERE barcode = ? ");
            ps.setString(1, sendMessage.getLuggage().getBarcodeNumber());
            ResultSet rs = ps.executeQuery();

            // if the barcode matches, then the next statement will update the sorted rows with the appropriate information
            while (rs.absolute(2)) {
                if (rs.getString(2).equals(sendMessage.getPassenger().getBookingNumber())) {

                    PreparedStatement load = connection.prepareStatement(
                            "BEGIN " +
                                    "DECLARE @i int = 0;" +
                                    "WHILE @i <=15 " +
                                    "UPDATE luggageproject.public.luggagestatus " +
                                    "SET loadedby[@i] = ?, loadedtime[@i] =?, loadedlocation[@i] = ? " +
                                    "WHERE barcode = ? " +
                                    "@i = @i + 1 " +
                                    "END");

                    load.setString(1, sendMessage.getStaff().getUsername());
                    load.setString(2, DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).format(ZonedDateTime.now()));
                    load.setArray(3, sendMessage.getLuggage().getLocation());
                    load.setString(4, sendMessage.getLuggage().getBarcodeNumber());

                    load.executeUpdate();
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
     * Data is inserted into the viewstatus table when luggage is loaded
     * This method should occur at the same time as loadToLuggageStatus()
     */

    public String loadIntoViewStatus(Luggage luggage) {

        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO luggageproject.public.viewstatus(" +
                    "barcode, location, status, datetime) VALUES(?,?,?,?)");

            ps.setString(1, luggage.getBarcodeNumber());
            ps.setArray(2, luggage.getLocation());
            ps.setString(3, "Loaded");
            ps.setString(4, DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).format(ZonedDateTime.now()));

            ps.executeUpdate();

            System.out.println("Data added to table");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * -----------------------------------------------------------------------------------------------------------------
     * The unloaded rows in the luggagestatus table is updated
     * This method should occur at the same time as unloadIntoViewStatus().
     */

    // TODO NEED TO CHECK IF WORKS WHEN CONNECTED TO SERVER AND CLIENT
    public boolean unloadToLuggageStatus(SendMessage sendMessage) {

        try {

            // this statement will query the luggagestatus table to get the barcode
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM luggageproject.public.luggagestatus " +
                    "WHERE barcode = ? ");
            ps.setString(1, sendMessage.getLuggage().getBarcodeNumber());
            ResultSet rs = ps.executeQuery();

            // if the barcode matches, then the next statement will update the sorted rows with the appropriate information
            while (rs.absolute(2)){
                if (rs.getString(2).equals(sendMessage.getPassenger().getBookingNumber())){

                    PreparedStatement unload = connection.prepareStatement(
                            "BEGIN " +
                                    "DECLARE @i int = 0;" +
                                    "WHILE @i <=15 " +
                                    "UPDATE luggageproject.public.luggagestatus " +
                                    "SET unloadedby[@i] = ?, unloadedtime[@i] =?, unloadedlocation[@i] = ? " +
                                    "WHERE barcode = ? " +
                                    "@i = @i + 1 " +
                                    "END");

                    unload.setString(1, sendMessage.getStaff().getUsername());
                    unload.setString(2, DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).format(ZonedDateTime.now()));
                    unload.setArray(3, sendMessage.getLuggage().getLocation());
                    unload.setString(4, sendMessage.getLuggage().getBarcodeNumber());

                    unload.executeUpdate();
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
     * Data is inserted into the viewstatus table when luggage is unloaded
     * This method should occur at the same time as unloadToLuggageStatus()
     */

    public String unloadIntoViewStatus(Luggage luggage) {

        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO luggageproject.public.viewstatus(" +
                    "barcode, location, status, datetime) VALUES(?,?,?,?)");

            ps.setString(1, luggage.getBarcodeNumber());
            ps.setArray(2, luggage.getLocation());
            ps.setString(3, "Unloaded");
            ps.setString(4, DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).format(ZonedDateTime.now()));

            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * Method to view all data in luggagestatus stable when boardingpass number and barcode are given,
     * this is intended for staff members when they want to look up missing luggage
     */
    public void staffLookupLuggage(Luggage luggage) {

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM luggageproject.public.luggagestatus " +
                    "WHERE barcode = ?");

            ps.setString(1, luggage.getBarcodeNumber());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * Method to view all data in viewstatus stable when boardingpass number and barcode are given,
     * this is intended for passengers when they want to look up their luggage.
     */

    public void passengerLuggageLookup(Luggage luggage) {

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT location, status, datetime " +
                    "FROM luggageproject.public.viewstatus WHERE barcode = ? ");

            ps.setString(1, luggage.getBarcodeNumber());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     */


}