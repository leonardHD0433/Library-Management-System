import java.io.*;
import java.util.*;


public class Catalog implements BookData, PatronData
{
    private ArrayList<Book> bookList;
    private Patron[] patronList = new Patron[5];
    ArrayList <String> genreTypes_inCatalog;
    private ArrayList <Integer> bookPos_forFilteredList;
    private boolean found, reject_chooseBook, isBookIndexInteger, backTo_ChooseBook;
    private int searchResultNo, chosenBookIndex; //number of books found after filter
    
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

    public String getPatronListName(int i)
    {
        return patronList[i].getPatronName();
    }
    
    public String getPatronListID(int i)
    {
        return patronList[i].getPatronID();
    }

    public String getPatronContactNumber(int i)
    {
        return patronList[i].getContactNumber();
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
        return !genreTypes_inCatalog.contains(getBookGenre(i));
    }

    //get genreTypes_inCatalog size
    public int getGenreTypes_inCatalogSize()
    {
        return genreTypes_inCatalog.size();
    }

    public void clearGenreTypes_inCatalog()
    {
        genreTypes_inCatalog.clear();
    }

    //get genreTypes_inCatalog element
    public String getGenreTypes_inCatalog(int i)
    {
        return genreTypes_inCatalog.get(i);
    }

    public void setChosenBookIndex(int chosenBookIndex)
    {
        this.chosenBookIndex = chosenBookIndex;
    }

    public int getChosenBookIndex()
    {
        return chosenBookIndex;
    }

    public void setRejectChooseBook(String str)
    {
        if(str.equals("back"))
        {
            reject_chooseBook = true;
        }
        else
        {
            reject_chooseBook = false;
        }
    }

    public boolean getRejectChooseBook()
    {
        return reject_chooseBook;
    }

    public void setBookIndexInteger(boolean flag)
    {
        isBookIndexInteger = flag;
    }

    public boolean isBookIndexInteger()
    {
        return isBookIndexInteger;
    }
    
    public void setBackTo_ChooseBook(boolean flag)
    {
        backTo_ChooseBook = flag;
    }

    public boolean getBackTo_ChooseBook()
    {
        return backTo_ChooseBook;
    }

    public void setBookListAvailability(int bookListIndex, boolean availability)
    {
        bookList.get(bookListIndex).setBookAvailability(availability);
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

    public int bookPos_forFilteredListSize()
    {
        return bookPos_forFilteredList.size();
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

    public String getBookList(int i)
    {
        return bookList.get(i).toString();
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
        System.out.println(bookList.get(i));
    }

    //Method to get size of book list
    public int getBookListSize()
    {
        return bookList.size();
    }

    //[SHAUN] Method to add book to list
    public void addBookToList(String bookTitle, String bookIsbn, String author, String publisher, int yearPublished, String genre, String bookAvailability)
    {
        bookList.add(new Book(bookTitle,bookIsbn,author,publisher, yearPublished,genre,bookAvailability));
    }

    //search result after the respective filters
    public void dispSearchResult(int i)
    {
        System.out.println("==============================================================================================================================================================");
        System.out.println(" " + (searchResultNo + 1));
        System.out.println("==============================================================================================================================================================");
        getBook(i);
        System.out.println("==============================================================================================================================================================\n");
    }  

    public void dispPatronList()
    {
        dispViewPatron();
        System.out.print("\n\nWhich Patron is borrowing? ");
    }

    public void dispViewPatron()
    {
        System.out.println("PATRON LIST");
        for (int j = 0; j < patronList.length; j++) {
            System.out.println("==============================================================================================================================================================");
            System.out.println("NO. " + (j + 1));
            System.out.println("==============================================================================================================================================================");
            System.out.println(patronList[j]);
            System.out.println("==============================================================================================================================================================\n");
        }
    }
}




