import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class UtilitiesForSystem 
{
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static Scanner[] readFile = new Scanner[7];
    public static FileWriter[] writer = new FileWriter[7];
    public static File[] file = new File[7];
    public static ArrayList <String> fileName;

    public static void clearScreen()
    {
        for (int i = 0; i < 10; i++) 
        {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    public static void selectionErrorMsg() throws InterruptedException
    {
        System.out.println("Invalid Selection. Try again.");
        TimeUnit.MILLISECONDS.sleep(500);
        clearScreen();
    }

    public static boolean allCharacterAreDigits(String str)  /*
                                                                (Asked by: Craig Angus) (Answered by: Ibrahim Arief) How to check if a String is numeric in Java. (n.d.). Stack Overflow. [Source Code]
                                                                https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java (Date Accessed: 25/10/2023)
                                                             */
    {
        if(!str.isBlank())
        {
            for(int i = 0; i < str.length(); i++)
            {
                if(!Character.isDigit(str.charAt(i)))
                {
                    return false;
                }
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    
    // This method checks to see if the user input has any digits! - [Shaun]
    public static boolean containsDigits(String input) {
        // Check if the input string contains digits
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                return true; // Digits found
            }
        }
        return false; // No digits found
    }

    public static void pressEnterToContinue() throws IOException /*
                                                                    (Asked by: user2840682) (Answered by: E235) How do I get “Press any key to continue” to work in my Java code? (n.d.). Stack Overflow. [Source Code]
                                                                    https://stackoverflow.com/questions/19870467/how-do-i-get-press-any-key-to-continue-to-work-in-my-java-code (Date Accessed: 9/11/2023)
                                                                 */
    { 
        System.out.println("Press any key to continue...");
        reader.readLine();
    }

    public static void fileName()
    {
        fileName = new ArrayList<String>();
        fileName.add("BookTitle.txt");           //0
        fileName.add("BookISBN.txt");            //1
        fileName.add("BookAuthor.txt");          //2
        fileName.add("BookPublisher.txt");       //3
        fileName.add("BookYearPublished.txt");   //4
        fileName.add("BookGenre.txt");           //5
        fileName.add("BookAvailability.txt");    //6
    }

    public static String getFileName(int i)
    {
        return fileName.get(i);
    }

    public static void setFile() throws IOException
    {
        for(int i = 0; i < fileName.size(); i++)
        {
            file[i] = new File(getFileName(i));
        }
    }

    public static void setWriter() throws IOException /*
                                                        (Asked by: Milly) (Answered by: Joachim Sauer) Is it ok to use same file writer for writing different files. (n.d.). Stack Overflow. [Source Code]
                                                        https://stackoverflow.com/questions/1075278/is-it-ok-to-use-same-file-writer-for-writing-different-files (Date Accessed: 13/11/2023)
                                                      */
    {
        for(int i = 0; i < fileName.size(); i++)
        {
            writer[i] = new FileWriter(file[i]);
        }
    }

    public static void setReader() throws IOException
    {
        for(int i = 0; i < fileName.size(); i++)
        {
            readFile[i] = new Scanner(new FileReader(file[i]));
        }
    }

    public static void closeReader() throws IOException
    {
        for(int i = 0; i < fileName.size(); i++)
        {
            readFile[i].close();
        }
    }

    public static void createFiles() throws IOException, InterruptedException
    {
        System.out.println("Backing up........");
        TimeUnit.MILLISECONDS.sleep(1000);
        for(int i = 0; i < fileName.size(); i++)
        {
            file[i].createNewFile();
            if(file[i].exists())
            {
                System.out.println("File created: " + file[i].getName());
            }
        }
    }

    public static void deleteFiles(int archiveListSize) throws IOException
    {
        for(int i = 0; i < fileName.size(); i++)
        {
            file[i].delete();
            if(!(i > 4))
            {
                File loan = new File("loan[" + (i+1) + "].txt" );
                loan.delete();
            }
        }

        if(archiveListSize != 0)
        {
            for(int i = 1; i <= archiveListSize; i++)
            {
                File archive = new File("archive" + i + ".txt");
                archive.delete();
            }
        }
    }

    public static void writeToFile(int i, String str) throws IOException /*
                                                                            (Asked by: Ricky) (Answered by: Andrey Adamovich) How to write an ArrayList of Strings into a text file? (n.d.). Stack Overflow. [Source Code]
                                                                            https://stackoverflow.com/questions/6548157/how-to-write-an-arraylist-of-strings-into-a-text-file (Date Accessed: 13/11/2023)

                                                                            Paraschiv, E., & Paraschiv, E. (2023, October 18). Java - Write to file | Baeldung. Baeldung. [Article]
                                                                            https://www.baeldung.com/java-write-to-file (Date Accessed: 13/11/2023)
                                                                         */
    {
        writer[i].write(str + System.lineSeparator());
    }

    public static void closeFile() throws IOException
    {
        for (int i = 0; i < fileName.size(); i++) 
        {
            writer[i].close();
        }   
    }

    //check if files exist
    public static boolean checkIfFilesExist() throws IOException
    {
        for(int i = 0; i < fileName.size(); i++)
        {
            if(!file[i].exists())
            {
                return false;
            }
        }
        return true;
    }

    public static void writeLoanListToFile(Loan[] loanList) throws IOException, ClassNotFoundException
    {
        FileOutputStream fos;
        ObjectOutputStream oos;
        int count = 1;
        for (Loan loan : loanList) 
        {
            try 
            {
                fos = new FileOutputStream("loan[" + count + "].txt");
                oos = new ObjectOutputStream(fos);
                oos.writeObject(loan);
                oos.close();
            }
            finally
            {
                count++;
            }
        } 
    }

    public static Loan readObjectFromFile(int count) throws IOException, ClassNotFoundException
    {
        FileInputStream fis;
        ObjectInputStream ois;
        Loan loan;
        fis = new FileInputStream("loan[" + count + "].txt");
        ois = new ObjectInputStream(fis);
        loan = (Loan) ois.readObject();
        ois.close();
        return loan;        
    }

    public static void storeArchiveListSize(int archiveListSize) throws IOException
    {
        FileOutputStream fos = new FileOutputStream("archiveListSize.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(archiveListSize);
        oos.close();
    }

    /*
        Sam. (2018, November 26). Write and read an ArrayList object to a file in Java - Samderlust. Samderlust. [Article]
        https://samderlust.com/dev-blog/java/write-read-arraylist-object-file-java (Date Accessed: 14/11/2023)

        (Asked by: kevin lee) (Answered by: user207421) Writing an ArrayList of Objects to a file. (n.d.). Stack Overflow. [Source Code]
        https://stackoverflow.com/questions/27631183/writing-an-arraylist-of-objects-to-a-file (Date Accessed: 14/11/2023)

        (Asked by: user3435407) (Answered by: MitchAman) java - Reading multiple objects from a file, as they were in an array. (n.d.). Stack Overflow. [Source Code]
        https://stackoverflow.com/questions/27409718/java-reading-multiple-objects-from-a-file-as-they-were-in-an-array (Date Accessed: 18/11/2023)

        What is transient variable in Java? Serialization Example. (n.d.). Java67. [Article]
        https://www.java67.com/2012/08/what-is-transient-variable-in-java.html#:~:text=transient%20variable%20in%20Java%20is,variable%20it%20would%20be%20null. (Date Accessed: 18/11/2023)
     */
    
    public static int readArchiveListSize() throws IOException, ClassNotFoundException
    {
        FileInputStream fis = new FileInputStream("archiveListSize.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        int archiveListSize = (int) ois.readObject();
        ois.close();
        return archiveListSize;
    }

    public static void writeArchiveToFile(ArrayList <Book> archiveList) throws IOException
    {
        int count = 1;
        for (Book book : archiveList) 
        {
            try
            {
                FileOutputStream fos = new FileOutputStream("archive" + count + ".txt");  
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(book);
                oos.close();
            }
            finally
            {
                count++;
            }
        }
    }

    public static ArrayList<Book> readArchiveFromFile() throws IOException, ClassNotFoundException
    {
        ArrayList<Book> archiveList = new ArrayList<>();
        
        for (int i = 0; i < UtilitiesForSystem.readArchiveListSize(); i++) 
        {
            FileInputStream fis = new FileInputStream("archive" + (i + 1) + ".txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Book book = (Book) ois.readObject();
            ois.close();
            archiveList.add(book);
        }
        
        return archiveList;       
    }
}

