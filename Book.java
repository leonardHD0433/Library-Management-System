public class Book 
{
    private String bookTitle, bookIsbn, author, publisher,  bookBorrowedDate, bookReturnDate;
    private double bookPrice;
    private boolean bookAvailability;

    //default constructor
    public Book() 
    {
        this(null, null, null, null, null, null, 0.00, true);
    }

    //parameterized constructor
    public Book(String bookTitle, String bookIsbn, String author, String publisher, String bookBorrowedDate, String bookReturnDate, double bookPrice, boolean bookAvailability)
    {
        setBookDetail(bookTitle, bookIsbn, author, publisher, bookBorrowedDate, bookReturnDate, bookPrice, bookAvailability);
    }

    public void setBookDetail(String bookTitle, String bookIsbn, String author, String publisher, String bookBorrowedDate, String bookReturnDate, double bookPrice, boolean bookAvailability)
    {
        this.bookTitle = bookTitle;
        this.bookIsbn = bookIsbn;
        this.author = author;
        this.publisher = publisher;
        this.bookPrice = bookPrice;
        this.bookAvailability = bookAvailability;
        this.bookBorrowedDate = bookBorrowedDate;
        this.bookReturnDate = bookReturnDate;
    }

    public void setBookPrice(double bookPrice)
    {
        this.bookPrice = bookPrice;
    }


}
