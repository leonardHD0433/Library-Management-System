import java.util.*;
public class Book
{
    private String bookTitle, bookIsbn, author, publisher, genre, bookAvailability;
    private int yearPublished;

    //parameterized constructor
    public Book(String bookTitle, String bookIsbn, String author, String publisher, int yearPublished, String genre, String bookAvailability)
    {
        setDefaultDetail(bookTitle, bookIsbn, author, publisher, yearPublished, genre, bookAvailability);
    }

    public void setDefaultDetail(String bookTitle, String bookIsbn, String author, String publisher, int yearPublished, String genre, String bookAvailability)
    {
        this.bookTitle = bookTitle;
        this.bookIsbn = bookIsbn;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.yearPublished = yearPublished;
        this.bookAvailability = bookAvailability;
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

    public int getYearPublished()
    {
        return yearPublished;
    }

    public String toString()
    {
        return "Book Title: " + bookTitle + "\nISBN: " + bookIsbn + "\nGenre: " + genre + "\nAuthor: " + author + "\nPublisher: " + publisher + "\nYear Published: " + yearPublished + "\nAvailability: " + bookAvailability;
    }
}
