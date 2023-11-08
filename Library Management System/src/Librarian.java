import java.io.IOException;

public class Librarian extends User 
{
    public Librarian(String userType, String userName, String userId, String password) //Composition
    {
        super(userType, userName, userId, password);
    }

    public void viewPatron()
    {

    }

    // Method to display all books in the catalog in ascending order of book title
    public void viewAll() 
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
    }

    // Method to search for a book by title
    public void searchByTitle() throws IOException
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
    }

    // Method to search for a book by author
    public void searchByAuthor() throws IOException
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
    }

    // Method to search for a book by publisher
    public void searchByPublisher() throws IOException
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
    }

    //Method to search for a book by ISBN
    public void searchByISBN() throws IOException
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
                }
            }
            
            System.out.println("SEARCH RESULTS");
            System.out.println("==============================================================================================================================================================");
            System.out.println("No.");
            for (int i = 0; i < bookList.size(); i++) 
            {
                if (bookList.get(i).getIsbn().contains(isbn)) 
                {
                    dispSearchResult(i);
                }
                else if(i == (bookList.size()) && (searchResultNo == 1))
                {
                    found = false;
                } 
            }

            if(!found)
            {
                System.out.println("Book not found.");
            }
        }while(!found);
    }
}
