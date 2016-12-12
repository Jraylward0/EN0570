/**
 * Item() Class
 * 
 * @author Jacob Aylward
 * @version Version.1
 */
public class Item {
    // instance variables
    private Integer itemID;
    private String description;
    private double price;
    private int numInStock;
    private int reorderLevel;
    /**
     * Constructor for objects of class Item
     * Item()
     * @param Integer itemID, String description, double price, int numInStock, int reorderLevel
     * @return
     */
    public Item(Integer iID, String dsr, double p, int nStk, int rLvl) {
        // initialise instance variables
        itemID          = iID;
        description     = dsr;
        price           = p;
        numInStock      = nStk;
        reorderLevel    = rLvl;
    }
    /**
     * Accessors
     */
    public Integer getItemID()          { return itemID; }
    public String getDescription()      { return description; }
    public double getPrice()            { return price; }
    public void setPrice(double p)      { price = p; }
    public int getNumInStock()          { return numInStock; }
    public void addNumInStock(int n)    { numInStock += n; }
    public void subNumInStock(int n)    { numInStock -= n; }
    public int getReorderLevel()        { return reorderLevel; }
    /**
     * toString()
     * @param
     * @return string
     */
    public String toString() {
        // code
        return String.format("%05d: %s: %5.2f: %d in stock; min %d",
        itemID, description, price, numInStock, reorderLevel);
    }// end toString()
    /**
     * briefString()
     * @param
     * @return string
     */
    public String briefString() {
        return String.format("%05d: %s", itemID, description);
    }// end briefString
}