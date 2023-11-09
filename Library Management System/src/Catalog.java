import java.io.*;
import java.util.*;

public class Catalog implements BookData, PatronData
{
    private ArrayList<Book> bookList;
    private Patron[] patronList = new Patron[5];
    ArrayList <String> genreTypes_inCatalog;
    private ArrayList <Integer> bookPos_forFilteredList;
    private boolean found;
    private int searchResultNo; //number of books found after filter
    
    public Catalog()
    {
        bookList = new ArrayList<Book>();
        bookPos_forFilteredList = new ArrayList<Integer>();
        genreTypes_inCatalog = new ArrayList<String>();
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

    //sort genre list in ascending order
    public void sortGenreTypes_inCatalog()
    {
        Collections.sort(genreTypes_inCatalog, 
        new Comparator <String>() 
        {
            public int compare(String genre1, String genre2) 
            {
                return genre1.compareTo(genre2);
            }
        }
        );
    }

    //add new genres to genreTypes_inCatalog 
    public void addToGenreTypes_inCatalogSize(int i)
    {
        genreTypes_inCatalog.add(getBookGenre(i));
    }

    public boolean checkCatalogForGenre(int i)
    {
        return genreTypes_inCatalog.contains(getBookGenre(i));
    }

    //get genreTypes_inCatalog size
    public int getGenreTypes_inCatalogSize()
    {
        return genreTypes_inCatalog.size();
    }

    //get genreTypes_inCatalog element
    public String getGenreTypes_inCatalog(int i)
    {
        return genreTypes_inCatalog.get(i);
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
