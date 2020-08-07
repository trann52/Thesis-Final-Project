package Client;


import Database.DbMethods;
import Stream.Luggage;
import Stream.Passenger;
import Stream.SendMessage;
import Stream.Staff;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Array;


/**
 * @author Nicky Tran
 * @version 25/07/2020: 3.1
 * This class contains the code for the client
 */
public class Client {

    private static SendMessage objectStaffLogin;
    private static SendMessage objectBookingCheck;
    private static SendMessage objectLuggageSearch;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Client() throws IOException {
        Socket socket = new Socket("localhost", 49990);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * The method on the client side to check the booking number.
     * A request is sent from the client after the input of a booking number.
     */

    public boolean clientBookingNumberCheck (String bookingNumber) throws IOException, ClassNotFoundException {

        // initialising the object to be sent to the server. In this case it is the message "Booking Check"
        objectBookingCheck = new SendMessage("Booking Check", new Passenger(bookingNumber));

        System.out.println("Sending to server: " + objectBookingCheck.getPassenger().getBookingNumber());
        out.writeObject(objectBookingCheck); // the objectBookingCheck object is sent out to the server

        System.out.println("Booking Check Object has been sent");
        String bcnServerMessage = (String) in.readObject(); // the string that is coming in from the server
        System.out.println(bcnServerMessage);

        String bcnServerMessage2 = "blank";  // a second string

        // if the first string coming in does match the designated text, the second string is received from the server.
        // the second string is then printed
        if (!bcnServerMessage.equals("Booking Number Check is successful") && !bcnServerMessage.equals("The booking number could not be found")) {
            bcnServerMessage2 = (String) in.readObject();
            System.out.println(bcnServerMessage2);
        }

        // if either of the 2 Strings sent from the server match the success text, then the data in the first String is printed/
        // the appropriate method in DbMethods is called
        if (bcnServerMessage.equals("Booking Number Check is successful") || bcnServerMessage2.equals("Booking Number Check is successful")){
            System.out.println(bcnServerMessage);
            DbMethods dbBcnObject = new DbMethods();
            dbBcnObject.checkBookingNumber(objectBookingCheck.getPassenger());
            return true;
        }
        else {
            if (!bcnServerMessage2.equals("blank")) {
                System.out.println(bcnServerMessage2);
                return false;
            }
            else {
                System.out.println(bcnServerMessage);
                return false;
            }
        }
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the client side for staff login.
     * A request is sent from the client after the staff member inputs their username and password
     */

    public boolean clientStaffLogin(String username, String password) throws IOException, ClassNotFoundException {

        // initialising the object to be sent to the server. In this case it is the message "Login"
        objectStaffLogin = new SendMessage("Login", new Staff(username, password));

        System.out.println("Sending to server: " + objectStaffLogin.getStaff().getUsername());
        out.writeObject(objectStaffLogin); // the objectBookingCheck object is sent out to the server

        System.out.println("The login object has been sent");
        String loginServerMessage = (String) in.readObject(); // the string that is coming in from the server
        System.out.println(loginServerMessage);

        String loginServerMessage2 = "blank"; // a second string

        // if the first string coming in does match the designated text, the second string is received from the server.
        // the second string is then printed
        if(!loginServerMessage.equals("Successfully logged in") && !loginServerMessage.equals("Invalid username and password combination")){
            loginServerMessage2 = (String) in.readObject(); // the string that is coming in from the server
            System.out.println(loginServerMessage2);
        }

        // if either of the 2 Strings sent from the server match the success text, then the data in the first String is printed/
        // the appropriate method in DbMethods is called
        if(loginServerMessage.equals("Successfully logged in") || loginServerMessage2.equals("Successfully logged in")){
            System.out.println(loginServerMessage);
            DbMethods dbLoginObject = new DbMethods();
            dbLoginObject.isStaffOnline(objectStaffLogin.getStaff());
            return true;
        }
        else {
            if(!loginServerMessage2.equals("blank")){
                System.out.println(loginServerMessage2);
                return false;
            }
            else {
                System.out.println(loginServerMessage);
                return false;
            }
        }



    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the client side for staff logout
     */

    public void clientStaffLogout(String username) throws IOException {

        // The object being sent is the message "Logout"
        SendMessage logoutServerMessage = new SendMessage("Logout", new Staff(username));
        out.writeObject(logoutServerMessage); // sending the object to the server
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the client side for submitting information to aboutluggage table
     */

    public String clientAddToAboutLuggage(String boardPassNumber, String barcodeNumber, String type, double weight, String colour, Array dimension, String fragile, String excess) throws IOException, ClassNotFoundException {

        // The object being sent is the message "Added to aboutluggage"
        SendMessage luggInfoServerMessage = new SendMessage("Added to aboutluggage", new Luggage(boardPassNumber, barcodeNumber, type, weight, colour, dimension, fragile, excess));

        System.out.println("Sending to server: " + luggInfoServerMessage);

        out.writeObject(luggInfoServerMessage); //sending object to server
        System.out.println("Luggage info sent");
        String submitAlFromServer = (String) in.readObject(); // receiving object from server
        System.out.println("Added to aboutluggage");
        return submitAlFromServer;

    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the client side for submitting information to luggagestatus table
     */

    public String clientInsertToLugguageStatus(String bookingNumber, String origin, String destination, Array layovers) throws IOException, ClassNotFoundException {

        // ls is shortened for luggagestatus
        // The object being sent is the message "Inserted to luggagestatus"
        SendMessage lsInsertMessage = new SendMessage("Inserted to luggagestatus", new Passenger(bookingNumber, origin, destination, layovers));

        System.out.println("Sending to server: " + lsInsertMessage);

        out.writeObject(lsInsertMessage); // sending object to server
        System.out.println("Luggage info sent");
        String insertLsFromServer = (String) in.readObject(); // receiving object from server
        System.out.println("Inserted to luggagestatus");
        return insertLsFromServer;

    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the client side for submitting data related to sorting to the luggagestatus table
     */

    public boolean clientSortToLs(String username,  String barcodeNumber, Array location) throws IOException, ClassNotFoundException {

        // The object being sent is the message "Sort lugguagestatus"
        SendMessage sortToLs = new SendMessage("Sort lugguagestatus", new Staff(username), new Luggage(barcodeNumber, location));
        out.writeObject(sortToLs);

        String sortLsMessage = (String) in.readObject(); // receiving object from the server

        if (sortLsMessage.equals("Sort data successfully added to luggagestatus")){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the client side for submitting data related to sorting to the viewstatus table
     */

    public String clientSortIntoVs(String barcodeNumber, Array location) throws IOException, ClassNotFoundException {

        // The object being sent is the message "Sort into viewstatus"
        SendMessage sortVsServerMessage = new SendMessage("Sort into viewstatus", new Luggage(barcodeNumber,location));

        System.out.println("Sending to server: " + sortVsServerMessage);

        out.writeObject(sortVsServerMessage); // sending object to server
        System.out.println("Sort data sent");
        String sortVsFromServer = (String) in.readObject();
        System.out.println("Sort data added to viewstatus");
        return sortVsFromServer;
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the client side for submitting data related to loading to the luggagestatus table
     */

    public boolean clientLoadToLs(String username,  String barcodeNumber, Array location) throws IOException, ClassNotFoundException {

        // The object being sent is the message "Load lugguagestatus"
        SendMessage loadToLs = new SendMessage("Load lugguagestatus", new Staff(username), new Luggage(barcodeNumber, location));
        out.writeObject(loadToLs);

        String loadLsMessage = (String) in.readObject(); // receiving object from the server

        if (loadLsMessage.equals("Load data successfully added to luggagestatus")){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the client side for submitting data related to loading to the viewstatus table
     */

    public String clientLoadIntoVs(String barcodeNumber, Array location) throws IOException, ClassNotFoundException {

        // The object being sent is the message "Load into viewstatus"
        SendMessage loadVsServerMessage = new SendMessage("Load into viewstatus", new Luggage(barcodeNumber,location));

        System.out.println("Sending to server: " + loadVsServerMessage);

        out.writeObject(loadVsServerMessage); // sending object to server
        System.out.println("Load data sent");
        String loadVsFromServer = (String) in.readObject();
        System.out.println("Load data added to viewstatus");
        return loadVsFromServer;
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the client side for submitting data related to unloading to the luggagestatus table
     */

    public boolean clientUnloadToLs(String username,  String barcodeNumber, Array location) throws IOException, ClassNotFoundException {

        // The object being sent is the message "Unload lugguagestatus"
        SendMessage unloadToLs = new SendMessage("Unload lugguagestatus", new Staff(username), new Luggage(barcodeNumber, location));
        out.writeObject(unloadToLs);

        String unloadLsMessage = (String) in.readObject(); // receiving object from the server

        if (unloadLsMessage.equals("Unload data successfully added to luggagestatus")){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the client side for submitting data related to unloading to the viewstatus table
     */

    public String clientUnloadIntoVs(String barcodeNumber, Array location) throws IOException, ClassNotFoundException {

        // The object being sent is the message "Unload into viewstatus"
        SendMessage unloadVsServerMessage = new SendMessage("Unload into viewstatus", new Luggage(barcodeNumber,location));

        System.out.println("Sending to server: " + unloadVsServerMessage);

        out.writeObject(unloadVsServerMessage); // sending object to server
        System.out.println("Load data sent");
        String unloadVsFromServer = (String) in.readObject();
        System.out.println("Load data added to viewstatus");
        return unloadVsFromServer;
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the client side for getting information from luggagestatus table when the barcode is searched.
     * This is for staff members to lookup the luggage
     */

    //TODO NEED TO CHECK IF WORKS
    public void clientGetLuggageStatus(String barcodeNumber) throws IOException {

        SendMessage getLsMessage = new SendMessage("Get lugguagestatus", new Luggage(barcodeNumber));
        out.writeObject(getLsMessage);

//        // The object being sent is the message "Get lugguagestatus"
//        out.writeObject(new SendMessage("Get lugguagestatus"));
//        SendMessage lsFromServer = (SendMessage) in.readObject();
//        return lsFromServer.getLuggage().getBarcodeNumber();
    }


    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the client side for getting information from viewstatus table when the barcode is searched.
     * This is for passengers to lookup the luggage
     */

    //TODO NEED TO CHECK IF WORKS
    public void clientGetViewStatus(String barcodeNumber) throws IOException {

        SendMessage getVsMessage = new SendMessage("Get viewstatus", new Luggage(barcodeNumber));
        out.writeObject(getVsMessage);

    }







    /**
     * -----------------------------------------------------------------------------------------------------------------
     */
    public static void main(String[] args) throws IOException {
        Client startClient = new Client();
    }



}
