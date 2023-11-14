import java.io.*;
import java.util.concurrent.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class Loan 
{
    ArrayList <Book> borrowedBooks = new ArrayList<Book>();
    Patron p;
    private String borrowDate, returnDate;

    public Loan(String PatronName, String PatronID, String PatronContactNumber)
    {
        p = new Patron(PatronName, PatronID, PatronContactNumber); 
    }

    public void addBook(String bookTitle, String bookIsbn, String author, String publisher, int yearPublished, String genre, String bookAvailability)
    {
        borrowedBooks.add(new Book(bookTitle, bookIsbn, author, publisher, yearPublished, genre, bookAvailability));
    }

    public void setDate(int bI) throws IOException, InterruptedException
    {
        boolean flag;
        do
        {
            System.out.println(borrowedBooks.get(bI) + "\n\n");
            setBorrowedDate(bI);
            setReturnDate(bI);
            flag = !(borrowedBooks.get(bI).getBorrowD().isBefore(borrowedBooks.get(bI).getReturnD()) && (Duration.between(borrowedBooks.get(bI).getReturnD().atStartOfDay(ZoneId.systemDefault()).toInstant(), borrowedBooks.get(bI).getBorrowD().atStartOfDay(ZoneId.systemDefault()).toInstant()).toDays() <= 7));
            //return must be 1 day or one week after borrow date
            if(flag)
            {
                System.out.println("Return date must be minimum of 1 day and maximum of 1 week after borrow date.");
                TimeUnit.MILLISECONDS.sleep(500);
                UtilitiesForSystem.clearScreen();
            }
        }while(flag);
    }

    //method to get borrow date
    public void setBorrowedDate(int i) throws IOException, InterruptedException
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
        boolean flag;
        do
        {
            System.out.println("Borrowed Date: (dd/mm/yyyy)");
            borrowDate = UtilitiesForSystem.reader.readLine();
            try 
            {
                borrowedBooks.get(i).setBorrowD(LocalDate.parse(borrowDate, dtf));
                flag = false;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Try again");
                TimeUnit.MILLISECONDS.sleep(500);
                UtilitiesForSystem.clearScreen();
                flag = true;
            }
        }while(flag);
    }

    public void setReturnDate(int i) throws IOException, InterruptedException
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
        boolean flag;
        do
        {
            System.out.println("Return Date: (dd/mm/yyyy)");
            returnDate = UtilitiesForSystem.reader.readLine();
            try 
            {
                borrowedBooks.get(i).setReturnD(LocalDate.parse(returnDate, dtf));
                flag = false;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Try again");
                TimeUnit.MILLISECONDS.sleep(500);
                UtilitiesForSystem.clearScreen();
                flag = true;
            }
        }while(flag);
    }

    public LocalDate getBorrowedDate(int i)
    {
        return borrowedBooks.get(i).getBorrowD();
    }

    public LocalDate getReturnDate(int i)
    {
        return borrowedBooks.get(i).getReturnD();
    }

    public void displayLoanDetails(int i) 
    {
        System.out.println("Date Borrowed:" + getBorrowedDate(i));
        System.out.println("Date Returned:" + getReturnDate(i));
        System.out.println("Book Title:" + borrowedBooks.get(i).getBookTitle());
        System.out.println("Patron Name: " + p.getPatronName());
    }
    public void dispPatronDetails()
    {
        System.out.println(p);
    }
}
