import java.io.*;
import java.util.concurrent.*;

public class Session
{
    private HeadLibrarian headLibrarian;
    private Librarian librarian;
    private String loginPageSelection, homePageSelection, manageCatalogSelection, browseCatalogSelection;

    public Session()
    {
        headLibrarian = new HeadLibrarian("HEAD LIBRARIAN", "Edwin Chua Jin Rui", "headStaff@001", "iamtheHeadLibrarianXD");
        librarian = new Librarian("LIBRARIAN", "Teh Yu Kang", "staff@002", "iamTehYuk6488");
    }

    public void loginPage() throws IOException, InterruptedException
    {
        do 
        {
            dispLoginPage();
            setLoginPageSelection();
            loginPageSelection(getLoginPageSelection());
        } while (!(getLoginPageSelection() == "1" || getLoginPageSelection() == "2" || getLoginPageSelection() == "3"));
    }

    public void setLoginPageSelection() throws IOException
    {
        loginPageSelection = UtilitiesForSystem.reader.readLine();
    }

    public String getLoginPageSelection()
    {
        return loginPageSelection;
    }

    public void loginPageSelection(String selection) throws IOException, InterruptedException
    {
        switch (selection) 
        {
            case "1":
                headLibrarian.login();
                break;
        
            case "2": 
                librarian.login();
                break;

            case "3":
                System.exit(0);
                break;

            default:
                System.out.println("Invalid Selection. Try again.");
                TimeUnit.MILLISECONDS.sleep(500);
                UtilitiesForSystem.clearScreen();
                break;
        }
    }

    public void setHomePageSelection() throws IOException
    {
        homePageSelection = UtilitiesForSystem.reader.readLine();
    }

    public String getHomePageSelection()
    {
        return homePageSelection;
    }

    public void dispLoginPage()
    {
        System.out.println("LIBRARY MANAGEMENT SYSTEM");
        System.out.println("==========================");
        System.out.println("1. Login as Head Librarian");
        System.out.println("2. Login as Librarian");
        System.out.println("3. Exit\n");
    }

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
                System.out.println("2. View Borrowed Books");
                System.out.println("3. View Patron");
                System.out.println("4. Logout");
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
