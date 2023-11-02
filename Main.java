import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main  
{
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static String selection;
    static boolean back = false;
    static String loginMenuChoice = null;

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
        
        while(!(librarian.isLoginSuccessful || headLibrarian.isLoginSuccessful))
        {
            do 
            {
                Utilities.clearScreen();
                dispHomeMenu();
                loginMenuChoice = reader.readLine();
                switch (loginMenuChoice)
                {
                    case "1":
                    //Head Librarian Login Menu
                        Utilities.clearScreen();
                        System.out.println("Head Librarian Login"); 
                        headLibrarian.login();
                        
                        if(!headLibrarian.isLoginSuccessful)
                        {
                            loginMenuChoice = " ";
                        }
                        Utilities.clearScreen();
                        break;

                    case "2":
                    //Librarian Login Menu
                        Utilities.clearScreen();
                        System.out.println("Librarian Login"); 
                        librarian.login();
                        
                        if(!librarian.isLoginSuccessful)
                        {
                            loginMenuChoice = " ";
                        }
                        Utilities.clearScreen();
                        break;

                    case "3":
                        System.out.println("Exit");
                        Utilities.terminateSession();
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        TimeUnit.MILLISECONDS.sleep(500);
                        Utilities.clearScreen();
                        dispHomeMenu();
                        break;
                }
            } while ((!(loginMenuChoice.equals("1") || loginMenuChoice.equals("2") || loginMenuChoice.equals("3"))));

            if(loginMenuChoice.equals("1"))
            {     
                do 
                {
                    Utilities.clearScreen();
                    headLibrarian.dispHomePage();
                    selection = reader.readLine();
                    switch (selection) 
                    {
                        case "1":
                            do 
                            {
                                Utilities.clearScreen();
                                headLibrarian.dispManageCatalog();

                                    switch(reader.readLine())
                                    {
                                        case "1":
                                            
                                            break;

                                        case "2":
                                            
                                            break;

                                        case "3":
                                            
                                            break;

                                        case "4":
                                            back = true;
                                            break;

                                        default: 
                                            System.out.println("Invalid choice. Please try again.");
                                            TimeUnit.MILLISECONDS.sleep(500);
                                            Utilities.clearScreen();
                                            break;
                                    }           
                            }while(!back);
                            break;
            
                        case "2":
                            headLibrarian.logout();
                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                            TimeUnit.MILLISECONDS.sleep(500);
                            Utilities.clearScreen();
                            break;
                    }
                } while (headLibrarian.loginSuccessful());

            }
            

            if(loginMenuChoice.equals("2"))
            {
            do
            {
                Utilities.clearScreen();
                librarian.dispHomePage();
                selection = reader.readLine();
                switch (selection) 
                {
                    case "1":
                        Utilities.clearScreen();
                        do 
                        {
                            Utilities.clearScreen();
                            librarian.dispBrowseCatalog();
                            selection = reader.readLine();
                            switch(selection)
                            {
                                case "1":
                                    
                                    break;

                                case "2":
                                    
                                    break;

                                case "3":
                                    
                                    break;

                                case "4":
                                        
                                    break;

                                case "5":
                                    back = true;
                                    break;

                                default: 
                                    System.out.println("Invalid choice. Please try again.");
                                    TimeUnit.MILLISECONDS.sleep(500);
                                    Utilities.clearScreen();
                                    break;
                            }
                        }while(!back);
                        break;

                    case "2": break;

                    case "3": break;

                    case "4":
                        librarian.logout();
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        TimeUnit.MILLISECONDS.sleep(500);
                        Utilities.clearScreen();
                        break;
                }
            }while(librarian.loginSuccessful());
        }
        }
    }
}
