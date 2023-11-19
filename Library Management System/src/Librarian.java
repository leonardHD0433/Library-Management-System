import java.io.IOException;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Librarian extends User 
{
    Loan[] loanList = new Loan[5];


    public Librarian(String userType, String userName, String userId, String password) throws IOException//Composition
    {
        super(userType, userName, userId, password);
        setLoanList();
    }

    //To view patron details - [Liew Zhen Nam] //add remove book need to be done to view book borrowed
    public void viewPatron() throws IOException, InterruptedException
    {
        String choosePatron, choice;
        int patronIndex; 
        do
        {
            UtilitiesForSystem.clearScreen();
            catalog.dispViewPatron();
            System.out.println("Choose Patron: ");

            choosePatron = UtilitiesForSystem.reader.readLine();
            if(!UtilitiesForSystem.allCharacterAreDigits(choosePatron))
            {
                UtilitiesForSystem.selectionErrorMsg();
            }
        }while(!UtilitiesForSystem.allCharacterAreDigits(choosePatron));

        patronIndex = Integer.parseInt(choosePatron)-1;
        
        if(loanList[patronIndex].borrowedBooks.size() == 0)
        {
            UtilitiesForSystem.clearScreen();
            do
            {
                System.out.println(loanList[patronIndex].p);
                System.out.println("Status: Not borrowing");
                System.out.println("\n\n1. Back");
                System.out.println("\nSelection: ");
                choice = UtilitiesForSystem.reader.readLine();
                switch (choice) 
                {
                    case "1": break;
                
                    default: UtilitiesForSystem.selectionErrorMsg();
                }
            }while(!(choice.equals("1")));
        }
        else
        {
            UtilitiesForSystem.clearScreen();
            do
            {
                System.out.println(loanList[patronIndex].p);
                System.out.print("Status: Borrowing");
                System.out.println("\n\n1. View Borrowed Books: ");
                System.out.println("2. Back");
                System.out.println("\nSelection: ");
                choice = UtilitiesForSystem.reader.readLine();
                switch (choice) 
                {
                    case "1": viewBorrowedBooks(patronIndex); break;
                
                    case "2": break;

                    default: UtilitiesForSystem.selectionErrorMsg();
                }
            }while(!(choice.equals("1") || choice.equals("2")));
        }
    }

    //To view borrowed books - [Liew Zhen Nam]
    public void viewBorrowedBooks(int patronIndex) throws IOException, InterruptedException
    {
        String choice;
        do
        {
            UtilitiesForSystem.clearScreen();
            System.out.println("-----------------------------------------------------------------------------------------------------");
            System.out.println("Patron: " + loanList[patronIndex].p.getPatronName() + " (" + loanList[patronIndex].p.getPatronID() + ")");
            
            for (int i = 0; i < loanList[patronIndex].borrowedBooks.size(); i++) 
            {
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println(loanList[patronIndex].borrowedBooks.get(i));  
            }
            System.out.println("-----------------------------------------------------------------------------------------------------");
            System.out.println("\n\n1. Back");
            System.out.println("\nSelection: ");
            choice = UtilitiesForSystem.reader.readLine();
            switch (choice) 
            {
                case "1": return;
            
                default: UtilitiesForSystem.selectionErrorMsg();
            }
        }while(!(choice.equals("1")));
    }

    //Set the patron to their respective loans - [Yu Kang]
    public void setLoanList() 
    {
        for (int i = 0; i < loanList.length; i++) {
            loanList[i] = new Loan(catalog.getPatronListName(i), catalog.getPatronListID(i), catalog.getPatronContactNumber(i));
        }
    }

    //Borrow and Return Book Methods Should be Here, 
    //these methods will be used to call the catalogs' methods that manipulate data or display 

    
    public void whatToDoWithBook(int bookIndex) throws IOException, InterruptedException
    {
        String borrow_or_back;
        do
        {
            dispOption_inBookPage(bookIndex);

            borrow_or_back = UtilitiesForSystem.reader.readLine().toLowerCase();
            switch (borrow_or_back) 
            {
                case "1": catalog.setBackTo_ChooseBook(false); break;
            
                case "2": catalog.setBackTo_ChooseBook(true); break;

                default: 
                    UtilitiesForSystem.selectionErrorMsg(); 
                    System.out.println("Book chosen:\n\n" + catalog.getBookList(catalog.getChosenBookIndex()));
            }
        }while(!(borrow_or_back.equals("1") || borrow_or_back.equals("2")));

        if(catalog.getBackTo_ChooseBook() == true)
        {
            return;
        }
        else
        {
            if(catalog.getBookListAvailability(bookIndex).equals("Available"))
            {
                borrowBook(bookIndex);
            }
            else
            {
                returnBook(bookIndex);
            }
        }
    }

    // Method to borrow a book -[Yu Kang] Guide: Edwin
    public void borrowBook(int bookIndex) throws IOException, InterruptedException
    {
        String choosePatron;
        int patronIndex, borrowedBooks_index;
        do
        {
            UtilitiesForSystem.clearScreen();
            catalog.dispPatronList();
            choosePatron = UtilitiesForSystem.reader.readLine();
            if(!UtilitiesForSystem.allCharacterAreDigits(choosePatron))
            {
                UtilitiesForSystem.selectionErrorMsg();
            }
        }while (!UtilitiesForSystem.allCharacterAreDigits(choosePatron));

        patronIndex = Integer.parseInt(choosePatron)-1;

        if(loanList[patronIndex].borrowedBooks.size() == 0)
        {
            borrowedBooks_index = 0;
        }
        else
        {
            borrowedBooks_index = 1;
        }

        //add book record to loan
        loanList[patronIndex].addBook(catalog.getBookListTitle(bookIndex), catalog.getBookListIsbn(bookIndex), 
        catalog.getBookListAuthor(bookIndex), catalog.getBookListPublisher(bookIndex), catalog.getBookListYearPublished(bookIndex), 
        catalog.getBookGenre(bookIndex), catalog.getBookListAvailability(bookIndex));

        //set borrow & return date
        loanList[patronIndex].setDate(borrowedBooks_index);

        //display loan details
        UtilitiesForSystem.clearScreen();
        System.out.println("Transaction Number: " + patronIndex + "." + borrowedBooks_index);
        loanList[patronIndex].displayLoanDetails(borrowedBooks_index);
        
        //confirmation
        if(confirmBorrow())
        {
            System.out.println("Book Borrowed.");
            TimeUnit.MILLISECONDS.sleep(500);
            UtilitiesForSystem.clearScreen();
            catalog.setBookListAvailability(bookIndex, false); //change to "borrowed"
            //make it borrowed for the headLibrarian side

        }
        else
        {
            System.out.println("Book not borrowed.");
            TimeUnit.MILLISECONDS.sleep(500);
            UtilitiesForSystem.clearScreen();
            loanList[patronIndex].borrowedBooks.remove(borrowedBooks_index);
        }
    }

    // Method to return a book - [Edwin]
    public void returnBook(int bookIndex) throws IOException, InterruptedException
    {
        LocalDate actualReturnDate = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
        String confirm, pay, transactionNo;
        int loanIndex = -1, borrowedBooksIndex = -1, daysOverdue;
        boolean flag;

        outerLoop: //label
        for (int i = 0; i < loanList.length; i++) //find in the loan list for where the borrowed book is
        {
            for (int j = 0; j < loanList[i].borrowedBooks.size(); j++) 
            {
                if(loanList[i].borrowedBooks.get(j).getBookTitle().equals(catalog.getBookListTitle(bookIndex)))
                {
                    loanIndex = i;
                    borrowedBooksIndex = j;
                    break outerLoop; //break both loops
                }
            }
        }

        transactionNo = loanIndex + "." + borrowedBooksIndex;

        do
        {
            System.out.println("-----------------------------------------------------------------------------------------------------");
            System.out.println("Transaction Number: " + transactionNo);
            loanList[loanIndex].displayLoanDetails(borrowedBooksIndex);
            System.out.println("-----------------------------------------------------------------------------------------------------");

            //actual return date
            System.out.println("Actual Return Date: (dd/mm/yyyy)");
            try 
            {
                actualReturnDate = LocalDate.parse(UtilitiesForSystem.reader.readLine(), dtf);
                if(actualReturnDate.atStartOfDay(ZoneId.systemDefault()).toInstant().isBefore(loanList[loanIndex].getBorrowedDate(borrowedBooksIndex).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                {
                    System.out.println("Actual return date cannot be before borrow date.");
                    TimeUnit.MILLISECONDS.sleep(500);
                    UtilitiesForSystem.clearScreen();
                    flag = true;
                }
                else
                {
                    flag = false;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Try again");
                TimeUnit.MILLISECONDS.sleep(500);
                UtilitiesForSystem.clearScreen();
                flag = true;
            }
        }while(flag);

        System.out.println("Confirm Return Date? (Y/N)");
        confirm = UtilitiesForSystem.reader.readLine().toLowerCase();
        switch (confirm) 
        {
            case "y": break;
        
            case "n": return;

            default: UtilitiesForSystem.selectionErrorMsg(); return;
        }        

        catalog.setBookListAvailability(bookIndex, true);

        //calculate if actual return date is before or after
        if(actualReturnDate.atStartOfDay(ZoneId.systemDefault()).toInstant().isBefore(loanList[loanIndex].getReturnDate(borrowedBooksIndex).atStartOfDay(ZoneId.systemDefault()).toInstant()) ||
           actualReturnDate.atStartOfDay(ZoneId.systemDefault()).toInstant().equals(loanList[loanIndex].getReturnDate(borrowedBooksIndex).atStartOfDay(ZoneId.systemDefault()).toInstant()))
        {
            System.out.println("Book Returned Successfully");
        }
        else if(actualReturnDate.isAfter(loanList[loanIndex].getReturnDate(borrowedBooksIndex)))
        {
            daysOverdue = (int)Duration.between(loanList[loanIndex].getReturnDate(borrowedBooksIndex).atStartOfDay(ZoneId.systemDefault()).toInstant(), actualReturnDate.atStartOfDay(ZoneId.systemDefault()).toInstant()).toDays();
            System.out.println("Book Returned Successfully");
            System.out.println("Overdue by: " + daysOverdue + " days");
            System.out.println("Calculating Fine.........");
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.print("Fine to be paid: RM ");
            System.out.printf("%.2f", loanList[loanIndex].calculateFine(daysOverdue));
            System.out.println();
            System.out.println("1. Pay");
            pay = UtilitiesForSystem.reader.readLine();
            if(pay.equals("1"))
            {
                System.out.println("Payment Successful");
                loanList[loanIndex].displayFineReceipt(transactionNo, actualReturnDate.format(dtf), borrowedBooksIndex);
                UtilitiesForSystem.pressEnterToContinue();
                TimeUnit.MILLISECONDS.sleep(500);
                UtilitiesForSystem.clearScreen();
                loanList[loanIndex].borrowedBooks.remove(borrowedBooksIndex);
            }
            else
            {
                System.out.println("Select the above options.");
                TimeUnit.MILLISECONDS.sleep(500);
                UtilitiesForSystem.clearScreen();
            }
        }

    }

    // Method to confirm when a book is borrowed - [Yu Kang]
    public boolean confirmBorrow() throws IOException, InterruptedException
    {
        String confirm;
        boolean flag = true;
        do
        {
            System.out.println("Confirm borrow book? (Y/N)");
            confirm = UtilitiesForSystem.reader.readLine().toLowerCase();
            switch (confirm) 
            {
                case "y": flag = true; break;
            
                case "n": flag = false; break;

                default: UtilitiesForSystem.selectionErrorMsg();
            }
        }while(!(confirm.equals("y") || confirm.equals("n")));

        return flag;
    }
}
