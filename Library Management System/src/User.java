
import java.io.*;
import java.util.concurrent.*;

public abstract class User extends Utilities
{
    protected String userType, userName, userId, password, readUserId, readPassword;
    boolean isLoginSuccessful;

    public abstract String getUserType();
    public abstract void dispHomePage();

    public void login() throws IOException, InterruptedException
    {
        System.out.println("User ID: ");
        readUserId = Utilities.reader.readLine();
        System.out.println("Password: ");
        readPassword = Utilities.reader.readLine();

        if(readUserId.equals(userId) && readPassword.equals(password))
        {
            isLoginSuccessful = true;
            System.out.println("Login Successful");
            TimeUnit.MILLISECONDS.sleep(500);
            clearScreen();
        }
        else
        {
            isLoginSuccessful = false;
            System.out.println("Invalid user Id or password. Please try again");
            TimeUnit.MILLISECONDS.sleep(500);
            clearScreen();
        }
    }

    public void logout()
    {
        isLoginSuccessful = false;
    }

    public boolean loginSuccessful()
    {
        return isLoginSuccessful;
    }

    
}
