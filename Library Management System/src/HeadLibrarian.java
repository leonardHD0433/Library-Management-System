import java.io.*;
import java.util.concurrent.*;
import java.util.Scanner;

//TimeUnit.MILLISECONDS.sleep(500);

public class HeadLibrarian extends User 
{

    public HeadLibrarian(String userType, String userName, String userId, String password) throws IOException//Composition
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
        }
        UtilitiesForSystem.clearScreen();
        return backToHomePage;
    }

    //editBook() - Shaun
    public void editBook() throws IOException, InterruptedException
    {
        viewAllAvailable();  
    }

    public void whatToDoWithBook(int bookIndex) throws IOException, InterruptedException //whatToDoWithBook() - Shaun
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
                        Boolean validChange = false;
                    
                        do {
                            System.out.println("[Current Book Title]: "+catalog.getBookListTitle(bookIndex));
                            String oldTitle = catalog.getBookListTitle(bookIndex);
                            System.out.println(oldTitle);
                            TimeUnit.MILLISECONDS.sleep(1000);
                            System.out.println("Please enter the new title for the book or enter '-999' to exit: ");
                            String newTitle = UtilitiesForSystem.reader.readLine();
                            if(newTitle.equals(oldTitle)) //Ensures the new title is not the same as the old title.
                            {
                                System.out.println("Please do not enter the same title.");
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
                                System.out.println("Please do not enter a blank title.");
                                continue;
                            }
                            else
                            {
                                System.out.println("Edited Succesfully!");
                                TimeUnit.MILLISECONDS.sleep(500);
                                validChange = true;
                                break;
                            }
                        
                        }while(validChange == false);
                        break;
                    }
                    case "2":
                    {
                        System.out.println("Edit Author!");
                        System.out.println("[Book Author]: "+catalog.getBookListAuthor(bookIndex));
                        TimeUnit.MILLISECONDS.sleep(1000);

                        break;
                    }
                    case "3":
                    {
                        System.out.println("Edit Publisher!");
                        System.out.println("[Book Publisher]: "+catalog.getBookListPublisher(bookIndex));
                        TimeUnit.MILLISECONDS.sleep(1000);

                        break;
                    }
                    case "4":
                    {
                        System.out.println("Edit ISBN!");
                        System.out.println("[Book ISBN]: "+catalog.getBookListIsbn(bookIndex));
                        TimeUnit.MILLISECONDS.sleep(1000);

                        break;
                    }
                    case "5":
                    {
                        System.out.println("Edit Genre!");
                        System.out.println("[Book Genre]: "+catalog.getBookGenre(bookIndex));
                        TimeUnit.MILLISECONDS.sleep(1000);

                        break;
                    }
                    case "6":
                    {
                        System.out.println("Edit Year Published!");
                        System.out.println("[Book's Year Published]: "+catalog.getBookListYearPublished(bookIndex));
                        TimeUnit.MILLISECONDS.sleep(1000);

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
                System.out.println("Please enter a digit");
                TimeUnit.MILLISECONDS.sleep(500);
                UtilitiesForSystem.clearScreen();
                System.out.println("Book chosen:\n\n" + catalog.getBookList(catalog.getChosenBookIndex()));
            }
        }while(validOption);

        //Use for loop -> find book -> nested for loop -> find details

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


    // add book method - [Shaun]
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
        String bookYearPublished = " ";

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

    // remove book method - [Shaun]

    
}
