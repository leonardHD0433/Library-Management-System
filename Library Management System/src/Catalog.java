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

    //sort Catalog by book title in ascending order
    public void sortCatalog()
    {
        Collections.sort(books, 
        new Comparator <Book>() 
        {
            public int compare(Book book1, Book book2) 
            {
                return book1.getBookTitle().compareTo(book2.getBookTitle());
            }
        }
        );
    }

    // Method to display all books in the catalog in ascending order of book title
    public void viewAll() 
    {
        sortCatalog();
        Utilities.clearScreen();
        System.out.println("CATALOG");
        System.out.println("==============================================================================================================================================================");
        System.out.println("No.");
        for (int i = 0; i < books.size(); i++) 
        { 
            System.out.println("==============================================================================================================================================================");
            System.out.println(" " + (i + 1));
            System.out.println("==============================================================================================================================================================");
            System.out.println("Book Tile: " + books.get(i).getBookTitle());
            System.out.println("Genre: " + books.get(i).getGenre());
            System.out.println("Author: " + books.get(i).getAuthor());
            System.out.println("Publisher: " + books.get(i).getPublisher());
            System.out.println("Year Published: " + books.get(i).getYearPublished());
            System.out.println("==============================================================================================================================================================\n");
        }
    }

}
