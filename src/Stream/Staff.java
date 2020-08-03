package Stream;

import java.io.Serializable;
import java.sql.Array;
import java.sql.Timestamp;

/**
 * @author Nicky Tran
 * @version 01/08/2020: 1.2
 * This class implements the Serializable interface because any serialised object
 * can be read to a file. When the file is deserialised, the data can be recreatead.
 */
public class Staff implements Serializable {

    private String username;
    private String password;
    private Timestamp timestamp;
    private String destination;
    private Array layovers;

    public Staff(String username, String password, Timestamp timestamp, String destination, Array layovers) {
        this.username = username;
        this.password = password;
        this.timestamp = timestamp;
        this.destination = destination;
        this.layovers = layovers;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getDestination() {
        return destination;
    }

    public Array getLayovers() {
        return layovers;
    }
}
