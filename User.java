public abstract class User 
{
    protected String userType, userName, userId, password, contactNumber, tempUserId, tempPassword;

    public abstract void setUserType();

    public void login(String userId, String password)
    {
        tempUserId = userId;
        tempPassword = password;
    }


}
