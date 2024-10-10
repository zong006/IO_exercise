package fc;

import java.io.*;
import java.util.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
    
        // assume user inputs args as "portNumber cookieFile"
        System.out.println("cookies...");
        int port = Integer.parseInt(args[0]);
        String cookieFile = args[1];

        ServerSocket server = new ServerSocket(port);
        Socket socket = server.accept();

        System.out.printf("Coonection established at port %d. \n", port);

        sendCookie(socket, cookieFile);
    }

    public static void sendCookie (Socket socket, String cookieFile) throws IOException{

        // read command from client
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        boolean close = false;
        Cookie.setFile(cookieFile);
        int numCookies = Cookie.open();
        
        while (!close){
            String command = dis.readUTF();
            
            if (command.equals("get-cookie")){
                String chosenCookie = Cookie.generateCookie(numCookies);
                System.out.println(chosenCookie);
                dos.writeUTF("cookie-text " + chosenCookie);

            }
            else if (command.equals("close")){
                
                dos.close();
                os.close();
                dis.close();
                is.close();

                close = true;

                socket.close();
            }
        }
    }
}
