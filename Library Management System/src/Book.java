import java.time.*;
import java.util.*;
public class Book
{
    private LocalDate borrowDate, returnDate;
    private String bookTitle, bookIsbn, author, publisher, genre, bookAvailability;
    private int yearPublished;

    //parameterized constructor
    public Book(String bookTitle, String bookIsbn, String author, String publisher, int yearPublished, String genre, String bookAvailability)
    {
        setDefaultDetail(bookTitle, bookIsbn, author, publisher, yearPublished, genre, bookAvailability);
    }

    //USE THIS to add book
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
    public void setBookAvailability(boolean availability)
    {
        if(availability = true)
        {
            bookAvailability = "Available";
        }
        else
        {
            bookAvailability = "Borrowed";
        }
    }

    public void setBorrowD(LocalDate d)
    {
        borrowDate = d;
    }

    public void setReturnD(LocalDate d)
    {
        returnDate = d;
    }

    public LocalDate getBorrowD()
    {
        return borrowDate;
    }

    public LocalDate getReturnD()
    {
        return returnDate;
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

    public String getAvailability()
    {
        return bookAvailability;
    }

    public String toString()
    {
        return "Book Title: " + bookTitle + "\nISBN: " + bookIsbn + "\nGenre: " + genre + "\nAuthor: " + author + "\nPublisher: " + publisher + "\nYear Published: " + yearPublished + "\nAvailability: " + bookAvailability;
    }
}
