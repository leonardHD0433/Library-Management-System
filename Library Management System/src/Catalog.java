import java.io.IOException;
import java.util.*;

public class Catalog
{
    private ArrayList<Book> books = new ArrayList<Book>();
    ArrayList <Integer> bookPos = new ArrayList<Integer>();
    boolean found;
    private int searchResultNo;
    
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
        do
        {
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
                searchResultNo = 1;
                if (books.get(i).getBookTitle().contains(title)) 
                {
                    dispSearchResult(i);
                }
                else
                {
                    found = false;
                }

                if(!found)
                {
                    System.out.println("Book not found.");
                }
            }
        }while(!found);
    }

    // Method to search for a book by author
    public void searchByAuthor() throws IOException
    {
        String author;
        bookPos.clear();
        Utilities.clearScreen();
        do
        {
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
                searchResultNo = 1;
                if (books.get(i).getAuthor().contains(author)) 
                {
                    dispSearchResult(i);
                }
                else
                {
                    found = false;
                } 
            }

            if(!found)
            {
                System.out.println("Book not found.");
            }
        }while(!found);
    }

    // Method to search for a book by genre
    public void searchByGenre() throws IOException
    {
        String chooseGenre;
        int index = 0, j =1;
        boolean is_digit;
        ArrayList <String> genre = new ArrayList<String>();
        for(int i = 0; i < books.size(); i++)
        {
            if(!genre.contains(books.get(i).getGenre()))
            {
                genre.add(books.get(i).getGenre());
            }
        }

        Collections.sort(genre, 
        new Comparator <String>() 
        {
            public int compare(String genre1, String genre2) 
            {
                return genre1.compareTo(genre2);
            }
        }
        );

        bookPos.clear();
        
        do{
            do 
            {
                Utilities.clearScreen();
                System.out.println("==================");
                System.out.println("SEARCH BY GENRE"); 
                System.out.println("==================");
                for(int i = 0; i < genre.size(); i++)
                {
                    System.out.println((i + 1) + ". " + genre.get(i));
                }
                System.out.print("\n\nChoose Genre: ");
                chooseGenre = Utilities.reader.readLine();
                is_digit = chooseGenre.chars().allMatch( Character::isDigit );
                if(!is_digit)
                {
                    System.out.println("Please enter a digit.");
                }
                else
                {
                    index = Integer.parseInt(chooseGenre) - 1;
                }
                
                if (index > genre.size() || index < 0)
                {
                    System.out.println("Invalid choice. Please try again.");
                }
                else
                {
                    break;
                }
            } while (!is_digit);

            if(is_digit && (index < genre.size() && index > 0))
            {
                System.out.println("SEARCH RESULTS");
                System.out.println("==============================================================================================================================================================");
                System.out.println("No.");
                for (int i = 0; i < books.size(); i++) 
                {
                    searchResultNo = 1;
                    if (books.get(i).getGenre().equals(genre.get(index))) 
                    {
                        dispSearchResult(i);
                    }
                    else if (i == (books.size()) && !(books.get(i).getGenre().equals(genre.get(index))))
                    {
                        found = false;
                    } 
                }  
            }

            if(!found)
            {
                System.out.println("Book not found.");
            }
        }while(!found);
    }

    public void dispSearchResult(int i)
    {
        found = true;
        System.out.println(searchResultNo);
        System.out.println("==============================================================================================================================================================");
        System.out.println(" " + (searchResultNo));
        System.out.println("==============================================================================================================================================================");
        System.out.println("==============================================================================================================================================================");
        System.out.println("Book Tile: " + books.get(i).getBookTitle());
        System.out.println("Genre: " + books.get(i).getGenre());
        System.out.println("Author: " + books.get(i).getAuthor());
        System.out.println("Publisher: " + books.get(i).getPublisher());
        System.out.println("Year Published: " + books.get(i).getYearPublished());
        System.out.println("==============================================================================================================================================================\n");
        bookPos.add(i);
        searchResultNo++;
    }
}
