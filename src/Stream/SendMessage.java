package Stream;

import java.io.Serializable;

/**
 * @author Nicky Tran
 * @version 01/08/2020: 1.1
 * This class implements the Serializable interface because any serialised object
 * can be read to a file. When the file is deserialised, the data can be recreatead.
 */
public class SendMessage implements Serializable {

    private String message;
    private Passenger passenger;
    private Staff staff;
    private Luggage luggage;

    /**
     * A constructor to initialise message for sending requests
     * @param message a message sent to Client.Client
     */
    public SendMessage(String message) {
        this.message = message;
    }

    /**
     * A constructor to initialise message and passenger for search the bookingNumber requests
     * @param message a message sent to Client.Client
     * @param passenger the passenger information
     */
    public SendMessage(String message, Passenger passenger) {
        this.message = message;
        this.passenger = passenger;
    }

    /**
     * A constructor to initialise message, passenger and luggage for viewing luggage status requests
     * @param message a message sent to Client.Client
     * @param passenger the passenger information
     * @param luggage the luggage information
     */
    public SendMessage(String message, Passenger passenger, Luggage luggage) {
        this.message = message;
        this.passenger = passenger;
        this.luggage = luggage;
    }

    /**
     *A constructor to initialise message and luggage for updating luggage status requests
     * @param message a message sent to Client.Client
     * @param luggage the luggage information
     */
    public SendMessage(String message, Luggage luggage) {
        this.message = message;
        this.luggage = luggage;
    }

    /**
     * A constructor to initialise message and staff for staff login requests
     * @param message a message sent to Client.Client
     * @param staff staff information
     */
    public SendMessage(String message, Staff staff) {
        this.message = message;
        this.staff = staff;
    }
}
