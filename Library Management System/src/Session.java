import java.io.*;

public class Session
{
    private HeadLibrarian headLibrarian;
    private Librarian librarian;
    private String loginPageSelection, homePageSelection, manageCatalogSelection, browseCatalogSelection, borrow_returnSelection;
    private boolean isLoginSuccessful;

    public Session() throws IOException, ClassNotFoundException
    {
        UtilitiesForSystem.fileName();
        UtilitiesForSystem.setFile();
        headLibrarian = new HeadLibrarian("HEAD LIBRARIAN", "Edwin Chua Jin Rui", "headStaff@001", "1234");
        librarian = new Librarian("LIBRARIAN", "Teh Yu Kang", "staff@002", "12345");
    }

    public boolean loginPage() throws IOException, InterruptedException, ClassNotFoundException
    {
        boolean exit = false;
        do 
        {
            dispLoginPage();
            setLoginPageSelection();
            exit = loginPageSelection(getLoginPageSelection());
        } while (!((getLoginPageSelection().equals("1") || getLoginPageSelection().equals("2")) && isLoginSuccessful) &&  !(getLoginPageSelection().equals("3")));

        if(isLoginSuccessful)
        {
            homePage();
        }

        return exit;
    }

    // Home Page State
    public void homePage() throws IOException, InterruptedException, ClassNotFoundException
    {
        boolean backToLoginPage = false;

        do 
        {
            backToLoginPage = false;
            UtilitiesForSystem.clearScreen();
            dispHomePage();
            setHomePageSelection();
            backToLoginPage = homePageSelection(getHomePageSelection());
        } while (!backToLoginPage);
    }

    // Login Page Selection Methods
    public boolean loginPageSelection(String selection) throws IOException, InterruptedException
    {
        boolean exit = false;

        switch (selection) 
        {
            case "1":
                UtilitiesForSystem.clearScreen();
                System.out.println("========================");
                System.out.println("| HEAD LIBRARIAN LOGIN |");
                System.out.println("========================\n");
                isLoginSuccessful = headLibrarian.login();
                break;
        
            case "2": 
                UtilitiesForSystem.clearScreen();
                System.out.println("========================");
                System.out.println("|    LIBRARIAN LOGIN   |");
                System.out.println("========================\n");
                isLoginSuccessful = librarian.login();
                break;

            case "3":
                isLoginSuccessful = false;
                exit = true;
                break;

            default:
                isLoginSuccessful = false;
                UtilitiesForSystem.selectionErrorMsg();
                break;
        }

        return exit;
    }

    // Setter for the Login Page Selection
    public void setLoginPageSelection() throws IOException
    {
        loginPageSelection = UtilitiesForSystem.reader.readLine();
    }

    // Getter for the Login Page Selection
    public String getLoginPageSelection()
    {
        return loginPageSelection;
    }

    // Home Page Selection Methods
    public boolean homePageSelection(String selection) throws InterruptedException, IOException, ClassNotFoundException
    {
        boolean backToLoginPage = false, backToHomePage = false;

        if(getLoginPageSelection().equals("1")) 
        {
            switch (selection) 
            {
                case "1":
                    do
                    {
                        dispManageCatalog();
                        setManageCatalogSelection();
                        backToHomePage = headLibrarian.manageCatalog(getManageCatalogSelection());
                    }while(!backToHomePage);
                    break;
            
                case "2":
                    logout("1");
                    backToLoginPage = true; break;

                default:
                    UtilitiesForSystem.selectionErrorMsg(); break;
            }
        }
        else
        {
            switch (selection) 
            {
                case "1":
                    do
                    {
                        dispBrowseCatalog();
                        setBrowseCatalogSelection();
                        backToHomePage = librarian.browseCatalog(getBrowseCatalogSelection());
                    }while(!backToHomePage);
                    break;
            
                case "2": 
                    do
                    {
                        backToHomePage = librarian.viewPatron();
                    }while(!backToHomePage);
                    break;

                case "3":
                    logout("2");
                    backToLoginPage = true; break;

                default:
                    UtilitiesForSystem.selectionErrorMsg(); break;
            }
        }

        return backToLoginPage;
    }

    // Setter for the Home Page Selection
    public void setHomePageSelection() throws IOException
    {
        homePageSelection = UtilitiesForSystem.reader.readLine();
    }

    // Getter for the Home Page Selection
    public String getHomePageSelection()
    {
        return homePageSelection;
    }

    // Setter for the Browse Catalog Selection
    public void setBrowseCatalogSelection() throws IOException
    {
        browseCatalogSelection = UtilitiesForSystem.reader.readLine();
    }

    // Getter for the Browse Catalog Selection
    public String getBrowseCatalogSelection()
    {
        return browseCatalogSelection;
    }

    public void setBorrowReturnSelection() throws IOException
    {
        borrow_returnSelection = UtilitiesForSystem.reader.readLine();
    }

    public String getBorrowReturnSelection()
    {
        return borrow_returnSelection;
    }

    //setter for manage catalog selection
    public void setManageCatalogSelection() throws IOException
    {
        manageCatalogSelection = UtilitiesForSystem.reader.readLine();
    }

    //getter for manage catalog selection
    public String getManageCatalogSelection()
    {
        return manageCatalogSelection;
    }
  
    //FILE PROCESSING

    public void logout(String userSession) throws IOException, InterruptedException, ClassNotFoundException//save sessions and data updates using files
    {
        if(UtilitiesForSystem.checkIfFilesExist())
        {
            UtilitiesForSystem.deleteFiles(getArchiveListSizeFromCatalog(userSession));
        }
        UtilitiesForSystem.createFiles();
        UtilitiesForSystem.setWriter();
        saveBookTitle(userSession);
        saveBookIsbn(userSession);
        saveBookAuthor(userSession);
        saveBookPublisher(userSession);
        saveBookYearPublished(userSession);
        saveBookGenre(userSession);
        saveBookAvailability(userSession);
        saveLoanList();
        saveArchiveListSize(getArchiveListSizeFromCatalog(userSession));
        if(getArchiveListSizeFromCatalog(userSession) > 0)
        {
            saveArchiveList();
        }
    }

    public void saveBookTitle(String userSession) throws IOException
    {
        if(userSession.equals("1"))
        {
            for (int i = 0; i < headLibrarian.catalog.getBookListSize(); i++) 
            {
                UtilitiesForSystem.writeToFile(0, headLibrarian.catalog.getBookListTitle(i));
            }
        }
        else if (userSession.equals("2"))
        {
            for (int i = 0; i < librarian.catalog.getBookListSize(); i++) 
            {
                UtilitiesForSystem.writeToFile(0, librarian.catalog.getBookListTitle(i));
            }
        }
        UtilitiesForSystem.writer[0].close();
    }

    public void saveBookIsbn(String userSession) throws IOException
    {
        if(userSession.equals("1"))
        {
            for (int i = 0; i < headLibrarian.catalog.getBookListSize(); i++) 
            {
                UtilitiesForSystem.writeToFile(1, headLibrarian.catalog.getBookListIsbn(i));
            }
        }
        else if (userSession.equals("2"))
        {
            for (int i = 0; i < librarian.catalog.getBookListSize(); i++) 
            {
                UtilitiesForSystem.writeToFile(1, librarian.catalog.getBookListIsbn(i));
            }
        }
        UtilitiesForSystem.writer[1].close();
    }

    public void saveBookAuthor(String userSession) throws IOException
    {
        if(userSession.equals("1"))
        {
            for (int i = 0; i < headLibrarian.catalog.getBookListSize(); i++) 
            {
                UtilitiesForSystem.writeToFile(2, headLibrarian.catalog.getBookListAuthor(i));
            }
        }
        else if (userSession.equals("2"))
        {
            for (int i = 0; i < librarian.catalog.getBookListSize(); i++) 
            {
                UtilitiesForSystem.writeToFile(2, librarian.catalog.getBookListAuthor(i));
            }
        }
        UtilitiesForSystem.writer[2].close();
    }

    public void saveBookPublisher(String userSession) throws IOException
    {
        if(userSession.equals("1"))
        {
            for (int i = 0; i < headLibrarian.catalog.getBookListSize(); i++) 
            {
                UtilitiesForSystem.writeToFile(3, headLibrarian.catalog.getBookListPublisher(i));
            }
        }
        else if (userSession.equals("2"))
        {
            for (int i = 0; i < librarian.catalog.getBookListSize(); i++) 
            {
                UtilitiesForSystem.writeToFile(3, librarian.catalog.getBookListPublisher(i));
            }
        }
        UtilitiesForSystem.writer[3].close();
    }

    public void saveBookYearPublished(String userSession) throws IOException
    {
        String str;
        if(userSession.equals("1"))
        {
            for (int i = 0; i < headLibrarian.catalog.getBookListSize(); i++) 
            {
                str = Integer.toString(headLibrarian.catalog.getBookListYearPublished(i));
                UtilitiesForSystem.writeToFile(4, str);
            }
        }
        else if (userSession.equals("2"))
        {
            for (int i = 0; i < librarian.catalog.getBookListSize(); i++) 
            {
                str = Integer.toString(librarian.catalog.getBookListYearPublished(i));
                UtilitiesForSystem.writeToFile(4, str);
            }
        }
        UtilitiesForSystem.writer[4].close();
    }

    public void saveBookGenre(String userSession) throws IOException
    {
        if(userSession.equals("1"))
        {
            for (int i = 0; i < headLibrarian.catalog.getBookListSize(); i++) 
            {
                UtilitiesForSystem.writeToFile(5, headLibrarian.catalog.getBookGenre(i));
            }
        }
        else if (userSession.equals("2"))
        {
            for (int i = 0; i < librarian.catalog.getBookListSize(); i++) 
            {
                UtilitiesForSystem.writeToFile(5, librarian.catalog.getBookGenre(i));
            }
        }
        UtilitiesForSystem.writer[5].close();
    }

    public void saveBookAvailability(String userSession) throws IOException
    {
        if(userSession.equals("1"))
        {
            for (int i = 0; i < headLibrarian.catalog.getBookListSize(); i++) 
            {
                UtilitiesForSystem.writeToFile(6, headLibrarian.catalog.getBookListAvailability(i));
            }
        }
        else if (userSession.equals("2"))
        {
            for (int i = 0; i < librarian.catalog.getBookListSize(); i++) 
            {
                UtilitiesForSystem.writeToFile(6, librarian.catalog.getBookListAvailability(i));
            }
        }
        UtilitiesForSystem.writer[6].close();
    }

    public void saveLoanList() throws IOException, ClassNotFoundException
    {
        UtilitiesForSystem.writeLoanListToFile(librarian.loanList);
    }

    public void saveArchiveListSize(int size) throws IOException
    {
        UtilitiesForSystem.storeArchiveListSize(size);
    }

    public void saveArchiveList() throws IOException
    {
        UtilitiesForSystem.writeArchiveToFile(headLibrarian.catalog.archiveList);
    }

    public int getArchiveListSizeFromCatalog(String userSession)
    {
        if(userSession.equals("1"))
        {
            return headLibrarian.catalog.getArchiveListSize();
        }
        else
        {
            return librarian.catalog.getArchiveListSize();
        }
    }

//----------------------------------------------------//
//                    DISPLAY METHODS                 //

    // Display Login Page
    public void dispLoginPage()
    {
        System.out.println("LIBRARY MANAGEMENT SYSTEM");
        System.out.println("==========================");
        System.out.println("1. Login as Head Librarian");
        System.out.println("2. Login as Librarian");
        System.out.println("3. Exit\n");
        System.out.print("Selection: ");
    }

    // Display Home Page
    public void dispHomePage()
    {
        switch (getLoginPageSelection()) 
        {
            case "1":
                System.out.println(headLibrarian + "\n\n\n\n");
                System.out.println("1. Manage Catalog");
                System.out.println("2. Logout\n");
                System.out.println("Selection: ");
                break;
        
            default:
                System.out.println(librarian + "\n\n\n\n");
                System.out.println("1. Browse Catalog");
                System.out.println("2. View Patron");
                System.out.println("3. Logout\n");
                System.out.println("Selection: ");
        }
    }

    public void dispBrowseCatalog()
    {
        UtilitiesForSystem.clearScreen();
        System.out.println(librarian + "\n\n\n\n");
        System.out.println("1. View All");
        System.out.println("2. Browse by Genre");
        System.out.println("3. Browse by Title");
        System.out.println("4. Browse by Author");
        System.out.println("5. Browse by Publisher");
        System.out.println("6. Browse by ISBN");
        System.out.println("7. Back\n");
        System.out.println("Selection: ");
    }

    public void dispManageCatalog()
    {
        UtilitiesForSystem.clearScreen();
        System.out.println(headLibrarian + "\n\n\n\n");
        System.out.println("1. Add Book");
        System.out.println("2. Edit Book");
        System.out.println("3. Remove Book");
        System.out.println("4. Back\n");
        System.out.println("Selection: ");
    }

    public void dispBorrowReturn()
    {
        System.out.println(librarian + "\n\n\n\n");
        System.out.println("1. Borrow Book");
        System.out.println("2. Return Book");
        System.out.println("3. Back\n");
        System.out.println("Selection: ");
    }
}
