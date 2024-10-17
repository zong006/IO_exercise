package fc.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {
        // the server is only responsible for opening a port for multiple clients to connect to it. 

        // assume user inputs args as "portNumber"
        System.out.println("cookies...");
        int port = Integer.parseInt(args[0]);
        int numOfThreads = 3;

        ServerSocket server = new ServerSocket(port);
        ExecutorService threadPool = Executors.newFixedThreadPool(numOfThreads);

        Console console = System.console();
        String serverInput = "";
        boolean serverClosed = false;

        while (!serverClosed){

            Socket socket = server.accept();
            System.out.println("Connection established.");

            CookieClientHandler cookieThread = new CookieClientHandler(socket);
            threadPool.submit(cookieThread);
        }
    }
}
