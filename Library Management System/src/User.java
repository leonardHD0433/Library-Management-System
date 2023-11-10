
import java.io.*;
import java.util.concurrent.*;

public class User extends UtilitiesForSystem
{
    Catalog catalog;
    protected String userType, userName, userId, password, readUserId, readPassword;
    

    public User(String userType, String userName, String userId, String password) //Composition
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

    public boolean login() throws IOException, InterruptedException
    {
        System.out.println("\n\nUser ID: ");
        readUserId = UtilitiesForSystem.reader.readLine();
        System.out.println("\nPassword: ");
        readPassword = UtilitiesForSystem.reader.readLine();

        if(readUserId.equals(userId) && readPassword.equals(password))
        { 
            System.out.println("Login Successful");
            TimeUnit.MILLISECONDS.sleep(500);
            clearScreen();
            return true;
        }
        else
        {   
            System.out.println("Invalid user Id or password. Please try again");
            TimeUnit.MILLISECONDS.sleep(500);
            clearScreen();
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
                System.out.println("Invalid choice. Please try again.");
                TimeUnit.MILLISECONDS.sleep(500);
                clearScreen();
        }

        return backToHomePage;
    }

    public void chooseBook(String chosenFilter) throws IOException, InterruptedException, IndexOutOfBoundsException
    {
        String chooseBook;
        int chosenIndex;
        do
        {
            chooseBook = UtilitiesForSystem.reader.readLine().toLowerCase();
            if(!UtilitiesForSystem.allCharacterAreDigits(chooseBook) && !chooseBook.equals("back"))
            {
                System.out.println("Please enter a digit.");
                TimeUnit.MILLISECONDS.sleep(500);
                clearScreen();
            }
        }while(!UtilitiesForSystem.allCharacterAreDigits(chooseBook) && !chooseBook.equals("back"));

        if(chooseBook.equals("back"))
        {
            return;
        }
        else
        {
            chosenIndex = Integer.parseInt(chooseBook) - 1;
            switch (chosenFilter) 
            {
                case "1":
                    //choose book from original list
                    if(chosenIndex >= 0 && chosenIndex < catalog.getBookListSize())
                    {
                        System.out.println("Book chosen:\n " + catalog.getBookList(chosenIndex));
                    }
                    else
                    {
                        System.out.println("Invalid choice. Please try again.");
                        TimeUnit.MILLISECONDS.sleep(500);
                        clearScreen();
                    }
                    break;
            
                default:
                    //choose book from filtered list
                    if(chosenIndex >= 0 && chosenIndex < catalog.bookPos_forFilteredListSize())
                    {
                        System.out.println(catalog.getBookList(catalog.getBookPos(chosenIndex)));
                    }
                    else
                    {
                        System.out.println("Invalid choice. Please try again.");
                        TimeUnit.MILLISECONDS.sleep(500);
                        clearScreen();
                    }
                    break;
            }   
        }
    }

    // Method to display all books in the catalog in ascending order of book title
    public void viewAll() throws IOException, InterruptedException
    {
        catalog.sortBookList();
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
    }

    // Method to search for a book by title
    public void searchByTitle() throws IOException, InterruptedException
    {
        String title;
        catalog.resetSearchResultNo();
        catalog.clearBookPos();
        do
        {
            UtilitiesForSystem.clearScreen();
            System.out.println("==================");       
            System.out.println("SEARCH BY TITLE");
            System.out.println("==================");
            System.out.print("\n\nEnter Title: ");
            title = UtilitiesForSystem.reader.readLine();
            System.out.println("SEARCH RESULTS");
            System.out.println("==============================================================================================================================================================");
            System.out.println("No.");
            for (int i = 0; i < catalog.getBookListSize(); i++) 
            {
                if (catalog.getBookListTitle(i).toLowerCase().contains(title.toLowerCase())) 
                {
                    catalog.foundBook();
                    catalog.dispSearchResult(i);
                    catalog.setBookPos(i);

                }
                else if (i == (catalog.getBookListSize()) && (catalog.getSearchResultNo() == 0))
                {
                    catalog.not_foundBook();
                }

                if(!catalog.isBookFound())
                {
                    System.out.println("Book not found.");
                }
            }
        }while(!catalog.isBookFound());
        System.out.println("\n\nChoose Book: (Enter \"back\" to return to prevous page)");
        chooseBook("0");
    }

    // Method to search for a book by author
    public void searchByAuthor() throws IOException, InterruptedException
    {
        String author;
        catalog.resetSearchResultNo();
        catalog.clearBookPos();
        do
        {
            UtilitiesForSystem.clearScreen();
            System.out.println("==================");
            System.out.println("SEARCH BY AUTHOR"); 
            System.out.println("==================");
            System.out.print("\n\nEnter Author: ");
            author = UtilitiesForSystem.reader.readLine();
            System.out.println("SEARCH RESULTS");
            System.out.println("==============================================================================================================================================================");
            System.out.println("No.");
            for (int i = 0; i < catalog.getBookListSize(); i++) 
            {
                if ((catalog.getBookListAuthor(i).toLowerCase().contains(author.toLowerCase()))) 
                {
                    catalog.foundBook();
                    catalog.dispSearchResult(i);
                    catalog.setBookPos(i);
                }
                else if(i == catalog.getBookListSize() && (catalog.getSearchResultNo() == 0))
                {
                    catalog.not_foundBook();
                } 
            }

            if(!catalog.isBookFound())
            {
                System.out.println("Book not found.");
            }
        }while(!catalog.isBookFound());
        System.out.println("\n\nChoose Book: (Enter \"back\" to return to prevous page)");
        chooseBook("0");
    }

    // Method to search for a book by publisher
    public void searchByPublisher() throws IOException, InterruptedException
    {
        String publisher;
        catalog.resetSearchResultNo();
        catalog.clearBookPos();
        UtilitiesForSystem.clearScreen();
        do
        {
            System.out.println("==================");
            System.out.println("SEARCH BY PUBLISHER"); 
            System.out.println("==================");
            System.out.print("\n\nEnter Publisher: ");
            publisher = UtilitiesForSystem.reader.readLine();
            System.out.println("SEARCH RESULTS");
            System.out.println("==============================================================================================================================================================");
            System.out.println("No.");
            for (int i = 0; i < catalog.getBookListSize(); i++) 
            {
                if (catalog.getBookListPublisher(i).toLowerCase().contains(publisher.toLowerCase())) 
                {
                    catalog.foundBook();
                    catalog.dispSearchResult(i);
                    catalog.setBookPos(i);
                }
                else if(i == (catalog.getBookListSize()) && (catalog.getSearchResultNo() == 0))
                {
                    catalog.not_foundBook();
                } 
            }

            if(!catalog.isBookFound())
            {
                System.out.println("Book not found.");
            }
        }while(!catalog.isBookFound());
        System.out.println("\n\nChoose Book: (Enter \"back\" to return to prevous page)");
        chooseBook("0");
    }

    //Method to search for a book by ISBN
    public void searchByISBN() throws IOException, InterruptedException
    {
        String isbn = null;
        catalog.resetSearchResultNo();
        catalog.clearBookPos();
        UtilitiesForSystem.clearScreen();
        do
        {
            while (isbn.equals(null)) 
            {
                UtilitiesForSystem.clearScreen();
                System.out.println("==================");
                System.out.println("SEARCH BY ISBN"); 
                System.out.println("==================");
                System.out.print("\n\nEnter ISBN (ISBN-13 format): ");
                isbn = UtilitiesForSystem.reader.readLine();
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
                    isbn = null;
                }
            }
            
            System.out.println("SEARCH RESULTS");
            System.out.println("==============================================================================================================================================================");
            System.out.println("No.");
            for (int i = 0; i < catalog.getBookListSize(); i++) 
            {
                if (catalog.getBookListIsbn(i).contains(isbn)) 
                {
                    catalog.foundBook();
                    catalog.dispSearchResult(i);
                }
                else if(i == (catalog.getBookListSize()) && (catalog.getSearchResultNo() == 0))
                {
                    catalog.not_foundBook();
                } 
            }

            if(!catalog.isBookFound())
            {
                System.out.println("Book not found.");
            }
        }while(!catalog.isBookFound());
        System.out.println("\n\nChoose Book: (Enter \"back\" to return to prevous page)");
        chooseBook("0");
    }

    // Method to search for a book by genre
    public void searchByGenre() throws IOException, InterruptedException
    {
        String chooseGenre;
        int index = 0;
        boolean is_digit;
        catalog.clearBookPos();
        UtilitiesForSystem.clearScreen();
        
        for(int i = 0; i < catalog.getBookListSize(); i++)
        {
            if(!catalog.checkCatalogForGenre(i))
            {
                catalog.addToGenreTypes_inCatalogSize(i);
            }
        }

        catalog.sortGenreTypes_inCatalog();

        catalog.clearBookPos();
        
        do{
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
                if(!is_digit)
                {
                    System.out.println("Please enter a digit.");
                }
                else
                {
                    index = Integer.parseInt(chooseGenre) - 1;
                }
                
                if (index > (catalog.getGenreTypes_inCatalogSize() - 1) || index < 0)
                {
                    System.out.println("Invalid choice. Please try again.");
                }
                else
                {
                    break;
                }
            } while (!is_digit);

            if(is_digit && (index < catalog.getGenreTypes_inCatalogSize() && index >= 0))
            {
                System.out.println("SEARCH RESULTS");
                System.out.println("==============================================================================================================================================================");
                System.out.println("No.");
                for (int i = 0; i < catalog.getBookListSize(); i++) 
                {
                    if (catalog.getBookGenre(i).equals(catalog.getGenreTypes_inCatalog(index))) 
                    {
                        catalog.foundBook();
                        catalog.dispSearchResult(i);
                    }
                    else if (i == (catalog.getBookListSize()) && (catalog.getSearchResultNo() == 0))
                    {
                        catalog.not_foundBook();
                    } 
                }  
            }

            if(!catalog.isBookFound())
            {
                System.out.println("Book not found.");
            }
        }while(!catalog.isBookFound());
        System.out.println("\n\nChoose Book: (Enter \"back\" to return to prevous page)");
        chooseBook("0");
    }

    public String toString()
    {
        return  "User Id: " + userId + "\nUser Name: " + userName  + "\nRole: " + userType;
    }


}
