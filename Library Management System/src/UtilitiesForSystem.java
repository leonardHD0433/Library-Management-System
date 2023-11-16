import java.io.*;
import java.util.concurrent.*;

public class UtilitiesForSystem 
{
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void clearScreen()
    {
        for (int i = 0; i < 10; i++) 
        {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
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
    
    // This method checks to see if the user input has any digits! - [Shaun]
    public static boolean containsDigits(String input) {
        // Check if the input string contains digits
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                return true; // Digits found
            }
        }
        return false; // No digits found
    }
}
