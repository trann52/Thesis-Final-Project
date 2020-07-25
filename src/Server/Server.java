package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public Server() throws IOException{

        ExecutorService service = Executors.newFixedThreadPool(100);

        ServerSocket serverSocket = new ServerSocket(49990);

        while (true){
            Socket socket = serverSocket.accept(); // accepts the incoming socket request
            System.out.println(socket.getInetAddress());
        }

    }










}
