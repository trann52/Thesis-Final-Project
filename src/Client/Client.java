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
     * The method on the client side to check the booking number.
     * A request is sent from the client after the input of a booking number.
     */

    public void clientBookingNumberCheck (String bookingNumber) throws IOException, ClassNotFoundException {

        // sending a new request form the client
        out.writeObject(new SendMessage("Booking Number Check Request"));

        SendMessage bncServer = (SendMessage) in.readObject();


//        // New request object per question
//        out.writeObject(new MessageProtocol("Question Request"));
//        // Create Global.Question object from server response
//        MessageProtocol questionFromServer = (MessageProtocol) in.readObject();
//
//        return questionFromServer.getQuestion();

    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     */
    public static void main(String[] args) throws IOException {
        Client startClient = new Client();
    }










}
