import java.io.*;
import java.util.concurrent.*;

public class HeadLibrarian extends User 
{

    public HeadLibrarian(String userType, String userName, String userId, String password) //Composition
    {
        super(userType, userName, userId, password);
    }

    public String getUserType() 
    {
        return "Head Librarian";
    }

    public void dispManageCatalog()
    {
        System.out.println(toString() + "\n\n\n\n");
        System.out.println("1. Add Book");
        System.out.println("2. Edit Book");
        System.out.println("3. Remove Book");
        System.out.println("4. Back");
    }


    

}
