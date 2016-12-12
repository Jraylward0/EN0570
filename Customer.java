/**
 * Customer Class
 * 
 * @author Jacob Aylward
 * @version Version.1
 */
public class Customer {
    // instance variables
    private int customerID;
    private String surname;
    private String firstName;
    private int houseNumber;
    private String postCode;
    /**
     * Constructor for objects of class Customer
     * Customer()
     * @param Integer customerID, String surname, String firstName, int houseNumber, String postCode
     * @return
     */
    public Customer(Integer cID, String sName, String fName, int hNum, String pCode) {
        // initialise instance variables
        customerID      = cID;
        surname         = sName;
        firstName       = fName;
        houseNumber     = hNum;
        postCode        = pCode;
    }
    /**
     * Accessors 
     */
    public Integer getCustomerID()      { return customerID; }
    public String getSurname()          { return surname; }
    public String getFirstName()        { return firstName; }
    public String getName()             { return surname + ", " + firstName; }
    public int getHouseNumber()         { return houseNumber; }
    public String getPostCode()         { return postCode; }
    /**
     * toString()
     * @param
     * @return
     */
    public String toString() {
        // code
        return String.format("%03d: %s %s (%d, %s)", customerID, firstName, surname, houseNumber, postCode);
    }
    /**
     * briefString()
     * @param
     * @return
     */
    public String briefString() {
        return String.format("%03d: %s %s", customerID, firstName,surname);
    }
}
