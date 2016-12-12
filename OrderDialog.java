/**
 * OrderDialog Class
 * 
 * @author Jacob Aylward 
 * @version Version.1
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class OrderDialog extends JDialog implements ActionListener {
    // instance variables - MainMenu (parent)
    private MainMenu parent;
    // instance variables - Graphical User Interface textfields
    private JTextField txtItemID;
    private JTextField txtCustomerID;
    private JTextField txtQty;
    // instance variables - Graphical User Interface buttons
    private JButton btnSubmit;
    private JButton btnCancel;
    /**
     * Constructor for objects of class OrderDialog()
     * @param MainMenu
     * @return
     */
    public OrderDialog(MainMenu p) {
        // initialise instance variables
        setTitle("Create Orders");
        parent = p;
        // initialise instance variables - components
        txtItemID       = new JTextField(10);
        txtCustomerID   = new JTextField(10);
        txtQty          = new JTextField(6);
        btnSubmit       = new JButton("Submit");
        btnCancel       = new JButton("Cancel");
        // initialise instance variables - layout
        JPanel pnl  = new JPanel();
        JPanel cpnl = new JPanel();
        pnl.add(new JLabel("Item code: "));
        pnl.add(txtItemID);
        cpnl.add(pnl);
        pnl = new JPanel();
        pnl.add(new JLabel("Customer ID: "));
        pnl.add(txtCustomerID);
        cpnl.add(pnl);
        pnl = new JPanel();
        pnl.add(new JLabel("Quantity: "));
        pnl.add(txtQty);
        cpnl.add(pnl);
        add(cpnl, BorderLayout.CENTER);
        pnl = new JPanel();
        pnl.add(btnSubmit);
        pnl.add(btnCancel);
        add(pnl, BorderLayout.SOUTH);
        setBounds(100, 100, 300, 200);
        // initialise instance variables - action
        btnSubmit.addActionListener(this);
        btnCancel.addActionListener(this);
    }// end constructor
    /**
     * actionPerformed() 
     * @param ActionEvent evt
     * @return
     */
    public void actionPerformed(ActionEvent evt) {
        // code
        Object src = evt.getSource();
        if (src == btnCancel) {
            setVisible(false);
            txtItemID.setText("");
            txtCustomerID.setText("");
            txtQty.setText("");
        }
        else if (src == btnSubmit) {
            processOrder();
            txtItemID.setText("");
            txtCustomerID.setText("");
            txtQty.setText("");
        }
    }// end actionPerformed()
    /**
     * processOrder()
     * @param
     * @return
     */
    public void processOrder() {
        try {
            Integer customerID  = new Integer(txtCustomerID.getText());
            Integer itemID      = new Integer(txtItemID.getText());
            Customer customer   = parent.getCustomers().get(customerID);
            Item item           = parent.getItems().get(itemID);
            int qty             = Integer.parseInt(txtQty.getText());
            if (customer == null) {
                JOptionPane.showMessageDialog(this, "Customer: not found ", "Error ",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (item == null) {
                JOptionPane.showMessageDialog(this, "Item: not found ", "Error ",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (qty == 0) {
                JOptionPane.showMessageDialog(this, "Quantity: a positive integer.", "Error ",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            parent.getOrders().add(new Order(parent.useNewOrderID(), item, customer, qty));
            System.out.printf("Order: %d of [%s][%s] successful\n", 
            qty, item.getDescription(), customer.getName());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Number format exception ",
            JOptionPane.ERROR_MESSAGE);
        }
    }// end processOrder()
}// end class