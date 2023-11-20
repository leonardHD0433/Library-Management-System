
import java.io.*;
import java.util.concurrent.*;

public abstract class User
{
    protected Catalog catalog;
    protected String userType, userName, userId, password, readUserId, readPassword;
    

    public User(String userType, String userName, String userId, String password) throws IOException, ClassNotFoundException//Composition
    {
        catalog = new Catalog();
        setUserDetails(userType, userName, userId, password);
    }

    public void setUserDetails(String userType, String userName, String userId, String password)
    {
        this.userType = userType;
        this.userName = userName;
        this.userId = userId;
        this.password = password;
    }

    public abstract void whatToDoWithBook(int bookIndex) throws IOException, InterruptedException;

    public boolean login() throws IOException, InterruptedException
    {
        System.out.println("\n\nUser ID: ");
        readUserId = UtilitiesForSystem.reader.readLine();
        System.out.println("\nPassword: ");
        readPassword = UtilitiesForSystem.reader.readLine();

        if(readUserId.equals(userId) && readPassword.equals(password))
        { 
            System.out.println("Login Successful");
            TimeUnit.MILLISECONDS.sleep(1000);           /*
                                                                    GeeksforGeeks. (2021, September 29). TimeUnit Class in Java with Examples. [Article] 
                                                                    https://www.geeksforgeeks.org/timeunit-class-in-java-with-examples/ (Date Accessed: 9/10/2023)

                                                                    (Asked by: Chris)(Answered by: Eyal Schneider) sleep from main thread is throwing InterruptedException. (n.d.). Stack Overflow. [Source Code]
                                                                    https://stackoverflow.com/questions/2663419/sleep-from-main-thread-is-throwing-interruptedexception (Date Accessed: 9/10/2023)
                                                                 */
            UtilitiesForSystem.clearScreen();  /*
                                                    How to clear screen in Java - Javatpoint. (n.d.). www.javatpoint.com. [Article] 
                                                    https://www.javatpoint.com/how-to-clear-screen-in-java (Date Accessed : 10/10/2023)
                                               */
            return true;
        }
        else
        {   
            System.out.println("Invalid user Id or password. Please try again");
            TimeUnit.MILLISECONDS.sleep(1000);
            UtilitiesForSystem.clearScreen();
            return false;
        }
    }

    public String getUserType()
    {
        return userType;
    }

    //Browse Catalog Method in Parent Class as both librarian and head librarian can browse the catalog but in different scenarios

    //literally browse the catalog
    public boolean browseCatalog(String selection) throws IOException, InterruptedException
    {
        boolean backToHomePage = false;
        switch (selection) 
        {
            case "1":
                viewAll(); break;

            case "2":
                searchByGenre(); break;

            case "3":
                searchByTitle(); break;

            case "4":
                searchByAuthor(); break;

            case "5":
                searchByPublisher(); break;

            case "6":
                searchByISBN(); break;

            case "7":
                backToHomePage = true; break;

            default:
                UtilitiesForSystem.selectionErrorMsg();
        }

        return backToHomePage;
    }

    public void chooseBook(String chosenFilter) throws IOException, InterruptedException, IndexOutOfBoundsException
    {
        String chooseBook;
        int chosenIndex;
        catalog.setBackTo_ChooseBook(false);

        chooseBook = UtilitiesForSystem.reader.readLine().toLowerCase();
        if(!UtilitiesForSystem.allCharacterAreDigits(chooseBook) && !chooseBook.equals("back"))
        {
            System.out.println("Please enter a digit or enter back.");
            TimeUnit.MILLISECONDS.sleep(1000);
            UtilitiesForSystem.clearScreen();
        }
        else if(chooseBook.equals("back"))
        {
            catalog.setRejectChooseBook(chooseBook);
            return;
        }
        else if ((Integer.parseInt(chooseBook) - 1) > catalog.getBookListSize() || Integer.parseInt(chooseBook) - 1 < 0)
        {
            System.out.println("Invalid choice. Please try again.");
            TimeUnit.MILLISECONDS.sleep(1000);
            UtilitiesForSystem.clearScreen();
        }
        else if((Integer.parseInt(chooseBook) - 1) < catalog.getBookListSize() && Integer.parseInt(chooseBook) - 1 >= 0)
        {
            chosenIndex = Integer.parseInt(chooseBook) - 1;
            
            catalog.setRejectChooseBook(chooseBook);
            catalog.setBookIndexInteger(true);
            switch (chosenFilter) 
            {
                case "1":
                    //choose book from original list
                    catalog.setChosenBookIndex(chosenIndex);
                    if(chosenIndex >= 0 && chosenIndex < catalog.getBookListSize())
                    {
                        UtilitiesForSystem.clearScreen();
                        System.out.println("Book chosen:\n\n" + catalog.getBookList(chosenIndex));
                        whatToDoWithBook(chosenIndex);
                    }
                    else
                    {
                        System.out.println("Invalid choice. Please try again.");
                        TimeUnit.MILLISECONDS.sleep(1000);
                    }
                    break;
            
                default:
                    //choose book from filtered list
                    if(chosenIndex >= 0 && chosenIndex < catalog.bookPos_forFilteredListSize())
                    {
                        catalog.setChosenBookIndex(catalog.getBookPos(chosenIndex));
                        UtilitiesForSystem.clearScreen();
                        System.out.println("Book chosen:\n\n" + catalog.getBookList(catalog.getChosenBookIndex()));
                        whatToDoWithBook(catalog.getChosenBookIndex());
                        
                    }
                    else
                    {
                        System.out.println("Invalid choice. Please try again.");
                        TimeUnit.MILLISECONDS.sleep(1000);
                    }
                    break;
            }   
        }
        else
        {
            catalog.setRejectChooseBook("back");
            catalog.setBookIndexInteger(false);
        }
    }

    // Method to display all books in the catalog in ascending order of book title
    public void viewAll() throws IOException, InterruptedException
    {
        boolean flag;
        catalog.sortBookList();
        do
        {
            UtilitiesForSystem.clearScreen();
            System.out.println("CATALOG");
            System.out.println("==============================================================================================================================================================");
            System.out.println("No.");
            for (int i = 0; i < catalog.getBookListSize(); i++) 
            { 
                System.out.println("==============================================================================================================================================================");
                System.out.println(" " + (i + 1));
                System.out.println("==============================================================================================================================================================");
                catalog.getBook(i);
                System.out.println("==============================================================================================================================================================\n");
            }


            System.out.println("\n\nChoose Book: (Enter \"back\" to return to prevous page)");
            chooseBook("1");
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

    // Method to search for a book by title
    public void searchByTitle() throws IOException, InterruptedException
    {
        String title;
        boolean flag, once = true;
        
        UtilitiesForSystem.clearScreen();
        System.out.println("==================");       
        System.out.println("SEARCH BY TITLE");
        System.out.println("==================");
        System.out.print("\n\nEnter Title: ");
        title = UtilitiesForSystem.reader.readLine();
        do
        {
            catalog.clearBookPos();
            catalog.resetSearchResultNo();
            UtilitiesForSystem.clearScreen();
            
            for (int i = 0; i < catalog.getBookListSize(); i++) 
            {
                if (catalog.getBookListTitle(i).toLowerCase().contains(title.toLowerCase())) /*
                                                                                                GeeksforGeeks. (2023, May 7). Arraylist.contains in Java. [Article]
                                                                                                https://www.geeksforgeeks.org/arraylist-contains-java/ (Date accessed: 25/10/2023)
                                                                                             */
                {
                    if(once)
                    {
                        System.out.println("SEARCH RESULTS");
                        System.out.println("==============================================================================================================================================================");
                        System.out.println("No.");
                    }
                    catalog.foundBook();
                    catalog.dispSearchResult(i);
                    catalog.setBookPos(i);
                    catalog.incrementSearchResultNo();
                }
                else if (i == (catalog.getBookListSize() - 1) && (catalog.getSearchResultNo() == 0))
                {
                    catalog.not_foundBook();
                }
            }

            if(catalog.isBookFound())
            {
                System.out.println("\n\nChoose Book: (Enter \"back\" to return to prevous page)");
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
            }
            else
            {
                System.out.println("Book not found.");
                TimeUnit.MILLISECONDS.sleep(1000);
                flag = true;
            }
        }while(!flag);
    }

    // Method to search for a book by author
    public void searchByAuthor() throws IOException, InterruptedException
    {
        String author;
        boolean flag, once = true;

        UtilitiesForSystem.clearScreen();
        System.out.println("==================");
        System.out.println("SEARCH BY AUTHOR"); 
        System.out.println("==================");
        System.out.print("\n\nEnter Author: ");
        author = UtilitiesForSystem.reader.readLine();
        do
        {
            catalog.clearBookPos();
            catalog.resetSearchResultNo();
            UtilitiesForSystem.clearScreen();
            
            for (int i = 0; i < catalog.getBookListSize(); i++) 
            {
                if ((catalog.getBookListAuthor(i).toLowerCase().contains(author.toLowerCase()))) 
                {
                    if(once)
                    {
                        System.out.println("SEARCH RESULTS");
                        System.out.println("==============================================================================================================================================================");
                        System.out.println("No.");
                        once = false;
                    }
                    catalog.foundBook();
                    catalog.dispSearchResult(i);
                    catalog.setBookPos(i);
                    catalog.incrementSearchResultNo();
                }
                else if(i == (catalog.getBookListSize() - 1) && (catalog.getSearchResultNo() == 0))
                {
                    catalog.not_foundBook();
                } 
            }

            if(catalog.isBookFound())
            {
                System.out.println("\n\nChoose Book: (Enter \"back\" to return to prevous page)");
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
            }
            else
            {
                System.out.println("Book not found.");
                TimeUnit.MILLISECONDS.sleep(1000);
                flag = true;
            }
        }while(!flag);
    }

    // Method to search for a book by publisher
    public void searchByPublisher() throws IOException, InterruptedException
    {
        String publisher;
        boolean flag, once = true;
 
        UtilitiesForSystem.clearScreen();
        System.out.println("==================");
        System.out.println("SEARCH BY PUBLISHER"); 
        System.out.println("==================");
        System.out.print("\n\nEnter Publisher: ");
        publisher = UtilitiesForSystem.reader.readLine();
        do
        {
            catalog.clearBookPos();   
            catalog.resetSearchResultNo();
            UtilitiesForSystem.clearScreen();
            
            for (int i = 0; i < catalog.getBookListSize(); i++) 
            {
                if (catalog.getBookListPublisher(i).toLowerCase().contains(publisher.toLowerCase())) 
                {
                    if(once)
                    {
                        System.out.println("SEARCH RESULTS");
                        System.out.println("==============================================================================================================================================================");
                        System.out.println("No.");
                        once = false;
                    }
                    catalog.foundBook();
                    catalog.dispSearchResult(i);
                    catalog.setBookPos(i);
                    catalog.incrementSearchResultNo();
                }
                else if(i == (catalog.getBookListSize() - 1) && (catalog.getSearchResultNo() == 0))
                {
                    catalog.not_foundBook();
                } 
            }

            if(catalog.isBookFound())
            {
                System.out.println("\n\nChoose Book: (Enter \"back\" to return to prevous page)");
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
            }
            else
            {
                System.out.println("Book not found.");
                TimeUnit.MILLISECONDS.sleep(1000);
                flag = true;
            }
        }while(!flag);
    }

    //Method to search for a book by ISBN
    public void searchByISBN() throws IOException, InterruptedException
    {
        String isbn;
        boolean flag, once = true;
        UtilitiesForSystem.clearScreen();
        System.out.println("==================");
        System.out.println("SEARCH BY ISBN"); 
        System.out.println("==================");
        System.out.print("\n\nEnter ISBN (ISBN-13 format): ");
        isbn = UtilitiesForSystem.reader.readLine();
        /*
            Java String startsWith() method - javatpoint. (n.d.). www.javatpoint.com. [Article]
            https://www.javatpoint.com/java-string-startswith (Date Accessed: 2/11/2023)

            Java String substring() method - javatpoint. (n.d.). www.javatpoint.com. [Article]
            https://www.javatpoint.com/java-string-substring (Date Accessed: 2/11/2023)
        */
        if ((isbn.length() == 13 && isbn.startsWith("978") && UtilitiesForSystem.allCharacterAreDigits(isbn)) || (isbn.length() == 14 && isbn.startsWith("978-") && UtilitiesForSystem.allCharacterAreDigits(isbn.replace("-", ""))))
        {
            if((isbn.length() == 13 && isbn.startsWith("978")))
            {
                isbn = isbn.substring(0, 3) + "-" + isbn.substring(3, 13);
            }
        }
        else
        {
            System.out.println("Invalid ISBN. ISBN must be in 13 digit format. \nExample 1: 978-1119803782\nExample 2: 9781119803782");
            UtilitiesForSystem.pressEnterToContinue();
            return;
        }
        
        do
        {
            catalog.clearBookPos();
            catalog.resetSearchResultNo();
            for (int i = 0; i < catalog.getBookListSize(); i++) 
            {
                if (catalog.getBookListIsbn(i).contains(isbn)) 
                {
                    if(once)
                    {
                        System.out.println("SEARCH RESULTS");
                        System.out.println("==============================================================================================================================================================");
                        System.out.println("No.");
                        once = false;
                    }
                    catalog.foundBook();
                    catalog.dispSearchResult(i);
                    catalog.setBookPos(i);
                    catalog.incrementSearchResultNo();
                }
                else if(i == (catalog.getBookListSize() - 1) && (catalog.getSearchResultNo() == 0))
                {
                    catalog.not_foundBook();
                } 
            }

            if(catalog.isBookFound())
            {
                System.out.println("\n\nChoose Book: (Enter \"back\" to return to prevous page)");
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
            }
            else
            {
                System.out.println("Book not found.");
                TimeUnit.MILLISECONDS.sleep(1000);
                flag = true;
            }
        }while(!flag);    
    }

    // Method to search for a book by genre
    public void searchByGenre() throws IOException, InterruptedException
    {
        String chooseGenre;
        int index = 0;
        boolean is_digit, flag, once = true;
        do
        {
            catalog.clearGenreTypes_inCatalog();
            UtilitiesForSystem.clearScreen();
            
            for(int i = 0; i < catalog.getBookListSize(); i++)
            {
                if(catalog.checkCatalogForGenre(i))
                {
                    catalog.addToGenreTypes_inCatalogSize(i);
                }
            }

            catalog.sortGenreTypes_inCatalog();
            do 
            { 
                UtilitiesForSystem.clearScreen();
                System.out.println("==================");
                System.out.println("SEARCH BY GENRE"); 
                System.out.println("==================");
                for(int i = 0; i < catalog.getGenreTypes_inCatalogSize(); i++)
                {
                    System.out.println((i + 1) + ". " + catalog.getGenreTypes_inCatalog(i));
                }
                System.out.print("\n\nChoose Genre: ");
                chooseGenre = UtilitiesForSystem.reader.readLine();
                is_digit = chooseGenre.chars().allMatch(Character::isDigit);

                if(is_digit)
                {
                    index = Integer.parseInt(chooseGenre) - 1;
                }
                
                if (index > (catalog.getGenreTypes_inCatalogSize() - 1) || index < 0)
                {
                    System.out.println("Invalid choice. Please try again.");
                    TimeUnit.MILLISECONDS.sleep(1000);
                }
                else
                {
                    break;
                }
            } while (!is_digit && (index > (catalog.getGenreTypes_inCatalogSize() - 1) || index < 0));

            if(is_digit && (index < catalog.getGenreTypes_inCatalogSize() && index >= 0))
            {
                do
                {
                    catalog.clearBookPos();
                    catalog.resetSearchResultNo();
                    UtilitiesForSystem.clearScreen();
                    System.out.println("SEARCH RESULTS");
                    System.out.println("==============================================================================================================================================================");
                    System.out.println("No.");
                    for (int i = 0; i < catalog.getBookListSize(); i++) 
                    {
                        if (catalog.getBookGenre(i).equals(catalog.getGenreTypes_inCatalog(index))) 
                        {
                            if(once)
                            {
                                System.out.println("SEARCH RESULTS");
                                System.out.println("==============================================================================================================================================================");
                                System.out.println("No.");
                                once = false;
                            }
                            catalog.foundBook();
                            catalog.dispSearchResult(i);
                            catalog.setBookPos(i);
                            catalog.incrementSearchResultNo();
                        }
                        else if (i == (catalog.getBookListSize() - 1) && (catalog.getSearchResultNo() == 0))
                        {
                            catalog.not_foundBook();
                        } 
                    }
                    
                    if(catalog.isBookFound())
                    {
                        System.out.println("\n\nChoose Book: (Enter \"back\" to return to prevous page)");
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
                    }
                    else
                    {
                        System.out.println("Book not found.");
                        TimeUnit.MILLISECONDS.sleep(1000);
                        flag = true;
                    }
                }while(!flag);
            }
            else
            {
                System.out.println("\nPlease choose from the available genres");
                TimeUnit.MILLISECONDS.sleep(1000);
            }  
        }while(index < catalog.getGenreTypes_inCatalogSize() && index >= 0 && catalog.getRejectChooseBook() == false); 
    } 

    public void dispOption_inBookPage(int i)
    {
        if(getUserType() == "HEAD LIBRARIAN")
        {
            System.out.println("\n\n\n1. Edit Book Title");
            System.out.println("2. Edit Author");
            System.out.println("3. Edit Publisher");
            System.out.println("4. Edit ISBN");
            System.out.println("5. Edit Genre");
            System.out.println("6. Edit Year Published");
            System.out.println("7. Back");
        }
        else
        {
            if(catalog.getBookListAvailability(i).equals("Available"))
            {
                System.out.println("\n\n\n1. Borrow Book");
            }
            else
            {
                System.out.println("\n\n\n1. Return Book");    
            }
            System.out.println("2. Back");
            System.out.print("\n\nSelection: ");
        }
    }

    public String toString()
    {
        return  "User Id: " + userId + "\nUser Name: " + userName  + "\nRole: " + userType;
    }
}
