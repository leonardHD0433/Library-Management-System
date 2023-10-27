public class HeadLibrarian extends User 
{
    public void setUserType() 
    {
        userType = "Head Librarian";
    }

    public HeadLibrarian(String userName, String userId, String password, String contactNumber) //Composition
    {
        setHeadLibrarianDetails(userName, userId, password, contactNumber);
    }

    public void setHeadLibrarianDetails(String userName, String userId, String password, String contactNumber)
    {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.contactNumber = contactNumber;
    }

}
