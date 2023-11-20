public interface BookData //Edwin
{
    public final String[] BOOK_TITLE = {"Systems Analysis and Design, 8th Edition", "Java Software Solutions, Foundations of Program Design, 9th edition", "Entrepreneurship, 11th Edition", 
                                       "ISE Foundations of Financial Management, 18th Edition", 
                                       "Introduction to Game Design, Prototyping, and Development: From Concept to Playable Game with Unity and C#, 2nd edition",
                                       "The Game Production Toolbox, 1st Edition", "Theories of Human Communication, Eleventh Edition",
                                       "Video Games, Violence, and the Ethics of Fantasy: Killing Time", "The Wager: A Tale of Shipwreck, Mutiny and Murder", 
                                       "The Nazi Conspiracy: The Secret Plot to Kill Roosevelt, Stalin, and Churchill"
                                      };

    public final String[] ISBN = {"978-1119803782", "978-0134462028", "978-1260043730", "978-1265074654", "978-0134659862", "978-1138341708", "978-1478634058", 
                                  "978-1350121874", "978-0385534260", "978-1250777263"};

    public final String[] GENRE = {"IT & Software", "IT & Software", "Finance", "Finance", "Game Development", "Game Development", "Philosophy", "Philosophy", "History", "History"};

    public final String[] AUTHOR = {"Alan Dennis, Barbara Wixom, Roberta M. Roth", "John Lewis, William Loftus",
                                    "Robert Hisrich, Michael Peters, Dean Shepherd", "Stanley B. Block, Geoffrey A. Hirt, Bartley Danielsen", "Jeremy Gibson Bond",
                                    "Heather Chandler", "Stephen W. Littlejohn, Karen A. Foss, John G. Oetzel", "Christopher Bartel", "David Grann", "Brad Meltzer, Josh Mensch"};

    public final String[] PUBLISHER = {"Wiley", "Pearson", "McGraw Hill", "McGraw Hill", "Addison-Wesley Professional", "CRC Press", "Waveland Press, Inc.", 
                                       "Bloomsbury Academic", "Doubleday", "Flatiron Books"};

    public final int[] YEAR_PUBLISHED = {2021, 2017, 2019, 2022, 2017, 2020, 2016, 2020, 2023, 2023};

    public final String[] BOOK_AVAILABILITY = {"Available", "Borrowed"};

    public void setBookList(int i);
}
