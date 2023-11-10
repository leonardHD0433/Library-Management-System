import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Librarian extends User 
{
    public Librarian(String userType, String userName, String userId, String password) //Composition
    {
        super(userType, userName, userId, password);
    }

    //To view patron details
    public void viewPatron()
    {

    }

    public void bookMenuOptions(String selection) throws IOException, InterruptedException
    {
        if(selection.equals("1"))
        {
            chooseBook(selection);
        }
        else if(selection.equals("2") || selection.equals("3") || selection.equals("4") || selection.equals("5") || selection.equals("6"))
        {
            chooseBook("0");
        }
    }


    //Borrow and Return Book Methods Should be Here, 
    //these methods will be used to call the catalogs' methods that manipulate data or display 

    // Method to borrow a book
    

    // Method to return a book

}
