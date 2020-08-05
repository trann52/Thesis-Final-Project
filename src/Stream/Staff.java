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
    private String location;

    // -----------------------------------------------------------------------------------------------------------------
    // constructors
    public Staff(String username, String password, String location) {
        this.username = username;
        this.password = password;
        this.location = location;
    }

//    public Staff(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }

    public Staff(String username, String location) {
        this.username = username;
        this.location = location;
    }

    //------------------------------------------------------------------------------------------------------------------
    // getter methods
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public String getLocation() {
        return location;
    }
}
