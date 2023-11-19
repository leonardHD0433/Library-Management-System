import java.io.*;
public class Main  
{   
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException
    {
        Session session = new Session();
        boolean exit = false;
        do
        {
            UtilitiesForSystem.clearScreen();
            exit = session.loginPage();
            session = new Session();
        }while(!exit);
    }
}