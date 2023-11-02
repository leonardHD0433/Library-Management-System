import java.io.*;
import java.util.concurrent.*;

public class HeadLibrarian extends User 
{
    private String contactNumber;

    public HeadLibrarian(String userName, String userId, String password, String contactNumber) //Composition
    {
        getUserType();
        setHeadLibrarianDetails(userName, userId, password, contactNumber);
    }

    public void setHeadLibrarianDetails(String userName, String userId, String password, String contactNumber)
    {
        this.userName = userName;
        this.userId = userId;
        this.password = password;
        this.contactNumber = contactNumber;
    }

    public String getUserType() 
    {
        return "Head Librarian";
    }

    public void dispHomePage()
    {
        System.out.println(toString() + "\n\n\n\n");
        System.out.println("1. Manage Catalog");
        System.out.println("2. Logout");
    }

    public void mainPageSelection(String selection) throws InterruptedException
    {
        switch(selection)
        {
            case "1":
                dispManageCatalog();
                break;
            case "2":
                logout();
                break;
        }
    }

    public void dispManageCatalog()
    {
        System.out.println(toString() + "\n\n\n\n");
        System.out.println("1. Add Book");
        System.out.println("2. Edit Book");
        System.out.println("3. Remove Book");
        System.out.println("4. Back");
    }

    public String toString()
    {
        return "Role: " + getUserType() + "\nUser Name: " + userName + "\nUser Id: " + userId + "\nContact Number: " + contactNumber;
    }

    

}
