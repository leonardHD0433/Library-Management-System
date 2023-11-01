public class Book implements Data
{
    private String bookTitle, bookIsbn, author, publisher, genre, bookBorrowedDate, bookReturnDate, bookAvailability;
    private int yearPublished;

    //parameterized constructor
    public Book(int i, String bookBorrowedDate, String bookReturnDate, boolean bookAvailability)
    {
        setDefaultBookDetail(i , bookBorrowedDate, bookReturnDate, bookAvailability);
    }

    //pass book details
    public void setDefaultBookDetail(int i, String bookBorrowedDate, String bookReturnDate, boolean bookAvailability)
    {
        this.bookTitle = BookTitle[i];
        this.bookIsbn = Isbn[i];
        this.author = Author[i];
        this.publisher = Publisher[i];
        this.yearPublished = YearPublished[i];
        this.genre = Genre[i];
        this.bookAvailability = "Available";
        this.bookBorrowedDate = bookBorrowedDate;
        this.bookReturnDate = bookReturnDate;
    }

    //edit the availability of the book
    public void setBookAvailability(boolean bookAvailability)
    {
        if(bookAvailability == true)
        {
            this.bookAvailability = "Available";
        }
        else
        {
            this.bookAvailability = "Borrowed";
        }
    }
}
