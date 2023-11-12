import java.text.SimpleDateFormat;
import java.util.Date;

public class Loan 
{
    SimpleDateFormat dateFormat;
    Date d;
    Book[] borrowedBooks = new Book[2];
    Patron p;
    private String borrowDate, returnDate;
    private String[] borrowDateList = new String[2];
    private String[] returnDateList = new String[2];

    public Loan(String PatronName, String PatronID, String PatronContactNumber)
    {
        p = new Patron(PatronName, PatronID, PatronContactNumber); 
        d = new Date();
        dateFormat = new SimpleDateFormat("dd/mm/yyyy");
    }

    public void addBook(int i, String bookTitle, String bookIsbn, String author, String publisher, int yearPublished, String genre, String bookAvailability)
    {
        borrowedBooks[i] = new Book(bookTitle, bookIsbn, author, publisher, yearPublished, genre, bookAvailability);
    }

    //method to get borrow date
    public void setBorrowedDate()
    {
        borrowDate = dateFormat.format(d);
    }

    public void setReturnDate()
    {
        returnDate = dateFormat.format(d);
    }
}
