package Server;

import Database.DbMethods;
import Stream.Luggage;
import Stream.Passenger;
import Stream.SendMessage;
import Stream.Staff;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author Nicky Tran
 * @version 04/08/2020: 3.1
 * This class contains methods for messages received at the server from the client
 */
public class ServerAction {

    ObjectOutputStream out;

    public ServerAction(ObjectOutputStream ot) {
        out = ot;
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * The server will receive an object from the the client and will lookup the
     * barcode number in the database.
     */

    public void serverBookingNumberCheck(Passenger passenger, Socket socket) throws IOException {

//        System.out.println("Booking Number server check");
//        boolean failBnc = true;
//        System.out.println("Received from client: " + passenger);  // passenger is now an object type that can be printed
//
//        DbMethods dbObject = new DbMethods();
//        if (dbObject.checkBookingNumber(passenger)) {
//            System.out.println("SEARCH FOUND ATTEMPT");
//            failBnc = false;
//        }
//
//        if (failBnc) {
//            System.out.println("SEARCH NOT FOUND ATTEMPT");
//            out.writeObject("The booking number could not be found"); // object sent to client
//        } else {
//            System.out.println("SEARCH FOUND");
//            out.writeObject("Booking Number Check is successful"); // object sent to client
//        }

        System.out.println("Booking Number server check");
        boolean bnCheck = false;
        System.out.println("Received from client: " + passenger);  // passenger is now an object type that can be printed

        DbMethods dbObject = new DbMethods();
        if (dbObject.checkBookingNumber(passenger)) {
            bnCheck = true;
        }

        if (bnCheck) {
            out.writeObject("Booking number found");
        } else {
            out.writeObject("Try again");
        }


    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the server side for staff login.
     * The staff member is them logged in, if their username and password match what is in the stofflogin database
     */

    public void serverStaffLogin(Staff staff, Socket socket) throws IOException {

        System.out.println("Login server check");
        boolean failLogin = true;
        System.out.println("Received from client: " + staff); // staff is now an object type that can be printed

        DbMethods dbObject = new DbMethods();
        if (dbObject.checkStaffUserWithPassword(staff)) {
            System.out.println("LOGIN ATTEMPT");
            failLogin = false;
        }

        if (failLogin) {
            System.out.println("LOGIN BAD ATTEMPT");
            out.writeObject("Invalid username and password combination"); // object sent to client
        } else {
            System.out.println("LOGIN ATTEMPT SUCCESS");
            out.writeObject("Successfully logged in");
        }
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the server side for staff logout
     */

    public void serverStaffLogout(Staff staff) {
        DbMethods dbObject = new DbMethods();
        dbObject.logStaffOut(staff);
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the server side for submitting information to aboutluggage table
     */

    public void serverAddToAboutLuggage(Luggage luggage, Socket socket) throws IOException {

//        System.out.println("ADD TO ABOUTLUGGAGE");
//        System.out.println("Received from client: " + luggage);

        DbMethods dbObject = new DbMethods();
        String submitAlToClient = dbObject.submitToAboutLuggage(luggage);
        out.writeObject(submitAlToClient);
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the server side for submitting information to luggagestatus table. However this version will not be used
     * as the origin, destination and layovers information should be retrieved from a table containing all the booking information.
     * This would be another system, so I have not included this.
     */

    public void serverInsertToLuggageStatus(SendMessage sendMessage, Socket socket) throws IOException {

        DbMethods dbObject = new DbMethods();
        String insertLsToClient = dbObject.submitToLuggageStatus(sendMessage);
        out.writeObject(insertLsToClient);

    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the server side for submitting information to luggagestatus table.
     */

    public void serverInsertToLuggageStatus2(SendMessage sendMessage, Socket socket) throws IOException {

        DbMethods dbObject = new DbMethods();
        String insertLsToClient = dbObject.submitToLuggageStatus2(sendMessage);
        out.writeObject(insertLsToClient);

    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This method is to update the lugguagestatus table with the appropriate sort information.
     * The update occurs if the boarding pass number and barcode match the ones in the database
     */

    public void serverSortToLs(SendMessage sendMessage, Socket socket) throws IOException {

        System.out.println("sorting to luggagestatus server side");
        DbMethods dbObject = new DbMethods();

        if (dbObject.sortToLuggageStatus(sendMessage)) {
            out.writeObject("Sort data successfully added to luggagestatus");
        } else {
            out.writeObject("Barcode not found");
        }

    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the server side for submitting sorting information to viewstatus table
     */

    // Vs is short for viewstatus
    public void serverSortIntoVs(Luggage luggage, Socket socket) throws IOException {

        DbMethods dbObject = new DbMethods();
        String sortVsToClient = dbObject.sortInsertToViewStatus(luggage);
        out.writeObject(sortVsToClient);

    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This method is to update the lugguagestatus table with the appropriate load information.
     * The update occurs if the boarding pass number and barcode match the ones in the database
     */

    public void serverLoadToLs(SendMessage sendMessage, Socket socket) throws IOException {

        System.out.println("loading to luggagestatus server side");
        DbMethods dbObject = new DbMethods();

        if (dbObject.loadToLuggageStatus(sendMessage)) {
            out.writeObject("Load data successfully added to luggagestatus");
        } else {
            out.writeObject("Barcode not found");
        }

    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the server side for submitting loading information to viewstatus table
     */

    public void serverLoadIntoVs(Luggage luggage, Socket socket) throws IOException {

        DbMethods dbObject = new DbMethods();
        String sortVsToClient = dbObject.loadIntoViewStatus(luggage);
        out.writeObject(sortVsToClient);

    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This method is to update the lugguagestatus table with the appropriate unload information.
     * The update occurs if the boarding pass number and barcode match the ones in the database
     */

    public void serverUnloadToLs(SendMessage sendMessage, Socket socket) throws IOException {

        System.out.println("unloading to luggagestatus server side");
        DbMethods dbObject = new DbMethods();

        if (dbObject.unloadToLuggageStatus(sendMessage)) {
            out.writeObject("Unload data successfully added to luggagestatus");
        } else {
            out.writeObject("Barcode not found");
        }

    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the server side for submitting loading information to viewstatus table
     */

    public void serverUnloadIntoVs(Luggage luggage, Socket socket) throws IOException {

        DbMethods dbObject = new DbMethods();
        String sortVsToClient = dbObject.unloadIntoViewStatus(luggage);
        out.writeObject(sortVsToClient);

    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the server side for getting information from luggagstatus table where the barcode is searched.
     * This is for staff members to lookup the luggage
     */

    //TODO NEED TO CHECK IF WORKS
    public void serverGetLuggageStatus(Luggage luggage) throws IOException {

//        DbMethods dbObject = new DbMethods();
//        dbObject.staffLookupLuggage(luggage);


//        System.out.println("Luggage Search server check");
//        boolean failStaffSearch = true;
//        System.out.println("Received from client: " + luggage);  // passenger is now an object type that can be printed
//
//        DbMethods dbObject = new DbMethods();
//        if (dbObject.staffLookupLuggage(luggage)) {
//            System.out.println("SEARCH FOUND ATTEMPT");
//            failStaffSearch = false;
//        }
//
//        if (failStaffSearch) {
//            System.out.println("SEARCH NOT FOUND ATTEMPT");
//            out.writeObject("The barcode could not be found"); // object sent to client
//        } else {
//            System.out.println("SEARCH FOUND");
//            out.writeObject("Barcode Check is successful"); // object sent to client
//        }

        System.out.println("Luggage Search server check");
        boolean staffSearch = false;
        System.out.println("Received from client: " + luggage);  // passenger is now an object type that can be printed
//
        DbMethods dbObject = new DbMethods();
        if (dbObject.staffLookupLuggage(luggage)) {
            staffSearch = true;
        }

        if (staffSearch) {
            out.writeObject("Barcode found"); // object sent to client
        } else {
            out.writeObject("Try again");
        }


    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * This is a method on the server side for getting information from viewstatus table where the barcode is searched.
     * This is for passengers to lookup the luggage
     */

    //TODO NEED TO CHECK IF WORKS
    public void serverGetViewStatus(Luggage luggage) throws IOException {

//        DbMethods dbObject = new DbMethods();
//        dbObject.passengerLuggageLookup(luggage);

//        System.out.println("Luggage Search server check");
//        boolean failPSearch = true;
//        System.out.println("Received from client: " + luggage);  // passenger is now an object type that can be printed
//
//        DbMethods dbObject = new DbMethods();
//        if (dbObject.passengerLuggageLookup(luggage)) {
//            System.out.println("SEARCH FOUND ATTEMPT");
//            failPSearch = false;
//        }
//
//        if (failPSearch) {
//            System.out.println("SEARCH NOT FOUND ATTEMPT");
//            out.writeObject("The barcode could not be found"); // object sent to client
//        } else {
//            System.out.println("SEARCH FOUND");
//            out.writeObject("Barcode Check is successful"); // object sent to client
//        }
//    }

        System.out.println("Luggage Search server check");
        boolean pSearch = false;
        System.out.println("Received from client: " + luggage);  // passenger is now an object type that can be printed

        DbMethods dbObject = new DbMethods();
        if (dbObject.passengerLuggageLookup(luggage)) {
            pSearch = true;
        }
        if (pSearch) {
            out.writeObject("Barcode found"); // object sent to client
        } else {
            out.writeObject("Try again");
        }
    }



}
