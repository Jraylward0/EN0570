
/**
 * FillDialog Class
 * 
 * @author Jacob Aylward
 * @version Version.1
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
public class FillDialog extends JDialog implements ActionListener {
    // instance variables
    private MainMenu parent;
    private Order orders;
    private Item items;
    // instance variables - Graphical User Interface
    private JTextField txtOrderID;
    private JTextField txtItem;
    private JTextField txtCustomer;
    private JTextField txtQty;
    private JTextField txtTimeStamp;
    private JButton btnNext, btnCancel, btnProcess;
    /**
     * Constructor for objects of class FillDialog
     * @param MainMenu p
     * @return
     */
    public FillDialog(MainMenu p) {
        // initialise instance variables
        setTitle("Fill orders");
        parent = p;
        // initialise instance variables - components
        txtOrderID      = new JTextField(10);
        txtItem         = new JTextField(10);
        txtCustomer     = new JTextField(10);
        txtQty          = new JTextField(6);
        txtTimeStamp    = new JTextField(10);
        // initialise instance variables - buttons
        btnNext         = new JButton("Next");
        btnCancel       = new JButton("Cancel");
        btnProcess      = new JButton("Process");
        // initialise instance variables - layout
        JPanel panel    = new JPanel();
        JPanel cpanel   = new JPanel();
        panel.add(new JLabel("Order ID: "));
        panel.add(txtOrderID);
        cpanel.add(panel);
        panel = new JPanel();
        panel.add(new JLabel("Item: "));
        panel.add(txtItem);
        cpanel.add(panel);
        panel = new JPanel();
        panel.add(new JLabel("Customer: "));
        panel.add(txtCustomer);
        cpanel.add(panel);
        panel = new JPanel();
        panel.add(new JLabel("Quantity: "));
        panel.add(txtQty);
        cpanel.add(panel);
        panel = new JPanel();
        panel.add(new JLabel("Time stamp: "));
        panel.add(txtTimeStamp);
        cpanel.add(panel);
        add(cpanel, BorderLayout.CENTER);
        panel = new JPanel();
        panel.add(btnNext);
        panel.add(btnCancel);
        panel.add(btnProcess);
        add(panel, BorderLayout.SOUTH);
        setBounds(100, 100, 300, 200);
        // initialise instance variables - button actions
        btnNext.addActionListener(this);
        btnCancel.addActionListener(this);
        btnProcess.addActionListener(this);
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
            txtOrderID.setText("");
            txtItem.setText("");
            txtCustomer.setText("");
            txtQty.setText("");
            txtTimeStamp.setText("");
        }
        else if (src == btnProcess) {
            OrderDetails();
            parent.getOrders();
        }
        else if (src == btnNext) {
            txtOrderID.setText("");
            txtCustomer.setText("");
            txtQty.setText("");
        }
    }// end actionPerformed()
    /**
     * OrderDetails()
     * @param
     * @return
     */
    public void OrderDetails() {
        Integer itemID          = new Integer(txtItem.getText());
        Item iID                = parent.getItems().get(itemID);
        itemID                  = iID.getItemID();
        
        String description      = new String(txtItem.getText());
        Item dsr                = parent.getItems().get(description);
        //description             = dsr.getDescription();
        
        Double price            = new Double(txtItem.getText());
        Item p                  = parent.getItems().get(price);
        //price                   = p.getPrice();
        
        Integer nStk            = new Integer(txtItem.getText());
        Item numStk             = parent.getItems().get(nStk);
        nStk                    = numStk.getNumInStock();

        Integer rLvl            = new Integer(txtItem.getText());
        Item reLvl              = parent.getItems().get(rLvl);
        rLvl                    = reLvl.getReorderLevel();
        
        Integer oID             = new Integer(txtItem.getText());
        //Order orderID           = parent.getOrders().get(oID);
        //oID                     = orderID.getID();
        
        Integer q               = new Integer(txtQty.getText());
        Order qty               = parent.getOrders().get(0);
        q                       = qty.getQty();
                         
        int newQty              = Integer.parseInt(txtQty.getText());
        if ((nStk -= q) > rLvl) {
            System.out.println("Order: complete.\n");
            
            
            Iterator it = parent.getOrders().iterator();
            while (it.hasNext()) {
                if (it.next().equals(orders)) {
                    //getItems().getNumberInStock();
                    it.remove();
                    //it.get();
                    //parent.getItems().get(new Item(itemID, description, price, nStk-q, rLvl));
                    //parent.getOrders().remove(0);
                }
                //parent.getOrders().remove(0);
            }
            
        }
        else if ((nStk -= q) <= rLvl) {
            System.out.println("Order: incomplete.\n");
        }
        
    }
}









