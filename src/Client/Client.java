package Client;


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
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Client() throws IOException {
        Socket socket = new Socket("localhost", 49990);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * The method on the client side to check the booking number
     */

    public boolean clientBookingNumberCheck (String bookingNumber){

        System.out.println("Type your booking number");

        //bn is shorted for booking number
        SendMessage bnMessage = new SendMessage("Booking Number Check", new Passenger(bookingNumber));



    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     */
    public static void main(String[] args) throws IOException {
        Client startClient = new Client();
    }










}
