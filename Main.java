import java.io.*;
import java.util.*;

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
    
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int LoginMenuChoice;
        String readString;
        dispLoginMenu();
        
        do 
        {
            readString = reader.readLine();
            if (readString.equals("1") || readString.equals("2") || readString.equals("3"))
            {
                LoginMenuChoice = Integer.parseInt(readString);
            }
            else
            {
                System.out.println("Invalid choice. Please try again.");
                dispLoginMenu();
            }
        } while (!(readString.equals("1") || readString.equals("2") || readString.equals("3")));
        
    }
}
