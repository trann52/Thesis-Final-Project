package Client;


import Database.DbMethods;
import Server.Server;
import Stream.Luggage;
import Stream.Passenger;
import Stream.SendMessage;
import Stream.Staff;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 * @author Nicky Tran
 * @version 25/07/2020: 1.1
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
     */
    public static void main(String[] args) throws IOException {
        Client startClient = new Client();
    }










}
