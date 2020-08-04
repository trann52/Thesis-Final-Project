package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Nicky Tran
 * @version 25/07/2020: 2.1
 * This class will contain the code for the server
 */
public class Server {

    public Server() throws IOException {

        ExecutorService service = Executors.newFixedThreadPool(500); //currently there are 500 threads, may change later

        try (ServerSocket serverSocket = new ServerSocket(49990);)
            {
                System.out.println("The server has started ");
            while (true) {
                Socket socket = serverSocket.accept(); // accepts the incoming socket request
                System.out.println("The client is connected from: " + socket.getInetAddress());
                service.execute(new Thread(socket));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }


}
