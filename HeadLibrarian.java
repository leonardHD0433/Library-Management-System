public class HeadLibrarian extends User 
{

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

    public void dispHeadLibrarianHomePage()
    {
        System.out.println("1. Manage Catalog");
        
        System.out.println("2. Logout");
    }

    public void headLibrarianPageSelection(int selection) throws InterruptedException
    {
        switch(selection)
        {
            case 1:
                System.out.println("Manage Catalog");
                break;
            case 2:
                logout();
                break;
        }
    }

    public String toString()
    {
        return "Role: " + userType + "\nUser Name: " + userName + "\nUser Id: " + userId + "\nContact Number: " + contactNumber;
    }

    

}
