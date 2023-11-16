import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Librarian extends User 
{
    Loan[] loanList = new Loan[5];


    public Librarian(String userType, String userName, String userId, String password) //Composition
    {
        super(userType, userName, userId, password);
        setLoanList();
    }

    //To view patron details - [Liew Zhen Nam] //add remove book need to be done to view book borrowed
    public void viewPatron() throws IOException, InterruptedException
    {
        String choosePatron;
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
        System.out.println(loanList[patronIndex].p);
        wait();
        System.out.println("Borrowed Book: ");
        // for (int i=0; i<=1;i++)
        // {
        //   System.out.println(p.borrowedBooks[i]);
        // }
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

    // Method to return a book
    public void returnBook(int bookIndex)
    {

    }

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
