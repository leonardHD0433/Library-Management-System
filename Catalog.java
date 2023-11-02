import java.util.*;

public class Catalog
{
    protected ArrayList <Book> catalog = new ArrayList <Book>();   

    public void addBook()
    {
        for(int i = 0; i < 5; i++)
        {
            catalog.add(new Book(i, " ", " ", true));
        }
    }
    
}
