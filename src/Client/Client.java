package Client;


import Database.DbMethods;
import Server.Server;
import Stream.Luggage;
import Stream.Passenger;
import Stream.SendMessage;

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
    private static SendMessage luggageSearch;
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

        objectBookingCheck = new SendMessage("Booking Check", new Passenger(bookingNumber));

        System.out.println("Sending to server: " + objectBookingCheck.getPassenger().getBookingNumber());
        out.writeObject(objectBookingCheck); // the objectBookingCheck object is sent to the server

        System.out.println("Booking Check Object has been sent");
        String bcnServerMessage = (String) in.readObject();
        System.out.println(bcnServerMessage);

        if (bcnServerMessage.equals("Booking Number Check is successful")){
            System.out.println(bcnServerMessage);
            DbMethods dbBcnObject = new DbMethods();
            dbBcnObject.checkBookingNumber(objectBookingCheck.getPassenger());
            return true;
        }
        else {
            return false;
        }
    }



    /**
     * -----------------------------------------------------------------------------------------------------------------
     */
    public static void main(String[] args) throws IOException {
        Client startClient = new Client();
    }










}
