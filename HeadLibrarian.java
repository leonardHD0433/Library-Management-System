public class HeadLibrarian extends User 
{
    public void setUserType() 
    {
        userType = "Head Librarian";
    }

    public HeadLibrarian(String userType, String userName, String userId, String password, String contactNumber) //Composition
    {
        setHeadLibrarianDetails(userType, userName, userId, password, contactNumber);
    }

    public void setHeadLibrarianDetails(String userType, String userName, String userId, String password, String contactNumber)
    {
        this.userType = userType;
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.contactNumber = contactNumber;
    }

}
