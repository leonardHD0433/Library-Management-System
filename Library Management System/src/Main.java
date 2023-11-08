import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class Main  
{
    static Session session;
    
    public static void main(String[] args) throws IOException, InterruptedException
    {
        session = new Session();
        session.startSession();
    }
}
