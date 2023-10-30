import java.io.IOException;

public class Librarian extends User implements PersonalData
{
    private int librarianArrayPos;
    private boolean isLoginSuccessful;

    public Librarian()
    {
        this("Teh Yu Kang", "staff@002", "iamTehYuk6488");
    }

    public Librarian(String userName, String userId, String password) //Composition
    {
        setUserType();
        setLibrarianDetails(userName, userId, password);
    }

    public void setLibrarianDetails(String userName, String userId, String password)
    {
        this.userName = userName;
        this.userId = userId;
        this.password = password;
    }

    public void login() throws IOException
    {
        super.login();
    }

    public void dispLibrarianPage()
    {
        System.out.println("1. Browse Catalog");
        System.out.println("2. View Borrowed Books");
        System.out.println("3. View Patron");
        System.out.println("4. Logout");
    }

    public void librarianPageSelection(int selection)
    {
        switch(selection)
        {
            case 1:
                dispBrowseCatalog();
                break;
            case 2:
                System.out.println("View Borrowed Books");
                break;
            case 3:
                System.out.println("View Patron");
                break;
            case 4:
                System.out.println("Logout");
                break;
        }
    }

    public void dispBrowseCatalog()
    {
        System.out.println("1. Browse by Title");
        System.out.println("2. Browse by Author");
        System.out.println("3. Browse by ISBN");
        System.out.println("4. Back");
    }

    public void setUserType() 
    {
        userType = "Librarian";
    }

    public int getPosition()
    {
        return librarianArrayPos;
    }

    public String toString()
    {
        return "Role: " + userType + "\n\nUser Name: " + userName + "\n\nUser Id: " + userId;
    }

    
}
