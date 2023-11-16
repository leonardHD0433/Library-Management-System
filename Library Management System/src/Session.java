import java.io.*;
import java.util.concurrent.*;

public class Session
{
    private HeadLibrarian headLibrarian;
    private Librarian librarian;
    private String loginPageSelection, homePageSelection, manageCatalogSelection, browseCatalogSelection, borrow_returnSelection;
    private boolean isLoginSuccessful;

    public Session()
    {
        headLibrarian = new HeadLibrarian("HEAD LIBRARIAN", "Edwin Chua Jin Rui", "headStaff@001", "1234");
        librarian = new Librarian("LIBRARIAN", "Teh Yu Kang", "staff@002", "12345");
    }

    public boolean loginPage() throws IOException, InterruptedException
    {
        boolean exit = false;
        do 
        {
            dispLoginPage();
            setLoginPageSelection();
            exit = loginPageSelection(getLoginPageSelection());
        } while (!((getLoginPageSelection().equals("1") || getLoginPageSelection().equals("2")) && isLoginSuccessful) &&  !(getLoginPageSelection().equals("3")));

        if(isLoginSuccessful)
        {
            homePage();
        }

        return exit;
    }

    // Home Page State
    public void homePage() throws IOException, InterruptedException
    {
        boolean backToLoginPage = false;

        do 
        {
            backToLoginPage = false;
            UtilitiesForSystem.clearScreen();
            dispHomePage();
            setHomePageSelection();
            backToLoginPage = homePageSelection(getHomePageSelection());
        } while (!backToLoginPage);
    }

    // Login Page Selection Methods
    public boolean loginPageSelection(String selection) throws IOException, InterruptedException
    {
        boolean exit = false;

        switch (selection) 
        {
            case "1":
                UtilitiesForSystem.clearScreen();
                System.out.println("========================");
                System.out.println("| HEAD LIBRARIAN LOGIN |");
                System.out.println("========================\n");
                isLoginSuccessful = headLibrarian.login();
                break;
        
            case "2": 
                UtilitiesForSystem.clearScreen();
                System.out.println("========================");
                System.out.println("|    LIBRARIAN LOGIN   |");
                System.out.println("========================\n");
                isLoginSuccessful = librarian.login();
                break;

            case "3":
                isLoginSuccessful = false;
                exit = true;
                break;

            default:
                isLoginSuccessful = false;
                UtilitiesForSystem.selectionErrorMsg();
                break;
        }

        return exit;
    }

    // Setter for the Login Page Selection
    public void setLoginPageSelection() throws IOException
    {
        loginPageSelection = UtilitiesForSystem.reader.readLine();
    }

    // Getter for the Login Page Selection
    public String getLoginPageSelection()
    {
        return loginPageSelection;
    }

    // Home Page Selection Methods
    public boolean homePageSelection(String selection) throws InterruptedException, IOException
    {
        boolean backToLoginPage = false, backToHomePage = false;

        if(getLoginPageSelection().equals("1")) 
        {
            switch (selection) 
            {
                case "1":
                    do
                    {
                        dispManageCatalog();
                        setManageCatalogSelection();
                        backToHomePage = headLibrarian.manageCatalog(getManageCatalogSelection());
                    }while(!backToHomePage);
                    break;
            
                case "2":
                    backToLoginPage = true; break;

                default:
                    UtilitiesForSystem.selectionErrorMsg(); break;
            }
        }
        else
        {
            switch (selection) 
            {
                case "1":
                    do
                    {
                        dispBrowseCatalog();
                        setBrowseCatalogSelection();
                        backToHomePage = librarian.browseCatalog(getBrowseCatalogSelection());
                    }while(!backToHomePage);
                    break;
            
                case "2": 
                    do
                    {
                        librarian.viewPatron();
                    }while(!backToHomePage);
                    break;

                case "3":
                    backToLoginPage = true; break;

                default:
                    UtilitiesForSystem.selectionErrorMsg(); break;
            }
        }

        return backToLoginPage;
    }

    // Setter for the Home Page Selection
    public void setHomePageSelection() throws IOException
    {
        homePageSelection = UtilitiesForSystem.reader.readLine();
    }

    // Getter for the Home Page Selection
    public String getHomePageSelection()
    {
        return homePageSelection;
    }

    // Setter for the Browse Catalog Selection
    public void setBrowseCatalogSelection() throws IOException
    {
        browseCatalogSelection = UtilitiesForSystem.reader.readLine();
    }

    // Getter for the Browse Catalog Selection
    public String getBrowseCatalogSelection()
    {
        return browseCatalogSelection;
    }

    public void setBorrowReturnSelection() throws IOException
    {
        borrow_returnSelection = UtilitiesForSystem.reader.readLine();
    }

    public String getBorrowReturnSelection()
    {
        return borrow_returnSelection;
    }

    //setter for manage catalog selection
    public void setManageCatalogSelection() throws IOException
    {
        manageCatalogSelection = UtilitiesForSystem.reader.readLine();
    }

    //getter for manage catalog selection
    public String getManageCatalogSelection()
    {
        return manageCatalogSelection;
    }

    public void borrow_returnPage()
    {

    }
  
    public void logout() //save sessions and data updates using files
    {

    }















    // Display Login Page
    public void dispLoginPage()
    {
        System.out.println("LIBRARY MANAGEMENT SYSTEM");
        System.out.println("==========================");
        System.out.println("1. Login as Head Librarian");
        System.out.println("2. Login as Librarian");
        System.out.println("3. Exit\n");
        System.out.print("Selection: ");
    }

    // Display Home Page
    public void dispHomePage()
    {
        switch (getLoginPageSelection()) 
        {
            case "1":
                System.out.println(headLibrarian + "\n\n\n\n");
                System.out.println("1. Manage Catalog");
                System.out.println("2. Logout");
                break;
        
            default:
                System.out.println(librarian + "\n\n\n\n");
                System.out.println("1. Browse Catalog");
                System.out.println("2. View Patron");
                System.out.println("3. Logout");
                break;
        }
    }

    public void dispBrowseCatalog()
    {
        UtilitiesForSystem.clearScreen();
        System.out.println(librarian + "\n\n\n\n");
        System.out.println("1. View All");
        System.out.println("2. Browse by Genre");
        System.out.println("3. Browse by Title");
        System.out.println("4. Browse by Author");
        System.out.println("5. Browse by Publisher");
        System.out.println("6. Browse by ISBN");
        System.out.println("7. Back");
    }

    public void dispManageCatalog()
    {
        UtilitiesForSystem.clearScreen();
        System.out.println(headLibrarian + "\n\n\n\n");
        System.out.println("1. Add Book");
        System.out.println("2. Edit Book");
        System.out.println("3. Remove Book");
        System.out.println("4. Back");
    }

    public void dispBorrowReturn()
    {
        System.out.println(librarian + "\n\n\n\n");
        System.out.println("1. Borrow Book");
        System.out.println("2. Return Book");
        System.out.println("3. Back");
    }
}
