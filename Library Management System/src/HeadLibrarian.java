import java.io.*;
import java.util.concurrent.*;
import java.util.Scanner;

//TimeUnit.MILLISECONDS.sleep(500);

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
                System.out.println("You have chosen to add a book..."); 
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("..");
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println(".");
                TimeUnit.MILLISECONDS.sleep(500);
                createBook();
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
            whatToDoWithBook(0); 
            //since the parameter is only useful for the librarian class, you can leave this 0 here.
            
        }      
    }

    public void whatToDoWithBook(int bookIndex) throws IOException, InterruptedException // you can use this abstract method however you like
    {
        dispOption_inBookPage(bookIndex);
        String read = UtilitiesForSystem.reader.readLine(); //Shaun start editing from here
        //choose what you want to edit or anything
        //Use for loop -> find book -> nested for loop -> find details
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

    public void createBook() throws IOException
    {
        Boolean validIsbn = false;
        Boolean validAuthor = false;
        Boolean validPublisher = false;
        String bIsbn = " ";
        String bAuthor = " ";
        String bPublisher = " ";

        //-------------------TITLE---------------------------
        System.out.println("Enter book TITLE: \n");
        String bTitle = UtilitiesForSystem.reader.readLine();
        //-------------------ISBN----------------------------
        do
        {

            System.out.println("Enter book ISBN: \n");
            String bookIsbn = UtilitiesForSystem.reader.readLine();
    
            if ((bookIsbn.length() == 13 && bookIsbn.startsWith("978") && UtilitiesForSystem.allCharacterAreDigits(bookIsbn)) || (bookIsbn.length() == 14 && bookIsbn.startsWith("978-") && UtilitiesForSystem.allCharacterAreDigits(bookIsbn.replace("-", ""))))
                {
                    if((bookIsbn.length() == 13 && bookIsbn.startsWith("978")))
                    {
                        bIsbn = bookIsbn.substring(0, 3) + "-" + bookIsbn.substring(3, 13);
                    }
                    bIsbn = bookIsbn;
                    validIsbn = true;
                }
                else
                {
                    System.out.println("Invalid ISBN. ISBN must be in 13 digit format. \nExample 1: 978-1119803782\nExample 2: 9781119803782");
                    continue;
                }  

        }while(validIsbn == false);
        //--------------------AUTHOR--------------------------
        do {

            System.out.println("Enter book AUTHOR: \n");
            String bookAuthor = UtilitiesForSystem.reader.readLine();

            if(UtilitiesForSystem.containsDigits(bookAuthor))
            {
                System.out.println("Please ensure there are no digits in your name.");
                continue;
            }
            else
            {
                bAuthor = bookAuthor;
                validAuthor = true;
            }

        }while(validAuthor == false);
        //------------------PUBLISHER-------------------------
        do {

            System.out.println("Enter book PUBLISHER: \n");
             String bookPublisher = UtilitiesForSystem.reader.readLine();

            if(UtilitiesForSystem.containsDigits(bookPublisher))
            {
                System.out.println("Please ensure there are no digits in your input.");
                continue;
            }
            else
            {
                bPublisher = bookPublisher;
                validPublisher = true;
            }

        }while(validPublisher == false);
        //---------------YEAR OF PUBLISHING---------------------
        System.out.println("Enter book's PUBLISHED YEAR: \n");
        int bYearPublished = Integer.parseInt(UtilitiesForSystem.reader.readLine());
        //--------------------GENRE-------------------------------
        System.out.println("Enter book GENRE: \n");
        String bGenre = UtilitiesForSystem.reader.readLine();
        //BOOK AVAILABILITY will always be set to AVAILABLE after a new book creation.
        String bAvailability = "Available";

        
        
        catalog.addBookToList(bTitle, bIsbn, bAuthor, bPublisher, bYearPublished, bGenre, bAvailability);
    }

    // remove book method

    
}
