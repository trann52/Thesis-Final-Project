package Stream;

import java.io.Serializable;

/**
 * @author Nicky Tran
 * @version 01/08/2020: 1.1
 * This class implements the Serializable interface because any serialised object
 * can be read to a file. When the file is deserialised, the data can be recreatead.
 */
public class Passenger implements Serializable {

    private String bookingNumber;

    /**
     * This is a constructor which will be used to initialise the booking number.
     * @param bookingNumber the booking number the passenger has for their flight
     */
    public Passenger(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "bookingNumber='" + bookingNumber + '\'' +
                '}';
    }





}
