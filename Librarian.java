public class Librarian extends User implements PersonalData
{
    public Librarian(String userName, String userId, String password, String contactNumber) //Composition
    {
        setUserType();
        setLibrarianDetails(userName, userId, password, contactNumber);
    }

    public void setLibrarianDetails(String userName, String userId, String password, String contactNumber)
    {
        this.userName = userName;
        this.userId = userId;
        this.password = password;
        this.contactNumber = contactNumber;
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
