package Stream;

import java.io.Serializable;

/**
 * @author Nicky Tran
 * @version 01/08/2020: 1.1
 * This class implements the Serializable interface because any serialised object
 * can be read to a file. When the file is deserialised, the data can be recreatead.
 */
public class Staff implements Serializable {

    private String username;
    private String password;
    private String name;

    /**
     * This is a constructor that initialises the staff username, password and name.
     * @param username the assigned username of the staff
     * @param password the assigned password of the staff
     * @param name the name of the staff with their own username and password
     */
    public Staff(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
