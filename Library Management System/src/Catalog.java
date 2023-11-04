import java.io.IOException;
import java.util.*;

public class Catalog
{
    private ArrayList<Book> books = new ArrayList<Book>();
    ArrayList <Integer> bookPos = new ArrayList<Integer>();
    
    public Catalog()
    {
        initialiseCatalog();
        sortCatalog();
    }
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

    // Method to search for a book by title
    public void searchByTitle() throws IOException
    {
        String title;
        bookPos.clear();
        Utilities.clearScreen();
        System.out.println("==================");       
        System.out.println("SEARCH BY TITLE");
        System.out.println("==================");
        System.out.print("\n\nEnter Title: ");
        title = Utilities.reader.readLine();
        System.out.println("SEARCH RESULTS");
        System.out.println("==============================================================================================================================================================");
        System.out.println("No.");
        for (int i = 0; i < books.size(); i++) 
        {
            int j = 1;
            if (books.get(i).getBookTitle().contains(title)) 
            {
                    System.out.println(j);
                    j++;
                    System.out.println("==============================================================================================================================================================");
                    System.out.println(" " + (j));
                    System.out.println("==============================================================================================================================================================");
                    System.out.println("==============================================================================================================================================================");
                    System.out.println("Book Tile: " + books.get(i).getBookTitle());
                    System.out.println("Genre: " + books.get(i).getGenre());
                    System.out.println("Author: " + books.get(i).getAuthor());
                    System.out.println("Publisher: " + books.get(i).getPublisher());
                    System.out.println("Year Published: " + books.get(i).getYearPublished());
                    bookPos.add(i);
                    System.out.println("==============================================================================================================================================================\n");
            }
            else
            {
                System.out.println("Book not found.");
            }
        }
    }

    // Method to search for a book by author
    public void searchByAuthor() throws IOException
    {
        String author;
        bookPos.clear();
        Utilities.clearScreen();
        System.out.println("==================");
        System.out.println("SEARCH BY AUTHOR"); 
        System.out.println("==================");
        System.out.print("\n\nEnter Author: ");
        author = Utilities.reader.readLine();
        System.out.println("SEARCH RESULTS");
        System.out.println("==============================================================================================================================================================");
        System.out.println("No.");
        for (int i = 0; i < books.size(); i++) 
        {
            int j = 1;
            if (books.get(i).getAuthor().contains(author)) 
            {
                    System.out.println(j);
                    j++;
                    System.out.println("==============================================================================================================================================================");
                    System.out.println(" " + (j));
                    System.out.println("==============================================================================================================================================================");
                    System.out.println("==============================================================================================================================================================");
                    System.out.println("Book Tile: " + books.get(i).getBookTitle());
                    System.out.println("Genre: " + books.get(i).getGenre());
                    System.out.println("Author: " + books.get(i).getAuthor());
                    System.out.println("Publisher: " + books.get(i).getPublisher());
                    System.out.println("Year Published: " + books.get(i).getYearPublished());
                    bookPos.add(i);
                    System.out.println("==============================================================================================================================================================\n");
            }
            else
            {
                System.out.println("Book not found.");
            }
        }
    }

}
