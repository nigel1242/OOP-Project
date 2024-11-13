package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.Controller;
import controller.MainFrame;
import data.Datastorage;

public class Checkout extends JPanel {
    private MainFrame main;
    private Controller cont;
    private Datastorage ds;
    
    private JList<String> orderList;
    private DefaultListModel<String> orderListModel;
    private JScrollPane orderScrollPane;
    private JLabel lblPrice;
    private JTextField textField;
    private boolean validPromoCode = false;

    public Checkout(MainFrame main, Controller cont) {
    	setBackground(new Color(255, 240, 245));
        this.main = main;
        this.cont = cont;
        this.ds = cont.getDatastorage();
        
        setLayout(null);
        initializeComponents();
        populateOrderList();	// populate order list so it doesnt clear when going to another screen
        updateTotalPrice();		// update total price so it doesnt clear when going to another screen
    }

    private void initializeComponents() {
        orderListModel = ds.getOrderListModel();	// get orderList from datastorage

        orderList = new JList<>(orderListModel);
        orderList.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        orderScrollPane = new JScrollPane(orderList);
        orderScrollPane.setBounds(20, 50, 943, 222);
        add(orderScrollPane);

        JLabel lblConfirm = new JLabel("Please confirm your order");
        lblConfirm.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblConfirm.setBounds(309, 16, 268, 25);
        add(lblConfirm);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
        
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                main.showOrderMenu();
            }
        });
        btnBack.setBounds(25, 365, 89, 34);
        add(btnBack);

        lblPrice = new JLabel("Total price: $");
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPrice.setBounds(742, 298, 221, 34);
        add(lblPrice);

        JButton btnPay = new JButton("Click to Pay");
        btnPay.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnPay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                main.showPaymentScreen();
            }
        });
        btnPay.setBounds(763, 348, 200, 51);
        add(btnPay);

        textField = new JTextField();
        textField.setBounds(195, 306, 153, 34);
        add(textField);
        textField.setColumns(10);

        JLabel lblPromo = new JLabel("Enter promo code:");
        lblPromo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPromo.setBounds(20, 311, 176, 20);
        add(lblPromo);

        JButton btnEnter = new JButton("Enter code");
        btnEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                applyPromoCode();
            }
        });
        btnEnter.setBounds(363, 306, 139, 34);
        add(btnEnter);
    }

    private void applyPromoCode() {		//code for applying promocode
        String promoCode = textField.getText();

        if (promoCode.equals("DISCOUNT10") && validPromoCode) {		// if promocode already entered
            JOptionPane.showMessageDialog(null, "Promo code already applied.");
        } else if (promoCode.equals("DISCOUNT10")) {				// correct promocode
            validPromoCode = true;
            updateTotalPrice();
            JOptionPane.showMessageDialog(null, "Promo code applied successfully!");
        } else {													// wrong promocode
            JOptionPane.showMessageDialog(null, "Invalid promo code.");
        }
    }

    private void populateOrderList() {	// populates orderList
        orderList.clearSelection();
        orderList.setModel(orderListModel);
    }

    public void updateTotalPrice() {	// updates price with promocode
        double price = cont.calculateTotalPrice();
        if (validPromoCode) {
            price *= 0.9; // Apply a 10% discount
        }
        lblPrice.setText("Total price: $" + String.format("%.2f", price));
    }
}
