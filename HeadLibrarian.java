public class HeadLibrarian extends User 
{

    public HeadLibrarian()
    {
        this("Edwin Chua Jin Rui", "headStaff@001", "iamtheHeadLibrarianXD", "012-805-0296");
    }

    public HeadLibrarian(String userName, String userId, String password, String contactNumber) //Composition
    {
        setUserType();
        setHeadLibrarianDetails(userName, userId, password, contactNumber);
    }

    public void setHeadLibrarianDetails(String userName, String userId, String password, String contactNumber)
    {
        this.userName = userName;
        this.userId = userId;
        this.password = password;
        this.contactNumber = contactNumber;
    }

    public void setUserType() 
    {
        userType = "Head Librarian";
    }

    public void headLibrarianLogin(String userId, String password) 
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

    public String toString()
    {
        return "Role: " + userType + "\n\nUser Name: " + userName + "\n\nUser Id: " + userId + "\n\nContact Number: " + contactNumber;
    }

    

}
