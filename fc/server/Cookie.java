package fc.server;

import java.io.*;
import java.util.*;

public class Cookie {

    // class to read cookie file, generate a random cookie, and returns that cookie
    private String cookieFile;
    private int numCookies;

    // public static void setFile(String file) {
    //     Cookie.file = file;
    // }
    public Cookie() throws IOException {
        CookieFileDir cookieFileDir = new CookieFileDir();
        this.cookieFile = cookieFileDir.getFilePath();
        this.numCookies = open();
    }

    static ArrayList<String> cookieList = new ArrayList<>();
    
    public int open() throws IOException{

        try (FileReader fr = new FileReader(this.cookieFile)) {
            try (BufferedReader br = new BufferedReader(fr)) {
                String cookieRead= "";

                while ((cookieRead = br.readLine())!=null){
                    if (cookieRead.length()!=0){
                        cookieList.add(cookieRead);
                        numCookies += 1;
                    }
                    else{
                        continue;
                    }
                }
            }
        }
        return numCookies;
    }

    public String generateCookie() throws IOException{
        Random randNum = new Random();
        int cookieChosen = randNum.nextInt(this.numCookies);

        return cookieList.get(cookieChosen);
    }


    
}
