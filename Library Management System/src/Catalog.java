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

    // Method to initialise the catalog with 10 books
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
        UtilitiesForSystem.clearScreen();
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
        searchResultNo = 1;
        bookPos.clear();
        do
        {
            UtilitiesForSystem.clearScreen();
            System.out.println("==================");       
            System.out.println("SEARCH BY TITLE");
            System.out.println("==================");
            System.out.print("\n\nEnter Title: ");
            title = UtilitiesForSystem.reader.readLine();
            System.out.println("SEARCH RESULTS");
            System.out.println("==============================================================================================================================================================");
            System.out.println("No.");
            for (int i = 0; i < books.size(); i++) 
            {
                if (books.get(i).getBookTitle().toLowerCase().contains(title.toLowerCase())) 
                {
                    dispSearchResult(i);
                }
                else if (i == (books.size()) && (searchResultNo == 1))
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
        searchResultNo = 1;
        bookPos.clear();
        do
        {
            UtilitiesForSystem.clearScreen();
            System.out.println("==================");
            System.out.println("SEARCH BY AUTHOR"); 
            System.out.println("==================");
            System.out.print("\n\nEnter Author: ");
            author = UtilitiesForSystem.reader.readLine();
            System.out.println("SEARCH RESULTS");
            System.out.println("==============================================================================================================================================================");
            System.out.println("No.");
            for (int i = 0; i < books.size(); i++) 
            {
                if ((books.get(i).getAuthor().toLowerCase().contains(author.toLowerCase()))) 
                {
                    dispSearchResult(i);
                }
                else if(i == books.size() && (searchResultNo == 1))
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
        int index = 0;
        searchResultNo = 1;
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
                UtilitiesForSystem.clearScreen();
                System.out.println("==================");
                System.out.println("SEARCH BY GENRE"); 
                System.out.println("==================");
                for(int i = 0; i < genre.size(); i++)
                {
                    System.out.println((i + 1) + ". " + genre.get(i));
                }
                System.out.print("\n\nChoose Genre: ");
                chooseGenre = UtilitiesForSystem.reader.readLine();
                is_digit = chooseGenre.chars().allMatch(Character::isDigit);
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

            if(is_digit && (index < genre.size() && index >= 0))
            {
                System.out.println("SEARCH RESULTS");
                System.out.println("==============================================================================================================================================================");
                System.out.println("No.");
                for (int i = 0; i < books.size(); i++) 
                {
                    if (books.get(i).getGenre().equals(genre.get(index))) 
                    {
                        dispSearchResult(i);
                    }
                    else if (i == (books.size()) && (searchResultNo == 1))
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

    // Method to search for a book by publisher
    public void searchByPublisher() throws IOException
    {
        String publisher;
        searchResultNo = 1;
        bookPos.clear();
        UtilitiesForSystem.clearScreen();
        do
        {
            System.out.println("==================");
            System.out.println("SEARCH BY PUBLISHER"); 
            System.out.println("==================");
            System.out.print("\n\nEnter Publisher: ");
            publisher = UtilitiesForSystem.reader.readLine();
            System.out.println("SEARCH RESULTS");
            System.out.println("==============================================================================================================================================================");
            System.out.println("No.");
            for (int i = 0; i < books.size(); i++) 
            {
                if (books.get(i).getPublisher().toLowerCase().contains(publisher.toLowerCase())) 
                {
                    dispSearchResult(i);
                }
                else if(i == (books.size()) && (searchResultNo == 1))
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
    
    //Method to search for a book by ISBN
    public void searchByISBN() throws IOException
    {
        String isbn = null;
        searchResultNo = 1;
        bookPos.clear();
        UtilitiesForSystem.clearScreen();
        do
        {
            while (isbn.equals(null)) 
            {
                UtilitiesForSystem.clearScreen();
                System.out.println("==================");
                System.out.println("SEARCH BY ISBN"); 
                System.out.println("==================");
                System.out.print("\n\nEnter ISBN (ISBN-13 format): ");
                isbn = UtilitiesForSystem.reader.readLine();
                if ((isbn.length() == 13 && isbn.startsWith("978") && UtilitiesForSystem.allCharacterAreDigits(isbn)) || (isbn.length() == 14 && isbn.startsWith("978-") && UtilitiesForSystem.allCharacterAreDigits(isbn.replace("-", ""))))
                {
                    if((isbn.length() == 13 && isbn.startsWith("978")))
                    {
                        isbn = isbn.substring(0, 3) + "-" + isbn.substring(3, 13);
                    }
                }
                else
                {
                    System.out.println("Invalid ISBN. ISBN must be in 13 digit format. \nExample 1: 978-1119803782\nExample 2: 9781119803782");
                }
            }
            
            System.out.println("SEARCH RESULTS");
            System.out.println("==============================================================================================================================================================");
            System.out.println("No.");
            for (int i = 0; i < books.size(); i++) 
            {
                if (books.get(i).getIsbn().contains(isbn)) 
                {
                    dispSearchResult(i);
                }
                else if(i == (books.size()) && (searchResultNo == 1))
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
    
    //search result after the respective filters
    public void dispSearchResult(int i)
    {
        found = true;
        System.out.println("==============================================================================================================================================================");
        System.out.println(" " + (searchResultNo));
        System.out.println("==============================================================================================================================================================");
        System.out.println("Book Title: " + books.get(i).getBookTitle());
        System.out.println("Genre: " + books.get(i).getGenre());
        System.out.println("Author: " + books.get(i).getAuthor());
        System.out.println("Publisher: " + books.get(i).getPublisher());
        System.out.println("Year Published: " + books.get(i).getYearPublished());
        System.out.println("==============================================================================================================================================================\n");
        bookPos.add(i);
        searchResultNo++;
    }
}
