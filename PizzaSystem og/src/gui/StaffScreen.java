package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.Controller;
import controller.MainFrame;
import java.awt.Color;
import javax.swing.JLabel;

public class StaffScreen extends JPanel {

    private MainFrame main;
    private Controller cont;

    private JButton btnView;
    private JButton btnEdit;
    private JButton btnBack;
    private JLabel lblHello;

    public StaffScreen(MainFrame main, Controller cont) {
    	setBackground(new Color(255, 240, 245));
        this.main = main;
        this.cont = cont;
        
        setLayout(null);
        initializeComponents();
    }

    private void initializeComponents() {
        btnView = new JButton("View items added");
        btnView.setFont(new Font("Tahoma", Font.PLAIN, 24));
        btnView.setBounds(312, 126, 357, 80);
        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.showDispMenu();
            }
        });
        add(btnView);

        btnEdit = new JButton("Add/edit items");
        btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 24));
        btnEdit.setBounds(312, 255, 357, 80);
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.showEditItem();
            }
        });
        add(btnEdit);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnBack.setBounds(25, 365, 89, 34);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.showLogin();
            }
        });
        add(btnBack);
        
        lblHello = new JLabel("Please choose either options");
        lblHello.setFont(new Font("Tahoma", Font.PLAIN, 36));
        lblHello.setBounds(262, 40, 480, 45);
        add(lblHello);
    }
	}
