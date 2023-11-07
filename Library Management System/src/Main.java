import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class Main  
{
    static HeadLibrarian headLibrarian = new HeadLibrarian("Edwin Chua Jin Rui", "headStaff@001", "iamtheHeadLibrarianXD", "012-805-0296");
    static Librarian librarian = new Librarian("Teh Yu Kang", "staff@002", "iamTehYuk6488");
    static Catalog catalog = new Catalog();
    static String selection;
    static boolean back = false;
    static String loginMenuChoice = null;

    public static void dispLoginMenu()
    {
        System.out.println("LIBRARY MANAGEMENT SYSTEM");
        System.out.println("==========================");
        System.out.println("1. Login as Head Librarian");
        System.out.println("2. Login as Librarian");
        System.out.println("3. Exit\n");
    }
    
    public static void main(String[] args) throws IOException, InterruptedException
    {
        Patron[] patron = new Patron[5];
        for(int i = 0; i < patron.length; i++)
        {
            patron[i] = new Patron(i);
        }

        loginMenu();

        if(loginMenuChoice.equals("1"))
        {     
            do 
            {
                UtilitiesForSystem.clearScreen();
                headLibrarian.dispHomePage();
                selection = UtilitiesForSystem.reader.readLine();
                switch (selection) 
                {
                    case "1":
                        do 
                        {
                            UtilitiesForSystem.clearScreen();
                            headLibrarian.dispManageCatalog();
                                selection = UtilitiesForSystem.reader.readLine();
                                switch(selection)
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
                                        UtilitiesForSystem.clearScreen();
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
                        UtilitiesForSystem.clearScreen();
                        break;
                }
            } while (headLibrarian.loginSuccessful());
        }
            

        if(loginMenuChoice.equals("2"))
        {
            do
            {
                UtilitiesForSystem.clearScreen();
                librarian.dispHomePage();
                selection = UtilitiesForSystem.reader.readLine();
                switch (selection) 
                {
                    case "1":
                        UtilitiesForSystem.clearScreen();
                        do 
                        {
                            UtilitiesForSystem.clearScreen();
                            librarian.dispBrowseCatalog();
                            selection = UtilitiesForSystem.reader.readLine();
                            browseCatalog(selection);
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
                        UtilitiesForSystem.clearScreen();
                        break;
                }
            }while(librarian.loginSuccessful());
        }
    }
    

    public static void loginMenu() throws IOException, InterruptedException
    {
        while(!(librarian.isLoginSuccessful || headLibrarian.isLoginSuccessful))
        {
            do 
            {
                UtilitiesForSystem.clearScreen();
                dispHomeMenu();
                loginMenuChoice = UtilitiesForSystem.reader.readLine();
                switch (loginMenuChoice)
                {
                    case "1":
                    //Head Librarian Login Menu
                        UtilitiesForSystem.clearScreen();
                        System.out.println("Head Librarian Login"); 
                        headLibrarian.login();
                        
                        if(!headLibrarian.isLoginSuccessful)
                        {
                            loginMenuChoice = " ";
                        }
                        UtilitiesForSystem.clearScreen();
                        break;

                    case "2":
                    //Librarian Login Menu
                        UtilitiesForSystem.clearScreen();
                        System.out.println("Librarian Login"); 
                        librarian.login();
                        
                        if(!librarian.isLoginSuccessful)
                        {
                            loginMenuChoice = " ";
                        }
                        UtilitiesForSystem.clearScreen();
                        break;

                    case "3":
                        System.out.println("Exit");
                        UtilitiesForSystem.terminateSession();
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        TimeUnit.MILLISECONDS.sleep(500);
                        UtilitiesForSystem.clearScreen();
                        dispHomeMenu();
                        break;
                }
            } while ((!(loginMenuChoice.equals("1") || loginMenuChoice.equals("2") || loginMenuChoice.equals("3"))));
        }
    }

    public static void browseCatalog(String selection) throws IOException, InterruptedException
    {
        switch(selection)
        {
            case "1":
                catalog.viewAll();
                TimeUnit.MINUTES.sleep(5);
                break;

            case "2":
                catalog.searchByGenre();
                TimeUnit.MINUTES.sleep(5);
                break;

            case "3":
                catalog.searchByTitle();
                TimeUnit.MINUTES.sleep(5);
                break;

            case "4":
                catalog.searchByAuthor(); 
                TimeUnit.MINUTES.sleep(5);
                break;

            case "5":
                catalog.searchByPublisher();
                TimeUnit.MINUTES.sleep(5);  
                break;

            case "6":
                
                break;

            case "7":
                back = true;
                break;

            default: 
                System.out.println("Invalid choice. Please try again.");
                TimeUnit.MILLISECONDS.sleep(500);
                UtilitiesForSystem.clearScreen();
                break;
        }
    }
}
