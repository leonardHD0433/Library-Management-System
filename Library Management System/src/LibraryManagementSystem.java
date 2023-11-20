import java.io.*;
public class LibraryManagementSystem  
{   
    static Session session;
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException
    {
        session = new Session();
        boolean exit = false;
        do
        {
            UtilitiesForSystem.clearScreen();
            exit = session.loginPage();
            session = new Session();
        }while(!exit);
    }
}