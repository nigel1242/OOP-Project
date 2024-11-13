package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import data.Food;
import controller.Controller;
import controller.MainFrame;

public class DispMenu extends JPanel {
    private MainFrame main;
    private Controller cont;
    
    private JList<String> itemList;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollPane;
    private JButton btnBack;
    private JLabel lblItem;

    public DispMenu(MainFrame main, Controller cont) {
    	setBackground(new Color(255, 240, 245));
        this.main = main;
        this.cont = cont;
        setLayout(null);
        initializeComponents();
        updateItemList();
    }

    private void initializeComponents() {
        listModel = new DefaultListModel<>();

        scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 68, 932, 234);
        add(scrollPane);

        itemList = new JList<>(listModel);
        itemList.setFont(new Font("Tahoma", Font.PLAIN, 20));
        scrollPane.setViewportView(itemList);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnBack.setBounds(25, 365, 89, 34);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                main.showStaffScreen();
            }
        });
        add(btnBack);
        
        lblItem = new JLabel("Added items");
        lblItem.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblItem.setBounds(425, 32, 164, 20);
        add(lblItem);
    }

    private void updateItemList() {
        listModel.clear();
        Food[] foods = cont.getFood();
        for (Food food : foods) {
            if (food.getPizzaName() != null) {	// retrieve pizzas
                listModel.addElement("Pizza - Name: " + food.getPizzaName() + ", Price: " + food.getPizzaPrice());
            } else if (food.getDrinkName() != null) {	// retrieve drinks
                listModel.addElement("Drink - Name: " + food.getDrinkName() + ", Price: " + food.getDrinkPrice());
            }
        }
    }
}
