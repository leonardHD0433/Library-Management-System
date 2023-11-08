import java.io.*;
import java.util.concurrent.*;

public class Session
{
    private HeadLibrarian headLibrarian;
    private Librarian librarian;
    private String loginPageSelection, homePageSelection, manageCatalogSelection, browseCatalogSelection;
    private boolean exit = false, backToLoginPage, backToHomePage, isLoginSuccessful;

    public Session()
    {
        headLibrarian = new HeadLibrarian("HEAD LIBRARIAN", "Edwin Chua Jin Rui", "headStaff@001", "iamtheHeadLibrarianXD");
        librarian = new Librarian("LIBRARIAN", "Teh Yu Kang", "staff@002", "iamTehYuk6488");
    }

    // Method to start session
    public void startSession() throws IOException, InterruptedException
    {
        do
        {
            loginPage();
        }while(!exit);
    }

    // Login State
    public void loginPage() throws IOException, InterruptedException
    {
        backToLoginPage = false;
        do 
        {
            dispLoginPage();
            setLoginPageSelection();
            loginPageSelection(getLoginPageSelection());
        } while (!((getLoginPageSelection() == "1" || getLoginPageSelection() == "2") && isLoginSuccessful) &&  !(getLoginPageSelection() == "3"));

        if(isLoginSuccessful)
        {
            homePage();
        }
    }

    // Home Page State
    public void homePage() throws IOException, InterruptedException
    {
        do 
        {
            backToHomePage = false;
            UtilitiesForSystem.clearScreen();
            dispHomePage();
            setHomePageSelection();
            homePageSelection(getHomePageSelection());
        } while (!backToLoginPage);
    }

    // Login Page Selection Methods
    public void loginPageSelection(String selection) throws IOException, InterruptedException
    {
        switch (selection) 
        {
            case "1":
                isLoginSuccessful = headLibrarian.login();
                break;
        
            case "2": 
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
    public void homePageSelection(String selection) throws InterruptedException
    {
        switch (getLoginPageSelection()) 
        {
            case "1":
                switch (selection) 
                {
                    case "1":
                        headLibrarian.manageCatalog();
                        break;
                
                    case "2":
                        backToLoginPage = true;
                        break;

                    default:
                        UtilitiesForSystem.selectionErrorMsg();
                        break;
                }
                break;
        
            default:
                switch (selection) 
                {
                    case "1":
                        librarian.browseCatalog();
                        break;
                
                    case "2": 
                        librarian.viewPatron();
                        break;

                    case "3":
                        backToLoginPage = true;
                        break;

                    default:
                        UtilitiesForSystem.selectionErrorMsg();
                        break;
                }
                break;
        }
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

    // Browse Catalog Selection Methods
    public void browseCatalogSelection(String selection) throws IOException, InterruptedException
    {
        switch (selection) {
            case "1":
                
                break;

            case "2":
                    
                break;

            case "3":
                
                break;
        
            case "4":
                
                break;

            case "5":
                
                break;  

            case "6":
                backToHomePage = true;
                break;
        
            default:
                UtilitiesForSystem.selectionErrorMsg();
                break;
        }
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

    // Display Login Page
    public void dispLoginPage()
    {
        System.out.println("LIBRARY MANAGEMENT SYSTEM");
        System.out.println("==========================");
        System.out.println("1. Login as Head Librarian");
        System.out.println("2. Login as Librarian");
        System.out.println("3. Exit\n");
    }

    // Display Home Page
    public void dispHomePage()
    {
        switch (getLoginPageSelection()) 
        {
            case "1":
                System.out.println(headLibrarian.toString() + "\n\n\n\n");
                System.out.println("1. Manage Catalog");
                System.out.println("2. Logout");
                break;
        
            default:
                System.out.println(librarian.toString() + "\n\n\n\n");
                System.out.println("1. Browse Catalog");
                System.out.println("2. View Patron");
                System.out.println("3. Logout");
                break;
        }
    }

    public void dispBrowseCatalog()
    {
        System.out.println(librarian.toString() + "\n\n\n\n");
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
        System.out.println(headLibrarian.toString() + "\n\n\n\n");
        System.out.println("1. Add Book");
        System.out.println("2. Edit Book");
        System.out.println("3. Remove Book");
        System.out.println("4. Back");
    }
}
