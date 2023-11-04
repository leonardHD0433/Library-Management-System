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

    public void dispHomePage()
    {
        System.out.println(toString() + "\n\n\n\n");
        System.out.println("1. Browse Catalog");
        System.out.println("2. View Borrowed Books");
        System.out.println("3. View Patron");
        System.out.println("4. Logout");
    }

    public void dispBrowseCatalog()
    {
        System.out.println(toString() + "\n\n\n\n");
        System.out.println("1. View All");
        System.out.println("2. Browse by Title");
        System.out.println("3. Browse by Genre");
        System.out.println("4. Browse by Author");
        System.out.println("5. Browse by ISBN");
        System.out.println("6. Back");
    }

    public String toString()
    {
        return "Role: " + getUserType() + "\nUser Name: " + userName + "\nUser Id: " + userId;
    }

    
}
