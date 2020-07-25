package Client;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Client() throws IOException {
        Socket socket = new Socket("localhost", 49990);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }










}
