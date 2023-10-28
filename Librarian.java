public class Librarian extends User implements PersonalData
{
    private int librarianArrayPos;
    private boolean isLoginSuccessful;

    public Librarian()
    {
        this(0);
    }

    public Librarian(int i) //Composition
    {
        setUserType();
        setLibrarianDetails(i);
    }

    public void setLibrarianDetails(int i)
    {
        this.userName = LibrarianName[i];
        this.userId = LibrarianUserId[i];
        this.password = LibrarianPassword[i];
    }

    public void login(String userId, String password) 
    {
        super.login(userId, password);
        for(int pos =0; pos < 4; pos++)
        {
            if(tempUserId.equals(LibrarianUserId[pos]) && tempPassword.equals(LibrarianPassword[pos]))
            {
                librarianArrayPos = pos;

                break;
            }
        }

        if(tempUserId.equals(LibrarianUserId[librarianArrayPos]) && tempPassword.equals(LibrarianPassword[librarianArrayPos]))
        {
            isLoginSuccessful = true;
            System.out.println("Login Successful");
        }
        else
        {
            isLoginSuccessful = false;
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

    public int getPosition()
    {
        return librarianArrayPos;
    }

    public String toString()
    {
        return "Role: " + userType + "\n\nUser Name: " + userName + "\n\nUser Id: " + userId + "\n\nContact Number: " + contactNumber;
    }

    public boolean loginSuccessful()
    {
        return isLoginSuccessful;
    }
}
