import java.io.*;
import java.util.*;

public class Catalog implements BookData, PatronData
{
    private ArrayList<Book> bookList;
    private Patron[] patronList = new Patron[5];
    private ArrayList <Integer> bookPos_forFilteredList;
    private boolean found;
    private int searchResultNo; //number of books found after filter
    
    public Catalog()
    {
        bookList = new ArrayList<Book>();
        bookPos = new ArrayList<Integer>();
        for (int i = 0; i < patronList.length; i++)
        {
            setPatronList(i);
        }

        for (int i = 0; i < BOOK_TITLE.length; i++)
        {
            setBookList(i);
        }
        sortBookList();
    }

    public void setBookList(int i)
    {
        bookList.add(new Book(BOOK_TITLE[i], BOOK_TITLE[i], AUTHOR[i], PUBLISHER[i], YEAR_PUBLISHED[i], GENRE[i], BOOK_AVAILABILITY[0]));
    }

    public void setPatronList(int i)
    {
        patronList[i] = new Patron(PATRON_NAME[i], PATRON_ID[i], PATRON_CONTACT_NO[i]);
    }
    
    //sort Catalog by book title in ascending order
    public void sortBookList()
    {
        Collections.sort(bookList, 
        new Comparator <Book>() 
        {
            public int compare(Book book1, Book book2) 
            {
                return book1.getBookTitle().compareTo(book2.getBookTitle());
            }
        }
        );
    }

    public void clearBookPos()
    {
        bookPos_forFilteredList.clear();
    }

    public void setBookPos(int i)
    {
        bookPos_forFilteredList.add(i);
    }

    public int getBookPos(int i)
    {
        return bookPos_forFilteredList.get(i);
    }

    public void resetSearchResultNo()
    {
        searchResultNo = 0;
    }

    public void incrementSearchResultNo()
    {
        searchResultNo++;
    }

    //Method to get the no of Search Results
    public int getSearchResultNo()
    {
        return searchResultNo;
    }

    //Check the catalog whether the book exist
    public boolean isBookFound()
    {
        return found;
    }

    // Book Found
    public void foundBook()
    {
        found = true;
    }

    // Book NOT Found
    public void not_foundBook()
    {
        found = false;
    }

    public String getBookListIsbn(int i)
    {
        return bookList.get(i).getIsbn();
    }

    public String getBookListTitle(int i)
    {
        return bookList.get(i).getBookTitle();
    }

    public String getBookGenre(int i)
    {
        return bookList.get(i).getGenre();
    }

    public String getBookListAuthor(int i)
    {
        return bookList.get(i).getAuthor();
    }

    public String getBookListPublisher(int i)
    {
        return bookList.get(i).getPublisher();
    }

    public int getBookListYearPublished(int i)
    {
        return bookList.get(i).getYearPublished();
    }

    public String getBookListAvailability(int i)
    {
        return bookList.get(i).getAvailability();
    }

    // Method to search for a book by genre
    public void searchByGenre() throws IOException
    {
        String chooseGenre;
        int index = 0;
        searchResultNo = 1;
        boolean is_digit;
        ArrayList <String> genre = new ArrayList<String>();
        for(int i = 0; i < bookList.size(); i++)
        {
            if(!genre.contains(bookList.get(i).getGenre()))
            {
                genre.add(bookList.get(i).getGenre());
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
                for (int i = 0; i < bookList.size(); i++) 
                {
                    if (bookList.get(i).getGenre().equals(genre.get(index))) 
                    {
                        dispSearchResult(i);
                    }
                    else if (i == (bookList.size()) && (searchResultNo == 1))
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

    
    
    
    
    public void getBook(int i)
    {
        System.out.println("Book Title: " + bookList.get(i).getBookTitle());
        System.out.println("Genre: " + bookList.get(i).getGenre());
        System.out.println("Author: " + bookList.get(i).getAuthor());
        System.out.println("Publisher: " + bookList.get(i).getPublisher());
        System.out.println("Year Published: " + bookList.get(i).getYearPublished());
    }

    //Method to get size of book list
    public int getBookListSize()
    {
        return bookList.size();
    }

    //search result after the respective filters
    public void dispSearchResult(int i)
    {
        System.out.println("==============================================================================================================================================================");
        System.out.println(" " + (searchResultNo + 1));
        System.out.println("==============================================================================================================================================================");
        System.out.println("Book Title: " + bookList.get(i).getBookTitle());
        System.out.println("Genre: " + bookList.get(i).getGenre());
        System.out.println("Author: " + bookList.get(i).getAuthor());
        System.out.println("Publisher: " + bookList.get(i).getPublisher());
        System.out.println("Year Published: " + bookList.get(i).getYearPublished());
        System.out.println("==============================================================================================================================================================\n");
    }
}
