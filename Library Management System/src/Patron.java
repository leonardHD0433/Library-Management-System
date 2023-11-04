public class Patron implements PatronData
{
    protected String patronName, patronId, contactNumber;

    public Patron(int i)
    {
        setPatronDetail(i);
    }

    public void setPatronDetail(int i)
    {
        patronName = PATRON_NAME[i];
        patronId = PATRON_ID[i];
        this.contactNumber = PATRON_CONTACT_NO[i];
    }

    public String toString()
    {
        return "User Name: " + patronName + "\n\nUser Id: " + patronId + "\n\nContact Number: " + contactNumber;
    }
    
}
