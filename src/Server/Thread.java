package Server;

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
 * @version 04/08/2020: 2.5
 * This class contains the code for server threads
 */
public class Thread implements Runnable {

    private Socket socket;
    ObjectOutputStream out;

    // constructor
    public Thread(Socket socket) {
        this.socket = socket;
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * The method for the in and out objects to the client
     */

    @Override
    public void run() {

        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());) {

            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();

            while (true) {

                SendMessage instruction = (SendMessage) in.readObject();  // this is to receive objects from the client
                ServerAction action = new ServerAction(out);

                // This is for checking the booking number
                if (instruction.getMessage().equals("Booking Check")) {
                    Passenger passenger = instruction.getPassenger();
                    action.serverBookingNumberCheck(passenger, socket);
                }

                // This is for staff login
                if (instruction.getMessage().equals("Login")) {
                    Staff staff = instruction.getStaff();
                    action.serverStaffLogin(staff, socket);
                }

                // This is for staff logout
                if (instruction.getMessage().equals("Logout")) {
                    Staff staff = instruction.getStaff();
                    action.serverStaffLogout(staff);
                }

                // This is for adding luggage information to aboutlugguage
                if (instruction.getMessage().equals("Added to aboutluggage")) {
                    Luggage luggage = instruction.getLuggage();
                    action.serverAddToAboutLuggage(luggage, socket);
                }

                // This is for inserting some luggage information to lugguagestatus
                if (instruction.getMessage().equals("Inserted to luggagestatus")) {
                    SendMessage sendMessage = instruction;
                    action.serverInsertToLuggageStatus2(sendMessage, socket);
                }

                // This is for updating the luggagestatus table with sort information
                if (instruction.getMessage().equals("Sort luggagestatus")) {
                    SendMessage sendMessage = instruction;
                    action.serverSortToLs(sendMessage, socket);
                }

                // This is for inserting the sort information into the viewstatus table
                if (instruction.getMessage().equals("Sort into viewstatus")) {
                    Luggage luggage = instruction.getLuggage();
                    action.serverSortIntoVs(luggage, socket);
                }

                // This is for updating the luggagestatus table with load information
                if (instruction.getMessage().equals("Load luggagestatus")) {
                    SendMessage sendMessage = instruction;
                    action.serverLoadToLs(sendMessage, socket);
                }

                // This is for inserting the load information into the viewstatus table
                if (instruction.getMessage().equals("Load into viewstatus")) {
                    Luggage luggage = instruction.getLuggage();
                    action.serverLoadIntoVs(luggage, socket);
                }

                // This is for updating the luggagestatus table with unload information
                if (instruction.getMessage().equals("Unload luggagestatus")) {
                    SendMessage sendMessage = instruction;
                    action.serverUnloadToLs(sendMessage, socket);
                }

                // This is for inserting the unload information into the viewstatus table
                if (instruction.getMessage().equals("Unload into viewstatus")){
                    Luggage luggage = instruction.getLuggage();
                    action.serverUnloadIntoVs(luggage, socket);
                }

                // This is for staff members looking up luggage to check if it is missing
                if(instruction.getMessage().equals("Get luggagestatus")){
                    Luggage luggage = instruction.getLuggage();
                    action.serverGetLuggageStatus(luggage);
                }

                // This is for passengers to lookup their luggage
                if (instruction.getMessage().equals("Get viewstatus")){
                    Luggage luggage = instruction.getLuggage();
                    action.serverGetViewStatus(luggage);
                }

            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


}
