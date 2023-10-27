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
        readString = reader.readLine();
        
        LoginMenuChoice = Integer.parseInt(readString);
    }
}
