import java.io.*;
import java.util.concurrent.*;

public class HeadLibrarian extends User 
{

    public HeadLibrarian(String userType, String userName, String userId, String password) //Composition
    {
        super(userType, userName, userId, password);
    }

    //provide selections to manage the catalog
    public boolean manageCatalog(String selection) throws IOException, InterruptedException
    {
        boolean backToHomePage = false;
        switch (selection) 
        {
            case "1":
                //add book; 
                break;

            case "2":
                //edit book
                break;

            case "3":
                //remove book 
                break;

            case "4":
                backToHomePage = true; break;

            default:
                System.out.println("Invalid choice. Please try again.");
                TimeUnit.MILLISECONDS.sleep(500);
                clearScreen();
        }
        return backToHomePage;
    }

    //add, edit, remove book methods will be here

    // add book method

    // edit book method

    // remove book method

    
}
