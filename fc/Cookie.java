package fc;

import java.io.*;
import java.util.*;

public class Cookie {

    // class to read cookie file, generate a random cookie, and returns that cookie
    
    private static String file;
    public static void setFile(String file) {
        Cookie.file = file;
    }

    private static int numCookies = 0;

    static ArrayList<String> cookieList = new ArrayList<>();
    
    public static int open() throws IOException{

        String dir = System.getProperty("user.dir");

        try (FileReader fr = new FileReader(dir +  File.separator + "fc" + File.separator + file)) {
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

    public static String generateCookie(int numCookies) throws IOException{
        Random randNum = new Random();
        int cookieChosen = randNum.nextInt(numCookies);

        return cookieList.get(cookieChosen);
    }


    
}
