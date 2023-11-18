import java.io.*;
import java.util.concurrent.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class Loan 
{
    DateTimeFormatter dtf;
    ArrayList <Book> borrowedBooks = new ArrayList<Book>();
    Patron p;
    private String borrowDate, returnDate;
    private double fine;

    public Loan(String PatronName, String PatronID, String PatronContactNumber)
    {
        dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
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
            UtilitiesForSystem.clearScreen();
            System.out.println(borrowedBooks.get(bI) + "\n");
            System.out.print("Borrowed by: " + p.getPatronName() + " (" + p.getPatronID() + ")\n\n");
            setBorrowedDate(bI);
            setReturnDate(bI);
            flag = !(borrowedBooks.get(bI).getBorrowD().isBefore(borrowedBooks.get(bI).getReturnD()) && (Duration.between(borrowedBooks.get(bI).getBorrowD().atStartOfDay(ZoneId.systemDefault()).toInstant(), borrowedBooks.get(bI).getReturnD().atStartOfDay(ZoneId.systemDefault()).toInstant()).toDays() <= 7));
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
                System.out.println(borrowedBooks.get(i) + "\n");
                System.out.print("Borrowed by: " + p.getPatronName() + " (" + p.getPatronID() + ")\n\n");
                flag = true;
            }
        }while(flag);
    }

    public void setReturnDate(int i) throws IOException, InterruptedException
    {
        boolean flag;
        do
        {
            System.out.println("Return Date: (dd/mm/yyyy)");
            returnDate = UtilitiesForSystem.reader.readLine();
            try 
            {
                borrowedBooks.get(i).setReturnD(LocalDate.parse(returnDate, dtf));
                flag = false;
            } 
            catch (DateTimeParseException e) 
            {
                System.out.println("Invalid date. Try again");
                TimeUnit.MILLISECONDS.sleep(500);
                UtilitiesForSystem.clearScreen();
                System.out.println(borrowedBooks.get(i) + "\n");
                System.out.print("Borrowed by: " + p.getPatronName() + " (" + p.getPatronID() + ")\n\n");
                System.out.println("Borrowed Date: " + getBorrowedDate(i).format(dtf));
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

    public double calculateFine(int daysOverdue)
    {
        fine = (double)daysOverdue * 1.00;
        return fine;
    }







    public void displayLoanDetails(int i) 
    {
        System.out.println("Date Borrowed:" + getBorrowedDate(i).format(dtf));
        System.out.println("Date Returned:" + getReturnDate(i).format(dtf));
        System.out.println("Book Title:" + borrowedBooks.get(i).getBookTitle());
        System.out.println("Patron: " + p.getPatronName() + " (" + p.getPatronID() + ")");
    }

    public void displayFineReceipt(String transactionNo, String date, int borrowedBooksIndex)
    {
        System.out.println("---------------------------------------");
        System.out.println("Date: " + date);
        System.out.println("---------------------------------------");
        System.out.println("Transaction No: " + transactionNo);
        System.out.println("Patron: " + p.getPatronName() + " (" + p.getPatronID() + ")");
        System.out.println("Book Title: " + borrowedBooks.get(borrowedBooksIndex).getBookTitle());
        System.out.println("---------------------------------------");
        System.out.println("Fine: RM" + fine);
        System.out.println("---------------------------------------");
    }

    public void dispPatronDetails()
    {
        System.out.println(p);
    }
}
