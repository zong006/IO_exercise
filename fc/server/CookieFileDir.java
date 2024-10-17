package fc.server;
import java.io.*;

public class CookieFileDir {

    private String dir = System.getProperty("user.dir");
    private String fileName = "cookie.txt";
    private String filePath = dir +  File.separator + "fc" + File.separator + "server" + File.separator +  fileName;
    
    public String getFilePath() {
        return filePath;
    }
}
