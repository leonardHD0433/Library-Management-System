import java.util.*;

public class Catalog
{
    private ArrayList<Book> books = new ArrayList<Book>();
    
    public void initialiseCatalog()
    {
        for(int i = 0; i < 10; i++)
        {
            books.add(new Book(i));
        }
    }

    // Method to display all books in the catalog
    public void displayBooks() {
        for (int i = 0; i < books.size(); i++) 
        {
            System.out.println(books.get(i));
        }
    }
}
