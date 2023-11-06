import java.util.regex.Pattern;
public class test extends UtilitiesForSystem
{

    public static void main(String[] args) {
        // Catalog catalog = new Catalog();
        // catalog.initialiseCatalog();
        // catalog.viewAll();
        String isbn;
        isbn = "1121119803782";
        if ((isbn.length() == 13 && isbn.startsWith("978") && UtilitiesForSystem.allCharacterAreDigits(isbn)) || (isbn.length() == 14 && isbn.startsWith("978-") && UtilitiesForSystem.allCharacterAreDigits(isbn.replace("-", ""))))
        {
            if((isbn.length() == 13 && isbn.startsWith("978")))
            {
                isbn = isbn.substring(0, 3) + "-" + isbn.substring(3, 13);
            }
            System.out.println(isbn);
        }
        else
        {
            System.out.println("Invalid ISBN");
        }
    }
}
