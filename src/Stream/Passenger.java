package Stream;

import java.io.Serializable;
import java.sql.Array;

/**
 * @author Nicky Tran
 * @version 01/08/2020: 1.2
 * This class implements the Serializable interface because any serialised object
 * can be read to a file. When the file is deserialised, the data can be recreatead.
 */
public class Passenger implements Serializable {

    private String bookingNumber;
    private String boardPassNumber;
    private String origin;
    private String destination;
    private Array layovers;

    // -----------------------------------------------------------------------------------------------------------------
    // constructors
    public Passenger(String bookingNumber, String boardPassNumber, String origin, String destination, Array layovers) {
        this.bookingNumber = bookingNumber;
        this.boardPassNumber = boardPassNumber;
        this.origin = origin;
        this.destination = destination;
        this.layovers = layovers;
    }

//    public Passenger(String bookingNumber) {
//        this.bookingNumber = bookingNumber;
//    }
//
//
//    public Passenger(String boardPassNumber) {
//        this.boardPassNumber = boardPassNumber;
//    }


    public Passenger(String bookingNumber, String boardPassNumber) {
        this.bookingNumber = bookingNumber;
        this.boardPassNumber = boardPassNumber;
    }

    public Passenger(String origin, String destination, Array layovers) {
        this.origin = origin;
        this.destination = destination;
        this.layovers = layovers;
    }


    // -----------------------------------------------------------------------------------------------------------------
    // getter methods
    public String getBookingNumber() {
        return bookingNumber;
    }

    public String getBoardPassNumber() {
        return boardPassNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Array getLayovers() {
        return layovers;
    }
}
