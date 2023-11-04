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
    public void viewAll() {
        sortCatalog();
        System.out.println("No\tGenre\tBook Title\tAuthor\tPublisher");
        for (int i = 0; i < books.size(); i++) {
            System.out.print((i + 1) + "\t");
            System.out.print(books.get(i).getGenre() + "\t");
            System.out.print(books.get(i).getBookTitle() + "\t");
            System.out.print(books.get(i).getAuthor() + "\t");
            System.out.print(books.get(i).getPublisher() + "\n");
        }
    }
}
