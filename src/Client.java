import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException {
        
        // write a program for a client to connect to server to send a file over

        // Client need a host IP address to know which server to connect to, and a port number of the server
        // assume that user enters args correctly in the format "serverhost:portNumber fileName"

        String[] hostAndPort = args[0].split(":");
        String host = hostAndPort[0];
        int port = Integer.parseInt(hostAndPort[1]);

        String fileName = args[1]; 
        

        Socket socket = new Socket(host, port);

        System.out.printf("Connected to %s at port %d. \n", host, port);

        sendFile(fileName, socket);

        
        socket.close();
    }

    public static void sendFile (String fileName, Socket socket) throws IOException {

        File file = new File(fileName);
        long fileSize = file.length();

        // reader to read the file contents
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        DataInputStream dis = new DataInputStream(bis);

        // write to write the file contents to the socket
        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        System.out.printf("Sending file %s of size %d bytes....  \n", fileName, fileSize);
        dos.writeUTF(fileName);
        dos.writeLong(fileSize);
        dos.flush();
        
        // reading and writing operation
        byte[] b = new byte[2*1024];
        int byteRead = 0;

        // while ((byteRead = bis.read(b))!=-1){
        while ((byteRead = dis.read(b))!=-1){
            dos.write(b, 0, byteRead);
            dos.flush();
        }

        System.out.printf("File %s sent. \n", fileName);

        dos.close();
        bos.close();
        os.close();

        bis.close();
        fis.close();
    }
}