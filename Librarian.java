public class Librarian extends User implements PersonalData
{
    public Librarian(int i) //Composition
    {
        setUserType();
        setLibrarianDetails(LibrarianName[i], LibrarianUserId[i], LibrarianPassword[i]);
    }

    public void setLibrarianDetails(String userName, String userId, String password)
    {
        this.userName = userName;
        this.userId = userId;
        this.password = password;
    }

    public void login(String userId, String password) 
    {
        super.login(userId, password);
        if(tempUserId.equals(userId) && tempPassword.equals(password))
        {
            System.out.println("Login Successful");
        }
        else
        {
            System.out.println("Invalid user Id or password. Please try again");
        }
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

    public String toString()
    {
        return "Role: " + userType + "\n\nUser Name: " + userName + "\n\nUser Id: " + userId + "\n\nContact Number: " + contactNumber;
    }
}
