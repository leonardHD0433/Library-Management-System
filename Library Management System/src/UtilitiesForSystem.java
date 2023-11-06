import java.io.*;
import java.util.concurrent.*;

public class UtilitiesForSystem 
{
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void clearScreen()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    public static void terminateSession() throws InterruptedException
    {
        System.out.println("Terminating session...");
        TimeUnit.MILLISECONDS.sleep(500);
        System.exit(0);
    }

    public static boolean allCharacterAreDigits(String str)
    {
        for(int i = 0; i < str.length(); i++)
        {
            if(!Character.isDigit(str.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
}
