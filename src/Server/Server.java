package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Nicky Tran
 * @version 25/07/2020: 1.2
 * This class will contain the code for the server
 */
public class Server {

    public Server() throws IOException {

        ExecutorService service = Executors.newFixedThreadPool(100);

        ServerSocket serverSocket = new ServerSocket(49990);

        while (true) {
            Socket socket = serverSocket.accept(); // accepts the incoming socket request
            System.out.println(socket.getInetAddress());
        }

    }

    public static void main(String[] args) throws IOException {
        new Server();
    }


}
