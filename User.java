public class User 
{
    protected String userType, userName, userId, password, contactNumber;

    public User()
    {
        this(null, null, null, null, null);
    }

    public User(String userType, String userName, String userId, String password, String contactNumber)
    {
        setUserDetails(userType, userName, userId, password, contactNumber);
    }

    public void setUserDetails(String userType, String userName, String userId, String password, String contactNumber)
    {
        this.userType = userType;
        this.userName = userName;
        this.userId = userId;
        this.password = password;
        this.contactNumber = contactNumber;
    }


}
