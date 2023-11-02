import java.util.concurrent.*;

public class Utilities 
{

    public static void clearScreen()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    public static void terminateSession() throws InterruptedException
    {
        System.out.println("Terminating session...");
        TimeUnit.MILLISECONDS.sleep(500);
        System.exit(0);
    }
}
