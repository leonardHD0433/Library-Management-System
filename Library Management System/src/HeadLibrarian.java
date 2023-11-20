import java.io.*;
import java.util.concurrent.*;

//TimeUnit.MILLISECONDS.sleep(500);

public class HeadLibrarian extends User 
{
    private String manageCatalogSelectionFromSession;

    public HeadLibrarian(String userType, String userName, String userId, String password) throws IOException//Composition
    {
        super(userType, userName, userId, password);
    }

    //provide selections to manage the catalog
    public boolean manageCatalog(String selection) throws IOException, InterruptedException
    {
        boolean backToHomePage = false;
        setManageCatalogSelectionFromSession(selection);
        switch (getManageCatalogSelectionFromSession()) 
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
                removeBook(); 
                break;

            case "4":
                backToHomePage = true;  break;

            default:
                System.out.println("Invalid choice. Please try again.");
                TimeUnit.MILLISECONDS.sleep(500);
        }
        UtilitiesForSystem.clearScreen();
        return backToHomePage;
    }

    //addBook
    public void addBook() throws IOException, InterruptedException
    {
        String selection;
        do
        {
        System.out.println("1. Add from archive");
        System.out.println("2. Add new book");
        selection = UtilitiesForSystem.reader.readLine();
        switch (selection) {
            case "1":
                
                break;
        
            case "2":
                createBook();
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
                TimeUnit.MILLISECONDS.sleep(500);
                UtilitiesForSystem.clearScreen();
        }
        }while(!(selection.equals("1") || selection.equals("2")));
            
    }

    public void addFromArchive()
    {
        catalog.dispArchiveList();
        
    }



    //editBook() - Shaun
    public void editBook() throws IOException, InterruptedException
    {
        viewAllAvailable();  
    }

    public void whatToDoWithBook(int bookIndex) throws IOException, InterruptedException //whatToDoWithBook() - Shaun
    {
        if(getManageCatalogSelectionFromSession().equals("2"))
        {
            Boolean validOption = true;
            //bookIndex will be my book chosen option - 1
            do {
                
                dispOption_inBookPage(bookIndex); //Displays the options for what to edit
                System.out.println("\nSelection: ");
                String selectedOption = UtilitiesForSystem.reader.readLine();
                
                if(UtilitiesForSystem.allCharacterAreDigits(selectedOption) && selectedOption.length() == 1)
                {
                    switch (selectedOption)
                    {
                        case "1":
                        {
                            editTitle(bookIndex);
                            break;
                        }
                        case "2":
                        {
                            editAuthor(bookIndex);
                            break;
                        }
                        case "3":
                        {
                            editPublisher(bookIndex);
                            break;
                        }
                        case "4":
                        {
                            editIsbn(bookIndex);    
                            break;
                        }
                        case "5":
                        {
                            editGenre(bookIndex);
                            break;
                        }
                        case "6":
                        {
                            editYearPublished(bookIndex);
                            break;
                        }
                        case "7":
                        {
                            validOption = false;
                            System.out.println("Exiting..");
                            TimeUnit.MILLISECONDS.sleep(1000);
                            break;
                        }
                        default:
                        {
                            System.out.println("Please select only options from 1 - 7!");
                            TimeUnit.MILLISECONDS.sleep(500);
                            UtilitiesForSystem.clearScreen();
                            System.out.println("Book chosen:\n\n" + catalog.getBookList(catalog.getChosenBookIndex()));
                        }
                    }
                            
                }
                else
                {
                    System.out.println("Please enter a digit: ");
                    TimeUnit.MILLISECONDS.sleep(500);
                    UtilitiesForSystem.clearScreen();
                    System.out.println("Book chosen:\n" + catalog.getBookList(catalog.getChosenBookIndex()));
                }
            }while(validOption);
        }
        else if(getManageCatalogSelectionFromSession().equals("3"))
        {
            String selection;
            do
            {
                System.out.println("\n\nAre you sure you want to remove this book? (Y/N)");
                selection = UtilitiesForSystem.reader.readLine().toLowerCase();
                if(selection.equals("y"))
                {
                    catalog.addToArchiveList(bookIndex);
                    catalog.removeFromBookList(bookIndex);
                    System.out.println("Book removed successfully!");
                    TimeUnit.MILLISECONDS.sleep(500);
                    UtilitiesForSystem.clearScreen();
                }
                else if(selection.equals("n"))
                {
                    System.out.println("Canceling book removal.....");
                    TimeUnit.MILLISECONDS.sleep(500);
                    UtilitiesForSystem.clearScreen();
                }
                else
                {
                    System.out.println("Invalid input. Please try again.");
                    TimeUnit.MILLISECONDS.sleep(500);
                    UtilitiesForSystem.clearScreen();
                    System.out.println(catalog.getBookList(bookIndex));
                }
            }while(!(selection.equals("y") || selection.equals("n")));
        }
    }

    //view all "available" books, modified changes using viewAll() at User Class as template - [Edwin]
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

            System.out.println("\n\nChoose Book: (Enter \"back\" to return to previous page)");
            chooseBook("0");
            if(catalog.getRejectChooseBook())
            {
                flag = true;
            }
            else
            {
                if(catalog.isBookIndexInteger())
                {
                    if(catalog.getBackTo_ChooseBook())
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


    // Book creation method - [Shaun]
    public void createBook() throws IOException
    {
        Boolean validIsbn = false;
        Boolean validAuthor = false;
        Boolean validPublisher = false;
        Boolean validGenre = false;
        String bIsbn = " ";
        String bAuthor = " ";
        String bPublisher = " ";
        String bGenre = " ";
        int bookYearPublished;

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
                System.out.println("Please ensure there are no digits in the name.");
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
            //bookYearPublished = String.valueOf(bYearPublished);
        //--------------------GENRE-------------------------------

        do {
            System.out.println("Enter book GENRE: \n");
            String bookGenre = UtilitiesForSystem.reader.readLine();
            if(UtilitiesForSystem.containsDigits(bookGenre))
            {
                System.out.println("Please ensure there are no digits in your input.");
                continue;
            }
            else
            {
                bGenre = bookGenre;
                validGenre = true;
            }
        }while(validGenre == false);
        //BOOK AVAILABILITY will always be set to AVAILABLE after a new book creation.
        String bAvailability = "Available";

        
        
        catalog.addBookToList(bTitle, bIsbn, bAuthor, bPublisher, bYearPublished, bGenre, bAvailability);
    }

    public void removeBook() throws IOException, InterruptedException
    {
        //remove book
        viewAllAvailable();

    }
    
    public void setManageCatalogSelectionFromSession(String selection)
    {
        manageCatalogSelectionFromSession = selection;
    }

    public String getManageCatalogSelectionFromSession()
    {
        return manageCatalogSelectionFromSession;
    }
    
    
    
    
    // All book editing methods - [Shaun]

    public void editTitle(int bookIndex) throws IOException, InterruptedException
    {
        Boolean validChange = false;
                    
        do {
            System.out.println("[Current Book Title]: "+catalog.getBookListTitle(bookIndex));
            String oldTitle = catalog.getBookListTitle(bookIndex);
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("\nPlease enter the new title for the book or enter '-999' to exit: ");
            String newTitle = UtilitiesForSystem.reader.readLine();
            if(newTitle.equals(oldTitle)) //Ensures the new title is not the same as the old title.
            {
                System.out.println("Please do not enter the same title.");
                TimeUnit.MILLISECONDS.sleep(500);
                continue;
            }
            else if("-999".equals(newTitle)) //Exit using "-999".
            {
                System.out.println("Exiting [Edit Title]..");
                TimeUnit.MILLISECONDS.sleep(500);
                validChange = true;
            }
            else if(newTitle.trim().isEmpty()) //Ensures the title is not left into a blank space.
            {
                System.out.println("Please do not enter a blank change.");
                TimeUnit.MILLISECONDS.sleep(500);
                continue;
            }
            else
            {
                for(int i = 0; i < catalog.getBookListSize(); i++)
                {
                    if(i == bookIndex)
                    {
                        catalog.setBookListTitle(bookIndex, newTitle);
                    }
                }
                System.out.println("Edited Succesfully!");
                TimeUnit.MILLISECONDS.sleep(500);
                validChange = true;
                break;
            }
        
        }while(validChange == false);
    } 

    public void editAuthor(int bookIndex) throws IOException, InterruptedException
    {
        Boolean validChange = false;
                    
        do {
            System.out.println("[Current Book Author]: "+catalog.getBookListAuthor(bookIndex));
            String oldAuthor = catalog.getBookListAuthor(bookIndex);
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("\nPlease enter the new author for the book or enter '-999' to exit: ");
            String newAuthor = UtilitiesForSystem.reader.readLine();
            if(newAuthor.equals(oldAuthor)) //Ensures the new author is not the same as the old author.
            {
                System.out.println("Please do not enter the same Author.");
                TimeUnit.MILLISECONDS.sleep(500);
                continue;
            }
            else if("-999".equals(newAuthor)) //Exit using "-999".
            {
                System.out.println("Exiting [Edit Author]..");
                TimeUnit.MILLISECONDS.sleep(500);
                validChange = true;
            }
            else if(newAuthor.trim().isEmpty()) //Ensures the author is not left into a blank space.
            {
                System.out.println("Please do not enter a blank change.");
                TimeUnit.MILLISECONDS.sleep(500);
                continue;
            }
            else
            {
                for(int i = 0; i < catalog.getBookListSize(); i++)
                {
                    if(i == bookIndex)
                    {
                        catalog.setBookListAuthor(bookIndex, newAuthor);
                    }
                }
                System.out.println("Edited Succesfully!");
                TimeUnit.MILLISECONDS.sleep(500);
                validChange = true;
                break;
            }
        
        }while(validChange == false);
    }

    public void editPublisher(int bookIndex) throws IOException, InterruptedException
    {
        Boolean validChange = false;
                    
        do {
            System.out.println("[Current Book Publisher]: "+catalog.getBookListPublisher(bookIndex));
            String oldPublisher = catalog.getBookListPublisher(bookIndex);
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("\nPlease enter the new publisher for the book or enter '-999' to exit: ");
            String newPublisher = UtilitiesForSystem.reader.readLine();
            if(newPublisher.equals(oldPublisher)) //Ensures the new publisher is not the same as the old publisher.
            {
                System.out.println("Please do not enter the same publisher.");
                TimeUnit.MILLISECONDS.sleep(500);
                continue;
            }
            else if("-999".equals(newPublisher)) //Exit using "-999".
            {
                System.out.println("Exiting [Edit Publisher]..");
                TimeUnit.MILLISECONDS.sleep(500);
                validChange = true;
            }
            else if(newPublisher.trim().isEmpty()) //Ensures the publisher is not left into a blank space.
            {
                System.out.println("Please do not enter a blank change.");
                TimeUnit.MILLISECONDS.sleep(500);
                continue;
            }
            else
            {
                for(int i = 0; i < catalog.getBookListSize(); i++)
                {
                    if(i == bookIndex)
                    {
                        catalog.setBookListPublisher(bookIndex, newPublisher);
                    }
                }
                System.out.println("Edited Succesfully!");
                TimeUnit.MILLISECONDS.sleep(500);
                validChange = true;
                break;
            }
        
        }while(validChange == false);
    }

    public void editGenre(int bookIndex) throws IOException, InterruptedException
    {
        Boolean validChange = false;
                    
        do {
            System.out.println("[Current Book Genre]: "+catalog.getBookGenre(bookIndex));
            String oldGenre = catalog.getBookGenre(bookIndex);
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("\nPlease enter the new genre for the book or enter '-999' to exit: ");
            String newGenre = UtilitiesForSystem.reader.readLine();
            if(newGenre.equals(oldGenre))//Ensures the new genre is not the same as the old genre.
            {
                System.out.println("Please do not enter the same genre.");
                TimeUnit.MILLISECONDS.sleep(500);
                continue;
            }
            else if("-999".equals(newGenre)) //Exit using "-999".
            {
                System.out.println("Exiting [Edit Genre]..");
                TimeUnit.MILLISECONDS.sleep(500);
                validChange = true;
            }
            else if(newGenre.trim().isEmpty()) //Ensures the genre is not left into a blank space.
            {
                System.out.println("Please do not enter a blank change.");
                TimeUnit.MILLISECONDS.sleep(500);
                continue;
            }
            else
            {
                for(int i = 0; i < catalog.getBookListSize(); i++)
                {
                    if(i == bookIndex)
                    {
                        catalog.setBookListGenre(bookIndex, newGenre);
                    }
                }
                System.out.println("Edited Succesfully!");
                TimeUnit.MILLISECONDS.sleep(500);
                validChange = true;
                break;
            }
        
        }while(validChange == false);
    }

    public void editIsbn(int bookIndex) throws IOException, InterruptedException
    {
        Boolean validChange = false;
                    
        do {
            System.out.println("[Current Book ISBN]: "+catalog.getBookListIsbn(bookIndex));
            String oldIsbn = catalog.getBookListIsbn(bookIndex);
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("\nPlease enter the new ISBN for the book or enter '-999' to exit: ");
            String newIsbn = UtilitiesForSystem.reader.readLine();
            if(newIsbn.equals(oldIsbn)) //Ensures the new ISBN is not the same as the old ISBN.
            {
                System.out.println("Please do not enter the same ISBN.");
                TimeUnit.MILLISECONDS.sleep(500);
                continue;
            }
            else if("-999".equals(newIsbn)) //Exit using "-999".
            {
                System.out.println("Exiting [Edit ISBN]..");
                TimeUnit.MILLISECONDS.sleep(500);
                validChange = true;
            }
            else if(newIsbn.trim().isEmpty()) //Ensures the ISBN is not left into a blank space.
            {
                System.out.println("Please do not enter a blank change.");
                TimeUnit.MILLISECONDS.sleep(500);
                continue;
            }
            else
            {
                if((newIsbn.length() == 13 && newIsbn.startsWith("978") && UtilitiesForSystem.allCharacterAreDigits(newIsbn)) || (newIsbn.length() == 14 && newIsbn.startsWith("978-") && UtilitiesForSystem.allCharacterAreDigits(newIsbn.replace("-", ""))))
                {
                    if((newIsbn.length() == 13 && newIsbn.startsWith("978")))
                    {
                        newIsbn = newIsbn.substring(0, 3) + "-" + newIsbn.substring(3, 13);
                        for(int i = 0; i < catalog.getBookListSize(); i++)
                        {
                            if(i == bookIndex)
                            {
                                catalog.setBookListIsbn(bookIndex, newIsbn);
                            }
                        }
                        System.out.println("Edited Succesfully!");
                        TimeUnit.MILLISECONDS.sleep(500);
                        validChange = true;
                        break;
                    }
                }
                else
                {
                    System.out.println("Invalid ISBN. ISBN must be in 13 digit format. \nExample 1: 978-1119803782\nExample 2: 9781119803782");
                    TimeUnit.MILLISECONDS.sleep(500);
                    continue;
                } 
            }

        }while(validChange == false);
    }

    public void editYearPublished(int bookIndex) throws IOException, InterruptedException
    {
        Boolean validChange = false;
                    
        do {
            System.out.println("[Current Book Publishing Year]: "+catalog.getBookListYearPublished(bookIndex));
            int oldYearPublished = catalog.getBookListYearPublished(bookIndex);
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("\nPlease enter the new publishing year for the book (1800 - 2023), or enter '-999' to exit: ");
            String newBookYearPublished = UtilitiesForSystem.reader.readLine(); //Have to place into string for error checking later
            int yearPublishedToInt = Integer.parseInt(newBookYearPublished);
            if(yearPublishedToInt == oldYearPublished) //Ensures the new publishing year is not the same as the old publishing year.
            {
                System.out.println("Please do not enter the same publishing year.");
                TimeUnit.MILLISECONDS.sleep(500);
                continue;
            }
            else if("-999".equals(newBookYearPublished)) //Exit using "-999".
            {
                System.out.println("Exiting [Edit Publishing Year]..");
                TimeUnit.MILLISECONDS.sleep(500);
                validChange = true;
            }
            else if(newBookYearPublished.trim().isEmpty()) //Ensures the publishing year is not left into a blank space.
            {
                System.out.println("Please do not enter a blank change.");
                TimeUnit.MILLISECONDS.sleep(500);
                continue;
            }
            else if (newBookYearPublished.length() == 4) //Ensures user can only input a year in 4 digits (XXXX).
            {
                if(yearPublishedToInt > 1800 && yearPublishedToInt < 2023) //Checks to see if the publishing year is more than the 1800s and below 2023.
                {
                    for(int i = 0; i < catalog.getBookListSize(); i++)
                    {
                        if(i == bookIndex)
                        {
                            catalog.setBookListYearPublished(i, yearPublishedToInt);
                        }
                    }
                    System.out.println("Edited Succesfully!");
                    TimeUnit.MILLISECONDS.sleep(500);
                    validChange = true;
                    break;
            
                } 
                else
                {
                    System.out.println("The limits of publishing year is between 1800 - 2023!");
                    TimeUnit.MILLISECONDS.sleep(1000);
                    continue;
                }
                
            }      
            else
            {
                System.out.println("Please enter a valid year in the format: XXXX");
                TimeUnit.MILLISECONDS.sleep(1000);
                continue;
            }
        
        }while(validChange == false);
    }


}
