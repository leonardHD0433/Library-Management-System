import java.io.*;
import java.util.concurrent.*;

public class HeadLibrarian extends User 
{

    public HeadLibrarian(String userType, String userName, String userId, String password) //Composition
    {
        super(userType, userName, userId, password);
    }

    //provide selections to manage the catalog
    public boolean manageCatalog(String selection) throws IOException, InterruptedException
    {
        boolean backToHomePage = false;
        switch (selection) 
        {
            case "1":
                //add book; 
                break;

            case "2":
                editBook();
                break;

            case "3":
                //remove book 
                break;

            case "4":
                backToHomePage = true;  break;

            default:
                System.out.println("Invalid choice. Please try again.");
                TimeUnit.MILLISECONDS.sleep(500);
                UtilitiesForSystem.clearScreen();
        }
        UtilitiesForSystem.clearScreen();
        return backToHomePage;
    }

    //edit book
    public void editBook() throws IOException, InterruptedException
    {
        viewAllAvailable(); 
        if(catalog.getRejectChooseBook() == false)
        {
            UtilitiesForSystem.clearScreen();
            System.out.println("Book chosen:\n\n" + catalog.getBookList(catalog.getChosenBookIndex()));
            whatToDoWithBook(0); //since the parameter is only useful for the librarian class, you can leave this 0 here.
            
        }      
    }

    public void whatToDoWithBook(int bookIndex) throws IOException, InterruptedException // you can use this abstract method however you like
    {
        dispOption_inBookPage(bookIndex);
        String read = UtilitiesForSystem.reader.readLine(); //Shaun start editing from here
        //choose what you want to edit or anything
    }

    //view all "available" books, modified changes using viewAll() at User Class as template
    public void viewAllAvailable() throws IOException, InterruptedException
    {
        boolean flag;
        catalog.sortBookList();
        do
        {
            catalog.clearBookPos();
            catalog.resetSearchResultNo();
            UtilitiesForSystem.clearScreen();
            System.out.println("CATALOG");
            System.out.println("==============================================================================================================================================================");
            System.out.println("No.");
            for (int i = 0; i < catalog.getBookListSize(); i++) 
            { 
                if(catalog.getBookListAvailability(i).equals("Available"))
                {
                    catalog.setBookPos(i);
                    catalog.dispSearchResult(i);
                    catalog.incrementSearchResultNo();        
                }
            }

            System.out.println("\n\nChoose Book: (Enter \"back\" to return to prevous page)");
            chooseBook("0");
            if(catalog.getRejectChooseBook() == true)
            {
                flag = true;
            }
            else
            {
                if(catalog.isBookIndexInteger() == true)
                {
                    if(catalog.getBackTo_ChooseBook() == true)
                    {
                        flag = false;
                    }
                    else
                    {
                        flag = catalog.getChosenBookIndex() >= 0 && catalog.getChosenBookIndex() < catalog.getBookListSize();
                    }
                }
                else
                {
                    flag = false;
                }
            }

        }while(!flag);
    }


    // add book method


    // remove book method

    
}
