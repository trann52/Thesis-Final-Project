package Server;

import Database.DbMethods;
import Stream.Passenger;
import Stream.Staff;

import java.io.IOException;
import java.io.ObjectOutputStream;

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

    public void serverBookingNumberCheck(Passenger passenger) throws IOException {

        System.out.println("Booking Number server check");
        boolean failBnc = true;
        System.out.println("Received from client: " + passenger);  // passenger is now an object type that can be printed

        DbMethods dbObject = new DbMethods();
        if (dbObject.checkBookingNumber(passenger)){
            System.out.println("SEARCH FOUND ATTEMPT");
            failBnc = false;
        }

        if(failBnc){
            System.out.println("SEARCH NOT FOUND ATTEMPT");
            out.writeObject("The booking number could not be found"); // object sent to client
        }
        else {
            System.out.println("SEARCH FOUND");
            out.writeObject("Booking Number Check is successful"); // object sent to client
        }

    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the server side for staff login.
     * The staff member is them logged in, if their username and password match what is in the stofflogin database
     */

    public void serverStaffLogin(Staff staff) throws IOException {

        System.out.println("Login server check");
        boolean failLogin = true;
        System.out.println("Received from client: " + staff); // staff is now an object type that can be printed

        DbMethods dbObject = new DbMethods();
        if (dbObject.checkStaffUserWithPassword(staff)){
            System.out.println("LOGIN ATTEMPT");
            failLogin = false;
        }

        if (failLogin){
            System.out.println("LOGIN BAD ATTEMPT");
            out.writeObject("Invalid username and password combination"); // object sent to client
        }
        else {
            System.out.println("LOGIN ATTEMPT SUCCESS");
            out.writeObject("Successfully logged in");
        }
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the server side for staff logout
     */

    public void serverStaffLogout(Staff staff){
        DbMethods dbObject = new DbMethods();
        dbObject.logStaffOut(staff);
    }















}
