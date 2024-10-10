import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        
        // server to recieve file from a client

        // assume user inputs args as "portNumber"
        int port = Integer.parseInt(args[0]);

        ServerSocket server = new ServerSocket(port);
        Socket socket = server.accept();

        System.out.printf("Coonection established at port %d. \n", port);

        recieveFile(socket);

    }

    public static void recieveFile (Socket socket) throws IOException {
        
        // get read input from socket
        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        String[] readFilePath = dis.readUTF().split("/");
        String readFileName = readFilePath[readFilePath.length-1];
        long fileSize = dis.readLong();
        String filePath = "copied_files" + File.separator + readFileName;

        // write to a file
        FileOutputStream fos = new FileOutputStream(filePath);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        DataOutputStream dos = new DataOutputStream(bos);
        
        System.out.printf("Recieving file %s of size %d bytes.... \n", readFileName, fileSize);

        // reading and writing operation
        byte[] b = new byte[2*1024];
        int byteRead = 0;
        int byteRecieved = 0;

        while (byteRecieved < fileSize){
            byteRead = dis.read(b);
            byteRecieved += byteRead;

            // bos.write(b, 0, byteRead);
            dos.write(b, 0, byteRead);
        }

        System.out.printf("File %s recieved. \n", readFileName);

        bos.close();
        fos.close();

        dis.close();
        bis.close();
        is.close();
        

    }
}