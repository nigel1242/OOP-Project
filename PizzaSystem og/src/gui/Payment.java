package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import controller.Controller;
import controller.MainFrame;
import data.Datastorage;
import java.awt.Font;
import java.awt.Color;

public class Payment extends JPanel {
    private MainFrame main;
	private Datastorage ds;

    public Payment(MainFrame main, Controller cont) {
    	setBackground(new Color(255, 240, 245));
        this.main = main;
        this.ds = cont.getDatastorage();
        
        setLayout(null);
        initializeComponents();
    }

    private void initializeComponents() {
    	JLabel lblPlease = new JLabel("Choose payment method");
        lblPlease.setFont(new Font("Tahoma", Font.PLAIN, 36));
        lblPlease.setBounds(297, 39, 429, 70);
        add(lblPlease);
        
        JButton btnCash = new JButton("Cash");
        btnCash.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		processPayment();
        	}
        });
        btnCash.setFont(new Font("Tahoma", Font.PLAIN, 24));
        btnCash.setBounds(170, 184, 243, 103);
        add(btnCash);
        
        
        
        JButton btnCard = new JButton("Debit/Credit Card");
        btnCard.setFont(new Font("Tahoma", Font.PLAIN, 24));
        btnCard.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		main.showCardPayment();
        	}
        });
        btnCard.setBounds(559, 184, 243, 103);
        add(btnCard);
        
        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
        
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		main.showCheckout();
        	}
        });
        btnBack.setBounds(25, 365, 89, 34);
        add(btnBack);
    }
    
    private void processPayment() {
    	JOptionPane.showMessageDialog(Payment.this, "Order received, please pay at the counter.", "Success", JOptionPane.INFORMATION_MESSAGE);
    	ds.clearOrderList(); // Clear the order list
        main.showCustomerScreen(); // back to customer screen
    }
}
