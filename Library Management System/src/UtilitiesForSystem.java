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

    public static void selectionErrorMsg() throws InterruptedException
    {
        System.out.println("Invalid Selection. Try again.");
        TimeUnit.MILLISECONDS.sleep(500);
        clearScreen();
    }

    public static boolean allCharacterAreDigits(String str)
    {
        if(!str.isBlank())
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
        else
        {
            return false;
        }
    }
    /* Trying to make a method that makes sure no digits are in the user input! - Shaun
    public static boolean allCharactersAreWords(String str)
    {
        if(!str.isBlank())
        {
            for(int i = 0; i < str.length(); i++)
            {
                if(Character.isDigit(str.charAt(i)))
                {
                    return false;
                }
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    */
}
