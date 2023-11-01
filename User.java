import java.io.*;

public abstract class User 
{
    protected String userType, userName, userId, password, contactNumber, readUserId, readPassword;
    boolean isLoginSuccessful;

    public abstract String getUserType();

    public void login() throws IOException
    {
        do 
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("User ID: ");
            readUserId = reader.readLine();
            System.out.println("Password: ");
            readPassword = reader.readLine();

            if(readUserId.equals(userId) && readPassword.equals(password))
            {
                isLoginSuccessful = true;
                System.out.println("Login Successful");
            }
            else
            {
                isLoginSuccessful = false;
                System.out.println("Invalid user Id or password. Please try again");
            }
        } while (isLoginSuccessful);
        
    }

    public void logout() throws InterruptedException
    {
        Utilities.terminateSession();
    }

    public boolean loginSuccessful()
    {
        return isLoginSuccessful;
    }

    
}
