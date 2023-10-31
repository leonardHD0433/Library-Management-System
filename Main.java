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

    public static void createPatron()
    {
        Patron[] patron = new Patron[5];
        for(int i = 0; i < patron.length; i++)
        {
            patron[i] = new Patron(i);
        }
    }
    
    public static void main(String[] args) throws IOException, InterruptedException
    {
        HeadLibrarian headLibrarian = new HeadLibrarian("Edwin Chua Jin Rui", "headStaff@001", "iamtheHeadLibrarianXD", "012-805-0296");
        Librarian librarian = new Librarian("Teh Yu Kang", "staff@002", "iamTehYuk6488");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int loginMenuChoice = 0;
        String readString;
        createPatron();
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
                do {
                    
                } while (!headLibrarian.loginSuccessful());
                headLibrarian.login();
                clearScreen();
                System.out.println(headLibrarian);
            break;

            case 2:
            //Librarian Login Menu
                clearScreen();
                System.out.println("Login as Librarian"); 
                do
                {
                    librarian.login();
                    clearScreen();
                }while(!librarian.loginSuccessful());

                System.out.println(librarian);
                break;

            case 3:
                System.out.println("Exit");
                break;
        }

        
        
    }
}
