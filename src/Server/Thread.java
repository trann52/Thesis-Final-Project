package Server;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author Nicky Tran
 * @version 04/08/2020: 1.1
 * This class contains the code for server threads
 */
public class Thread implements Runnable {

    private Socket socket;
    ObjectOutputStream out;

    // constructor
    public Thread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

    }
}
