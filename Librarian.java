public class Librarian extends User 
{

    public Librarian(String userName, String userId, String password) //Composition
    {
        getUserType();
        setLibrarianDetails(userName, userId, password);
    }

    public void setLibrarianDetails(String userName, String userId, String password)
    {
        this.userName = userName;
        this.userId = userId;
        this.password = password;
    }

    public String getUserType() 
    {
        return "Librarian";
    }

    public void dispLibrarianHomePage()
    {
        System.out.println("1. Browse Catalog");
        System.out.println("2. View Borrowed Books");
        System.out.println("3. View Patron");
        System.out.println("4. Logout");
    }

    public void librarianPageSelection(int selection) throws InterruptedException
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
                logout();
                break;
        }
    }

    public void dispBrowseCatalog()
    {
        System.out.println(toString());
        System.out.println("1. Browse by Title");
        System.out.println("2. Browse by Author");
        System.out.println("3. Browse by ISBN");
        System.out.println("4. Back");
    }


    public String toString()
    {
        return "Role: " + userType + "\nUser Name: " + userName + "\nUser Id: " + userId;
    }

    
}
