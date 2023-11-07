public class Patron
{
    protected String patronName, patronId, contactNumber;

    public Patron(String patronName, String patronId, String contactNumber)
    {
        setPatronDetail(patronName, patronId, contactNumber);
    }

    public void setPatronDetail(String patronName, String patronId, String contactNumber)
    {
        this.patronName = patronName;
        this.patronId = patronId;
        this.contactNumber = contactNumber;
    }

    public String toString()
    {
        return "User Name: " + patronName + "\n\nUser Id: " + patronId + "\n\nContact Number: " + contactNumber;
    }
    
}
