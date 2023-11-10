import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class Main  
{   
    public static void main(String[] args) throws IOException, InterruptedException
    {
        Session session = new Session();
        boolean exit = false;
        // session.startSession();
        do
        {
            UtilitiesForSystem.clearScreen();
            exit = session.loginPage();
        }while(!exit);
    }
}
