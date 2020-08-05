package Server;

import Database.DbMethods;
import Stream.Luggage;
import Stream.Passenger;
import Stream.SendMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author Nicky Tran
 * @version 04/08/2020: 1.1
 * This class contains methods for messages received at the server from the client
 */
public class ServerAction {

    ObjectOutputStream out;

    public ServerAction(ObjectOutputStream out) {
        this.out = out;
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * The server will receive an object from the the client and will lookup the
     * barcode number in the database.
     */

    public void serverBookingNumberCheck(Passenger passenger, Socket socket) throws IOException {

        System.out.println("Booking Number server check");
        boolean failBnc = true;
        System.out.println("Received from client: " + passenger);  // passenger is now an object type that can be printed

        DbMethods dbObject = new DbMethods();
        if (dbObject.checkBookingNumber(passenger)){
            System.out.println("Search found");
            failBnc = false;
        }

        if(failBnc){
            System.out.println("Search not found");
            out.writeObject("The booking number could not be found");
        }
        else {
            System.out.println("Search found");
            out.writeObject("Booking Number Check is successful");
        }

    }











}
