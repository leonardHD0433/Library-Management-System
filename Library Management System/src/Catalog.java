import java.io.*;
import java.util.*;


public class Catalog implements BookData, PatronData
{
    private ArrayList<Book> bookList;
    private ArrayList<Book> archiveList; // Uses pointer to remove book from a certain index. (.remove function)
    private Patron[] patronList = new Patron[5];
    private ArrayList <String> genreTypes_inCatalog;
    private ArrayList <String> bookTitleFromFile;
    private ArrayList <String> bookIsbnFromFile;
    private ArrayList <String> authorFromFile;
    private ArrayList <String> publisherFromFile;
    private ArrayList <Integer> yearPublishedFromFile;
    private ArrayList <String> genreFromFile;
    private ArrayList <String> bookAvailabilityFromFile;
    private ArrayList <Integer> bookPos_forFilteredList; //
    private boolean found, reject_chooseBook, isBookIndexInteger, backTo_ChooseBook;
    private int searchResultNo, chosenBookIndex; //number of books found after filter
    
    public Catalog() throws IOException
    {
        bookList = new ArrayList<Book>();
        bookPos_forFilteredList = new ArrayList<Integer>();
        genreTypes_inCatalog = new ArrayList<String>();
        archiveList = new ArrayList<Book>();
        for (int i = 0; i < patronList.length; i++)
        {
            setPatronList(i);
        }

        if(!UtilitiesForSystem.checkIfFilesExist())
        {
            for (int i = 0; i < BOOK_TITLE.length; i++)
            {
                setBookList(i);
            }
            
        }
        else
        {
            UtilitiesForSystem.setReader();
            setBookFromFile();
            for (int i = 0; i < bookTitleFromFile.size(); i++) 
            {
                setBookList(bookTitleFromFile.get(i), bookIsbnFromFile.get(i), authorFromFile.get(i), publisherFromFile.get(i), yearPublishedFromFile.get(i), genreFromFile.get(i), bookAvailabilityFromFile.get(i));  
            } 
        }
        
        sortBookList();
    }

    public void setBookList(int i)
    {
        bookList.add(new Book(BOOK_TITLE[i], BOOK_TITLE[i], AUTHOR[i], PUBLISHER[i], YEAR_PUBLISHED[i], GENRE[i], BOOK_AVAILABILITY[0]));
    }

    public void setBookList(String bookTitle, String bookIsbn, String author, String publisher, int yearPublished, String genre, String bookAvailability)
    {
        bookList.add(new Book(bookTitle, bookIsbn, author, publisher, yearPublished, genre, bookAvailability));
    }

    public void setBookFromFile() throws IOException
    {
        loadBookTitle();
        loadBookIsbn();
        loadBookAuthor();
        loadBookPublisher();
        loadBookYearPublished();
        loadBookGenre();
        loadBookAvailability();
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
        bookList.add(new Book(bookTitle, bookIsbn, author, publisher, yearPublished, genre, bookAvailability));
    }

    //Method to directly edit the title of a book - [SHAUN]
    public void setBookListTitle(int bookListIndex, String newBookTitle)
    {
        bookList.get(bookListIndex).changeBookTitle(newBookTitle);
    }

    //Method to directly edit the author of a book - [SHAUN]
    public void setBookListAuthor(int bookListIndex, String newBookAuthor)
    {
        bookList.get(bookListIndex).changeBookAuthor(newBookAuthor);
    }

    //Method to directly edit the publisher of a book - [SHAUN]
    public void setBookListPublisher(int bookListIndex, String newBookPublisher)
    {
        bookList.get(bookListIndex).changeBookPublisher(newBookPublisher);
    }

    //Method to directly edit the ISBN of a book - [SHAUN]
    public void setBookListIsbn(int bookListIndex, String newBookIsbn)
    {
        bookList.get(bookListIndex).changeBookIsbn(newBookIsbn);
    }

    //Method to directly edit the genre of a book - [SHAUN]
    public void setBookListGenre(int bookListIndex, String newBookGenre)
    {
        bookList.get(bookListIndex).changeBookGenre(newBookGenre);
    }

    //Method to directly edit the publishing year of a book - [SHAUN]
    public void setBookListYearPublished(int bookListIndex, int newBookYearPublished)
    {
        bookList.get(bookListIndex).changeBookYearPublished(newBookYearPublished);
    }

    public void addToArchiveList(int bookIndex)
    {
        archiveList.add(bookList.get(bookIndex));
    }

    public void removeFromArchiveList(int bookIndex)
    {
        archiveList.remove(bookIndex);
    }

    public void addFromArchiveToBookList(int bookIndex)
    {
        bookList.add(archiveList.get(bookIndex));
    }

    public void removeFromBookList(int bookIndex)
    {
        bookList.remove(bookIndex);
    }






    public void loadBookTitle() throws IOException
    {
        bookTitleFromFile = new ArrayList<String>();

        while(UtilitiesForSystem.readFile[0].hasNextLine())
        {
            bookTitleFromFile.add(UtilitiesForSystem.readFile[0].nextLine());
        }
    }

    public void loadBookIsbn() throws IOException
    {
        bookIsbnFromFile = new ArrayList<String>();

        while(UtilitiesForSystem.readFile[1].hasNextLine())
        {
            bookIsbnFromFile.add(UtilitiesForSystem.readFile[1].nextLine());
        }
    }

    public void loadBookAuthor() throws IOException
    {
        authorFromFile = new ArrayList<String>();

        while(UtilitiesForSystem.readFile[2].hasNextLine())
        {
            authorFromFile.add(UtilitiesForSystem.readFile[2].nextLine());
        }
    }

    public void loadBookPublisher() throws IOException
    {
        publisherFromFile = new ArrayList<String>();

        while(UtilitiesForSystem.readFile[3].hasNextLine())
        {
            publisherFromFile.add(UtilitiesForSystem.readFile[3].nextLine());
        }
    }

    public void loadBookYearPublished() throws IOException
    {
        yearPublishedFromFile = new ArrayList<Integer>();

        while(UtilitiesForSystem.readFile[4].hasNextLine())
        {
            yearPublishedFromFile.add(Integer.parseInt(UtilitiesForSystem.readFile[4].nextLine()));
        }
    }

    public void loadBookGenre() throws IOException
    {
        genreFromFile = new ArrayList<String>();

        while(UtilitiesForSystem.readFile[5].hasNextLine())
        {
            genreFromFile.add(UtilitiesForSystem.readFile[5].nextLine());
        }
    }

    public void loadBookAvailability() throws IOException
    {
        bookAvailabilityFromFile = new ArrayList<String>();

        while(UtilitiesForSystem.readFile[6].hasNextLine())
        {
            bookAvailabilityFromFile.add(UtilitiesForSystem.readFile[6].nextLine());
        }
    }

    public void dispArchiveList()
    {
        System.out.println("ARCHIVE LIST");
        System.out.println("==============================================================================================================================================================");
        System.out.println("NO. ");
        for (int j = 0; j < archiveList.size(); j++) {
            System.out.println("==============================================================================================================================================================");
            System.out.println(" " + (j + 1));
            System.out.println("==============================================================================================================================================================");
            System.out.println(archiveList.get(j));
            System.out.println("==============================================================================================================================================================\n");
        }
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




