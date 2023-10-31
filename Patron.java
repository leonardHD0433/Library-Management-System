public class Patron implements PersonalData
{
    protected String patronName, patronId, contactNumber;

    public Patron(int i)
    {
        this.patronName = PatronName[i];
        this.patronId = PatronName[i];
        this.contactNumber = PatronContactNumber[i];
    }

    public String toString()
    {
        return "User Name: " + patronName + "\n\nUser Id: " + patronId + "\n\nContact Number: " + contactNumber;
    }
    
}
