import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Librarian extends User 
{
    Loan[] loanList = new Loan[5];


    public Librarian(String userType, String userName, String userId, String password) //Composition
    {
        super(userType, userName, userId, password);
        
    }

    //To view patron details
    public void viewPatron()
    {
        
    }

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
            
                case "back": catalog.setBackTo_ChooseBook(true); break;

                default: UtilitiesForSystem.selectionErrorMsg();
            }
        }while(!(borrow_or_back.equals("1") || borrow_or_back.equals("back")));

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

    // Method to borrow a book
    public void borrowBook(int bookIndex) throws IOException, InterruptedException
    {
        String choosePatron;
        int patronIndex;
        do
        {
            catalog.dispPatronList();
            choosePatron = UtilitiesForSystem.reader.readLine();
            if(!UtilitiesForSystem.allCharacterAreDigits(choosePatron))
            {
                UtilitiesForSystem.selectionErrorMsg();
            }
        }while (!UtilitiesForSystem.allCharacterAreDigits(choosePatron));
        patronIndex = Integer.parseInt(choosePatron)-1;
        loanList[patronIndex].addBook(patronIndex, catalog.getBookListTitle(bookIndex), catalog.getBookListIsbn(bookIndex), 
        catalog.getBookListAuthor(bookIndex), catalog.getBookListPublisher(bookIndex), catalog.getBookListYearPublished(bookIndex), 
        catalog.getBookGenre(bookIndex), catalog.getBookListAvailability(bookIndex));
    }

    // Method to return a book
    public void returnBook(int bookIndex)
    {

    }
}
