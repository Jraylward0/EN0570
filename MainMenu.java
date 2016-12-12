
/**
 * MainMenu Class
 * 
 * @author Jacob Aylward 
 * @version Version.1
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.LinkedList;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class MainMenu extends JFrame implements ActionListener {
    // instance variables - Data Collection
    private Map<Integer, Customer> customers;
    private Map<Integer, Item> items;
    private List<Order> orders;
    private int newOrderID = 0;
    // instance variables - Graphical User Interface
    private OrderDialog orderDialog;
    private FillDialog fillDialog;
    private JButton btnReadData, btnSaveData;
    private JButton btnTakeOrders, btnFillOrders, btnListOrders;
    private JButton btnListStock;
    /**
     * main() Launch Application (Graphical User Interface) 
     * @param n/a
     * @return n/a
     */
    public static void main(String[] args) {
        MainMenu app = new MainMenu();
        app.setVisible(true);
    }
    /**
     * Constructor MainMenu()
     * @param n/a
     * @return n/a
     */
    public MainMenu() {
        // initialise instance variables - Database
        customers   = new HashMap<Integer, Customer>();
        items       = new HashMap<Integer, Item>();
        orders      = new LinkedList<Order>();
        // initialise instance variables - (GUI) Customer
        orderDialog = new OrderDialog(this);
        fillDialog  = new FillDialog(this);
        // initialise instance variables - (GUI) Window setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 100, 250, 300);
        // initialise instance variables - (GUI) Grid layout
        JPanel mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(6, 1));
        // initialise instance variables - (GUI) Button read data
        btnReadData = new JButton("Read data");
        btnReadData.addActionListener(this);
        mainPnl.add(btnReadData);
        // initialise instance variables - (GUI) Button take orders
        btnTakeOrders = new JButton("Take orders");
        btnTakeOrders.addActionListener(this);
        mainPnl.add(btnTakeOrders);
        // initialise instance variables - (GUI) Button fill orders
        btnFillOrders = new JButton("Fill orders");
        btnFillOrders.addActionListener(this);
        mainPnl.add(btnFillOrders);
        // initialise instance variables - (GUI) Button list orders
        btnListOrders = new JButton("List orders");
        btnListOrders.addActionListener(this);
        mainPnl.add(btnListOrders);
        // initialise instance variables - (GUI) Button list stock
        btnListStock = new JButton("List stock");
        btnListStock.addActionListener(this);
        mainPnl.add(btnListStock);
        // initialise instance variables - (GUI) Button save data
        btnSaveData = new JButton("Save data");
        btnSaveData.addActionListener(this);
        mainPnl.add(btnSaveData);
        // initialise instance variables - (GUI) Border layout
        add(mainPnl, BorderLayout.CENTER);
        // Example initialise instance variable : x = 0;
    }// end constuctor MainMenu()
    // accessors - Data structures
    public Map<Integer, Customer> getCustomers() { return customers; }
    public Map<Integer, Item> getItems() { return items; }
    public List<Order> getOrders() {return orders; }
    /**
     * useNewOrder() method - Incremets static var newOrderID, value = ID
     * @param n/a
     * @return newOrderID
     */
    public int useNewOrderID() {
        // code
        newOrderID++;// auto increments variable newOrderID
        return newOrderID;
    }// end useNewOrder() method
    /**
     * actionPerformed() method
     * @param ActionEvent evt
     * @return n/a
     */
    public void actionPerformed(ActionEvent evt) {
        // code
        Object src = evt.getSource();
        if (src == btnReadData) {
            readCustomerData();// read customers - initialise the system data
            readItemData();// read items - initialise the system data
            readSavedOrders();// read orders - initialise the system data
            newOrderID = 0;// initialisation of newOrderID
            for (Order o: orders) { 
                if (o.getID() >= newOrderID)
                newOrderID = o.getID();
            }
            btnReadData.setEnabled(false);
        }
        else if (src == btnTakeOrders) {
            orderDialog.setVisible(true);// dialog - multiple orders
        }
        else if (src == btnFillOrders) {
            fillDialog.setVisible(true);// dialog - iteration through orders
        }
        else if (src == btnListStock) {
            listStock();// dialog - itheration through orders
        }
        else if (src == btnListOrders) {
            listOrders();// dialog - iteration through orders
        }
        else if (src == btnSaveData) {
            saveItems();
            saveOrders();
        }
    }// end actionPerformed() method
    /**
     * readCustomerData() method
     * @param n/a
     * @return n/a
     */
    public void readCustomerData() {
        String firstName = "", surname = "", postCode = "";
        int num = 0, id = 1;
        try {
            Scanner scnr = new Scanner(new File("customers.txt"));
            scnr.useDelimiter("\\s*#\\s*");// Notes: octothorpe starts line
            while (scnr.hasNextInt()) {
                id          = scnr.nextInt();
                surname     = scnr.next();
                firstName   = scnr.next();
                num         = scnr.nextInt();
                postCode    = scnr.next();
                String zip  = "^[A-Z]{2}[0-9]{1,2}\\s[0-9][A-Z]{2}$";
                Pattern pattern = Pattern.compile(zip);
                Matcher matcher = pattern.matcher(postCode);
                if (customers.containsKey(id)) {
                    System.out.println("Customer ID: must be unique.\n");
                }
                else if (id <= 0) {
                    System.out.println("Customer ID: a positive integer.\n");
                }
                else if (surname.equals("")) {
                    System.out.println("Surname: a string; cannot be empty." +id + "\n");
                }
                else if (firstName.equals("")) {
                    System.out.println("First name: a string; cannot be empty." +id + "\n");
                }
                else if (num <= 0) {
                    System.out.println("House number: a positive integer." + id + "\n");
                }
                else if ((matcher.matches() == false)) {
                    System.out.println("Postcode: constist of two uppercase letters followed"
                    + " by one or two digits, a space, a digit," 
                    + " and two more uppercase letters." +id);
                }
                else {
                    customers.put(new Integer(id), new Customer(id, surname, firstName, num, postCode));
                }
            }
            scnr.close();
        } catch (NoSuchElementException e) {
            System.out.printf("%d %s %s %d %s\n", id, surname, firstName, num, postCode);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Fetch: next tolen failed ",
            JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error ",
            JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error ",
            JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "File: not found ", "Error ",
            JOptionPane.ERROR_MESSAGE);
        }
        listCustomers();
    }// end readCutomerData() method
    /**
     * listCustomers() method
     * @param n/a
     * @return
     */
    public void listCustomers() {
        System.out.println("Customers: ");
        for (Customer b: customers.values()) {
            System.out.println(b);
        }
        System.out.println("\n");
    }// end listCustomers() method
    /**
     * readItemData() method
     * @param
     * @return
     */
    public void readItemData() {
        String description = "";
        double price = 0.0;
        int id = 0, stockLevel = 0, reorderLevel = 0;
        try {
            Scanner scnr = new Scanner(new File("items.txt"));
            scnr.useDelimiter("\\s*#\\s*");
            while (scnr.hasNextInt()) {
                id              = scnr.nextInt();
                description     = scnr.next();
                price           = scnr.nextDouble();
                stockLevel      = scnr.nextInt();
                reorderLevel    = scnr.nextInt();
                if (items.containsKey(id)) {
                    System.out.println("Item ID: must be unique.\n");
                }
                else if (id <= 0) {
                    System.out.println("ItemID: a positive integer.\n");
                }
                else if (description.equals("")) {
                    System.out.println("Description: a string; cannot be empty." +id + "\n");
                }
                else if (price <= 0.00) {
                    System.out.println("Price: a double-precision number; must not be negative." +id + "\n");
                }
                else if (stockLevel <= 0) {
                    System.out.println("Stock level: an integer; must not be negative." +id + "\n");
                }
                else if (reorderLevel <= 0) {
                    System.out.println("Reorder level:an integer; must not be negative." +id + "\n");
                }
                else {
                    items.put(new Integer(id), new Item(id, description, price, stockLevel, reorderLevel));
                }
            }
            scnr.close();
        } catch (NoSuchElementException e) {
            System.out.printf("%06d %s %5.2f, %d, %d\n", id, description, price, stockLevel, reorderLevel);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Fetch: next token failed ",
            JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error ",
            JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error ",
            JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "File: not found ", "Error ",
            JOptionPane.ERROR_MESSAGE);
        }
        listItems();
    }// end readItemData() method
    /**
     * listItems() method
     * @param
     * @return
     */
    public void listItems() {
        System.out.println("Items: ");
        for (Item i: items.values()) {
            System.out.println(i);
        }
        System.out.println("\n");
    }// end listItems() method
    /**
     * listStock() method
     * @param
     * @return
     */
    public void listStock() {
        System.out.println("Stock: ");
        for (int i = 1; i <= items.size(); i++) {
            System.out.println(items.get(i));
        }
        System.out.println("\n");
    }// end listStock() method
    /**
     * listOrders() method
     * @param
     * @return
     */
    public void listOrders() {
        System.out.println("Orders: ");
        for(Order o: orders) {
            System.out.println(o);
        }
        System.out.println("\n");
    }// end listOrders() method
    /**
     * saveItems()
     * @param
     * @return
     */
    public void saveItems() {
        String description = "";
        double price = 0.0;
        int id = 1, stockLevel = 0, reorderLevel = 0;
        try {
            PrintWriter printWriter;
            printWriter = new PrintWriter(new FileOutputStream("items.txt"));
            System.out.println("Save items: succesfull");
            for (Item i: items.values()) {
                System.out.println("\n" + i.getItemID() + "# " + i.getDescription() + "# " +
                i.getPrice() + "# " + i.getNumInStock() + "# " + i.getReorderLevel());
            }
            printWriter.close();
        } catch (NoSuchElementException e) {
            System.out.printf("%06d %s %5.2f, %d, %d\n", id, description, price, stockLevel, reorderLevel);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Fetch: next token failed ",
            JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error ", 
            JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error ",
            JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "File: not found ", "Error ",
            JOptionPane.ERROR_MESSAGE);
        }
    }// end saveItems() class
    /**
     * saveOrders()
     * @param
     * @return
     */
    public void saveOrders() {
        int orderID = 1, quantity = 0;
        String item = "";
        String customer = "";
        long timeStamp = 0;
        try {
            PrintWriter printWriter;
            printWriter = new PrintWriter(new FileOutputStream("order.txt"));
            System.out.println("Save orders: succesfull");
            for (Order o: getOrders()) {
                System.out.println("Saved orders: \n");
                printWriter.println(o.getID() + "# " + o.getItem() + "# " + o.getCustomer() + "# " +
                o.getQty() + "# " + o.getTimeStamp());
            }
            printWriter.close();
        } catch (NoSuchElementException e) {
            System.out.printf("%d %s %s, %d, %d\n", orderID, item, customer, quantity, timeStamp);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Fetch: next token failed ",
            JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error ",
            JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error ",
            JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "File: not found ", "Error ",
            JOptionPane.ERROR_MESSAGE);
        }
    }// end saveOrders() class
    /**
     * readSavedOrders()
     * @param
     * @return
     */
    public void readSavedOrders() {
        int orderID = 1, quantity = 0;
        String item = "";
        String customer = "";
        long timeStamp = 0;
        try {
            Scanner scnr = new Scanner(new File("orders.txt"));
            scnr.useDelimiter("\\s*#\\s*");
            while (scnr.hasNextInt()) {
                orderID     = scnr.nextInt();
                item        = scnr.next();
                customer    = scnr.next();
                quantity    = scnr.nextInt();
                timeStamp   = scnr.nextInt();
                if (customers.containsKey(orderID)) {
                    System.out.println("Order ID: must be unique.\n");
                }
                else if (orderID <= 0) {
                    System.out.println("Order ID: a positive integer.\n");
                }
                else if (item.equals("")) {
                    System.out.println("Item: a string; cannot be empty." +orderID + "\n");
                }
                else if (customer.equals("")) {
                    System.out.println("Customer: a string; cannot be empty." +orderID + "\n");
                }
                else if (quantity <= 0) {
                    System.out.println("Quantity: a positive integer." +orderID + "\n");
                }
                else if (timeStamp <= 0) {
                    System.out.println("Time stamp: a positive integer." +orderID + "\n");
                }
                else {
                    orders.add(new Integer(orderID), new Order(orderID, items.get(item.toString()), customers.get(customer.toString()), quantity, timeStamp));
                }
            }
            scnr.close();
        } catch (NoSuchElementException e) {
            System.out.printf("%d %s %s, %d, %d\n", orderID, item, customer, quantity, timeStamp);
            JOptionPane.showMessageDialog(this, "Fetch: next token failed ", "Error ",
            JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error ",
            JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error ",
            JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "File: not found", "Error ",
            JOptionPane.ERROR_MESSAGE);
        }
    }// end readSavedOrders() class
}//end class