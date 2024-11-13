package gui;

import javax.swing.JPanel;

import controller.Controller;
import controller.MainFrame;
import data.Datastorage;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CustomerScreen extends JPanel {
    private MainFrame main;
    private Datastorage ds;

    public CustomerScreen(MainFrame main, Controller cont) {
    	setBackground(new Color(255, 240, 245));
        this.main = main;
        this.ds = cont.getDatastorage();
        
        setLayout(null);
        initializeComponents();
    }

    private void initializeComponents() {
        JLabel lblWelcome = new JLabel("Welcome to Temasek Pizzas!");
        lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblWelcome.setBounds(300, 46, 393, 68);
        add(lblWelcome);
        
        JButton btnOrder = new JButton("Click here to order");
        btnOrder.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnOrder.setBounds(378, 181, 222, 68);
        btnOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showOrderMenu();
            }
        });
        add(btnOrder);
        
        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnBack.setBounds(25, 365, 89, 34);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showLogin();
                ds.clearOrderList();
            }
        });
        add(btnBack);
    }

    // These methods delegate view change actions to MainFrame
    private void showOrderMenu() {
        if (main != null) {
            main.showOrderMenu();
        }
    }

    private void showLogin() {
        if (main != null) {
            main.showLogin();
        }
    }

}
