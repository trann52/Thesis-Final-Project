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
    private String boardPassNumber;

    public Passenger(String bookingNumber, String boardPassNumber) {
        this.bookingNumber = bookingNumber;
        this.boardPassNumber = boardPassNumber;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public String getBoardPassNumber() {
        return boardPassNumber;
    }
}
