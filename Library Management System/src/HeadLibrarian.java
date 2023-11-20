import java.io.*;
import java.util.concurrent.*;

public class HeadLibrarian extends User 
{
    private String manageCatalogSelectionFromSession;

    public HeadLibrarian(String userType, String userName, String userId, String password) throws IOException, ClassNotFoundException//Composition
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
                addBook();
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
                TimeUnit.MILLISECONDS.sleep(1000);
        }
        UtilitiesForSystem.clearScreen();
        return backToHomePage;
    }

    //addBook
    public void addBook() throws IOException, InterruptedException
    {
        boolean flag = true;
        String selection;
        UtilitiesForSystem.clearScreen();
        do
        {
            System.out.println(toString());
            System.out.println("\n\n\n1. Add from archive");
            System.out.println("2. Add new book");
            System.out.println("3. Back\n");
            System.out.print("Selection: ");
            selection = UtilitiesForSystem.reader.readLine();
            switch (selection) 
            {
                case "1":
                    addFromArchive(); flag = false;
                    break;
            
                case "2":
                    createBook(); flag = false;
                    break;

                case "3":
                    flag = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    TimeUnit.MILLISECONDS.sleep(1000);
                    UtilitiesForSystem.clearScreen();
            }
        }while(flag);
            
    }

    public void addFromArchive() throws IOException, InterruptedException
    {
        String index, selection;
        int archiveIndex;
        UtilitiesForSystem.clearScreen();

        if(catalog.getArchiveListSize() == 0)
        {
            System.out.println("No books in archive.");
            TimeUnit.MILLISECONDS.sleep(1000);
            UtilitiesForSystem.clearScreen();
            return;
        }

        do
        {
            index = "";
            do
            {
                catalog.dispArchiveList();
                System.out.println("Book to add back to list: ");
                index = UtilitiesForSystem.reader.readLine();
                if(!UtilitiesForSystem.allCharacterAreDigits(index))
                {
                    System.out.println("Please enter a digit.");
                    TimeUnit.MILLISECONDS.sleep(1000);
                    UtilitiesForSystem.clearScreen();
                }
            }while(!UtilitiesForSystem.allCharacterAreDigits(index));

            archiveIndex = Integer.parseInt(index);
            if(archiveIndex < 0 || archiveIndex >= catalog.getArchiveListSize())
            {
                System.out.println("Please enter a valid index.");
                TimeUnit.MILLISECONDS.sleep(1000);
                UtilitiesForSystem.clearScreen();
            }
        }while(archiveIndex < 0 || archiveIndex >= catalog.getArchiveListSize());

        do
        {
            catalog.getBookFromArchive(archiveIndex);
            System.out.println("\n\nConfirmation to add this book from archive? (Y/N)");
            selection = UtilitiesForSystem.reader.readLine().toLowerCase();
            switch (selection) 
            {
                case "y":
                    catalog.addFromArchiveToBookList(archiveIndex);
                    catalog.removeFromArchiveList(archiveIndex);
                    System.out.println("Book added successfully!");
                    TimeUnit.MILLISECONDS.sleep(1000);
                    UtilitiesForSystem.clearScreen();
                    break;

                case "n":
                    System.out.println("Canceling book addition.....");
                    TimeUnit.MILLISECONDS.sleep(1000);
                    UtilitiesForSystem.clearScreen();
                    break;
            
                default:
                    System.out.println("Invalid choice. Please try again.");
                    TimeUnit.MILLISECONDS.sleep(1000);
                    UtilitiesForSystem.clearScreen();
                    break;
            }
        }while (!(selection.equals("y") || selection.equals("n")));




    }

    //editBook() - Shaun
    public void editBook() throws IOException, InterruptedException
    {
        viewAllAvailable();  
    }

    public void whatToDoWithBook(int bookIndex) throws IOException, InterruptedException //whatToDoWithBook() - [Shaun] Updates : Edwin
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
                            TimeUnit.MILLISECONDS.sleep(1000);
                            
                        }    
                    }       

                    UtilitiesForSystem.clearScreen();
                    System.out.println("Book chosen:\n\n" + catalog.getBookList(catalog.getChosenBookIndex()));
                }
                else
                {
                    System.out.println("Please enter a digit: ");
                    TimeUnit.MILLISECONDS.sleep(1000);
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
                    if(catalog.getBookListSize() == 5)
                    {
                        System.out.println("There must be at least 5 books in the catalog.");
                        TimeUnit.MILLISECONDS.sleep(1000);
                        UtilitiesForSystem.clearScreen();
                    }
                    else
                    {
                        catalog.addToArchiveList(bookIndex);
                        catalog.removeFromBookList(bookIndex);
                        System.out.println("Book removed successfully!");
                        TimeUnit.MILLISECONDS.sleep(1000);
                        UtilitiesForSystem.clearScreen();
                    }
                }
                else if(selection.equals("n"))
                {
                    System.out.println("Canceling book removal.....");
                    TimeUnit.MILLISECONDS.sleep(1000);
                    UtilitiesForSystem.clearScreen();
                }
                else
                {
                    System.out.println("Invalid input. Please try again.");
                    TimeUnit.MILLISECONDS.sleep(1000);
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
                else if((i == catalog.getBookListSize() - 1) && catalog.getSearchResultNo() == 0)
                {
                    System.out.println("No books available.");
                    TimeUnit.MILLISECONDS.sleep(1000);
                    UtilitiesForSystem.clearScreen();
                    return;
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

    // Book creation method - [Shaun] Updates : Edwin
    public void createBook() throws IOException, InterruptedException
    {
        Boolean valid = false;
        String bTitle = "", bIsbn = " ", bAuthor = " ", bPublisher = " ", bGenre = " ", bAvailability = "Available", confirm = ""; //BOOK AVAILABILITY will always be set to AVAILABLE after a new book creation
        int bookYearPublished = 0;

        UtilitiesForSystem.clearScreen();
        System.out.println("-------------------------------------------------");
        System.out.println("ADDING NEW BOOK");
        System.out.println("-------------------------------------------------\n");
        //-------------------TITLE---------------------------
        System.out.print("Enter book TITLE: ");
        bTitle = UtilitiesForSystem.reader.readLine();

        //-------------------ISBN----------------------------
        do
        {
            System.out.print("\nEnter book ISBN: ");
            String bookIsbn = UtilitiesForSystem.reader.readLine();
    
            if ((bookIsbn.length() == 13 && bookIsbn.startsWith("978") && UtilitiesForSystem.allCharacterAreDigits(bookIsbn)) || (bookIsbn.length() == 14 && bookIsbn.startsWith("978-") && UtilitiesForSystem.allCharacterAreDigits(bookIsbn.replace("-", ""))))
                {
                    if((bookIsbn.length() == 13 && bookIsbn.startsWith("978")))
                    {
                        bIsbn = bookIsbn.substring(0, 3) + "-" + bookIsbn.substring(3, 13);
                    }
                    else
                    {
                        bIsbn = bookIsbn;
                    }
                    valid = true;
                }
                else
                {
                    System.out.println("Invalid ISBN. ISBN must be in 13 digit format. \nExample 1: 978-1119803782\nExample 2: 9781119803782");
                    UtilitiesForSystem.pressEnterToContinue();
                    UtilitiesForSystem.clearScreen();
                    System.out.println("-------------------------------------------------");
                    System.out.println("ADDING NEW BOOK");
                    System.out.println("-------------------------------------------------\n");
                    System.out.println("Enter book TITLE: " + bTitle);
                }  
        }while(!valid);
        
        //--------------------AUTHOR--------------------------
        do 
        {
            System.out.print("\nEnter book AUTHOR: ");
            String bookAuthor = UtilitiesForSystem.reader.readLine();

            if(UtilitiesForSystem.containsDigits(bookAuthor))
            {
                System.out.println("Please ensure there are no digits in the name.");
                TimeUnit.MILLISECONDS.sleep(1000);
                UtilitiesForSystem.clearScreen();
                System.out.println("-------------------------------------------------");
                System.out.println("ADDING NEW BOOK");
                System.out.println("-------------------------------------------------\n");
                System.out.println("Enter book TITLE: " + bTitle + "\n");
                System.out.println("Enter book ISBN: " + bIsbn);
                valid = false;
            }
            else
            {
                bAuthor = bookAuthor;
                valid = true;
            }

        }while(!valid);

        //------------------PUBLISHER-------------------------
        do 
        {
            System.out.print("\nEnter book PUBLISHER: ");
             String bookPublisher = UtilitiesForSystem.reader.readLine();
            if(UtilitiesForSystem.containsDigits(bookPublisher))
            {
                System.out.println("Please ensure there are no digits in your input.");
                TimeUnit.MILLISECONDS.sleep(1000);
                UtilitiesForSystem.clearScreen();
                System.out.println("-------------------------------------------------");
                System.out.println("ADDING NEW BOOK");
                System.out.println("-------------------------------------------------\n");
                System.out.println("Enter book TITLE: " + bTitle + "\n");
                System.out.println("Enter book ISBN: " + bIsbn + "\n");
                System.out.println("Enter book AUTHOR: " + bAuthor);
                valid = false;
            }
            else
            {
                bPublisher = bookPublisher;
                valid = true;
            }
        }while(!valid);

        //---------------YEAR OF PUBLICATION---------------------
        do 
        {
            String yearPublished;
            System.out.print("\nEnter book's publication year (Publishing year limit: 2000-2023): ");
            yearPublished = UtilitiesForSystem.reader.readLine();
            if(UtilitiesForSystem.allCharacterAreDigits(yearPublished) && yearPublished.length() == 4)
            {
                bookYearPublished = Integer.parseInt(yearPublished);
                if(bookYearPublished >= 2000 && bookYearPublished <= 2023)
                {
                    valid = true;
                }
                else
                {
                    System.out.println("The limits of publishing year is between 2000 - 2023!");
                    TimeUnit.MILLISECONDS.sleep(1000);
                    UtilitiesForSystem.clearScreen();
                    System.out.println("-------------------------------------------------");
                    System.out.println("ADDING NEW BOOK");
                    System.out.println("-------------------------------------------------\n");
                    System.out.println("Enter book TITLE: " + bTitle + "\n");
                    System.out.println("Enter book ISBN: " + bIsbn + "\n");
                    System.out.println("Enter book AUTHOR: " + bAuthor + "\n");
                    System.out.println("Enter book PUBLISHER: " + bPublisher);
                    valid = false;
                }
            }
            else
            {
                System.out.println("Please enter a valid year in the format: XXXX");
                TimeUnit.MILLISECONDS.sleep(1000);
                UtilitiesForSystem.clearScreen();
                System.out.println("-------------------------------------------------");
                System.out.println("ADDING NEW BOOK");
                System.out.println("-------------------------------------------------\n");
                System.out.println("Enter book TITLE: " + bTitle + "\n");
                System.out.println("Enter book ISBN: " + bIsbn + "\n");
                System.out.println("Enter book AUTHOR: " + bAuthor + "\n");
                System.out.println("Enter book PUBLISHER: " + bPublisher);
                valid = false;
            }
                
        }while(!valid);

        //--------------------GENRE-------------------------------
        do 
        {
            String selection;
            catalog.clearGenreTypes_inCatalog();
            for(int i = 0; i < catalog.getBookListSize(); i++)
            {
                if(catalog.checkCatalogForGenre(i))
                {
                    catalog.addToGenreTypes_inCatalogSize(i);
                }
            }
            catalog.sortGenreTypes_inCatalog();
            for(int i = 0; i < catalog.getGenreTypes_inCatalogSize(); i++)
            {
                System.out.println((i + 1) + ". " + catalog.getGenreTypes_inCatalog(i));
            }
            System.out.println(catalog.getGenreTypes_inCatalogSize() + 1 + ". " + "Add New Genre");
            System.out.println("\nSelection: ");
            selection = UtilitiesForSystem.reader.readLine();

            if(selection.equals(String.valueOf(catalog.getGenreTypes_inCatalogSize() + 1)))
            {
                boolean flag = false;
                
                System.out.println("Enter new genre: ");
                bGenre = UtilitiesForSystem.reader.readLine();

                for (int i = 0; i < catalog.getGenreTypes_inCatalogSize(); i++) 
                {
                    if (catalog.getGenreTypes_inCatalog(i).equalsIgnoreCase(bGenre))
                    {
                        flag = true;
                        break;
                    } 
                }

                if(UtilitiesForSystem.containsDigits(bGenre))
                {
                    System.out.println("Please ensure there are no digits in your input.");
                    TimeUnit.MILLISECONDS.sleep(1000);
                    UtilitiesForSystem.clearScreen();
                    System.out.println("-------------------------------------------------");
                    System.out.println("ADDING NEW BOOK");
                    System.out.println("-------------------------------------------------\n");
                    System.out.println("Enter book TITLE: " + bTitle + "\n");
                    System.out.println("Enter book ISBN: " + bIsbn + "\n");
                    System.out.println("Enter book AUTHOR: " + bAuthor + "\n");
                    System.out.println("Enter book PUBLISHER: " + bPublisher + "\n");
                    System.out.println("Enter book's publication year (Publishing year limit: 2000-2023): " + bookYearPublished);
                    valid = false;
                }
                else if(flag)
                {
                    System.out.println("Genre already exist. Please choose from the existing genres.");
                    TimeUnit.MILLISECONDS.sleep(1000);
                    UtilitiesForSystem.clearScreen();
                    System.out.println("-------------------------------------------------");
                    System.out.println("ADDING NEW BOOK");
                    System.out.println("-------------------------------------------------\n");
                    System.out.println("Enter book TITLE: " + bTitle + "\n");
                    System.out.println("Enter book ISBN: " + bIsbn + "\n");
                    System.out.println("Enter book AUTHOR: " + bAuthor + "\n");
                    System.out.println("Enter book PUBLISHER: " + bPublisher + "\n");
                    System.out.println("Enter book's publication year (Publishing year limit: 2000-2023): " + bookYearPublished);
                    valid = false;
                }
            }
            else
            {
                if(UtilitiesForSystem.allCharacterAreDigits(selection))
                {
                    int genreIndex = Integer.parseInt(selection) - 1;

                    if(genreIndex >=0 && genreIndex < catalog.getGenreTypes_inCatalogSize())
                    {
                        bGenre = catalog.getGenreTypes_inCatalog(genreIndex);
                        valid = true;
                    }
                    else
                    {
                        System.out.println("Please enter from the available options");
                        TimeUnit.MILLISECONDS.sleep(1000);
                        UtilitiesForSystem.clearScreen();
                        System.out.println("-------------------------------------------------");
                        System.out.println("ADDING NEW BOOK");
                        System.out.println("-------------------------------------------------\n");
                        System.out.println("Enter book TITLE: " + bTitle + "\n");
                        System.out.println("Enter book ISBN: " + bIsbn + "\n");
                        System.out.println("Enter book AUTHOR: " + bAuthor + "\n");
                        System.out.println("Enter book PUBLISHER: " + bPublisher + "\n");
                        System.out.println("Enter book's publication year (Publishing year limit: 2000-2023): " + bookYearPublished);
                        valid = false;
                    }
                }
                else
                {
                    System.out.println("Please enter a digit.");
                    TimeUnit.MILLISECONDS.sleep(1000);
                    UtilitiesForSystem.clearScreen();
                    System.out.println("-------------------------------------------------");
                    System.out.println("ADDING NEW BOOK");
                    System.out.println("-------------------------------------------------\n");
                    System.out.println("Enter book TITLE: " + bTitle + "\n");
                    System.out.println("Enter book ISBN: " + bIsbn + "\n");
                    System.out.println("Enter book AUTHOR: " + bAuthor + "\n");
                    System.out.println("Enter book PUBLISHER: " + bPublisher + "\n");
                    System.out.println("Enter book's publication year (Publishing year limit: 2000-2023): " + bookYearPublished);
                    valid = false;
                }
            }
        }while(!valid);

        //confirm add book
        do
        {
            UtilitiesForSystem.clearScreen();
            System.out.println("-------------------------------------------------");
            System.out.println("NEW BOOK");
            System.out.println("-------------------------------------------------");
            System.out.println("Book TITLE: " + bTitle);
            System.out.println("Book ISBN: " + bIsbn);
            System.out.println("Book AUTHOR: " + bAuthor);
            System.out.println("Book PUBLISHER: " + bPublisher);
            System.out.println("Book YEAR PUBLISHED: " + bookYearPublished);
            System.out.println("Book GENRE: " + bGenre);
            System.out.println("Book AVAILABILITY: " + bAvailability);
            System.out.println("-------------------------------------------------");
            System.out.println("\n\nConfirm add book? (Y/N)");
            confirm = UtilitiesForSystem.reader.readLine().toLowerCase();
            
            if(confirm.equals("y"))
            {
                catalog.addBookToList(bTitle, bIsbn, bAuthor, bPublisher, bookYearPublished, bGenre, bAvailability);
                System.out.println("Book added successfully!");
                TimeUnit.MILLISECONDS.sleep(1000);
                UtilitiesForSystem.clearScreen();
            }
            else if(confirm.equals("n"))
            {
                System.out.println("Canceling book addition.....");
                TimeUnit.MILLISECONDS.sleep(1000);
                UtilitiesForSystem.clearScreen();
            }
            else
            {
                System.out.println("Invalid input. Please try again.");
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        }while(!(confirm.equals("y") || confirm.equals("n")));
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
            UtilitiesForSystem.clearScreen();
            System.out.println("[Current Book Title]: "+catalog.getBookListTitle(bookIndex));
            String oldTitle = catalog.getBookListTitle(bookIndex);
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("\nPlease enter the new title for the book or enter '-999' to exit: ");
            String newTitle = UtilitiesForSystem.reader.readLine();
            if(newTitle.equals(oldTitle)) //Ensures the new title is not the same as the old title.
            {
                System.out.println("Please do not enter the same title.");
                TimeUnit.MILLISECONDS.sleep(1000);
            }
            else if("-999".equals(newTitle)) //Exit using "-999".
            {
                System.out.println("Exiting [Edit Title]..");
                TimeUnit.MILLISECONDS.sleep(1000);
                UtilitiesForSystem.clearScreen();
                validChange = true;
            }
            else if(newTitle.isBlank()) //Ensures the title is not left into a blank space.
            {
                System.out.println("Please do not enter a blank change.");
                TimeUnit.MILLISECONDS.sleep(1000);
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
                TimeUnit.MILLISECONDS.sleep(1000);
                validChange = true;
                break;
            }
        
        }while(!validChange);
    } 

    public void editAuthor(int bookIndex) throws IOException, InterruptedException
    {
        Boolean validChange = false;
                    
        do {
            UtilitiesForSystem.clearScreen();
            System.out.println("[Current Book Author]: "+catalog.getBookListAuthor(bookIndex));
            String oldAuthor = catalog.getBookListAuthor(bookIndex);
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("\nPlease enter the new author for the book or enter '-999' to exit: ");
            String newAuthor = UtilitiesForSystem.reader.readLine();
            if(newAuthor.equals(oldAuthor)) //Ensures the new author is not the same as the old author.
            {
                System.out.println("Please do not enter the same Author.");
                TimeUnit.MILLISECONDS.sleep(1000);
            }
            else if("-999".equals(newAuthor)) //Exit using "-999".
            {
                System.out.println("Exiting [Edit Author]..");
                TimeUnit.MILLISECONDS.sleep(1000);
                UtilitiesForSystem.clearScreen();
                validChange = true;
            }
            else if(newAuthor.isBlank()) //Ensures the author is not left into a blank space.
            {
                System.out.println("Please do not enter a blank change.");
                TimeUnit.MILLISECONDS.sleep(1000);
            }
            else if(UtilitiesForSystem.containsDigits(newAuthor))
            {
                System.out.println("Please ensure there are no digits in the name.");
                TimeUnit.MILLISECONDS.sleep(1000);
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
                TimeUnit.MILLISECONDS.sleep(1000);
                validChange = true;
                break;
            }
        
        }while(!validChange);
    }

    public void editPublisher(int bookIndex) throws IOException, InterruptedException
    {
        Boolean validChange = false;
                    
        do {
            UtilitiesForSystem.clearScreen();
            System.out.println("[Current Book Publisher]: "+catalog.getBookListPublisher(bookIndex));
            String oldPublisher = catalog.getBookListPublisher(bookIndex);
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("\nPlease enter the new publisher for the book or enter '-999' to exit: ");
            String newPublisher = UtilitiesForSystem.reader.readLine();
            if(newPublisher.equals(oldPublisher)) //Ensures the new publisher is not the same as the old publisher.
            {
                System.out.println("Please do not enter the same publisher.");
                TimeUnit.MILLISECONDS.sleep(1000);
            }
            else if("-999".equals(newPublisher)) //Exit using "-999".
            {
                System.out.println("Exiting [Edit Publisher]..");
                TimeUnit.MILLISECONDS.sleep(1000);
                UtilitiesForSystem.clearScreen();
                validChange = true;
            }
            else if(newPublisher.isBlank()) //Ensures the publisher is not left into a blank space.
            {
                System.out.println("Please do not enter a blank change.");
                TimeUnit.MILLISECONDS.sleep(1000);
            }
            else if(UtilitiesForSystem.containsDigits(newPublisher))
            {
                System.out.println("Please ensure there are no digits in the name.");
                TimeUnit.MILLISECONDS.sleep(1000);
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
                TimeUnit.MILLISECONDS.sleep(1000);
                validChange = true;
                break;
            }
        
        }while(!validChange);
    }

    public void editGenre(int bookIndex) throws IOException, InterruptedException
    {
        Boolean validChange = false;
                    
        do {
            UtilitiesForSystem.clearScreen();
            System.out.println("[Current Book Genre]: "+catalog.getBookGenre(bookIndex));
            String oldGenre = catalog.getBookGenre(bookIndex);
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("\nPlease enter the new genre for the book or enter '-999' to exit: ");
            String newGenre = UtilitiesForSystem.reader.readLine();
            if(newGenre.equals(oldGenre))//Ensures the new genre is not the same as the old genre.
            {
                System.out.println("Please do not enter the same genre.");
                TimeUnit.MILLISECONDS.sleep(1000);
            }
            else if("-999".equals(newGenre)) //Exit using "-999".
            {
                System.out.println("Exiting [Edit Genre]..");
                TimeUnit.MILLISECONDS.sleep(1000);
                UtilitiesForSystem.clearScreen();
                validChange = true;
            }
            else if(newGenre.isBlank()) //Ensures the genre is not left into a blank space.
            {
                System.out.println("Please do not enter a blank change.");
                TimeUnit.MILLISECONDS.sleep(1000);
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
                TimeUnit.MILLISECONDS.sleep(1000);
                validChange = true;
                break;
            }
        
        }while(!validChange);
    }

    public void editIsbn(int bookIndex) throws IOException, InterruptedException
    {
        Boolean validChange = false;
                    
        do {
            UtilitiesForSystem.clearScreen();
            System.out.println("[Current Book ISBN]: "+catalog.getBookListIsbn(bookIndex));
            String oldIsbn = catalog.getBookListIsbn(bookIndex);
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("\nPlease enter the new ISBN for the book or enter '-999' to exit: ");
            String newIsbn = UtilitiesForSystem.reader.readLine();
            if(newIsbn.equals(oldIsbn)) //Ensures the new ISBN is not the same as the old ISBN.
            {
                System.out.println("Please do not enter the same ISBN.");
                TimeUnit.MILLISECONDS.sleep(1000);
            }
            else if("-999".equals(newIsbn)) //Exit using "-999".
            {
                System.out.println("Exiting [Edit ISBN]..");
                TimeUnit.MILLISECONDS.sleep(1000);
                UtilitiesForSystem.clearScreen();
                validChange = true;
            }
            else if(newIsbn.isBlank()) //Ensures the ISBN is not left into a blank space.
            {
                System.out.println("Please do not enter a blank change.");
                TimeUnit.MILLISECONDS.sleep(1000);
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
                        TimeUnit.MILLISECONDS.sleep(1000);
                        validChange = true;
                        break;
                    }
                }
                else
                {
                    System.out.println("Invalid ISBN. ISBN must be in 13 digit format. \nExample 1: 978-1119803782\nExample 2: 9781119803782");
                    TimeUnit.MILLISECONDS.sleep(1000);
                } 
            }

        }while(!validChange);
    }

    public void editYearPublished(int bookIndex) throws IOException, InterruptedException
    {
        Boolean validChange = false;
                    
        do {   
            UtilitiesForSystem.clearScreen();  
            System.out.println("[Current Book Publishing Year]: "+ catalog.getBookListYearPublished(bookIndex));
            int oldYearPublished = catalog.getBookListYearPublished(bookIndex), yearPublishedToInt;
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("\nPlease enter the new publishing year for the book (2000 - 2023), or enter '-999' to exit: ");
            String newBookYearPublished = UtilitiesForSystem.reader.readLine(); //Have to place into string for error checking later
            if(UtilitiesForSystem.allCharacterAreDigits(newBookYearPublished))
            {
                yearPublishedToInt = Integer.parseInt(newBookYearPublished);
            
                if(yearPublishedToInt == oldYearPublished) //Ensures the new publishing year is not the same as the old publishing year.
                {
                    System.out.println("Please do not enter the same publishing year.");
                    TimeUnit.MILLISECONDS.sleep(1000);
                }
                else if(yearPublishedToInt == -999) //Exit using "-999".
                {
                    System.out.println("Exiting [Edit Publishing Year]..");
                    TimeUnit.MILLISECONDS.sleep(1000);
                    UtilitiesForSystem.clearScreen();
                    validChange = true;
                }
                else if (newBookYearPublished.length() == 4) //Ensures user can only input a year in 4 digits (XXXX).
                {
                    if(yearPublishedToInt >= 2000 && yearPublishedToInt <= 2023) //Checks to see if the publishing year is more than the 1800s and below 2023.
                    {
                        for(int i = 0; i < catalog.getBookListSize(); i++)
                        {
                            if(i == bookIndex)
                            {
                                catalog.setBookListYearPublished(i, yearPublishedToInt);
                            }
                        }
                        System.out.println("Edited Succesfully!");
                        TimeUnit.MILLISECONDS.sleep(1000);
                        validChange = true;
                        break;
                
                    } 
                    else
                    {
                        System.out.println("The limits of publishing year is between 2000 - 2023!");
                        TimeUnit.MILLISECONDS.sleep(1000);
                    }
                    
                }      
                else
                {
                    System.out.println("Please enter a valid year in the format: XXXX");
                    TimeUnit.MILLISECONDS.sleep(1000);
                }
            }
            else
            {
                System.out.println("Please enter a digit.");
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        
        }while(!validChange);
    }


}
