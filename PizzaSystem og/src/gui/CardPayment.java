package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import controller.Controller;
import controller.MainFrame;
import data.Visa;
import data.Datastorage;
import data.MasterCard;
import java.awt.Color;
import java.awt.Font;

public class CardPayment extends JPanel {
    private MainFrame main;
    private Datastorage ds;
    
    private JTextField txtCardNumber;
    private JTextField txtCVV;
    private JTextField txtMonth;
    private JTextField txtYear;
    private JComboBox<String> cbCardType;
    private JButton btnBack;
    private JLabel lblVisa;
    private JLabel lblMaster;

    public CardPayment(MainFrame main, Controller cont) {
        setBackground(new Color(255, 240, 245));
        this.main = main;
        this.ds = cont.getDatastorage();
        
        setLayout(null);
        initializeComponents();
    }

    private void initializeComponents() {
        JLabel lblChooseCardType = new JLabel("Choose Card Type:");
        lblChooseCardType.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblChooseCardType.setBounds(32, 65, 184, 28);
        add(lblChooseCardType);

        cbCardType = new JComboBox<>();
        cbCardType.setModel(new DefaultComboBoxModel<>(new String[] {"Visa", "MasterCard"}));
        cbCardType.setBounds(220, 53, 184, 56);
        add(cbCardType);

        JButton btnEnter = new JButton("Enter");
        btnEnter.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnEnter.setBounds(382, 312, 135, 49);
        btnEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (areFieldsFilled()) {
                    processPayment();
                } else {
                    JOptionPane.showMessageDialog(CardPayment.this, "Please fill in all fields before clicking Enter.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(btnEnter);

        JLabel lblDigits = new JLabel("16 digits");
        lblDigits.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblDigits.setBounds(32, 145, 96, 41);
        add(lblDigits);

        txtCardNumber = new JTextField();
        txtCardNumber.setBounds(143, 147, 331, 41);
        txtCardNumber.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txtCardNumber.getText().length() >= 16 || !Character.isDigit(e.getKeyChar())) {
                    e.consume(); // Ignore this event if card number is already 16 digits or not a digit
                }
            }
        });
        add(txtCardNumber);
        txtCardNumber.setColumns(10);

        JLabel lblCvv = new JLabel("CVV");
        lblCvv.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblCvv.setBounds(32, 236, 69, 20);
        add(lblCvv);

        txtCVV = new JTextField();
        txtCVV.setBounds(143, 231, 146, 34);
        txtCVV.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txtCVV.getText().length() >= 3 || !Character.isDigit(e.getKeyChar())) {
                    e.consume(); // Ignore this event if CVV is already 3 digits or not a digit
                }
            }
        });
        add(txtCVV);
        txtCVV.setColumns(10);

        JLabel lblExpiry = new JLabel("Expiry");
        lblExpiry.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblExpiry.setBounds(311, 231, 69, 31);
        add(lblExpiry);

        txtMonth = new JTextField();
        txtMonth.setText("MM");
        txtMonth.setBounds(382, 235, 60, 26);
        txtMonth.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txtMonth.getText().length() >= 2 || !Character.isDigit(e.getKeyChar())) {
                    e.consume(); // Ignore this event if month is already 2 digits or not a digit
                }
            }
        });
        add(txtMonth);
        txtMonth.setColumns(10);

        txtYear = new JTextField();
        txtYear.setText("YY");
        txtYear.setBounds(457, 235, 60, 26);
        txtYear.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txtYear.getText().length() >= 2 || !Character.isDigit(e.getKeyChar())) {
                    e.consume(); // Ignore this event if year is already 2 digits or not a digit
                }
            }
        });
        add(txtYear);
        txtYear.setColumns(10);
        
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.showPaymentScreen();
            }
        });
        btnBack.setBounds(25, 365, 89, 34);
        add(btnBack);
        
        lblVisa = new JLabel("New label");
        Image img = new ImageIcon(this.getClass().getResource("/visa.jpeg")).getImage();
        Image modifiedImg = img.getScaledInstance(308, 253, Image.SCALE_SMOOTH);
        lblVisa.setIcon(new ImageIcon(modifiedImg));
        lblVisa.setBounds(573, 53, 308, 143);
        add(lblVisa);
        
        lblMaster = new JLabel("New label");
        Image img1 = new ImageIcon(this.getClass().getResource("/master.png")).getImage();
        Image modifiedImg1 = img1.getScaledInstance(308, 205, Image.SCALE_SMOOTH);
        lblMaster.setIcon(new ImageIcon(modifiedImg1));
        lblMaster.setBounds(573, 187, 309, 229);
        add(lblMaster);
        
        JLabel lblWeAccept = new JLabel("We accept:");
        lblWeAccept.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblWeAccept.setBounds(573, 16, 208, 35);
        add(lblWeAccept);
    }

    private boolean areFieldsFilled() {
        return !txtCardNumber.getText().isEmpty() &&
               !txtCVV.getText().isEmpty() &&
               !txtMonth.getText().isEmpty() &&
               !txtYear.getText().isEmpty();
    }

    private void processPayment() {
        String cardType = (String) cbCardType.getSelectedItem(); // Get selected card type
        String cardNumber = txtCardNumber.getText();
        String cvv = txtCVV.getText(); // CVV as String
        int month = Integer.parseInt(txtMonth.getText());
        int year = Integer.parseInt(txtYear.getText());

        boolean isValid = false;
        if (cardType.equals("Visa")) {
            isValid = Visa.validate(cardNumber, cvv, month, year);
        } else if (cardType.equals("MasterCard")) {
            isValid = MasterCard.validate(cardNumber, cvv, month, year);
        }

        if (isValid) {
            JOptionPane.showMessageDialog(CardPayment.this, "Payment received! Please wait while we prepare your order.", "Success", JOptionPane.INFORMATION_MESSAGE);
            ds.clearOrderList(); // Clear the order list
            main.showCustomerScreen(); // back to customer screen
        } else {
            JOptionPane.showMessageDialog(CardPayment.this, "Invalid card details, please enter correctly.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
