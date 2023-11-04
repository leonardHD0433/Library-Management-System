import java.util.*;

public class Book
{
    private final String[] BOOK_TITLE = {"Systems Analysis and Design, 8th Edition", "Java Software Solutions, Foundations of Program Design, 9th edition", "Entrepreneurship, 11th Edition", 
                                       "ISE Foundations of Financial Management, 18th Edition", 
                                       "Introduction to Game Design, Prototyping, and Development: From Concept to Playable Game with Unity and C#, 2nd edition",
                                       "The Game Production Toolbox, 1st Edition", "Theories of Human Communication, Eleventh Edition",
                                       "Video Games, Violence, and the Ethics of Fantasy: Killing Time", "The Wager: A Tale of Shipwreck, Mutiny and Murder", 
                                       "The Nazi Conspiracy: The Secret Plot to Kill Roosevelt, Stalin, and Churchill"
                                      };

    private final String[] ISBN = {"978-1119803782", "978-0134462028", "978-1260043730", "978-1265074654", "978-0134659862", "978-1138341708", "978-1478634058", 
                                  "978-1350121874", "978-0385534260", "978-1250777263"};

    private final String[] GENRE = {"IT & Software", "IT & Software", "Finance", "Finance", "Game Development", "Game Development", "Philosophy", "Philosophy", "History", "History"};

    private final String[] AUTHOR = {"Alan Dennis, Barbara Wixom, Roberta M. Roth", "John Lewis, William Loftus",
                                    "Robert Hisrich, Michael Peters, Dean Shepherd", "Stanley B. Block, Geoffrey A. Hirt, Bartley Danielsen", "Jeremy Gibson Bond",
                                    "Heather Chandler", "Stephen W. Littlejohn, Karen A. Foss, John G. Oetzel", "Christopher Bartel", "David Grann", "Brad Meltzer, Josh Mensch"};

    private final String[] PUBLISHER = {"Wiley", "Pearson", "McGraw Hill", "McGraw Hill", "Addison-Wesley Professional", "CRC Press", "Waveland Press, Inc.", 
                                       "Bloomsbury Academic", "Doubleday", "Flatiron Books"};

    private final int[] YEAR_PUBLISHED = {2021, 2017, 2019, 2022, 2017, 2020, 2016, 2020, 2023, 2023};

    private String bookTitle, bookIsbn, author, publisher, genre, bookAvailability, strBorrowedDate, strReturnDate;
    private Date bookBorrowedDate = new Date(), bookReturnDate = new Date();
    private int yearPublished;

    //parameterized constructor
    public Book(int i)
    {
        setDefaultDetail(i, null, null, "Available");
    }

    public void setDefaultDetail(int i, String strB_Date, String strR_Date, String bookAvailability)
    {
        bookTitle = BOOK_TITLE[i];
        bookIsbn = ISBN[i];
        this.author = AUTHOR[i];
        this.publisher = PUBLISHER[i];
        this.yearPublished = YEAR_PUBLISHED[i];
        this.genre = GENRE[i]; 
        this.bookAvailability = bookAvailability;
        strBorrowedDate = strB_Date;
        strReturnDate = strR_Date;
    }

    //edit the availability of the book
    public void setBookAvailability(String selection)
    {
        switch (selection) {
            case "1":
                bookAvailability = "Available";
                break;

            case "2":
                bookAvailability = "Borrowed";
                break;

            case "3":
                bookAvailability = "Archived";
                break;
        
            default:
                System.out.println("Invalid Selection. Try again.");
                break;
        }
    }

    //get book details
    public String getBookTitle()
    {
        return bookTitle;
    }

    public String getIsbn()
    {
        return bookIsbn;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public String getGenre()
    {
        return genre;
    }

    public String toString()
    {
        return "Book Title: " + bookTitle + "\nISBN: " + bookIsbn + "\nGenre: " + genre + "\nAuthor: " + author + "\nPublisher: " + publisher + "\nYear Published: " + yearPublished + "\nAvailability: " + bookAvailability;
    }
}
