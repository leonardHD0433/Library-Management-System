import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main
{
    public static void dispHomeMenu()
    {
        System.out.println("LIBRARY MANAGEMENT SYSTEM");
        System.out.println("==========================");
        System.out.println("1. Login as Head Librarian");
        System.out.println("2. Login as Librarian");
        System.out.println("3. Exit\n");
    }
    
    public static void main(String[] args) throws IOException, InterruptedException
    {
        HeadLibrarian headLibrarian = new HeadLibrarian("Edwin Chua Jin Rui", "headStaff@001", "iamtheHeadLibrarianXD", "012-805-0296");
        Librarian librarian = new Librarian("Teh Yu Kang", "staff@002", "iamTehYuk6488");
        Patron[] patron = new Patron[5];
        for(int i = 0; i < patron.length; i++)
        {
            patron[i] = new Patron(i);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String loginMenuChoice = null;
        Utilities.clearScreen();
        dispHomeMenu();

        do 
        {
            loginMenuChoice = reader.readLine();
            switch (loginMenuChoice)
            {
                case "1":
                //Head Librarian Login Menu
                    Utilities.clearScreen();
                    System.out.println("Head Librarian Login"); 
                    headLibrarian.login();
                    Utilities.clearScreen();
                    System.out.println(headLibrarian);
                    break;

                case "2":
                //Librarian Login Menu
                    Utilities.clearScreen();
                    System.out.println("Login as Librarian"); 
                    librarian.login();
                    Utilities.clearScreen();
                    System.out.println(librarian);
                    librarian.dispLibrarianHomePage();
                    break;

                case "3":
                    System.out.println("Exit");
                    Utilities.terminateSession();
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    TimeUnit.SECONDS.sleep(1);
                    Utilities.clearScreen();
                    dispHomeMenu();
                    break;
            }
        } while (!(loginMenuChoice.equals("1") || loginMenuChoice.equals("2") || loginMenuChoice.equals("3")));

        

        
        
    }
}
