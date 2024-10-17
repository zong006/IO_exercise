package fc.server;

import java.net.Socket;
import java.io.*;

public class CookieClientHandler implements Runnable{
    // client handler is responsible for taking client inputs and sending them to the coockie class

    private Socket socket;
    private Cookie cookie;

    public CookieClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.cookie = new Cookie();
    }

    @Override
    public void run() {
        try {
            sendCookie();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCookie () throws IOException{

        // read command from client
        InputStream is = this.socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        OutputStream os = this.socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        boolean close = false;
        
        while (!close){
            String command = dis.readUTF();
            
            if (command.equals("get-cookie")){
                String chosenCookie = cookie.generateCookie();
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
