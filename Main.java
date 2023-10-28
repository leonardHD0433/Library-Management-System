import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main
{
    public static void dispLoginMenu()
    {
        System.out.println("LIBRARY MANAGEMENT SYSTEM");
        System.out.println("**************************");
        System.out.println("1. Login as Head Librarian");
        System.out.println("2. Login as Librarian");
        System.out.println("3. Exit\n");
    }

    public static void clearScreen()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
    
    public static void main(String[] args) throws IOException, InterruptedException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int loginMenuChoice = 0;
        String readString;
        clearScreen();
        dispLoginMenu();

        do 
        {
            readString = reader.readLine();
            if (readString.equals("1") || readString.equals("2") || readString.equals("3"))
            {
                loginMenuChoice = Integer.parseInt(readString);
            }
            else
            {
                System.out.println("Invalid choice. Please try again.");
                TimeUnit.SECONDS.sleep(1);
                clearScreen();
                dispLoginMenu();
            }
        } while (!(readString.equals("1") || readString.equals("2") || readString.equals("3")));

        switch (loginMenuChoice)
        {
            case 1:
                System.out.println("Login as Head Librarian"); break;
            case 2:
                System.out.println("Login as Librarian"); break;
            case 3:
                System.out.println("Exit"); break;
        }
        
    }
}
