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

// constructor
    public SendMessage(String message, Passenger passenger, Staff staff, Luggage luggage) {
        this.message = message;
        this.passenger = passenger;
        this.staff = staff;
        this.luggage = luggage;
    }

    // getter methods
    public String getMessage() {
        return message;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Staff getStaff() {
        return staff;
    }

    public Luggage getLuggage() {
        return luggage;
    }
}
