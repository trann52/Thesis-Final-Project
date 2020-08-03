package Stream;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Nicky Tran
 * @version 01/08/2020: 1.21
 * This class implements the Serializable interface because any serialised object
 * can be read to a file. When the file is deserialised, the data can be recreatead.
 */
public class Staff implements Serializable {

    private String username;
    private String password;
    private Timestamp timestamp;

    // constructor
    public Staff(String username, String password, Timestamp timestamp) {
        this.username = username;
        this.password = password;
        this.timestamp = timestamp;
    }

    // getter methods
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

}
