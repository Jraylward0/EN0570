/**
 * Order Class
 * 
 * @author Jacob Aylward 
 * @version Version.1
 */
import java.util.Date;
public class Order {
    // instance variables
    private int orderID;
    private Item item;
    private Customer customer;
    private int qty;
    private long timeStamp;
    /**
     * Constructor for objects of class Order
     * Order()
     * @param
     * @return
     */
    public Order(int oID, Item i, Customer c, int q, long ts) {
        // initialise instance variables
        orderID = oID;
        item = i;
        customer = c;
        qty = q;
        timeStamp = ts;
    }
    /**
     * Constructor 2
     * Order()
     * @param
     * @return
     */
    public Order(int oID, Item i, Customer c, int q) {
        // code
        this (oID, i, c, q, System.currentTimeMillis());
    }
    /**
     * Accessors
     */
    public int getID()              { return orderID; }
    public Item getItem()           { return item; }
    public Customer getCustomer()   { return customer; }
    public int getQty()             { return qty; }
    public long getTimeStamp()      { return timeStamp; }
    public void setQty(int q)       { qty = q; }
    public void updateTimeStamp()   { timeStamp = System.currentTimeMillis(); }
    public String toString() {
        return String.format("%06d: %d of [%s] ordered by %s on %s", orderID, qty, item.briefString(),
        customer.briefString(), new Date(timeStamp));
    }
}// end class
