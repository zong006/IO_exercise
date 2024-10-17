package fc.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException {
        
        // write a program for a client to connect to server to get a random cookie.

        // Client need a host IP address to know which server to connect to, and a port number of the server
        // assume that user enters args correctly in the format "serverhost:portNumber fileName"

        String[] hostAndPort = args[0].split(":");
        String host = hostAndPort[0];
        int port = Integer.parseInt(hostAndPort[1]);

        Socket socket = new Socket(host, port);

        System.out.printf("Connected to %s at port %d. \n", host, port);

        requestCookie(socket);
        }
        
    public static void requestCookie(Socket socket) throws IOException{

        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        Console console = System.console();
        System.out.println("Enter either: \n get-cookie \n close");
        
        boolean close = false;

        while (!close){
            String command = console.readLine(">>> ");
            if (command.equals("get-cookie")){
                dos.writeUTF(command);
                String recievedCookie = dis.readUTF();
                System.out.println(recievedCookie);
            }
            else if (command.equals("close")){
                dos.writeUTF(command);

                dos.close();
                os.close();
                dis.close();
                is.close();

                socket.close();
                close = true;
            }
            else {
                System.out.println("Enter either 'get-cookie' or 'close'.");
            }   
        }
    }
}    

