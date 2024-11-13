package gui;

import javax.swing.*;
import controller.Controller;
import controller.MainFrame;
import data.Datastorage;
import data.Food;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class OrderMenu extends JPanel {
    private MainFrame main;
    private Controller cont;
    private Datastorage ds;

    private JList<String> menuList;
    private JList<String> orderList;
    private DefaultListModel<String> menuListModel;
    private DefaultListModel<String> orderListModel;
    private JScrollPane menuScrollPane;
    private JScrollPane orderScrollPane;
    private JButton btnAddPizza;
    private JButton btnRemovePizza;
    private JTextField txtQuantity;
    private JButton btnMenu;
    private JLabel lblQuantity;
    private JLabel lblPrice;
    private JButton btnCheckout;

    public OrderMenu(MainFrame main, Controller cont) {
    	setBackground(new Color(255, 240, 245));
        this.main = main;
        this.cont = cont;
        this.ds = cont.getDatastorage();
        
        setLayout(null);
        initializeComponents();
        updateTotalPrice();
        populateMenuList();
    }

    private void initializeComponents() {
        // Initializing the list models
        menuListModel = new DefaultListModel<>();
        orderListModel = ds.getOrderListModel();

        // Creating the JLists with the list models
        menuList = new JList<>(menuListModel);
        menuList.setFont(new Font("Tahoma", Font.PLAIN, 17));
        orderList = new JList<>(orderListModel);
        orderList.setFont(new Font("Tahoma", Font.PLAIN, 17));

        menuScrollPane = new JScrollPane(menuList);
        orderScrollPane = new JScrollPane(orderList);
        menuScrollPane.setBounds(25, 30, 416, 160);
        orderScrollPane.setBounds(534, 30, 426, 160);
        add(menuScrollPane);
        add(orderScrollPane);

        JLabel lblMenu = new JLabel("Menu");
        lblMenu.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblMenu.setBounds(203, 5, 67, 23);
        add(lblMenu);

        JLabel lblOrder = new JLabel("Your Order");
        lblOrder.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblOrder.setBounds(700, 5, 125, 23);
        add(lblOrder);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                main.showCustomerScreen();
            }
        });
        btnBack.setBounds(25, 365, 89, 34);
        add(btnBack);

        btnCheckout = new JButton("Checkout");
        btnCheckout.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnCheckout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (orderListModel.isEmpty()) {		// if no ordered items
                    JOptionPane.showMessageDialog(main, "Please add items to your order before proceeding to checkout.", "Empty Order", JOptionPane.WARNING_MESSAGE);
                } else {		// show checkout page
                    main.showCheckout();
                }
            }
        });
        btnCheckout.setBounds(782, 271, 177, 71);
        add(btnCheckout);

        btnAddPizza = new JButton("+");
        btnAddPizza.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnAddPizza.setBounds(220, 271, 50, 30);
        btnAddPizza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adjustPizzaQuantity(true);
            }
        });
        add(btnAddPizza);

        btnRemovePizza = new JButton("-");
        btnRemovePizza.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnRemovePizza.setBounds(273, 271, 50, 30);
        btnRemovePizza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adjustPizzaQuantity(false);
            }
        });
        add(btnRemovePizza);

        txtQuantity = new JTextField("1");
        txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtQuantity.setBounds(133, 271, 60, 27);
        add(txtQuantity);

        btnMenu = new JButton("Menu");
        btnMenu.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMenuPictures();
            }
        });
        btnMenu.setBounds(534, 271, 177, 71);
        add(btnMenu);

        lblQuantity = new JLabel("Quantity:");
        lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblQuantity.setBounds(25, 268, 95, 33);
        add(lblQuantity);

        lblPrice = new JLabel("Price: $0.00");
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPrice.setBounds(782, 206, 203, 33);
        add(lblPrice);

        populateMenuList();
    }

    private void populateMenuList() {		// populate menuList
        Food[] foods = main.getController().getFood();
        menuListModel.clear();

        for (Food food : foods) {
            if (food.getPizzaName() != null && !food.getPizzaName().isEmpty()) {
                String item = food.getPizzaName() + ", Price: " + food.getPizzaPrice();
                menuListModel.addElement(item);
            } else if (food.getDrinkName() != null && !food.getDrinkName().isEmpty()) {
                String item = food.getDrinkName() + ", Price: " + food.getDrinkPrice();
                menuListModel.addElement(item);
            }
        }
    }

    private void adjustPizzaQuantity(boolean increase) {
        String selectedMenuItem = menuList.getSelectedValue();
        if (selectedMenuItem != null) { // if item selected from menu
            String[] itemDetails = selectedMenuItem.split(", "); // format it
            String itemName = itemDetails[0]; // name
            double itemPrice;
            int quantity;
            itemPrice = Double.parseDouble(itemDetails[1].split(": ")[1]); // price
            quantity = Integer.parseInt(txtQuantity.getText()); // price to int
            
            int indexInOrder = -1;
            for (int i = 0; i < ds.getOrderListModel().getSize(); i++) { 	// add to orderList
                String orderItem = ds.getOrderListModel().getElementAt(i);
                if (orderItem.startsWith(itemName)) {
                    indexInOrder = i;
                    break;
                }
            }

            if (indexInOrder != -1) { // if list is filled
                String orderItem = ds.getOrderListModel().getElementAt(indexInOrder);
                String[] orderItemDetails = orderItem.split(", "); // format it
                int currentQuantity;
                currentQuantity = Integer.parseInt(orderItemDetails[2].split(": ")[1]); // current qty to int
                if (increase) { // add qty to orderList
                    ds.getOrderListModel().setElementAt(itemName + ", Price: " + itemPrice + ", Quantity: " + (currentQuantity + quantity), indexInOrder);
                } else if (currentQuantity > quantity) { // delete qty from orderList
                    ds.getOrderListModel().setElementAt(itemName + ", Price: " + itemPrice + ", Quantity: " + (currentQuantity - quantity), indexInOrder);
                } else { 		// delete whole item from orderlist
                    cont.removeItemFromOrder(indexInOrder);
                }
            } else if (increase) {
                cont.addItemToOrder(itemName + ", Price: " + itemPrice + ", Quantity: " + quantity);
            }
            updateTotalPrice();
        }
    }

    private void updateTotalPrice() {
        double totalPrice = cont.calculateTotalPrice();
        lblPrice.setText("Subtotal: $" + String.format("%.2f", totalPrice));
    }

    private void showMenuPictures() {
        MenuPictures menuPicturesWindow = new MenuPictures();
        menuPicturesWindow.setVisible(true); // Open a new window for menu pictures
    }
}
