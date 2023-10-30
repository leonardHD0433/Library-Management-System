import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main
{
    public static void dispHomeMenu()
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
        HeadLibrarian headLibrarian = new HeadLibrarian();
        Librarian librarian = new Librarian();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int loginMenuChoice = 0;
        String readString;
        clearScreen();
        dispHomeMenu();

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
                dispHomeMenu();
            }
        } while (!(readString.equals("1") || readString.equals("2") || readString.equals("3")));

        switch (loginMenuChoice)
        {
            case 1:
            //Head Librarian Login Menu
                clearScreen();
                System.out.println("Head Librarian Login"); 
                headLibrarian.login();
                System.out.println(headLibrarian);
            break;

            case 2:
            //Librarian Login Menu
                clearScreen();
                System.out.println("Login as Librarian"); 
                do
                {
                    System.out.println("\nUsername: ");
                    librarian.login();
                }while(!librarian.loginSuccessful());

                System.out.println(librarian);
                break;

            case 3:
                System.out.println("Exit");
                
                break;
        }
        
    }
}
