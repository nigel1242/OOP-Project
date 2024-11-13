package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.Controller;
import controller.MainFrame;

public class Login extends JPanel {

    private MainFrame main;
    private Controller cont;
    
    private JTextField textField;
    private JPasswordField passwordField;
    private JRadioButton rdbtnStaff;
    private JRadioButton rdbtnUser;
    private ButtonGroup userTypeGroup;

    public Login(MainFrame main, Controller cont) {
    	setBackground(new Color(255, 240, 245));
        this.main = main;
        this.cont = cont;
        setLayout(null);
        initializeComponents();
    }

    private void initializeComponents() {
        textField = new JTextField();
        textField.setBounds(455, 76, 250, 28);
        add(textField);
        textField.setColumns(10);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 36));
        lblUsername.setBounds(200, 68, 218, 28);
        add(lblUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 36));
        lblPassword.setBounds(200, 137, 204, 28);
        add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(455, 145, 250, 28);
        add(passwordField);

        rdbtnStaff = new JRadioButton("Staff");
        rdbtnStaff.setBackground(new Color(255, 240, 245));
        rdbtnStaff.setFont(new Font("Tahoma", Font.PLAIN, 24));
        rdbtnStaff.setBounds(455, 185, 89, 58);
        add(rdbtnStaff);

        rdbtnUser = new JRadioButton("Customer");
        rdbtnUser.setBackground(new Color(255, 240, 245));
        rdbtnUser.setFont(new Font("Tahoma", Font.PLAIN, 24));
        rdbtnUser.setBounds(572, 185, 133, 58);
        add(rdbtnUser);

        userTypeGroup = new ButtonGroup();
        userTypeGroup.add(rdbtnStaff);
        userTypeGroup.add(rdbtnUser);

        JButton btnRegister = new JButton("Click to register");
        btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnRegister.setBounds(470, 255, 218, 35);
        add(btnRegister);

        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });

        JButton btnStaff = new JButton("Staff login");
        btnStaff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleStaffLogin();
            }
        });
        btnStaff.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnStaff.setBounds(200, 342, 204, 53);
        add(btnStaff);

        JButton btnCustomer = new JButton("Customer login");
        btnCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleCustomerLogin();
            }
        });
        btnCustomer.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnCustomer.setBounds(492, 342, 196, 53);
        add(btnCustomer);

        JLabel lblNewLabel = new JLabel("Select which user to register:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel.setBounds(116, 197, 321, 35);
        add(lblNewLabel);
    }

    private void handleRegister() {
        String name = textField.getText();
        String password = new String(passwordField.getPassword());

        if (name.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username and Password cannot be empty.");
            return;
        }

        if (rdbtnStaff.isSelected()) {
            cont.registerUser(name, password, "staff");
            JOptionPane.showMessageDialog(null, "Staff registered successfully.");
        } else if (rdbtnUser.isSelected()) {
            cont.registerUser(name, password, "user");
            JOptionPane.showMessageDialog(null, "User registered successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Please select user type to register.");
        }

        textField.setText("");
        passwordField.setText("");
    }

    private void handleStaffLogin() {
        String name = textField.getText();
        String password = new String(passwordField.getPassword());

        if (name.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username and Password cannot be empty.");
            return;
        }

        if (cont.authenticateUser(name, password, "staff")) {
            textField.setText("");
            passwordField.setText("");
            main.showStaffScreen();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Staff credentials.");
        }
    }

    private void handleCustomerLogin() {
        String name = textField.getText();
        String password = new String(passwordField.getPassword());

        if (name.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username and Password cannot be empty.");
            return;
        }

        if (cont.authenticateUser(name, password, "user")) {
            textField.setText("");
            passwordField.setText("");
            main.showCustomerScreen();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Customer credentials.");
        }
    }
}
