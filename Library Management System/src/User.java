
import java.io.*;
import java.util.concurrent.*;

public abstract class User extends UtilitiesForSystem
{
    Catalog catalog;
    protected String userType, userName, userId, password, readUserId, readPassword;
    boolean isLoginSuccessful;

    public User(String loginMenuSelection, String userName, String userId, String password) //Composition
    {
        catalog = new Catalog();
        setUserDetails(userType, userName, userId, password);
    }

    public void setUserDetails(String userType, String userName, String userId, String password)
    {
        this.userType = userType;
        this.userName = userName;
        this.userId = userId;
        this.password = password;
    }

    public void login() throws IOException, InterruptedException
    {
        System.out.println("User ID: ");
        readUserId = UtilitiesForSystem.reader.readLine();
        System.out.println("Password: ");
        readPassword = UtilitiesForSystem.reader.readLine();

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

    public String getUserType()
    {
        return userType;
    }

    public void browseCatalog()
    {

    }
    
    public String toString()
    {
        return  "User Id: " + userId + "\nUser Name: " + userName  + "\nRole: " + userType;
    }
}
